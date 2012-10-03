package soa.sample.model.data.persons.info;

import java.io.Serializable;
import soa.sample.model.data.Address;
import soa.sample.model.data.persons.Person;

public class PersonIdentificationResult implements Serializable {
    
     private Person person;
     private Address address;

    public void setPerson(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }
    
}
