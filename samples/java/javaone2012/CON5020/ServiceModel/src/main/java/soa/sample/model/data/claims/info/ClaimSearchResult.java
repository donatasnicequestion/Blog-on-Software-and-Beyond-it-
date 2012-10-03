package soa.sample.model.data.claims.info;

import java.io.Serializable;
import java.util.List;


public class ClaimSearchResult implements Serializable {
    
    private List<ClaimIdentificationResult>  claimIdentificationResultList;
    private String additionalInformation;

    public void setClaimIdentificationResultList(List<ClaimIdentificationResult> claimIdentificationResultList) {
        this.claimIdentificationResultList = claimIdentificationResultList;
    }

    public List<ClaimIdentificationResult> getClaimIdentificationResultList() {
        return claimIdentificationResultList;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }
}
