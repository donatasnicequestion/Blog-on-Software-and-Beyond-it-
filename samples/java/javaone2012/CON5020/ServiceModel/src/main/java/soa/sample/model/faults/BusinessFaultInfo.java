package soa.sample.model.faults;


import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author dvalys
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class BusinessFaultInfo {
    
    @XmlElement(name = "BusinessFault")        
    private List<BusinessFault> businessFaultList;
    
    public BusinessFaultInfo() {
        super();
        businessFaultList = new ArrayList<BusinessFault>();
    }

    public void setBusinessFaultList(List<BusinessFault> businessFaultList) {
        this.businessFaultList = businessFaultList;
    }

    public List<BusinessFault> getBusinessFaultList() {
        return businessFaultList;
    }
        
}
