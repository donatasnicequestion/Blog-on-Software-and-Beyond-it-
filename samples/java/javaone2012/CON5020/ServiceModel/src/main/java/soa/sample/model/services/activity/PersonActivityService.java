package soa.sample.model.services.activity;

import soa.sample.model.data.persons.activity.PersonActivityInput;
import soa.sample.model.data.persons.activity.PersonActivityReturn;
import soa.sample.model.faults.BusinessException;


public interface PersonActivityService {
    
    public PersonActivityReturn createPerson
            (PersonActivityInput personActivityInput) 
                throws BusinessException;    
    
    public PersonActivityReturn updatePerson(PersonActivityInput personActivityInput) throws BusinessException;
             
}
