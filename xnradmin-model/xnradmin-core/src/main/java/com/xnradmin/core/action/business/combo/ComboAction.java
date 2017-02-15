/**
 *2012-5-10 下午3:52:29
 */
package com.xnradmin.core.action.business.combo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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

import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.core.action.ParentAction;
import com.xnradmin.core.service.StaffService;
import com.xnradmin.core.service.business.combo.ComboService;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.po.business.BusinessOrderRecord;
import com.xnradmin.po.business.Combo;
import com.xnradmin.po.common.status.Status;
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

	private ComboVO comboVo;
	
	private List<Combo> comboList;
	
	private String pageType;//页面类型 查看1 编辑2 新增3
	
	private List<Status> comboCycleStatusList;
	
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
	 * 跳转到信息页。。
	 * 
	 * @return
	 */
	@Action(value = "modifyInfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/business/admin/combo/modify.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/business/admin/combo/modify.jsp") })
	public String modifyInfo() {
		//初始化comboVo
		initComboVo();
		findComboCycleStatusList();
		if(!pageType.equals("3")){
			
		}
		return StrutsResMSG.SUCCESS;
	}
	
	/**
	 * 跳转到信息页。。
	 * 
	 * @return
	 * @throws IOException 
	 */
	@Action(value = "modify", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/business/admin/combo/modify.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/business/admin/combo/modify.jsp") })
	public String modify() throws IOException {
		
		
		comboService.modify(comboVo);
		super.success(null, null,"allocationManager", null);
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
	
	
}
