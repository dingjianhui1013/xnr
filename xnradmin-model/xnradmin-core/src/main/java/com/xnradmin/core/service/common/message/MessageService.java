/**
 * 2012-10-10 上午3:46:59
 */
package com.xnradmin.core.service.common.message;


import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntinker.util.MailSend;
import com.cntinker.util.StringHelper;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.constant.MsgType;
import com.xnradmin.po.CommonOrganization;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.message.MessageMsg;
import com.xnradmin.vo.message.MessageVO;

@Service("MessageService")
public class MessageService{

    static Log logEx = LogFactory.getLog("ex");

    static Log log = LogFactory.getLog("msg");

    @Autowired
    private CommonDAO dao;

    /**
     * return <br>
     * -2:接收人员的手机号和邮箱均未填写<br>
     * -1:传入参数错误<br>
     * 0:成功<br>
     * 1:发送短信时，标题+内容长度不能超过66个字（包括标点）<br>
     * 2:短信发送失败<br>
     * 
     * @param vo
     * @return int
     */
    public int sendByVO(MessageVO vo){
        if(vo == null || vo.getMsg() == null)
            return -1;
        if(!vo.isSendSms() && !vo.isSendMail())
            return -2;

        MessageMsg msg = vo.getMsg();

        if(vo.isSendSms() && vo.getReceiver() != null
                && !StringHelper.isNull(vo.getReceiver().getMobile())){

            int smsMsgLength = ( vo.getMsg().getTitle().trim() + vo.getMsg()
                    .getContent().trim() ).length();
            if(smsMsgLength > 66)
                return 1;
            // 考虑到群发时会有部分接收者没手机号，暂时跳过处理
            if(!StringHelper.isNull(vo.getReceiver().getMobile())){
                int act = this.sendSms(vo.getReceiver().getMobile(),vo.getMsg()
                        .getTitle().trim()
                        + vo.getMsg().getContent());
                if(act != 0){
                    return 2;
                }
            }
        }

        if(vo.getSender().getOrganizationId() != null){
            CommonOrganization org = findOrgByID(vo.getSender()
                    .getOrganizationId().toString());
            msg.setSendOrgName(org.getOrganizationName());
        }
        if(vo.getReceiver().getOrganizationId() != null){
            CommonOrganization org = findOrgByID(vo.getReceiver()
                    .getOrganizationId().toString());
            msg.setRecOrgName(org.getOrganizationName());
        }

        msg.setSenderId(vo.getSender().getId());
        msg.setSenderOrgid(vo.getSender().getOrganizationId());
        msg.setReceiverId(vo.getReceiver().getId());
        msg.setRecOrgid(vo.getReceiver().getOrganizationId());
        msg.setModelName(vo.getModelName());

        msg.setSenderName(vo.getSender().getStaffName());
        msg.setReceiverName(vo.getReceiver().getStaffName());

        msg.setRecMail(vo.getReceiver().getEmail());
        msg.setRecMobile(vo.getReceiver().getMobile());
        msg.setSendTime(new Timestamp(System.currentTimeMillis()));

        if(vo.isSendMail() && !StringHelper.isNull(vo.getReceiver().getEmail())){
            this.sendMail(vo.getSender().getStaffName(),vo.getMsg().getTitle(),
                    vo.getMsg().getContent(),vo.getReceiver().getEmail());
        }
        try{
            log.debug(msg);
            this.dao.save(msg);
        }catch(Exception e){
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * 外部接口 - 发送单条
     * 
     * @param sender
     * @param receiver
     * @param modelName
     * @param title
     * @param content
     */
    public void sendMsg(CommonStaff sender,CommonStaff receiver,
            String modelName,String title,String content){
        sendMsg(sender,receiver,modelName,title,content,MsgType.SYSMSG);
    }

    /**
     * 外部接口 - 群发
     * 
     * @param sender
     * @param receiver
     * @param modelName
     * @param title
     * @param content
     */
    public void sendMsg(CommonStaff sender,CommonStaff[] receiver,
            String modelName,String title,String content){
        for(CommonStaff e : receiver){
            sendMsg(sender,e,modelName,title,content,MsgType.SYSMSG);
        }
    }

    /**
     * 各模块发系统消息用
     * 
     * @param sender
     * @param receiver
     * @param orgId
     * @param title
     * @param content
     * @param status
     */
    private void sendMsg(CommonStaff sender,CommonStaff receiver,
            String modelName,String title,String content,Integer msgType){

        try{
            if(!StringHelper.isNull(receiver.getEmail())){
                sendMail(modelName,title,content,receiver.getEmail());
            }
            if(!StringHelper.isNull(receiver.getMobile())){
                sendSms(receiver.getMobile(),title + content);
            }
        }catch(Throwable e){
            e.printStackTrace();
            logEx.error(e);
        }

        MessageMsg msg = new MessageMsg();
        if(StringHelper.isNull(receiver.getEmail())
                && StringHelper.isNull(receiver.getMobile())){
            msg.setMsgType(MsgType.UNSEND);
            msg.setMsgTypeName(MsgType.getResultMsg(MsgType.UNSEND));
        }else{
            msg.setMsgType(msgType);
            msg.setMsgTypeName(MsgType.getResultMsg(msgType));
        }
        if(sender.getOrganizationId() != null){
            CommonOrganization org = findOrgByID(sender.getOrganizationId()
                    .toString());
            msg.setSendOrgName(org.getOrganizationName());
        }
        if(receiver.getOrganizationId() != null){
            CommonOrganization org = findOrgByID(receiver.getOrganizationId()
                    .toString());
            msg.setRecOrgName(org.getOrganizationName());
        }

        msg.setTitle(title);
        msg.setContent(content);
        msg.setSenderId(sender.getId());
        msg.setSenderOrgid(sender.getOrganizationId());
        msg.setReceiverId(receiver.getId());
        msg.setRecOrgid(receiver.getOrganizationId());
        msg.setModelName(modelName);

        msg.setSenderName(sender.getStaffName());
        msg.setReceiverName(receiver.getStaffName());

        msg.setRecMail(receiver.getEmail());
        msg.setRecMobile(receiver.getMobile());
        msg.setSendTime(new Timestamp(System.currentTimeMillis()));

        try{
            log.debug(msg);
            this.dao.save(msg);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private CommonOrganization findOrgByID(String orgid){
        Object obj = this.dao.findById(CommonOrganization.class,new Integer(
                orgid));
        if(obj == null)
            return null;
        else
            return (CommonOrganization) obj;
    }

    /**
     * 发送单封
     * 
     * @param sender
     * @param title
     * @param content
     * @param receiver
     * @return int
     */
    private int sendMail(String sender,String title,String content,
            String receiver){
        String[] to = new String[]{receiver};
        MailSend ms = new MailSend();
        String formName = "海恩信息系统 - " + sender;
        ms.sendSampleMail("qiye.163.com","smtp.qiye.163.com",
                "weixin@heinsaw.com","heinsaw1",formName,"weixin@heinsaw.com",
                to,title,content);
        return 0;
    }

    /**
     * 发送短信
     * 
     * @param mobile
     * @param content
     * @return int 0：成功 -1:API接口错误 -2：发送错误
     */
    private int sendSms(String mobile,String content){
//        SmsServiceStub smsStub = null;
//        try{
//            smsStub = new SmsServiceStub();
//        }catch(AxisFault e){
//            e.printStackTrace();
//            logEx.error(StringHelper.getStackInfo(e));
//            return -1;
//        }
//        content += "后缀";
//        SmsServiceStub.SendMessage msg = new SmsServiceStub.SendMessage();
//        msg.setUserId("2512");
//        msg.setPassword("123456");
//        msg.setMsg(content);
//        msg.setDestnumbers(mobile);
//
//        SmsServiceStub.SendMessageResponse res = null;
//        try{
//            res = smsStub.sendMessage(msg);
//        }catch(RemoteException e){
//            e.printStackTrace();
//            logEx.error(StringHelper.getStackInfo(e));
//            return -2;
//        }
//
//        log.debug("Send result:\t" + mobile + " status:"
//                + res.getSendMessageResult().getState() + "\t yd:"
//                + res.getSendMessageResult().getYDGroupId() + "\tlt:"
//                + res.getSendMessageResult().getLTGroupId() + "\terror:"
//                + res.getSendMessageResult().getWrongNumbers());
//
//        String smsSendLog = "SMS SEND[" + " | content: " + content
//                + " | mobile: " + mobile + "]";
//        log.debug(smsSendLog);
//        log.debug("smsSendLog: " + smsSendLog);
        return 0;
    }

    public List<MessageMsg> voList(String senderId,String receiverId,
            String recOrgId,String sendOrgId,int curPage,int pageSize,
            String orderField,String direction){
        String hql = getHql(senderId,receiverId,recOrgId,sendOrgId);
        List<MessageMsg> l = dao.getEntitiesByPropertiesWithHql(hql,curPage,
                pageSize);
        return l;
    }

    public int voListCount(String senderId,String receiverId,String recOrgId,
            String sendOrgId){
        String hql = "select count(*) "
                + getHql(senderId,receiverId,recOrgId,sendOrgId);
        return this.dao.getNumberOfEntitiesWithHql(hql);
    }

    private String getHql(String senderId,String receiverId,String recOrgid,
            String sendOrgId){
        // MessageMsg msg
        int flag = 0;
        StringBuffer sb = new StringBuffer();
        sb.append(" from MessageMsg");
        if(!StringHelper.isNull(senderId) || !StringHelper.isNull(receiverId)
                || !StringHelper.isNull(recOrgid)
                || !StringHelper.isNull(sendOrgId))
            sb.append(" where ");
        if(!StringHelper.isNull(senderId)){
            sb.append(" senderId=").append(senderId);
            flag ++ ;
        }
        if(!StringHelper.isNull(receiverId)){
            if(flag > 0)
                sb.append(" and ");
            sb.append(" receiverId=").append(receiverId);
            flag ++ ;
        }
        if(!StringHelper.isNull(recOrgid)){
            if(flag > 0)
                sb.append(" and ");
            sb.append(" recOrgid=").append(recOrgid);
            flag ++ ;
        }
        if(!StringHelper.isNull(sendOrgId)){
            if(flag > 0)
                sb.append(" and ");
            sb.append(" senderOrgid=").append(sendOrgId);
            flag ++ ;
        }

        sb.append(" order by id desc");

        return sb.toString();
    }

    public static void main(String[] args) throws Exception{
        // sendMail("管理员","测试标题","测试内容","liubin0821@126.com");
        // sendMail("管理员", "测试标题", "测试内容", new String[] { "liubin0821@126.com"
        // });
    }
}
