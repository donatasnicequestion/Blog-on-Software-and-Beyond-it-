/*
 * JavaOne2012-CON5020-Using JSR 303 Bean Validation with the Common Data Model in SOA
 */
package soa.sample.model.validation.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import soa.sample.model.faults.BusinessException;
import soa.sample.model.faults.BusinessFault;
import soa.sample.model.faults.BusinessFaultInfo;


/**
 * Utility methods for bean validation
 * 
 * @author  Donatas Valys
 *
 */
public class ValidationUtils {

    public static javax.validation.Validator getValidator() {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        return factory.getValidator();
    }

       

    /** Validate object     
     * 
     * @param obj an object to validate
     * @throws BusinessException in case of constraint violations
     */
    public static void validate(Object obj) throws BusinessException {
        ValidationUtils.validate(obj,null);
    }       
    
   //public <T extends Object> Set<ConstraintViolation<T>> validate(T t, Class<?>[] types);
   
   public static  <T extends Object> void validate(T obj,Class<?>[] types) throws BusinessException {    
       
        Set<ConstraintViolation<T>> constraintViolations = getValidator().validate(obj,types);
        
        if(constraintViolations.size()>0) {
                                                         
            BusinessException ex = new BusinessException("Business rules violated!",new BusinessFaultInfo());
                                
            // Map constraint violations to BusinessFaults                               
            for(ConstraintViolation violation : constraintViolations) {
                                    
                BusinessFault fault = new BusinessFault();
            
                fault.setCode(violation.getConstraintDescriptor().getAnnotation().toString());
                fault.setMessage(violation.getPropertyPath().toString());
                fault.setDescription(violation.getMessage());
            
                ex.getFaultInfo().getBusinessFaultList().add(fault);
                        
            }
                                    
            throw ex;
        }  
        
   }   
   
   public static <T extends Object> Map<String,String> convertToMap(Set<ConstraintViolation<T>> constraintViolations) {
       Map<String,String> violations = new HashMap<String,String>();
        for(ConstraintViolation<T> violation: constraintViolations) {            
            violations.put(violation.getPropertyPath().toString(),  violation.getMessage());
        } 
        return violations;
   }
   
   public static <T extends Object> void throwBusinessException(Set<ConstraintViolation<T>> constraintViolations) throws BusinessException {
   
        if(constraintViolations.size()>0) {
                                                         
            BusinessException ex = new BusinessException("Business rules violated!",new BusinessFaultInfo());
                                
            // Map constraint violations to BusinessFaults                               
            for(ConstraintViolation violation : constraintViolations) {
                                    
                BusinessFault fault = new BusinessFault();
            
                fault.setCode(violation.getRootBean().getClass().getCanonicalName()+ "." + violation.getPropertyPath().toString());
                fault.setMessage(violation.getMessage());
                fault.setDescription(violation.getPropertyPath().toString());
            
                ex.getFaultInfo().getBusinessFaultList().add(fault);
                
                System.out.println(fault.getCode() + " " + fault.getDescription() + " " + fault.getMessage());
                        
            }
                                    
            throw ex;
        }    
   }

    public static Map<String, String> convertToMap(List<BusinessFault> businessFaultList) {
        Map<String,String> violations = new HashMap<String,String>();
        for(BusinessFault fault: businessFaultList) {            
            violations.put(fault.getDescription(),  fault.getMessage());
        } 
        return violations;
    }
}


