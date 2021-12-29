package test.java;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;
import test.pages.ToolTipPage;

import java.io.File;
import java.io.FileNotFoundException;

// regression testing

public class TestL {//extends BaseTest {


    @Test(timeOut = 1000)
    public void loginTest1() throws FileNotFoundException, InterruptedException {
        System.out.println("test 1");
        Thread.sleep(1000);
//        throw new FileNotFoundException();
    }


}
