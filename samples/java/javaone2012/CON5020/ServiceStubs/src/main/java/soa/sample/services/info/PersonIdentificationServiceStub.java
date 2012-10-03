/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soa.sample.services.info;

import javax.ejb.Stateless;
import soa.sample.database.DataStoreStub;
import soa.sample.model.data.persons.Person;
import soa.sample.model.data.persons.info.PersonIdentificationCriteria;
import soa.sample.model.data.persons.info.PersonIdentificationResult;
import soa.sample.model.data.persons.info.PersonSearchCriteria;
import soa.sample.model.data.persons.info.PersonSearchResult;
import soa.sample.model.services.info.PersonIdentificationService;

/**
 *
 * @author dvalys
 */
@Stateless(name="PersonIdentificationService")
public class PersonIdentificationServiceStub implements PersonIdentificationService {
    
    private DataStoreStub dataStore = DataStoreStub.DATABASE;

    public PersonIdentificationResult identifyPerson(PersonIdentificationCriteria personIdentificationCriteria) {
        return dataStore.getPersonIdentificationResultMap().get(personIdentificationCriteria.getId());
    }

    public PersonSearchResult personSearch(PersonSearchCriteria personSearchCriteria) {
        PersonSearchResult result = new PersonSearchResult();        
        result.setPersonIdentificationResultList(dataStore.getPersonIdentificationResultList());        
        return result;
    }

    public Boolean personExists(Person person) {
        return dataStore.personExists(person); 
    }
    
}
