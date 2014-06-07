package com.nicequestion.donatas.adf.validate;

import javax.el.ValueExpression;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.BeanValidator;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

import oracle.jbo.uicli.binding.JUCtrlAttrsBinding;

/** 
 * Extended JSF 2.x bean property validator for Oracle ADF 
 * 
 * Integrates property validation of JSR 303 Bean Validation and 
 * POJO based ADF Data Controls
 * 
 * @author Donatas Valys
 * 
 * 
 * @link http://donatas.nicequestion.com
 * 
 */
@FacesValidator(value = "com.nicequestion.donatas.adf.validate.ADFBeanValidator",isDefault = true)
public class ADFBeanValidator extends BeanValidator {

    /** The regular expression pattern that is used to indentify ADF binding value expressions e.g.
     *  
     *  #{bindings.someAttribute.inputValue}  - Attribute value binding
     *  #{row.bindings.someAttribute.inputValue} -- Attribute value from a row of tree binding iterator
     *  
     */
    public static final String ADF_BINDINGS_INPUT_VALUE_EXPRESSION_PATTERN =
        "#\\{(\\w+[.])*bindings[.]\\w+[.]inputValue\\}";


    public ADFBeanValidator() {
        super();
    }

    /** Given faces UIcomponent with a value which contains ADF attribute value binding expression
     * create a new value expression referencing an appropriate property of java bean instance.
     *
     *
     * An example snippet in ADF page definition:
     * ...
     * <attributeValues IterBinding="someIterator" id="someAttribute">
     *   <AttrNames>
     *     <Item Value="someProperty"/>
     *   </AttrNames>
     * </attributeValues>
     * ...
     *
     * Given ADF value expression  #{bindings.someAttribute.inputValue}
     * creates the following bean value expression: 
     * 
     * #{bindings.someAttribute.currentRow.dataProvider.someProperty}
     *     *
     * @param facesContext @inheritDoc
     * @param component @inheritDoc
     *
     * @return Bean value expression for ADF binding value expression only, otherwise null
     */
    private ValueExpression createBeanValueExpression(FacesContext facesContext, UIComponent component) {

        /* Get value expression string for the attribute "value" */
        String value = component.getValueExpression("value").getExpressionString();

        if (value == null) {
            return null;
        }

        /* We are interested in ADF binding expressions only:
         * check if expression string matches  ADF_BINDING_INPUT_VALUE_EXPRESSION_PATTERN
         * e.g. #{bindings.xyz.inputValue}  */
        if (value.matches(ADF_BINDINGS_INPUT_VALUE_EXPRESSION_PATTERN)) {

            /* remove expression part .inputValue  */
            String adfValueExpression = value.replaceFirst("[.]inputValue", "");

            /* resolve  remaining expression 
             *  e.g. #{bindings.xyz} */
            JUCtrlAttrsBinding ctrlBinding =
                facesContext.getApplication().evaluateExpressionGet(facesContext, adfValueExpression, JUCtrlAttrsBinding.class);

            /* get attribute names from a page definition */
            String[] names = ctrlBinding.getAttributeNames();

            /* get a name of java bean property behind the first attribute name */
            String property = names[0];

            /* build a new value expression which resolves to java bean property value 
             * e.g. #{bindings.xyz.currentRow.dataProvider.xyz} */
            String beanValueExpression = value.replaceFirst("inputValue", "currentRow.dataProvider." + property);

            System.out.println("ADF value expression: " + value + " Bean value expression: " + beanValueExpression);

            return facesContext.getApplication().getExpressionFactory().createValueExpression(facesContext.getELContext(),
                                                                                              beanValueExpression, Object.class);

        }

        return null;

    }


    /**
     *
     * @param context @inheritDoc
     * @param component @inheritDoc
     * @param value @inheritDoc
     * @throws ValidatorException @inheritDoc
     */
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        /* Create bean property value expression given  ADF bindings inputValue expression */
        ValueExpression beanValueExpression = createBeanValueExpression(context, component);

        if (beanValueExpression != null) {

            /* backup original ADF bindings value expression */
            ValueExpression adfValueExpression = component.getValueExpression("value");

            try {

                /* replace  ADF bindings value expression with a bean value expression */
                component.setValueExpression("value", beanValueExpression);

                /* validate */
                super.validate(context, component, value);

            } finally {

                /* restore original ADF bindings value expression */
                component.setValueExpression("value", adfValueExpression);
            }
        }

    }
}

