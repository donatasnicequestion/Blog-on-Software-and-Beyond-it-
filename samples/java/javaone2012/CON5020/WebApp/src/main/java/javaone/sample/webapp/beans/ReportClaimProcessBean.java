package javaone.sample.webapp.beans;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import soa.sample.model.data.Address;
import soa.sample.model.data.LossType;
import soa.sample.model.data.claims.AutoClaim;
import soa.sample.model.data.claims.Claim;
import soa.sample.model.data.claims.ClaimType;
import soa.sample.model.data.claims.PropertyClaim;
import soa.sample.model.data.claims.activity.ClaimActivityInput;
import soa.sample.model.data.claims.activity.process.ReportClaimInput;
import soa.sample.model.data.claims.activity.process.ReportClaimReturn;
import soa.sample.model.data.claims.info.ClaimSearchCriteria;
import soa.sample.model.data.claims.info.ClaimSearchResult;
import soa.sample.model.data.persons.Person;
import soa.sample.model.data.persons.activity.PersonActivityInput;
import soa.sample.model.data.persons.info.PersonSearchCriteria;
import soa.sample.model.data.persons.info.PersonSearchResult;
import soa.sample.model.faults.BusinessException;
import soa.sample.model.faults.BusinessFault;
import soa.sample.model.processes.ReportClaimProcess;
import soa.sample.model.services.info.ClaimIdentificationService;
import soa.sample.model.services.info.LookupService;
import soa.sample.model.services.info.PersonIdentificationService;

/**
 *  Report claim process controller bean
 *
 * @author Donatas Valys
 */
@ManagedBean
@Named(value="ReportClaimProcessController")
@SessionScoped
public class ReportClaimProcessBean implements Serializable {
    
    static final Logger logger = Logger.getLogger(ReportClaimProcessBean.class.getName());
        
    private @Inject ReportClaimProcess reportClaimProcess;        
    private @Inject LookupService lookupService;        
    private @Inject PersonIdentificationService personIdentificationService;
    private @Inject ClaimIdentificationService claimIdentificationService;
    
    private ReportClaimInput reportClaimInput;       
    private ReportClaimReturn reportClaimReturn;
    private PropertyClaim propertyClaim;
    private AutoClaim autoClaim;
    private PersonSearchCriteria personSearchCriteria;
    private ClaimSearchCriteria claimSearchCriteria;

    @PostConstruct
    public void initialize() {
        reportClaimInput = new ReportClaimInput();
        reportClaimInput.setClaimActivityInput(new ClaimActivityInput());
        reportClaimInput.setPersonActivityInput(new PersonActivityInput());
        reportClaimInput.getPersonActivityInput().setPerson(new Person());
        reportClaimInput.getPersonActivityInput().setAddress(new Address());        
        reportClaimInput.getClaimActivityInput().setClaim(new Claim());        
        autoClaim = new AutoClaim();
        autoClaim.setDriver(new Person());
        autoClaim.setAccidentAddress(new Address());
        propertyClaim = new PropertyClaim();
        propertyClaim.setAddress(new Address());
        
        //Default claim type
        reportClaimInput.getClaimActivityInput().getClaim().setType(ClaimType.Auto);
        
        //PersonSearchCriteria
        personSearchCriteria = new PersonSearchCriteria();
        
        //ClaimSearchCriteria
        claimSearchCriteria = new ClaimSearchCriteria();
        
    }
    
    
    public String reportClaim() {
        
        logger.info("ReportClaimProcess call");
        try {
            
            if(reportClaimInput.getClaimActivityInput().getClaim().getType().equals(ClaimType.Auto )) {
                reportClaimInput.getClaimActivityInput().getClaim().setAutoClaim(autoClaim);
                reportClaimInput.getClaimActivityInput().getClaim().setPropertyClaim(null);        
            } else {
                reportClaimInput.getClaimActivityInput().getClaim().setPropertyClaim(propertyClaim);        
                reportClaimInput.getClaimActivityInput().getClaim().setAutoClaim(null);        
            }    
                    
            /* Call reportClaim process */
            reportClaimReturn = reportClaimProcess.process(reportClaimInput);        
                        
            /* */
            initialize();
            
        } catch (BusinessException ex) {
            logger.info(ex.getFaultInfo().getBusinessFaultList().toString());        
            showBusinessFaults(ex);            
            return "";
        }
        
        return "submitClaim";
    }
    
    public ReportClaimInput getReportClaimInput() {
        return reportClaimInput;
    }
    
    public void setReportClaimInput(ReportClaimInput reportClaimInput) {
        this.reportClaimInput = reportClaimInput;
    }

    public ReportClaimReturn getReportClaimReturn() {
        return reportClaimReturn;
    }

    public void setReportClaimReturn(ReportClaimReturn reportClaimReturn) {
        this.reportClaimReturn = reportClaimReturn;
    }

    public PropertyClaim getPropertyClaim() {
        return propertyClaim;
    }

    public void setPropertyClaim(PropertyClaim propertyClaim) {
        this.propertyClaim = propertyClaim;
    }

    public AutoClaim getAutoClaim() {
        return autoClaim;
    }

    public void setAutoClaim(AutoClaim autoClaim) {
        this.autoClaim = autoClaim;
    }
    
        
    public PersonSearchResult getPersonsSearchResult() {        
        return personIdentificationService.personSearch(personSearchCriteria);
    }
        
    public PersonSearchCriteria getPersonSearchCriteria() {        
        return personSearchCriteria;
    }
        
    public ClaimSearchResult getClaimSearchResult() {        
        return claimIdentificationService.claimSearch(claimSearchCriteria);
    }
        
    public ClaimSearchCriteria getClaimSearchCriteria() {        
        return claimSearchCriteria;
    }    
    
        
    public Map<String,Boolean> getYesNoItems() {
		return yesNoItems;
    }    
    
    public ClaimType[] getClaimTypes() {
        return ClaimType.values();
    }    
    
    public List<LossType> getLossTypList() {
        return lookupService.getLossTypeList();
    }        
    
    private void showBusinessFaults(BusinessException ex) {
         // Show validation errors as faces error messages
        for(BusinessFault fault: ex.getFaultInfo().getBusinessFaultList()) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, fault.getMessage() , null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);    
        }
        
    }
    
    private static final Map<String,Boolean> yesNoItems;
    
    static {
		yesNoItems = new LinkedHashMap<String,Boolean>();		
		yesNoItems.put("Yes", Boolean.TRUE);
		yesNoItems.put("No", Boolean.FALSE);
    }    
}
