package works.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;

import works.dao.StoreDao;
import works.entity.entities.Store;

@Service
@Transactional
public class StoreBussiness {
	@Autowired
	private StoreDao storedao;

	@Transactional(readOnly = true)
	public Page<Store> getAll(Page<Store> page) {
		return storedao.getAll(page);
	}

	public void save(Store store) {
		storedao.save(store);
	}

	public void delete(Long id) {
		storedao.delete(id);
	}

	@Transactional(readOnly = true)
	public Store get(Long id) {
		return storedao.get(id);
	}

	public Store getStoreById(Long pid) {
		String hql = "from Store where id=?";
		List<Store> record = storedao.find(hql, pid);
		Store store = null;
		if (record != null) {
			for (int i = 0; i < record.size(); i++) {
				store = record.get(0);
			}
		}
		return store;
	}

	public Store findProductByName(String productName) {
		return storedao.findUniqueBy("productName", productName);
	}

}
