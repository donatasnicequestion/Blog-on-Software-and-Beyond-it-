package soa.sample.model.data.persons;


import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import soa.sample.model.validation.constraints.PersonUniqueConstraint;
import soa.sample.model.validation.groups.New;
import soa.sample.model.validation.groups.Unique;
import soa.sample.model.validation.groups.Update;

@PersonUniqueConstraint(message="Person already exists",groups= {Unique.class})
public class Person implements Serializable {
    
    @Null(groups = New.class)
    @NotNull(groups = Update.class)
    private Long id;
        
    @Null(groups = New.class)    
    private Integer personalNumber;
    
    @Size(min=2, max = 10)  
    private String firstName;
    
    @NotNull
    @Size(min=2, max = 50)                            
    private String lastName;
    
    @Past
    private Date birthdate;
    
    @Size(max = 50)                
    private String phone;
        
    @Size(max = 250)                        
    private String email;
    
    @NotNull(groups = Update.class)
    private Long addressId;

    public Person() {
        super();
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getId() {
        return id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setAdressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getAdressId() {
        return addressId;
    }

    public void setPersonalNumber(Integer personalNumber) {
        this.personalNumber = personalNumber;
    }

    public Integer getPersonalNumber() {
        return personalNumber;
    }
                
}
