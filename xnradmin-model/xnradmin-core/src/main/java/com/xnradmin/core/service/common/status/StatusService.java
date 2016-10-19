/**
 * 2012-5-14 上午6:36:30
 */
package com.xnradmin.core.service.common.status;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.dao.common.status.StatusDAO;
import com.xnradmin.po.common.status.Status;

/**
 * @autohr: bin_liu
 */
@Service("StatusService")
public class StatusService {

	static Log log = LogFactory.getLog(StatusService.class);

	@Autowired
	private StatusDAO dao;

	@Autowired
	private CommonDAO commonDao;

	/**
	 * @param po
	 * @return int
	 */
	public int save(Status po) {
		dao.save(po);
		return 0;
	}

	public Status findByid(String id) {
		return dao.findById(new Integer(id));
	}

	/**
	 * @param porgid
	 * @return int
	 */
	public int getCountByPorgid(String statusName) {
		String hql = "select count(1) from Status";
		if (!StringHelper.isNull(statusName)) {
			hql = hql + " where STATUS_NAME like '%" + statusName + "%'";
		}
		return this.commonDao.getNumberOfEntitiesWithHql(hql);
	}

	/**
	 * @param po
	 * @return int
	 */
	public int modify(Status po) {
		this.dao.merge(po);
		return 0;
	}

	public int del(Status po) {
		this.dao.delete(po);
		return 0;
	}

	/**
	 * @param orgTypeName
	 * @param curPage
	 * @param pageSize
	 * @param orderField
	 * @param direction
	 * @return List<CommonStaffStatus>
	 */
	public List<Status> listVO(Status query, int curPage, int pageSize) {

		String hql = getHql(query);
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage,
				pageSize);

		return l;

	}

	public int getCount(Status query) {
		String hql = "select count(*) " + getHql(query);

		return commonDao.getNumberOfEntitiesWithHql(hql);
	}

	private String getHql(Status query) {
		StringBuffer hql = new StringBuffer("from Status");

		boolean isWhere = false;
		int isAnd = 0;
		if (query != null
				&& (!StringHelper.isNull(query.getClassName())
						|| !StringHelper.isNull(query.getDescription())
						|| !StringHelper.isNull(query.getStatusName()) || !StringHelper
							.isNull(query.getReadme()))) {
			hql.append(" where ");
			isWhere = true;
		}
		if (isWhere && !StringHelper.isNull(query.getClassName())) {
			if (isAnd > 0)
				hql.append(" and ");
			hql.append(" className like '%").append(query.getClassName())
					.append("%'");
			isAnd++;
		}
		if (isWhere && !StringHelper.isNull(query.getDescription())) {
			if (isAnd > 0)
				hql.append(" and ");
			hql.append(" description like '%").append(query.getDescription())
					.append("%'");
			isAnd++;
		}
		if (isWhere && !StringHelper.isNull(query.getStatusName())) {
			if (isAnd > 0)
				hql.append(" and ");
			hql.append(" statusName like '%").append(query.getStatusName())
					.append("%'");
			isAnd++;
		}
		if (isWhere && !StringHelper
				.isNull(query.getReadme())) {
			if (isAnd > 0)
				hql.append(" and ");
			hql.append(" readme like '%").append(query.getReadme())
					.append("%'");
			isAnd++;
		}
		if (isWhere && !StringHelper
				.isNull(query.getStatusCode())) {
			if (isAnd > 0)
				hql.append(" and ");
			hql.append(" statusCode like '%").append(query.getStatusCode())
					.append("%'");
			isAnd++;
		}
		 
		hql.append(" order by id desc");

		return hql.toString();
	}

	/**
	 * @return List<CommonStaffStatus>
	 */
	public List<Status> listAll() {
		return dao.findAll();
	}

	public Status find(Class cls, String desc, String statusName) {
		String hql = "from Status where DESCTIPTION='" + desc
				+ "' and className='" + cls.getName() + "' and statusName='"
				+ statusName + "' order by sort desc";

		List<Status> l = commonDao.getEntitiesByPropertiesWithHql(hql, 0, 0);
		if (l == null || l.size() <= 0)
			return null;
		return l.get(0);
	}
	
	public Status findByStatusCode(Class cls, String desc, String statusCode) {
		String hql = "from Status where DESCTIPTION='" + desc
				+ "' and className='" + cls.getName() + "' and statusCode='"
				+ statusCode + "' order by sort desc";

		List<Status> l = commonDao.getEntitiesByPropertiesWithHql(hql, 0, 0);
		if (l == null || l.size() <= 0)
			return null;
		return l.get(0);
	}

	public Status findStatus(Class cls, String desc) {
		String hql = "from Status where DESCTIPTION='" + desc
				+ "' and className='" + cls.getName() + "' order by sort desc";

		List<Status> l = commonDao.getEntitiesByPropertiesWithHql(hql, 0, 0);
		if (l == null || l.size() <= 0)
			return null;
		return l.get(0);
	}

	public Status findByStatusCodeOne(Class cls, String statusCode) {
		List<Status> l = findByStatusCode(cls, statusCode);
		if (l == null || l.size() <= 0)
			return null;
		return l.get(0);
	}

	public List<Status> findByStatusCode(Class cls, String statusCode) {
		if (StringHelper.isNull(statusCode))
			return null;
		String hql = "from Status where statusCode='" + statusCode
				+ "' and className='" + cls.getName() + "' order by sort desc";
		return commonDao.getEntitiesByPropertiesWithHql(hql, 0, 0);
	}

	public List<Status> find(Class cls, String desc) {
		String hql = "from Status where DESCTIPTION='" + desc
				+ "' and className='" + cls.getName() + "' order by sort desc";
		return commonDao.getEntitiesByPropertiesWithHql(hql, 0, 0);
	}

	public List<Status> find(String desc) {
		String hql = "from Status where DESCTIPTION='" + desc
				+ "'  order by sort desc";
		return commonDao.getEntitiesByPropertiesWithHql(hql, 0, 0);
	}

	public List<Status> find(Class cls) {
		String hql = "from Status where className='" + cls.getName()
				+ "'  order by sort desc";
		return commonDao.getEntitiesByPropertiesWithHql(hql, 0, 0);
	}

	public List<Status> findById(String id) {
		String hql = "from Status where id=" + id;
		return commonDao.getEntitiesByPropertiesWithHql(hql, 0, 0);
	}

}
