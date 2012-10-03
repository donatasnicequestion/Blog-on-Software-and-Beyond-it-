/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soa.sample.services.info;

import javax.ejb.Stateless;
import soa.sample.database.DataStoreStub;
import soa.sample.model.data.claims.info.ClaimIdentificationResult;
import soa.sample.model.data.claims.info.ClaimSearchCriteria;
import soa.sample.model.data.claims.info.ClaimSearchResult;
import soa.sample.model.data.persons.Person;
import soa.sample.model.services.info.ClaimIdentificationService;

/**
 *
 * @author dvalys
 */
@Stateless(name="ClaimIdentificationService")
public class ClaimIdentificationServiceStub implements ClaimIdentificationService {
    private DataStoreStub dataStore = DataStoreStub.DATABASE;
    
    public ClaimIdentificationResult identifyClaimById(Long claimId) {        
        ClaimIdentificationResult result = dataStore.getClaimIdentificationResultMap().get(claimId);                
        Person person = dataStore.getPersonIdentificationResultMap().get(result.getClaim().getReporterPersonId()).getPerson();        
        result.setReporterPerson(person);                              
        return result;
    }

    public ClaimSearchResult claimSearch(ClaimSearchCriteria claimSearchCriteria) {
        ClaimSearchResult result = new ClaimSearchResult();        
        result.setClaimIdentificationResultList(dataStore.getClaimIdentificationResultList());        
        return result;
    }
    
}
