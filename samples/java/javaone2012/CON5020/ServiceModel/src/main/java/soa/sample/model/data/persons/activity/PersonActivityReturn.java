package soa.sample.model.data.persons.activity;

import java.io.Serializable;

public class PersonActivityReturn implements Serializable {
    
    private Long personId;
    private Integer personalNumber;

    public void setPersonalNumber(Integer personalNumber) {
        this.personalNumber = personalNumber;
    }

    public Integer getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getPersonId() {
        return personId;
    }
}
