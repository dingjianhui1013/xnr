/**
 *2012-4-30 下午11:17:12
 */
package com.xnradmin.vo.mall;


import java.io.Serializable;

/**
 * @autohr: xiang_liu
 */
public class RegionVO implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String countryId;
    
    private String country;
    
    private String provinceId;
    
    private String province;
    
    private String cityId;

    private String city;
    
    private String areaId;

    private String area;

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
}
