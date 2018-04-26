package PHPTravelsTests;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.WebDriverFactory;
import utils.phpTravelsutils.PHPHomePage;

import java.util.Date;

public class PHPTravelsdef {
    protected WebDriver browser;
    private PHPHomePage search;
    @Before
    public void setUp(){
        browser = WebDriverFactory.getWebDriver("chrome");
    }

    @After
    public void tearDown(){
        browser.quit();
        System.out.println("PHPTravels");
    }
    @Given("^I am at \"([^\"]*)\" home page$")
    public void iAmAtHomePage(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        browser.get(arg0);
    }

    @When("^I give location london and duration for two days with (\\d+) adults$")
    public void iGiveLocationLondonAndDurationForTwoDaysWithAdults(int arg0) throws Exception {
        // Write code here that turns the phrase above into concrete actions
        search = PageFactory.initElements(browser,PHPHomePage.class);

        search.setSearchBox("London");
        search.setNoOfAdults(arg0);
        search.setSearchEndDate("26/04/2018");
        Thread.sleep(1000);
    }

    @Then("^I am on the correct page if and only if the url is correct$")
    public void iAmOnTheCorrectPageIfAndOnlyIfTheUrlIsCorrect() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
    }
}
