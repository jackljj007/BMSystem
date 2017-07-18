package works.web.security;

import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import works.entity.security.Authority;
import works.entity.security.Role;
import works.service.security.SecurityManager;
import works.web.CrudActionSupport;
import org.springside.modules.orm.hibernate.HibernateWebUtils;

/**
 * 角色管理Action.
 * 
 * 演示不分页的简单管理界面.
 * 
 * @author calvin
 */
@SuppressWarnings("serial")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "role.action", type = "redirect") })
public class RoleAction extends CrudActionSupport<Role> {

	@Autowired
	private SecurityManager securityManager;

	// 页面属性 //
	private Long id;
	private Role entity;
	private List<Role> allRoleList;//角色列表
	private List<Long> checkedAuthIds;//页面中钩选的权限id列表

	// ModelDriven 与 Preparable函数 //
	public Role getModel() {
		return entity;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			entity = securityManager.getRole(id);
		} else {
			entity = new Role();
		}
	}

	// CRUD Action 函数 //
	@Override
	public String list() throws Exception {
		allRoleList = securityManager.getAllRole();
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		checkedAuthIds = entity.getAuthIds();
		return INPUT;
	}

	@Override
	public String save() throws Exception {
		//根据页面上的checkbox 整合Role的Authorities Set.
		HibernateWebUtils.mergeByCheckedIds(entity.getAuthorityList(), checkedAuthIds, Authority.class);
		//保存用户并放入成功信息.
		securityManager.saveRole(entity);
		addActionMessage("保存角色成功");
		return RELOAD;
	}

	@Override
	public String delete() throws Exception {
		securityManager.deleteRole(id);
		addActionMessage("删除角色成功");
		return RELOAD;
	}

	// 页面属性访问函数 //
	public List<Role> getAllRoleList() {
		return allRoleList;
	}

	public List<Authority> getAllAuthorityList() {
		return securityManager.getAllAuthority();
	}

	public List<Long> getCheckedAuthIds() {
		return checkedAuthIds;
	}

	public void setCheckedAuthIds(List<Long> checkedAuthIds) {
		this.checkedAuthIds = checkedAuthIds;
	}
}