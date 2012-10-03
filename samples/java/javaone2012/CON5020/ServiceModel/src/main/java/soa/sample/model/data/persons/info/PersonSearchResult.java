package soa.sample.model.data.persons.info;

import java.io.Serializable;
import java.util.List;


public class PersonSearchResult implements Serializable {
        
    private List<PersonIdentificationResult> personIdentificationResultList;
    private String additionalInformation;

    public void setPersonIdentificationResultList(List<PersonIdentificationResult> personIdentificationResultList) {
        this.personIdentificationResultList = personIdentificationResultList;
    }

    public List<PersonIdentificationResult> getPersonIdentificationResultList() {
        return personIdentificationResultList;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }
}
