package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class samplesteps {
	
    @Given("^I execute first step$")
    public void i_execute_first_step() throws Throwable {
        System.out.println("First");
    }

    @When("^I execute second step$")
    public void i_execute_second_step() throws Throwable {
    	 System.out.println("second");
    }

    @Then("^I execute third step$")
    public void i_execute_third_step() throws Throwable {
    	 System.out.println("third");
    }


}
