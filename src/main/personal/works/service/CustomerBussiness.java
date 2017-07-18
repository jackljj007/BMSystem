package works.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import works.dao.CustomerDao;
import works.entity.entities.Customer;

@Service
@Transactional
public class CustomerBussiness {
	
	@Autowired
	private CustomerDao customerdao;

	@Transactional(readOnly = true)
	public Page<Customer> getAll(Page<Customer> page) {
		return customerdao.getAll(page);
	}

	public void save(Customer customer) {
		customerdao.save(customer);
	}

	public void delete(Long id) {
		customerdao.delete(id);
	}

	@Transactional(readOnly = true)
	public Customer get(Long id) {
		return customerdao.get(id);
	}

	@Transactional(readOnly = true)
	public List<Customer> findCustomer() {
		return customerdao.getAll();
	}

	@Transactional(readOnly = true)
	public Page<Customer> searchCustomer(final Page<Customer> page,
			final List<PropertyFilter> filters) {
		return customerdao.findPage(page, filters);
	}

}
