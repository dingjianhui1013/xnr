/**
 *2012-7-24 下午5:09:02
 */
package com.xnradmin.core.service;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cntinker.util.StringHelper;
import com.cntinker.uuid.UUIDGener;
import com.xnradmin.core.dao.CommonAttachDAO;
import com.xnradmin.po.CommonAttach;

/**
 * @author: liubin
 * 
 */
@Service("UploadService")
public class UploadService {

	static Log log = LogFactory.getLog(UploadService.class);

	@Autowired
	private CommonAttachDAO dao;

	/**
	 * 单文件上传,ACTION中只需要对业务层数据赋值，<br>
	 * 例如uploadStaff..flowid..orderid...<br>
	 * 文件名，路径均有service完成<br>
	 * 
	 * @param file
	 * @param po
	 * @throws Exception
	 */
	public CommonAttach upload(String oldFileName, String uploadGroupid,
			String uploadRemark, File file, CommonAttach po) throws Exception {
		log.debug("savePath::: " + path);

		try {
			String savePath = path + StringHelper.getSystime("yyyy") + "/"
					+ StringHelper.getSystime("MM") + "/"
					+ StringHelper.getSystime("dd") + "/";
			
			savePath = savePath.replaceAll("%20", " ");

			String serverPath = StringHelper.getSystime("yyyy") + "/"
					+ StringHelper.getSystime("MM") + "/"
					+ StringHelper.getSystime("dd") + "/";

			log.debug("savePath: " + savePath);
			File f1 = new File(savePath);

			if (!f1.exists()) {
				f1.mkdirs();
			}

			String fileSuffix = "";

			if (oldFileName.indexOf(".") > -1)
				fileSuffix = oldFileName
						.substring(oldFileName.lastIndexOf(".") + 1);
			else
				fileSuffix = oldFileName;

			String fileNewName = UUIDGener.getUUID();
			po.setAttachName(fileNewName);

			po.setOldFileName(oldFileName);
			po.setSuffix(fileSuffix);
			po.setAttachName(fileNewName + "." + fileSuffix);
			po.setGroupid(uploadGroupid);
			po.setPath(serverPath);
			po.setRealPath(savePath);
			//po.setUploadModel(UploadConstant.UPLOAD_MODEL_CP);
			po.setRemark(uploadRemark);
			po.setUploadTime(new Timestamp(System.currentTimeMillis()));
			
			String newF = savePath + fileNewName + "." + fileSuffix;
			File newFile = new File(newF);
			boolean success =  file.renameTo(newFile); // 保存文件

			Long id = dao.save(po);
			po.setId(id);
		} catch (Throwable e) {
			e.printStackTrace();
			throw new Exception("upload service exception");
		}
		return po;
	}

	public void modify(CommonAttach po) {
		this.dao.merge(po);
	}

	/**
	 * 得到指定groupid的总附件数
	 * 
	 * @param groupid
	 * @return int
	 */
	public int getCountByGroupid(String groupid) {
		return this.dao.findCountByGroupid(groupid);
	}

	public List<CommonAttach> getAttachsByGroupid(String groupid) {
		return this.dao.findByGroupid(groupid);
	}

	public CommonAttach getAttach(Long attachId) {
		return this.dao.findById(attachId);
	}

	public void deleteAttach(Long attachId) {
		CommonAttach attach = this.dao.findById(attachId);
		if (attach != null)
			this.dao.delete(attach);
	}

	/**
	 * 得到一组对象
	 * 
	 * @param groupid
	 * @return CommonAttach
	 */
	public List<CommonAttach> getByGroupid(String groupid) {
		return this.dao.findByGroupid(groupid);
	}

	private String path;

	public String getPath() {
		return path;
	}

	@Value(value = "${upload.path}")
	public void setPath(String path) {
		this.path = path;
	}
}
