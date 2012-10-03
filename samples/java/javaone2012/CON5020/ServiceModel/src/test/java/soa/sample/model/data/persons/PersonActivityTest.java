/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soa.sample.model.data.persons;

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
import soa.sample.model.data.persons.activity.PersonActivityInput;
import soa.sample.model.services.activity.PersonActivityService;
import soa.sample.model.validation.groups.New;
import soa.sample.model.validation.utils.ValidationUtils;

/**
 *
 * @author dvalys
 */
public class PersonActivityTest {
    
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
    public void testPersonActivityInput() {
        PersonActivityInput activityInput = new PersonActivityInput();
        
        // Test default validation group
        Set<ConstraintViolation<PersonActivityInput>> constraintViolations = validator.validate(activityInput);
                        
        Assert.assertEquals( 2, constraintViolations.size() );        
        Map<String,String> violations = ValidationUtils.convertToMap(constraintViolations);        
                
        Assert.assertEquals("may not be null", violations.get("address"));
        Assert.assertEquals("may not be null", violations.get("person"));
        
        // Ok, provide missing data - person and address - and try again
        Person person = new Person();
        
        person.setFirstName("John");
        person.setLastName("Smith");        
        
        Address address = new Address();
        
        activityInput.setPerson(person);
        activityInput.setAddress(address);
        
        constraintViolations = validator.validate(activityInput,PersonActivityService.class);        
        violations = ValidationUtils.convertToMap(constraintViolations);        
        
        Assert.assertEquals( 1, constraintViolations.size() );                
        
        Assert.assertEquals("may not be null", violations.get("address.zip"));
        
        
        
    }
    
}
