/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soa.sample.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import soa.sample.database.DataStoreStub;
import soa.sample.model.data.persons.Person;
import soa.sample.model.validation.constraints.PersonUniqueConstraint;

/**
 *
 * @author dvalys
 */
public class PersonUniqueValidationStub implements ConstraintValidator<PersonUniqueConstraint, Person> {        
    
    private DataStoreStub dataStore = DataStoreStub.DATABASE;    
    
    public boolean isValid(Person person, ConstraintValidatorContext context) {                
        return !dataStore.personExists(person);                        
    }
    
    public void initialize(PersonUniqueConstraint constraintAnnotation) {        
    }    
    
}
