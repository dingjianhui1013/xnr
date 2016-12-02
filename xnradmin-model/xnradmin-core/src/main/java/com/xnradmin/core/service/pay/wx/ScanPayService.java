package com.xnradmin.core.service.pay.wx;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.xnradmin.core.pay.wxpay.util.HttpClientUtil;
import com.xnradmin.core.pay.wxpay.util.Util;
import com.xnradmin.po.pay.protocol.pay_protocol.ScanPayReqData;
import com.xnradmin.po.pay.protocol.pay_protocol.ScanPayResData;
import com.xnradmin.po.wx.connect.WXfInit;

/**
 * 
 * @author tf
 *
 */
@Service
public class ScanPayService extends BaseService {

    private final static Logger LOG = LoggerFactory.getLogger(ScanPayService.class);

    public ScanPayService()
            throws IllegalAccessException, InstantiationException, ClassNotFoundException, UnrecoverableKeyException,
            KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException {
        super(WXfInit.UNIFIED_ORDER);
    }

    public String getCallOrderInfo(ScanPayReqData scanPayReqData) {
        // --------------------------------------------------------------------
        // 发送HTTPS的Post请求到API地址
        // --------------------------------------------------------------------
        String responseString = HttpClientUtil.doXmlPostSSL(WXfInit.UNIFIED_ORDER, scanPayReqData, "utf8");
        LOG.debug("response from wechat:{}", responseString);
        ScanPayResData uorResData = (ScanPayResData) Util.getObjectFromXML(responseString,
                ScanPayResData.class);
        Gson gson = new Gson();
        return gson.toJson(uorResData);
    }


}
