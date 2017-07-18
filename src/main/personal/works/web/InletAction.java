package works.web;

import java.util.Calendar;
import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;

import works.entity.entities.Inlet;
import works.entity.entities.Product;
import works.entity.entities.Store;
import works.service.InletBussiness;
import works.service.ProductBussiness;
import works.service.ServiceException;
import works.service.StoreBussiness;

import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@Results({ @Result(name = CrudActionSupport.RELOAD, location = "inlet.action", type = "redirect") })
public class InletAction extends CrudActionSupport<Inlet> {
	private Long id;
	private Inlet inlet;
	private Page<Inlet> page = new Page<Inlet>(5);

	public Page<Inlet> getPage() {
		return page;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Autowired
	private InletBussiness inletBussiness;

	public void setInletBussiness(InletBussiness inletBussiness) {
		this.inletBussiness = inletBussiness;
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

	@Override
	public String list() throws Exception {
		page = inletBussiness.getAll(page);
		return SUCCESS;
	}

	public Inlet getModel() {
		return inlet;
	}

	@Override
	public String input() throws Exception {
		List<Product> products = productBussiness.findProduct();
		ActionContext.getContext().put("products", products);
		return INPUT;
	}

	@Override
	public String save() throws Exception {
		Inlet inletProduct = inletBussiness.findProductByName(inlet
				.getProductName());
		Inlet inleted = null;
		if (inletProduct != null) {
			inleted = inletBussiness.get(inletProduct.getId());
			inleted.setNumber(inlet.getNumber() + inletProduct.getNumber());
			inleted.setInletDate(inlet.getInletDate());
		} else {
			inleted = inlet;
		}
		inletBussiness.save(inleted);
		addActionMessage("保存成功");
		return RELOAD;
	}

	@Override
	public String delete() throws Exception {
		try {
			inletBussiness.delete(id);
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
			inlet = inletBussiness.get(id);
		} else {
			inlet = new Inlet();
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

	public void instore() throws Exception {
		inlet = inletBussiness.getInletById(pid);
		Store store = new Store();
		Store storeProduct = storeBussiness.findProductByName(inlet
				.getProductName());
		if (storeProduct != null) {
			store = storeBussiness.get(storeProduct.getId());
			store.setNumber(storeProduct.getNumber() + num);
		} else {
			store.setProductName(inlet.getProductName());
			store.setNumber(num);
		}
		store.setStoreDate(Calendar.getInstance().getTime());
		inlet.setNumber(inlet.getNumber() - num);
		inletBussiness.save(inlet);
		storeBussiness.save(store);
	}

}
