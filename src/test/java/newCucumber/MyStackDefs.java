package newCucumber;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class MyStackDefs {
    StackEx stack;
    @Given("^An empty stack$")
    public void anEmptyStack() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        stack=new StackExample();
        Assert.assertEquals(0,stack.getSize());
        throw new PendingException();
    }

    @When("^I add a string to the stack$")
    public void iAddAStringToTheStack() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        stack.addString("Anirban");
        throw new PendingException();
    }

    @Then("^The size is equal to (\\d+)$")
    public void theSizeIsEqualTo(int one) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(one,stack.getSize());
        throw new PendingException();
    }
}
