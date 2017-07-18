package works.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import works.dao.ProductDao;
import works.entity.entities.Product;

@Service
@Transactional
public class ProductBussiness {
	@Autowired
	private ProductDao productdao;

	@Transactional(readOnly = true)
	public Page<Product> getAll(Page<Product> page) {
		return productdao.getAll(page);
	}

	public void save(Product product) {
		productdao.save(product);
	}

	public void delete(Long id) {
		productdao.delete(id);
	}

	@Transactional(readOnly = true)
	public Product get(Long id) {
		return productdao.get(id);
	}

	@Transactional(readOnly = true)
	public List<Product> findProduct() {
		return productdao.getAll();
	}

	@Transactional(readOnly = true)
	public Page<Product> searchProduct(final Page<Product> page,
			final List<PropertyFilter> filters) {
		return productdao.findPage(page, filters);
	}

}
