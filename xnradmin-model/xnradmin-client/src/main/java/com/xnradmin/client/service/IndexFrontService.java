package com.xnradmin.client.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.po.business.BusinessCategory;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.BusinessWeight;
import com.xnradmin.po.front.ReceiptAddress;
import com.xnradmin.vo.business.BusinessGoodsVO;

@Service("IndexFrontService")
public class IndexFrontService {
	private Logger log = Logger.getLogger(IndexFrontService.class);
	@Autowired
	private CommonDAO commonDao;
	
	/**
	 * 分类
	 */
	public List<Map<BusinessCategory, List<Map<BusinessCategory, List<BusinessCategory>>>>> getAllBusinessCategory(){
		List<Map<BusinessCategory, List<Map<BusinessCategory, List<BusinessCategory>>>>> firstList = new ArrayList<Map<BusinessCategory, List<Map<BusinessCategory, List<BusinessCategory>>>>>();
		List<BusinessCategory> firstCategorys = getFirstCategory();//一级分类列表
		for(BusinessCategory firstBusinessCategory:firstCategorys){
			Map<BusinessCategory, List<Map<BusinessCategory, List<BusinessCategory>>>> firstCategory = new HashMap<BusinessCategory, List<Map<BusinessCategory, List<BusinessCategory>>>>();
			
			List<Map<BusinessCategory, List<BusinessCategory>>> secondList = new ArrayList<Map<BusinessCategory, List<BusinessCategory>>>();
			List<BusinessCategory> secondCategorys = getParentCategory(firstBusinessCategory.getId());//二级分类列表
			for(BusinessCategory secondBusinessCategory:secondCategorys){
				Map<BusinessCategory, List<BusinessCategory>> secondCategory = new HashMap<BusinessCategory, List<BusinessCategory>>();
				List<BusinessCategory> threeList = getParentCategory(secondBusinessCategory.getId());//三级分类列表
				secondCategory.put(secondBusinessCategory, threeList);
				secondList.add(secondCategory);
			}
			
			firstCategory.put(firstBusinessCategory, secondList);
			firstList.add(firstCategory);
		}
		return firstList;
	}
	public List<Map<String, List<Map<String, List<BusinessCategory>>>>> getAllBusinessCategoryS(){
		List<Map<String, List<Map<String, List<BusinessCategory>>>>> firstList = new ArrayList<Map<String, List<Map<String, List<BusinessCategory>>>>>();
		List<BusinessCategory> firstCategorys = getFirstCategory();//一级分类列表
		for(BusinessCategory firstBusinessCategory:firstCategorys){
			Map<String, List<Map<String, List<BusinessCategory>>>> firstCategory = new HashMap<String, List<Map<String, List<BusinessCategory>>>>();
			
			List<Map<String, List<BusinessCategory>>> secondList = new ArrayList<Map<String, List<BusinessCategory>>>();
			List<BusinessCategory> secondCategorys = getParentCategory(firstBusinessCategory.getId());//二级分类列表
			for(BusinessCategory secondBusinessCategory:secondCategorys){
				Map<String, List<BusinessCategory>> secondCategory = new HashMap<String, List<BusinessCategory>>();
				List<BusinessCategory> threeList = getParentCategory(secondBusinessCategory.getId());//三级分类列表
				secondCategory.put(secondBusinessCategory.getCategoryName(), threeList);
				secondList.add(secondCategory);
			}
			
			firstCategory.put(firstBusinessCategory.getCategoryName(), secondList);
			firstList.add(firstCategory);
		}
		return firstList;
	}
	/**
	 * 一级分类
	 */
	private List<BusinessCategory> getFirstCategory(){
		String hql = "from BusinessCategory where categoryLevel=1";
		List<BusinessCategory> list  = commonDao.getEntitiesByPropertiesWithHql(hql,0,0);
		return list;
	}
	/**
	 * 三级分类
	 */
	private List<BusinessCategory> getThreeCategory(){
		String hql = "from BusinessCategory where categoryLevel=3";
		List<BusinessCategory> list  = commonDao.getEntitiesByPropertiesWithHql(hql,0,0);
		return list;
	}
	/**
	 * 查找子分类
	 */
	private List<BusinessCategory> getParentCategory(Integer parentId){
		String hql = "from BusinessCategory where categoryParentId="+parentId;
		List<BusinessCategory> list  = commonDao.getEntitiesByPropertiesWithHql(hql,0,0);
		return list;
	}
	/**
	 * 
	 * @param type
	 * @param curPage
	 * @param pageSize
	 * @return
	 */
	public List<BusinessGoodsVO> listBusinessGoodsVO( ) {
		String hql = "from BusinessGoods a,BusinessCategory b,BusinessWeight c where a.goodsCategoryId=b.id and a.goodsWeightId=c.id";
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, 0,0);
		List<BusinessGoodsVO> resList = new LinkedList<BusinessGoodsVO>();
		for (int i = 0; i < l.size(); i++) {
			Object[] obj = (Object[]) l.get(i);
			BusinessGoodsVO businessGoodsVO = new BusinessGoodsVO();
			businessGoodsVO.setBusinessGoods((BusinessGoods)obj[0]);
			businessGoodsVO.setBusinessCategory((BusinessCategory)obj[1]);
			businessGoodsVO.setBusinessWeight((BusinessWeight)obj[2]);
			resList.add(businessGoodsVO);
		}
		return resList;
	}
	public List<BusinessGoodsVO> listBusinessGoodsByCategoryId( String categoryId) {
		String hql = "from BusinessGoods a,BusinessCategory b,BusinessWeight c where a.goodsCategoryId=b.id and a.goodsWeightId=c.id and a.goodsCategoryId='"+categoryId+"'";
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, 0,0);
		List<BusinessGoodsVO> resList = new LinkedList<BusinessGoodsVO>();
		for (int i = 0; i < l.size(); i++) {
			Object[] obj = (Object[]) l.get(i);
			BusinessGoodsVO businessGoodsVO = new BusinessGoodsVO();
			businessGoodsVO.setBusinessGoods((BusinessGoods)obj[0]);
			businessGoodsVO.setBusinessCategory((BusinessCategory)obj[1]);
			businessGoodsVO.setBusinessWeight((BusinessWeight)obj[2]);
			resList.add(businessGoodsVO);
		}
		return resList;
	}
	public BusinessGoods findGoodsByName(String goodsName){
		String hql = "from BusinessGoods where goodsName like '%"+goodsName+"%'";
		List<BusinessGoods> l= commonDao.getEntitiesByPropertiesWithHql(hql,0,0);
		if(l.isEmpty()){
			return null;
		}else{
			return l.get(0);
		}
	}
	/***
	 * 增加收货地址
	 */
	public void saveAddress(ReceiptAddress receiptAddress)
	{
		try {
			commonDao.save(receiptAddress);
			log.debug("地址保存成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("地址保存失败");
			e.printStackTrace();
		}
	}
	/**
	 * 遍历收货地址
	 */
	public List<ReceiptAddress> getListAddress()
	{
		String hql = "from ReceiptAddress";
		List<ReceiptAddress> list = commonDao.getEntitiesByPropertiesWithHql(hql, 0,0);
		return list;
	}
	public void setDefault(String setDefaultId) {
		String hql = "update ReceiptAddress set type=1 where id ="+setDefaultId;
		commonDao.executeUpdateOrDelete(hql);
	}
	public void changeDefault() {
		String getIdhql ="from ReceiptAddress where type=1";
		List<ReceiptAddress> idList = (List)commonDao.getEntitiesByPropertiesWithHql(getIdhql, 0,0);
		if(!idList.isEmpty())
		{
			String hql = " update ReceiptAddress set type=0 where id = "+idList.get(0).getId();
			commonDao.executeUpdateOrDelete(hql);
		}
	}
	public void deleteAddress(ReceiptAddress receiptAddress) {
		// TODO Auto-generated method stub
		commonDao.delete(receiptAddress);
	}
}
