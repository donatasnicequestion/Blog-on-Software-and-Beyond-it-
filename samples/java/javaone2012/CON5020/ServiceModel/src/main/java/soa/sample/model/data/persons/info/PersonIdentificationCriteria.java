package soa.sample.model.data.persons.info;

import java.io.Serializable;

public class PersonIdentificationCriteria implements Serializable {
    
    private Long id;
    private Integer personnumber;
    
    
    public PersonIdentificationCriteria() {
        super();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setPersonnumber(Integer personnumber) {
        this.personnumber = personnumber;
    }

    public Integer getPersonnumber() {
        return personnumber;
    }
}
