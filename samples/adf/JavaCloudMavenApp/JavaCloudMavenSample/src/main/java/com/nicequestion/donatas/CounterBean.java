package com.nicequestion.donatas;

import java.util.Date;

import javax.annotation.PostConstruct;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped
public class CounterBean {
    
    private Date startDate;
    private int count = 0;
    
    public CounterBean() {
        super();
    }
    
    @PostConstruct
    private void init() {
        startDate = new Date();
    }


    public Date getStartDate() {
        return startDate;
    }

    synchronized public int getCount() {
        return ++count;
    }
}
