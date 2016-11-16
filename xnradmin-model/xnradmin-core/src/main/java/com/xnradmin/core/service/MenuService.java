/**
 * 2012-5-12 上午9:52:14
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
import com.xnradmin.core.dao.CommonMenuDAO;
import com.xnradmin.po.CommonMenu;
import com.xnradmin.po.CommonRole;
import com.xnradmin.vo.MenuVO;

/**
 * @author: bin_liu
 */
@Service("MenuService")
public class MenuService {

	static Log log = LogFactory.getLog(MenuService.class);

	@Autowired
	private CommonMenuDAO dao;

	@Autowired
	private CommonDAO commonDao;

	@Autowired
	private RoleService roleService;

	/**
	 * @param po
	 */
	public void save(CommonMenu po) {
		dao.save(po);

	}

	public CommonMenu findByid(Integer id) {
		return dao.findById(id);
	}

	/**
	 * 数据总数
	 * 
	 * @param porgid
	 * @return int
	 */
	public int getCountByPmenuName(String menuName) {
		String hql = "select count(*) from CommonMenu";
		if (!StringHelper.isNull(menuName)) {
			hql = hql + " where menu_name like '%" + menuName + "%'";
		}
		return this.commonDao.getNumberOfEntitiesWithHql(hql);
	}

	public void modify(CommonMenu po) {
		this.dao.merge(po);
	}

	/**
	 * 页面返回
	 * 
	 * @param orgTypeName
	 * @param curPage
	 * @param pageSize
	 * @param orderField
	 * @param direction
	 * @return List<MenuVO>
	 */
	public List<MenuVO> listVO(String menuName, int curPage, int pageSize,
			String orderField, String direction) {
		String hql = "from CommonMenu";
		if (!StringHelper.isNull(menuName)) {
			hql = hql + " where menu_name like '%" + menuName + "%'";
		}
		if (!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		} else {
			hql += " order by id desc";
		}

		List<CommonMenu> l = commonDao.getEntitiesByPropertiesWithHql(hql,
				curPage, pageSize);

		List<MenuVO> res = new ArrayList<MenuVO>();
		// log.info("querySize: " + l.size());
		for (CommonMenu e : l) {
			MenuVO vo = new MenuVO();
			vo.setEnmenuname(e.getEnName());
			vo.setId(e.getId().toString());
			vo.setLink(e.getMenuLink());
			vo.setMenulevel(e.getMenuLevel().toString());
			vo.setMenuname(e.getMenuName());
			vo.setSortOrder(e.getSortOrder() == null ? "0" : e.getSortOrder()
					.toString());

			if (e.getPraentMenuId() != null) {
				CommonMenu parent = this.dao.findById(e.getPraentMenuId());
				vo.setPid(parent.getId().toString());
				vo.setPmenuname(parent.getMenuName());
			}
			res.add(vo);
		}

		return res;

	}

	public void del(String id) {
		CommonMenu po = this.dao.findById(new Integer(id));
		CommonMenu ex = new CommonMenu();
		ex.setPraentMenuId(po.getId());
		List<CommonMenu> list = this.dao.findByExample(ex);
		if (list != null && list.size() > 0) {
			for (CommonMenu e : list) {
				if (e != null && e.getId() != null && e.getId().intValue() > 0)
					this.dao.delete(e);
			}
		}

		this.dao.delete(po);
	}

	/**
	 * 根据某个角色返回对应的根菜单列表
	 * 
	 * @return List<CommonMenu>
	 */
	private List<CommonMenu> menuListByRole(CommonRole role) {

		return menuList("1");
	}

	/**
	 * @param parentId
	 * @param level
	 * @return List<CommonMenu>
	 */
	private List<CommonMenu> menuList(CommonRole role, String parentId,
			String level) {
		CommonMenu po = new CommonMenu();
		po.setPraentMenuId(new Integer(parentId));
		po.setMenuLevel(new Integer(level));
		return this.dao.findByExample(po);
	}

	/**
	 * @param level
	 * @return List<CommonMenu>
	 */
	private List<CommonMenu> menuList(String level) {
		CommonMenu po = new CommonMenu();
		po.setMenuLevel(new Integer(level));
		return this.dao.findByExample(po);
	}

	/**
	 * 菜单列表
	 * 
	 * @param role
	 * @return String
	 */
	public String menuOut(List<Integer> role) {
		StringBuffer sb = new StringBuffer();
		for (Integer e : role) {
			// 暂时写死,角色ID=1=ADMIN
			if (e == 2)
				sb.append(sysMenuOut());
		}

		List<CommonMenu> root = menuListByRole(role);

		for (CommonMenu e : root) {
			sb.append("<div class=\"accordionHeader\">");
			sb.append("<h2><span>Folder</span>").append(e.getMenuName())
					.append("</h2>");
			sb.append("</div>");
			sb.append("<div class=\"accordionContent\">");
			sb.append("<ul class=\"tree treeFolder\">");
			// 得到2就级菜单
			List<CommonMenu> lv2List = menuList(role, e.getId().toString(), "2");
			for (CommonMenu l2 : lv2List) {
				// 如果有3级子菜单就变文件夹
				List<CommonMenu> lv3List = menuList(role,
						l2.getId().toString(), "3");
				if (lv3List != null && lv3List.size() > 0) {
					sb.append("<li>");
					sb.append("<a>").append(l2.getMenuName())
							.append("</a><ul>");
					// 输出3级子菜单
					for (CommonMenu l3 : lv3List) {
						sb.append("<li><a href=\"");
						sb.append(l3.getMenuLink());
						sb.append("\" target=\"navTab\" ");
						sb.append("rel=\"").append(l3.getEnName())
								.append("\">");
						sb.append(l3.getMenuName()).append("</a></li>");
					}
					sb.append("</ul></li>");
				} else {
					sb.append("<li><a href=\"");
					sb.append(l2.getMenuLink());
					sb.append("\" target=\"navTab\" ");
					sb.append("rel=\"").append(l2.getEnName()).append("\">");
					sb.append(l2.getMenuName()).append("</a></li>");
				}
			}
			sb.append("</ul>");
			sb.append("</div>");
		}
		return sb.toString();
	}

	/**
	 * 根据某个角色返回对应的根菜单列表
	 * 
	 * @return List<CommonMenu>
	 */
	private List<CommonMenu> menuListByRole(List<Integer> roleid) {

		// return menuList("1");
		return roleService.findMenuListByRoleid(roleid, "1", null);
	}

	public String sysMenuOut() {
		StringBuffer sb = new StringBuffer();
		sb.append("<div class=\"accordionHeader\">");
		sb.append("<h2><span>Folder</span>系统管理</h2>");
		sb.append("</div>");

		sb.append("<div class=\"accordionContent\">");
		sb.append("<ul class=\"tree treeFolder\">");
		sb.append("<li><a href=\"page/menu/info.action\" target=\"navTab\" rel=\"menuInfo\">菜单管理</a></li>");
		sb.append("</ul></div>	");
		return sb.toString();

	}

	/**
	 * @param parentId
	 * @param level
	 * @return List<CommonMenu>
	 */
	private List<CommonMenu> menuList(List<Integer> roleid, String parentId,
			String level) {

		return roleService.findMenuListByRoleid(roleid, level, parentId);
	}

	/**
	 * 得到所有组织类型
	 * 
	 * @return List<CommonMenu>
	 */
	public List<CommonMenu> listAll() {
		return dao.findAll();
	}
}
