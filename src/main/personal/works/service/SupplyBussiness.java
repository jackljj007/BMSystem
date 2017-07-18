package works.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import works.dao.SupplyDao;
import works.entity.entities.Supply;

@Service
@Transactional
public class SupplyBussiness {
	@Autowired
	private SupplyDao supplydao;

	@Transactional(readOnly = true)
	public Page<Supply> getAll(Page<Supply> page) {
		return supplydao.getAll(page);
	}

	public void save(Supply supply) {
		supplydao.save(supply);
	}

	public void delete(Long id) {
		supplydao.delete(id);
	}

	@Transactional(readOnly = true)
	public Supply get(Long id) {
		return supplydao.get(id);
	}

	@Transactional(readOnly = true)
	public List<Supply> findSupply() {
		return supplydao.getAll();
	}

	@Transactional(readOnly = true)
	public Page<Supply> searchSupply(final Page<Supply> page,
			final List<PropertyFilter> filters) {
		return supplydao.findPage(page, filters);
	}

}
