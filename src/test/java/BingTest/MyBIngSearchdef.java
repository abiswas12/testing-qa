package BingTest;

import com.google.common.base.Function;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import utils.BingSearch;
import utils.WebDriverFactory;

import java.util.concurrent.TimeUnit;

public class MyBIngSearchdef {
    protected WebDriver browser;
    private BingSearch search;
    private Wait<WebDriver> wait;


    @Before
    public void setUp(){
        browser = WebDriverFactory.getWebDriver("chrome");
        wait = new FluentWait<WebDriver>(browser)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(3, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
    }

    @After
    public void tearDown(){
        browser.quit();
        System.out.println("Bing");
    }

    @Given("^I am at \"([^\"]*)\" search page$")
    public void iAmAtSearchPage(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        browser.get(arg0);
        wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.name("q"));
            }
        });
        browser.manage().window().maximize();
    }

    @When("^I search for \"([^\"]*)\"$")
    public void iSearchForString(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        search = PageFactory.initElements(browser, BingSearch.class);
        search.setSearchBox(arg0);
    }

    @Then("^The first result is google$")
    public void theFirstResultIsGoogle() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        String success = browser.findElement(By.xpath("//*[@id=\"b_results\"]/li[1]/h2/a/strong")).getText();
        Assert.assertTrue(success.equalsIgnoreCase("Google"));

    }

    @Then("^The first result is yahoo$")
    public void theFirstResultIsYahoo() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        String success = browser.findElement(By.xpath("//*[@id=\"b_results\"]/li[1]/h2/a/strong")).getText();
        Assert.assertTrue(success.equalsIgnoreCase("Yahoo"));
    }
}