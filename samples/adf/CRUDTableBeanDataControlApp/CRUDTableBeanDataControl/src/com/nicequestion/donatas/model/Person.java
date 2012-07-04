package com.nicequestion.donatas.model;

import java.util.Date;

/**
 *  Sample domain object 
 */
public class Person {
    
    private Integer number;
    private String firstName;
    private String lastName;
    private Date birthday;  
    
    public Person() {
        super();
    }
    
    /**
     * Copy constructor to provide clone funktionality
     *
     * @param person instance to clone
     */
    public Person(Person person) {        
        this.number = person.number;
        this.firstName = person.firstName;
        this.lastName = person.lastName;
        this.birthday = person.birthday;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
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

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getBirthday() {
        return birthday;
    }
    
}
