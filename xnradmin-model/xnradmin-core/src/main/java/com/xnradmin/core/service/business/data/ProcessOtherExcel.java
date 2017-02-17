/**
 *2014年12月31日 下午12:39:34
 */
package com.xnradmin.core.service.business.data;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

/**
 * @author: liubin
 *
 */
@Service("ProcessOtherExcel")
public class ProcessOtherExcel {

	@Autowired
	private ProcessYsmcExcel ysmc;

	@Autowired
	private ProcessFdlmExcel fdlm;

	@Autowired
	private ProcessLNExcel ln;

	public void outputYsmcFile(HttpServletResponse response)
			throws IOException, SAXException, JSONException {
		ysmc.genYsmcFile(response);
	}

	public void outputLnFile(HttpServletResponse response) throws IOException,
			SAXException, JSONException {
		ln.genExcel(response);
	}

	public void outputFdlmFile(HttpServletResponse response)
			throws IOException, SAXException, JSONException {
		fdlm.genFdlmExcel(response);
	}

}
