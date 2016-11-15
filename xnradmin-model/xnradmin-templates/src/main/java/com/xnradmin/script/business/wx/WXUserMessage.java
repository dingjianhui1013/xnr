/**
 *2014年9月9日 下午5:16:24
 */
package com.xnradmin.script.business.wx;

import org.apache.commons.logging.Log;
import org.dom4j.DocumentException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import com.cntinker.util.StringHelper;
import com.xnradmin.client.service.wx.WXUserService;
import com.xnradmin.client.service.wx.WxReactiveMessagePortService;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.core.util.Log4jUtil;
import com.xnradmin.core.util.SpringBase;
import com.xnradmin.core.util.WxUtil;
import com.xnradmin.dto.client.SyncDTOAck;
import com.xnradmin.vo.client.wx.WXReactiveMessageVO;

/**
 * @author: liubin
 *
 */
public class WXUserMessage {

	private Log log = Log4jUtil.getLog("scriptBusiness");

	private Log exLog = Log4jUtil.getLog("ex");

	@Autowired
	private WXUserService wxuserService;
	@Autowired
	private WxReactiveMessagePortService reactiveMessagePortService;
	@Autowired
	private StatusService statusService;

	public WXUserMessage() {
		wxuserService = (WXUserService) SpringBase.getCtx().getBean(
				"WXUserService");
		reactiveMessagePortService = (WxReactiveMessagePortService) SpringBase
				.getCtx().getBean("WxReactiveMessagePortService");
		statusService = (StatusService) SpringBase.getCtx().getBean(
				"StatusService");
	}

	/**
	 * 主入口
	 * 
	 * @return
	 * @throws JSONException
	 */
	public SyncDTOAck execute(Object... obj) throws JSONException {
		SyncDTOAck ack = new SyncDTOAck();
		String wxuserid = (String) obj[0];
		String source = (String) obj[1];
		WxUtil wu = new WxUtil(source);

		try {
			String msgType = wu.getDataFromKey("MsgType");
			WXReactiveMessageVO vo = null;
			if(msgType.equals("event")){
				vo =  reactiveMessagePortService.findByClick(
						wxuserid, wu.getDataFromKey("EventKey"));
			}else {
				vo = reactiveMessagePortService.findByInput(
						wxuserid, wu.getDataFromKey("Content"));
			}
			if(vo==null){
				ack.setWxOut("");
				return ack;
			}
			
			log.debug("WXUserMessage vo: " + vo);
			String res = reactiveMessagePortService.toWxPortMsg(source, vo);
			ack.setWxOut(res);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			exLog.error("错误:" + StringHelper.getStackInfo(e));
		} catch (DocumentException e) {
			e.printStackTrace();
			exLog.error("错误:" + StringHelper.getStackInfo(e));
		}

		log.debug("WXUserEvent script source:" + source + " | wxuserid:"
				+ wxuserid);

		return ack;
	}
}
