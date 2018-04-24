package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.google.common.base.Function;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import utils.Constants;
import utils.SpreadSheetReader;
import utils.WebDriverFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyFirstTest {

    WebDriver browser;
    private static ExtentReports report;
    private Wait<WebDriver> wait;

    @BeforeClass
    public static void init() {
        report = new ExtentReports();
        report.attachReporter(new ExtentHtmlReporter(Constants.PATHTO.getStuff()+Constants.REPORT.getStuff()));
    }

    @Before
    public void setUp(){
         browser = WebDriverFactory.getWebDriver("chrome");
//        FirefoxOptions capabilities = new FirefoxOptions();
//        capabilities.setCapability("marionette", true);
//        browser = new FirefoxDriver(capabilities);
        wait = new FluentWait<WebDriver>(browser)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(3, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
    }

    @After
    public void tearDown(){
        browser.quit();
    }

    @Test
    public void testqa(){
        browser.get(Constants.URLTOQA.getStuff());
        String title = browser.getTitle();
        Assert.assertEquals("Page Title not the same", "IT Training | Project Management Training | Business Skills Training | QA", title);
    }
    @Test
    public void testdemo(){
        ExtentTest test = report.createTest("MyFirstTest");
        SpreadSheetReader sheetReader = new SpreadSheetReader(Constants.PATHTO.getStuff()+Constants.EXCEL.getStuff());

        for (int i=0; i<2; i++) {
            List<String> row = sheetReader.readRow(i, "Sheet1");

            browser.navigate().to(Constants.URLTODEMO.getStuff());
            wait.until(new Function<WebDriver, WebElement>() {
                public WebElement apply(WebDriver driver) {
                    return driver.findElement(By.xpath("/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[3]"));
                }
            });
            test.log(Status.INFO, "At the Website");

            browser.findElement(By.xpath("/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[3]")).click();
            wait.until(new Function<WebDriver, WebElement>() {
                public WebElement apply(WebDriver driver) {
                    return driver.findElement(By.cssSelector("id"));
                }
            });
            test.log(Status.INFO, "At the addUser page");

            browser.findElement(By.name("username")).sendKeys(row.get(0));
            browser.findElement(By.name("password")).sendKeys(row.get(1));
            test.log(Status.DEBUG, "added username: " + row.get(0) + " & password: " + row.get(1));

            browser.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[3]/td[2]/p/input")).click();

            browser.findElement(By.xpath("/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[4]")).click();
            wait.until(new Function<WebDriver, WebElement>() {
                public WebElement apply(WebDriver driver) {
                    return driver.findElement(By.cssSelector("id"));
                }
            });
            test.log(Status.INFO, "At the login page");

            browser.findElement(By.name("username")).sendKeys(row.get(0));
            browser.findElement(By.name("password")).sendKeys(row.get(1));
            browser.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[3]/td[2]/p/input")).click();
            test.log(Status.DEBUG, "login using username: " + row.get(0) + " & password: " + row.get(1));

            String success_log = browser.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b")).getText();
            Assert.assertEquals("**Successful Login**", success_log);
        }
    }

    @Test
    public void spreadSheets() {
        SpreadSheetReader sheetReader = new SpreadSheetReader(Constants.PATHTO.getStuff()+Constants.EXCEL.getStuff());
        List<String> row = sheetReader.readRow(0, "Sheet1");


        for(String cell : row){
            System.out.println(cell);
        }
    }

    @AfterClass
    public static void cleanUp() {
        report.flush();
    }

}
