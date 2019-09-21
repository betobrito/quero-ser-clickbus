package br.com.clickbus.places.service.impl;

import br.com.clickbus.places.domain.Place;
import br.com.clickbus.places.domain.SearchParameterDTO;
import br.com.clickbus.places.service.PlaceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceServiceImpl implements PlaceService {

    @Override
    public Place create(Place place) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Place> getSpecific(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Place edit(Place placeConverted) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Place> listFilterByName(SearchParameterDTO searchParameter) {
        throw new UnsupportedOperationException();
    }
}
