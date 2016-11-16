/**
 *2014年12月1日 下午3:15:02
 */
package com.xnradmin.core.service.business;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.core.util.Log4jUtil;
import com.xnradmin.vo.business.BusinessDataVO;

/**
 * @author: liubin
 *
 */
@Service("BusinessDataService")
public class BusinessDataService {

	private Log log = Log4jUtil.getLog("businessdata");

	private Log exLog = Log4jUtil.getLog("ex");

	@Autowired
	private CommonDAO commonDao;

	@Autowired
	private StatusService statusService;

	private String getHql(BusinessDataVO query) {
		StringBuffer sb = new StringBuffer();
		sb.append("from BusinessData");
		if (query == null || query.getBusinessData() == null)
			return sb.append(" order by id desc ").toString();
		int flag = 0;
		if ((!StringHelper.isNull(query.getQueryStime()) && !StringHelper
				.isNull(query.getQueryEtime()))
				|| query.getBusinessData() != null)
			sb.append(" where ");
		if (!StringHelper.isNull(query.getQueryStime())
				&& !StringHelper.isNull(query.getQueryEtime())){
			if(flag>0)
				sb.append(" and ");
			sb.append(" updateTime ");
			flag++;
		}
		return sb.toString();
	}
}
