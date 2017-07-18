package works.web;

import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateWebUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import works.entity.entities.Product;
import works.entity.entities.Shelves;
import works.entity.entities.Sold;
import works.service.ProductBussiness;
import works.service.ServiceException;
import works.service.ShelvesBussiness;
import works.service.SoldBussiness;

import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@Results({ @Result(name = CrudActionSupport.RELOAD, location = "sold.action", type = "redirect") })
public class SoldAction extends CrudActionSupport<Sold> {
	private Long id;
	private Sold sold;
	private Page<Sold> page = new Page<Sold>(5);

	public Page<Sold> getPage() {
		return page;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Autowired
	private SoldBussiness soldBussiness;

	public void setSoldBussiness(SoldBussiness soldBussiness) {
		this.soldBussiness = soldBussiness;
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
		List<PropertyFilter> filters = HibernateWebUtils
				.buildPropertyFilters(Struts2Utils.getRequest());
		if (filters != null) {
			page = soldBussiness.searchSold(page, filters);
		} else {
			page = soldBussiness.getAll(page);
		}
		return SUCCESS;
	}

	public Sold getModel() {
		return sold;
	}

	@Override
	public String input() throws Exception {
		List<Product> products = productBussiness.findProduct();
		ActionContext.getContext().put("products", products);
		return INPUT;
	}

	@Override
	public String save() throws Exception {
		Shelves shelvesProduct = shelvesBussiness.findProductByName(sold
				.getProductName());
		Shelves shelves = shelvesBussiness.get(shelvesProduct.getId());
		if(shelves.getNumber() > sold.getNumber()){
			shelves.setNumber(shelves.getNumber() - sold.getNumber());
			shelvesBussiness.save(shelves);
			soldBussiness.save(sold);
			addActionMessage("保存成功");
		}
		return RELOAD;
	}

	@Override
	public String delete() throws Exception {
		try {
			soldBussiness.delete(id);
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
			sold = soldBussiness.get(id);
		} else {
			sold = new Sold();
		}
	}

}
