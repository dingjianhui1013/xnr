/**
 *2014年8月15日 下午5:41:58
 */
package com.xnradmin.core.service.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntinker.util.FileHelper;
import com.cntinker.util.StringHelper;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.util.SpringBase;
import com.xnradmin.po.common.Log;
import com.xnradmin.po.common.status.Status;

/**
 * @author: liubin
 * 
 */
@Service("LogService")
public class LogService {

	@Autowired
	private CommonDAO commonDao;

	private void process() {

	}

	private static String getLog4jContent() throws FileNotFoundException,
			IOException {
		String c = FileHelper.getContent(getLog4j());
		return c;
	}

	private static String getLog4j() {
		String f = SpringBase.getCfg().getCfgPath();

		return f.substring(0, f.lastIndexOf("/") - 4) + "log4j.xml";
	}

	/**
	 * @param po
	 * @return int
	 */
	public int save(Log po) {
		commonDao.save(po);
		return 0;
	}

	/**
	 * @param po
	 * @return int
	 */
	public int modify(Log po) {
		this.commonDao.merge(po);
		return 0;
	}

	public int del(Status po) {
		this.commonDao.delete(po);
		return 0;
	}

	public Log findByid(String id) {
		Object o = commonDao.findById(Log.class, new Integer(id));
		if (o == null)
			return null;
		return (Log) o;
	}

	public List<Log> listVO(Log query, int curPage, int pageSize) {

		String hql = getHql(query);
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage,
				pageSize);

		return l;

	}

	public int getCount(Log query) {
		String hql = "select count(*) " + getHql(query);

		return commonDao.getNumberOfEntitiesWithHql(hql);
	}

	private String getHql(Log query) {
		StringBuffer hql = new StringBuffer("from Status");

		boolean isWhere = false;
		int isAnd = 0;
		if (query != null
				&& (!StringHelper.isNull(query.getLogName()) || !StringHelper
						.isNull(query.getDescription()))) {
			hql.append(" where ");
			isWhere = true;
		}
		if (isWhere && !StringHelper.isNull(query.getLogName())) {
			if (isAnd > 0)
				hql.append(" and ");
			hql.append(" logName like '%").append(query.getLogName())
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

		hql.append(" order by id desc");

		return hql.toString();
	}

	private static void test() throws FileNotFoundException, IOException {
		String f = SpringBase.getCfg().getCfgPath();

		System.out
				.println(f.substring(0, f.lastIndexOf("/") - 4) + "log4j.xml");

		System.out.println(getLog4jContent());
	}

	public static void main(String[] args) throws Exception {
		test();
		System.out.println(getLog4j());
		System.out.println(getLog4jContent());
	}
}
