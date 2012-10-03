/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soa.sample.model.data.persons;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import soa.sample.model.validation.groups.New;
import soa.sample.model.validation.groups.Unique;
import soa.sample.model.validation.groups.Update;
import soa.sample.model.validation.utils.ValidationUtils;

/**
 *
 * @author dvalys
 */
public class PersonTest {
    
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
    public void testNew() {
                
        Person person = new Person();
        person.setId(Long.MIN_VALUE);
        
        Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person, New.class);
                        
        Assert.assertEquals( 1, constraintViolations.size() );
        
        Map<String,String> violations = ValidationUtils.convertToMap(constraintViolations);
        
        Assert.assertEquals("must be null", violations.get("id"));        
        
    }
    
    @Test        
    public void testUpdate() {
        Person person = new Person();
                
        Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person, Update.class);
                
        Assert.assertEquals( 2, constraintViolations.size() );
        
        Map<String,String> violations = ValidationUtils.convertToMap(constraintViolations);
             
         Assert.assertEquals("may not be null", violations.get("id"));        
         Assert.assertEquals("may not be null", violations.get("addressId"));        
                         
        
    }        
    
    @Test
    public void testDefault() {
        Person person = new Person();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        person.setBirthdate(cal.getTime());
        
        Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);

        Assert.assertEquals( 2, constraintViolations.size() );
                
        Map<String,String> violations = ValidationUtils.convertToMap(constraintViolations);
                                                
        Assert.assertEquals("may not be null", violations.get("lastName"));        
        Assert.assertEquals("must be in the past", violations.get("birthdate"));        
                        
    }
    
    @Test
    public void testDefaultSize() {
        Person person = new Person();
        
        person.setFirstName(null);
        person.setLastName(null);
                        
        Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);
        Assert.assertEquals( 1, constraintViolations.size() );
        
        Map<String,String> violations = ValidationUtils.convertToMap(constraintViolations);
                                
        Assert.assertEquals("may not be null", violations.get("lastName"));        
                
    }    
    
    @Test
    public void testNewServer() {
                
        Person person = new Person();
        person.setId(Long.MIN_VALUE);
        
        Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person, New.class,Unique.class);
                        
        Assert.assertEquals( 1, constraintViolations.size() );
        
        Map<String,String> violations = ValidationUtils.convertToMap(constraintViolations);
        
        Assert.assertEquals("must be null", violations.get("id"));        
        
    }    
        
}
