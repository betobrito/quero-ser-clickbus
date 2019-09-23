package br.com.clickbus.places.cucumber.stepdefs;

import br.com.clickbus.places.domain.dto.PlaceDTO;
import br.com.clickbus.places.domain.dto.SearchParameterDTO;
import br.com.clickbus.places.util.JsonConverter;
import cucumber.api.java.Before;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FunctionalityStepDefs extends StepDefs {

    public static final int ONE_RECORD = 1;

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
        assertEquals(ONE_RECORD, places.size());
        assertEquals(dataTable.column(0).get(1), places.get(0).getName());
        assertEquals(dataTable.column(1).get(1), places.get(0).getSlug());
        assertEquals(dataTable.column(2).get(1), places.get(0).getCity());
    }

    @Then("should return a empty list")
    public void shouldReturnAEmptyList() throws Exception {
        this.actions.andExpect(status().isOk());
        List<PlaceDTO> places = JsonConverter.asJsonToClassList(this.actions.andReturn().getResponse().getContentAsString(), PlaceDTO.class);
        assertTrue(places.isEmpty());
    }

    @Given("Since you should register a place with the following information: name {string}, slug {string} and city {string}")
    public void sinceYouShouldRegisterAPlaceWithTheFollowingInformationNameSlugAndCity(String name, String slug, String city) throws Exception {
        PlaceDTO place = new PlaceDTO().name(name).slug(slug).city(city);
        mockPost("/places/", place);
    }

    @Then("should return a place with id {string} and name {string}")
    public void shouldReturnAPlaceWithIdAndNamed(String id, String name) throws Exception {
        this.actions.andExpect(status().isCreated());
        final PlaceDTO placeDTO = JsonConverter.asJsonToClass(this.actions.andReturn().getResponse().getContentAsString(), PlaceDTO.class);
        assertEquals(id, placeDTO.getId().toString());
        assertEquals(name, placeDTO.getName());
    }
}
