package works.web.security;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import works.entity.security.Role;
import works.entity.security.User;
import works.service.ServiceException;
import works.service.security.SecurityManager;
import works.web.CrudActionSupport;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateWebUtils;
import org.springside.modules.web.struts2.Struts2Utils;

/**
 * 用户管理Action.
 * 
 * 使用Struts2 convention-plugin annotation定义Action参数.
 * 演示带分页的管理界面.
 * 
 * @author calvin
 */
@SuppressWarnings("serial")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "user.action", type = "redirect") })
public class UserAction extends CrudActionSupport<User> {

	@Autowired
	private SecurityManager securityManager;

	// 页面属性 //
	private Long id;
	private User entity;
	private Page<User> page = new Page<User>(5);//每页5条记录
	private List<Long> checkedRoleIds; //页面中钩选的角色id列表

	// ModelDriven 与 Preparable函数 //
	public void setId(Long id) {
		this.id = id;
	}

	public User getModel() {
		return entity;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			entity = securityManager.getUser(id);
		} else {
			entity = new User();
		}
	}

	// CRUD Action 函数 //
	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.ASC);
		}
		page = securityManager.searchUser(page, filters);
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		checkedRoleIds = entity.getRoleIds();
		return INPUT;
	}

	@Override
	public String save() throws Exception {
		//根据页面上的checkbox选择 整合User的Roles Set
		HibernateWebUtils.mergeByCheckedIds(entity.getRoleList(), checkedRoleIds, Role.class);

		securityManager.saveUser(entity);
		addActionMessage("保存用户成功");
		return RELOAD;
	}

	@Override
	public String delete() throws Exception {
		try {
			securityManager.deleteUser(id);
			addActionMessage("删除用户成功");
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage("删除用户失败");
		}
		return RELOAD;
	}

	// 其他Action函数 //
	/**
	 * 支持使用Jquery.validate Ajax检验用户名是否重复.
	 */
	public String checkLoginName() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String loginName = request.getParameter("loginName");
		String oldLoginName = request.getParameter("oldLoginName");

		if (securityManager.isLoginNameUnique(loginName, oldLoginName)) {
			Struts2Utils.renderText("true");
		} else {
			Struts2Utils.renderText("false");
		}
		//因为直接输出内容而不经过jsp,因此返回null.
		return null;
	}

	// 页面属性访问函数 //
	/**
	 * List页面的用户分页列表显示.
	 */
	public Page<User> getPage() {
		return page;
	}

	/**
	 * Input页面的用户可选角色列表显示.
	 */
	public List<Role> getAllRoleList() {
		return securityManager.getAllRole();
	}

	/**
	 * Input页面的用户拥有角色显示.
	 */
	public List<Long> getCheckedRoleIds() {
		return checkedRoleIds;
	}

	/**
	 * Input页面的用户拥有角色提交.
	 */
	public void setCheckedRoleIds(List<Long> checkedRoleIds) {
		this.checkedRoleIds = checkedRoleIds;
	}
}
