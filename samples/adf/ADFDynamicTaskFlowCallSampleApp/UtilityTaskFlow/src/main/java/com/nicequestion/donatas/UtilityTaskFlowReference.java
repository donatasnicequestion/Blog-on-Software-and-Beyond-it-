package com.nicequestion.donatas;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.NoneScoped;

/** Implementation of the first part of 
 * module inteface as described in the blog post 
 * at http://donatas.nicequestion.com
 * 
 * @author Donatas Valys
 */
@ManagedBean(name = "utilityFlowReference")
@NoneScoped
public class UtilityTaskFlowReference {
    
    public static final String  FLOW_ID 
        = "/WEB-INF/utility-task-flow-definition.xml#utility-task-flow-definition";
    
    public String getFlowId() {
        return FLOW_ID;
    }
    
}
