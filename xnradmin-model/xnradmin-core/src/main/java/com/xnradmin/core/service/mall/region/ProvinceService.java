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
import com.xnradmin.core.dao.mall.region.ProvinceDAO;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.po.mall.region.Province;
import com.xnradmin.vo.mall.RegionVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Service("ProvinceService")
public class ProvinceService {

	@Autowired
	private ProvinceDAO dao;
	
	@Autowired
    private CommonDAO commonDao;
	 
	static Log log = LogFactory.getLog(ProvinceService.class);

	/**
	 * 
	 * @param po
	 * @return int
	 */
	public int save(Province po) {
		if (this.dao.findByProvince(po.getProvince()).size() > 0) {
			return 1;
		}
		dao.save(po);
		return 0;
	}

	public Province findByid(String id) {
		return dao.findById(new Integer(id));
	}

	/**	  
	 * @param po
	 * @return int
	 */
	public int modify(Province po) {
		log.info("modify : " + po.getProvince() + " | "
				+ po.getId());
		this.dao.merge(po);
		//级联更新
		String updateCity = "update City set province = '"
				+ po.getProvince() + "' where provinceId = " + po.getId();
		commonDao.executeUpdateOrDelete(updateCity);
		
		String updateArea = "update Area set province = '"
					+ po.getProvince() + "' where provinceId = " + po.getId();
		commonDao.executeUpdateOrDelete(updateArea);
		return 0;
	}

	public void del(String id){
		Province po = this.dao.findById(new Integer(id));
        this.dao.delete(po);
        //级联删除
        String delCity = "delete from City where provinceId = "
				+ po.getId();
		commonDao.executeUpdateOrDelete(delCity);
		String delArea = "delete from Area where provinceId = "
				+ po.getId();
		commonDao.executeUpdateOrDelete(delArea);
    }
	
	
	/**
	 * @return int
	 */
	public int getCount(String countryId, String province) {
		String hql = "select count(*) from Province where 1=1";
		if (!StringHelper.isNull(countryId)) {
			hql = hql + " and countryId = "+ countryId;
		}
		if (!StringHelper.isNull(province)) {
			hql = hql + " and  province like '%"+ province +"%'";
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
	public List<RegionVO> listVO(String countryId, String province,int curPage, int pageSize, String orderField, String direction) {
		String hql = "from Province where 1=1";
		if (!StringHelper.isNull(countryId)) {
			hql = hql + " and countryId = "+ countryId;
		}
		if (!StringHelper.isNull(province)) {
			hql = hql + " and province like '%"+ province +"%'";
		}
		if (!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		}else{
			hql +=" order by id asc";
		}
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage, pageSize); // .findByQuerySplitPage(hql,curPage,pageSize);

		List<RegionVO> resList = new ArrayList<RegionVO>();
		for (int i = 0; i < l.size(); i++) {
			Province po = (Province) l.get(i);
			RegionVO vo = new RegionVO();
			vo.setCountry(po.getCountry());
			if(po.getCountryId()!=null){
				vo.setCountryId(po.getCountryId().toString());
			}
			vo.setProvince(po.getProvince());
			vo.setProvinceId(po.getId().toString());
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
	public List<Province> listPO(String countryId, String province,int curPage, int pageSize, String orderField, String direction) {
		String hql = "from Province where 1=1";
		if (!StringHelper.isNull(countryId)) {
			hql = hql + " and countryId = "+ countryId;
		}
		if (!StringHelper.isNull(province)) {
			hql = hql + " and province like '%"+ province +"%'";
		}
		if (!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		}else{
			hql +=" order by id asc";
		}
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage, pageSize); // .findByQuerySplitPage(hql,curPage,pageSize);

		List<Province> resList = new ArrayList<Province>();
		for (int i = 0; i < l.size(); i++) {
			Province po = (Province) l.get(i);
			resList.add(po);
		}

		return resList;

	}
	
	/**
	 * @return List<CarBrand>
	 */
	public List<Province> listAll() {
		return dao.findAll();
	}

}
