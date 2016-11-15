/**
 *2012-9-18 下午3:58:47
 */
package com.xnradmin.core.action.dishes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.core.action.ParentAction;
import com.xnradmin.core.service.dishes.UploadOrderService;
import com.xnradmin.po.CommonAttach;

/**
 * @author: bin_liu
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/page/dishes/upload")
@ParentPackage("json-default")
public class UploadOrderAction extends ParentAction {

	static Log log = LogFactory.getLog(UploadOrderAction.class);

	@Autowired
	private UploadOrderService uploadOrderService;

	@Override
	public boolean isPublic() {
		return false;
	}

	@Action(value = "uploadOrder", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/dishes/upload/uploadOrder.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/dishes/upload/uploadOrder.jsp") })
	public String uploadTecAttachInfo() throws IOException {
		// uploadGroupid = UUIDGener.getUUID();
		return StrutsResMSG.SUCCESS;
	}

	@Action(value = "uploadTecAttachments")
	public String uploadTecAttachments() throws IOException {
		log.debug("uploadTecAttachments----in");
		try {
			int i=0;
			for (File file : getFiles()) {
				//文件名形如：ID.文件后缀
				String newFileName = getSavePath() + "\\"
						+ UUID.randomUUID().toString()+"."+filesFileName.get(i);
				FileOutputStream fos = new FileOutputStream(newFileName);
				FileInputStream fis = new FileInputStream(file);
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.close();
				fis.close();
				i++;
				uploadOrderService.excelIntoDB(newFileName);
			}
			super.success(null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			super.error("上传附件产生内部错误，请联系管理员");
		}
		return null;
	}

	/**
	 * 上传文件域 集合
	 */
	private List<File> files;

	/**
	 * 上传文件名 列表
	 */
	private List<String> filesFileName;

	public List<String> getFilesFileName() {
		return filesFileName;
	}

	public void setFilesFileName(List<String> filesFileName) {
		this.filesFileName = filesFileName;
	}

	public List<String> getFilesContentType() {
		return filesContentType;
	}

	public void setFilesContentType(List<String> filesContentType) {
		this.filesContentType = filesContentType;
	}

	/**
	 * 上传文件类型 列表
	 */
	private List<String> filesContentType;

	/**
	 * 保存路径
	 */
	private static String savePath="/upload";

	public String getSavePath() throws Exception{
		//return "C:/work/eclipse-jee-kepler-R-win32-x86_64/eclipse/javaworkspace/xnradmin/xnradmin-web/src/main/webapp/upload";
		return ServletActionContext.getServletContext().getRealPath("") + savePath;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	/**
	 * 保存
	 * 
	 * @return
	 * @throws Exception
	 */
	public String save() {

		log.info("filesFileName:" + filesFileName);
		log.info("filesContentType:"+filesContentType);
		
		try {
			int i=0;
			for (File file : getFiles()) {
				//文件名形如：ID.文件后缀
				FileOutputStream fos = new FileOutputStream(getSavePath() + "\\"
						+ UUID.randomUUID().toString()+"."+filesFileName.get(i));
				FileInputStream fis = new FileInputStream(file);
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.close();
				fis.close();
				i++;
			}
		} catch (Exception e) {
			log.error("保存",e);
		}
		return null;
	}
}
