/**
 *2012-5-10 下午3:52:29
 */
package com.xnradmin.core.action.mall.region;

import java.io.IOException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cntinker.util.StringHelper;
import com.xnradmin.constant.AjaxResult;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.core.action.ParentAction;
import com.xnradmin.core.service.mall.region.AreaService;
import com.xnradmin.core.service.mall.region.CityService;
import com.xnradmin.core.service.mall.region.ProvinceService;
import com.xnradmin.core.service.mall.region.CountryService;
import com.xnradmin.po.mall.region.Area;
import com.xnradmin.po.mall.region.City;
import com.xnradmin.po.mall.region.Province;
import com.xnradmin.po.mall.region.Country;
import com.xnradmin.vo.mall.RegionVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/page/wx/admin/region/country")
@ParentPackage("json-default")
public class CountryAction extends ParentAction {

	@Autowired
	private CountryService service;
	private String countryId;
	private String countryName;
	private List<Country> poList;
	private Country country;

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public List<Country> getPoList() {
		return poList;
	}

	public void setPoList(List<Country> poList) {
		this.poList = poList;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Override
	public boolean isPublic() {
		return false;
	};

	static Log log = LogFactory.getLog(ProvinceAction.class);

	@Action(value = "lookup", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/region/country/lookup.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/wx/admin/region/country/lookup.jsp") })
	public String lookup() {
		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}

	private void setPageInfo() {
		this.poList = service.listPO(countryName, getPageNum(),
				getNumPerPage(), orderField, orderDirection);
	}
}
