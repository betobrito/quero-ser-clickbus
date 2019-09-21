package br.com.clickbus.places.service;

import br.com.clickbus.places.domain.Place;
import br.com.clickbus.places.domain.SearchParameterDTO;

import java.util.List;
import java.util.Optional;

public interface PlaceService {

    Place create(Place place);

    Optional<Place> getSpecific(Long id);

    Place edit(Place placeConverted);

    List<Place> listFilterByName(SearchParameterDTO searchParameter);
}
