/**
 *2012-5-11 上午9:00:02
 */
package com.xnradmin.core.service.business.commodity;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntinker.util.FileHelper;
import com.cntinker.util.LogHelper;
import com.cntinker.util.PoiExcelHelper;
import com.cntinker.util.StringHelper;
import com.xnradmin.constant.EnvConstant;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.dao.business.commodity.BusinessGoodsDAO;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.core.util.Log4jUtil;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.business.BusinessCategory;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.BusinessWeight;
import com.xnradmin.vo.business.BusinessGoodsVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Service("BusinessGoodsService")
public class BusinessGoodsService {

	@Autowired
	private BusinessGoodsDAO dao;

	@Autowired
	private CommonDAO commonDao;

	@Autowired
	private StatusService statusService;
	
	private Log log = Log4jUtil.getLog("uploadGoods");
	
	private Log exlog = Log4jUtil.getLog("ex");

	//static Log log = LogFactory.getLog(BusinessGoodsService.class);

	/**
	 * 
	 * @param po
	 * @return int
	 */
	public int save(BusinessGoods po) {
		String str ="";
		Pattern p = Pattern.compile("[<=-_>!@#$%^*/abcdefghijklmnopqrstuvwxyz]+"); 
		String[] result = p.split(po.getGoodsDescription());
		for (int i=0; i<result.length; i++) {
			str += result[i];
			po.setGoodsDescription(str);
		}
		Serializable cls = dao.save(po);
		return Integer.parseInt((cls.toString()));
	}

	public BusinessGoods findByid(String id) {
		return dao.findById(Integer.valueOf(id));
	}

	public int findByGoodsName(String goodsName, String id) {
		return dao.findByOlay(goodsName, id).size();
	}

	/**
	 * @param po
	 * @return int
	 */
	public int modify(BusinessGoods po) {
		BusinessGoods old = new BusinessGoods();
		old = findByid(po.getId().toString());
		if (StringHelper.isNull(po.getGoodsName())) {
			po.setGoodsName(old.getGoodsName());
		}
		if (po.getSortId() == null) {
			po.setSortId(old.getSortId());
		}
		if (po.getGoodsParentId() == null) {
			po.setGoodsParentId(old.getGoodsParentId());
		}
		if (StringHelper.isNull(po.getGoodsCategoryId())) {
			po.setGoodsCategoryId(old.getGoodsCategoryId());
		}

		if (po.getGoodsOriginalPrice() == null) {
			po.setGoodsOriginalPrice(old.getGoodsOriginalPrice());
		}
		if (po.getGoodsPreferentialPrice() == null) {
			po.setGoodsPreferentialPrice(old.getGoodsPreferentialPrice());
		}
		if (po.getGoodsBrandId() == null) {
			po.setGoodsBrandId(old.getGoodsBrandId());
		}
		if (po.getGoodsWeight() == null) {
			po.setGoodsWeight(old.getGoodsWeight());
		}
		if (po.getGoodsStock() == null) {
			po.setGoodsStock(old.getGoodsStock());
		}
		if (po.getGoodsStatus() == null) {
			po.setGoodsStatus(old.getGoodsStatus());
		}
		if (po.getIsFreeLogistics() == null) {
			po.setIsFreeLogistics(old.getIsFreeLogistics());
		}
		if (po.getIsNewGoods() == null) {
			po.setIsNewGoods(old.getIsNewGoods());
		}
		if (po.getIsDiscountGoods() == null) {
			po.setIsDiscountGoods(old.getIsDiscountGoods());
		}
		if (po.getIsHotSaleGoods() == null) {
			po.setIsHotSaleGoods(old.getIsHotSaleGoods());
		}
		if (StringHelper.isNull(po.getGoodsLogo())) {
			po.setGoodsLogo(old.getGoodsLogo());
		}
		if (po.getPrimaryConfigurationId() == null) {
			po.setPrimaryConfigurationId(old.getPrimaryConfigurationId());
		}
		if (po.getModifyStaffId() == null) {
			po.setModifyStaffId(old.getModifyStaffId());
		}
		if (po.getGoodsWeightId() == null) {
			po.setGoodsWeightId(old.getGoodsWeightId());
		}
		if (po.getGoodsSoldCount() == null) {
			po.setGoodsSoldCount(old.getGoodsSoldCount());
		}
		if (!StringHelper.isNull(po.getGoodsDescription())) {
			po.setGoodsDescription(po.getGoodsDescription());
		} else
			po.setGoodsDescription("");
		po.setModifyTime(new Timestamp(System.currentTimeMillis()));
		this.dao.merge(po);
		return 0;
	}

