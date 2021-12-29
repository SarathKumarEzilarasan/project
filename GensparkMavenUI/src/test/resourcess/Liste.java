package test.resourcess;

import org.testng.*;

public class Liste implements ITestListener {

    private ITestClass testClass;

    private void checkAndInitTestClass(ITestResult result) {
        if (result.getMethod().getTestClass() != testClass) {
            testClass = result.getMethod().getTestClass();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        checkAndInitTestClass(result);
        System.out.println(testClass.getRealClass().getSimpleName());
        System.out.println(testClass.getRealClass().getName());
        System.out.println(result.getMethod().getMethodName());
    }

}