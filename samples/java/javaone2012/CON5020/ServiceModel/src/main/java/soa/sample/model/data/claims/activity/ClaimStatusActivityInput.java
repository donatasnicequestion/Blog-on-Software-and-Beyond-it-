package soa.sample.model.data.claims.activity;


import java.io.Serializable;
import soa.sample.model.data.claims.ClaimStatus;

public class ClaimStatusActivityInput implements Serializable {
    
    private Long ClaimID;
    private ClaimStatus newStatus;

    public void setClaimID(Long ClaimID) {
        this.ClaimID = ClaimID;
    }

    public Long getClaimID() {
        return ClaimID;
    }

    public void setNewStatus(ClaimStatus newStatus) {
        this.newStatus = newStatus;
    }

    public ClaimStatus getNewStatus() {
        return newStatus;
    }
}
