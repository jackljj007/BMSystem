package works.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;

import works.dao.ReturnsDao;
import works.entity.entities.Returns;

@Service
@Transactional
public class ReturnsBussiness {
	@Autowired
	private ReturnsDao returnsdao;

	@Transactional(readOnly = true)
	public Page<Returns> getAll(Page<Returns> page) {
		return returnsdao.getAll(page);
	}

	public void save(Returns returns) {
		returnsdao.save(returns);
	}

	public void delete(Long id) {
		returnsdao.delete(id);
	}

	@Transactional(readOnly = true)
	public Returns get(Long id) {
		return returnsdao.get(id);
	}

	public Returns getReturnsById(Long id) {
		String hql = "from Returns where id=?";
		List<Returns> record = returnsdao.find(hql, id);
		Returns returns = null;
		if (record != null) {
			for (int i = 0; i < record.size(); i++) {
				returns = record.get(0);
			}
		}
		return returns;
	}
}
