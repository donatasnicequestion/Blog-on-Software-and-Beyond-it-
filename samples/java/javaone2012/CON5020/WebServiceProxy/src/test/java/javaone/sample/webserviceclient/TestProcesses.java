/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaone.sample.webserviceclient;

import java.util.Date;
import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import soa.sample.model.data.Address;
import soa.sample.model.data.LossType;
import soa.sample.model.data.claims.Claim;
import soa.sample.model.data.claims.ClaimStatus;
import soa.sample.model.data.claims.ClaimType;
import soa.sample.model.data.claims.PropertyClaim;
import soa.sample.model.data.claims.activity.ClaimActivityInput;
import soa.sample.model.data.claims.activity.process.ReportClaimInput;
import soa.sample.model.data.claims.activity.process.ReportClaimReturn;
import soa.sample.model.data.claims.info.ClaimIdentificationResult;
import soa.sample.model.data.persons.Person;
import soa.sample.model.data.persons.activity.PersonActivityInput;
import soa.sample.model.faults.BusinessException;
import soa.sample.model.processes.ReportClaimProcess;
import soa.sample.model.processes.ReportClaimProcessWebService_Service;
import soa.sample.model.services.info.ClaimIdentificationService;
import soa.sample.model.services.info.ClaimIdentificationWebService_Service;


/**
 *
 * @author dvalys
 */
public class TestProcesses  {
    
    private static ReportClaimProcess process;
    private static ClaimIdentificationService service;
    
   
    @BeforeClass
    public static void setUp() throws Exception {        
        process = new ReportClaimProcessWebService_Service().getReportClaimProcessWebServicePort();
        service = new ClaimIdentificationWebService_Service().getClaimIdentificationWebServicePort();
        
    }
    
    @Test
    public void testReportClaim() throws BusinessException {
        
        ReportClaimInput processInput = new ReportClaimInput();
        
        PersonActivityInput personActivityInput = new PersonActivityInput();
        ClaimActivityInput claimActivityInput = new ClaimActivityInput();
        
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Smith"+System.currentTimeMillis());
        person.setEmail("email@address.com");                
        personActivityInput.setPerson(person);
        
        Address address = new Address();
        address.setCity("San Francisco");
        address.setZip("123456");
        address.setAddress("45th Street");
        personActivityInput.setAddress(address);
        
        Claim claim = new Claim();
        claim.setInjuredPersons(Boolean.TRUE);
        claim.setWhitnesses(Boolean.TRUE);
        claim.setOfficialsContacted(Boolean.TRUE);
        claim.setType(ClaimType.Property);
        claim.setReporterPersonId(Long.MIN_VALUE);

        claim.setAccidentDate(new Date());
                                       
        PropertyClaim propertyClaim = new PropertyClaim();
        Address address2 = new Address();
        address2.setCity("San Francisco");
        address2.setZip("123456");
        address2.setAddress("45th Street");
        propertyClaim.setAddress(address2);
        propertyClaim.setDescription("Some description of incident");
        propertyClaim.setLossTypeId(1L);
        
        claim.setPropertyClaim(propertyClaim);
        claimActivityInput.setClaim(claim);
        
        processInput.setPersonActivityInput(personActivityInput);
        processInput.setClaimActivityInput(claimActivityInput);

        ReportClaimReturn processReturn = process.process(processInput);
        
        Assert.assertNotNull(processReturn);
        Assert.assertNotNull(processReturn.getPersonActivityReturn());
        Assert.assertNotNull(processReturn.getPersonActivityReturn().getPersonId());   
        
        Assert.assertNotNull(processReturn.getClaimActivityReturn());
        Assert.assertNotNull(processReturn.getClaimActivityReturn().getClaimId());
        Assert.assertNotNull(processReturn.getClaimActivityReturn().getClaimNumber());
        Assert.assertNotNull(processReturn.getClaimActivityReturn().getClaimStatus());
        Assert.assertEquals(ClaimStatus.Submitted,processReturn.getClaimActivityReturn().getClaimStatus());
        
        
        // Test if our claim submitted can be identified by identification service
        ClaimIdentificationResult claimIdentificationResult  = 
                service.identifyClaimById(processReturn.getClaimActivityReturn().getClaimId());
        
        Assert.assertNotNull(claimIdentificationResult);
        Assert.assertNotNull(claimIdentificationResult.getClaim());
        Assert.assertNotNull(claimIdentificationResult.getReporterPerson());
        
        Assert.assertEquals(claimIdentificationResult.getReporterPerson().getId(),processReturn.getPersonActivityReturn().getPersonId() );
        Assert.assertEquals(claimIdentificationResult.getClaim().getId(),processReturn.getClaimActivityReturn().getClaimId());
        
        
        // Test if a person reporting a claim can be identified by identification service
        
        
    }
        
}
