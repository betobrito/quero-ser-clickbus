package br.com.clickbus.places.service.impl;

import br.com.clickbus.places.domain.Place;
import br.com.clickbus.places.repository.PlaceRepository;
import br.com.clickbus.places.service.PlaceService;
import br.com.clickbus.places.util.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static br.com.clickbus.places.util.Constant.MSG_NO_LOCATIONS_FOUND;

@Service
@Transactional
public class PlaceServiceImpl implements PlaceService {

    private PlaceRepository placeRepository;

    public PlaceServiceImpl(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @Override
    public Place create(Place place) {
        return placeRepository.save(place);
    }

    @Override
    public Place find(Long id) {
        final Optional<Place> optionalPlace = placeRepository.findById(id);
        return optionalPlace.orElseThrow(() -> new NotFoundException(MSG_NO_LOCATIONS_FOUND));
    }

    @Override
    public Place edit(Place modifiedPlace) {
        checkIfExistsPlace(modifiedPlace);
        return placeRepository.save(modifiedPlace);
    }

    private void checkIfExistsPlace(Place modifiedPlace) {
        find(modifiedPlace.getId());
    }

    @Override
    public List<Place> listFilterByName(String name) {
        return placeRepository.findAllByNameContaining(name);
    }
}
