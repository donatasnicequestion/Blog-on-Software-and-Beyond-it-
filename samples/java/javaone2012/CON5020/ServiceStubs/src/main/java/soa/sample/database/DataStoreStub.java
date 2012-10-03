package soa.sample.database;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import soa.sample.model.data.Address;
import soa.sample.model.data.Country;
import soa.sample.model.data.LossType;
import soa.sample.model.data.claims.info.ClaimIdentificationResult;
import soa.sample.model.data.persons.Person;
import soa.sample.model.data.persons.info.PersonIdentificationResult;


/**
 * Very simple data store implementation using enum constant (java 5.0 and above) as a database
 *
 * {@link http://docs.oracle.com/javase/1.5.0/docs/guide/language/enums.html}
 */
public enum DataStoreStub {
    
      DATABASE;

      static Map<Long, PersonIdentificationResult> personIdentificationResultMap;
      
      static Map<Long, ClaimIdentificationResult> claimIdentificationResultMap;

      static Map<Long, Country> countryMap;
    
      static Map<Long, Address> addressMap;
    
      static Map<Long, LossType> lossTypeMap;
    
    
    private static Long idSequence;    
    
    static {
        initTestData();   
        idSequence = 10L;
    }
        

    public  synchronized Long getNextId() {
        idSequence++;
        return idSequence;
    }
   
    public  List<PersonIdentificationResult> getPersonIdentificationResultList() {
        return new ArrayList<PersonIdentificationResult>(personIdentificationResultMap.values());
    }
    
    public  Map<Long, PersonIdentificationResult> getPersonIdentificationResultMap() {
        return Collections.synchronizedMap(personIdentificationResultMap);
    }
    
    public List<ClaimIdentificationResult> getClaimIdentificationResultList() {
        return new ArrayList<ClaimIdentificationResult>(claimIdentificationResultMap.values());
    }    

    public Map<Long, ClaimIdentificationResult> getClaimIdentificationResultMap() {
        return Collections.synchronizedMap(claimIdentificationResultMap);
    }

    public  List<Country> getCountryList() {
        return new ArrayList<Country>(countryMap.values());
    }
    

    public  Map<Long, Country> getCountryMap() {
        return Collections.synchronizedMap(countryMap);
    }

    public  List<Address> getAddressList() {
        return new ArrayList<Address>(addressMap.values());        
    }

    public  Map<Long, Address> getAddressMap() {
        return Collections.synchronizedMap(addressMap);
    }

    public  List<LossType> getLossTypeList() {        
        return new ArrayList<LossType>(lossTypeMap.values());
    }
    
    public  Map<Long,LossType> getLossTypeMap() {        
        return Collections.synchronizedMap(lossTypeMap);
    }
        
    private static void initTestData() {
        
        //Loss types
        lossTypeMap = new HashMap<Long,LossType>();
        
        LossType lossType = new LossType();
        lossType.setId(1L);
        lossType.setCode("C1");
        lossType.setName("Water damage");
        lossTypeMap.put(lossType.getId(), lossType);
        
        lossType = new LossType();
        lossType.setId(2L);
        lossType.setCode("B1");
        lossType.setName("The roof is on fire");
        lossTypeMap.put(lossType.getId(), lossType);
        
        lossType = new LossType();
        lossType.setId(3L);
        lossType.setCode("E1");
        lossType.setName("Huge explosion");
        lossTypeMap.put(lossType.getId(), lossType);
        
        lossType = new LossType();
        lossType.setId(4L);
        lossType.setCode("XX");
        lossType.setName("My house accidently collapsed");
        lossTypeMap.put(lossType.getId(), lossType);
        

        addressMap = new HashMap<Long,Address>();

        //Some adresses
        Address address = new Address();

        address.setId(1L);
        address.setAddress("Longlane street 44");
        address.setCity("Alberwile");
        address.setZip("C56897");
        address.setCountryId(1L);
        
        addressMap.put(address.getId(), address);
        
        address = new Address();

        address.setId(2L);
        address.setAddress("Woodstreet 22");
        address.setCity("Smallwile");
        address.setZip("US56841");
        address.setCountryId(2L);        

        addressMap.put(address.getId(), address);

        //Some countries
        DataStoreStub.countryMap = new HashMap<Long,Country>();

        Country country = new Country();
        country.setId(1L);
        country.setCode("CA");
        country.setName("Canada");
        countryMap.put(country.getId(),country);

        country = new Country();
        country.setId(2L);
        country.setCode("US");
        country.setName("United States of America");
        
        countryMap.put(country.getId(),country);
        
        
        //Some persons
        personIdentificationResultMap = new HashMap<Long,PersonIdentificationResult>();

        PersonIdentificationResult result = new PersonIdentificationResult();

        Person person = new Person();
        person.setId(1L);
        person.setFirstName("John");
        person.setLastName("Smith");
        person.setPersonalNumber(1001);
        person.setBirthdate(new Date());
        person.setEmail("john.smith@email.com");
        person.setPhone("+123 (0) 4567895555");
        person.setAdressId(1L);
        
        addPerson(result, person);


        result = new PersonIdentificationResult();
                
        person = new Person();
        person.setId(2L);
        person.setFirstName("Adam");
        person.setLastName("Smith");
        person.setPersonalNumber(1002);
        person.setBirthdate(new Date());
        person.setEmail("adam.smith@email.com");
        person.setPhone("+5689 (0) 4567895555");
        person.setAdressId(2L);
        
        addPerson(result, person);
        
    

        result = new PersonIdentificationResult();
        
        person = new Person();
        person.setId(3L);
        person.setFirstName("Adam");
        person.setLastName("Blake");
        person.setPersonalNumber(1003);
        person.setBirthdate(new Date());
        person.setEmail("adam.blake@email.com");
        person.setPhone("+12 (0) 11111111");
        person.setAdressId(2L);
        
        addPerson(result, person);
        
        claimIdentificationResultMap = new HashMap<Long,ClaimIdentificationResult>();
    
            
    }
    
    public Boolean personExists(Person person) {
        if(person == null || person.getLastName() == null) return false;
                
        for(PersonIdentificationResult result: DATABASE.getPersonIdentificationResultList()) {
            if(result.getPerson().getLastName().equals(person.getLastName())) {
                return true;
            }
        }
        return false;
    }
    
    private  static void addPerson(PersonIdentificationResult result, Person person) {
        result.setPerson(person);
        result.setAddress(addressMap.get(person.getAdressId()));
        //result.setCountry(countryMap.get(addressMap.get(person.getAdressId()).getCountryId()));        
        personIdentificationResultMap.put(person.getId(),result );
    }
    
        
}
