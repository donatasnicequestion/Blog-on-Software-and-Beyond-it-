package com.nicequestion.donatas.adf.service;

import com.nicequestion.donatas.adf.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*import oracle.adfinternal.controller.state.AdfcContext;
import oracle.adfinternal.controller.state.PageFlowStack;
import oracle.adfinternal.controller.state.PageFlowStackEntry;*/

public class HRService {
    
    private List<Employee> employeeList;
    

    public HRService() {
        super();
        employeeList = new ArrayList<Employee>();
        employeeList.add(new Employee() {{  setId(1L); setName("John");}});
        employeeList.add(new Employee() {{ setId(2L); setName("Smith");}});   
    }
        
    public List<Employee> getEmployeeList() {
      return employeeList;   
    }
    
    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}
