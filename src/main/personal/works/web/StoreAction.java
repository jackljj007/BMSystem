package works.web;

import java.util.Calendar;
import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;

import works.entity.entities.Product;
import works.entity.entities.Shelves;
import works.entity.entities.Store;
import works.service.ProductBussiness;
import works.service.ServiceException;
import works.service.ShelvesBussiness;
import works.service.StoreBussiness;

import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@Results({ @Result(name = CrudActionSupport.RELOAD, location = "store.action", type = "redirect") })
public class StoreAction extends CrudActionSupport<Store> {
	private Long id;
	private Store store;
	private Page<Store> page = new Page<Store>(5);

	public Page<Store> getPage() {
		return page;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Autowired
	private StoreBussiness storeBussiness;

	public void setStoreBussiness(StoreBussiness storeBussiness) {
		this.storeBussiness = storeBussiness;
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
	private ShelvesBussiness shelvesBussiness;

	public ShelvesBussiness getShelvesBussiness() {
		return shelvesBussiness;
	}

	public void setShelvesBussiness(ShelvesBussiness shelvesBussiness) {
		this.shelvesBussiness = shelvesBussiness;
	}

	@Override
	public String list() throws Exception {
		page = storeBussiness.getAll(page);
		return SUCCESS;
	}

	public Store getModel() {
		return store;
	}

	@Override
	public String input() throws Exception {
		List<Product> products = productBussiness.findProduct();
		ActionContext.getContext().put("products", products);
		return INPUT;
	}

	@Override
	public String save() throws Exception {
		storeBussiness.save(store);
		addActionMessage("保存成功");
		return RELOAD;
	}

	@Override
	public String delete() throws Exception {
		try {
			storeBussiness.delete(id);
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
			store = storeBussiness.get(id);
		} else {
			store = new Store();
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

	public void toshelves() throws Exception {
		store = storeBussiness.getStoreById(pid);
		Shelves shelves = new Shelves();
		Shelves shelvesProduct = shelvesBussiness.findProductByName(store
				.getProductName());
		if (shelvesProduct != null) {
			shelves = shelvesBussiness.get(shelvesProduct.getId());
			shelves.setNumber(shelvesProduct.getNumber() + num);
		} else {
			shelves.setProductName(store.getProductName());
			shelves.setNumber(num);
		}
		shelves.setShelvesDate(Calendar.getInstance().getTime());
		store.setNumber(store.getNumber() - num);
		storeBussiness.save(store);
		shelvesBussiness.save(shelves);
	}

}
