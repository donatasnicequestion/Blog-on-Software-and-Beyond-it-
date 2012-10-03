/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soa.sample.model.webserviceproxy;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import soa.sample.model.data.persons.Person;
import soa.sample.model.services.info.PersonIdentificationService;
import soa.sample.model.validation.constraints.PersonUniqueConstraint;

/**
 *
 * @author dvalys
 */
public class ValidatorProxyProducer {    
    @Inject PersonIdentificationService service;        
    @Produces ConstraintValidator<PersonUniqueConstraint, Person> getClaimIdentificationService() {
        return new ConstraintValidator<PersonUniqueConstraint, Person>() {
            public void initialize(PersonUniqueConstraint constraintAnnotation) {                
            }
            public boolean isValid(Person value, ConstraintValidatorContext context) {
                return !service.personExists(value);                
            }            
        };
    }            
    
}
