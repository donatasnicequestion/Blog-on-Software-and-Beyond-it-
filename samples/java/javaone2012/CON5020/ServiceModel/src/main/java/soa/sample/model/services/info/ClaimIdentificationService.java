package soa.sample.model.services.info;

import soa.sample.model.data.claims.info.ClaimIdentificationResult;
import soa.sample.model.data.claims.info.ClaimSearchCriteria;
import soa.sample.model.data.claims.info.ClaimSearchResult;

public interface ClaimIdentificationService {
    
    public ClaimIdentificationResult identifyClaimById(Long claimId);
    
    public ClaimSearchResult claimSearch(ClaimSearchCriteria claimSearchCriteria);
    
}
