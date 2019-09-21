package br.com.clickbus.places.web.rest;

import br.com.clickbus.places.domain.Place;
import br.com.clickbus.places.domain.PlaceDTO;
import br.com.clickbus.places.domain.converter.impl.PlaceConvert;
import br.com.clickbus.places.service.PlaceService;
import br.com.clickbus.places.util.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;
import java.util.Optional;

import static junit.framework.TestCase.assertSame;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PlaceResourceTest {

    public static final long ID_ONE = 1L;
    @Mock
    private PlaceService placeServiceMock;

    @Mock
    private PlaceConvert convertMock;

    private PlaceResource placeResource;
    private PlaceDTO placeDTO;
    private Place place;
    private Optional<Place> optionalPlace;

    @Before
    public void context() {
        this.placeResource = new PlaceResource(placeServiceMock, convertMock);
        this.placeDTO = new PlaceDTO();
        this.place = new Place();
        this.optionalPlace = Optional.of(place);
    }

    @Test
    public void shouldCallMethodCreateDelegatingToTheConvertAndCreate() throws URISyntaxException {
        when(convertMock.convert(placeDTO)).thenReturn(place);
        when(placeServiceMock.create(place)).thenReturn(place);

        ResponseEntity resultado = placeResource.create(placeDTO);

        verify(convertMock).convert(placeDTO);
        verify(placeServiceMock).create(place);
        assertEquals(HttpStatus.CREATED, resultado.getStatusCode());
        assertSame(place, resultado.getBody());
    }

    @Test
    public void shouldCallMethodGetSpecificPlaceDelegatingToTheService() throws URISyntaxException {
        when(placeServiceMock.getSpecific(ID_ONE)).thenReturn(optionalPlace);

        ResponseEntity resultado = placeResource.getSpecific(ID_ONE);

        verify(placeServiceMock).getSpecific(ID_ONE);
        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertSame(place, resultado.getBody());
    }

    @Test
    public void shouldCallMethodGetSpecificThrowingExceptionNotFound() throws URISyntaxException {
        when(placeServiceMock.getSpecific(ID_ONE)).thenReturn(Optional.empty());

        try{
            ResponseEntity resultado = placeResource.getSpecific(ID_ONE);
            fail("This method should not be called.");
        } catch (NotFoundException e) {
            assertEquals("No locations found.", e.getMessage());
        }
    }

    @Test
    public void shouldCallMethodEditDelegatingToTheConvertAndCreate() throws URISyntaxException {
        when(convertMock.convert(placeDTO)).thenReturn(place);
        when(placeServiceMock.edit(place)).thenReturn(place);

        ResponseEntity resultado = placeResource.edit(placeDTO);

        verify(convertMock).convert(placeDTO);
        verify(placeServiceMock).edit(place);
        assertEquals(HttpStatus.CREATED, resultado.getStatusCode());
        assertSame(place, resultado.getBody());
    }

    @Test
    public void shouldCallMethodListPlacesDelegatingToTheService() throws URISyntaxException {
        when(placeServiceMock.getSpecific(ID_ONE)).thenReturn(optionalPlace);

        ResponseEntity resultado = placeResource.getSpecific(ID_ONE);

        verify(placeServiceMock).getSpecific(ID_ONE);
        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertSame(place, resultado.getBody());
    }
}
