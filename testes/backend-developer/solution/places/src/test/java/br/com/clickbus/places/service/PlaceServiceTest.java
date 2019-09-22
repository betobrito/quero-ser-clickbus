package br.com.clickbus.places.service;

import br.com.clickbus.places.domain.Place;
import br.com.clickbus.places.repository.PlaceRepository;
import br.com.clickbus.places.service.impl.PlaceServiceImpl;
import br.com.clickbus.places.util.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.com.clickbus.places.util.Constant.MSG_NO_LOCATIONS_FOUND;
import static br.com.clickbus.places.util.TestConstant.MSG_THIS_METHOD_SHOULD_NOT_BE_CALLED;
import static br.com.clickbus.places.util.TestConstant.STRING_VAZIA;
import static br.com.clickbus.places.web.rest.PlaceResourceTest.ID_ONE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PlaceServiceTest {

    @Mock
    private PlaceRepository placeRepositoryMock;
    private PlaceService placeService;

    private Place place;
    private Optional<Place> optionalPlace;
    private List<Place> places;

    @Before
    public void context() {
        this.placeService = new PlaceServiceImpl(placeRepositoryMock);
        this.place = new Place();
        this.optionalPlace = Optional.of(place);
        this.places = new ArrayList<>();
    }

    @Test
    public void shouldCallMethodCreateDelegatingToTheRepository() {
        when(placeRepositoryMock.save(this.place)).thenReturn(this.place);

        final Place returnPlace = placeService.create(this.place);

        verify(placeRepositoryMock).save(this.place);
        assertEquals(returnPlace, this.place);
    }

    @Test
    public void shouldCallMethodFindDelegatingToTheRepository() {
        when(placeRepositoryMock.findById(ID_ONE)).thenReturn(this.optionalPlace);

        final Place placeReturning = placeService.find(ID_ONE);

        verify(placeRepositoryMock).findById(ID_ONE);
        assertEquals(placeReturning, this.place);
    }

    @Test
    public void shouldCallMethodGetSpecificThrowingExceptionNotFound() {
        when(placeRepositoryMock.findById(ID_ONE)).thenReturn(Optional.empty());

        try{
            placeService.find(ID_ONE);
            fail(MSG_THIS_METHOD_SHOULD_NOT_BE_CALLED);
        } catch (NotFoundException e) {
            assertEquals(MSG_NO_LOCATIONS_FOUND, e.getMessage());
        }
    }

    @Test
    public void shouldCallMethodEditWithPlaceNotFoundThrowingExceptionNotFound() {
        try{
            placeService.edit(this.place);
            fail(MSG_THIS_METHOD_SHOULD_NOT_BE_CALLED);
        } catch (NotFoundException e) {
            assertEquals(MSG_NO_LOCATIONS_FOUND, e.getMessage());
        }
    }

    @Test
    public void shouldCallMethodEditDelegatingToTheRepository() {
        when(placeRepositoryMock.findById(this.place.getId())).thenReturn(optionalPlace);
        when(placeRepositoryMock.save(this.place)).thenReturn(this.place);

        final Place editedPlace = placeService.edit(this.place);

        verify(placeRepositoryMock).save(this.place);
        assertEquals(editedPlace, this.place);
    }

    @Test
    public void shouldCallMethodListPlaceByNameDelegatingToTheRepository() {
        when(placeRepositoryMock.findAllByNameContaining(STRING_VAZIA)).thenReturn(this.places);

        final List<Place> placesReturn = placeService.listFilterByName(STRING_VAZIA);

        verify(placeRepositoryMock).findAllByNameContaining(STRING_VAZIA);
        assertEquals(placesReturn, this.places);
    }
}
