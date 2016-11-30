/**
 *2014年12月9日 下午4:15:41
 */
package com.xnradmin.core.test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cntinker.json.JSONException;
import com.cntinker.json.JSONObject;
import com.cntinker.util.StringHelper;

/**
 * @author: liubin
 *
 */
public class TestJson2 {
	private static Logger log = Logger.getLogger(TestJson2.class);
	private static void testJsonSort() throws JSONException {
		JSONObject o = new JSONObject();
		JSONObject b = new JSONObject();
		b.put("1", "1");
		b.put("2", "2");
		o.put("aa", b);
		o.put("bb", b);
		o.put("cc", b);
		o.put("dd", b);
		log.debug(o.isNull("1"));
		log.debug(o.toString());
	}

	private static void testList() {
		List arrayList = new ArrayList();
		arrayList.add("1");
		arrayList.add("2");
		arrayList.add("3");

		for (int i = 0; i < arrayList.size(); i++) {
			log.debug(arrayList.get(i));
		}

		List linkedList = new LinkedList();
		linkedList.add("1");
		linkedList.add("2");
		linkedList.add("3");

		for (int i = 0; i < linkedList.size(); i++) {
			log.debug(linkedList.get(i));
		}
	}
	
	

	public static void main(String[] args) throws Exception {
		testList();

	}
}
