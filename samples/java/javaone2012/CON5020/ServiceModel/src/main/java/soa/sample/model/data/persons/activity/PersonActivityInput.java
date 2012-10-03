package soa.sample.model.data.persons.activity;

import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import soa.sample.model.data.Address;
import soa.sample.model.data.persons.Person;

public class PersonActivityInput implements Serializable {
        
    @Valid          
    @NotNull
    private Person person;
        
    @Valid      
    @NotNull
    private Address address;

    public PersonActivityInput() {
    }
        
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
