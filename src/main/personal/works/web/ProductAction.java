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
import works.entity.entities.Supply;
import works.service.ProductBussiness;
import works.service.ServiceException;
import works.service.SupplyBussiness;

import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@Results({ @Result(name = CrudActionSupport.RELOAD, location = "product.action", type = "redirect") })
public class ProductAction extends CrudActionSupport<Product> {
	private Long id;
	private Product product;
	private Page<Product> page = new Page<Product>(5);

	public Page<Product> getPage() {
		return page;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Autowired
	private ProductBussiness productBussiness;

	public void setProductBussiness(ProductBussiness productBussiness) {
		this.productBussiness = productBussiness;
	}

	@Autowired
	private SupplyBussiness supplyBussiness;

	public SupplyBussiness getSupplyBussiness() {
		return supplyBussiness;
	}

	public void setSupplyBussiness(SupplyBussiness supplyBussiness) {
		this.supplyBussiness = supplyBussiness;
	}

	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = HibernateWebUtils
				.buildPropertyFilters(Struts2Utils.getRequest());
		if (filters != null) {
			page = productBussiness.searchProduct(page, filters);
		} else {
			page = productBussiness.getAll(page);
		}
		return SUCCESS;
	}

	public Product getModel() {
		return product;
	}

	@Override
	public String input() throws Exception {
		List<Supply> supplys = supplyBussiness.findSupply();
		ActionContext.getContext().put("supplys", supplys);
		return INPUT;
	}

	@Override
	public String save() throws Exception {
		productBussiness.save(product);
		addActionMessage("保存成功");
		return RELOAD;
	}

	@Override
	public String delete() throws Exception {
		try {
			productBussiness.delete(id);
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
			product = productBussiness.get(id);
		} else {
			product = new Product();
		}
	}

}
