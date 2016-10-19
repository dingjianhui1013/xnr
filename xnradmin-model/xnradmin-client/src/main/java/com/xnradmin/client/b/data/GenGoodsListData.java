/**
 *2014年12月12日 下午4:11:14
*/
package com.xnradmin.client.b.data;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import com.cntinker.json.JSONArray;
import com.cntinker.json.JSONObject;
import com.xnradmin.po.business.BusinessCategory;
import com.xnradmin.vo.business.BusinessGoodsVO;

/**
 * @author: liubin
 *
 */
public class GenGoodsListData {
	
//	/**
//	 * 生成列表数据
//	 */
//	private static void genData(){
//		//取得分类，商品所有数据
//				this.voList = goodsService.webBusinessList(null, 0, 0, "a.sortId,b.sortId", "asc");
//				this.categoryList = categoryService.webBusinessList(null, 0, 0, "sortId", "asc");
//				
//				LinkedHashMap<String,LinkedList<BusinessGoodsVO>> jsonMap = new LinkedHashMap();
//				//将所有分类的商品汇总到MAP中
//				for(int i = 0 ; voList.size() > i ; i++){
//					BusinessGoodsVO vo = voList.get(i);
//					//处理主分类数据
//					for(int j = 0 ; categoryList.size() > j ; j++){
//						BusinessCategory bc = categoryList.get(j);
//						if(vo.getBusinessCategory().getCategoryParentId() == bc.getId()){
//							vo.setBusinessParentCategory(bc);
//						}
//					}
//					//归纳数据，KEY为分类ID
//					if(jsonMap.get(vo.getBusinessCategory().getId().toString())!=null){
//						LinkedList<BusinessGoodsVO> a = jsonMap.get(vo.getBusinessCategory().getId().toString());
//						a.add(vo);
//						jsonMap.put(vo.getBusinessCategory().getId().toString(), a);
//					}else{
//						LinkedList<BusinessGoodsVO> b = new LinkedList<BusinessGoodsVO>();
//						b.add(vo);
//						jsonMap.put(vo.getBusinessCategory().getId().toString(), b);
//					}
//				}
//				// 处理分拣好的MAP
//				JSONObject parentCategory = new JSONObject();
//				JSONObject category = new JSONObject();
//				String parentCategoryName = "";
//				for (Iterator it = jsonMap.keySet().iterator(); it.hasNext();) {
//					String categoryId = it.next().toString();
//					LinkedList<BusinessGoodsVO> c = jsonMap.get(categoryId);
//					if (c.size() > 0
//							&& !parentCategoryName.equals("")
//							&& !parentCategoryName.equals(c.get(0)
//									.getBusinessParentCategory().getCategoryName())) {
//						//遍历已存在的分类产品并合并
//						if(!parentCategory.isNull(parentCategoryName.toString())){
//							JSONObject temp = new JSONObject();
//							temp = (JSONObject) parentCategory.get(parentCategoryName);
//							for (Iterator tp = category.keys(); tp.hasNext();) {
//								String key = tp.next().toString();
//								temp.put(key, category.get(key));
//							}
//							category = temp;
//						}
//						parentCategory.put(parentCategoryName, category);
//						category = new JSONObject();
//					}
//					
//					JSONArray goodsArray = new JSONArray();
//					for (int i = 0; c.size() > i; i++) {
//						BusinessGoodsVO vo = c.get(i);
//						JSONObject goods = new JSONObject();
//						goods.put("id", vo.getBusinessGoods().getId());
//						goods.put("format", vo.getBusinessGoods().getGoodsWeight()
//								+ vo.getBusinessWeight().getWeightName());
//						goods.put("level", vo.getBusinessGoods().getSortId());
//						goods.put("price_unit", vo.getBusinessWeight().getWeightName());
//						goods.put("weight", "1");
//						goods.put("class1", vo.getBusinessParentCategory()
//								.getCategoryName());
//						goods.put("class2", vo.getBusinessCategory().getCategoryName());
//						goods.put("unit", vo.getBusinessWeight().getWeightName());
//						goods.put("name", vo.getBusinessGoods().getGoodsName());
//						goods.put("subject", vo.getBusinessGoods()
//								.getGoodsDescription());
//						goods.put("sell_brand", "");
//						//计算平均价格
//						BigDecimal b1 = new BigDecimal(vo.getBusinessGoods()
//								.getGoodsOriginalPrice());
//						BigDecimal b2 = new BigDecimal(vo.getBusinessGoods().getGoodsWeight());
//						goods.put("price", b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP).doubleValue());
//						goods.put("commodity_price_id", "");
//						goods.put("own_brand", "");
//						goods.put("trick_base", "");
//						goods.put("sold_count", 8701);
//						goods.put("is_favorite", 0);
//						goods.put("standard_item_num", "10");
//						goods.put("commodity_total_price", vo.getBusinessGoods()
//								.getGoodsOriginalPrice());
//						goodsArray.put(goods);
//						parentCategoryName = vo.getBusinessParentCategory()
//								.getCategoryName();
//					}
//					if (c.size() > 0) {
//						category.put(c.get(0).getBusinessCategory().getCategoryName(),
//								goodsArray);
//					}
//					if(!it.hasNext()){
//						if (c.size() > 0 && !parentCategoryName.equals("")) {
//							//遍历已存在的分类产品并合并
//							if(!parentCategory.isNull(parentCategoryName.toString())){
//								JSONObject temp = new JSONObject();
//								temp = (JSONObject) parentCategory.get(parentCategoryName);
//								for (Iterator tp = category.keys(); tp.hasNext();) {
//									String key = tp.next().toString();
//									temp.put(key, category.get(key));
//								}
//								category = temp;
//							}
//							parentCategory.put(parentCategoryName, category);
//						}
//					}
//				}
//				//json排序
//				JSONObject endJson = new JSONObject();
//				for(int i = 0 ; categoryList.size() > i ; i++){
//					BusinessCategory bc = categoryList.get(i);
//					if(bc.getCategoryLevel()==0 && 
//							!parentCategory.isNull(bc.getCategoryName().toString())){
//						endJson.put(bc.getCategoryName().toString(), parentCategory.get(bc.getCategoryName().toString()));
//					}
//				}
//				listJson = endJson.toString();
//	}
	
	public static void main(String[] args) throws Exception {
		System.out.println("");
	}
}
