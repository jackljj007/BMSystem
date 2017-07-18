package works.web;

import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateWebUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import works.entity.entities.Supply;
import works.service.ServiceException;
import works.service.SupplyBussiness;

@SuppressWarnings("serial")
@Results({ @Result(name = CrudActionSupport.RELOAD, location = "supply.action", type = "redirect") })
public class SupplyAction extends CrudActionSupport<Supply> {
	private Long id;
	private Supply supply;
	private Page<Supply> page = new Page<Supply>(5);

	public Page<Supply> getPage() {
		return page;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Autowired
	private SupplyBussiness supplyBussiness;

	public void setSupplyBussiness(SupplyBussiness supplyBussiness) {
		this.supplyBussiness = supplyBussiness;
	}

	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = HibernateWebUtils
				.buildPropertyFilters(Struts2Utils.getRequest());
		if (filters != null) {
			page = supplyBussiness.searchSupply(page, filters);
		} else {
			page = supplyBussiness.getAll(page);
		}
		return SUCCESS;
	}

	public Supply getModel() {
		return supply;
	}

	@Override
	public String input() throws Exception {
		return INPUT;
	}

	@Override
	public String save() throws Exception {
		supplyBussiness.save(supply);
		addActionMessage("保存成功");
		return RELOAD;
	}

	@Override
	public String delete() throws Exception {
		try {
			supplyBussiness.delete(id);
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
			supply = supplyBussiness.get(id);
		} else {
			supply = new Supply();
		}
	}

}
