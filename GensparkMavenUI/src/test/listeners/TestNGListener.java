package test.listeners;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.*;
import test.utils.LoadProperties;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static test.utils.DriverUtil.getDriver;

public class TestNGListener implements ITestListener {

    private ITestClass testClass;
    public static String testCaseName;

    private void checkAndInitTestClass(ITestResult result) {
        if (result.getMethod().getTestClass() != testClass) {
            testClass = result.getMethod().getTestClass();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        checkAndInitTestClass(result);
        testCaseName = result.getMethod().getMethodName();
    }


    @Override
    public void onTestSuccess(ITestResult result) {

    }

    @Override
    public void onTestFailure(ITestResult result) {
//        System.out.println(result);
//        File screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
//        //Copy the file to a location and use try catch block to handle exception
//        try {
//            String screenshotAbsolutePath = System.getProperty("user.dir") + "/failedScreenShots/" + result.getMethod().getMethodName() + ".png";
//            FileUtils.copyFile(screenshot, new File(screenshotAbsolutePath));
//            String path = "<img src=\"" + screenshotAbsolutePath + "\" />";
//            Reporter.log(screenshotAbsolutePath);
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        String methodName = result.getName();
        File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/failedScreenShots/";
            File destFile = new File((String) reportDirectory + "/failure_screenshots/" + methodName + "_" + formater.format(calendar.getTime()) + ".png");
            FileUtils.copyFile(scrFile, destFile);
            Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "' height='100' width='100'/> </a>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {
        LoadProperties.init();
    }

    @Override
    public void onFinish(ITestContext context) {

    }
}
