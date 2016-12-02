/**
 * 2014年1月16日 下午3:32:39
 */
package com.xnradmin.core.test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.commons.compiler.CompileException;

import com.xnradmin.core.cache.ScriptCacheManager;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.dao.CommonJDBCDAO;
import com.xnradmin.core.dao.CommonJDBCMasterDAO;
import com.xnradmin.core.util.ScriptHelper;
import com.xnradmin.core.util.SpringBase;
import com.xnradmin.dto.ScriptDTO;
import com.xnradmin.po.wx.connect.FarmerImage;
import com.xnradmin.vo.client.ClientUserVO;

/**
 * @author: bin_liu
 */
public class TestDB {
	private static Logger log = Logger.getLogger(TestDB.class);
	private static void testDB1() {
		CommonDAO dao = (CommonDAO) SpringBase.getCtx().getBean("CommonDAO");
//		String hql = "from CommonStaff";
//		List l = dao.getEntitiesByPropertiesWithHql(hql, 0, 0);
		FarmerImage p = new FarmerImage();
		p.setUserName("123");
//		log.debug(l);
		dao.save(p);
	}

	private static void testDB2() throws CompileException,
			ClassNotFoundException, IOException {
		String test = "com.xnradmin.script.TestScript";
		String test2 = "com.xnradmin.script.business.ScriptBusiness";

		ScriptHelper service = (ScriptHelper) SpringBase.getCtx().getBean(
				"ScriptHelper");
		List<String> l = ScriptCacheManager.getKeyIndex();
		log.debug(l);

		service.loadByLocal(test);
		service.loadByLocal(test2);

		ScriptDTO dto = service.find(test);
		log.debug("dto: " + dto);
		service.del(test);

		ScriptDTO dto2 = service.find(test2);
		log.debug(dto2);
	}

	private static void testDB3() throws CompileException,
			ClassNotFoundException, IOException {
		String test = "com.xnradmin.script.TestScript";
		String test2 = "com.xnradmin.script.business.ScriptBusiness";
		ScriptHelper service = (ScriptHelper) SpringBase.getCtx().getBean(
				"ScriptHelper");

		Map<String, ScriptDTO> classMap = new Hashtable<String, ScriptDTO>();
		ScriptDTO d1 = service.loadByLocal(test);
		ScriptDTO d2 = service.loadByLocal(test2);

		classMap.put(test, d1);
		classMap.put(test2, d2);

		log.debug("classMap: " + classMap);
		classMap.remove(test2);

		classMap.get(test);
		log.debug("classMap: " + classMap);

	}

	// private static void testJDBC() throws IllegalAccessException,
	// IllegalArgumentException,InvocationTargetException{
	// CommonJDBCMasterDAO dao = (CommonJDBCMasterDAO) SpringBase.getCtx()
	// .getBean("CommonJDBCMasterDAO");
	//
	// DynamicTable t = (DynamicTable) SpringBase.getCtx().getBean(
	// "DynamicTable");
	//
	// String[] tables = t.getTables();
	// log.debug("tables: " + tables);
	// List<String> l =
	// dao.findAllTableNames("xnradmin","client_user_reg_",1);
	// log.debug("res size: " + l.size());
	// for(String e : l){
	// log.debug("table: " + e);
	//
	// String sql = "select a.* from " + e + " a";
	// ClientUserReg type = new ClientUserReg();
	// List<Object[]> rl = dao.findBySQL(type,sql,0,0);
	//
	// log.debug("rl: " + rl);
	// for(Object o : rl){
	//
	// + " | value: " + o.toString() + " ]");
	// }
	// log.debug(" | ");
	// }
	//
	// }

	private static void testDBCreate() {
		CommonJDBCMasterDAO dao = (CommonJDBCMasterDAO) SpringBase.getCtx()
				.getBean("CommonJDBCMasterDAO");

		for (int i = 0; i < 3; i++) {
			dao.createPhonenoTable("client_user_reg_" + i, 1);
		}

	}

