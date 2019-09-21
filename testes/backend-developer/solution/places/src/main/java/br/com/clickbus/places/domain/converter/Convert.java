package br.com.clickbus.places.domain.converter;


public interface Convert<EntryObject, OutObject> {

    OutObject convert(EntryObject entryObject);

}
