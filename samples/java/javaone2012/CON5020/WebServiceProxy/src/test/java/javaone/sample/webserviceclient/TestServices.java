/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaone.sample.webserviceclient;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import soa.sample.model.data.Address;
import soa.sample.model.data.persons.Person;
import soa.sample.model.data.persons.activity.PersonActivityInput;
import soa.sample.model.data.persons.activity.PersonActivityReturn;
import soa.sample.model.faults.BusinessException;
import soa.sample.model.services.activity.PersonActivityService;
import soa.sample.model.services.activity.PersonActivityWebService_Service;
import soa.sample.model.validation.utils.ValidationUtils;

/**
 *
 * @author dvalys
 */
public class TestServices {
    
    private static PersonActivityService service;
    
  
    @BeforeClass
    public static void setUp() throws Exception {
       
        service = new PersonActivityWebService_Service().getPersonActivityWebServicePort();         
        
    }
    
    @Test
    public void testCreatePerson() {
                
        PersonActivityInput personActivityInput = new PersonActivityInput();
        Person person = new Person();
        Address address = new Address();
        personActivityInput.setPerson(person);
        personActivityInput.setAddress(address);        
        
        
        PersonActivityReturn ret;
        try {
            ret = service.createPerson(personActivityInput);
            Assert.assertTrue("Business exception expected", false);
        } catch (BusinessException ex) {
            
            Assert.assertNotNull(ex.getFaultInfo());
            Assert.assertNotNull(ex.getFaultInfo().getBusinessFaultList());
            Assert.assertEquals(2, ex.getFaultInfo().getBusinessFaultList().size());
           
            Map<String,String> violations = ValidationUtils.convertToMap(ex.getFaultInfo().getBusinessFaultList());
            
            Logger.getLogger(TestServices.class.getName()).info(violations.keySet().toString());
             
            org.junit.Assert.assertEquals("may not be null", violations.get("person.lastName"));        
            org.junit.Assert.assertEquals("may not be null", violations.get("address.zip"));
            
            personActivityInput.getPerson().setLastName("Smith"+System.currentTimeMillis());
            personActivityInput.getAddress().setZip("123456");
            
            try {
                ret = service.createPerson(personActivityInput);
                
                Assert.assertNotNull(ret);
                Assert.assertNotNull(ret.getPersonId());
                Assert.assertNotNull(ret.getPersonalNumber());
                
                //Second call with a same data to test unique validation
                try {
                
                    ret = service.createPerson(personActivityInput);
                    Assert.assertTrue("BusinessException expected",false);      
                
                } catch(BusinessException ex2) {
                        Assert.assertNotNull(ex2.getFaultInfo());
                        Assert.assertNotNull(ex2.getFaultInfo().getBusinessFaultList());
                        Assert.assertEquals(1, ex2.getFaultInfo().getBusinessFaultList().size());                    
                }    
                
                        
            } catch (BusinessException ex1) {
                Assert.assertTrue("No BusinessException expected",false);                 
            }
        }
        
    }
    
    @Test
    public void testUpdatePerson() {
        
        PersonActivityInput input = new PersonActivityInput();
        
        try {
            PersonActivityReturn ret =  service.updatePerson(input);        
        } catch (BusinessException ex) {            
            Logger.getLogger(TestServices.class.getName()).log(Level.SEVERE, null, ex.getFaultInfo().getBusinessFaultList().get(0).getCode());        
        }
        
        
    }
}
