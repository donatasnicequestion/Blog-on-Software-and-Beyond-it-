package com.nicequestion.donatas.chat.jsf;

import com.nicequestion.donatas.chat.model.Chat;

import com.nicequestion.donatas.chat.model.ChatListener;

import java.beans.PropertyChangeEvent;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

import java.util.logging.Level;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.activedata.ActiveDataEventUtil;
import oracle.adf.view.rich.activedata.ActiveModelContext;
import oracle.adf.view.rich.activedata.BaseActiveDataModel;
import oracle.adf.view.rich.event.ActiveDataEntry;
import oracle.adf.view.rich.event.ActiveDataUpdateEvent;


/** 
 * Chat listener implemented as view scoped JSF bean for the
 * sample ADF active Chat. Shows a usage of active data service
 * functionality to push the updates to the UI in Oracle ADF.
 * 
 * The sample is described in a blog 
 * http://donatas.nicequestion.com
 * 
 * Active data service part is based on the description from
 * http://matthiaswessendorf.wordpress.com/2010/01/07/adf%E2%80%99s-active-data-service-and-scalar-data-like-activeoutputtext/
 *   
 * @author Donatas Valys
 * 
 */
@ManagedBean
@ViewScoped
public class ChatListenerBean extends BaseActiveDataModel implements ChatListener {

    private final AtomicInteger counter = new AtomicInteger(0);

    /** Inject Chat bean
     */
    @ManagedProperty(value = "#{chatBean}")
    private Chat chat;

    /** Inject username.
     * 
     *  Value is defined as input parameter in 
     *  a bounded task flow chat-task-flow-definition.xml
     *  ..
     *  <input-parameter-definition>
     *      <name>username</name>
     *      <value>#{pageFlowScope.username}</value>
     *    <required/>
     *  </input-parameter-definition>
     *  ..
     */
    @ManagedProperty(value = "#{pageFlowScope.username}")
    private String username;
    
    private boolean alive = true;
    
    @PostConstruct
    public void initializer() {        
        logger.setLevel(Level.INFO);
        logger.info("Username:" + username);
        
        /** 
         * Register active data model key path for the “message” attribute         
         */
        ActiveModelContext context = ActiveModelContext.getActiveModelContext();
        Object[] keyPath = new String[0];
        context.addActiveModelInfo(this, keyPath, "message");
        
        /**
         * Login this listener to chat
         */
        chat.login(this);        
    }

    /** This method is also referenced in 
     * a bounded task flow chat-task-flow-definition.xml
     * as a property "finalizer". 
     */
    @PreDestroy
    public void finalizer() { 
        logger.info("Username:" + username);
        /**
         * Logout this listener from chat
         */        
        chat.logout(this);        
    }

    /** Receive chat messages as property change event
     * 
     * @param evt new chat message event
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {        
        logger.info("Username:" + username +" Event:" + evt.getPropertyName() + " Message:" + evt.getNewValue());
                
        counter.incrementAndGet();
                            
        /* Fire active data event to push new value of the message to UI */   
        ActiveDataUpdateEvent event =
            ActiveDataEventUtil.buildActiveDataUpdateEvent(ActiveDataEntry.ChangeType.UPDATE, counter.get(),
                                                           new String[0], null, new String[] { "message" },
                                                           new Object[] {evt.getNewValue() });        
        fireActiveDataUpdate(event);
         
    }

    public void setMessage(String message) {
        chat.addMessage(username + " > " + message);
    }

    public String getMessage() {
        return null;
    }

    /** This call is initiated by active data - set alive flag to true.
     *  This listener is still alive on a client side, 
     *  because it was able to receive this method call. 
     * 
     * @return yes, i'm alive. 
     */
    public boolean getPong() {
        alive = true;
        return alive;
    }
    
    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public Chat getChat() {
        return chat;
    }
        
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username != null ? username : "anonymous";
    }
    
    
    @Override
    protected void startActiveData(Collection<Object> collection, int i) {        
    }

    @Override
    protected void stopActiveData(Collection<Object> collection) {
    }

    @Override
    public int getCurrentChangeCount() {
        return counter.get();
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    
    public boolean isAlive() {
        return alive;
    }
    
    private static ADFLogger logger = 
                ADFLogger.createADFLogger(ChatListenerBean.class); 
}
