package br.com.clickbus.places.domain.converter.impl;

import br.com.clickbus.places.domain.Place;
import br.com.clickbus.places.domain.PlaceDTO;
import br.com.clickbus.places.domain.converter.Convert;
import org.springframework.stereotype.Component;

@Component
public class PlaceConvert implements Convert<PlaceDTO, Place> {

    @Override
    public Place convert(PlaceDTO placeDTO) {
        return new Place()
                .id(placeDTO.getId())
                .name(placeDTO.getName())
                .city(placeDTO.getCity())
                .slug(placeDTO.getSlug())
                .createdAt(placeDTO.getCreatedAt())
                .updatedAt(placeDTO.getUpdatedAt());
    }
}
