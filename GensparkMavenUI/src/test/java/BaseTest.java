package test.java;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import test.utils.DriverUtil;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    Logger Log = Logger.getLogger(BaseTest.class);
    static WebDriver driver;

    @BeforeTest
    public void setUp() {
        PropertyConfigurator.configure("/Users/cb-sarathkumar/work/cb-ui-middleware/GensparkMavenUI/src/test/resources/log4j.properties");
        driver = DriverUtil.getDriver();
        driver.manage().window().maximize();
        Log.info("New driver instantiated");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }


    @DataProvider(name = "dataProvider")
    public Object[][] data() {
        return new Object[][]{
                {"valid username", "valid password", "login successfull"},
                {"invalid username", "invalid password", "login failed"},
                {"", "", "login failed"},
                {"sdghjsdhgjsdjsdjhsdjsd", "sdghjsdhgjsdjsdjhsdjsd", "login failed"},
        };
    }
}
