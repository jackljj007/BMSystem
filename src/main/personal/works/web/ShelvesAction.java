package works.web;

import java.util.Calendar;
import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;

import works.entity.entities.Customer;
import works.entity.entities.Shelves;
import works.entity.entities.Store;
import works.service.CustomerBussiness;
import works.service.ProductBussiness;
import works.service.ServiceException;
import works.service.ShelvesBussiness;
import works.service.StoreBussiness;

import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@Results({ @Result(name = CrudActionSupport.RELOAD, location = "shelves.action", type = "redirect") })
public class ShelvesAction extends CrudActionSupport<Shelves> {
	private Long id;
	private Shelves shelves;
	private Page<Shelves> page = new Page<Shelves>(5);

	public Page<Shelves> getPage() {
		return page;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Autowired
	private ShelvesBussiness shelvesBussiness;

	public void setShelvesBussiness(ShelvesBussiness shelvesBussiness) {
		this.shelvesBussiness = shelvesBussiness;
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
	private StoreBussiness storeBussiness;

	public StoreBussiness getStoreBussiness() {
		return storeBussiness;
	}

	public void setStoreBussiness(StoreBussiness storeBussiness) {
		this.storeBussiness = storeBussiness;
	}

	@Autowired
	private CustomerBussiness customerBussiness;

	public CustomerBussiness getCustomerBussiness() {
		return customerBussiness;
	}

	public void setCustomerBussiness(CustomerBussiness customerBussiness) {
		this.customerBussiness = customerBussiness;
	}

	@Override
	public String list() throws Exception {
		page = shelvesBussiness.getAll(page);
		return SUCCESS;
	}

	public Shelves getModel() {
		return shelves;
	}

	@Override
	public String input() throws Exception {
		List<Customer> customers = customerBussiness.findCustomer();
		ActionContext.getContext().put("customers", customers);
		return INPUT;
	}

	@Override
	public String save() throws Exception {
		shelvesBussiness.save(shelves);
		addActionMessage("保存成功");
		return RELOAD;
	}

	@Override
	public String delete() throws Exception {
		try {
			shelvesBussiness.delete(id);
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
			shelves = shelvesBussiness.get(id);
		} else {
			shelves = new Shelves();
		}
	}

	Long pid;
	int num;

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public void returnstore() throws Exception {
		shelves = shelvesBussiness.getShelvesById(pid);
		Store store = new Store();
		Store storeProduct = storeBussiness.findProductByName(shelves
				.getProductName());
		if (storeProduct != null) {
			store = storeBussiness.get(storeProduct.getId());
			store.setNumber(storeProduct.getNumber() + num);
		} else {
			store.setProductName(shelves.getProductName());
			store.setNumber(num);
		}
		store.setStoreDate(Calendar.getInstance().getTime());
		shelves.setNumber(shelves.getNumber() - num);
		shelvesBussiness.save(shelves);
		storeBussiness.save(store);
	}

}
