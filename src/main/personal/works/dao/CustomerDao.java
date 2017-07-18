package works.dao;

import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

import works.entity.entities.Customer;

@Repository
public class CustomerDao extends HibernateDao<Customer, Long> {

}
