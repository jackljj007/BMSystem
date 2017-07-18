package works.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;

import works.dao.ShelvesDao;
import works.entity.entities.Shelves;

@Service
@Transactional
public class ShelvesBussiness {
	@Autowired
	private ShelvesDao shelvesdao;

	@Transactional(readOnly = true)
	public Page<Shelves> getAll(Page<Shelves> page) {
		return shelvesdao.getAll(page);
	}

	public void save(Shelves shelves) {
		shelvesdao.save(shelves);
	}

	public void delete(Long id) {
		shelvesdao.delete(id);
	}

	@Transactional(readOnly = true)
	public Shelves get(Long id) {
		return shelvesdao.get(id);
	}

	public Shelves getShelvesById(Long pid) {
		String hql = "from Shelves where id=?";
		List<Shelves> record = shelvesdao.find(hql, pid);
		Shelves shelves = null;
		if (record != null) {
			for (int i = 0; i < record.size(); i++) {
				shelves = record.get(0);
			}
		}
		return shelves;
	}

	public Shelves findProductByName(String productName) {
		return shelvesdao.findUniqueBy("productName", productName);
	}
}
