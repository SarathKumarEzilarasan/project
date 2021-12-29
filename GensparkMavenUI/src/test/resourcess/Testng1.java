package test.resourcess;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import javax.crypto.IllegalBlockSizeException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class Testng1 {

    public static void main(String[] args) throws InterruptedException, IOException, InvalidKeySpecException, NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, Exception {
//        File file =    new File("/Users/cb-sarathkumar/Downloads/demo.xlsx");
//        FileInputStream inputStream = new FileInputStream(file);
//        XSSFWorkbook wb=new XSSFWorkbook(inputStream);
//        XSSFSheet ws = wb.getSheet("Sheet1");
//        int rows = ws.getRow(0).getLastCellNum();
//        XSSFRow row = ws.getRow(0);
//        int cols = row.getLastCellNum();
//        System.out.println(rows);
//        System.out.println(cols);
//        for (int i = 0; i < rows; i++) {
//            row =  ws.getRow(i);
//            for (int j = 0; j < row.getLastCellNum(); j++) {
//                System.out.println(row.getCell(j));
//            }
//        }
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.leafground.com/pages/mouseOver.html");
        WebElement element = driver.findElement(By.xpath("//a[@class=\"btnMouse\"]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        WebElement element1 = driver.findElement(By.xpath("(//a[@class=\"listener\"])[1]"));
        actions.moveToElement(element1).click().perform();
        String text = driver.switchTo().alert().getText();
        Assert.assertEquals(text, "You have clicked on Selenium");
        driver.switchTo().defaultContent();
        WebElement element2 = driver.findElement(By.xpath("(//a[@class=\"listener\"])[2]"));
        actions.moveToElement(element2).click().perform();
        String text1 = driver.switchTo().alert().getText();
        Assert.assertEquals(text1, "You have clicked on RPA");
    }

}
