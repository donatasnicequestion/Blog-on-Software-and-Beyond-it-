/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soa.sample.model.data.persons;

import com.sun.istack.logging.Logger;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import soa.sample.model.data.Address;
import soa.sample.model.data.claims.Claim;
import soa.sample.model.data.claims.ClaimType;
import soa.sample.model.data.claims.PropertyClaim;
import soa.sample.model.data.claims.activity.ClaimActivityInput;
import soa.sample.model.data.persons.activity.PersonActivityInput;
import soa.sample.model.services.activity.ClaimActivityService;
import soa.sample.model.services.activity.PersonActivityService;
import soa.sample.model.validation.groups.New;
import soa.sample.model.validation.utils.ValidationUtils;

/**
 *
 * @author dvalys
 */
public class ClaimActivityTest {
    
private static Validator validator;
   
    /**
     *
     */
    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    
    @Test
    public void testClaimActivityInput() {
        ClaimActivityInput activityInput = new ClaimActivityInput();
        
        // Test default validation group
        Set<ConstraintViolation<ClaimActivityInput>> constraintViolations = validator.validate(activityInput);
                        
        Assert.assertEquals( 1, constraintViolations.size() );        
        Map<String,String> violations = ValidationUtils.convertToMap(constraintViolations);        
                
        Assert.assertEquals("may not be null", violations.get("claim"));
                
        // Ok, provide missing data - claim - and try again
        Claim claim = new Claim();        
        activityInput.setClaim(claim);
        constraintViolations = validator.validate(activityInput);
        Assert.assertEquals( 4, constraintViolations.size() );        
        violations = ValidationUtils.convertToMap(constraintViolations);    
        
        Logger.getLogger(ClaimActivityTest.class).info(violations.keySet().toString());
                
        Assert.assertEquals( "may not be null", violations.get("claim.injuredPersons") );                
        Assert.assertEquals( "may not be null", violations.get("claim.whitnesses") );                
        Assert.assertEquals( "may not be null", violations.get("claim.officialsContacted") );                        
        Assert.assertEquals( "may not be null", violations.get("claim.type") );                        
        
        // Validate service level constraints    
        constraintViolations = validator.validate(activityInput,ClaimActivityService.class);
        Assert.assertEquals( 1, constraintViolations.size() );        
        violations = ValidationUtils.convertToMap(constraintViolations);    
        
        Assert.assertEquals( "may not be null", violations.get("claim.reporterPersonId") );                        
        
        // Fix missing values 
        claim.setInjuredPersons(Boolean.TRUE);
        claim.setWhitnesses(Boolean.TRUE);
        claim.setOfficialsContacted(Boolean.TRUE);
        claim.setType(ClaimType.Property);
        claim.setReporterPersonId(Long.MIN_VALUE);
        
        constraintViolations = validator.validate(activityInput);
        violations = ValidationUtils.convertToMap(constraintViolations); 
        Logger.getLogger(ClaimActivityTest.class).info(violations.keySet().toString());
        Assert.assertEquals( 1, constraintViolations.size() );                
        Assert.assertEquals( "{soa.sample.model.validation.constraints.ClaimConstraint}", violations.get("claim") );
        
        // Submit property claim
        claim.setAccidentDate(new Date());
       
                                
        PropertyClaim propertyClaim = new PropertyClaim();
        Address address = new Address();
        propertyClaim.setAddress(address);
        
        claim.setPropertyClaim(propertyClaim);
                        
        activityInput.setClaim(claim);
                        
        constraintViolations = validator.validate(activityInput,ClaimActivityService.class);        
        Assert.assertEquals( 3, constraintViolations.size() );                                
        
        violations = ValidationUtils.convertToMap(constraintViolations);        
        
        Assert.assertEquals("may not be null", violations.get("claim.propertyClaim.address.address"));
        Assert.assertEquals("may not be null", violations.get("claim.propertyClaim.address.city"));
        Assert.assertEquals("may not be null", violations.get("claim.propertyClaim.address.zip"));
                        
    }
    
}
