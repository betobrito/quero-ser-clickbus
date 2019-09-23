package br.com.clickbus.places.cucumber.assembler;

import br.com.clickbus.places.cucumber.util.HelperContext;
import org.springframework.stereotype.Component;

import static br.com.clickbus.places.web.rest.PlaceResourceTest.ID_ONE;

@Component
public class ContextAssemblerImpl implements ContextAssembler {

    public static final String NAME_PAJUCARA_BEACH = "Pajuçara Beach";
    public static final String SLUG_PAJUCARA_BEACH = "/pajucara-beach";
    public static final String CITY_MACEIO = "Maceió";
    private HelperContext helperContext;

    public ContextAssemblerImpl(HelperContext helperContext) {
        this.helperContext = helperContext;
    }

    @Override
    public void addPlaces() {
        helperContext.insertPlace(ID_ONE, NAME_PAJUCARA_BEACH, SLUG_PAJUCARA_BEACH, CITY_MACEIO);
    }
}
