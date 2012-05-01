package com.nicequestion.donatas.adf;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * JSF managed bean used in UserRegistrationApp sample
 * to show a usage of ADF JMX Data Control for
 * Weblogic Server embedded LDAP  access over JMX
 * and to check an JSF2 annotation based managed
 * bean configuration in JDeveloper R2 (design time) and ADF
 * 
 * @author Donatas Valys
 */
@ManagedBean
@ViewScoped
public class UserBean {

    private String username;
    private String password;

    public UserBean() {
        super();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

}
