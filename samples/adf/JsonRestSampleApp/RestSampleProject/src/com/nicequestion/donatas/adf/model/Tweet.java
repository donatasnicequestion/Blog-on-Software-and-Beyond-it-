package com.nicequestion.donatas.adf.model;

import java.util.Date;

/**
 * Tweet - custom domain object  
 * 
 * @author Donatas Valys
 */
public class Tweet {
        
    private Date date;
    private String message;
    private Integer feeling;


    public Tweet() {
        super();
    }

    public void setMessage(String message) {    
        this.message = message;        
    }

    public String getMessage() {
        // a hack ( very bad - it modifies domain value inside a getter )
        // to replace hashes in twitter message text.
        // Otherwise the qery_part of URL GET request gets broken by # symbol
        return message.replace("#", "$");
    }
    
    public void setFeeling(Integer feeling) {
        this.feeling = feeling;
    }

    public Integer getFeeling() {
        return feeling;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }
}
