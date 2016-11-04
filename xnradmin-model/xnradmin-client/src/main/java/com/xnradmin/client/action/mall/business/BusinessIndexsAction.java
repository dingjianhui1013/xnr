/**
 *2012-5-10 下午3:52:29
 */
package com.xnradmin.client.action.mall.business;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
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
import com.xnradmin.core.service.mall.clientUser.ClientUserRegionInfoService;
import com.xnradmin.core.util.Log4jUtil;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.business.BusinessCategory;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.BusinessUserFavorite;
import com.xnradmin.po.client.ClientUserRegionInfo;
import com.xnradmin.vo.business.BusinessGoodsVO;

;

/**
 * @autohr: xiang_liu
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/page/wx/client/web/business/businessIndex")
@ParentPackage("json-default")
public class BusinessIndexsAction extends ParentAction {

	@Autowired
	private BusinessCategoryService categoryService;

	@Autowired
	private BusinessGoodsService goodsService;

	@Autowired
	private ClientUserRegionInfoService clientUserRegionInfoService;

	@Autowired
	private BusinessUserFavoriteService favoriteService;

	@Autowired
	private StatusService statusService;

	@Autowired
	private StaffService staffService;

	private String uid; // 用户登录ID
	private String listJson; // List页面返回的JSON值
	private String staffId; // 用户ID
	private List<BusinessCategory> categoryList;
	private List<BusinessGoods> goodsList;
	private List<BusinessGoodsVO> voList;
	private List<ClientUserRegionInfo> clientUserRegionInfoList;
	private CommonStaff staff; // 登陆的商户
	private String coopid; // 合作方ID

	@Override
	public boolean isPublic() {
		return true;
	};

	static Log log = LogFactory.getLog(BusinessIndexsAction.class);

	private Log coopLog = Log4jUtil.getLog("coopLog");

	@Action(value = "coopGoodsList", results = { @Result(name = StrutsResMSG.SUCCESS, type = "json", params = {
			"enableGZIP", "true" }) })
	public String coopGoodsList() throws JSONException, IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		if (StringHelper.isNull(coopid)) {
			response.getWriter().println("Error: coopid is null");
			return null;
		}

		staff = staffService.findByid(coopid);
		
		coopLog.debug("remote ip:"+super.getRemoteIp()+" | coop staff ip:"+staff.getIp());
		
		if (staff == null || StringHelper.isNull(staff.getIp())
				|| !super.getRemoteIp().equals(staff.getIp())) {
			response.getWriter().println("Error: ip is null or ip is block");
			return null;
		}

		coopLog.debug("ip: " + request.getRemoteHost() + " | "
				+ request.getRemoteAddr() + " | " + request.getRemotePort());
		// 取得分类，商品所有数据
		this.voList = goodsService.webBusinessList(null, 0, 0,
				"a.sortId,b.sortId", "asc", false);
		this.categoryList = categoryService.webBusinessList(null, 0, 0,
				"sortId", "asc");

		List<BusinessUserFavorite> userFavoriteList = new ArrayList<BusinessUserFavorite>();
		if (!StringHelper.isNull(staffId)) {
			userFavoriteList = (List<BusinessUserFavorite>) favoriteService
					.findByUserId("staffId", Integer.parseInt(coopid));
		}
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

			if (c != null
					&& c.size() > 0
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
				goods.put("level", "");// vo.getBusinessGoods().getSortId()
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

				b1 = new BigDecimal(vo.getBusinessGoods()
						.getGoodsOriginalPrice() * staff.getViewDiscount());

				BigDecimal b2 = new BigDecimal(vo.getBusinessGoods()
						.getGoodsWeight());
				goods.put("price", b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP)
						.doubleValue());
				goods.put("commodity_price_id", "");
				goods.put("own_brand", "");
				goods.put("trick_base", "");
				goods.put("sold_count", 0);
				goods.put("is_favorite", 0);

				if (userFavoriteList != null && userFavoriteList.size() > 0) {
					for (int s = 0; userFavoriteList.size() > s; s++) {
						BusinessUserFavorite bu = userFavoriteList.get(s);
						if (bu.getGoodsId().equals(
								vo.getBusinessGoods().getId())) {
							goods.put("is_favorite", 1);
						}
					}
				}
				goods.put("standard_item_num", "10");

				Float p = new Double(vo.getBusinessGoods()
						.getGoodsOriginalPrice() * staff.getViewDiscount())
						.floatValue();

				goods.put("commodity_total_price",
						StringHelper.formartDecimalToStr(p));

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
	 * HTML list.html 列表页显示
	 * 
	 * @return
	 */
	@Action(value = "webList", results = { @Result(name = StrutsResMSG.SUCCESS, type = "json", params = {
			"enableGZIP", "true" }) })
	public String webList() throws IOException, JSONException {
		// 取得分类，商品所有数据
		this.voList = goodsService.webBusinessList(null, 0, 0,
				"a.sortId,b.sortId", "asc", false);
		this.categoryList = categoryService.webBusinessList(null, 0, 0,
				"sortId", "asc");
		staff = staffService.findByid(staffId);
		List<BusinessUserFavorite> userFavoriteList = new ArrayList<BusinessUserFavorite>();
		if (!StringHelper.isNull(staffId)) {
			userFavoriteList = (List<BusinessUserFavorite>) favoriteService
					.findByUserId("staffId", Integer.parseInt(staffId));
		}
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

			if (c != null
					&& c.size() > 0
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
				goods.put("level", "");// vo.getBusinessGoods().getSortId()
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

				b1 = new BigDecimal(vo.getBusinessGoods()
						.getGoodsOriginalPrice() * staff.getViewDiscount());

				BigDecimal b2 = new BigDecimal(vo.getBusinessGoods()
						.getGoodsWeight());
				goods.put("price", b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP)
						.doubleValue());
				goods.put("commodity_price_id", "");
				goods.put("own_brand", "");
				goods.put("trick_base", "");
				goods.put("sold_count", 0);
				goods.put("is_favorite", 0);

				if (userFavoriteList != null && userFavoriteList.size() > 0) {
					for (int s = 0; userFavoriteList.size() > s; s++) {
						BusinessUserFavorite bu = userFavoriteList.get(s);
						if (bu.getGoodsId().equals(
								vo.getBusinessGoods().getId())) {
							goods.put("is_favorite", 1);
						}
					}
				}
				goods.put("standard_item_num", "10");

				Float p = new Double(vo.getBusinessGoods()
						.getGoodsOriginalPrice() * staff.getViewDiscount())
						.floatValue();

				goods.put("commodity_total_price",
						StringHelper.formartDecimalToStr(p));

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
	 * HTML gwc.html 购物车页显示
	 * 
	 * @return
	 */
	@Action(value = "webGwc", results = { @Result(name = StrutsResMSG.SUCCESS, type = "json", params = {
			"enableGZIP", "true" }) })
	public String webGwc() throws IOException, JSONException {
		staff = staffService.findByid(staffId);
		// 取得分类，商品所有数据
		this.voList = goodsService.webBusinessList(null, 0, 0, "", "", false);
		this.categoryList = categoryService.listAll();
		List<BusinessUserFavorite> userFavoriteList = new ArrayList<BusinessUserFavorite>();
		if (!StringHelper.isNull(staffId)) {
			userFavoriteList = (List<BusinessUserFavorite>) favoriteService
					.findByUserId("staffId", Integer.parseInt(staffId));
		}
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
		JSONArray goodsArray = new JSONArray();
		for (Iterator it = jsonMap.keySet().iterator(); it.hasNext();) {
			String categoryId = it.next().toString();
			LinkedList<BusinessGoodsVO> c = jsonMap.get(categoryId);
			for (int i = 0; c.size() > i; i++) {
				BusinessGoodsVO vo = c.get(i);
				JSONObject goods = new JSONObject();
				goods.put(
						"id",
						StringHelper.convertUnicode(vo.getBusinessGoods()
								.getId().toString()));
				goods.put("format", StringHelper.convertUnicode(vo
						.getBusinessGoods().getGoodsWeight()
						+ vo.getBusinessWeight().getWeightName()));
				goods.put("level", vo.getBusinessGoods().getSortId());
				goods.put("price_unit", StringHelper.convertUnicode(vo
						.getBusinessWeight().getWeightName()));
				goods.put("weight", "1");
				goods.put("class1", StringHelper.convertUnicode(vo
						.getBusinessParentCategory().getCategoryName()));
				goods.put("class2", StringHelper.convertUnicode(vo
						.getBusinessCategory().getCategoryName()));
				goods.put("unit", StringHelper.convertUnicode(vo
						.getBusinessWeight().getWeightName()));
				goods.put("name", StringHelper.convertUnicode(vo
						.getBusinessGoods().getGoodsName()));
				if (vo.getBusinessGoods().getGoodsDescription() != null) {
					goods.put("subject", StringHelper.convertUnicode(vo
							.getBusinessGoods().getGoodsDescription()));
				}
				goods.put("sell_brand", "");
				// 计算平均价格
				BigDecimal b1 = new BigDecimal(vo.getBusinessGoods()
						.getGoodsOriginalPrice());
				b1 = new BigDecimal(vo.getBusinessGoods()
						.getGoodsOriginalPrice() * staff.getViewDiscount());
				BigDecimal b2 = new BigDecimal(vo.getBusinessGoods()
						.getGoodsWeight());
				goods.put("price", StringHelper.convertUnicode(String
						.valueOf(b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP)
								.doubleValue())));
				goods.put("commodity_price_id", "");
				goods.put("own_brand", "");
				goods.put("trick_base", "");
				goods.put("sold_count", StringHelper.convertUnicode(""));
				goods.put("is_favorite", StringHelper.convertUnicode("0"));
				if (userFavoriteList != null && userFavoriteList.size() > 0) {
					for (int s = 0; userFavoriteList.size() > s; s++) {
						BusinessUserFavorite bu = userFavoriteList.get(s);
						if (bu.getGoodsId().equals(
								vo.getBusinessGoods().getId())) {
							goods.put("is_favorite",
									StringHelper.convertUnicode("1"));
						}
					}
				}
				goods.put("standard_item_num", StringHelper.convertUnicode("1"));

				Float p = new Double(vo.getBusinessGoods()
						.getGoodsOriginalPrice() * staff.getViewDiscount())
						.floatValue();

				goods.put("commodity_total_price", StringHelper
						.convertUnicode(StringHelper.formartDecimalToStr(p)
								.toString()));

				goodsArray.put(goods);
			}
			listJson = goodsArray.toString();
		}

		// 取得当前用户的送货信息
		if (staffId != null) {
			clientUserRegionInfoList = clientUserRegionInfoService
					.findByProperty("staffId", Integer.parseInt(staffId));
		}
		return StrutsResMSG.SUCCESS;
	}

	/**
	 * 外部调用，获取所有组织信息
	 * 
	 * @return String
	 * @throws IOException
	 */
	@Action(value = "all", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String all() throws IOException {
		super.toJsonArray(categoryService.listAll());
		return null;
	}

	public CommonStaff getStaff() {
		return staff;
	}

	public void setStaff(CommonStaff staff) {
		this.staff = staff;
	}

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

	public String getCoopid() {
		return coopid;
	}

	public void setCoopid(String coopid) {
		this.coopid = coopid;
	}
}
