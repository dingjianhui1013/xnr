package com.xnradmin.core.service.business.combo;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xnradmin.core.dao.business.combo.ComboUserDao;
import com.xnradmin.po.business.ComboUser;
import com.xnradmin.vo.business.ComboVO;

@Service("ComboUserService")
public class ComboUserService {

	@Autowired
	private ComboUserDao dao;
	
	public Long save(ComboUser comboUser)
	{
		Serializable cls = dao.save(comboUser);
		return Long.valueOf(((cls.toString())));
	}
	public List<ComboVO> findByComboVOs(Long orderId,Integer userId)
	{
		List<ComboVO> comVos = dao.findByComboVOs(orderId,userId);
		return comVos;
	}
	//周期订单
	public List<ComboVO> findByComboVOs(Long userId,int curPage,int pageSize)
	{
		List<ComboVO> comVos = dao.findByComboVOs(userId,curPage,pageSize);
		return comVos;
	}
	public int getCount(Long userId) {
		int comboCount = dao.getCount(userId);
		return comboCount;
	}
	public ComboUser findById(String comboUserId) {
		// TODO Auto-generated method stub
		return dao.findById(comboUserId);
	}
}
