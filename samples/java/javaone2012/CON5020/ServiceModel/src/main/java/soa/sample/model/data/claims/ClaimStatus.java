package soa.sample.model.data.claims;

public enum ClaimStatus {    
    Submitted("0","Submitted"),
    Accepted("1","Accepted"),
    Approved("2","Approved"),
    Rejected("3","Rejected"),
    Closed("5","Closed");
    
    private String code;             
    private String title;                 
    
        
    private ClaimStatus(String code,String title) {
        this.code = code;
        this.title = title;
    }
    
    public String getCode() {
        return code;
    }
    
    public String getTitle() {
         return title;
    }
                
        
}
