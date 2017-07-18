package works.dao;

import works.entity.entities.Product;

import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

@Repository
public class ProductDao extends HibernateDao<Product, Long> {

}
