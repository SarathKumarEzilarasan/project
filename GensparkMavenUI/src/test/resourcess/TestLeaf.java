package test.resourcess;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class TestLeaf {
    WebDriver driver;

    @BeforeTest
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        // Explicit Wait can be here
        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testEditBox(ITestContext context){
        context.getName();
        // Edit Box
        // Home test.resources.Page
        driver.get("http://www.leafground.com/home.html");
        // Going to Edit test.resources.Page
        driver.findElement(By.xpath("//a[@href='pages/Edit.html'][1]")).click();

        // Enter your email address
        WebElement textBox1 = driver.findElement(By.id("email")); //input[@id='email']
        textBox1.sendKeys("test@gmail.com");

        // Append a text and press keyboard tab
        WebElement textBox2 = driver.findElement(By.xpath("//input[@value='Append ']"));
        textBox2.sendKeys("test user");
        textBox2.sendKeys(Keys.TAB);

        // Get default text entered
        WebElement textBox3 = driver.findElement(By.xpath("//input[@name='username' and @value='TestLeaf']"));
        String defaultValue = textBox3.getAttribute("value");
        Assert.assertEquals(defaultValue, "TestLeaf");

        // Clear the text
        WebElement textBox4 = driver.findElement(By.xpath("//input[@name='username' and @value='Clear me!!']"));
        textBox4.clear();

        // Confirm that edit field is disabled
        WebElement textBox5 = driver.findElement(By.xpath("//input[@style=\"width:350px;background-color:LightGrey;\"]"));
        String disabled = textBox5.getAttribute("disabled");
        Assert.assertEquals(disabled, "true", "the field is enabled");
    }

    @Test
    public void testButton(){
        driver.get("http://www.leafground.com/pages/Button.html");
        driver.findElement(By.id("home")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "http://www.leafground.com/home.html");
        // Go back to the Button Test page
        driver.get("http://www.leafground.com/pages/Button.html");

        // Getting X and Y mouse cordinates
        int x = driver.findElement(By.id("position")).getLocation().getX();
        int y = driver.findElement(By.id("position")).getLocation().getY();
        System.out.println(x + " " + y);

        // Making sure button is "lightgreen"
        String style = driver.findElement(By.id("color")).getAttribute("style");
        Assert.assertTrue(style.contains("lightgreen"));

        // Finding height and width of the blue button
        int width = driver.findElement(By.id("size")).getSize().getWidth();
        int height = driver.findElement(By.id("size")).getSize().getHeight();
        System.out.println(width + "  " + height);
    }

    @Test
    public void testHyperLink() throws IOException {
        driver.get("http://www.leafground.com/pages/Link.html");

        // Go to Home test.resources.Page
        WebElement test1 = driver.findElement(By.xpath("//a[@href='../home.html'][1]"));
        test1.click();
        Assert.assertEquals(driver.getCurrentUrl(), "http://www.leafground.com/home.html");
        driver.get("http://www.leafground.com/pages/Link.html");

        // Find where am supposed to go without clicking me?
        String href = driver.findElement(By.xpath("//a[text()='Find where am supposed to go without clicking me?']")).getAttribute("href");
        System.out.println("URL is: " +href);

        // Go to Home test.resources.Page (Interact with same link name)
        WebElement test2 = driver.findElement(By.xpath("//a[text()='Go to Home test.resources.Page' and @style='color: #CC0000']"));
        test2.click();
        // Verify current url is same
        Assert.assertEquals(driver.getCurrentUrl(), "http://www.leafground.com/home.html");
        driver.get("http://www.leafground.com/pages/Link.html");

        // Verify am I broken?
        driver.get("http://www.leafground.com/pages/Link.html");
        WebElement brokenLink = driver.findElement(By.xpath("//a[@href='error.html']"));
        String url = brokenLink.getAttribute("href");

        // check url if status code is 200 or 404
        URL u = new URL(url); // throws "MalformedURLException" is same as try/catch/finally
        // if having HttpURLConnection will throw "IOException"

        // open url site into exception
        HttpURLConnection huc = (HttpURLConnection) u.openConnection();
        // calls the url
        huc.setRequestMethod("GET"); // get the url
        huc.connect();               // connect once you get the url

        // Verify if Link is broken or not - code status "200" or "400 or more"
        int respCode = huc.getResponseCode();
        if(respCode >= 400){
            System.out.println("Broken Link");
        } else {
            System.out.println("Valid Link");
        }
    }

    @Test
    public void testImage() throws IOException {
        driver.get("http://www.leafground.com/pages/Image.html");
        // Verify same link
        Assert.assertEquals(driver.getCurrentUrl(), "http://www.leafground.com/pages/Image.html");

        // Click on this image to go home page
        driver.findElement(By.xpath("//img[@src='../images/home.png']")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "http://www.leafground.com/home.html");

        // Am I Broken Image?
        driver.get("http://www.leafground.com/pages/Image.html");
        WebElement brokenLink1 = driver.findElement(By.xpath("//img[@src='../images/abcd.jpg']"));
        String url1 = brokenLink1.getAttribute("src");
        // check url if status code is 200 or 404
        URL ud = new URL(url1);
        HttpURLConnection huc1 = (HttpURLConnection) ud.openConnection();
        huc1.setRequestMethod("GET"); // get the url
        huc1.connect();               // connect once you get the url
        // Verify if Link is broken or not - code status "200" or "400 or more"
        int respCode1 = huc1.getResponseCode();
        if(respCode1 >= 400){
            System.out.println("Broken Image Link");
        } else {
            System.out.println("Valid Image Link");
        }

        // Test image using keyboard or mouse to click
        driver.get("http://www.leafground.com/pages/Image.html");
        driver.findElement(By.xpath("//img[@src=\"../images/keyboard.png\"]")).click();
        // Verify Click to Home test.resources.Page
        Assert.assertEquals(driver.getCurrentUrl(), "http://www.leafground.com/home.html");

        // Notes on Keyboard
        // some action features
        // actions.doubleclick()
        // pressing ctrl+shift+del example code below
        // actions.keyDown(keys.CONTROL).keyDown(Keys.SHIFT).keyDown(keys.DELETE).perform()
    }

    @Test
    public void testDropDown(){
        // direct to Dropdown test.resources.Page
        driver.get("http://www.leafground.com/pages/Dropdown.html");
        Assert.assertEquals(driver.getCurrentUrl(), "http://www.leafground.com/pages/Dropdown.html");

        // Select training program using Index
        WebElement element = driver.findElement(By.id("dropdown1"));
        Select select = new Select(element);
        select.selectByIndex(1);
        select.selectByIndex(2);
        select.selectByIndex(3);
        select.selectByIndex(4);

        // Select training program using Text
        WebElement element2 = driver.findElement(By.xpath("//select[@name='dropdown2']"));
        Select select2 = new Select(element2);
        select2.selectByVisibleText("Selenium");
        select2.selectByVisibleText("Appium");
        select2.selectByVisibleText("UFT/QTP");
        select2.selectByVisibleText("Loadrunner");

        // Select training program using Value
        WebElement element3 = driver.findElement(By.id("dropdown3"));
        Select select3 = new Select(element3);
        select3.selectByValue("1");
        select3.selectByValue("2");
        select3.selectByValue("3");
        select3.selectByValue("4");

        // Get the number of dropdown options
        WebElement element4 = driver.findElement(By.className("dropdown"));
        Select select4 = new Select(element4);
        List<WebElement> options4 = select4.getOptions();
        // output the total number of options
        System.out.println("The number of dropdown options: " + options4.size());

        // You can also use sendKeys to select
        WebElement select5 = driver.findElement(By.cssSelector("div.example:nth-child(13) > select:nth-child(1)"));
        select5.sendKeys("Selenium");
        select5.sendKeys("Appium");
        select5.sendKeys("UFT/QTP");
        select5.sendKeys("Loadrunner");

        // Select your programs
        WebElement element6 = driver.findElement(By.xpath("//select[@multiple='']"));
        Select select6 = new Select(element6);

        // select all options
        if (select6.isMultiple()){
            select6.getAllSelectedOptions();
        }
    }

    @Test
    public void testRadioButton(){
        driver.get("http://www.leafground.com/pages/radio.html");
        Assert.assertEquals(driver.getCurrentUrl(), "http://www.leafground.com/pages/radio.html");

        // Are you enjoying the classes?
        driver.findElement(By.xpath("//input[@id='yes']")).click();
        WebElement testLink2 = driver.findElement(By.xpath("//label[@for='yes']"));
        // Verify clicked
        Assert.assertEquals(testLink2.getText(), "Yes");

        // Find default selected radio button
        driver.findElement(By.xpath("//label[@for='Checked']")).click();
        WebElement defaultValue2 = driver.findElement(By.xpath("//label[@for='Checked']"));
        Assert.assertEquals(defaultValue2.getText(), "Checked");

        System.out.println("Default Select Radio Button is: " +defaultValue2.getText());

        // Select your age group (Only if choice wasn't selected)
        HashMap<Integer, String> map = new HashMap<>();   // mapping the radio buttons age group
        map.put(0, " 1 - 20 years ");    // key=value, value=text
        map.put(1, " 21 - 40 years ");
        map.put(2, "Above 40 years");

        // direct to "Select your age group (Only if choice wasn't selected)"
        List<WebElement> ageGroups = driver.findElements(By.xpath("//input[@name=\"age\"]"));
        ageGroups.get(1).click();  // Clicked key "1" ----> 21 - 40 years
    }

    @Test
    public void testCheckBox(){
        driver.get("http://leafground.com/pages/checkbox.html"); // checkbox page url
        List<WebElement> languages = driver.findElements(By.xpath("//div[@class='example'][1]//input[@type='checkbox']"));
        languages.get(0).click();
        languages.get(3).click();
        languages.get(4).click();

        // Confirm Selenium is checked
        boolean checked = driver.findElement(By.xpath("//div[@class='example'][2]//input[@type='checkbox']")).isSelected();
        Assert.assertTrue(checked);

        // DeSelect only checked
        List<WebElement> deselectCheckBoxes = driver.findElements(By.xpath("//div[@class='example'][3]//input[@type='checkbox']"));
        for (WebElement elementDeselect : deselectCheckBoxes){
            if(elementDeselect.isSelected()){
                elementDeselect.click();
            }
        }

        // Select all below checkboxes
        List<WebElement> selectCheckBoxes = driver.findElements(By.xpath("//div[@class='example'][4]//input[@type='checkbox']"));
        for(WebElement checks : selectCheckBoxes){
            checks.click();
        }
    }

    @Test
    public void testTable(){
        driver.get("http://www.leafground.com/pages/table.html");
        //------------------------------
        WebElement table = driver.findElement(By.id("table_id"));
        List<WebElement> columnsHeaders = table.findElements(By.xpath("//th"));
        System.out.println("No of columns: " +columnsHeaders);
        //------------------------------
        List<WebElement> rows = table.findElements(By.xpath("//tr"));
        int total = rows.size() - 1;
        System.out.println("No of rows: " + total);
        //------------------------------
        // total = rows.size() - 1  -----------> the total rows
        for(int i = 0; i < total; i++){
            List<WebElement> columns = rows.get(i).findElements(By.xpath("td"));
            for(int j = 0; j < columns.size(); j++){
                String text = columns.get(j).getText();
                //System.out.print(text + "  ");           // print all text
                if(text.equals("Learn to interact with Elements")){
                    System.out.println(columns.get(j + 1).getText());   // j + 1 is the column "Learn to interact with Elements" plus one more is "Progress"
                    break;
                }
            }
            System.out.println();   // print text within the second for-loop
        }

        // Check the vital task for the least completed progress.
        List<WebElement> Progress = driver.findElements(By.xpath("//td[2]"));

        // Loop through "//td[2]" as i
        for(int i = 0; i < Progress.size(); i++){
            // Convert to text ---> //td[i] to Text
            String Text = Progress.get(i).getText();
            if(Text.equals("30%")){
                driver.findElement(By.xpath("//tr[4]//td[3]//input")).click();
            }
        }
    }

    @Test
    public void testAlert(){
        driver.get("http://www.leafground.com/pages/Alert.html");

        // Click the button to display a alert box.
        driver.findElement(By.xpath("//button[text()='Alert Box']")).click();
        driver.switchTo().alert().accept();

        // Click the button to display a "Confirm box"
        driver.findElement(By.xpath("//button[text()='Alert Box']")).click();
        driver.switchTo().alert().dismiss();
        String actual = driver.findElement(By.id("result")).getText();

        // Click the button to display a prompt box.
        driver.findElement(By.xpath("//button[@onclick='confirmPrompt()']")).click();
        driver.switchTo().alert().sendKeys("Typing Testing");
        driver.switchTo().alert().accept();
        WebElement promptBox = driver.findElement(By.xpath("//p[@id='result1']"));
        Assert.assertEquals(promptBox.getText(), "You should not have enjoyed learning at Typing Testing as compared to TestLeaf! Right?");

        // Click the button to learn line-breaks in an alert.
        driver.findElement(By.xpath("//button[@onclick='lineBreaks()']")).click();
        driver.switchTo().alert().getText();
        // Verify Text
        Assert.assertEquals(driver.switchTo().alert().getText(), "Hello\n" +
                "How are you doing today?");
        // Print to Console
        System.out.println("---------------Test Alerts-------------------");
        System.out.println(driver.switchTo().alert().getText());
        System.out.println("");
        driver.switchTo().alert().accept();
    }

    @Test
    public void testFrame(){
        driver.get("http://www.leafground.com/pages/frame.html");

        // Find total number of frames.
        List<WebElement> iframes1 = driver.findElements(By.xpath("//iframe"));
        List<WebElement> iframes2 = driver.findElements(By.xpath("//iframe[@src='page.html']"));
        List<WebElement> iframes3 = driver.findElements(By.xpath("//iframe[@src='countframes.html']"));
        int totalFrame = iframes1.size() + iframes2.size() + iframes3.size();    // add all iframe in page

        // Verify total iframes is 5 in the page
        Assert.assertEquals(totalFrame, 5);
        System.out.println("---------------Test Iframe-------------------");
        System.out.println("Total iframes: " +totalFrame);
    }

    @Test
    public void testWindow() throws InterruptedException {
        driver.get("http://www.leafground.com/pages/Window.html");

        // Click button to open home page in New Window
        WebElement Button1 = driver.findElement(By.id("home"));
        Button1.click();
        Set<String> windows = driver.getWindowHandles();
        ArrayList<String> listStr = new ArrayList<>(windows);
        driver.switchTo().window(listStr.get(1));
        driver.close();
        driver.switchTo().window(listStr.get(0));

        // Find the number of opened windows
        WebElement Button2 = driver.findElement(By.xpath("(//button[contains(@onclick, 'openWindows')])[1]"));
        Button2.click();
        windows= driver.getWindowHandles();
        Assert.assertEquals(windows.size(), 3);
        listStr = new ArrayList<>(windows);
        driver.switchTo().window(listStr.get(2));
        driver.close();
        driver.switchTo().window(listStr.get(1));
        driver.close();
        driver.switchTo().window(listStr.get(0));

        // Close all except this window
        WebElement Button3 = driver.findElement(By.xpath("(//button[contains(@onclick, 'openWindows')])[2]"));
        Button3.click();
        windows = driver.getWindowHandles();
        listStr = new ArrayList<>(windows);
        driver.switchTo().window(listStr.get(2));
        driver.close();
        driver.switchTo().window(listStr.get(1));
        driver.close();
        driver.switchTo().window(listStr.get(0));
        Assert.assertTrue(driver.getCurrentUrl().contains("Window.html"));

        // Wait for 2 new Windows to open
        WebElement Button4 = driver.findElement(By.xpath("//button[contains(@onclick, 'openWindowsWithWait')]"));
        Button4.click();
        Thread.sleep(5000);
        windows = driver.getWindowHandles();
        Assert.assertEquals(windows.size(), 3);
        listStr = new ArrayList<>(windows);
        driver.switchTo().window(listStr.get(2));
        driver.close();
        driver.switchTo().window(listStr.get(1));
        driver.close();
        driver.switchTo().window(listStr.get(0));
    }

    @Test
    public void testCalendar(){
        driver.get("http://www.leafground.com/pages/Calendar.html");
        // click the blank text field
        driver.findElement(By.id("datepicker")).click();
        // click the calendar
        driver.findElement(By.xpath("//a[@data-handler='next']")).click();
        // click the number 10
        driver.findElement(By.xpath("//a[text()='10']"));
    }

    @Test
    public void testDraggable(){
        driver.get("http://leafground.com/pages/drag.html");
        WebElement dragging = driver.findElement(By.id("draggable"));
        Actions actions = new Actions(driver);
        actions.dragAndDropBy(dragging, 100, 200).perform();
    }

    @Test
    public void testDroppable(){
        driver.get("http://leafground.com/pages/drop.html");
        WebElement source = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.id("droppable"));
        Actions actions2 = new Actions(driver);
        actions2.dragAndDrop(source, target).perform();
    }

    @Test
    public void testSelectable(){
        driver.get("http://leafground.com/pages/selectable.html");
        List<WebElement> selecting = driver.findElements(By.xpath("//div[@id='mydiv']//li"));
        Actions actions3 = new Actions(driver);
        // click first element, press CTRL key, click the second element
        actions3.click(selecting.get(0)).keyDown(Keys.CONTROL).click(selecting.get(selecting.size()-1)).perform();
    }

    @Test
    public void testSortable(){
        //Navigate to Sortable test.resources.Page
        driver.get("http://www.leafground.com/pages/sortable.html");

        // Item 1, 2, 4, 5
        WebElement item1 = driver.findElement(By.xpath("(//ul[@id='sortable']//following::li)[1]"));
        WebElement item2 = driver.findElement(By.xpath("(//ul[@id='sortable']//following::li)[2]"));
        WebElement item4 = driver.findElement(By.xpath("(//ul[@id='sortable']//following::li)[4]"));
        WebElement item5 = driver.findElement(By.xpath("(//ul[@id='sortable']//following::li)[5]"));

        Actions actionSort = new Actions(driver);
        actionSort.dragAndDrop(item5, item4).perform();
        actionSort.dragAndDrop(item2,item1).perform();
    }

    @Test
    public void testAutoComplete() throws InterruptedException {
        driver.get("http://www.leafground.com/pages/autoComplete.html");
        // blank text box
        WebElement autoInput = driver.findElement(By.id("tags"));
        autoInput.sendKeys("sele");
        Thread.sleep(2000);
        driver.findElement(By.id("ui-id-2")).click();
    }

    @Test
    public void testDownloadFiles() throws InterruptedException {
        driver.get("http://www.leafground.com/pages/download.html");

        // download location
        File file = new File("C:\\Users\\Asterisk\\Downloads\\Documents");
        int before = file.listFiles().length;

        //Download Excel:
        WebElement linkOne = driver.findElement(By.xpath("//div[@class='todo']//a[text()='Download Excel']"));
        linkOne.click();
        Thread.sleep(1000);
        Assert.assertEquals(before,13);

        // Download PDF
        WebElement linkTwo = driver.findElement(By.xpath("(//div[@class='todo']//following::a)[1]"));
        linkTwo.click();
        driver.get("http://www.leafground.com/pages/download.html");

        // Download Text
        WebElement linkThree = driver.findElement(By.xpath("(//div[@class='todo']//following::a)[2]"));
        linkThree.click();
    }

    @Test
    public void testUploadFiles(){
        driver.get("http://leafground.com/pages/upload.html");
        // since it's an input, we just sendKeys("File location from Computer")
        driver.findElement(By.xpath("//input[@name='filename']")).sendKeys("D:\\7zip\\7-Zip\\7z.exe");
    }

    @Test
    public void testToolTip(){
        driver.get("http://leafground.com/pages/tooltip.html");
        // Input box
        WebElement textBox = driver.findElement(By.id("age"));
        Actions action = new Actions(driver);
        action.moveToElement(textBox).perform();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class = 'ui-tooltip-content']")).isDisplayed());

    }

    @Test
    public void testWaitToDisappear(){
        //-------------- notes
        // implicit wait ----> wait on certain amount of time then THROW the error
        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // explicit wait ----> wait on certain CONDITION then THROW the error
        // WebDriverWait wait = new WebDriverWait(driver, 30);

        // Below for FLuent Wait

        driver.get("http://leafground.com/pages/disapper.html");
        WebElement elementWait = driver.findElement(By.id("btn"));
        // This Explicit Wait will overwrite the implicit wait
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.invisibilityOf(elementWait));

        String textWait = driver.findElement(By.id("show")).getText();
        Assert.assertEquals(textWait, "I know you can do it! Button is disappeared!");

        //-------------- notes
        // fluent wait   ----> Similar as Explicit wait just 3 parameters|arguments CONDITIONS
        //        Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
        //                .withTimeout(20, TimeUnit.SECONDS)    // timeout at 20 seconds
        //                .pollingEvery(5, TimeUnit.SECONDS)    // check element is there every 5 seconds
        //                .ignoring(NoSuchElementException.class);     // if not there ignore it
        //        wait.until((driver) -> element.isEnabled());        // waiting until element is available
    }

    @Test
    public void testWaitToAppear(){
        driver.get("http://leafground.com/pages/appear.html");
        // wait for new button text is visible than click
        WebElement elementAppear = driver.findElement(By.xpath("//button[@id='btn']"));
        WebDriverWait waitAppear = new WebDriverWait(driver, 30);
        waitAppear.until(ExpectedConditions.visibilityOf(elementAppear));
        // verify the new button is there
        Assert.assertEquals(elementAppear.getText(), "Voila! I'm here Guys");
    }

    @Test
    public void testWaitForTextChange(){
        driver.get("http://leafground.com/pages/TextChange.html");
        // wait for button text changes to "Click ME!"
        WebElement elementT = driver.findElement(By.xpath("//button[@id='btn' and @onclick='clicked()']"));
        WebDriverWait wait2 = new WebDriverWait(driver, 30);
        wait2.until(ExpectedConditions.textToBePresentInElement(elementT, "Click ME!"));
        // verify new button text
        Assert.assertEquals(elementT.getText(), "Click ME!");
        // click, get the text, verify it, finally accept the alert
        elementT.click();
        String AlertText = driver.switchTo().alert().getText();
        Assert.assertEquals(AlertText, "Click ME!");
        driver.switchTo().alert().accept();
    }

    @Test
    public void testWaitForAlert(){
        driver.get("http://leafground.com/pages/alertappear.html");
        // Click on Alert
        WebElement elementAlertAppear = driver.findElement(By.xpath("//button[text()='Click to get Alert']"));
        elementAlertAppear.click();
        // Wait until Alert is visible, Verify the Alert, finally accept the Alert
        WebDriverWait wait3 = new WebDriverWait(driver, 30);
        wait3.until(ExpectedConditions.alertIsPresent());
        String AlertText2 = driver.switchTo().alert().getText();
        Assert.assertEquals(AlertText2, "Hurray, Click OK");
        driver.switchTo().alert().accept();
    }

    @Test
    public void testMouseHover(){
        driver.get("http://leafground.com/pages/mouseOver.html");
        // mouse hover, THEN print all the following Links
        WebElement MouseHover = driver.findElement(By.xpath("//a[text()='TestLeaf Courses']"));
        List<WebElement> element3List = driver.findElements(By.xpath("//li//ul//li//a"));
        // mouse hovering
        Actions actionsMouse = new Actions(driver);
        actionsMouse.moveToElement(MouseHover).perform();
        // print all following links
        System.out.println("-----------Test Mouse Hover-------------------");
        for(WebElement totalAlert : element3List){
            System.out.println(totalAlert.getText());
        }
        System.out.println("------------------------------------------");

        // click on course and handle the alert
        WebElement handleAlert = driver.findElement(By.xpath("//li//ul//li//a"));
        handleAlert.click();
        driver.switchTo().alert().accept();
    }

    @Test
    public void testAdvanceWebTable(){
        //        - Q 5: Advance webtable
        //        - Click on 'name' column -> store the values in a list
        //                - sort the above list in reverse order
        //        - Click on 'name' column again -> store the values in a list
        //        - Compare both the lists using [1. Use collections sort , best to 2. Use comparator]

        driver.get("http://leafground.com/pages/sorttable.html");
        // click name
        WebElement Name = driver.findElement(By.xpath("//tr//th[text()='Name']"));
        Name.click();

        //---List 1---------------------------------------
        // store Value in list in reverse order
        List<WebElement> nameValue = driver.findElements(By.xpath("//tr//td[2]"));
        // store in a new list and get text of each value
        List<String> nameValueList = new ArrayList<>();
        for (WebElement text1 : nameValue ) {
            nameValueList.add(text1.getText());
        }

        // another way to do it
        //        String[] text = new String[nameValueList.size()];
        //        int i = 0;
        //        for(WebElement name : nameValueList){
        //                text[i] = name.getText();
        //                i++;
        //        }
        //        Arrays.sort(text);

        System.out.println("-----------Test Advance Web table-------------------");
        // print all the text value
        System.out.println("Sorted in Reverse: " + nameValueList);

        //---List 2---------------------------------------
        // store new name after click "Name"
        Name.click();
        // verify where list is located
        List<WebElement> nameValue2 = driver.findElements(By.xpath("//tr//td[2]"));
        // store in a new list and get text of each value
        List<String> nameValueList2 = new ArrayList<>();
        for (WebElement text2 : nameValue2 ) {
            nameValueList2.add(text2.getText());
        }

        // another way to do it
        //        String[] text2 = new String[nameValueList2.size()];
        //        int j = 0;
        //        for(WebElement name2 : nameValueList2){
        //            text2[j] = name2.getText();
        //            j++;
        //        }

        // print all the text value
        System.out.println("The New name after another Click: " + nameValueList2);

        //---Compare Both List---------------------------------------
        System.out.println("Same Table?: " +nameValue.containsAll(nameValue2)); // return true because both list contains same element
        System.out.println("------------------------------------------");
    }

    @AfterTest
    public void tearDown(){
        System.out.println("Test Finished");
        driver.quit();
    }
}

