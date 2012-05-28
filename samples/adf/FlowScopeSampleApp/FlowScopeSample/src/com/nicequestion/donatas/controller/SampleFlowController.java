package com.nicequestion.donatas.controller;

/** 
 * Sample implementation of flow controller to show a usage of 
 * it as flow scoped managed bean and flow scoped data control in ADF
 * 
 * @author Donatas Valys
 */
public class SampleFlowController {
    private String sampleValue;
               
    /* The method is aimed to show, that the same instance 
     * of controller is used, regardless of a way it gets consumed -
     * as a data control or managed bean.
     * */           
    public void doSomethingUseful() {
        sampleValue = sampleValue.toUpperCase();        
        System.out.println("Something useful performed on sampleValue:" + sampleValue);
    }
    
    public void setSampleValue(String testSampleValue) {
        this.sampleValue = testSampleValue;
    }

    public String getSampleValue() {
        return sampleValue;
    }    
}
