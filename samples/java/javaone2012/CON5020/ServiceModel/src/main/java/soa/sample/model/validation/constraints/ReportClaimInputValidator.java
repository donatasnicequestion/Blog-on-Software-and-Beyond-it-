package soa.sample.model.validation.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import soa.sample.model.data.claims.ClaimType;
import soa.sample.model.data.claims.activity.process.ReportClaimInput;

/** JavaOne2012-CON5020-Using JSR 303 Bean Validation with the Common Data Model in SOA
 * 
 * Validator to show a validation on a business process level
 * 
 * @author Donatas Valys
 */
public class ReportClaimInputValidator implements ConstraintValidator<ReportClaimInputConstraint, ReportClaimInput> {

    @Override
    public void initialize(ReportClaimInputConstraint reportClaimInputConstraint) {
    }

    @Override
    public boolean isValid(ReportClaimInput reportClaimInput, ConstraintValidatorContext constraintValidatorContext) {

        // Do not validate null values 
        if (reportClaimInput == null || reportClaimInput.getClaimActivityInput() == null ||
            reportClaimInput.getPersonActivityInput() == null ||
            reportClaimInput.getClaimActivityInput().getClaim() == null ||
            reportClaimInput.getClaimActivityInput().getClaim().getType() == null || 
            reportClaimInput.getPersonActivityInput() == null || 
            reportClaimInput.getPersonActivityInput().getPerson().getLastName() == null 
            ) {
            return true;
        }
        
        // Business rule:        
        // "Do not accept auto claims from a persons whose lastname starts wiht 'X' (bad experience) 
        //
        // The business rule is just intended to show, that on a business process level a validation logic can 
        // access all process data. Therefore more complex and process-specific business rules can be implemented here
        if (reportClaimInput.getClaimActivityInput().getClaim().getType() == ClaimType.Auto &&
            reportClaimInput.getPersonActivityInput().getPerson().getLastName().toUpperCase().startsWith("X")) {
            return false;
        }
        
        return true;
    }

}
