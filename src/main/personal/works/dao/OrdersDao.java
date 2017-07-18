package works.dao;

import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

import works.entity.entities.Orders;

@Repository
public class OrdersDao extends HibernateDao<Orders, Long> {

}
