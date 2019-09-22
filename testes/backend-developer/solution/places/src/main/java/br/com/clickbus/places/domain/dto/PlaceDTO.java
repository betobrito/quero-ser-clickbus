package br.com.clickbus.places.domain.dto;

import br.com.clickbus.places.domain.Place;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PlaceDTO {

    @NotNull(message = "ID parameter is required.")
    private Long id;

    private String name;
    private String slug;
    private String city;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PlaceDTO() {
    }

    public PlaceDTO(Place place) {
        this.id = place.getId();
        this.name = place.getName();
        this.slug = place.getSlug();
        this.city = place.getCity();
        this.createdAt = place.getCreatedAt();
        this.updatedAt = place.getUpdatedAt();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public String getCity() {
        return city;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Place transformToEntity(){
        return new Place(this.id, this.name, this.slug, this.city, this.createdAt, this.updatedAt);
    }

    public static PlaceDTO of(Place place){
        return new PlaceDTO(place);
    }

    public static List<PlaceDTO> convert(List<Place> places){
        return places.stream().map(PlaceDTO::new).collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlaceDTO placeDTO = (PlaceDTO) o;
        return Objects.equals(id, placeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, slug, city, createdAt, updatedAt);
    }
}
