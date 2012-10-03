package soa.sample.model.services.activity;

import soa.sample.model.data.claims.activity.ClaimActivityInput;
import soa.sample.model.data.claims.activity.ClaimActivityReturn;
import soa.sample.model.faults.BusinessException;

public interface ClaimActivityService {
    
      public ClaimActivityReturn createClaim(ClaimActivityInput claimActivityInput) throws BusinessException;
                  
}
