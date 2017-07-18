package works.dao;

import works.entity.entities.Shelves;

import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

@Repository
public class ShelvesDao extends HibernateDao<Shelves, Long> {

}
