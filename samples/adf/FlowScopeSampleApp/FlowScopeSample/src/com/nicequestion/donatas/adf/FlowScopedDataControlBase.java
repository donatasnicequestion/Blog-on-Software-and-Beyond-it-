package com.nicequestion.donatas.adf;

import oracle.adf.view.rich.context.AdfFacesContext;

/** Base generic implementation of ADF flow scoped bean data control.
 *
 * Note: specific design and naming convention is applied:
 * the type T must be defined as managed bean (name flowController, scope pageFlow) 
 * in a bounded task flow definition.
 * 
 * Example definition for the type com.nicequestion.donatas.controller.SampleFlowController:
 * 
 *     <managed-bean-name>flowController</managed-bean-name>
 *     <managed-bean-class>com.nicequestion.donatas.controller.SampleFlowController</managed-bean-class>
 *     <managed-bean-scope>pageFlow</managed-bean-scope>
 * 
 * @param <T> the type of a flow scoped bean being exposed as data control
 * 
 * @author Donatas Valys
 */
public abstract class FlowScopedDataControlBase<T> {
    
    /** Name of the flow controller bean  */
    public static final String FLOW_CONTROLLER_BEAN = "flowController";
        
    public T getFlowController() {
        return (T)AdfFacesContext.getCurrentInstance().getPageFlowScope().get(FLOW_CONTROLLER_BEAN);        
    }       
}
