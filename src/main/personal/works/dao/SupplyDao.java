package works.dao;

import works.entity.entities.Supply;

import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

@Repository
public class SupplyDao extends HibernateDao<Supply, Long> {

}
