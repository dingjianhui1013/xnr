/**
 * 2014年2月22日 下午3:50:01
 */
package com.xnradmin.core.test;

import java.io.IOException;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;

import com.xnradmin.core.dao.CommonMasterDAO;
import com.xnradmin.core.util.SpringBase;

/**
 * @author: bin_liu
 */
public class TestSpring {

	private static void testBase() throws IOException {
		String c = SpringBase.getCfg().getValueByName("server.properties",
				"http.disable.dir");
		System.out.println("c: " + c);
		CommonMasterDAO dao = (CommonMasterDAO) SpringBase.getCtx().getBean(
				"CommonMasterDAO");
		System.out.println(dao.getDbname());

		Session s = dao.getSession();
		System.out.println(s);
		SessionFactory sf = dao.getSessionFactory();
		System.out.println(sf);
		
	}

	private static void testConfig() throws SQLException {
		SessionFactoryImpl sf = (SessionFactoryImpl) SpringBase.getCtx()
				.getBean("sessionFactory_master");
		System.out.println(sf);
		ConnectionProvider cp = sf.getConnectionProvider();

		System.out.println(cp.getConnection());
	}
	
	private static void testConfig2(){
		
		System.out.println(SpringBase.getCfg().getCfgPath());
		System.out.println(SpringBase.getCfg().getWorklassPath());
		System.out.println(SpringBase.getScriptDTO().getScriptDIR());
		
	}

	public static void main(String[] args) throws Exception {
		testConfig2();
	}
}
