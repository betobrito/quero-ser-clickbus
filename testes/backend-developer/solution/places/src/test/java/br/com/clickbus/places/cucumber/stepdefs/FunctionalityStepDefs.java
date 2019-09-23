package br.com.clickbus.places.cucumber.stepdefs;

import br.com.clickbus.places.domain.dto.PlaceDTO;
import br.com.clickbus.places.domain.dto.SearchParameterDTO;
import br.com.clickbus.places.util.JsonConverter;
import cucumber.api.java.Before;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FunctionalityStepDefs extends StepDefs {

    public static final int ONE_RECORD = 1;
    public static final int NO_RECORDS = 0;

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

    @Given("Given that when calling the list places service by name with name {string}")
    public void givenThatWhenCallingTheListPlacesServiceByNameWithName(String name) throws Exception {
        SearchParameterDTO searchParameterDTO = new SearchParameterDTO(name);
        mockPost("/places/list", searchParameterDTO);
    }

    @Then("should return a list with a place, with the characteristics below:")
    public void shouldReturnAListWithAPlaceWithTheCharacteristicsBelow(DataTable dataTable) throws Exception {
        this.actions.andExpect(status().isOk());
        List<PlaceDTO> places = JsonConverter.asJsonToClassList(this.actions.andReturn().getResponse().getContentAsString(), PlaceDTO.class);
        Assert.assertEquals(ONE_RECORD, places.size());
        Assert.assertEquals(dataTable.column(0).get(1), places.get(0).getName());
        Assert.assertEquals(dataTable.column(1).get(1), places.get(0).getSlug());
        Assert.assertEquals(dataTable.column(2).get(1), places.get(0).getCity());
    }

    @Then("should return a empty list")
    public void shouldReturnAEmptyList() throws Exception {
        this.actions.andExpect(status().isOk());
        List<PlaceDTO> places = JsonConverter.asJsonToClassList(this.actions.andReturn().getResponse().getContentAsString(), PlaceDTO.class);
        Assert.assertEquals(NO_RECORDS, places.size());
    }
}
