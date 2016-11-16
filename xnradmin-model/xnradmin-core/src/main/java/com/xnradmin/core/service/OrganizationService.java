/**
 *2012-5-11 上午9:00:02
 */
package com.xnradmin.core.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.dao.CommonOrganizationDAO;
import com.xnradmin.po.CommonOrganization;
import com.xnradmin.po.CommonOrganizationType;
import com.xnradmin.vo.GetOrgListVO;

/**
 * @autohr: bin_liu
 * 
 */
@Service("OrganizationService")
public class OrganizationService {

	@Autowired
	private CommonOrganizationDAO dao;
	
	@Autowired
    private CommonDAO commonDao;
	
	@Autowired
	private OrganizationTypeService typeService;

	static Log log = LogFactory.getLog(OrganizationService.class);

	/**
	 * 
	 * @param po
	 * @return int
	 */
	public int save(CommonOrganization po) {
		if (this.dao.findByOrganizationName(po.getOrganizationName()).size() > 0) {
			return 1;
		}
		dao.save(po);
		return 0;
	}

	public CommonOrganization findByid(String id) {
		return dao.findById(new Integer(id));
	}

	/**
	 * 得到页面需要的组织结构图HTML代码
	 * 
	 * @return
	 */
	public String getOrgTree() {
		StringBuffer sb = new StringBuffer();

		// 先得到LV 1菜单(只可有一个根菜单)

		String hql = " from CommonOrganization where praentOrganizationId is null";
		List rootList = commonDao.getEntitiesByPropertiesWithHql(hql, 1, 1);

		if (rootList == null || rootList.size() == 0)
			return "";

		CommonOrganization root = (CommonOrganization) rootList.get(0);

		String res = getByParentOrgid(root.getId());
		sb.append(root.getOrganizationName());

		if (!StringHelper.isNull(res)) {
			sb.append("<ul>");
			sb.append(res);
			sb.append("</ul>");
		}

		return sb.toString();
	}
	
	/**
	 * 得到所有下级组织
	 * 
	 * @param orgid
	 * @return List<CommonOrganization>
	 */
	public List<CommonOrganization> getSubOrg(Integer orgid) {
		return this.dao.findByPraentOrganizationId(orgid);
	}
	
	/**
	 * 递归拼装级联子菜单
	 * 
	 * @param parentOrgid
	 * @return String
	 */
	private String getByParentOrgid(Integer parentOrgid) {
		StringBuffer sb = new StringBuffer();

		CommonOrganization poRoot = new CommonOrganization();
		poRoot.setPraentOrganizationId(parentOrgid);
		List<CommonOrganization> rootList = this.dao.findByExample(poRoot);

		for (CommonOrganization e : rootList) {
			List<CommonOrganization> sub = getSubOrg(e.getId());

			sb.append("<li>");
			sb.append(e.getOrganizationName());
			if (sub != null && sub.size() > 0)
				sb.append("<ul>");
			sb.append(getByParentOrgid(e.getId()));
			if (sub != null && sub.size() > 0)
				sb.append("</ul>");
			sb.append("</li>");
		}

		return sb.toString();
	}
	
	/**
	 * @param porgid
	 * @return int
	 */
	public int getCountByPorgid(String porgid, String orgName) {
		String hql = "select count(*) from CommonOrganization";
		if (!StringHelper.isNull(porgid) || !StringHelper.isNull(orgName)) {
			hql = hql + " where ";
			if (!StringHelper.isNull(porgid)) {
				hql = hql + " PRAENT_ORGANIZATION_ID=" + porgid;
			}
			if (!StringHelper.isNull(orgName)) {
				if (!StringHelper.isNull(porgid))
					hql = hql + " and ";
				hql = hql + " ORGANIZATION_NAME like '%" + orgName + "%'";
			}
		}
		hql+=" order by id desc";

		return this.commonDao.getNumberOfEntitiesWithHql(hql);
	}

	/**	  
	 * @param po
	 * @return int
	 */
	public int modify(CommonOrganization po) {
		List l = this.dao.findByOrganizationName(po.getOrganizationName());

		log.info("modify : " + po.getPraentOrganizationId() + " | "
				+ po.getOrganizationTypeId());

		if (l.size() > 0) {
			CommonOrganization old = this.dao.findById(po.getId());
			if (!old.getOrganizationName().equals(po.getOrganizationName()))
				return 1;
		}
		this.dao.merge(po);
		return 0;
	}

	/**
	 * 
	 * @param porgid
	 * @param curPage
	 * @param pageSize
	 * @param orderField
	 * @param direction
	 * @return List<GetOrgListVO>
	 */
	public List<GetOrgListVO> listVO(String porgid, String orgName,
			int curPage, int pageSize, String orderField, String direction) {
		String hql = "from CommonOrganization";
		if (!StringHelper.isNull(porgid) || !StringHelper.isNull(orgName)) {
			hql = hql + " where ";
			if (!StringHelper.isNull(porgid)) {
				hql = hql + " PRAENT_ORGANIZATION_ID=" + porgid;
			}
			if (!StringHelper.isNull(orgName)) {
				if (!StringHelper.isNull(porgid))
					hql = hql + " and ";
				hql = hql + " ORGANIZATION_NAME like '%" + orgName + "%'";
			}
		}
		if (!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		}else{
			hql +=" order by id desc";
		}

		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage, pageSize); // .findByQuerySplitPage(hql,curPage,pageSize);

		List<GetOrgListVO> resList = new ArrayList<GetOrgListVO>();
		for (int i = 0; i < l.size(); i++) {
			CommonOrganization po = (CommonOrganization) l.get(i);
			GetOrgListVO vo = new GetOrgListVO();
			vo.setOrgid(po.getId().toString());
			vo.setOrgname(po.getOrganizationName());

			if (po.getPraentOrganizationId() != null) {
				CommonOrganization parent = this.dao.findById(po
						.getPraentOrganizationId());
				vo.setPorgname(parent.getOrganizationName());
			}

			CommonOrganizationType type = new CommonOrganizationType();
			if (po.getOrganizationTypeId() != null) {
				vo.setType(typeService.findByid(
						po.getOrganizationTypeId().toString())
						.getOrganizationTypeName());
			}
			resList.add(vo);

		}

		return resList;

	}

	/**
	 * @return List<CommonOrganization>
	 */
	public List<CommonOrganization> listAll() {
		return dao.findAll();
	}

}
