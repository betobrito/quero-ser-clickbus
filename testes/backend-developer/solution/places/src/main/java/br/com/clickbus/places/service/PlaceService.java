package br.com.clickbus.places.service;

import br.com.clickbus.places.domain.Place;

import java.util.List;

public interface PlaceService {

    Place create(Place place);

    Place find(Long id);

    Place edit(Place placeConverted);

    List<Place> listFilterByName(String name);
}
