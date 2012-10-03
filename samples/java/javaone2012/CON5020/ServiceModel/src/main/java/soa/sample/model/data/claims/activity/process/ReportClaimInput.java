package soa.sample.model.data.claims.activity.process;

import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import soa.sample.model.data.claims.activity.ClaimActivityInput;
import soa.sample.model.data.persons.activity.PersonActivityInput;
import soa.sample.model.validation.constraints.ReportClaimInputConstraint;


@ReportClaimInputConstraint
public class ReportClaimInput implements Serializable {
    
    @Valid
    @NotNull        
    private PersonActivityInput personActivityInput;
    
    @Valid        
    @NotNull        
    private ClaimActivityInput claimActivityInput;

    public void setPersonActivityInput(PersonActivityInput personActivityInput) {
        this.personActivityInput = personActivityInput;
    }

    public PersonActivityInput getPersonActivityInput() {
        return personActivityInput;
    }

    public void setClaimActivityInput(ClaimActivityInput claimActivityInput) {
        this.claimActivityInput = claimActivityInput;
    }

    public ClaimActivityInput getClaimActivityInput() {
        return claimActivityInput;
    }
}
