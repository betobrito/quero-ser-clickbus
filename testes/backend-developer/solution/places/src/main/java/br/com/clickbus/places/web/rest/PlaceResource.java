package br.com.clickbus.places.web.rest;

import br.com.clickbus.places.domain.Place;
import br.com.clickbus.places.domain.PlaceDTO;
import br.com.clickbus.places.domain.converter.Convert;
import br.com.clickbus.places.domain.converter.impl.PlaceConvert;
import br.com.clickbus.places.service.PlaceService;
import br.com.clickbus.places.util.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import static br.com.clickbus.places.util.Constant.API_PLACES;
import static br.com.clickbus.places.util.Constant.MSG_NO_LOCATIONS_FOUND;

@RestController
@RequestMapping(API_PLACES)
public class PlaceResource {

    private final Logger log = LoggerFactory.getLogger(PlaceResource.class);

    private final PlaceService placeService;
    private final Convert<PlaceDTO, Place> convert;

    public PlaceResource(PlaceService placeService, PlaceConvert placeConvert) {
        this.placeService = placeService;
        this.convert = placeConvert;
    }

    @PostMapping
    public ResponseEntity<Place> create(@RequestBody PlaceDTO place) throws URISyntaxException {
        log.debug("Rest call method create place: {}", place);
        final Place placeConverted = this.convert.convert(place);
        final Place insertedPlace = placeService.create(placeConverted);
        return ResponseEntity
                .created(new URI(API_PLACES + insertedPlace.getId()))
                .body(insertedPlace);
    }

    @PutMapping
    public ResponseEntity<Place> edit(@RequestBody PlaceDTO place) throws URISyntaxException {
        log.debug("Rest call method edit place: {}", place);
        final Place placeConverted = this.convert.convert(place);
        final Place editedPlace = placeService.edit(placeConverted);
        return ResponseEntity
                .created(new URI(API_PLACES + editedPlace.getId()))
                .body(editedPlace);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Place> getSpecific(@PathVariable Long id) {
        log.debug("Rest call method to get specific place by id: {}", id);
        Optional<Place> place = placeService.getSpecific(id);
        return ResponseEntity.ok().body(place.orElseThrow(() -> new NotFoundException(MSG_NO_LOCATIONS_FOUND)));
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<Place>> list(@PathVariable String name) {
        log.debug("Rest call method to list places by name: {}", name);
        List<Place> places = placeService.listFilterByName(name);
        return ResponseEntity.ok().body(places);
    }
}
