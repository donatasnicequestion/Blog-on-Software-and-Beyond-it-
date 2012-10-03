/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soa.sample.model.webserviceproxy;

import javax.enterprise.inject.New;
import javax.enterprise.inject.Produces;
import soa.sample.model.processes.ReportClaimProcess;
import soa.sample.model.processes.ReportClaimProcessWebService_Service;
import soa.sample.model.services.activity.PersonActivityService;
import soa.sample.model.services.activity.PersonActivityWebService_Service;
import soa.sample.model.services.info.ClaimIdentificationService;
import soa.sample.model.services.info.ClaimIdentificationWebService_Service;
import soa.sample.model.services.info.LookupService;
import soa.sample.model.services.info.LookupWebService_Service;
import soa.sample.model.services.info.PersonIdentificationService;
import soa.sample.model.services.info.PersonIdentificationWebService_Service;

/**
 *
 * @author dvalys
 */
public class ServiceProxyProducer {
    
    
    @Produces PersonActivityService getPersonActivityService(@New PersonActivityWebService_Service service) {
        return service.getPersonActivityWebServicePort();         
    }
    
    
    @Produces ReportClaimProcess getReportClaimProcess(@New ReportClaimProcessWebService_Service service)  {
        return service.getReportClaimProcessWebServicePort();         
    }    
    
    
    @Produces LookupService getLookupService(@New LookupWebService_Service service) {
        return service.getLookupWebServicePort();         
    }        
    
    
    @Produces PersonIdentificationService getPersonIdentificationService(@New PersonIdentificationWebService_Service service) {
        return service.getPersonIdentificationWebServicePort();         
    }            
    
    
    @Produces ClaimIdentificationService getClaimIdentificationService(@New ClaimIdentificationWebService_Service service) {
        return service.getClaimIdentificationWebServicePort();         
    }            
    
        
}
