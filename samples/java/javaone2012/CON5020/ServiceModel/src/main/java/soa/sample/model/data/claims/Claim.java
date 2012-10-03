package soa.sample.model.data.claims;


import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Past;
import soa.sample.model.services.activity.ClaimActivityService;
import soa.sample.model.validation.constraints.ClaimConstraint;
import soa.sample.model.validation.groups.New;
import soa.sample.model.validation.groups.Update;

@ClaimConstraint
public class Claim implements Serializable {

    @Null(groups = New.class)
    @NotNull(groups = Update.class)
    private Long id;
    
    @NotNull
    private ClaimType type;
    
    @Valid
    private AutoClaim autoClaim;
    
    @Valid
    private PropertyClaim propertyClaim;            
    
    @Null(groups = New.class)   
    private Integer claimNumber;
    
    
    
    @Past
    private Date accidentDate;  
   
    @NotNull(groups = ClaimActivityService.class)
    private Long reporterPersonId;
        
    
    @NotNull
    private Boolean whitnesses;
    @NotNull
    private Boolean injuredPersons;
    @NotNull
    private Boolean officialsContacted;    
    
    @NotNull
    private ClaimStatus status = ClaimStatus.Submitted;
    
    private Date createdOn;

    

    public Claim() {
    }
        
    public void setAutoClaim(AutoClaim autoClaim) {
        this.autoClaim = autoClaim;
    }

    public AutoClaim getAutoClaim() {
        return autoClaim;
    }

    public void setPropertyClaim(PropertyClaim propertyClaim) {
        this.propertyClaim = propertyClaim;
    }

    public PropertyClaim getPropertyClaim() {
        return propertyClaim;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setClaimNumber(Integer claimNumber) {
        this.claimNumber = claimNumber;
    }
    
    public Integer getClaimNumber() {
        return claimNumber;
    }

    public void setAccidentDate(Date accidentDate) {
        this.accidentDate = accidentDate;
    }
    
    public Date getAccidentDate() {
        return accidentDate;
    }


    public void setType(ClaimType type) {
        this.type = type;
    }
    
    public ClaimType getType() {
        return type;
    }

    public void setReporterPersonId(Long reporterPersonId) {
        this.reporterPersonId = reporterPersonId;
    }

    public Long getReporterPersonId() {
        return reporterPersonId;
    }

    public void setWhitnesses(Boolean whitnesses) {
        this.whitnesses = whitnesses;
    }

    public Boolean getWhitnesses() {
        return whitnesses;
    }

    public void setInjuredPersons(Boolean injuredPersons) {
        this.injuredPersons = injuredPersons;
    }

    public Boolean getInjuredPersons() {
        return injuredPersons;
    }

    public void setOfficialsContacted(Boolean officialsContacted) {
        this.officialsContacted = officialsContacted;
    }

    public Boolean getOfficialsContacted() {
        return officialsContacted;
    }
    
/*    public void setClaimTypeTitle(String claimTitle) {        
        this.setType(ClaimType.valueOf(claimTitle));
    }

    public String getClaimTypeTitle() {
        return this.getType().getTitle();
    }*/
    
    public void setClaimStatusTitle(String claimStatus) {        
        this.setStatus(ClaimStatus.valueOf(claimStatus));
    }

    public String getClaimStatusTitle() {
        return this.getStatus().getTitle();
    }

    public void setStatus(ClaimStatus status) {
        this.status = status;
    }

    public ClaimStatus getStatus() {
        return status;
    }


    /**
     * Helper methods to workaround some problems dealing with native enum on a client (ADF binding) side
     * 
     * @return List of claim types from enum
     */
    public List<ClaimType> getClaimTypeList() {
        return Arrays.asList(ClaimType.values());        
    }
        
    public List<ClaimStatus> getClaimStatusList() {        
        return Arrays.asList(ClaimStatus.values());
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getCreatedOn() {
        return createdOn;
    }
}
