/**
 *2012-5-10 下午3:52:29
 */
package com.xnradmin.core.action.business.combo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xnradmin.constant.AjaxResult;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.core.action.ParentAction;
import com.xnradmin.core.service.StaffService;
import com.xnradmin.core.service.business.combo.ComboService;
import com.xnradmin.core.service.business.order.BusinessOrderRecordService;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.po.business.BusinessOrderRecord;
import com.xnradmin.po.business.Combo;
import com.xnradmin.po.business.ComboPlan;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.vo.business.BusinessOrderVO;
import com.xnradmin.vo.business.ComboGoodsVO;
import com.xnradmin.vo.business.ComboUserGoodsVO;
import com.xnradmin.vo.business.ComboUserVO;
import com.xnradmin.vo.business.ComboVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/page/business/admin/combo")
@ParentPackage("json-default")
public class ComboAction extends ParentAction {
	@Autowired
	private StatusService statusService;

	@Autowired
	private StaffService staffService;
	
	@Autowired
	private ComboService comboService;
	
	@Autowired
	BusinessOrderRecordService businessOrderRecordService;
	
	private ComboVO comboVo;
	
	private List<Combo> comboList;
	
	private ComboUserVO comboUserVo;
	
	private List<ComboUserVO> comboUserVOList;
	
	private List<BusinessOrderVO> businessOrderVOList;
	
	private List<ComboUserGoodsVO> comboUserGoodsVOList;
	
	private String pageType;//页面类型 查看1 编辑2 新增3
	
	private List<Status> comboCycleStatusList;//套餐周期
	private List<Status> comboCycleList;//固定周期
	private List<StatusBlock> comboFixedStatusList;//固定周期固定时间
	private File comboImgSmallFile; // 套餐小图片上传文件
	private File comboImgBigFile; // 套餐大图片上传文件
	private String comboImgSmallPath = "/themes/business/goodsLogo/"; // 套餐小图片上传文件路径
	private String comboImgBigPath= "/themes/business/goodsBigLogo/"; // 套餐大图片上传文件路径
	@Override
	public boolean isPublic() {
		return false;
	};

	static Log log = LogFactory.getLog(ComboAction.class);

