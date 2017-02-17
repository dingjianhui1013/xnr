/**
 * 2012-9-18 下午3:58:47
 */
package com.xnradmin.core.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.xmlbeans.impl.common.SystemCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.service.OrganizationService;
import com.xnradmin.core.service.UploadService;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.po.CommonAttach;
import com.xnradmin.po.CommonOrganization;

/**
 * @author: bin_liu
 */
@Controller
@Scope("prototype")
@Namespace("/page/attach")
@ParentPackage("json-default")
public class AttachAction extends ParentAction {

	static Log log = LogFactory.getLog(AttachAction.class);
	
	static Log exLog = LogFactory.getLog("ex");

	@Autowired
	private UploadService uploadService;

	@Autowired
	private OrganizationService orgService;

	@Override
	public boolean isPublic() {
		// TODO Auto-generated method stub
		return false;
	}

	@Action(value = "uploadAttachInfo", results = { @Result(name = StrutsResMSG.SUCCESS, location = "/uploadify/upload.jsp") })
	public String uploadAttachInfo() throws IOException {
		// uploadGroupid = UUIDGener.getUUID();
		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}

	private void setPageInfo() {
		if (!StringHelper.isNull(this.uploadGroupid)) {
			attachCount = uploadService.getCountByGroupid(uploadGroupid);
			attachList = uploadService.getAttachsByGroupid(uploadGroupid);

			uploadContent = "";
			for (CommonAttach attach : attachList) {
				uploadContent += uploadContent.length() == 0 ? attach
						.getOldFileName() : "," + attach.getOldFileName();
			}
		}
	}

	@Action(value = "upload")
	public String uploadTecAttachments() throws IOException {
		// log.debug("uploadTecAttachments----in");
		
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			log.debug("upload session id:"
					+ request.getSession().getId());
			// 设置保存信息
			CommonAttach po = new CommonAttach();
			po.setUploadStaffid(super.getCurrentStaff().getId());
			po.setUploadStaffName(super.getCurrentStaff().getStaffName());

			CommonOrganization org = orgService.findByid(super
					.getCurrentStaff().getOrganizationId().toString());

			po.setUploadStaffOrgid(org.getId());
			po.setUploadStaffOrgName(org.getOrganizationName());

			log.debug("uploadGroupid::::: " + uploadGroupid);
			uploadService.upload(uploadifyFileName, uploadGroupid,
					uploadRemark, uploadify, po);

			super.success(null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			exLog.error(StringHelper.getStackInfo(e));
			super.error("上传附件产生内部错误，请联系管理员");
		}
		return null;
	}

	@Action(value = "deleteAttach", results = { @Result(name = StrutsResMSG.SUCCESS, type = "json") })
	public String deleteAttache() {
		CommonAttach attach = uploadService.getAttach(attachId);
		if (attach != null) {
			uploadGroupid = attach.getGroupid();
			uploadService.deleteAttach(attachId);
		}
		return refreshByGroupid();
	}

	@Action(value = "refreshCountByGroupid", results = { @Result(name = StrutsResMSG.SUCCESS, type = "json") })
	public String refreshCountByGroupid() {
		countByGroupid = uploadService.getCountByGroupid(uploadGroupid);
		return StrutsResMSG.SUCCESS;
	}

	@Action(value = "refreshAttach", results = { @Result(name = StrutsResMSG.SUCCESS, type = "json") })
	public String refreshByGroupid() {
		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}

	@Action(value = "view", results = { @Result(name = StrutsResMSG.SUCCESS, location = "/uploadify/view.jsp") })
	public String view() {
		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}

	private List<CommonAttach> attachList;

	private String uploadContent;

	private Integer attachCount;

	// private String uploadGroupId;
	private int countByGroupid;

	private String uploadRemark;

	private File uploadify;

	private String uploadGroupid;

	private String uploadifyFileName;

	private Long attachId;

	private String webPath;

	public String getWebPath() {
		return webPath;
	}

	@Value(value = "${upload.webPath}")
	public void setWebPath(String webPath) {
		this.webPath = webPath;
	}

	public int getCountByGroupid() {
		return countByGroupid;
	}

	public void setCountByGroupid(int countByGroupid) {
		this.countByGroupid = countByGroupid;
	}

	public String getUploadRemark() {
		return uploadRemark;
	}

	public void setUploadRemark(String uploadRemark) {
		this.uploadRemark = uploadRemark;
	}

	public File getUploadify() {
		return uploadify;
	}

	public void setUploadify(File uploadify) {
		this.uploadify = uploadify;
	}

	public String getUploadGroupid() {
		return uploadGroupid;
	}

	public void setUploadGroupid(String uploadGroupid) {
		this.uploadGroupid = uploadGroupid;
	}

	public String getUploadifyFileName() {
		return uploadifyFileName;
	}

	public void setUploadifyFileName(String uploadifyFileName) {
		this.uploadifyFileName = uploadifyFileName;
	}

	public List<CommonAttach> getAttachList() {
		return attachList;
	}

	public void setAttachList(List<CommonAttach> attachList) {
		this.attachList = attachList;
	}

	public String getUploadContent() {
		return uploadContent;
	}

	public void setUploadContent(String uploadContent) {
		this.uploadContent = uploadContent;
	}

	public Integer getAttachCount() {
		return attachCount;
	}

	public void setAttachCount(Integer attachCount) {
		this.attachCount = attachCount;
	}

	public Long getAttachId() {
		return attachId;
	}

	public void setAttachId(Long attachId) {
		this.attachId = attachId;
	}
}
