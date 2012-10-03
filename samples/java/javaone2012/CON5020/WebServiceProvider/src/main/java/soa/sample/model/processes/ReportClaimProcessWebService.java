/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soa.sample.model.processes;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import soa.sample.model.data.claims.activity.process.ReportClaimInput;
import soa.sample.model.data.claims.activity.process.ReportClaimReturn;
import soa.sample.model.faults.BusinessException;

/**
 *
 * @author dvalys
 */
@WebService(serviceName = "ReportClaimProcessWebService")
public class ReportClaimProcessWebService implements ReportClaimProcess {
    @EJB
    private ReportClaimProcess ejbRef;

    @WebMethod(operationName = "process")
    @Override
    public ReportClaimReturn process(@WebParam(name = "reportClaimInput") ReportClaimInput reportClaimInput) throws BusinessException {
        return ejbRef.process(reportClaimInput);
    }
    
}
