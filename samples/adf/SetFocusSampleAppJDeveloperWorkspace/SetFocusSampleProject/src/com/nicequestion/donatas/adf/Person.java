package com.nicequestion.donatas.adf;

/* POJO used for the sample How To Set Initial Focus in ADF Faces */
public class Person {
    
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    
    public Person() {
        super();
        address = "this is readOnly test";
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
}
