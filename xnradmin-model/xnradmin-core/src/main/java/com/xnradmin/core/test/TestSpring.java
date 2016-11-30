/**
 * 2014年2月22日 下午3:50:01
 */
package com.xnradmin.core.test;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.log4j.Logger;
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
	private static Logger log = Logger.getLogger(TestSpring.class);
	private static void testBase() throws IOException {
		String c = SpringBase.getCfg().getValueByName("server.properties",
				"http.disable.dir");
		log.debug("c: " + c);
		CommonMasterDAO dao = (CommonMasterDAO) SpringBase.getCtx().getBean(
				"CommonMasterDAO");
		log.debug(dao.getDbname());

		Session s = dao.getSession();
		log.debug(s);
		SessionFactory sf = dao.getSessionFactory();
		log.debug(sf);
		
	}

	private static void testConfig() throws SQLException {
		SessionFactoryImpl sf = (SessionFactoryImpl) SpringBase.getCtx()
				.getBean("sessionFactory_master");
		log.debug(sf);
		ConnectionProvider cp = sf.getConnectionProvider();

		log.debug(cp.getConnection());
	}
	
	private static void testConfig2(){
		
		log.debug(SpringBase.getCfg().getCfgPath());
		log.debug(SpringBase.getCfg().getWorklassPath());
		log.debug(SpringBase.getScriptDTO().getScriptDIR());
		
	}

	public static void main(String[] args) throws Exception {
		testConfig2();
	}
}
