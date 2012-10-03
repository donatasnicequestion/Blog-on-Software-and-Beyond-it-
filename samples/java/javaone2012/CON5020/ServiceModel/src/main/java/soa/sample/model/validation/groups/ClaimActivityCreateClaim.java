package soa.sample.model.validation.groups;

import javax.validation.groups.Default;
import soa.sample.model.services.activity.ClaimActivityService;

/** JavaOne2012-CON5020-Using JSR 303 Bean Validation with the Common Data Model in SOA
 * 
 * Validation group for validation constrains, specific to service ClaimActivity, operation CreateClaim
 * 
 * @author Donatas Valys
 */
public interface ClaimActivityCreateClaim extends Default, New, ClaimActivityService {
    
}
