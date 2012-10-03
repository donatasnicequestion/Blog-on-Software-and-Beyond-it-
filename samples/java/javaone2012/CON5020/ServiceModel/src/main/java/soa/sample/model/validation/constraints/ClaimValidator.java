package soa.sample.model.validation.constraints;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import soa.sample.model.data.claims.Claim;
import soa.sample.model.data.claims.ClaimType;


/** JavaOne2012-CON5020-Using JSR 303 Bean Validation with the Common Data Model in SOA
 * 
 * Sample validator to show a validation on a type level.
 *
 * @author Donatas Valys
 */
public class ClaimValidator implements ConstraintValidator<ClaimConstraint, Claim> {

    @Override
    public void initialize(ClaimConstraint reportClaimInputConstraint) {
    }

    @Override
    public boolean isValid(Claim claim, ConstraintValidatorContext constraintValidatorContext) {
        // Do not validate null values 
        if (claim == null || claim.getType() == null) 
            return true;                                                       
        if (claim.getType().equals(ClaimType.Auto) && claim.getAutoClaim() == null) 
            return false;                
        if (claim.getType().equals(ClaimType.Property) && claim.getPropertyClaim() == null)
            return false;
        
        
        if (claim.getPropertyClaim() != null && claim.getAutoClaim() != null) {
            return false;
        }        
                                        
        return true;
    }

}
