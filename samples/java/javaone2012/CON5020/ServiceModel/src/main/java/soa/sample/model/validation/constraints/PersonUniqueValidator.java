package soa.sample.model.validation.constraints;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.InjectionTarget;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import soa.sample.model.data.persons.Person;

/** JavaOne2012-CON5020-Using JSR 303 Bean Validation with the Common Data Model in SOA
 * 
 * Sample unique validation constraint to show validator delegate injection.
 * BeanValidation Validator as of Version 1.0 is not under the control of CDI yet
 * SPI is used to inject an implementation.
 *
 * @author Donatas Valys
 */
@Alternative
public class PersonUniqueValidator implements ConstraintValidator<PersonUniqueConstraint, Person> {
    
    @Inject ConstraintValidator<PersonUniqueConstraint, Person> delegateValidator;
                    
    public boolean isValid(Person obj,
            ConstraintValidatorContext constraintValidatorContext) {
             
        /* BeanValidation Validator as of 1.0 is not under the control of CDI - 
         * therefore we "inject" validator delegate here */
        delegateValidator = getDelegateInstance();
                        
        if (obj == null || delegateValidator == null ) { return true; }
        
        return delegateValidator.isValid(obj, constraintValidatorContext);                    
    }
    
    public void initialize(PersonUniqueConstraint constraint) {        
    }
    
    private ConstraintValidator<PersonUniqueConstraint, Person> getDelegateValidator() {
        return delegateValidator;
    }
    
    /* 
     * Use CDI facility to compensate missing injection support for Bean Validator
     * implementations - based on example provided at:
     * @See http://docs.jboss.org/weld/reference/latest/en-US/html/extend.html#d0e5035 
     */
    private ConstraintValidator<PersonUniqueConstraint, Person> getDelegateInstance()  {
    
        //get the BeanManager from JNDI
        BeanManager beanManager;
        try {
            beanManager = (BeanManager) new InitialContext().lookup("java:comp/BeanManager");
        } catch(NamingException ex)  {
            /* Execution outside container - BeanManager is not available */
            return null;
        }  
        //CDI uses an AnnotatedType object to read the annotations of a class
        AnnotatedType<PersonUniqueValidator> type = beanManager.createAnnotatedType(PersonUniqueValidator.class);
        //The extension uses an InjectionTarget to delegate instantiation, dependency injection 
        //and lifecycle callbacks to the CDI container
        InjectionTarget<PersonUniqueValidator> it = beanManager.createInjectionTarget(type);
        //each instance needs its own CDI CreationalContext
        CreationalContext ctx = beanManager.createCreationalContext(null);
        //instantiate the framework component and inject its dependencies
        PersonUniqueValidator instance = it.produce(ctx);  //call the constructor
        it.inject(instance, ctx);  //call initializer methods and perform field injection        
        it.dispose(instance);  //it is now safe to discard the instance
        ctx.release();  //clean up dependent objects           
        return instance.getDelegateValidator();
    }
}

