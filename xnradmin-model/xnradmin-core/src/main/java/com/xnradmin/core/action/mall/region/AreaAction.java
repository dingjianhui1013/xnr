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
@Namespace("/page/wx/admin/region/area")
@ParentPackage("json-default")
public class AreaAction extends ParentAction {

	@Autowired
	private AreaService service;
	private String countryId;
	private String countryName;
	private String provinceId;
	private String provinceName;
	private String cityId;
	private String cityName;
	private String areaId;
	private String areaName;
	private List<Area> poList;
	private Area area;

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

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public List<Area> getPoList() {
		return poList;
	}

	public void setPoList(List<Area> poList) {
		this.poList = poList;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	@Override
	public boolean isPublic() {
		return false;
	};

	static Log log = LogFactory.getLog(AreaAction.class);

	@Action(value = "lookup", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/region/area/lookup.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/wx/admin/region/area/lookup.jsp") })
	public String lookup() {
		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}

	private void setPageInfo() {
		this.poList = service.listPO(countryId, provinceId, cityId, areaName, getPageNum(),
				getNumPerPage(), orderField, orderDirection);
	}
}
