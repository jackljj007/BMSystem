package works.web;

import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateWebUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import works.entity.entities.Customer;
import works.service.CustomerBussiness;
import works.service.ServiceException;

import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@Results({ @Result(name = CrudActionSupport.RELOAD, location = "customer.action", type = "redirect") })
public class CustomerAction extends CrudActionSupport<Customer> {
	private Long id;
	private Customer customer;
	private Page<Customer> page = new Page<Customer>(5);

	public Page<Customer> getPage() {
		return page;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Autowired
	private CustomerBussiness customerBussiness;

	public void setCustomerBussiness(CustomerBussiness customerBussiness) {
		this.customerBussiness = customerBussiness;
	}

	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = HibernateWebUtils
				.buildPropertyFilters(Struts2Utils.getRequest());
		if (filters != null) {
			page = customerBussiness.searchCustomer(page, filters);
		} else {
			page = customerBussiness.getAll(page);
		}
		return SUCCESS;
	}

	public Customer getModel() {
		return customer;
	}

	@Override
	public String input() throws Exception {
		List<Customer> customers = customerBussiness.findCustomer();
		ActionContext.getContext().put("customers", customers);
		return INPUT;
	}

	@Override
	public String save() throws Exception {
		customerBussiness.save(customer);
		addActionMessage("保存成功");
		return RELOAD;
	}

	@Override
	public String delete() throws Exception {
		try {
			customerBussiness.delete(id);
			addActionMessage("删除成功");
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage("删除失败");
		}
		return RELOAD;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			customer = customerBussiness.get(id);
		} else {
			customer = new Customer();
		}
	}

}
