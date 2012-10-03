package soa.sample.model.services.info;

import soa.sample.model.data.persons.Person;
import soa.sample.model.data.persons.info.PersonIdentificationCriteria;
import soa.sample.model.data.persons.info.PersonIdentificationResult;
import soa.sample.model.data.persons.info.PersonSearchCriteria;
import soa.sample.model.data.persons.info.PersonSearchResult;

public interface PersonIdentificationService {
    
    public PersonIdentificationResult identifyPerson(PersonIdentificationCriteria personIdentificationCriteria);
            
    public PersonSearchResult personSearch(PersonSearchCriteria personSearchCriteria);
    
    public Boolean personExists(Person person);
    
}
