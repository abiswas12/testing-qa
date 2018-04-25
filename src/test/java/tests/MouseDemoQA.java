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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import utils.Constants;
import utils.WebDriverFactory;

import java.util.concurrent.TimeUnit;

public class MouseDemoQA {
    WebDriver browser;
    private static ExtentReports report;
    private Wait<WebDriver> wait;
    private Actions mouseDriver;

    @BeforeClass
    public static void init() {
        report = new ExtentReports();
        report.attachReporter(new ExtentHtmlReporter(Constants.PATHTO.getStuff()+Constants.REPORT.getStuff()));
    }

    @Before
    public void setUp(){
        browser = WebDriverFactory.getWebDriver("chrome");
        mouseDriver = new Actions(browser);
        wait = new FluentWait<WebDriver>(browser)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(3, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
        browser.get(Constants.URLTOMOUSEDEMO.getStuff());
    }

    @After
    public void tearDown(){
        browser.quit();
    }

    @Test
    public void simpleTab(){
        ExtentTest test = report.createTest("MySimpleTab");
        test.log(Status.INFO, "At the Website");
        browser.findElement(By.xpath("//*[@id=\"ui-id-5\"]")).click();
        test.log(Status.DEBUG, "Clicked tab 5");
        String initString = browser.findElement(By.xpath("//*[@id=\"tabs-5\"]/p[1]/b")).getText();
        test.log(Status.DEBUG, "At the Website");
        Assert.assertEquals("Content 5 Title",initString);

    }

    @Test
    public void Draggable(){
        ExtentTest test = report.createTest("MyDraggableTab");

        test.log(Status.INFO, "At the Website");

        browser.findElement(By.xpath("//*[@id=\"menu-item-140\"]")).click();
        wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.xpath("//*[@id=\"draggable\"]/p"));
            }
        });
        test.log(Status.INFO, "Droppable tab");

        WebElement box = browser.findElement(By.xpath("//*[@id=\"draggable\"]"));
        test.log(Status.DEBUG, "Find box");

        mouseDriver.moveToElement(box);
        mouseDriver.dragAndDropBy(box, 450, 0).pause(1000).dragAndDropBy(box, -450, 200).pause(1000).dragAndDropBy(box, 450, 0).pause(1000).perform();
        test.log(Status.INFO, "Move box");

        Assert.assertEquals(714,box.getLocation().x);
        Assert.assertEquals(539,box.getLocation().y);

        browser.findElement(By.xpath("//*[@id=\"ui-id-2\"]")).click();
        test.log(Status.INFO, "Next droppable tab");

        WebElement boxVertical = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.xpath("//*[@id=\"draggabl\"]"));
            }
        });
        test.log(Status.DEBUG, "Find box which only moves vertically");

        WebElement boxHorizontal=browser.findElement(By.xpath("//*[@id=\"draggabl2\"]"));
        test.log(Status.DEBUG, "Find box which only moves horizontally");

        int startX = boxVertical.getLocation().x;
        int startY = boxHorizontal.getLocation().y;
        test.log(Status.DEBUG, "X value for vertical box:"+startX+"Y value for horizontal box:"+startY);

        mouseDriver.dragAndDropBy(boxVertical,100,100).dragAndDropBy(boxHorizontal,100,100).perform();
        test.log(Status.INFO, "Move both");

        Assert.assertEquals(boxVertical.getLocation().x,startX);
        Assert.assertEquals(boxHorizontal.getLocation().y,startY);
        test.log(Status.DEBUG, "Assert them");

    }

    @Test
    public void Droppable(){ // In progress
        ExtentTest test = report.createTest("MyDroppableTab");

        test.log(Status.INFO, "At the Website");

        browser.findElement(By.xpath("//*[@id=\"menu-item-141\"]")).click();
        wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.xpath("//*[@id=\"draggableview\"]/p"));
            }
        });
        test.log(Status.INFO, "Droppable tab");

        WebElement box = browser.findElement(By.xpath("//*[@id=\"draggableview\"]"));
        test.log(Status.DEBUG, "Find box");
    }
    @AfterClass
    public static void cleanUp() {
        report.flush();
    }
}
