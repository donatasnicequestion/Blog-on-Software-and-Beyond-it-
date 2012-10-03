/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soa.sample.services.activity;

import java.util.Set;
import javax.ejb.Stateless;
import javax.validation.ConstraintViolation;
import soa.sample.database.DataStoreStub;
import soa.sample.model.data.Address;
import soa.sample.model.data.persons.Person;
import soa.sample.model.data.persons.activity.PersonActivityInput;
import soa.sample.model.data.persons.activity.PersonActivityReturn;
import soa.sample.model.data.persons.info.PersonIdentificationResult;
import soa.sample.model.faults.BusinessException;
import soa.sample.model.services.activity.PersonActivityService;
import soa.sample.model.validation.groups.PersonActivityCreatePerson;
import soa.sample.model.validation.groups.PersonActivityUpdatePerson;
import soa.sample.model.validation.utils.ValidationUtils;

/**
 *
 * @author dvalys
 */
@Stateless(name = "PersonActivityService")
public class PersonActivityServiceStub implements PersonActivityService {

    private DataStoreStub dataStore = DataStoreStub.DATABASE;

    public PersonActivityReturn createPerson(PersonActivityInput personCreateInput) throws BusinessException {

        Set<ConstraintViolation<PersonActivityInput>> constraintViolations = 
                ValidationUtils.getValidator().validate(personCreateInput, PersonActivityCreatePerson.class);

        ValidationUtils.throwBusinessException(constraintViolations);

        PersonIdentificationResult personIdentificationResult = new PersonIdentificationResult();
        Person person = personCreateInput.getPerson();

        person.setId(dataStore.getNextId());
        person.setPersonalNumber(person.getId().intValue() * 10);

        personIdentificationResult.setPerson(person);

        Address address = personCreateInput.getAddress();
        address.setId(dataStore.getNextId());
        personIdentificationResult.setAddress(address);
        person.setAdressId(address.getId());

        //Country country = dataStore.getCountryMap().get(address.getCountryId());
        //personIdentificationResult.setCountry(country);

        dataStore.getAddressMap().put(address.getId(), address);
        dataStore.getPersonIdentificationResultMap().put(person.getId(), personIdentificationResult);


        PersonActivityReturn serviceReturn = new PersonActivityReturn();
        serviceReturn.setPersonId(person.getId());
        serviceReturn.setPersonalNumber(person.getPersonalNumber());

        return serviceReturn;
    }

    public PersonActivityReturn updatePerson(PersonActivityInput personCreateInput) throws BusinessException {

        Set<ConstraintViolation<PersonActivityInput>> constraintViolations = 
                ValidationUtils.getValidator().validate(personCreateInput, PersonActivityUpdatePerson.class);

        ValidationUtils.throwBusinessException(constraintViolations);

        PersonIdentificationResult personIdentificationResult = new PersonIdentificationResult();
        Person person = personCreateInput.getPerson();
        personIdentificationResult.setPerson(person);

        Address address = personCreateInput.getAddress();
        personIdentificationResult.setAddress(address);

//        Country country = dataStore.getCountryMap().get(address.getCountryId());
//        personIdentificationResult.setCountry(country);

        dataStore.getAddressMap().put(address.getId(), address);
        dataStore.getPersonIdentificationResultMap().put(person.getId(), personIdentificationResult);

        PersonActivityReturn serviceReturn = new PersonActivityReturn();
        serviceReturn.setPersonId(person.getId());
        serviceReturn.setPersonalNumber(person.getPersonalNumber());

        return serviceReturn;
    }
}
