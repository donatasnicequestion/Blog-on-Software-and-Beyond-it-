package com.nicequestion.donatas.adf.extensions;

import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.component.behavior.ClientBehaviorBase;
import javax.faces.component.behavior.ClientBehaviorContext;
import javax.faces.component.behavior.FacesBehavior;
import javax.faces.context.FacesContext;

import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;


/**
 *  Custom tag implementation to set initial focus
 *  on ADF (Trinidad) Faces EditableValueHolder based component 
 *
 * @author Donatas Valys
 *  
 */
@FacesBehavior("donatas.nicequestion.com.adf.extensions.initialFocus")
public class SetInitialFocusBehavior extends ClientBehaviorBase {


    /** (ADF) Faces component attributes to handle special cases
     * where setting the initial focus doesn't make sence
     */
    private static final String ATTR_READ_ONLY = "readOnly";
    private static final String ATTR_VISIBLE = "visible";
    private static final String ATTR_DISABLED = "disabled";

    /** Custom marker attribute.
     * Marks initial focus as already set to handle special cases:
     *   having multiple initial focus tags in the same view
     *   partial refresh
     */
    private static final String ATTR_INITIAL_FOCUS_SET = "initialFocusSet";

    public SetInitialFocusBehavior() {
        super();
    }

    @Override
    public String getScript(ClientBehaviorContext clientBehaviorContext) {

        FacesContext context = clientBehaviorContext.getFacesContext();

        // Get the instance of Faces component for the tag
        UIComponent component = clientBehaviorContext.getComponent();

        //Check if initial focus was already initialy set on component on a same view
        if (component.getAttributes().containsKey(ATTR_INITIAL_FOCUS_SET)) {
            // Put the marker initialFocusSet into request to prevent cursor 
            // jumping to the next component in case  multiple initialFocusTags are used
            context.getExternalContext().getRequestMap().put(ATTR_INITIAL_FOCUS_SET, Boolean.TRUE);
            return "";
        }

        // Check if (does it make sence to set initial focus):
        // * component is editable
        // * initial focus was NOT already set on THIS component
        // * initial focus was NOT already set on OTHER component during the same request
        // * component is NOT readOnly
        // * component is NOT invisible
        // * component is NOT disabled
        // ? some case is not relevant or missing ? Remove or add one..
        if (component instanceof EditableValueHolder &&
            !component.getAttributes().containsKey(ATTR_INITIAL_FOCUS_SET) &&
            !context.getExternalContext().getRequestMap().containsKey(ATTR_INITIAL_FOCUS_SET) &&
            !(component.getAttributes().containsKey(ATTR_READ_ONLY) &&
              (Boolean)component.getAttributes().get(ATTR_READ_ONLY)) &&
            !(component.getAttributes().containsKey(ATTR_VISIBLE) &&
              !(Boolean)component.getAttributes().get(ATTR_VISIBLE)) &&
            !(component.getAttributes().containsKey(ATTR_DISABLED) &&
              (Boolean)component.getAttributes().get(ATTR_DISABLED))) {

            StringBuffer script = new StringBuffer();

            // Get component client Id (a whole path with naming containers)
            String clientId = clientBehaviorContext.getSourceId();

            // Simple Javascript to set a focus on component
            script.append("var component = document.getElementById");
            // clientId with a suffix "::content"
            // NOTE It is not recomended to use DOM level access functions with
            // automatically rendered ADF Faces components
            // In case the naming convention "::content" of ADF (Trinindad) Faces changes
            // please fall back to the the documented ADF Faces client API
            script.append("('").append(clientId).append("::content');");
            // Fire the function focus() twice, just to be sure :)
            // Supposed to work around some issues  at environments with complex ui and latency.
            // If it's not a case in your project - remove the second call.
            script.append("if(component != null) component.focus();component.focus();");

            // Mark the component with custom marker attribute initialFocusSet
            // to handle the case of partial refresh: doesn't change the focus if it was already set
            component.getAttributes().put(ATTR_INITIAL_FOCUS_SET, Boolean.TRUE);

            // Sent javascript to client
            ExtendedRenderKitService extendedRenderKitService = 
                Service.getRenderKitService(context, ExtendedRenderKitService.class);
            extendedRenderKitService.addScript(context, script.toString());

            // Put the marker initialFocusSet into request to handle the case with 
            // multiple initial focus tags in a same view
            // Enables to leave a cursor on the first input tag redered in a view
            context.getExternalContext().getRequestMap().put(ATTR_INITIAL_FOCUS_SET, Boolean.TRUE);

        }

        // Nothing to return. No success on returning Javascript here :( Why - timing issue ?
        // An alternative implementation based on ExtendedRenderKitService was selected to fire a javascript
        return "";
    }


}


