/**
 * 2014年5月17日 下午1:12:16
 */
package com.xnradmin.core.dao;

import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.jdbc.ReturningWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author: bin_liu
 */
@Repository("CommonJDBCDAO")
public class CommonJDBCDAO<T> {

	@Autowired
	private CommonJDBCMasterDAO masterDao;

	@Autowired
	private CommonJDBCSlave01DAO slave01Dao;

	public void exeSql(String sql) {
		masterDao.exeSql(sql);
	}

	public int getNumberOfEntitiesWithSql(String sql) {
		return getNumber(sql).intValue();
	}

	public long getNumberLongOfEntitiesWithSql(String sql) {
		return getNumber(sql).longValue();
	}

	private BigInteger getNumber(String sql) {
		BigInteger o = (BigInteger) slave01Dao.findUniqueResult(sql);
		return o;
	}

	public List findBySQL(Object obj, String sql, int pageNo, int numPerPage)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, ClassNotFoundException,
			InstantiationException {
		return slave01Dao.findBySQL(obj, sql, pageNo, numPerPage);
	}

	public List<Map> findBySQLListMap(String sql, int pageNo, int numPerPage)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, ClassNotFoundException,
			InstantiationException {
		return slave01Dao.findBySQLListMap(sql, pageNo, numPerPage);
	}

}
