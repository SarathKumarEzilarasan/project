package test.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class DriverUtil {

    static WebDriver driver;

    private DriverUtil() {

    }

    public static WebDriver getDriver() {
        String browser = LoadProperties.getProperty("browser");
//        String browser = System.getProperty("browser");
        try {
            if (driver == null) {
                if (browser.contains("chrome")) {
                    WebDriverManager.chromedriver().browserVersion("95").setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
//                    chromeOptions.setHeadless(true);
                    chromeOptions.addArguments("disable-infobars");
                    Map<String, Object> prefs = new HashMap<String, Object>();
                    prefs.put("download.default_directory", System.getProperty("user.dir") + File.separator + "externalFiles" + File.separator + "downloadFiles");
                    chromeOptions.setExperimentalOption("prefs", prefs);
                    driver = new ChromeDriver(chromeOptions);
                }
                if (browser.contains("firefox")) {
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return driver;
    }

    public static void main(String[] args) {
        getDriver();
    }
}
