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
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.po.mall.region.Country;
import com.xnradmin.vo.mall.RegionVO;
import com.xnradmin.core.dao.mall.region.CountryDAO;

/**
 * @autohr: xiang_liu
 * 
 */
@Service("CountryService")
public class CountryService {

	@Autowired
	private CountryDAO dao;
	
	@Autowired
    private CommonDAO commonDao;
	 
	static Log log = LogFactory.getLog(ProvinceService.class);

	/**
	 * 
	 * @param po
	 * @return int
	 */
	public int save(Country po) {
		if (this.dao.findByProvince(po.getCountry()).size() > 0) {
			return 1;
		}
		dao.save(po);
		return 0;
	}

	public Country findByid(String id) {
		return dao.findById(new Integer(id));
	}

	/**	  
	 * @param po
	 * @return int
	 */
	public int modify(Country po) {
		log.info("modify : " + po.getCountry() + " | "
				+ po.getId());
		this.dao.merge(po);
		//级联更新
		String updateCity = "update City set country = '"
				+ po.getCountry() + "' where countryId = " + po.getId();
		commonDao.executeUpdateOrDelete(updateCity);
		
		String updateArea = "update Area set country = '"
					+ po.getCountry() + "' where countryId = " + po.getId();
		commonDao.executeUpdateOrDelete(updateArea);
		
		String updateRoad = "update Province set country = '"
				+ po.getCountry() + "' where countryId = " + po.getId();
		commonDao.executeUpdateOrDelete(updateRoad);
		
		return 0;
	}

	public void del(String id){
		Country po = this.dao.findById(new Integer(id));
        this.dao.delete(po);
        //级联删除
        String delCity = "delete from City where countryId = "
				+ po.getId();
		commonDao.executeUpdateOrDelete(delCity);
		String delArea = "delete from Area where countryId = "
				+ po.getId();
		commonDao.executeUpdateOrDelete(delArea);
		String delProvince = "delete from Province where countryId = "
				+ po.getId();
		commonDao.executeUpdateOrDelete(delProvince);
    }
	
	
	/**
	 * @return int
	 */
	public int getCount(String country) {
		String hql = "select count(*) from Country";
		if (!StringHelper.isNull(country)) {
			hql = hql + " where  country like '%"+ country +"%'";
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
	public List<RegionVO> listVO(String country,int curPage, int pageSize, String orderField, String direction) {
		String hql = "from Country";
		if (!StringHelper.isNull(country)) {
			hql = hql + " where  country like '%"+ country +"%'";
		}
		if (!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		}else{
			hql +=" order by id asc";
		}
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage, pageSize); // .findByQuerySplitPage(hql,curPage,pageSize);

		List<RegionVO> resList = new ArrayList<RegionVO>();
		for (int i = 0; i < l.size(); i++) {
			Country po = (Country) l.get(i);
			RegionVO vo = new RegionVO();
			vo.setCountry(po.getCountry());
			vo.setCountryId(po.getId().toString());
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
	public List<Country> listPO(String country,int curPage, int pageSize, String orderField, String direction) {
		String hql = "from Country";
		if (!StringHelper.isNull(country)) {
			hql = hql + " where  country like '%"+ country +"%'";
		}
		if (!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		}else{
			hql +=" order by id asc";
		}
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage, pageSize); // .findByQuerySplitPage(hql,curPage,pageSize);

		List<Country> resList = new ArrayList<Country>();
		for (int i = 0; i < l.size(); i++) {
			Country po = (Country) l.get(i);
			resList.add(po);
		}

		return resList;

	}
	
	/**
	 * @return List<CarBrand>
	 */
	public List<Country> listAll() {
		return dao.findAll();
	}

}
