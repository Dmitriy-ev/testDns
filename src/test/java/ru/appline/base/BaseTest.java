package ru.appline.base;

import org.junit.*;
import org.openqa.selenium.support.ui.*;
import ru.appline.managers.*;
import static ru.appline.managers.DriverManager.*;
import static ru.appline.utils.PropConst.*;

public class BaseTest {
	
	protected PageManager app = PageManager.getPageManager();

	@BeforeClass
	public static void before() {
		InitManager.initFramework();
		
 	}
	@Before
	public void beforeEach() {
		getDriver().get(TestPropManager.getTestPropManager().getProperty(APP_URL));
	}

	@AfterClass
	public static void after(){
		InitManager.quitFramework();
	}

}
