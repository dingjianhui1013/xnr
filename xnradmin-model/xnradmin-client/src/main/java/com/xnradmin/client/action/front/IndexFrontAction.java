package com.xnradmin.client.action.front;

import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xnradmin.client.service.IndexFrontService;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.po.business.BusinessCategory;
import com.xnradmin.vo.business.BusinessGoodsVO;

@Controller
@Scope("prototype")
@Namespace("/front")
@ParentPackage("json-default")
public class IndexFrontAction  {
	
	private List<Map<BusinessCategory, List<Map<BusinessCategory, List<BusinessCategory>>>>> allBusinessCategorys;
	private List<BusinessGoodsVO> indexGoods;
	@Autowired IndexFrontService indexFrontService;
	
	@Action(value = "index", results = { @Result(name = StrutsResMSG.SUCCESS, location = "/front/index.jsp") })
	public String info() {
		this.allBusinessCategorys = indexFrontService.getAllBusinessCategory();
		this.indexGoods = indexFrontService.listBusinessGoodsVO(0,8);
		return StrutsResMSG.SUCCESS;
	}
	
	//getter And setter
	public List<Map<BusinessCategory, List<Map<BusinessCategory, List<BusinessCategory>>>>> getAllBusinessCategorys() {
		return allBusinessCategorys;
	}

	public void setAllBusinessCategorys(
			List<Map<BusinessCategory, List<Map<BusinessCategory, List<BusinessCategory>>>>> allBusinessCategorys) {
		this.allBusinessCategorys = allBusinessCategorys;
	}

	public List<BusinessGoodsVO> getIndexGoods() {
		return indexGoods;
	}

	public void setIndexGoods(List<BusinessGoodsVO> indexGoods) {
		this.indexGoods = indexGoods;
	}

	
	
}