	private static void testJDBCCount() {
		CommonJDBCDAO dao = (CommonJDBCDAO) SpringBase.getCtx().getBean(
				"CommonJDBCDAO");

		String sql = "select count(*)"
				+ " from client_user_info a left outer join description b on a.region_description_id=b.id";

		log.debug("res : " + dao.getNumberOfEntitiesWithSql(sql));
	}

	private static void testCommonJDBCDao() {
		CommonJDBCDAO dao = (CommonJDBCDAO) SpringBase.getCtx().getBean(
				"CommonJDBCDAO");
		String sql = "select count(*) from client_user_info";
		log.debug(dao.getNumberOfEntitiesWithSql(sql));
	}

	private static void testJDBC() {
		String hql = "select a.ID,a.CREATE_TIME as createtime,a.EMAIL,a.LAST_LOGIN_TIME,"
				+ " a.LOGIN_PASSWORD,a.MOBILE,a.NICK_NAME,a.PAYMENT_PASSWORD,"
				+ " a.STATUS,a.STATUS_NAME,a.UUID,a.TYPE,a.TYPE_NAME,a.MODIFY_TIME,"
				+ " a.SOURCE_NAME,a.SOURCE_TYPE,a.SOURCE_TYPE_NAME,a.SOURCE_ID,"
				+ " a.DISCOUNT,a.REGION_DESCRIPTION_ID,b.AREA_ID,b.CITY_ID,"
				+ " b.COMMUNITY_ID,b.DESCRIPTION,b.PROVINCE_ID,b.ROAD_ID,b.areaName,"
				+ " b.cityName,b.communityName,b.provinceName,b.roadName "
				+ " from client_user_info a left outer join description b on a.region_description_id=b.id";
		CommonJDBCDAO dao = (CommonJDBCDAO) SpringBase.getCtx().getBean(
				"CommonJDBCDAO");
		List<ClientUserVO> resList = new ArrayList<ClientUserVO>();
		ClientUserVO clientUserInfo = new ClientUserVO();
		try {
			resList = dao.findBySQLListMap(hql, 0, 0);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < resList.size(); i++) {
			Map m = (Map) resList.get(i);
			log.debug(m);
			// log.debug(resList.get(i));
		}

		log.debug(resList);
	}

	private static void testDB4() {
		CommonDAO dao = (CommonDAO) SpringBase.getCtx().getBean("CommonDAO");
		String hqlAppid = "select count(*) from WXUser where appid=2222";
		int countAppid = dao.getNumberOfEntitiesWithHql(hqlAppid);
		log.debug(countAppid);
	}
	
	private static void testDB5() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, InstantiationException {
		CommonJDBCDAO dao = (CommonJDBCDAO) SpringBase.getCtx().getBean("CommonJDBCDAO");
		String hqlAppid = "select t.GOODS_NAME, t.WEIGHT_NAME ,sum(t.con)  from "
				+ " ( select b.GOODS_NAME , d.WEIGHT_NAME , (b.GOODS_WEIGHT*c.GOODS_COUNT) as con "
				+ " from business_order_record a, business_goods b, business_order_goods_relation c , "
				+ " business_weight d where c.ORDER_RECORD_ID=a.id and c.GOODS_ID=b.id "
				+ " and b.GOODS_WEIGHT_ID = d.id "
				+ " order by b.GOODS_NAME ) as t group by t.GOODS_NAME, t.WEIGHT_NAME order by t.GOODS_NAME";
		List list = dao.findBySQLListMap(hqlAppid, 0, 0);
		for (int i = 0; i < list.size(); i++) {
			String[] content = new String[3];
			HashMap map = new HashMap();
			map = (HashMap) list.get(i);
			if (map == null)
				continue;
			content[0] = map.get("GOODS_NAME") == null ? "0" : map.get("GOODS_NAME").toString();
			content[1] = map.get("sum(t.con)") == null ? "0" : map.get("sum(t.con)").toString();
			content[2] = map.get("WEIGHT_NAME") == null ? "0" : map.get("WEIGHT_NAME").toString();
		}
		
	}

	public static void main(String[] args) throws Exception {
		testDB1();
	}
}
