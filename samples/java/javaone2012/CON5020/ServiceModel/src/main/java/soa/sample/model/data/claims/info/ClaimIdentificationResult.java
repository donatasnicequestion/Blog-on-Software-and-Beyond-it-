package soa.sample.model.data.claims.info;


import java.io.Serializable;
import soa.sample.model.data.claims.Claim;
import soa.sample.model.data.persons.Person;


public class ClaimIdentificationResult implements Serializable {
    
    private Claim claim;
    private Person reporterPerson;

    public void setClaim(Claim claim) {
        this.claim = claim;
    }

    public Claim getClaim() {
        return claim;
    }

    public void setReporterPerson(Person reporterPerson) {
        this.reporterPerson = reporterPerson;
    }

    public Person getReporterPerson() {
        return reporterPerson;
    }
}
