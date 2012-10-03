/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soa.sample.model.services.info;

import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;
import soa.sample.model.data.Country;
import soa.sample.model.data.LossType;

/**
 *
 * @author dvalys
 */
@WebService(serviceName = "LookupWebService")
public class LookupWebService implements LookupService {
    @EJB
    private LookupService ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"

    @WebMethod(operationName = "getLossTypeList")
    @Override
    public List<LossType> getLossTypeList() {
        return ejbRef.getLossTypeList();
    }

    @WebMethod(operationName = "getCountryList")
    @Override
    public List<Country> getCountryList() {
        return ejbRef.getCountryList();
    }
    
}
