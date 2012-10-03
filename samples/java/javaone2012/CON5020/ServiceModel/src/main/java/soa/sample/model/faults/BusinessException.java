package soa.sample.model.faults;

import javax.xml.ws.WebFault;

@WebFault(name = "BusinessException",targetNamespace="http://faults.model.sample.soa", faultBean = "soa.sample.model.faults.BusinessFaultInfo")
public class BusinessException extends Exception {

    private BusinessFaultInfo faultInfo;

    public BusinessException() {
        super();
        this.faultInfo = new BusinessFaultInfo();
    }

    
    public BusinessException(String string,BusinessFaultInfo faultInfo, Throwable throwable) {
        super(string, throwable);
        this.faultInfo = faultInfo;
    }

    public BusinessException(String string, BusinessFaultInfo faultInfo) {
        super(string);
        this.faultInfo = faultInfo;
    }
    
    public void setFaultInfo(BusinessFaultInfo faultInfo) {
        this.faultInfo = faultInfo;
    }

    public BusinessFaultInfo getFaultInfo() {
        return faultInfo;
    }
    
}
