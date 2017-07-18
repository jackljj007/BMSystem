package works.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import works.dao.SoldDao;
import works.entity.entities.Sold;

@Service
@Transactional
public class SoldBussiness {
	@Autowired
	private SoldDao solddao;

	@Transactional(readOnly = true)
	public Page<Sold> getAll(Page<Sold> page) {
		return solddao.getAll(page);
	}

	public void save(Sold sold) {
		solddao.save(sold);
	}

	public void delete(Long id) {
		solddao.delete(id);
	}

	@Transactional(readOnly = true)
	public Sold get(Long id) {
		return solddao.get(id);
	}

	@Transactional(readOnly = true)
	public Page<Sold> searchSold(final Page<Sold> page,
			final List<PropertyFilter> filters) {
		return solddao.findPage(page, filters);
	}

}
