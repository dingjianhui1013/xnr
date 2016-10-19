/**
 * 2012-10-10 上午3:46:48
 */
package com.xnradmin.core.action.common;


import java.io.IOException;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.action.ParentAction;
import com.xnradmin.core.service.StaffService;
import com.xnradmin.core.service.common.message.MessageService;
import com.xnradmin.constant.AjaxResult;
import com.xnradmin.constant.MsgType;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.message.MessageMsg;
import com.xnradmin.vo.message.MessageVO;

/**
 * @author: bin_liu
 */
@Controller
@Scope("prototype")
@Namespace("/message/msg")
public class MessageAction extends ParentAction{

    @Autowired
    private MessageService msgService;

    @Autowired
    private StaffService staffService;

    @Override
    public boolean isPublic(){
        // TODO Auto-generated method stub
        return false;
    }

    @Action(value = "info", results = {@Result(name = StrutsResMSG.SUCCESS, location = "/message/info.jsp")})
    public String info(){
        if(query == null)
            query = new MessageVO();
        String senderId = "";
        String receiverId = "";
        String recOrgId = "";
        String sendOrgId = "";
        if(query != null){
            if(query.getSender() != null && query.getSender().getId() != null)
                senderId = query.getSender().getId().toString();
            if(query.getReceiver() != null
                    && query.getReceiver().getId() != null)
                receiverId = query.getReceiver().getId().toString();
            if(query.getSendOrg() != null && query.getSendOrg().getId() != null)
                sendOrgId = query.getSendOrg().getId().toString();
            if(query.getRecOrg() != null && query.getRecOrg().getId() != null)
                recOrgId = query.getRecOrg().getId().toString();
        }

        this.voList = msgService.voList(senderId,receiverId,recOrgId,sendOrgId,
                getPageNum(),getNumPerPage(),null,null);
        this.totalCount = msgService.voListCount(senderId,receiverId,recOrgId,
                sendOrgId);
        return StrutsResMSG.SUCCESS;
    }

    @Action(value = "sendMsgInfo", results = {@Result(name = StrutsResMSG.SUCCESS, location = "/message/sendMsg.jsp")})
    public String sendMsgInfo(){
        return StrutsResMSG.SUCCESS;
    }

    @Action(value = "sendMsg")
    public String sendMsg(){
        try{
            String[] ids = StringHelper.splitStr(this.query.getRecStaffIds(),
                    ",");
            for(String e : ids){
                CommonStaff staff = staffService.findByid(e);
                this.query.setModelId(MsgType.USERMSG);
                this.query.setModelName(MsgType.getResultMsg(MsgType.USERMSG));
                this.query.setReceiver(staff);
                this.query.setSender(super.getCurrentStaff());
                this.query.getMsg().setMsgType(MsgType.USERMSG);
                this.query.getMsg().setMsgTypeName(
                        MsgType.getResultMsg(MsgType.USERMSG));
                int res = msgService.sendByVO(this.query);

                if(res == 0)
                    super.success(null,AjaxResult.CALL_BACK_TYPE_CLOSECURRENT,
                            "messageInfo",null);
                else if(res == 1)
                    super.error("发送短信时，标题+内容长度不能超过66个字（包括标点）");
                else if(res == 2)
                    super.error("短信发送失败");
                else if(res == -1)
                    super.error("传入参数错误");
                else if(res == -2)
                    super.error("接收人员的手机号和邮箱均未填写");

            }

        }catch(IOException e){
            try{
                super.error("内部错误");
            }catch(IOException e1){
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

        return null;
    }

    private MessageVO query;

    private List<MessageMsg> voList;

    public List<MessageMsg> getVoList(){
        return voList;
    }

    public void setVoList(List<MessageMsg> voList){
        this.voList = voList;
    }

    public MessageVO getQuery(){
        return query;
    }

    public void setQuery(MessageVO query){
        this.query = query;
    }

}
