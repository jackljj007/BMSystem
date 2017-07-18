package works.dao;

import works.entity.entities.Inlet;

import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

@Repository
public class InletDao extends HibernateDao<Inlet, Long> {

}
