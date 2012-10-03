package soa.sample.model.data.claims.activity;

import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import soa.sample.model.data.claims.Claim;

public class ClaimActivityInput implements Serializable {

    @Valid 
    @NotNull
    private Claim claim;
            
    public void setClaim(Claim claim) {
        this.claim = claim;
    }

    public Claim getClaim() {
        return claim;
    }
}
