package br.com.clickbus.places.web.rest;

import br.com.clickbus.places.domain.Place;
import br.com.clickbus.places.domain.dto.PlaceDTO;
import br.com.clickbus.places.domain.dto.SearchParameterDTO;
import br.com.clickbus.places.service.PlaceService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PlaceResourceTest {

    public static final long ID_ONE = 1L;

    @Mock
    private PlaceService placeServiceMock;

    private PlaceResource placeResource;
    private PlaceDTO placeDTO;
    private Place place;
    private Optional<Place> optionalPlace;
    private SearchParameterDTO searchParameter;
    private List<Place> places;

    @Before
    public void context() {
        this.placeResource = new PlaceResource(placeServiceMock);
        this.place = new Place();
        this.placeDTO = new PlaceDTO(place);
        this.optionalPlace = Optional.of(place);
        this.searchParameter = new SearchParameterDTO();
        this.places = new ArrayList<>();
    }

    @Test
    public void shouldCallMethodCreateDelegatingToTheConvertAndCreate() throws URISyntaxException {
        when(placeServiceMock.create(place)).thenReturn(place);

        ResponseEntity resultado = placeResource.create(placeDTO);

        verify(placeServiceMock).create(place);
        assertEquals(HttpStatus.CREATED, resultado.getStatusCode());
        assertEquals(placeDTO, resultado.getBody());
    }

    @Test
    public void shouldCallMethodGetSpecificPlaceDelegatingToTheService() {
        when(placeServiceMock.find(ID_ONE)).thenReturn(place);

        ResponseEntity resultado = placeResource.find(ID_ONE);

        verify(placeServiceMock).find(ID_ONE);
        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(placeDTO, resultado.getBody());
    }

    @Test
    public void shouldCallMethodEditDelegatingToTheConvertAndCreate() throws URISyntaxException {
        when(placeServiceMock.edit(place)).thenReturn(place);

        ResponseEntity resultado = placeResource.edit(placeDTO);

        verify(placeServiceMock).edit(place);
        assertEquals(HttpStatus.CREATED, resultado.getStatusCode());
        assertEquals(placeDTO, resultado.getBody());
    }

    @Test
    public void shouldCallMethodListPlacesFilterByNameDelegatingToTheService() {
        when(placeServiceMock.listFilterByName(searchParameter.getName())).thenReturn(places);

        ResponseEntity resultado = placeResource.list(searchParameter);

        verify(placeServiceMock).listFilterByName(searchParameter.getName());
        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(places, resultado.getBody());
    }
}
