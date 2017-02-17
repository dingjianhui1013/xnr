/**
 *2015年1月31日 下午2:06:35
 */
package com.xnradmin.core.service.common.quesion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.po.common.quesion.Quesion;

/**
 * @author: liubin
 *
 */
@Service("QuesionService")
public class QuesionService {
	@Autowired
	private CommonDAO commonDao;

	public void save(Quesion po) {
		this.commonDao.save(po);
	}

	public void modify(Quesion po) {
		this.commonDao.modify(po);
	}

}
