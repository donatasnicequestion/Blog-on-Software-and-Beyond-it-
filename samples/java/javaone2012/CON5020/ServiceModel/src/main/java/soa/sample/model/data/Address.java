package soa.sample.model.data;


import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import soa.sample.model.services.activity.ClaimActivityService;
import soa.sample.model.services.activity.PersonActivityService;
import soa.sample.model.validation.groups.New;
import soa.sample.model.validation.groups.Update;

public class Address implements Serializable {
    
    @Null(groups = New.class)
    @NotNull(groups = Update.class)
    private Long id;
        
    @Size(max = 200)        
    @NotNull(groups  = {ClaimActivityService.class})
    private String address;
    
    @Size(max = 50) 
    @NotNull(groups  = {ClaimActivityService.class})
    private String city;
    
    @Size(min = 5, max = 10)  
    @NotNull(groups  = {
        PersonActivityService.class,
        ClaimActivityService.class}
            )          
    private String zip;
    
    private Long countryId;

    /**
     * @param id
     */    
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getAddress() {
        return address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getZip() {
        return zip;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Long getCountryId() {
        return countryId;
    }
}
