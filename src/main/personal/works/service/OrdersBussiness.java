package works.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;

import works.dao.OrdersDao;
import works.entity.entities.Orders;

@Service
@Transactional
public class OrdersBussiness {
	@Autowired
	private OrdersDao ordersdao;

	@Transactional(readOnly = true)
	public Page<Orders> getAll(Page<Orders> page) {
		return ordersdao.getAll(page);
	}

	public void save(Orders orders) {
		ordersdao.save(orders);
	}

	public void delete(Long id) {
		ordersdao.delete(id);
	}

	@Transactional(readOnly = true)
	public Orders get(Long id) {
		return ordersdao.get(id);
	}

	public Orders getShelvesById(Long id) {
		String hql = "from Orders where id=?";
		List<Orders> record = ordersdao.find(hql, id);
		Orders orders = null;
		if (record != null) {
			for (int i = 0; i < record.size(); i++) {
				orders = record.get(0);
			}
		}
		return orders;
	}
}
