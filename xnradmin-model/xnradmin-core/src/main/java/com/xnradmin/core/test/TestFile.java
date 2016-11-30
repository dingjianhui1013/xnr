/**
 *2014年9月3日 下午4:42:01
*/
package com.xnradmin.core.test;

import java.io.File;

import org.apache.log4j.Logger;

import com.cntinker.util.ConfigHelper;

/**
 * @author: liubin
 *
 */
public class TestFile {
	private static Logger log = Logger.getLogger(TestFile.class);
	private static void testFile(){
		File f = new File("/Users/liubin/temp/菜品图片定版/荤菜/temp/番茄鱼片.JPG.new.jpg.sy.jpg");
		String savePath = "/Users/liubin/source/xnradmin/xnradmin-web/target/xnradmin-web/data/2014/09/03/";
		String fileNewName = "番茄鱼片.JPG.new.jpg.sy";
		boolean success = f.renameTo(new File(savePath + fileNewName + ".jpg"));
		log.debug(success);
	}
	
	
	private static void testFile2(){
		File f = new File("/Volumes/Macintosh\\ HD/Users/liubin/Documents/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp8/work/Catalina/localhost/_/upload_963149e3_99a4_4730_a586_eb443b064c0d_00000003.tmp");
		String savePath = "/Users/liubin/source/xnradmin/xnradmin-web/target/xnradmin-web/data/2014/09/03/";
		String fileNewName = "temp.JPG.new.jpg.sy";
		boolean success = f.renameTo(new File(savePath + fileNewName + ".jpg"));
		log.debug(success);
	}
	
	private static void testConfig(){
		ConfigHelper c = new ConfigHelper(new Test());
		log.debug(c);
		log.debug(c.getCfgPath());
	}

	public static void main(String[] args) throws Exception {
		testFile2();
		//testConfig();
	}
}
