package works.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;

import works.dao.InletDao;
import works.entity.entities.Inlet;

@Service
@Transactional
public class InletBussiness {
	@Autowired
	private InletDao inletdao;

	@Transactional(readOnly = true)
	public Page<Inlet> getAll(Page<Inlet> page) {
		return inletdao.getAll(page);
	}

	public void save(Inlet inlet) {
		inletdao.save(inlet);
	}

	public void delete(Long id) {
		inletdao.delete(id);
	}

	@Transactional(readOnly = true)
	public Inlet get(Long id) {
		return inletdao.get(id);
	}

	public Inlet getInletById(Long pid) {
		String hql = "from Inlet where id=?";
		List<Inlet> record = inletdao.find(hql, pid);
		Inlet inlet = null;
		if (record != null) {
			for (int i = 0; i < record.size(); i++) {
				inlet = record.get(0);
			}
		}
		return inlet;
	}

	public Inlet findProductByName(String productName) {
		return inletdao.findUniqueBy("productName", productName);
	}
}
