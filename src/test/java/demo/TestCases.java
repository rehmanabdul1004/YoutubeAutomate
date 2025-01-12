package demo;

import org.openqa.selenium.By;          // All these are the packages which we imported to use in the code below
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import demo.utils.ExcelDataProvider;
import demo.wrappers.Wrappers;

import java.sql.Wrapper;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class TestCases extends ExcelDataProvider{ // Lets us read the data

    ChromeDriver driver;                // Initializing ChromeDriver
    SoftAssert softAssert = new SoftAssert(); // Initialize SoftAssert

    @Test//Code for TestCase01
    public void testCase01() throws InterruptedException{
        Wrappers wrapp = new Wrappers(driver); // Initializing Wrapper clasess
        driver.get("https://www.youtube.com/");         //Url to open in the Browser
        Wrappers.logStatus("TestCase01 has Stared", "Pass");           // Usage of Wrapper Class
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));         //Usage of Wait as we are working with WebDriverWait
        //This following line is used to wait until page is loaded completely
        wait.until(driver -> ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        //String originalUrl = driver.getWindowHandle();
        // Using SoftAssert for checking the URL
        softAssert.assertTrue(driver.getCurrentUrl().equals("https://www.youtube.com/"), "URL mismatch!");

        driver.findElement(By.xpath("//*[@id='guide-icon']")).click();  // clicking the element
        WebElement about = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='guide-links-primary']/a[1]")));    //Wait until the given elemenet presence is identified
        Wrappers.moveToElement(about);         //Usage of Wrapper class
        about.click();  //Clicking the WebElement
        //Wait until the webpage is loaded
        wait.until(driver -> ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        //Thread.sleep(4000);
        Wrappers.logStatus(driver.findElement(By.xpath("//*[@id=\"content\"]/section/h1")).getText(), "Check");
        Wrappers.logStatus(driver.findElement(By.xpath("//*[@id=\"content\"]/section/p[1]")).getText(), "Check");
        Wrappers.logStatus(driver.findElement(By.xpath("//*[@id=\"content\"]/section/p[2]")).getText(), "Check");
        Wrappers.logStatus("TestCase01 has Ended", "Pass");
        // If you want to check multiple conditions, you can continue using softAssert for other checks
    }
    @Test// Code for TestCase02
    public void testCase02(){
        Wrappers.logStatus("TestCase2 has Stared", "Check");    //Usage of Wrapper Class
        Wrappers wrapp = new Wrappers(driver);  //Initializing the Wrapper class with driver
        driver.get("https://www.youtube.com/");
        //Wrappers.logStatus("TestCase01 has Stared", "Pass");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); //Usage of WebDriver Wait
        //This is used to wait until the page is loaded completely
        wait.until(driver -> ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        driver.findElement(By.xpath("//*[@id='guide-icon']")).click();
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement films = driver.findElement(By.xpath("//yt-formatted-string[text()='Films' or text()='Movies']"));
        Wrappers.moveToElement(films);  //Usage of Wrapper Class
        films.click();
        //This is used to wait until the page is loaded completely        
        wait.until(driver -> ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        //WebElement sideScrollButton=driver.findElement(By.xpath("//*[@id='right-arrow']/ytd-button-renderer/yt-button-shape"));
        while(true){
                try{
                        WebElement rightArrowButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='right-arrow']/ytd-button-renderer/yt-button-shape")));
                        rightArrowButton.click();
                        Thread.sleep(500);
                }catch(Exception e){
                        break;
                }
        }
        WebElement lastMovie = driver.findElement(By.xpath("//*[@id='items']/ytd-grid-movie-renderer[last()]"));
        String category = lastMovie.findElement(By.xpath("./a/span")).getText();
        String categoryType = category.substring(0,category.indexOf(" "));
        //System.out.println(categoryType);
        softAssert.assertTrue(categoryType.equals("Animation"),"Category does not match");
       // softAssert.assertTrue(categoryType.equals("Animation"), "Category does not match");        
        String Type = lastMovie.findElement(By.xpath("./ytd-badge-supported-renderer/div[2]/p")).getText();
        softAssert.assertTrue(Type.equals("U"),"Movie Type does not match");
        Wrappers.logStatus("TestCase02 has Ended", "Check");
    }
    @Test       //Code for TestCase03
    public void testCase03(){
        Wrappers.logStatus("TestCase3 has Stared", "Check");
        Wrappers wrapp = new Wrappers(driver);  //Initializing the Wrapper class with driver
        driver.get("https://www.youtube.com/"); //Opening the url in the Browser
        //Wrappers.logStatus("TestCase01 has Stared", "Pass");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //Wit until page is Loaded completely
        wait.until(driver -> ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        driver.findElement(By.xpath("//*[@id='guide-icon']")).click();
        WebElement MuiscButton = driver.findElement(By.xpath("//yt-formatted-string[text()='Music']"));
        MuiscButton.click();
        wait.until(driver -> ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        //WebElement sideScroll = driver.findElement(By.xpath("//*[@id='contents']/ytd-item-section-renderer[2]/div[3]/ytd-shelf-renderer/div[1]/div[2]/yt-horizontal-list-renderer/div[3]/ytd-button-renderer//button"));
        while(true){
                try{  
                        WebElement scrollClick= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@id='right-arrow']/ytd-button-renderer/yt-button-shape/button)[1]")));
                        Wrappers.moveToElement(scrollClick);
                        scrollClick.click();
                }catch(Exception e){
                        break;
                }
        }
        WebElement lastMusic = driver.findElement(By.xpath("//*[@id='contents']/ytd-item-section-renderer[1]/div[3]/ytd-shelf-renderer/div[1]/div[2]/yt-horizontal-list-renderer/div[2]/div/div/yt-lockup-view-model[last()]"));
        String songNameCollection = lastMusic.findElement(By.xpath("./div/div/yt-lockup-metadata-view-model/div/h3/a/span")).getText();
        //System.out.println(songNameCollection);
        Wrappers.Print(songNameCollection);//Usage of Wrapper class
        String count = driver.findElement(By.xpath("//*[@id='items']/yt-lockup-view-model[12]/div/a/yt-collection-thumbnail-view-model/yt-collections-stack/div/div[3]/yt-thumbnail-view-model/yt-thumbnail-overlay-badge-view-model/yt-thumbnail-badge-view-model/badge-shape/div[2]")).getText();
        int value = Integer.parseInt(count.substring(0,count.indexOf(" "))); 
        System.out.println(value);
        softAssert.assertTrue(value>=50,"Count mismatched");    //Softasser Usage
        Wrappers.logStatus("TestCase3 has Ended", "DONE");
    }//*[@id='contents']/ytd-item-section-renderer[1]/div[3]/ytd-shelf-renderer/div[1]/div[2]/yt-horizontal-list-renderer/div[2]/div/div/yt-lockup-view-model[last()]/div/a/yt-collection-thumbnail-view-model/yt-collections-stack/div/div[3]/yt-thumbnail-view-model/div
    @Test
    public void testCase04(){//Code for TestCase04
        Wrappers.logStatus("TestCase3 has Stared", "Check");
        Wrappers wrapp = new Wrappers(driver);  //Initializing the Wrapper class with driver
        driver.get("https://www.youtube.com/");         //Opening the code for the browser
        //Wrappers.logStatus("TestCase01 has Stared", "Pass");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); //Usage of WebDriver Wait
        //wait until the page is loaded completely
        wait.until(driver -> ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        driver.findElement(By.xpath("//*[@id='guide-icon']")).click();  //Applying click even on the element
        WebElement NewsButton = driver.findElement(By.xpath("//yt-formatted-string[text()='News']"));
        NewsButton.click();
        wait.until(driver -> ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        int sum =0;
        for(int i=0;i<3;i++){
                //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[@id='contents']/ytd-rich-item-renderer["+(i+1)+"])[2]")));
                WebElement newsElement = driver.findElement(By.xpath("(//*[@id='contents']/ytd-rich-item-renderer["+(i+1)+"])[2]"));
                Wrappers.moveToElement(newsElement);
                String Title = newsElement.findElement(By.xpath("./div/ytd-post-renderer/div[1]/div[1]/div[2]/a/span")).getText();
                System.out.println("Title of "+ (i+1)+" is "+ Title);
                String Body = newsElement.findElement(By.xpath("./div/ytd-post-renderer/div[1]/div[2]/div[1]/yt-formatted-string/span[1]")).getText();
                System.out.println("Body of "+ (i+1)+" is "+ Body);
                try{
                        String Likes = newsElement.findElement(By.xpath("./div/ytd-post-renderer/div[1]/div[3]/ytd-comment-action-buttons-renderer/div[1]/span[2]")).getText();
                        sum = sum+Integer.parseInt(Likes);
                        //System.out.println(Likes);
                }catch(Exception e){}
                System.out.println("Sum of Likes is -"+sum);
        }
    }
    @Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)              //Usage of DataProvider and TestCase05
    public void testCase05(String Searchterm) throws InterruptedException{
        Wrappers.logStatus("TestCase3 has Stared", "Check");
        Wrappers wrapp = new Wrappers(driver);
        driver.get("https://www.youtube.com/");
        //Wrappers.logStatus("TestCase01 has Stared", "Pass");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver -> ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        WebElement SearchBar= driver.findElement(By.xpath("//*[@id='center']/yt-searchbox/div[1]/form/input"));
        SearchBar.sendKeys(Searchterm);
        SearchBar.sendKeys(Keys.ENTER);
        Thread.sleep(10000);
        wait.until(driver -> ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        
        //WebElement ViewData = driver.findElement(By.xpath("//*[@id='contents']/ytd-video-renderer[1]/div[1]/div/div[1]/ytd-video-meta-block/div[1]/div[2]/span[1]")); 
        
        //String IntValue = Viewvalue.substring(0, ViewData.indexOf(" "));
        long views = 0;
        //List<WebElement> a = new ArrayList<>();
        List<WebElement> viewdataElements = driver.findElements(By.xpath("//*[@id='contents']/ytd-video-renderer/div[1]/div/div[1]/ytd-video-meta-block/div[1]/div[2]/span[1]"));
        for(WebElement viewData: viewdataElements){
                //Wrappers.moveToElement(viewData);
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].scrollIntoView(true);", viewData);
                String viewCountText = viewData.getText();
                try{ 
                        if(views<=100000000){
                                if (viewCountText.contains("K")) {
                                        // Extract numeric part and convert to long
                                        String intValue = viewCountText.substring(0, viewCountText.indexOf("K")).trim();
                                        views = (long) (Double.parseDouble(intValue) * 1000); // Multiply by 1000 for "K"
                                } else if (viewCountText.contains("M")) {
                                        // Extract numeric part and convert to long
                                        String intValue = viewCountText.substring(0, viewCountText.indexOf("M")).trim();
                                        views = (long) (Double.parseDouble(intValue) * 1000000); // Multiply by 1,000,000 for "M"
                                } else if (viewCountText.contains("B")) {
                                        // Extract numeric part and convert to long
                                        String intValue = viewCountText.substring(0, viewCountText.indexOf("B")).trim();
                                        views = (long) (Double.parseDouble(intValue) * 1000000000); // Multiply by 1,000,000,000 for "B"
                                } else {
                                        // If the view count is just a number (e.g., "50000"), no need for "K", "M", or "B"
                                        views = Long.parseLong(viewCountText.replace(",", "").trim()); // Remove commas for large numbers
                                }
                                
                        }
                        else{
                                break;
                        }
                        //Wrappers.moveToElement(ViewData);
        }catch(Exception e){}
        }
        System.out.println("views count is - " + views);
        
    }

    @BeforeTest                 //Executed before each test
    public void startBrowser() {
        
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // ChromeOptions and logging preferences setup
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        // Set ChromeDriver log property
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");
        System.setProperty("file.encoding", "UTF-8");


        // Initialize ChromeDriver with options
        driver = new ChromeDriver(options);

        // Maximize the window
        driver.manage().window().maximize();
    }

    @AfterTest          //Executed after each test
    public void endTest() {
        // Assert all the soft assertions
        softAssert.assertAll();  // Ensure all soft assertions are checked

        // Close and quit the driver
        driver.close();
        driver.quit();
    }
}
