/**
 *2015年1月31日 下午2:06:44
 */
package com.xnradmin.core.service.common.quesion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xnradmin.core.dao.CommonDAO;

/**
 * @author: liubin
 *
 */
@Service("QuesionLogService")
public class QuesionLogService {

	@Autowired
	private CommonDAO commonDao;
}
