package br.com.clickbus.places.cucumber.util;

public interface HelperContext {

    void clearTestData();

    void insertPlace(long id, String namePajucaraBeach, String slugPajucaraBeach, String cityMaceio);
}
