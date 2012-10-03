/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soa.sample.processes;

import java.util.Set;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.ConstraintViolation;
import soa.sample.model.data.claims.ClaimStatus;
import soa.sample.model.data.claims.activity.ClaimActivityReturn;
import soa.sample.model.data.claims.activity.process.ReportClaimInput;
import soa.sample.model.data.claims.activity.process.ReportClaimReturn;
import soa.sample.model.data.persons.activity.PersonActivityReturn;
import soa.sample.model.faults.BusinessException;
import soa.sample.model.processes.ReportClaimProcess;
import soa.sample.model.services.activity.ClaimActivityService;
import soa.sample.model.services.activity.PersonActivityService;
import soa.sample.model.validation.utils.ValidationUtils;

/**
 *
 * @author dvalys
 */
@Stateless(name="ReportClaimProcess")
public class ReportClaimProcessStub implements ReportClaimProcess {
    
    static final Logger logger = Logger.getLogger(ReportClaimProcessStub.class.getName());
    
    @EJB
    private ClaimActivityService claimActivityService;
    
    @EJB
    private PersonActivityService personActivityService;
    

    public ReportClaimReturn process(ReportClaimInput reportClaimInput) throws BusinessException {
        logger.info("ReportClaimProcess validate");        
        
        //Apply default bean validation contraints on a server side
        Set<ConstraintViolation<ReportClaimInput>> constraintViolations 
                = ValidationUtils.getValidator().validate(reportClaimInput);
        
        ValidationUtils.throwBusinessException(constraintViolations);
        
        logger.info("ReportClaimProcess process");                                                
        ReportClaimReturn processReturn = new ReportClaimReturn();
        PersonActivityReturn personActivityReturn;
        ClaimActivityReturn claimActivityReturn;
        
        // Very lightweight service orchestration 
        if(reportClaimInput.getPersonActivityInput().getPerson().getId() == null) {
            logger.info("ReportClaimProcess create Person");                                        
        
            // New person reporting a claim - create new person service call
            personActivityReturn = personActivityService.createPerson(reportClaimInput.getPersonActivityInput());
            
            //Set status to submitted for new person
            reportClaimInput.getClaimActivityInput().getClaim().setStatus(ClaimStatus.Submitted);
            
        } else {
            logger.info("ReportClaimProcess update Person");                                        
        
            // Person reporting a claim already exists - update person information service call
            personActivityReturn = personActivityService.updatePerson(reportClaimInput.getPersonActivityInput());
            
            //Set status to accepted in case a person reporting already exists
            reportClaimInput.getClaimActivityInput().getClaim().setStatus(ClaimStatus.Accepted);
                    
        }

        //Set id of the person reporting a claim
        reportClaimInput.getClaimActivityInput().getClaim().setReporterPersonId(personActivityReturn.getPersonId());
        
        // Report claim data            
        logger.info("ReportClaimProcess Report claim");
        claimActivityReturn = claimActivityService.createClaim(reportClaimInput.getClaimActivityInput());
        
        // Compose process return message
        processReturn.setPersonActivityReturn(personActivityReturn);
        processReturn.setClaimActivityReturn(claimActivityReturn);
        
        logger.info("ReportClaimProcess return");
        return processReturn;
    }
    
}
