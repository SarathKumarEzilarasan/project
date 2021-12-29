package test.java;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class test {
    WebDriver driver;

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterTest
    public void close() {
        driver.close();
    }

    @Test
    public void table2() {
        driver.get("http://www.leafground.com/pages/sorttable.html");
        WebElement table = driver.findElement(By.id("table_id"));
        List<WebElement> rows = driver.findElements(By.tagName("tr"));
        List<WebElement> colsheader = table.findElements(By.tagName("th"));
        System.out.println("Rows:" + rows.size());
        System.out.println("colsheader:" + colsheader.size());
        for (int i = 1; i < rows.size(); i++) {
            WebElement Row = rows.get(i);
            List<WebElement> cols = Row.findElements(By.tagName("td"));
            for (int j = 0; j < cols.size(); j++) {
                System.out.println(cols.get(j).getText() + " ");
            }
            System.out.println();
        }
        List<WebElement> name1 = driver.findElements(By.cssSelector("td:nth-child(2)"));
        for (int i = 0; i < name1.size(); i++) {
            System.out.println(name1.get(i).getText());
        }
        List<String> name = new ArrayList<>();
        name.add("Sam David");
        name.add("Gopi");
        name.add("Naveen");
        name.add("Balaji");
        name.add("Koushik");
        name.add("Narashiman");
        System.out.println(name);
        Collections.sort(name, Collections.reverseOrder());
    }
}