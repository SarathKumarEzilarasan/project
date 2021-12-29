package test.java;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import test.pages.ToolTipPageFactory;

import java.io.IOException;

public class TestLeaf1 extends BaseTest {
        Logger Log = Logger.getLogger(TestLeaf1.class);
    //regression ---> 1000
    // smoke testing --> login tests[ critical functionality] --> 10
    // sanity testing -->


    //key word driven framework
    //data  driven framework
    //hybrid framework --> Data + POM
    @Test
    public void ToolTip() throws IOException {
        driver.get("http://www.leafground.com/pages/tooltip.html");
        Log.info("New driver instantiated");
        ToolTipPageFactory toolTipPageFactory = new ToolTipPageFactory(driver);
        Log.info("page factory configured");
        toolTipPageFactory.login("aaaa", "sssss")
                .searchProduct("sssss", "");
    }


}
