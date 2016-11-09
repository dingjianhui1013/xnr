/**
 *2012-5-11 上午9:00:02
 */
package com.xnradmin.core.service.mall.region;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.dao.mall.region.CityDAO;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.po.mall.region.City;
import com.xnradmin.vo.mall.RegionVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Service("CityService")
public class CityService {

	@Autowired
	private CityDAO dao;
	
	@Autowired
	private CommonDAO commonDao;

	static Log log = LogFactory.getLog(CityService.class);

	/**
	 * 
	 * @param po
	 * @return int
	 */
	public int save(City po) {
		if (this.dao.findByCity(po.getCity()).size() > 0) {
			return 1;
		}
		dao.save(po);
		return 0;
	}

	/**
	 * 
	 * @param id
	 * @return List<City>
	 */
	public List<City> findByProvinceId(String id) {
		return dao.findByProvinceId(id);
	}


	/**
	 * 
	 * @param id
	 * @return City
	 */
	public City findByid(String id) {
		return dao.findById(new Integer(id));
	}
	
	/**	  
	 * @param po
	 * @return int
	 */
	public int modify(City po) {
		log.info("modify : " + po.getCity() + " | "
				+ po.getProvinceId() + " | "
				+ po.getProvince() + " | "
				+ po.getId());
		this.dao.merge(po);
		//级联更新
		String updateArea = "update Area set city = '"
					+ po.getCity() + "' where cityId = " + po.getId();
		commonDao.executeUpdateOrDelete(updateArea);
		return 0;
	}

	public void del(String id){
		City po = this.dao.findById(new Integer(id));
        this.dao.delete(po);
        //级联删除
		String delArea = "delete from Area where cityId = "
				+ po.getId();
		commonDao.executeUpdateOrDelete(delArea);
    }
	
//	public int removeCityId(Integer id) {
//		return dao.removeCityId(id);
//	}
	
	
	/**
	 * @return int
	 */
	public int getCount(String countryId, String provinceId,String city) {
		String hql = "select count(*) from City where 1=1";
		if (!StringHelper.isNull(countryId)) {
			hql = hql + " and countryId = "+ countryId;
		}
		if(!StringHelper.isNull(provinceId)){
			hql = hql + " and provinceId = "+provinceId;
		}
		if(!StringHelper.isNull(city)){
			hql = hql + " and city like '%" + city +"%'";
		}
		return this.commonDao.getNumberOfEntitiesWithHql(hql);
	}
	
	/**
	 * @param curPage
	 * @param pageSize
	 * @param orderField
	 * @param direction
	 * @return List<GetOrgListVO>
	 */
	public List<RegionVO> listVO(String countryId, String provinceId,String city,
			int curPage, int pageSize, String orderField, String direction) {
		String hql = "from City where 1=1";
		if (!StringHelper.isNull(countryId)) {
			hql = hql + " and countryId = "+ countryId;
		}
		if(!StringHelper.isNull(provinceId)){
			hql = hql + " and provinceId = "+provinceId;
		}
		if(!StringHelper.isNull(city)){
			hql = hql + " and city like '%" + city +"%'";
		}
		if (!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		}else{
			hql +=" order by provinceId asc";
		}

		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage, pageSize);
		List<RegionVO> resList = new ArrayList<RegionVO>();
		for (int i = 0; i < l.size(); i++) {
			City po = (City) l.get(i);
			RegionVO vo = new RegionVO();
			vo.setCountry(po.getCountry());
			if(po.getCountryId()!=null){
				vo.setCountryId(po.getCountryId().toString());
			}
			vo.setProvince(po.getProvince());
			if(po.getProvinceId()!=null){
				vo.setProvinceId(po.getProvinceId().toString());
			}
			vo.setCity(po.getCity());
			if(po.getId()!=null){
				vo.setCityId(po.getId().toString());
			}
			resList.add(vo);
		}

		return resList;
	}
	
	
	/**
	 * @param curPage
	 * @param pageSize
	 * @param orderField
	 * @param direction
	 * @return List<GetOrgListVO>
	 */
	public List<City> listPO(String countryId, String provinceId,String city,
			int curPage, int pageSize, String orderField, String direction) {
		String hql = "from City where 1=1";
		if (!StringHelper.isNull(countryId)) {
			hql = hql + " and countryId = "+ countryId;
		}
		if(!StringHelper.isNull(provinceId)){
			hql = hql + " and provinceId = "+provinceId;
		}
		if(!StringHelper.isNull(city)){
			hql = hql + " and city like '%" + city +"%'";
		}
		if (!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		}else{
			hql +=" order by provinceId asc";
		}

		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage, pageSize);
		List<City> resList = new ArrayList<City>();
		for (int i = 0; i < l.size(); i++) {
			City po = (City) l.get(i);
			resList.add(po);
		}

		return resList;
	}
	/**
	 * @return List<CarBrandModels>
	 */
	public List<City> listAll() {
		return dao.findAll();
	}

}
