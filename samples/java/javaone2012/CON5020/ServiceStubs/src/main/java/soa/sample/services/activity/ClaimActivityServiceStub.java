/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soa.sample.services.activity;

import java.util.Set;
import javax.ejb.Stateless;
import javax.validation.ConstraintViolation;
import javax.validation.groups.Default;
import soa.sample.database.DataStoreStub;
import soa.sample.model.data.claims.Claim;
import soa.sample.model.data.claims.activity.ClaimActivityInput;
import soa.sample.model.data.claims.activity.ClaimActivityReturn;
import soa.sample.model.data.claims.activity.ClaimStatusActivityInput;
import soa.sample.model.data.claims.info.ClaimIdentificationResult;
import soa.sample.model.data.persons.Person;
import soa.sample.model.faults.BusinessException;
import soa.sample.model.services.activity.ClaimActivityService;
import soa.sample.model.validation.groups.New;
import soa.sample.model.validation.utils.ValidationUtils;

/**
 *
 * @author dvalys
 */
@Stateless(name="ClaimActivityService")
public class ClaimActivityServiceStub implements ClaimActivityService {

    private DataStoreStub dataStore = DataStoreStub.DATABASE;
        
    public ClaimActivityReturn createClaim(ClaimActivityInput claimActivityInput) throws BusinessException {
                        
        Set<ConstraintViolation<ClaimActivityInput>> constraintViolations 
                = ValidationUtils.getValidator().validate(claimActivityInput, Default.class,New.class,ClaimActivityService.class);
        
        ValidationUtils.throwBusinessException(constraintViolations);
        
        ClaimIdentificationResult claimIdentificationResult = new ClaimIdentificationResult();
        Claim claim = claimActivityInput.getClaim();
                
        claim.setId(dataStore.getNextId());
        claim.setClaimNumber(claim.getId().intValue() * 10);
        
        claimIdentificationResult.setClaim(claim);
        
        //Person reporting a claim
        Person person = dataStore.getPersonIdentificationResultMap().get(claimActivityInput.getClaim().getReporterPersonId()).getPerson();
        claimIdentificationResult.setReporterPerson(person);
        
        dataStore.getClaimIdentificationResultMap().put(claim.getId(), claimIdentificationResult);
                                                
                    
        ClaimActivityReturn serviceReturn = new ClaimActivityReturn();
        serviceReturn.setClaimId(claim.getId());
        serviceReturn.setClaimNumber(claim.getClaimNumber());
        serviceReturn.setClaimStatus(claim.getStatus());
                    
        return serviceReturn;        
        
    }

    public ClaimActivityReturn updateClaimStatus(ClaimStatusActivityInput claimStatusActivityInput) throws BusinessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
