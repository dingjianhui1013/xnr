/**
 * 2012-5-15 下午1:54:08
 */
package com.xnradmin.core.action;


import java.io.IOException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.service.CustomerService;
import com.xnradmin.constant.AjaxResult;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.po.CommonContact;
import com.xnradmin.po.CommonCustomer;
import com.xnradmin.vo.ContactVO;

/**
 * @author: bin_liu
 */
@Controller
@Scope("prototype")
@Namespace("/page/cus")
public class CustomerAction extends ParentAction{

    static Log log = LogFactory.getLog(CustomerAction.class);

    @Autowired
    private CustomerService service;

    @Override
    public boolean isPublic(){
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * 跳转到信息页
     * 
     * @return
     */
    @Action(value = "info", results = {
            @Result(name = StrutsResMSG.SUCCESS, location = "/customer/info.jsp"),
            @Result(name = StrutsResMSG.FAILED, location = "/customer/info.jsp")})
    public String info(){
        setPageInfo();
        return StrutsResMSG.SUCCESS;
    }

    @Action(value = "lookup", results = {
            @Result(name = StrutsResMSG.SUCCESS, location = "/customer/lookup.jsp"),
            @Result(name = StrutsResMSG.FAILED, location = "/customer/lookup.jsp")})
    public String lookup(){
        setPageInfo();
        return StrutsResMSG.SUCCESS;
    }

    @Action(value = "multLookup", results = {
            @Result(name = StrutsResMSG.SUCCESS, location = "/customer/multLookup.jsp"),
            @Result(name = StrutsResMSG.FAILED, location = "/customer/multLookup.jsp")})
    public String multLookup(){
        setPageInfo();
        return StrutsResMSG.SUCCESS;
    }

    /**
     * 带信息到修改页
     * 
     * @return String
     */
    @Action(value = "modifyinfo", results = {
            @Result(name = StrutsResMSG.SUCCESS, location = "/customer/modify.jsp"),
            @Result(name = StrutsResMSG.FAILED, location = "/customer/modify.jsp")})
    public String modifyinfo(){

        this.customer = service.findCustomerByID(queryid);
        return StrutsResMSG.SUCCESS;
    }

    @Action(value = "conInfo", results = {
            @Result(name = StrutsResMSG.SUCCESS, location = "/customer/contactInfo.jsp"),
            @Result(name = StrutsResMSG.FAILED, location = "/customer/contactInfo.jsp")})
    public String conInfo() throws JSONException,IOException{
        this.contListVO = service.listVo(conid,searchName,getPageNum(),
                getNumPerPage(),orderField,orderDirection);
        this.totalCount = service.getCount(conid,searchName);
        return StrutsResMSG.SUCCESS;
    }

    @Action(value = "conAllInfo", results = {
            @Result(name = StrutsResMSG.SUCCESS, location = "/customer/contactInfo.jsp"),
            @Result(name = StrutsResMSG.FAILED, location = "/customer/contactInfo.jsp")})
    public String conAllInfo() throws JSONException,IOException{
        this.contListVO = service.listVo(null,searchName,getPageNum(),
                getNumPerPage(),orderField,orderDirection);
        this.totalCount = service.getCount(conid,searchName);
        return StrutsResMSG.SUCCESS;
    }

    @Action(value = "conModifyInfo", results = {
            @Result(name = StrutsResMSG.SUCCESS, location = "/customer/contactModify.jsp"),
            @Result(name = StrutsResMSG.FAILED, location = "/customer/contactModify.jsp")})
    public String conModifyInfo(){
        this.contactVO = service.findContactByID(this.conid,this.queryid);
        return StrutsResMSG.SUCCESS;
    }

    @Action(value = "conAdd", results = {
            @Result(name = StrutsResMSG.SUCCESS, location = "/customer/contactAdd.jsp"),
            @Result(name = StrutsResMSG.FAILED, location = "/customer/contactAdd.jsp")})
    public String conAdd(){
        this.customer = service.findCustomerByID(this.conid);
        return StrutsResMSG.SUCCESS;
    }

    @Action(value = "conModify")
    public String conModify() throws IOException,JSONException{
        // this.service.saveCustomer(po);

        CommonContact po = new CommonContact();
        po.setId(new Integer(contid));
        po.setContactName(contactName);
        po.setEmail(contactEmail);
        po.setMobile(contactMobile);
        po.setPosition(contactExtent);

        this.service.modifyContact(po);

        super.success(null,AjaxResult.CALL_BACK_TYPE_CLOSECURRENT,
                "nav_cont_info",null);

        return null;
    }

    @Action(value = "conSave")
    public String saveCon() throws IOException,JSONException{
        // this.service.saveCustomer(po);

        CommonContact po = new CommonContact();
        po.setContactName(contactName);
        po.setEmail(contactEmail);
        po.setMobile(contactMobile);
        po.setPosition(contactExtent);

        this.service.saveContact(queryid,po);

        super.success(null,AjaxResult.CALL_BACK_TYPE_CLOSECURRENT,
                "nav_cont_info",null);

        return null;
    }

    /**
     * 设置排序相关项
     */
    private void setSort(){
        if(!StringHelper.isNull(orderField)){
            if(orderField.equals("level")){
                this.levelsort = orderDirection;
                this.idsort = "asc";
            }else if(orderField.equals("id")){
                this.levelsort = "asc";
                this.idsort = orderDirection;
            }
        }else{
            this.levelsort = "asc";
            this.idsort = "asc";
        }
    }

    private void setPageInfo(){
        // 设置排序
        setSort();
        this.voList = service.listVO(searchName,getPageNum(),getNumPerPage(),
                orderField,orderDirection);
        this.totalCount = service.getCount(searchName);
    }

    /**
     * 保存对象接口
     * 
     * @return String
     * @throws IOException
     * @throws JSONException
     */
    @Action(value = "add")
    public String save() throws IOException,JSONException{

        CommonCustomer po = new CommonCustomer();
        po.setCity(customerCity);
        po.setCountry(customerCountry);
        po.setCustomerAddress(customerAddr);
        po.setCustomerName(customerName);
        po.setExtent(customerExtent);
        po.setLevel(customerLevel);
        po.setProvince(customerProvince);

        this.service.saveCustomer(super.getCurrentStaff(),po);

        super.success(null,AjaxResult.CALL_BACK_TYPE_CLOSECURRENT,
                "customerInfo",null);

        return StrutsResMSG.SUCCESS;
    }

    @Action(value = "modify")
    public String modify() throws JSONException,IOException{

        CommonCustomer po = new CommonCustomer();
        po.setId(new Integer(queryid));
        po.setCity(customerCity);
        po.setCountry(customerCountry);
        po.setCustomerAddress(customerAddr);
        po.setCustomerName(customerName);
        po.setExtent(customerExtent);
        po.setLevel(customerLevel);
        po.setProvince(customerProvince);
        po.setTelephone(telephone);
        po.setFax(fax);
        po.setPostCode(postcode);

        this.service.modifyCustomer(po);
        super.success(null,AjaxResult.CALL_BACK_TYPE_CLOSECURRENT,
                "customerInfo",null);

        return null;
    }

    private String searchName;

    private List<CommonCustomer> voList;

    private String queryid;

    private String idsort;

    private String levelsort;

    private CommonCustomer customer;

    private String customerName;

    private String customerCountry;

    private String customerProvince;

    private String customerCity;

    private String customerExtent;

    private String customerLevel;

    private String customerAddr;

    private String telephone;

    private String fax;

    private String postcode;

    private String conid;

    private String contactName;

    private String contactMobile;

    private String contactEmail;

    private String contactExtent;

    private List<ContactVO> contListVO;

    private ContactVO contactVO;

    private String contid;

    public String getContid(){
        return contid;
    }

    public void setContid(String contid){
        this.contid = contid;
    }

    public List<ContactVO> getContListVO(){
        return contListVO;
    }

    public void setContListVO(List<ContactVO> contListVO){
        this.contListVO = contListVO;
    }

    public ContactVO getContactVO(){
        return contactVO;
    }

    public void setContactVO(ContactVO contactVO){
        this.contactVO = contactVO;
    }

    public String getContactName(){
        return contactName;
    }

    public void setContactName(String contactName){
        this.contactName = contactName;
    }

    public String getContactMobile(){
        return contactMobile;
    }

    public void setContactMobile(String contactMobile){
        this.contactMobile = contactMobile;
    }

    public String getContactEmail(){
        return contactEmail;
    }

    public void setContactEmail(String contactEmail){
        this.contactEmail = contactEmail;
    }

    public String getContactExtent(){
        return contactExtent;
    }

    public void setContactExtent(String contactExtent){
        this.contactExtent = contactExtent;
    }

    public String getConid(){
        return conid;
    }

    public void setConid(String conid){
        this.conid = conid;
    }

    public CommonCustomer getCustomer(){
        return customer;
    }

    public void setCustomer(CommonCustomer customer){
        this.customer = customer;
    }

    public String getCustomerName(){
        return customerName;
    }

    public void setCustomerName(String customerName){
        this.customerName = customerName;
    }

    public String getCustomerCountry(){
        return customerCountry;
    }

    public void setCustomerCountry(String customerCountry){
        this.customerCountry = customerCountry;
    }

    public String getCustomerProvince(){
        return customerProvince;
    }

    public void setCustomerProvince(String customerProvince){
        this.customerProvince = customerProvince;
    }

    public String getCustomerCity(){
        return customerCity;
    }

    public void setCustomerCity(String customerCity){
        this.customerCity = customerCity;
    }

    public String getCustomerExtent(){
        return customerExtent;
    }

    public void setCustomerExtent(String customerExtent){
        this.customerExtent = customerExtent;
    }

    public String getCustomerLevel(){
        return customerLevel;
    }

    public void setCustomerLevel(String customerLevel){
        this.customerLevel = customerLevel;
    }

    public String getCustomerAddr(){
        return customerAddr;
    }

    public void setCustomerAddr(String customerAddr){
        this.customerAddr = customerAddr;
    }

    public String getIdsort(){
        return idsort;
    }

    public void setIdsort(String idsort){
        this.idsort = idsort;
    }

    public String getLevelsort(){
        return levelsort;
    }

    public void setLevelsort(String levelsort){
        this.levelsort = levelsort;
    }

    public String getQueryid(){
        return queryid;
    }

    public void setQueryid(String queryid){
        this.queryid = queryid;
    }

    public String getSearchName(){
        return searchName;
    }

    public void setSearchName(String searchName){
        this.searchName = searchName;
    }

    public List<CommonCustomer> getVoList(){
        return voList;
    }

    public void setVoList(List<CommonCustomer> voList){
        this.voList = voList;
    }

    public String getTelephone(){
        return telephone;
    }

    public void setTelephone(String telephone){
        this.telephone = telephone;
    }

    public String getFax(){
        return fax;
    }

    public void setFax(String fax){
        this.fax = fax;
    }

    public String getPostcode(){
        return postcode;
    }

    public void setPostcode(String postcode){
        this.postcode = postcode;
    }

}
