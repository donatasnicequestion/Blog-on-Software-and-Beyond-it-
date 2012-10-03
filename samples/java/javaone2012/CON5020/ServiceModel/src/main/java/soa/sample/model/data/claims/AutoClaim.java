package soa.sample.model.data.claims;

import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import soa.sample.model.data.Address;
import soa.sample.model.data.persons.Person;
import soa.sample.model.validation.groups.New;
import soa.sample.model.validation.groups.Update;

public class AutoClaim implements Serializable {
    
    @Null(groups = New.class)
    @NotNull(groups = Update.class)    
    private Long id;
    
    @NotNull
    private Boolean driverIsReporter;
        
    private Person driver;    
    
    @NotNull
    @Valid
    private Address accidentAddress;

    @NotNull
    @Size(min=2, max = 200)
    private String verhicle;    
    
    @NotNull
    private String plateNumber;

    public Boolean getDriverIsReporter() {
        return driverIsReporter;
    }

    public void setDriverIsReporter(Boolean driverIsReporter) {
        this.driverIsReporter = driverIsReporter;
    }

    public Person getDriver() {
        return driver;
    }

    public void setDriver(Person driver) {
        this.driver = driver;
    }

    public Address getAccidentAddress() {
        return accidentAddress;
    }

    public void setAccidentAddress(Address accidentAddress) {
        this.accidentAddress = accidentAddress;
    }

    public String getVerhicle() {
        return verhicle;
    }

    public void setVerhicle(String verhicle) {
        this.verhicle = verhicle;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

  
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    
    
   
}
