/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soa.sample.model.services.info;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import soa.sample.model.data.persons.Person;
import soa.sample.model.data.persons.info.PersonIdentificationCriteria;
import soa.sample.model.data.persons.info.PersonIdentificationResult;
import soa.sample.model.data.persons.info.PersonSearchCriteria;
import soa.sample.model.data.persons.info.PersonSearchResult;

/**
 *
 * @author dvalys
 */
@WebService(serviceName = "PersonIdentificationWebService")
public class PersonIdentificationWebService implements PersonIdentificationService {
    @EJB
    private PersonIdentificationService ejbRef;

    @WebMethod(operationName = "identifyPerson")
    @Override
    public PersonIdentificationResult identifyPerson(@WebParam(name = "personIdentificationCriteria") PersonIdentificationCriteria personIdentificationCriteria) {
        return ejbRef.identifyPerson(personIdentificationCriteria);
    }

    @WebMethod(operationName = "personSearch")
    @Override
    public PersonSearchResult personSearch(@WebParam(name = "personSearchCriteria") PersonSearchCriteria personSearchCriteria) {
        return ejbRef.personSearch(personSearchCriteria);
    }

    @WebMethod(operationName = "personExists")
    @Override
    public Boolean personExists(Person person) {
        return ejbRef.personExists(person);
    }
    
}
