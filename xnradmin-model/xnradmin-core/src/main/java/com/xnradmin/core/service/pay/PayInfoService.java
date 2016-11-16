/**
 *2014年8月21日 下午4:54:48
*/
package com.xnradmin.core.service.pay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.vo.pay.PayInfoVO;

/**
 * @author: liubin
 *
 */
@Service("PayInfoService")
public class PayInfoService {
	@Autowired
    private CommonDAO commonDao;
	
	public int save(PayInfoVO vo,CommonStaff staff){
		return 0;
	}
	
	public int modify(PayInfoVO vo,CommonStaff staff){
		return 0;
	}
	
	 
}
