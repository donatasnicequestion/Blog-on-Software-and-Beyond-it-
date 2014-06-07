package com.nicequestion.donatas.adf.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import oracle.adf.model.adapter.bean.annotation.Id;

public class Employee {
    
    private Long id;
    private String name;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @NotNull
    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    @Size(min = 3,max=20)
    public String getName() {
        return name;
    }

    public Employee() {
        super();
    }
}
