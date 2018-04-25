package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.Constants;
import utils.Cookies;

public class bbcSiteCookie {
    String url;
    WebDriver driver;
    ExtentReports report;
    ExtentTest test;

    @Before
    public void setup() {

        report = new ExtentReports();


        System.setProperty("webdriver.chrome.driver", "C:/Development/web_driver/chromedriver.exe");

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        url = "https://www.bbc.co.uk/";

    }

    @After
    public void teardown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }

    @Test
    public void cookieTest() {

        login();
        Cookies.createCookie(driver,url);
        logout();

        driver.get("https://www.google.co.uk/");

        loadCookie();
    }

    public void login() {

        test = report.createTest("Verify login");

        driver.get(url);

        driver.findElement(By.id("idcta-link")).click();
        driver.findElement(By.id("user-identifier-input")).sendKeys("");


        driver.findElement(By.id("password-input")).sendKeys("");
        driver.findElement(By.id("submit-button")).click();

        test.log(Status.INFO, "Attempted to login");

        checkLogin();
        report.flush();
    }

    public void logout() {
        test = report.createTest("Verify logout");

        driver.findElement(By.id("idcta-link")).click();
        driver.findElement(By.cssSelector("#orb-modules > div.___iso-html___ > div > div > "
                + "div.background.background--attenborough > div.u-background-transparent-black > "
                + "div > div > div > div > nav > ul > li:nth-child(3) > a")).click();

        test.log(Status.INFO, "Attempted to logout");

        checkLogout();

        report.endTest(test);
        report.flush();
    }
    public void checkLogin() {

        driver.findElement(By.id("idcta-link")).click();
        driver.findElement(By.id("idcta-link")).click();

        String bodyText = driver.findElement(By.tagName("body")).getText();

        if (bodyText.contains("Your account")) {
            test.log(LogStatus.PASS, "Login valid");
        } else {
            test.log(LogStatus.FAIL, "Login failed");
        }
    }

    public void checkLogout() {

        String check = driver.findElement(By.id("idcta-username")).getText();

        if (check.equalsIgnoreCase("Sign in")) {
            test.log(LogStatus.PASS, "Login valid");
        } else {
            test.log(LogStatus.FAIL, "Login failed");
        }
    }
}
