package test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

// POM -> Page Object Model
public class ToolTipPage {
//    driver.findElement(By.id("age"));
//    driver.findElement(By.xpath("//div[@class=\"ui-tooltip-content\"]"));

    By ageId = By.id("ages");
    By toolTipXpath = By.xpath("//div[@class=\"ui-tooltip-content\"]");

    WebDriver driver;

    public ToolTipPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getAge() {
        return driver.findElement(ageId);
    }

    public WebElement getToolTip() {
        return driver.findElement(toolTipXpath);
    }



}

