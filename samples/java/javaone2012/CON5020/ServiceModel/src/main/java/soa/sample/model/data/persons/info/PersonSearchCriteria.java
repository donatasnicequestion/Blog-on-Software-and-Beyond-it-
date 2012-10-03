package soa.sample.model.data.persons.info;

import java.io.Serializable;
import java.util.Date;


public class PersonSearchCriteria implements Serializable {
    
    private String number;
    private String firstName;
    private String lastName;
    private Date birthdate;
    private String phone;
    private String email;

    public void setNumber(String number) {
        this.number = number;
    }
    
    public String getNumber() {
        return number;
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
}
