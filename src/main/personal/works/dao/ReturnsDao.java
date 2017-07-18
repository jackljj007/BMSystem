package works.dao;

import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

import works.entity.entities.Returns;

@Repository
public class ReturnsDao extends HibernateDao<Returns, Long> {

}
