package test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ToolTipPageFactory {

    private WebDriver driver;

    public ToolTipPageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "age")
    private WebElement age; //  driver.findElement(By.xpath("//div[@class=\"ui-tooltip-content\"]"));

    @FindBy(xpath = "//div[@class=\"ui-tooltip-content\"]")
    private WebElement toolTip;

    @FindBy(xpath = "//div[@class=\"ui-tooltip-content\"]")
    private WebElement loginButton;

    public WebElement getAge(){
        return age;
    }

    public void setAge(String str){
        age.sendKeys(str);
    }

    // login

    //fluent design pattern
    public ToolTipPageFactory login(String userName, String password){
        age.sendKeys(userName);
        toolTip.sendKeys(password);
        loginButton.click();
        return this;
    }

    public ToolTipPageFactory searchProduct(String userName, String password){
        age.sendKeys(userName);
        toolTip.sendKeys(password);
        loginButton.click();
        return this;
    }

}
