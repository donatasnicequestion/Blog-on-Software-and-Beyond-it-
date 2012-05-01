package com.nicequestion.donatas.adf;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;

import oracle.adf.controller.ControllerContext;
import oracle.adf.controller.ViewPortContext;


/** Custom JSF2 exception factory and handler implementation 
 * to handle oracle.adf.model.adapter.AdapterException of 
 * ADF Data Control 
 * 
 *  For information on JSF2 Exception API search for
 *  article of Ed Burns:
 *  "Dealing Gracefully with ViewExpiredException in JSF2"
 * 
 *  @author Donatas Valys
 */
public class CustomExceptionHandlerFactory extends ExceptionHandlerFactory {

    private final javax.faces.context.ExceptionHandlerFactory parent;

    public CustomExceptionHandlerFactory(final javax.faces.context.ExceptionHandlerFactory parent) {
        this.parent = parent;
    }


    @Override
    public ExceptionHandler getExceptionHandler() {
        return new CustomExceptionHandler(this.parent.getExceptionHandler());
    }

    private static class CustomExceptionHandler extends ExceptionHandlerWrapper {

        private final javax.faces.context.ExceptionHandler wrapped;

        public CustomExceptionHandler(final ExceptionHandler wrapped) {
            this.wrapped = wrapped;
        }


        @Override
        public ExceptionHandler getWrapped() {
            return this.wrapped;
        }

        /**
         * Custom logic to handle instances of ADF AdapterException. 
         * Extracts usefull information from exception and shows 
         * it as Faces Error Message.
         *
         * @throws FacesException
         */
        @Override
        public void handle() throws FacesException {

            ControllerContext controllerContext = ControllerContext.getInstance();
            ViewPortContext viewPortContext = controllerContext.getCurrentRootViewPort();

            // Check if exception is present in ADF root view port context
            if (viewPortContext.isExceptionPresent()) {

                Exception ex = viewPortContext.getExceptionData();
                
                // Handle only instances of AdapterException 
                // thrown (in this sample) by ADF JMX Data Control
                if (ex instanceof oracle.adf.model.adapter.AdapterException) {
                    // Clear the exception - it is going to be handled
                    // as faces message
                    viewPortContext.clearException();                    

                    // Extract some information (usefull for user in our case)
                    // from exception to display it as faces error message 
                    FacesContext fc = FacesContext.getCurrentInstance();
                    FacesMessage message =
                        new FacesMessage(FacesMessage.SEVERITY_ERROR
                                         , ex.getMessage()
                                         , ex.getCause().getCause().getMessage());
                    fc.addMessage(null, message);
                    
                    return;
                }
            }

            // handle other exceptions default way
            this.wrapped.handle();

            /*
            for (Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator(); i.hasNext(); ) {

                ExceptionQueuedEvent event = i.next();
                ExceptionQueuedEventContext context = (ExceptionQueuedEventContext)event.getSource();
                Throwable ex = context.getException();

                FacesContext fc = FacesContext.getCurrentInstance();
                FacesMessage message = new FacesMessage(ex.getMessage());
                fc.addMessage(null, message);

                if (ex instanceof oracle.adf.model.adapter.AdapterException) {

                    if (viewPortContext.isExceptionPresent()) {
                        viewPortContext.clearException();
                    }

                }


            }

            this.wrapped.handle(); */
        }
    }
}
