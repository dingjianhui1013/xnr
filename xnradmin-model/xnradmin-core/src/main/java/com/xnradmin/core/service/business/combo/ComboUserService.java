package com.xnradmin.core.service.business.combo;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xnradmin.core.dao.business.combo.ComboUserDao;
import com.xnradmin.po.business.ComboUser;

@Service("ComboUserService")
public class ComboUserService {

	@Autowired
	private ComboUserDao dao;
	
	public Long save(ComboUser comboUser)
	{
		Serializable cls = dao.save(comboUser);
		return Long.valueOf(((cls.toString())));
	}
}
