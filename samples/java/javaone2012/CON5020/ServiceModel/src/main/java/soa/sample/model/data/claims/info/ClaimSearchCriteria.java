package soa.sample.model.data.claims.info;

import java.io.Serializable;
import java.util.Date;
import soa.sample.model.data.claims.ClaimType;


public class ClaimSearchCriteria implements Serializable {
    
    private String claimNumber;
    private Date accidentDate;        
    private String reporterPersonName;
    private ClaimType type;

    public void setClaimNumber(String claimNumber) {
        this.claimNumber = claimNumber;
    }

    public String getClaimNumber() {
        return claimNumber;
    }

    public void setAccidentDate(Date accidentDate) {
        this.accidentDate = accidentDate;
    }

    public Date getAccidentDate() {
        return accidentDate;
    }

    public void setReporterPersonName(String reporterPersonName) {
        this.reporterPersonName = reporterPersonName;
    }

    public String getReporterPersonName() {
        return reporterPersonName;
    }

    public void setType(ClaimType type) {
        this.type = type;
    }

    public ClaimType getType() {
        return type;
    }
}
