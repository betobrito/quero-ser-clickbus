package br.com.clickbus.places.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Place implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String slug;
    private String city;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Place() {}

    public Place(Long id, String name, String slug, String city, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.city = city;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public Place id(Long id){
        this.id = id;
        return this;
    }

    public Place name(String name){
        this.name = name;
        return this;
    }

    public Place slug(String slug){
        this.slug = slug;
        return this;
    }

    public Place city(String city){
        this.city = city;
        return this;
    }

    public Place createdAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
        return this;
    }

    public Place updatedAt(LocalDateTime updatedAt){
        this.updatedAt = updatedAt;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return Objects.equals(id, place.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", slug='" + slug + '\'' +
                ", city='" + city + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
