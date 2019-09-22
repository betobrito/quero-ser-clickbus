package br.com.clickbus.places.web.rest;

import br.com.clickbus.places.domain.Place;
import br.com.clickbus.places.domain.dto.PlaceDTO;
import br.com.clickbus.places.domain.dto.SearchParameterDTO;
import br.com.clickbus.places.service.PlaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static br.com.clickbus.places.domain.dto.PlaceDTO.convert;
import static br.com.clickbus.places.util.Constant.API_PLACES;

@RestController
@RequestMapping(API_PLACES)
public class PlaceResource {

    private final Logger log = LoggerFactory.getLogger(PlaceResource.class);

    private final PlaceService placeService;

    public PlaceResource(PlaceService placeService) {
        this.placeService = placeService;
    }

    @PostMapping
    public ResponseEntity<PlaceDTO> create(@RequestBody PlaceDTO place) throws URISyntaxException {
        log.debug("Rest call method create place: {}", place);
        final Place insertedPlace = placeService.create(place.transformToEntity());
        return ResponseEntity
                .created(new URI(API_PLACES + insertedPlace.getId()))
                .body(PlaceDTO.of(insertedPlace));
    }

    @PutMapping
    public ResponseEntity<PlaceDTO> edit(@Valid @RequestBody PlaceDTO place) throws URISyntaxException {
        log.debug("Rest call method edit place: {}", place);
        final Place editedPlace = placeService.edit(place.transformToEntity());
        return ResponseEntity
                .created(new URI(API_PLACES + editedPlace.getId()))
                .body(PlaceDTO.of(editedPlace));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaceDTO> find(@PathVariable Long id) {
        log.debug("Rest call method to get specific place by id: {}", id);
        Place place = placeService.find(id);
        return ResponseEntity.ok().body(PlaceDTO.of(place));
    }

    @PostMapping("/list")
    public ResponseEntity<List<PlaceDTO>> list(@RequestBody SearchParameterDTO searchParameter) {
        log.debug("Rest call method to list places by name: {}", searchParameter);
        List<Place> places = placeService.listFilterByName(searchParameter.getName());
        return ResponseEntity.ok().body(convert(places));
    }
}
