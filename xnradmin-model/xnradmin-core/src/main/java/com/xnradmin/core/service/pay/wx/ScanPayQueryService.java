package com.xnradmin.core.service.pay.wx;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xnradmin.core.pay.wxpay.util.HttpClientUtil;
import com.xnradmin.po.pay.protocol.pay_query_protocol.ScanPayQueryReqData;
import com.xnradmin.po.wx.connect.WXfInit;

/**
 * User: rizenguo
 * Date: 2014/10/29
 * Time: 16:04
 */
public class ScanPayQueryService extends BaseService{

    private final static Logger LOG = LoggerFactory.getLogger(ScanPayService.class);
    
    public ScanPayQueryService() throws IllegalAccessException, InstantiationException, ClassNotFoundException, UnrecoverableKeyException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException {
        super(WXfInit.QUERY_URL);
    }

    /**
     * 请求支付查询服务
     * @param scanPayQueryReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @return API返回的XML数据
     * @throws Exception
     */
    public String request(ScanPayQueryReqData scanPayQueryReqData) throws Exception {

        //--------------------------------------------------------------------
        //发送HTTPS的Post请求到API地址
        //--------------------------------------------------------------------
        String responseString = HttpClientUtil.doXmlPostSSL(WXfInit.QUERY_URL, scanPayQueryReqData, "utf8");
        LOG.debug("response from wechat:{}", responseString);
        return responseString;
    }


}
