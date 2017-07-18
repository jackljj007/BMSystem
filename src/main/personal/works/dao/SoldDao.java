package works.dao;

import works.entity.entities.Sold;

import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

@Repository
public class SoldDao extends HibernateDao<Sold, Long> {

}
