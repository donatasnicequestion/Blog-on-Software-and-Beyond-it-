package soa.sample.model.data.claims.activity;

import java.io.Serializable;
import soa.sample.model.data.claims.ClaimStatus;

public class ClaimActivityReturn implements Serializable {
   
   private Long claimId;
   private Integer claimNumber;
   private ClaimStatus claimStatus;

    public void setClaimNumber(Integer claimNumber) {
        this.claimNumber = claimNumber;
    }

    public Integer getClaimNumber() {
        return claimNumber;
    }

    public void setClaimId(Long claimId) {
        this.claimId = claimId;
    }

    public Long getClaimId() {
        return claimId;
    }

    public void setClaimStatus(ClaimStatus claimStatus) {
        this.claimStatus = claimStatus;
    }

    public ClaimStatus getClaimStatus() {
        return claimStatus;
    }
}
