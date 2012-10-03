package soa.sample.model.services.info;

import java.util.List;
import soa.sample.model.data.Country;
import soa.sample.model.data.LossType;

public interface LookupService {
    
    public List<LossType> getLossTypeList();   
    public List<Country> getCountryList();
        
}
