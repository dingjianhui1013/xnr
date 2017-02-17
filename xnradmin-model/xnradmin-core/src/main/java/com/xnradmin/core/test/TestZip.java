/**
 *2015年3月14日 下午3:22:34
 */
package com.xnradmin.core.test;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.cntinker.util.FileHelper;
import com.cntinker.util.ZipHelper;

/**
 * @author: liubin
 *
 */
public class TestZip {
	private static Logger log = Logger.getLogger(TestZip.class);
	private static void comDir() throws IOException {
		String srcDir = "/Users/liubin/temp/temp2/";
		FileHelper.mkdir(srcDir);
		String outDir = srcDir + "/temp.zip";
		ZipHelper.compress(srcDir, outDir);
	}

	public static void main(String[] args) throws Exception {
		comDir();
		log.debug("finish");

	}
}
