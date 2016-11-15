/**
 *2012-5-10 下午3:52:29
 */
package com.xnradmin.client.action.mall.business;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cntinker.json.JSONArray;
import com.cntinker.json.JSONException;
import com.cntinker.json.JSONObject;
import com.cntinker.util.StringHelper;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.core.action.ParentAction;
import com.xnradmin.core.service.StaffService;
import com.xnradmin.core.service.business.commodity.BusinessCategoryService;
import com.xnradmin.core.service.business.commodity.BusinessGoodsService;
import com.xnradmin.core.service.business.commodity.BusinessUserFavoriteService;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.core.service.mall.clientUser.ClientUserInfoService;
import com.xnradmin.core.service.mall.clientUser.ClientUserRegionInfoService;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.business.BusinessCategory;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.BusinessOrderRecord;
import com.xnradmin.po.business.BusinessUserFavorite;
import com.xnradmin.po.client.ClientUserInfo;
import com.xnradmin.po.client.ClientUserRegionInfo;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.po.mall.seting.LogisticsCompany;
import com.xnradmin.vo.business.BusinessGoodsVO;
import com.xnradmin.vo.business.BusinessOrderVO;

;

/**
 * @autohr: xiang_liu
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/page/wx/client/web/business/userfavorite")
@ParentPackage("json-default")
public class BusinessUserFavoriteAction extends ParentAction {

	@Autowired
	private BusinessUserFavoriteService favoriteService;

	@Autowired
	private BusinessCategoryService categoryService;

	@Autowired
	private BusinessGoodsService goodsService;

	@Autowired
	private ClientUserInfoService clientUserInfoService;
	
	@Autowired
	private ClientUserRegionInfoService clientUserRegionInfoService;

	@Autowired
	private StatusService statusService;

	private String uid; // 用户登录ID
	private String listJson; // List页面返回的JSON值
	private String staffId; // 用户ID
	private String goodsId; // 商品ID
	private String clientUserInfoId; // 用户ID
	private String type;
	private int flag;
	private List<BusinessCategory> categoryList;
	private List<BusinessGoods> goodsList;
	private List<BusinessGoodsVO> voList;
	private List<ClientUserRegionInfo> clientUserRegionInfoList;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getListJson() {
		return listJson;
	}

	public void setListJson(String listJson) {
		this.listJson = listJson;
	}

	public List<BusinessCategory> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<BusinessCategory> categoryList) {
		this.categoryList = categoryList;
	}

	public List<BusinessGoods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<BusinessGoods> goodsList) {
		this.goodsList = goodsList;
	}

	public List<BusinessGoodsVO> getVoList() {
		return voList;
	}

	public void setVoList(List<BusinessGoodsVO> voList) {
		this.voList = voList;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public List<ClientUserRegionInfo> getClientUserRegionInfoList() {
		return clientUserRegionInfoList;
	}

	public void setClientUserRegionInfoList(
			List<ClientUserRegionInfo> clientUserRegionInfoList) {
		this.clientUserRegionInfoList = clientUserRegionInfoList;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getClientUserInfoId() {
		return clientUserInfoId;
	}

	public void setClientUserInfoId(String clientUserInfoId) {
		this.clientUserInfoId = clientUserInfoId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	@Override
	public boolean isPublic() {
		return true;
	};

	static Log log = LogFactory.getLog(BusinessUserFavoriteAction.class);

	/**
	 * HTML list.html 列表页显示
	 * 
	 * @return
	 */
	@Action(value = "webUserFavoriteList", results = { @Result(name = StrutsResMSG.SUCCESS, type = "json", params = {
			"enableGZIP", "true" }) })
	public String webUserFavoriteList() throws IOException, JSONException {

		// 取得用户收藏分类，商品所有数据
		
		this.voList = favoriteService.webUserFavoriteList(staffId, "", false);
		this.categoryList = categoryService.webBusinessList(null, 0, 0,
				"sortId", "asc");

		LinkedHashMap<String, LinkedList<BusinessGoodsVO>> jsonMap = new LinkedHashMap();
		// 将所有分类的商品汇总到MAP中
		for (int i = 0; voList.size() > i; i++) {
			BusinessGoodsVO vo = voList.get(i);
			// 处理主分类数据
			for (int j = 0; categoryList.size() > j; j++) {
				BusinessCategory bc = categoryList.get(j);
				if (vo.getBusinessCategory().getCategoryParentId() == bc
						.getId()) {
					vo.setBusinessParentCategory(bc);
				}
			}
			// 归纳数据，KEY为分类ID
			if (jsonMap.get(vo.getBusinessCategory().getId().toString()) != null) {
				LinkedList<BusinessGoodsVO> a = jsonMap.get(vo
						.getBusinessCategory().getId().toString());
				a.add(vo);
				jsonMap.put(vo.getBusinessCategory().getId().toString(), a);
			} else {
				LinkedList<BusinessGoodsVO> b = new LinkedList<BusinessGoodsVO>();
				b.add(vo);
				jsonMap.put(vo.getBusinessCategory().getId().toString(), b);
			}
		}
		// 处理分拣好的MAP
		JSONObject parentCategory = new JSONObject();
		JSONObject category = new JSONObject();
		String parentCategoryName = "";
		for (Iterator it = jsonMap.keySet().iterator(); it.hasNext();) {
			String categoryId = it.next().toString();
			LinkedList<BusinessGoodsVO> c = jsonMap.get(categoryId);

			if (c.size() > 0
					&& !StringHelper.isNull(parentCategoryName)
					&& !parentCategoryName.equals(c.get(0)
							.getBusinessParentCategory().getCategoryName())) {
				// 遍历已存在的分类产品并合并
				if (!parentCategory.isNull(parentCategoryName.toString())) {
					JSONObject temp = new JSONObject();
					temp = (JSONObject) parentCategory.get(parentCategoryName);
					for (Iterator tp = category.keys(); tp.hasNext();) {
						String key = tp.next().toString();
						temp.put(key, category.get(key));
					}
					category = temp;
				}
				parentCategory.put(parentCategoryName, category);
				category = new JSONObject();
			}

			JSONArray goodsArray = new JSONArray();
			for (int i = 0; c.size() > i; i++) {
				BusinessGoodsVO vo = c.get(i);
				JSONObject goods = new JSONObject();
				goods.put("id", vo.getBusinessGoods().getId());
				goods.put("format", vo.getBusinessGoods().getGoodsWeight()
						+ vo.getBusinessWeight().getWeightName());
				goods.put("level", vo.getBusinessGoods().getSortId());
				goods.put("price_unit", vo.getBusinessWeight().getWeightName());
				goods.put("weight", "1");
				goods.put("class1", vo.getBusinessParentCategory()
						.getCategoryName());
				goods.put("class2", vo.getBusinessCategory().getCategoryName());
				goods.put("unit", vo.getBusinessWeight().getWeightName());
				goods.put("name", vo.getBusinessGoods().getGoodsName());
				goods.put("subject", vo.getBusinessGoods()
						.getGoodsDescription());
				goods.put("sell_brand", "");
				// 计算平均价格
				BigDecimal b1 = new BigDecimal(vo.getBusinessGoods()
						.getGoodsOriginalPrice());
				BigDecimal b2 = new BigDecimal(vo.getBusinessGoods()
						.getGoodsWeight());
				goods.put("price", b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP)
						.doubleValue());
				goods.put("commodity_price_id", "");
				goods.put("own_brand", "");
				goods.put("trick_base", "");
				goods.put("sold_count", 0);
				goods.put("is_favorite", 1);
				goods.put("standard_item_num", "10");
				goods.put("commodity_total_price", vo.getBusinessGoods()
						.getGoodsOriginalPrice());
				goodsArray.put(goods);
				parentCategoryName = vo.getBusinessParentCategory()
						.getCategoryName();
			}
			if (c.size() > 0) {
				category.put(c.get(0).getBusinessCategory().getCategoryName(),
						goodsArray);
			}
			if (!it.hasNext()) {
				if (c.size() > 0 && !parentCategoryName.equals("")) {
					// 遍历已存在的分类产品并合并
					if (!parentCategory.isNull(parentCategoryName.toString())) {
						JSONObject temp = new JSONObject();
						temp = (JSONObject) parentCategory
								.get(parentCategoryName);
						for (Iterator tp = category.keys(); tp.hasNext();) {
							String key = tp.next().toString();
							temp.put(key, category.get(key));
						}
						category = temp;
					}
					parentCategory.put(parentCategoryName, category);
				}
			}
		}
		// json排序
		JSONObject endJson = new JSONObject();
		for (int i = 0; categoryList.size() > i; i++) {
			BusinessCategory bc = categoryList.get(i);
			if (bc.getCategoryLevel() == 0
					&& !parentCategory.isNull(bc.getCategoryName().toString())) {
				endJson.put(bc.getCategoryName().toString(),
						parentCategory.get(bc.getCategoryName().toString()));
			}
		}
		listJson = endJson.toString();
		return StrutsResMSG.SUCCESS;
	}

	/**
	 * 保存对象接口
	 * 
	 * @return String
	 * @throws JSONException
	 * @throws Exception
	 */
	@Action(value = "add", results = { @Result(name = StrutsResMSG.SUCCESS, type = "json") })
	public String add() throws JSONException {
		if(!StringHelper.isNull(goodsId) && !StringHelper.isNull(staffId)
				&& !StringHelper.isNull(type)){
			// 取得当前登录人信息
			ClientUserInfo clientUserInfo = clientUserInfoService.findByProperty("staffId", Integer.parseInt(staffId));
			// 增加收藏
			BusinessUserFavorite userFavorite = new BusinessUserFavorite();
			userFavorite.setClientUserInfoId(clientUserInfo.getId());
			userFavorite.setGoodsId(Integer.parseInt(goodsId));
			userFavorite.setStaffId(Integer.parseInt(staffId));
			userFavorite.setCreateTime(new Timestamp(System.currentTimeMillis()));
			if(type.equals("add")){
				flag = favoriteService.save(userFavorite);
			}
			else if(type.equals("cancel")){
				flag = favoriteService.deleteStaffIdAndGoodsId(userFavorite);
			}
		}
		return StrutsResMSG.SUCCESS;
	}
}
