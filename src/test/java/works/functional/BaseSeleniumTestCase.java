package works.functional;

import org.junit.BeforeClass;
import org.springside.modules.test.selenium.SeleniumTestCase;

/**
 * 项目相关的SeleniumTestCase基类,定义服务器地址,浏览器类型及登陆函数. 
 * 
 * @author calvin
 */
public abstract class BaseSeleniumTestCase extends SeleniumTestCase {

	@BeforeClass
	public static void loginAsAdmin() {
		selenium.open("/BMSystem/login.action");
		selenium.type("j_username", "admin");
		selenium.type("j_password", "admin");
		selenium.click("//input[@value='登录']");
		waitPageLoad();
		assertTrue(selenium.isTextPresent("你好,admin."));
	}

	protected void clickMenu(String menuName) {
		selenium.open("/BMSystem");
		waitPageLoad();
		selenium.click("link=" + menuName);
		waitPageLoad();
	}
}
