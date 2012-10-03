package soa.sample.model.data.claims;


public enum ClaimType  {    
    Auto("AUTO","Auto"),Property("PROP","Property");
    
    private String code;             
    private String title;                 
    
    
    
    private ClaimType(String code,String title) {
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
