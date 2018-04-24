package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.Constants;
import utils.SpreadSheetReader;

import java.util.List;

public class MyFirstTest {

    WebDriver browser;
    private static ExtentReports report;

    @BeforeClass
    public static void init() {
        report = new ExtentReports();
        report.attachReporter(new ExtentHtmlReporter(Constants.PATHTO.getStuff()+Constants.REPORT.getStuff()));
    }

    @Before
    public void setUp(){
        browser = new ChromeDriver();
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
            test.log(Status.INFO, "At the Website");

            browser.findElement(By.xpath("/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[3]")).click();
            test.log(Status.INFO, "At the addUser page");

            browser.findElement(By.name("username")).sendKeys(row.get(0));
            browser.findElement(By.name("password")).sendKeys(row.get(1));
            test.log(Status.DEBUG, "added username: " + row.get(0) + " & password: " + row.get(1));

            browser.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[3]/td[2]/p/input")).click();

            browser.findElement(By.xpath("/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[4]")).click();
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
