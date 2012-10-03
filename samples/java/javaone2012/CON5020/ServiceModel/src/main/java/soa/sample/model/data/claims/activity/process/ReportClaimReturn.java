package soa.sample.model.data.claims.activity.process;


import java.io.Serializable;
import soa.sample.model.data.claims.activity.ClaimActivityReturn;
import soa.sample.model.data.persons.activity.PersonActivityReturn;


public class ReportClaimReturn implements Serializable {
    
    private PersonActivityReturn personActivityReturn;
    private ClaimActivityReturn claimActivityReturn;


    public void setPersonActivityReturn(PersonActivityReturn personActivityReturn) {
        this.personActivityReturn = personActivityReturn;
    }

    public PersonActivityReturn getPersonActivityReturn() {
        return personActivityReturn;
    }

    public void setClaimActivityReturn(ClaimActivityReturn claimActivityReturn) {
        this.claimActivityReturn = claimActivityReturn;
    }

    public ClaimActivityReturn getClaimActivityReturn() {
        return claimActivityReturn;
    }
}
