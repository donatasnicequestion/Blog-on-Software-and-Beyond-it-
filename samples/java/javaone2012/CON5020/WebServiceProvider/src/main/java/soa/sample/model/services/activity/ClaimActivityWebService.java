/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soa.sample.model.services.activity;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import soa.sample.model.data.claims.activity.ClaimActivityInput;
import soa.sample.model.data.claims.activity.ClaimActivityReturn;
import soa.sample.model.faults.BusinessException;

/**
 *
 * @author dvalys
 */
@WebService(serviceName = "ClaimActivityWebService")
public class ClaimActivityWebService implements ClaimActivityService {
    @EJB
    private ClaimActivityService ejbRef;

    @WebMethod(operationName = "createClaim")
    @Override
    public ClaimActivityReturn createClaim(@WebParam(name = "claimActivityInput") ClaimActivityInput claimActivityInput) throws BusinessException {
        return ejbRef.createClaim(claimActivityInput);
    }
    
}
