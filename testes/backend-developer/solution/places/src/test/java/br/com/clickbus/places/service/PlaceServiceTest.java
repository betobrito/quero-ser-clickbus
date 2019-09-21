package br.com.clickbus.places.service;

import br.com.clickbus.places.repository.PlaceRepository;
import br.com.clickbus.places.service.impl.PlaceServiceImpl;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PlaceServiceTest {

    @Mock
    private PlaceRepository placeRepositoryMock;

    private PlaceService placeService;

    @Before
    public void context() {
        this.placeService = new PlaceServiceImpl(placeRepositoryMock);
    }

}
