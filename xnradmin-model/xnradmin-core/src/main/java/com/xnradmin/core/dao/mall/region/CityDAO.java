package com.xnradmin.core.dao.mall.region;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.po.mall.region.City;

@Repository("CityDAO")
public class CityDAO {
	private static final Logger log = LoggerFactory.getLogger(CityDAO.class);

	@Autowired
	private CommonDAO commonDao;

	// property constants
	public static final String ID = "id";
	public static final String CITY = "city";
	public static final String PROVINCE_ID = "provinceId";
    public static final String PROVINCE = "province";
    public static final String COUNTRY_ID = "countryId";
    public static final String COUNTRY = "country";
	protected void initDao() {
		// do nothing
	}

	public void save(City city) {
		log.debug("saving City instance");
		try {
			commonDao.save(city);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(City city) {
		log.debug("deleting City instance");
		try {
			commonDao.delete(city);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public City findById(java.lang.Integer id) {
		log.debug("getting City instance with id: " + id);
		try {

			return (City) commonDao.findById(City.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<City> findByExample(City instance) {
		log.debug("finding City instance by example");
		try {

			return commonDao.getEntitesForExample(instance, 0, 0);
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByCity(String city, Object value) {
		log.debug("finding City instance with property: " + city + ", value: "
				+ value);
		try {
			String queryString = "from City as model where model." + city
					+ "= ?";
			return commonDao.getEntitiesByPropertiesWithHql(queryString, 0, 0,
					value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<City> findByProvinceId(Object provinceId) {
		return findByCity(PROVINCE_ID, provinceId);
	}

	public List<City> findByCity(Object city) {
		return findByCity(CITY, city);
	}

	public List findAll() {
		log.debug("finding all City instances");
		try {
			String queryString = "from City";
			return commonDao.getEntitiesByPropertiesWithHql(queryString, 0, 0);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public City merge(City detachedInstance) {
		log.debug("merging City instance");
		try {
			City result = (City) commonDao.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(City instance) {
		log.debug("attaching dirty City instance");
		try {
			commonDao.saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public int removeCityId(Integer id) {

		log.debug("CityId: " + id);
		try {
			String queryString = "delete  from City as model where model.id = "
					+ id;
			return commonDao.executeUpdateOrDelete(queryString);
		} catch (RuntimeException re) {
			log.error("removeCityId failed", re);
			throw re;
		}
	}

	public static CityDAO getFromApplicationContext(ApplicationContext ctx) {
		return (CityDAO) ctx.getBean("CityDAO");
	}
}