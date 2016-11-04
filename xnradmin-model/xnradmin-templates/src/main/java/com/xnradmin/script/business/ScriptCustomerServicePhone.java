/**
 * 2014年2月9日 下午2:36:22
 */
package com.xnradmin.script.business;

import org.apache.commons.logging.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cntinker.util.JsonUtil;
import com.cntinker.util.StringHelper;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.core.util.Log4jUtil;
import com.xnradmin.core.util.SpringBase;
import com.xnradmin.dto.client.SyncDTOAck;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.common.status.Status;

/**
 * @author: xiang_liu
 */
public class ScriptCustomerServicePhone {

	private Log log = Log4jUtil.getLog("scriptBusiness");

	private Log exLog = Log4jUtil.getLog("ex");

	private StatusService statusService;

	public ScriptCustomerServicePhone() {

		statusService = (StatusService) SpringBase.getCtx().getBean(
				"StatusService");

	}

	// @Autowired
	// private ClientUserInfoService service;

	/**
	 * 主入口
	 * 
	 * @return
	 * @throws JSONException
	 */
	public SyncDTOAck execute(Object... obj) throws JSONException {
		SyncDTOAck ack = new SyncDTOAck();
		JSONObject jsonObject = (JSONObject) obj[0];
		ack = getCustomerServicePhone(jsonObject);
		return ack;
	}

	/**
	 * 取得客服电话
	 */
	public SyncDTOAck getCustomerServicePhone(JSONObject jsonObject)
			throws JSONException {
		SyncDTOAck ack = new SyncDTOAck();
		String action = jsonObject.get("action").toString();
		JSONObject out = new JSONObject();
		Status status = statusService.findStatus(CommonStaff.class,
				"customerServicePhone");
		out.put("customerServicePhone", status.getStatusName());
		out.put("action", action);
		ack.setJsonOut(out.toString());
		log.debug(ack);
		return ack;
	}

}
