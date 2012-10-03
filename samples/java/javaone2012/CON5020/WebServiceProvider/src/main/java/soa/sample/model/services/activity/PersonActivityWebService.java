/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soa.sample.model.services.activity;

import javax.ejb.EJB;
import javax.jws.WebParam;
import javax.jws.WebService;
import soa.sample.model.data.persons.activity.PersonActivityInput;
import soa.sample.model.data.persons.activity.PersonActivityReturn;
import soa.sample.model.faults.BusinessException;

/**
 *
 * @author dvalys
 */
@WebService(serviceName = "PersonActivityWebService")
public class PersonActivityWebService implements PersonActivityService {
    @EJB
    private PersonActivityService ejbRef;

    @Override    
    public PersonActivityReturn createPerson(@WebParam(name = "personActivityInput") PersonActivityInput personActivityInput) throws BusinessException {
        return ejbRef.createPerson(personActivityInput);
    }

    @Override
    public PersonActivityReturn updatePerson(@WebParam(name = "personActivityInput") PersonActivityInput personActivityInput) throws BusinessException {
        return ejbRef.updatePerson(personActivityInput);
    }
    
}
