package works.dao;

import works.entity.entities.Store;

import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

@Repository
public class StoreDao extends HibernateDao<Store, Long> {

}
