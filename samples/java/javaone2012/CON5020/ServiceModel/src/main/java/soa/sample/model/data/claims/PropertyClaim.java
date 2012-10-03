package soa.sample.model.data.claims;

import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import soa.sample.model.data.Address;
import soa.sample.model.validation.groups.New;
import soa.sample.model.validation.groups.Update;

public class PropertyClaim implements Serializable {
    
    @Null(groups = New.class)
    @NotNull(groups = Update.class)    
    private Long id;

    @NotNull
    @Valid
    private Address address;
    
    @NotNull
    private Long    lossTypeId; 
    
    @NotNull    
    private String  description;
    private Boolean totalLoss;
    
    public PropertyClaim() {
        super();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
 
    public void setLossTypeId(Long lostTypeId) {
        this.lossTypeId = lostTypeId;
    }

    public Long getLossTypeId() {
        return lossTypeId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setTotalLoss(Boolean totalLoss) {
        this.totalLoss = totalLoss;
    }

    public Boolean getTotalLoss() {
        return totalLoss;
    }
    
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }    
}
