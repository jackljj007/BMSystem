package works.web;

import java.util.Calendar;
import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;

import works.entity.entities.Customer;
import works.entity.entities.Orders;
import works.entity.entities.Product;
import works.entity.entities.Sold;
import works.entity.entities.Store;
import works.service.CustomerBussiness;
import works.service.OrdersBussiness;
import works.service.ProductBussiness;
import works.service.ServiceException;
import works.service.SoldBussiness;
import works.service.StoreBussiness;

import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@Results({ @Result(name = CrudActionSupport.RELOAD, location = "orders.action", type = "redirect") })
public class OrdersAction extends CrudActionSupport<Orders> {
	private Long id;
	private Orders orders;
	private Page<Orders> page = new Page<Orders>(5);

	public Page<Orders> getPage() {
		return page;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Autowired
	private OrdersBussiness ordersBussiness;

	public void setOrdersBussiness(OrdersBussiness ordersBussiness) {
		this.ordersBussiness = ordersBussiness;
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
	private SoldBussiness soldBussiness;

	public SoldBussiness getSoldBussiness() {
		return soldBussiness;
	}

	public void setSoldBussiness(SoldBussiness soldBussiness) {
		this.soldBussiness = soldBussiness;
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
		page = ordersBussiness.getAll(page);
		return SUCCESS;
	}

	public Orders getModel() {
		return orders;
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
		Store storeProduct = storeBussiness.findProductByName(orders
				.getProductName());
		if (storeProduct != null) {
			Store store = storeBussiness.get(storeProduct.getId());
			if (store.getNumber() > orders.getNumber()) {
				ordersBussiness.save(orders);
				addActionMessage("保存成功");
			} else {
				addActionMessage("该商品存量不足");
			}
		} else {
			addActionMessage("仓库无此商品");
		}
		return RELOAD;
	}

	@Override
	public String delete() throws Exception {
		try {
			ordersBussiness.delete(id);
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
			orders = ordersBussiness.get(id);
		} else {
			orders = new Orders();
		}
	}

	public void deliver() throws Exception {
		orders = ordersBussiness.getShelvesById(id);
		Sold sold = new Sold();
		Store storeProduct = storeBussiness.findProductByName(orders
				.getProductName());
		Store store = storeBussiness.get(storeProduct.getId());
		if (store.getNumber() > orders.getNumber()) {
			store.setNumber(storeProduct.getNumber() - orders.getNumber());
			sold.setProductName(orders.getProductName());
			sold.setCustomerName(orders.getCustomerName());
			sold.setNumber(orders.getNumber());
			sold.setSoldDate(Calendar.getInstance().getTime());
			storeBussiness.save(store);
			soldBussiness.save(sold);
			orders.setState(1);
			ordersBussiness.save(orders);
		} else {
			addActionMessage("该商品存量不足");
		}
	}

}
