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
@Constraint(validatedBy = PersonUniqueValidator.class)
@Documented
public @interface PersonUniqueConstraint {
    
    String message() default "{soa.sample.model.validation.constraints.PersonUniqueConstraint}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    
    boolean performValidation() default false;
    
}
