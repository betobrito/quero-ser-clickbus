package br.com.clickbus.places.util.errors;

@FunctionalInterface
public interface SupplierThrowsException<T> {

    T get() throws Exception;

}
