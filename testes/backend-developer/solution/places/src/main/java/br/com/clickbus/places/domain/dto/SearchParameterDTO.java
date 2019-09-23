package br.com.clickbus.places.domain.dto;

import java.io.Serializable;

public class SearchParameterDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    public SearchParameterDTO() {
    }

    public SearchParameterDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
