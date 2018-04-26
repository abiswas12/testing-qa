package pickleRick.stackTest;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.Stack;

public class MyStackDefs {
    Stack<String> stack;

    @Given("^An empty stack$")
    public void anEmptyStack() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        stack=new Stack<String>();
        Assert.assertEquals(0,stack.size());
        throw new PendingException();
    }

    @When("^I add a string to the stack$")
    public void iAddAStringToTheStack() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        stack.push("Anirban");
        throw new PendingException();
    }

    @Then("^The size is equal to (\\d+)$")
    public void theSizeIsEqualTo(int one) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        stack.pop();
        Assert.assertEquals(one,stack.size());
        throw new PendingException();
    }
}
