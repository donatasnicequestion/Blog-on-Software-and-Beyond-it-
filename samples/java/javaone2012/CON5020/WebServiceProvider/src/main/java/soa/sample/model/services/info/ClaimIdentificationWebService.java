/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soa.sample.model.services.info;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import soa.sample.model.data.claims.info.ClaimIdentificationResult;
import soa.sample.model.data.claims.info.ClaimSearchCriteria;
import soa.sample.model.data.claims.info.ClaimSearchResult;

/**
 *
 * @author dvalys
 */
@WebService(serviceName = "ClaimIdentificationWebService")
public class ClaimIdentificationWebService implements ClaimIdentificationService {
    @EJB
    private soa.sample.model.services.info.ClaimIdentificationService ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"

    @WebMethod(operationName = "identifyClaimById")
    @Override
    public ClaimIdentificationResult identifyClaimById(@WebParam(name = "claimNumber") Long claimNumber) {
        return ejbRef.identifyClaimById(claimNumber);
    }

    @WebMethod(operationName = "claimSearch")
    @Override
    public ClaimSearchResult claimSearch(@WebParam(name = "claimSearchCriteria") ClaimSearchCriteria claimSearchCriteria) {
        return ejbRef.claimSearch(claimSearchCriteria);
    }
    
}