	public void del(String id) {
		BusinessGoods po = this.dao.findById(Integer.valueOf(id));
		this.dao.delete(po);
	}

	public int removeGoodsId(String id) {
		return dao.removeBusinessGoodsId(Integer.valueOf(id));
	}

	/**
	 * @param brandname
	 * @return int
	 */
	public int getCount(BusinessGoodsVO vo) {
		String hql = "select count(*) from BusinessGoods where 1=1";
		if (vo != null && vo.getBusinessGoods() != null) {
			if (!StringHelper.isNull(vo.getBusinessGoods().getGoodsName())) {
				hql = hql + " and goodsName like '%"
						+ vo.getBusinessGoods().getGoodsName() + "%'";
			}
			if (vo.getBusinessGoods().getGoodsParentId() != null) {
				hql = hql + " and goodsParentId = "
						+ vo.getBusinessGoods().getGoodsParentId();
			}
			if (vo.getBusinessGoods().getGoodsCategoryId() != null) {
				hql = hql + " and goodsCategoryId = "
						+ vo.getBusinessGoods().getGoodsCategoryId();
			}
			if (vo.getBusinessGoods().getGoodsBrandId() != null) {
				hql = hql + " and goodsBrandId = "
						+ vo.getBusinessGoods().getGoodsBrandId();
			}
			if (vo.getBusinessGoods().getGoodsStatus() != null) {
				hql = hql + " and goodsStatus = "
						+ vo.getBusinessGoods().getGoodsStatus();
			}
			if (vo.getBusinessGoods().getPrimaryConfigurationId() != null) {
				hql = hql + " and primaryConfigurationId = "
						+ vo.getBusinessGoods().getPrimaryConfigurationId();
			}
			if (vo.getBusinessGoods().getIsFreeLogistics() != null) {
				hql = hql + " and isFreeLogistics = "
						+ vo.getBusinessGoods().getIsFreeLogistics();
			}
			if (vo.getBusinessGoods().getIsNewGoods() != null) {
				hql = hql + " and isNewGoods = "
						+ vo.getBusinessGoods().getIsNewGoods();
			}
			if (vo.getBusinessGoods().getIsDiscountGoods() != null) {
				hql = hql + " and isDiscountGoods = "
						+ vo.getBusinessGoods().getIsDiscountGoods();
			}
			if (vo.getBusinessGoods().getIsHotSaleGoods() != null) {
				hql = hql + " and isHotSaleGoods = "
						+ vo.getBusinessGoods().getIsHotSaleGoods();
			}
			if (!StringHelper.isNull(vo.getCreateStartTime())) {
				hql = hql + " and createTime >='" + vo.getCreateStartTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getCreateEndTime())) {
				hql = hql + " and createTime <='" + vo.getCreateEndTime() + "'";
			}
			if (vo.getBusinessGoods().getCreateStaffId() != null) {
				hql = hql + " and createStaffId ="
						+ vo.getBusinessGoods().getCreateStaffId();
			}
			if (!StringHelper.isNull(vo.getModifyStartTime())) {
				hql = hql + " and modifyTime <='" + vo.getModifyStartTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getModifyEndTime())) {
				hql = hql + " and modifyTime >='" + vo.getModifyEndTime() + "'";
			}
			if (vo.getBusinessGoods().getModifyStaffId() != null) {
				hql = hql + " and modifyStaffId ="
						+ vo.getBusinessGoods().getModifyStaffId();
			}
			if (vo.getBusinessGoods().getGoodsWeightId() != null) {
				hql = hql + " and goodsWeightId ="
						+ vo.getBusinessGoods().getGoodsWeightId();
			}
		}
		return this.commonDao.getNumberOfEntitiesWithHql(hql);
	}

	/**
	 * 
	 * @param firstletter
	 * @param curPage
	 * @param pageSize
	 * @param orderField
	 * @param direction
	 * @return List<GetOrgListVO>
	 */
	public List<BusinessGoodsVO> listVO(BusinessGoodsVO vo, int curPage,
			int pageSize, String orderField, String direction) {
		String hql = "from BusinessGoods where 1=1";
		if (vo != null && vo.getBusinessGoods() != null) {
			if (!StringHelper.isNull(vo.getBusinessGoods().getGoodsName())) {
				hql = hql + " and goodsName like '%"
						+ vo.getBusinessGoods().getGoodsName() + "%'";
			}
			if (vo.getBusinessGoods().getGoodsParentId() != null) {
				hql = hql + " and goodsParentId = "
						+ vo.getBusinessGoods().getGoodsParentId();
			}
			if (vo.getBusinessGoods().getGoodsCategoryId() != null) {
				hql = hql + " and goodsCategoryId = "
						+ vo.getBusinessGoods().getGoodsCategoryId();
			}
			if (vo.getBusinessGoods().getGoodsBrandId() != null) {
				hql = hql + " and goodsBrandId = "
						+ vo.getBusinessGoods().getGoodsBrandId();
			}
			if (vo.getBusinessGoods().getGoodsStatus() != null) {
				hql = hql + " and goodsStatus = "
						+ vo.getBusinessGoods().getGoodsStatus();
			}
			if (vo.getBusinessGoods().getPrimaryConfigurationId() != null) {
				hql = hql + " and primaryConfigurationId = "
						+ vo.getBusinessGoods().getPrimaryConfigurationId();
			}
			if (vo.getBusinessGoods().getIsFreeLogistics() != null) {
				hql = hql + " and isFreeLogistics = "
						+ vo.getBusinessGoods().getIsFreeLogistics();
			}
			if (vo.getBusinessGoods().getIsNewGoods() != null) {
				hql = hql + " and isNewGoods = "
						+ vo.getBusinessGoods().getIsNewGoods();
			}
			if (vo.getBusinessGoods().getIsDiscountGoods() != null) {
				hql = hql + " and isDiscountGoods = "
						+ vo.getBusinessGoods().getIsDiscountGoods();
			}
			if (vo.getBusinessGoods().getIsHotSaleGoods() != null) {
				hql = hql + " and isHotSaleGoods = "
						+ vo.getBusinessGoods().getIsHotSaleGoods();
			}
			if (!StringHelper.isNull(vo.getCreateStartTime())) {
				hql = hql + " and createTime >='" + vo.getCreateStartTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getCreateEndTime())) {
				hql = hql + " and createTime <='" + vo.getCreateEndTime() + "'";
			}
			if (vo.getBusinessGoods().getCreateStaffId() != null) {
				hql = hql + " and createStaffId ="
						+ vo.getBusinessGoods().getCreateStaffId();
			}
			if (!StringHelper.isNull(vo.getModifyStartTime())) {
				hql = hql + " and modifyTime <='" + vo.getModifyStartTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getModifyEndTime())) {
				hql = hql + " and modifyTime >='" + vo.getModifyEndTime() + "'";
			}
			if (vo.getBusinessGoods().getModifyStaffId() != null) {
				hql = hql + " and modifyStaffId ="
						+ vo.getBusinessGoods().getModifyStaffId();
			}
			if (vo.getBusinessGoods().getGoodsWeightId() != null) {
				hql = hql + " and goodsWeightId ="
						+ vo.getBusinessGoods().getGoodsWeightId();
			}
		}
		if (!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		} else {
			hql += " order by id desc";
		}
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage,
				pageSize);
		List<BusinessGoodsVO> resList = new LinkedList<BusinessGoodsVO>();
		for (int i = 0; i < l.size(); i++) {
			BusinessGoods po = (BusinessGoods) l.get(i);
			BusinessGoodsVO businessGoodsVO = new BusinessGoodsVO();
			businessGoodsVO.setBusinessGoods(po);
			resList.add(businessGoodsVO);
		}
		return resList;
	}

	/**
	 * 生菜配送
	 * 
	 * @param firstletter
	 * @param curPage
	 * @param pageSize
	 * @param orderField
	 * @param direction
	 * @return List<GetOrgListVO>
	 */
	public List<BusinessGoodsVO> webBusinessList(BusinessGoodsVO vo,
			int curPage, int pageSize, String orderField, String direction,
			boolean getAll) {
		String hql = "from BusinessCategory a, BusinessGoods b, BusinessWeight c where a.id=b.goodsCategoryId"
				+ " and b.goodsWeightId = c.id and a.id<>153";

		if (!getAll)
			hql += " and b.goodsStatus=192 ";

		if (vo != null && vo.getBusinessCategory() != null) {
			if (!StringHelper
					.isNull(vo.getBusinessCategory().getCategoryName())) {
				hql = hql + " and a.categoryName like '%"
						+ vo.getBusinessCategory().getCategoryName() + "%'";
			}
			if (vo.getBusinessCategory().getCategoryParentId() != null) {
				hql = hql + " and a.categoryParentId = "
						+ vo.getBusinessCategory().getCategoryParentId();
			}
			if (vo.getBusinessCategory().getCategoryLevel() != null) {
				hql = hql + " and a.categoryLevel = "
						+ vo.getBusinessCategory().getCategoryLevel();
			}
			if (vo.getBusinessCategory().getCategoryStatus() != null) {
				hql = hql + " and a.categoryStatus = "
						+ vo.getBusinessCategory().getCategoryStatus();
			}
			if (vo.getBusinessCategory().getPrimaryConfigurationId() != null) {
				hql = hql + " and a.primaryConfigurationId = "
						+ vo.getBusinessCategory().getPrimaryConfigurationId();
			}
		}
		if (!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		} else {
			hql += " order by a.id desc";
		}
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage,
				pageSize);
		List<BusinessGoodsVO> resList = new LinkedList<BusinessGoodsVO>();
		for (int i = 0; i < l.size(); i++) {
			Object[] obj = (Object[]) l.get(i);
			BusinessCategory category = (BusinessCategory) obj[0];
			BusinessGoods goods = (BusinessGoods) obj[1];
			BusinessWeight weight = (BusinessWeight) obj[2];
			BusinessGoodsVO businessGoodsVo = new BusinessGoodsVO();
			businessGoodsVo.setBusinessCategory(category);
			businessGoodsVo.setBusinessGoods(goods);
			businessGoodsVo.setBusinessWeight(weight);
			resList.add(businessGoodsVo);
		}
		return resList;
	}
	public BusinessGoodsVO getBusinessGoodsAndWeight(String goodsId)
	{
		String hql  = "from BusinessGoods a,BusinessWeight b where a.goodsWeightId = b.id and a.id = "+goodsId;
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, 0,0);
		BusinessGoodsVO businessGoodsVO = new BusinessGoodsVO();
		if(!l.isEmpty())
		{
			Object[] o = (Object[])l.get(0);
			BusinessGoods businessGoods = (BusinessGoods)o[0];
			BusinessWeight businessWeight = (BusinessWeight)o[1];
			businessGoodsVO.setBusinessGoods(businessGoods);
			businessGoodsVO.setBusinessWeight(businessWeight);
		}
