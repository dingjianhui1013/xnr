package com.xnradmin.client.action.front;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.core.pay.wxpay.util.Signature;
import com.xnradmin.core.pay.wxpay.util.Util;
import com.xnradmin.core.service.business.order.BusinessOrderGoodsRelationService;
import com.xnradmin.core.service.business.order.BusinessOrderRecordService;
import com.xnradmin.core.service.mall.commodity.GoodsAllocationShowService;
import com.xnradmin.core.service.pay.wx.ScanPayQueryService;
import com.xnradmin.po.business.BusinessOrderRecord;
import com.xnradmin.po.mall.commodity.GoodsAllocationShow;
import com.xnradmin.po.pay.protocol.pay_protocol.ScanPayCallBackReq;
import com.xnradmin.po.pay.protocol.pay_protocol.ScanPayCallBackRes;
import com.xnradmin.po.pay.protocol.pay_query_protocol.ScanPayQueryReqData;
import com.xnradmin.po.pay.protocol.pay_query_protocol.ScanPayQueryResData;
import com.xnradmin.vo.business.BusinessOrderRelationVO;

@Controller
@Scope("prototype")
@Namespace("/front/weixinPay")
@ParentPackage("json-default")
public class WeiXinPayAction  {

    private final static Logger LOG = LoggerFactory.getLogger(WeiXinPayAction.class);

    @Autowired
    private BusinessOrderRecordService orderRecordService;
    
    @Autowired
	private GoodsAllocationShowService allocationShowService;
    
    @Autowired
	private BusinessOrderGoodsRelationService businessOrderGoodsRelationService;
    
    
    private String outTradeNo;
    
    private String reqData;//支付结果通知 的数据 
    
    private  String result;//  支付结果通知 返回的结果
    
    private String status;//订单查询时返回的 状态
    private String errMeg;//错误信息

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrMeg() {
        return errMeg;
    }

    public void setErrMeg(String errMeg) {
        this.errMeg = errMeg;
    }
    
    

    public String getReqData() {
        return reqData;
    }

    public void setReqData(String reqData) {
        this.reqData = reqData;
    }

    /**
     * 微信返回的 支付结果通知
     * 
     * @param reqData
     * @param response
     * @return
     */
    @Action(value = "wechatNotifyURL", results = { @Result(name = StrutsResMSG.SUCCESS, type = "json") })
    public String wechatNotifyURL() {
        LOG.info("------->wechatPay notify:" + reqData);
        ScanPayCallBackRes notifyResData = new ScanPayCallBackRes();
        try {
            if (Signature.checkIsSignValidFromResponseString(reqData)) {
                ScanPayCallBackReq notifyReqData = (ScanPayCallBackReq) Util.getObjectFromXML(reqData, ScanPayCallBackReq.class);
                if (notifyReqData != null && "SUCCESS".equalsIgnoreCase(notifyReqData.getReturn_code())
                        && "SUCCESS".equalsIgnoreCase(notifyReqData.getResult_code())) {
                    BusinessOrderRecord businessOrderRecord =  orderRecordService.findByOutOderNo(notifyReqData.getOut_trade_no());
                    if (businessOrderRecord != null) {
                        notifyResData.setReturn_code("SUCCESS");
                        notifyResData.setReturn_msg("Already Notified!!!!");
                        //支付成功 保存有用的信息 改变订单状态
                        businessOrderRecord.setPaymentStatusName("支付完成");
                        businessOrderRecord.setPaymentStatus(200);
                        orderRecordService.modify(businessOrderRecord);
                        //修改库存 查出所有产品
                        List<BusinessOrderRelationVO> relaions = businessOrderGoodsRelationService.findByOrderRecordId(businessOrderRecord.getId());
                        for(BusinessOrderRelationVO br:relaions){
                        	GoodsAllocationShow allocationshow = allocationShowService.findByGoodsidToday(br.getBusinessGoods().getId()+"");
                        	int using = br.getOrderGoodsRelation().getGoodsCount();
                        	int surplus = allocationshow.getAllocationCount()-using<0?0:allocationshow.getAllocationCount()-using;
                        	allocationshow.setSaleCount(using);
                        	allocationshow.setSurplusCount(surplus);
                        	allocationShowService.save(allocationshow);
                        }
                    } else {
                        notifyResData.setReturn_code("FAIL");
                        notifyResData.setReturn_msg("Server Error");
                    }
                }
            } else {
                notifyResData.setReturn_code("FAIL");
                notifyResData.setReturn_msg("Sign Error");
            }
        } catch (Exception e) {
            LOG.error("wechatPay notify error:" + e.getMessage());
            e.printStackTrace();
            notifyResData.setReturn_code("FAIL");
            notifyResData.setReturn_msg(e.getMessage());
        }
        XStream xStreamForRequestPostData = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
        ServletActionContext.getResponse().setContentType("application/xml");
       this.result = xStreamForRequestPostData.toXML(notifyResData);
       this.result = StringUtils.replace(this.result, "com.tencent.protocol.apppay_protocol.NotifyResData", "xml");
        LOG.info("wechatPay notify response:" + this.result);
        return StrutsResMSG.SUCCESS;
    }

    /**
     * 查询订单详情
     * 
     * @param request
     * @param response
     * @param outTradeNo
     * @return
     */
    @Action(value = "queryOrder", results = { @Result(name = StrutsResMSG.SUCCESS, type = "json") })
    public String queryOrder() {
        try {
            ScanPayQueryReqData scanPayQueryReqData = new ScanPayQueryReqData("", outTradeNo);
            ScanPayQueryService service = new ScanPayQueryService();
            String res = service.request(scanPayQueryReqData);
            LOG.info("支付订单查询API返回的数据如下：");
            LOG.info(res);
            // 将从API返回的XML数据映射到Java对象
            ScanPayQueryResData scanPayQueryResData = (ScanPayQueryResData) Util.getObjectFromXML(res, ScanPayQueryResData.class);

            if (scanPayQueryResData == null || scanPayQueryResData.getReturn_code() == null) {
                LOG.info("支付订单查询请求逻辑错误，请仔细检测传过去的每一个参数是否合法");
                   this.status = "-1";
                   this.errMeg = "支付订单查询请求逻辑错误，请仔细检测传过去的每一个参数是否合法";
            } else {
                if (scanPayQueryResData.getReturn_code().equals("FAIL")) {
                    // 注意：一般这里返回FAIL是出现系统级参数错误，请检测Post给API的数据是否规范合法
                    LOG.info("支付订单查询API系统返回失败，失败信息为：" + scanPayQueryResData.getReturn_msg());
                    this.status = "-1";
                    this.errMeg = "支付订单查询API系统返回失败";
                } else {
                    if (scanPayQueryResData.getResult_code().equals("SUCCESS")) {// 业务层成功
                        if (scanPayQueryResData.getTrade_state().equals("SUCCESS")) {
                            // 表示查单结果为“支付成功”
                            LOG.info("查询到订单支付成功");
                            this.status = "1";
                            this.errMeg = "查询到订单支付成功";
                        } else {
                            // 支付不成功
                            LOG.info("查询到订单支付不成功");
                            this.status = "-1";
                            this.errMeg = "查询到订单支付不成功";
                        }
                    } else {
                        LOG.info("查询出错，错误码：" + scanPayQueryResData.getErr_code() + "错误信息：" + scanPayQueryResData.getErr_code_des());
                        this.status = "-1";
                        this.errMeg = "查询出错";
                    }
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return StrutsResMSG.SUCCESS;
    }
}
