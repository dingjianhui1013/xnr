/**
 *2014年12月14日 下午1:22:44
 */
package com.xnradmin.core.service.business.commodity;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.dao.business.commodity.BusinessUserFavoriteDAO;
import com.xnradmin.po.business.BusinessCategory;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.BusinessUserFavorite;
import com.xnradmin.po.business.BusinessWeight;
import com.xnradmin.vo.business.BusinessGoodsVO;

/**
 * @author: liubin
 *
 */
@Service("BusinessUserFavoriteService")
public class BusinessUserFavoriteService {

	@Autowired
	private BusinessUserFavoriteDAO dao;
	
	@Autowired
	private CommonDAO commonDao;

	static Log log = LogFactory.getLog(BusinessUserFavoriteService.class);

	/**
	 * @param po
	 * @return int 返回0为成功，-1:传入缺少参数
	 */
	public int save(BusinessUserFavorite po) {
		if (po == null || po.getGoodsId() == null || po.getStaffId() == null)
			return -1;
		BusinessUserFavorite p = findByUserIDAndGoodsId(po.getStaffId(),
				po.getGoodsId());
		if (p == null)
			dao.save(po);

		return 0;
	}

	public int deleteStaffIdAndGoodsId(BusinessUserFavorite po) {
		if(po!=null){
			dao.deleteStaffIdAndGoodsId(po.getStaffId(), po.getGoodsId());
		}
		else{
			return -1;
		}
		return 0;
	}
	
	public int removeBusinessUserFavoriteGoodsIdStaffId(Integer id, Integer StaffId) {
		dao.removeBusinessUserFavoriteGoodsIdStaffId(id,StaffId);
		return 0;
	}

	private BusinessUserFavorite findByUserIDAndGoodsId(Integer staffid,
			Integer goodsid) {
		String hql = "from BusinessUserFavorite where staffId=" + staffid
				+ " and goodsId=" + goodsid;

		List<BusinessUserFavorite> l = commonDao
				.getEntitiesByPropertiesWithHql(hql, 0, 0);
		if (l == null || l.size() <= 0)
			return null;
		return l.get(0);
	}
	
	public List<BusinessUserFavorite> findByUserId(String propertyName, Integer staffid) {
		List<BusinessUserFavorite> l = dao.findByProperty(propertyName, staffid);
		return l;
	}
	
	
	/**
	 * 商户收藏
	 * 
	 * @param firstletter
	 * @param curPage
	 * @param pageSize
	 * @param orderField
	 * @param direction
	 * @return List<GetOrgListVO>
	 */
	public List<BusinessGoodsVO> webUserFavoriteList(String staffId, String clientUserInfoId, boolean getAll) {
		String hql = "from BusinessCategory a, BusinessGoods b, BusinessWeight c ,BusinessUserFavorite d"
				+ " where a.id=b.goodsCategoryId and b.id = d.goodsId"
				+ " and b.goodsWeightId = c.id";

		if(!getAll){
			hql = hql + " and b.goodsStatus=192";
		}
		
		hql = hql + " and d.staffId="+staffId;
		
		if(!StringHelper.isNull(clientUserInfoId)){
			hql = hql + " and d.clientUserInfoId="+clientUserInfoId;
		}

		hql = hql + " order by a.sortId, b.sortId";
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, 0,
				0);
		List<BusinessGoodsVO> resList = new LinkedList<BusinessGoodsVO>();
		for (int i = 0; i < l.size(); i++) {
			Object[] obj = (Object[]) l.get(i);
			BusinessCategory category = (BusinessCategory) obj[0];
			BusinessGoods goods = (BusinessGoods) obj[1];
			BusinessWeight weight = (BusinessWeight) obj[2];
			BusinessUserFavorite userFavorite = (BusinessUserFavorite) obj[3];
			BusinessGoodsVO businessGoodsVo = new BusinessGoodsVO();
			businessGoodsVo.setBusinessCategory(category);
			businessGoodsVo.setBusinessGoods(goods);
			businessGoodsVo.setBusinessWeight(weight);
			businessGoodsVo.setBusinessUserFavorite(userFavorite);
			resList.add(businessGoodsVo);
		}
		return resList;
	}
}
