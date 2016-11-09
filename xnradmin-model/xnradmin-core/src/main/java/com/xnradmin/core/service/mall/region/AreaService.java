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
import com.xnradmin.core.dao.mall.region.AreaDAO;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.po.mall.region.Area;
import com.xnradmin.vo.mall.RegionVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Service("AreaService")
public class AreaService {
	
	//
	@Autowired
	private AreaDAO dao;

	@Autowired
	private CommonDAO commonDao;

	static Log log = LogFactory.getLog(AreaService.class);

	/**
	 * 
	 * @param po
	 * @return int
	 */
	public int save(Area po) {
		if (this.dao.findByArea(po.getArea()).size() > 0) {
			return 1;
		}
		dao.save(po);
		return 0;
	}

	public Area findByid(String id) {
		return dao.findById(new Integer(id));
	}

	/**
	 * 
	 * @param id
	 * @return List<City>
	 */
	public List<Area> findByCityId(String id) {
		return dao.findByCityId(id);
	}
	
	/**
	 * @param po
	 * @return int
	 */
	public int modify(Area po) {
		log.info("modify : " + po.getArea() + " | " + po.getProvinceId()
				+ " | " + po.getProvince() + " | " + po.getCityId() + " | "
				+ po.getCity() + " | " + po.getId());
		this.dao.merge(po);
		return 0;
	}

	public void del(String id) {
		Area po = this.dao.findById(new Integer(id));
		this.dao.delete(po);
	}

//	public int removeAreaId(Integer id) {
//		return dao.removeAreaId(id);
//	}

	/**
	 * @return int
	 */
	public int getCount(String countryId, String provinceId, String cityId, String area) {
		String hql = "select count(*) from Area where 1=1";
		if (!StringHelper.isNull(countryId)) {
			hql = hql + " and countryId = "+ countryId;
		}
		if (!StringHelper.isNull(provinceId)) {
			hql = hql + " and provinceId = " + provinceId;
		}
		if (!StringHelper.isNull(cityId)) {
			hql = hql + " and cityId = " + cityId;
		}
		if (!StringHelper.isNull(area)) {
			hql = hql + " and area like '%" + area + "%'";
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
	public List<RegionVO> listVO(String countryId, String provinceId, String cityId, String area, 
			int curPage, int pageSize, String orderField, String direction) {
		String hql = "from Area where 1=1";
		if (!StringHelper.isNull(countryId)) {
			hql = hql + " and countryId = "+ countryId;
		}
		if (!StringHelper.isNull(provinceId)) {
			hql = hql + " and provinceId = " + provinceId;
		}
		if (!StringHelper.isNull(cityId)) {
			hql = hql + " and cityId = " + cityId;
		}
		if (!StringHelper.isNull(area)) {
			hql = hql + " and area like '%" + area + "%'";
		}
		if (!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		} else {
			hql += " order by provinceId asc";
		}

		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage, pageSize);
		List<RegionVO> resList = new ArrayList<RegionVO>();
		for (int i = 0; i < l.size(); i++) {
			Area po = (Area) l.get(i);
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
			if(po.getCityId()!=null){
				vo.setCityId(po.getCityId().toString());
			}
			vo.setArea(po.getArea());
			vo.setAreaId(po.getId().toString());
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
	public List<Area> listPO(String countryId, String provinceId, String cityId, String area, 
			int curPage, int pageSize, String orderField, String direction) {
		String hql = "from Area where 1=1";
		if (!StringHelper.isNull(countryId)) {
			hql = hql + " and countryId = "+ countryId;
		}
		if (!StringHelper.isNull(provinceId)) {
			hql = hql + " and provinceId = " + provinceId;
		}
		if (!StringHelper.isNull(cityId)) {
			hql = hql + " and cityId = " + cityId;
		}
		if (!StringHelper.isNull(area)) {
			hql = hql + " and area like '%" + area + "%'";
		}
		if (!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		} else {
			hql += " order by provinceId asc";
		}

		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage, pageSize);
		List<Area> resList = new ArrayList<Area>();
		for (int i = 0; i < l.size(); i++) {
			Area po = (Area) l.get(i);
			resList.add(po);
		}

		return resList;
	}
	/**
	 * @return List<CarBrandModels>
	 */
	public List<Area> listAll() {
		return dao.findAll();
	}

}
