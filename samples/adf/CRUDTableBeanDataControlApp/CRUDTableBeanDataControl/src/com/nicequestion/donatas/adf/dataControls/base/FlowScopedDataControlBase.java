package com.nicequestion.donatas.adf.dataControls.base;

import oracle.adf.view.rich.context.AdfFacesContext;

/** Base generic implementation of ADF flow scoped bean data control.
 *
 * Note: specific design and naming convention is applied:
 * the type T must be defined as managed bean (name flowController, scope pageFlow) 
 * in a bounded task flow definition.
 * 
 * @see http://donatas.nicequestion.com/2012/05/how-to-align-managed-bean-scope-and.html
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
