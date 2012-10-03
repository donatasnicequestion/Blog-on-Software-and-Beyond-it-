/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soa.sample.services.info;

import java.util.List;
import javax.ejb.Stateless;
import soa.sample.database.DataStoreStub;
import soa.sample.model.data.Country;
import soa.sample.model.data.LossType;
import soa.sample.model.services.info.LookupService;

/**
 *
 * @author dvalys
 */
@Stateless(name="LookupService")
public class LookupServiceStub implements LookupService {
    
    private DataStoreStub dataStore = DataStoreStub.DATABASE;

    public List<LossType> getLossTypeList() {
        return dataStore.getLossTypeList();
    }

    public List<Country> getCountryList() {
        return dataStore.getCountryList();
    }
    
}
