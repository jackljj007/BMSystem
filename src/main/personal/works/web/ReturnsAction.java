package works.web;

import java.util.Calendar;
import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;

import works.entity.entities.Customer;
import works.entity.entities.Product;
import works.entity.entities.Returns;
import works.entity.entities.Store;
import works.service.CustomerBussiness;
import works.service.ProductBussiness;
import works.service.ReturnsBussiness;
import works.service.ServiceException;
import works.service.StoreBussiness;

import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@Results({ @Result(name = CrudActionSupport.RELOAD, location = "returns.action", type = "redirect") })
public class ReturnsAction extends CrudActionSupport<Returns> {
	private Long id;
	private Returns returns;
	private Page<Returns> page = new Page<Returns>(5);

	public Page<Returns> getPage() {
		return page;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Autowired
	private ReturnsBussiness returnsBussiness;

	public void setReturnsBussiness(ReturnsBussiness returnsBussiness) {
		this.returnsBussiness = returnsBussiness;
	}

	@Autowired
	private ProductBussiness productBussiness;

	public ProductBussiness getProductBussiness() {
		return productBussiness;
	}

	public void setProductBussiness(ProductBussiness productBussiness) {
		this.productBussiness = productBussiness;
	}

	@Autowired
	private CustomerBussiness customerBussiness;

	public CustomerBussiness getCustomerBussiness() {
		return customerBussiness;
	}

	public void setCustomerBussiness(CustomerBussiness customerBussiness) {
		this.customerBussiness = customerBussiness;
	}

	@Autowired
	private StoreBussiness storeBussiness;

	public StoreBussiness getStoreBussiness() {
		return storeBussiness;
	}

	public void setStoreBussiness(StoreBussiness storeBussiness) {
		this.storeBussiness = storeBussiness;
	}

	@Override
	public String list() throws Exception {
		page = returnsBussiness.getAll(page);
		return SUCCESS;
	}

	public Returns getModel() {
		return returns;
	}

	@Override
	public String input() throws Exception {
		List<Product> products = productBussiness.findProduct();
		List<Customer> customers = customerBussiness.findCustomer();
		ActionContext.getContext().put("products", products);
		ActionContext.getContext().put("customers", customers);
		return INPUT;
	}

	@Override
	public String save() throws Exception {
		returnsBussiness.save(returns);
		addActionMessage("保存成功");
		return RELOAD;
	}

	@Override
	public String delete() throws Exception {
		try {
			returnsBussiness.delete(id);
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
			returns = returnsBussiness.get(id);
		} else {
			returns = new Returns();
		}
	}

	public void returnstore() throws Exception {
		returns = returnsBussiness.getReturnsById(id);
		Store store = new Store();
		Store storeProduct = storeBussiness.findProductByName(returns
				.getProductName());
		if (storeProduct != null) {
			store = storeBussiness.get(storeProduct.getId());
			store.setNumber(storeProduct.getNumber() + returns.getNumber());
		} else {
			store.setProductName(returns.getProductName());
			store.setNumber(returns.getNumber());
		}
		store.setStoreDate(Calendar.getInstance().getTime());
		storeBussiness.save(store);
		returns.setState(1);
		returnsBussiness.save(returns);
	}

}