	/**
	 * 跳转到信息页。。
	 * 
	 * @return
	 */
	@Action(value = "info", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/business/admin/combo/info.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/business/admin/combo/info.jsp") })
	public String info() {
		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}
	
	/**
	 * 跳转到用户套餐页面。。
	 * 
	 * @return
	 */
	@Action(value = "userInfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/business/admin/combo/userInfo.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/business/admin/combo/userInfo.jsp") })
	public String userInfo() {
		setPageUserInfo();
		return StrutsResMSG.SUCCESS;
	}
	
	/**
	 * 跳转到订单详情页面
	 * 
	 * @return
	 */
	@Action(value = "orderInfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/business/admin/combo/orderInfo.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/business/admin/combo/orderInfo.jsp") })
	public String orderInfo() {
		setPageOrderInfo();
		return StrutsResMSG.SUCCESS;
	}
	
	/**
	 * 跳转到订单详情页面
	 * 
	 * @return
	 */
	@Action(value = "goodsInfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/business/admin/combo/goodsInfo.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/business/admin/combo/goodsInfo.jsp") })
	public String goodsInfo() {
		setPageGoodsInfo();
		return StrutsResMSG.SUCCESS;
	}
	
	/**
	 * 跳转到信息页。。
	 * 
	 * @return
	 */
	@Action(value = "modifyInfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/business/admin/combo/modify.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/business/admin/combo/modify.jsp") })
	public String modifyInfo() {
		//初始化comboVo
		if(!pageType.equals("3")){
			//查出套餐，套餐商品，套餐计划信息 赋给 comboVo
			comboVo = comboService.getComboVOById(comboVo);
		}else{
			initComboVo();
		}
		findComboCycleStatusList();
		findComboFixedStatusList();
		findComboCycleList();
		return StrutsResMSG.SUCCESS;
	}
	
	/**
	 * 跳转到信息页。。
	 * 
	 * @return
	 * @throws IOException 
	 */
	@Action(value = "delInfo", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String delInfo() throws IOException {
		log.debug("delInfo action!");
		if(comboService.delComboVOById(comboVo)){
			super.success("删除成功", null,"comboManager", null);
		}else{
			super.success("删除失败，套餐使用中", null,"comboManager", null);			
		}
		return null;
	}
	
	/**
	 * 修改状态接口
	 * 
	 * @return String
	 * @throws Exception
	 */
	@Action(value = "changeComboUserStatus", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String changeComboUserStatus() throws Exception {
		log.debug("send action!");
		if(comboService.modifyComboUser(comboUserVo)){
			super.success("状态修改成功", null,"userCombo", null);
		}else{
			super.success("状态修改失败，套餐使用中", null,"userCombo", null);			
		}
		
		return null;
	}
	
	/**
	 * 跳转到信息页。。
	 * 
	 * @return
	 * @throws Exception 
	 */
	@Action(value = "modify", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/business/admin/combo/modify.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/business/admin/combo/modify.jsp") })
	public String modify() throws Exception {
		String comboName = "";
		if(comboVo!=null&&comboVo.getCombo()!=null){
			if(comboVo.getCombo().getComboName()!=null){
				comboName=comboVo.getCombo().getComboName()+".jpg";
			}
			//先上传保存两张图片
			String newGoodsLogo = "";
			if (comboImgSmallFile != null) {
				String fileName = UUID.randomUUID().toString() + comboName;
				newGoodsLogo = getGoodsLogoSavePath() + "/" + fileName;
				File fopath = new File(getGoodsLogoSavePath());
				if(!fopath.exists()){
					fopath.mkdirs();
				}
				FileOutputStream fos = new FileOutputStream(newGoodsLogo);
				FileInputStream fis = new FileInputStream(comboImgSmallFile);
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.close();
				fis.close();
				comboVo.getCombo().setComboImgSmall(comboImgSmallPath + fileName);
			}
			String newGoodsBigLogo = "";
			if (comboImgBigFile != null) {
				String fileName = UUID.randomUUID().toString() + comboName;
				newGoodsBigLogo = getGoodsBigLogoSavePath() + "/" + fileName;
				File fopath = new File(getGoodsBigLogoSavePath());
				if(!fopath.exists()){
					fopath.mkdirs();
				}
				FileOutputStream fos = new FileOutputStream(newGoodsBigLogo);
				FileInputStream fis = new FileInputStream(comboImgBigFile);
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.close();
				fis.close();
				comboVo.getCombo().setComboImgBig(comboImgBigPath + fileName);
			}
			
			//查询出所有状态 以备使用
			List<Status> statusList = statusService.find(Combo.class);
			statusList.addAll(statusService.find(ComboPlan.class));
			comboService.modify(comboVo,statusList);
			super.success("套餐保存成功", AjaxResult.CALL_BACK_TYPE_CLOSECURRENT,"comboManager", null);
		}else{
			super.success("套餐保存失败", null,"comboManager", null);
		}
		return null;
	}
	
	/*
	 * 加载商品图片上传路径
	 */
	public String getGoodsLogoSavePath() throws Exception {
		// return
		// "C:/work/eclipse-jee-kepler-R-win32-x86_64/eclipse/javaworkspace/xnradmin/xnradmin-web"
		// + "/src/main/webapp/themes/business/goodsLogo";
		return ServletActionContext.getServletContext().getRealPath("")
				+ comboImgSmallPath;
	}

	/*
	 * 加载商品大图片上传路径
	 */
	public String getGoodsBigLogoSavePath() throws Exception {
		// return
		// "C:/work/eclipse-jee-kepler-R-win32-x86_64/eclipse/javaworkspace/xnradmin/xnradmin-web"
		// + "/src/main/webapp/themes/business/goodsBigLogo";
		return ServletActionContext.getServletContext().getRealPath("")
				+ comboImgBigPath;
	}
	
	/**
	 * 修改状态接口
	 * 
	 * @return String
	 * @throws Exception
	 */
	@Action(value = "send", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String send() throws Exception {
		log.debug("send action!");
		if(comboService.modifyCombo(comboVo)){
			super.success("状态修改成功", null,"comboManager", null);
		}else{
			super.success("状态修改失败，套餐使用中", null,"comboManager", null);			
		}
		
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	@Action(value = "enum", results = {@Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String getEnum() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/plain; charset=UTF-8");    
        response.setHeader("Content-Disposition", "inline"); 
		PrintWriter out;
		try {
			out = response.getWriter();
			//0--固定时间 1--固定周期 2--固定周期固定时间
			out.println("<select id='comboType' name='comboType' onchange='changeComboType(this)'>"
					+ "		<option value=''>请选择</option>"
					+ "		<option value='0'>固定时间</option>"
					+ "		<option value='1'>固定周期</option>"
					+ "		<option value='2'>固定周期固定时间</option>"
					+ "  <select>"
					+ ""
					//+ "<input type=\"text\" name=\"items[0].goodsCount\" value=\"\" class=\"date required number textInput\" datefmt=\"yyyy-MM-dd\" size=\"20\"><a class=\"inputDateButton\" href=\"javascript:void(0)\">选择</a>"
					//+ "	 <select id='comboCycle' name='comboCycle'></select>"
					//+ "	 <select id='comboCycle' name='comboCycle'></select>"
					);//返回的字符串数据  
		} catch (IOException e) {
			e.printStackTrace();
		}  
		  
        return null;  
	}

	/**
	 * 
	 * @return
	 */
	@Action(value = "enumPrice", results = {@Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String getEnumPrice() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/plain; charset=UTF-8");    
        response.setHeader("Content-Disposition", "inline"); 
		PrintWriter out;
		try {
			out = response.getWriter();
			//0--固定时间 1--固定周期 2--固定周期固定时间
			out.println("<input type=\"text\" name=\"comboVo.comboGoodsList[0].comboGoods.goodsCount\" value=\"\" "
					+ "size=\"20\" class=\"required number textInput valid\" onchange=\"countPrice(this)\">");//返回的字符串数据  
		} catch (IOException e) {
			e.printStackTrace();
		}  
		  
        return null;  
	}
	
	private void setPageInfo() {
		// 设置排序
		//findBusinessGoodsStatusList();
		
		this.comboList = comboService.findByPage(comboVo, getPageNum(), getNumPerPage(),
				orderField, orderDirection);
		this.totalCount = comboService.getCount(comboVo);

	}
	
	private void setPageUserInfo() {
		// 设置排序
		
		this.comboUserVOList = comboService.findComboUsesrByPage(comboUserVo, getPageNum(), getNumPerPage(),
				orderField, orderDirection);
		this.totalCount = comboService.getComboUserCount(comboUserVo);

	}
	
	private void setPageOrderInfo() {
		BusinessOrderVO businessOrderVO = new BusinessOrderVO();
		BusinessOrderRecord bor = new BusinessOrderRecord();
		bor.setIsChild(comboUserVo.getComboUser().getOrderId());
		businessOrderVO.setBusinessOrderRecord(bor);
		this.businessOrderVOList = businessOrderRecordService.listVO(businessOrderVO, getPageNum(), getNumPerPage(),
				orderField, orderDirection);
		this.totalCount = businessOrderRecordService.getCount(businessOrderVO);
	}
	
	private void setPageGoodsInfo() {
		//一件商品一条数据 商品名，商品总数，配送总数，剩余配送数 
		//根据用户套餐查询出套餐商品  查询该用户套餐所有子订单 汇总商品数量
		//商品的ID 
		Map<Integer,ComboUserGoodsVO> goodsMap = comboService
				.findComboGoodsByComboUserId(comboUserVo.getCombo().getId());
		List<BusinessOrderVO> borList = comboService
				.findBusinessOrderRelationVOByOrderId(comboUserVo.getComboUser().getOrderId());
		List<ComboUserGoodsVO> cugVO = new ArrayList<ComboUserGoodsVO>();
		for(BusinessOrderVO vo:borList){
			ComboUserGoodsVO cgv = goodsMap.get(vo.getBusinessGoods().getId());
			//配送数计算
			if(cgv.getHasAllocateNumber()==null){
				cgv.setHasAllocateNumber(vo.getBusinessOrderGoodsRelation().getGoodsCount());
			}else{
				cgv.setHasAllocateNumber(cgv.getHasAllocateNumber()+vo.getBusinessOrderGoodsRelation().getGoodsCount());
			}
		}
		for(Entry<Integer, ComboUserGoodsVO> s:goodsMap.entrySet()){
			cugVO.add(s.getValue());
		}
		this.comboUserGoodsVOList = cugVO;
		this.totalCount = cugVO.size();
	}
	
	private void initComboVo() {
		//初始化套餐默认周期为一个月
		this.comboVo = new ComboVO();
		Combo combo = new Combo();
		combo.setComboCycleStatus("228");
		comboVo.setCombo(combo);
	}
	
	/**
	 * 加载所有套餐周期类型
	 */
	private void findComboCycleStatusList() {
		this.comboCycleStatusList = statusService.find(Combo.class,
				"comboCycleStatus");
	}
	
	/**
	 * 加载所有套餐固定周期
	 */
	private void findComboCycleList() {
		this.comboCycleList = statusService.find(Combo.class,
				"fixedCycle");
	}
	
	
	/**
	 * 加载所有套餐周期类型
	 */
	private void findComboFixedStatusList() {
		List<Status> statusList = statusService.find(ComboPlan.class);
		List<Status> tempstatusList = new ArrayList<>();
		tempstatusList.addAll(statusList);
		List<StatusBlock> tempList = new ArrayList<>();
		Iterator<Status> it = statusList.iterator();
		while(it.hasNext()){
			Status s = it.next();
			if(s.getParentId()==null){
				StatusBlock sb = new StatusBlock();
				sb.setStatus(s);
				List<StatusBlock> list = getChildList(tempstatusList,s.getId()); 
				sb.setStatusChildList(list);
				tempList.add(sb);
			}
		}
		this.comboFixedStatusList = tempList;
	}
	
	private List<StatusBlock> getChildList(List<Status> stautsList, Integer parentId) {
		List<StatusBlock> list = new ArrayList<>();
		List<Status> tempList = new ArrayList<>();
		tempList.addAll(stautsList);
		Iterator<Status> it = stautsList.iterator();
		while(it.hasNext()){
			Status s = it.next();
			if(parentId.equals(s.getParentId())){
				StatusBlock sb = new StatusBlock();
				sb.setStatus(s);
				List<StatusBlock> child = getChildList(tempList,s.getId());
				sb.setStatusChildList(child);
				list.add(sb);
				it.remove();
			}
		}
		stautsList.retainAll(tempList);
		if(list.size()>0){
			return list;
		}
		return null;
	}

	public ComboVO getComboVo() {
		return comboVo;
	}

	public void setComboVo(ComboVO comboVo) {
		this.comboVo = comboVo;
	}

	public List<Combo> getComboList() {
		return comboList;
	}

	public void setComboList(List<Combo> comboList) {
		this.comboList = comboList;
	}

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	public List<Status> getComboCycleStatusList() {
		return comboCycleStatusList;
	}

	public void setComboCycleStatusList(List<Status> comboCycleStatusList) {
		this.comboCycleStatusList = comboCycleStatusList;
	}

	public List<StatusBlock> getComboFixedStatusList() {
		return comboFixedStatusList;
	}

	public void setComboFixedStatusList(List<StatusBlock> comboFixedStatusList) {
		this.comboFixedStatusList = comboFixedStatusList;
	}

	public List<Status> getComboCycleList() {
		return comboCycleList;
	}

	public void setComboCycleList(List<Status> comboCycleList) {
		this.comboCycleList = comboCycleList;
	}

	public File getComboImgSmallFile() {
		return comboImgSmallFile;
	}

	public void setComboImgSmallFile(File comboImgSmallFile) {
		this.comboImgSmallFile = comboImgSmallFile;
	}

	public File getComboImgBigFile() {
		return comboImgBigFile;
	}

	public void setComboImgBigFile(File comboImgBigFile) {
		this.comboImgBigFile = comboImgBigFile;
	}

	public String getComboImgSmallPath() {
		return comboImgSmallPath;
	}

	public void setComboImgSmallPath(String comboImgSmallPath) {
		this.comboImgSmallPath = comboImgSmallPath;
	}

	public String getComboImgBigPath() {
		return comboImgBigPath;
	}

	public void setComboImgBigPath(String comboImgBigPath) {
		this.comboImgBigPath = comboImgBigPath;
	}

	public ComboUserVO getComboUserVo() {
		return comboUserVo;
	}

	public void setComboUserVo(ComboUserVO comboUserVo) {
		this.comboUserVo = comboUserVo;
	}

	public List<ComboUserVO> getComboUserVOList() {
		return comboUserVOList;
	}

	public void setComboUserVOList(List<ComboUserVO> comboUserVOList) {
		this.comboUserVOList = comboUserVOList;
	}

	public List<BusinessOrderVO> getBusinessOrderVOList() {
		return businessOrderVOList;
	}

	public void setBusinessOrderVOList(List<BusinessOrderVO> businessOrderVOList) {
		this.businessOrderVOList = businessOrderVOList;
	}

	public List<ComboUserGoodsVO> getComboUserGoodsVOList() {
		return comboUserGoodsVOList;
	}

	public void setComboUserGoodsVOList(List<ComboUserGoodsVO> comboUserGoodsVOList) {
		this.comboUserGoodsVOList = comboUserGoodsVOList;
	}
	
}

class StatusBlock{
	Status status;
	List<StatusBlock> statusChildList;
	
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public List<StatusBlock> getStatusChildList() {
		return statusChildList;
	}
	public void setStatusChildList(List<StatusBlock> statusChildList) {
		this.statusChildList = statusChildList;
	}
}

