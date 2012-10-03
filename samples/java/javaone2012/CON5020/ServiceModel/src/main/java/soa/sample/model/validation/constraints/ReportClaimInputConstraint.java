package soa.sample.model.validation.constraints;

import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;


@Target( { METHOD, FIELD, ANNOTATION_TYPE, TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = ReportClaimInputValidator.class)
@Documented
public @interface ReportClaimInputConstraint {
    
    String message() default "{soa.sample.model.validation.constraints.ReportClaimInputConstraint}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
