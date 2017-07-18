package works.integration.dao.security;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import works.dao.security.AuthorityDao;
import works.data.SecurityData;
import works.entity.security.Authority;
import org.springside.modules.test.spring.SpringTxTestCase;

/**
 * UserDao的集成测试用例,测试ORM映射及特殊的DAO操作.
 * 
 * @author calvin
 */
public class AuthorityDaoTest extends SpringTxTestCase {
	@Autowired
	private AuthorityDao entityDao;

	@Test
	public void crudEntity() {
		//new entity and save it. 
		Authority entity = SecurityData.getRandomAuthority();
		entityDao.save(entity);
		flush();

		//find entity.
		Authority entityFromDB = entityDao.findUniqueBy("id", entity.getId());
		assertReflectionEquals(entity, entityFromDB);

		//delete entity.
		entityDao.delete(entity.getId());
		flush();
		entity = entityDao.findUniqueBy("id", entity.getId());
		assertNull(entity);
	}
}