//		Object[] o = (Object[])l.get(0);
//		BusinessGoods businessGoods = (BusinessGoods)o[0];
//		BusinessWeight businessWeight = (BusinessWeight)o[1];
//		businessGoodsVO.setBusinessGoods(businessGoods);
//		businessGoodsVO.setBusinessWeight(businessWeight);
		return businessGoodsVO;
		
		
	}

	/**
	 * @return List<PrimaryConfiguration>
	 */
	public List<BusinessGoods> listAll() {
		return dao.findAll();
	}

	/**
	 * @param f
	 * @return int
	 * @throws IOException
	 */
	public int modifyAllGoods(File f, CommonStaff staff) throws IOException {
		PoiExcelHelper ph = new PoiExcelHelper();

		// log.debug("modifyAllGoods::::: "
		// + System.getProperty(EnvConstant.XICHEADMIN_HOME));
		String dir = System.getProperty(EnvConstant.XICHEADMIN_HOME)
				+ "/data/temp/";
		FileHelper.mkdir(dir);
		File file = new File(dir + "temp.xls");

		FileHelper.copyFile(f, file);
		List<ArrayList<String>> dataLst = new PoiExcelHelper().read(
				file.getCanonicalPath(), 0);

		for (int i = 1; i < dataLst.size(); i++) {
			List<String> l = dataLst.get(i);
			log.debug("update list: "+l);
			Map<Boolean, BusinessGoods> res = parseGoods(l);

			if (res == null)
				continue;

			boolean isUpdate = false;
			BusinessGoods p = null;
			Iterator<Boolean> it = res.keySet().iterator();
			while (it.hasNext()) {
				isUpdate = it.next();
			}
			p = res.get(isUpdate);
			if (isUpdate) {
				p.setModifyStaffId(staff.getId());
				modifyGoods(p);
			}
		}
		return 0;
	}

	private Map<Boolean, BusinessGoods> parseGoods(List<String> l) {

		String goodsName = l.get(1);
		boolean isUpdate = false;
		BusinessGoods po = findByName(goodsName);
		if (po == null)
			return null;

		// 更新售价
		String updatePrice = l.get(5);
		if (!StringHelper.isNull(updatePrice)) {
			Float uPrice = new Float(updatePrice);
			if (uPrice > 0.0f) {
				uPrice = StringHelper
						.formartDecimalToDouble(new Double(uPrice))
						.floatValue();
				po.setGoodsOriginalPrice(uPrice);
				isUpdate = true;
			}
		}

		// 更新进货价
		String updatePurchasePrice = l.get(9);
		if (!StringHelper.isNull(updatePurchasePrice)) {
			Float uPrice = new Float(updatePurchasePrice);
			if (uPrice.floatValue() > 0.0f) {
				uPrice = StringHelper
						.formartDecimalToDouble(new Double(uPrice))
						.floatValue();
				po.setGoodsPurchasePrice(uPrice);
				isUpdate = true;
			}
		}

		// 更新排序
		String updateSort = l.get(0);
		if (!StringHelper.isNull(updateSort)) {
			Double d = new Double(updateSort);

			Integer sort = null;

			if (d.doubleValue() <= 0)
				sort = 0;
			else
				sort = d.intValue();

			if (sort.intValue() > 0) {
				po.setSortId(sort);
				isUpdate = true;
			}
		}
		
		//更新类别
		

		Map<Boolean, BusinessGoods> res = new HashMap<Boolean, BusinessGoods>();
		res.put(isUpdate, po);
		return res;
	}

	public BusinessGoods findByName(String goodsName) {
		String hql = "from BusinessGoods where goodsName='" + goodsName + "'";
		List<BusinessGoods> lst = commonDao.getEntitiesByPropertiesWithHql(hql,
				0, 0);
		if (lst == null || lst.size() <= 0)
			return null;
		return lst.get(0);
	}

	public int modifyGoods(BusinessGoods po) {
		po.setModifyTime(new Timestamp(System.currentTimeMillis()));
		
		log.debug("update : "+po);
		commonDao.modify(po);
		return 0;
	}

	public List<BusinessGoods> getTypeNameById(String types) {
		String hql = "from BusinessGoods where id in("+types+")";
		List<BusinessGoods> lst = commonDao.getEntitiesByPropertiesWithHql(hql,
				0, 0);
		return lst;
	}
	public List<BusinessGoods> getListBycategoryId(String categoryId) {
		String hql = "from BusinessGoods where goodsCategoryId ='"+categoryId+"'";
		List<BusinessGoods> lst = commonDao.getEntitiesByPropertiesWithHql(hql,
				0, 0);
		return lst;
	}
}
