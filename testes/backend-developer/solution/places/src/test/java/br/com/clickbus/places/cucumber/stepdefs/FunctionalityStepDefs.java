package br.com.clickbus.places.cucumber.stepdefs;

import cucumber.api.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FunctionalityStepDefs extends StepDefs {

    @Before
    public void context() {
        helperContext.clearTestData();
    }

    @Given("^that there is a registered place$")
    public void thatThereIsAhRegisteredPlace() {
        contextAssembler.addPlaces();
    }

    @Given("that the place entered has id {string}")
    public void thatThePlaceEnteredHasId(String id) throws Exception {
        this.actions = mockGet("/places/{id}", id);
    }

    @Then("should return a place named {string}")
    public void shouldReturnAPlaceNamed(String name) throws Exception {
        this.actions.andExpect(status().isOk());
        this.actions.andExpect(MockMvcResultMatchers.jsonPath("$.name").value(name));
    }

    @Then("should return an error with status not found")
    public void shouldReturnAnErrorWithStatusNotFound() throws Exception {
        this.actions.andExpect(status().isNotFound());
    }
}
