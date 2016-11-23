package com.xnradmin.client.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.po.business.BusinessCategory;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.BusinessWeight;
import com.xnradmin.vo.business.BusinessGoodsVO;

@Service("IndexFrontService")
public class IndexFrontService {
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
	public List<BusinessGoodsVO> listBusinessGoodsVO( int curPage,int pageSize) {
		String hql = "from BusinessGoods a,BusinessCategory b,BusinessWeight c where a.goodsCategoryId=b.id and a.goodsWeightId=c.id";
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage,pageSize);
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
}
