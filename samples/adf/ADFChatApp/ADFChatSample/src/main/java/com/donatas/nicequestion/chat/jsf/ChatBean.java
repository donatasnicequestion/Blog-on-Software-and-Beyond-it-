package adstest;

import com.nicequestion.donatas.chat.model.Chat;

import com.nicequestion.donatas.chat.model.ChatListener;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import oracle.adf.share.logging.ADFLogger;


/** Chat implemented as application scoped JSF bean for the
 * sample ADF active Chat. Shows a usage of active data service
 * functionality to push the updates to the UI in Oracle ADF.
 * 
 * The sample is described in a blog 
 * http://donatas.nicequestion.com
 * 
 * @author Donatas Valys
 */
@ManagedBean
@ApplicationScoped
public class ChatBean implements Chat {
        
    private List<String> messages = Collections.synchronizedList(new ArrayList<String>());
    private List<String> users = Collections.synchronizedList(new ArrayList<String>());

    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private ScheduledExecutorService scheduler; 
    
    @PostConstruct
    public void initializer() {
        logger.setLevel(Level.INFO);
        
        scheduler = Executors.newSingleThreadScheduledExecutor();
        /** 
         * Schedule a task to ping chat listeners every 10 seconds         
         * */
        scheduler.scheduleWithFixedDelay(new PingTask(), 10, 10, TimeUnit.SECONDS);
        /** 
         * Schedule a task to clean "dead" chat listeners every 30 seconds         
         * */        
        scheduler.scheduleWithFixedDelay(new CleanTask(), 30, 30, TimeUnit.SECONDS);
                
    }
    
    @PreDestroy
    public void finalizer() {
        scheduler.shutdownNow();
    }

    /** Login ChatLister by subscribing to chat messages 
     * and adding its username to user list
     * 
     * @param listener ChatListener to login
     */
    public void login(ChatListener listener) {
       logger.info(listener.getUsername());
        
       propertyChangeSupport.addPropertyChangeListener(listener); 
       users.add(listener.getUsername());
       addMessage("Welcome " + listener.getUsername());           
    }

    /** Logout ChatLister by unsubscribing from chat messages 
     * and removing its username from user list.
     * 
     * @param listener ChatListener to login
     */    
    public void logout(ChatListener listener) {        
        logger.info(listener.getUsername());
        
        propertyChangeSupport.removePropertyChangeListener(listener);
        users.remove(listener.getUsername());
        addMessage("Goodbye " + listener.getUsername());            
    }

    /** Broadcast new message to chat listeners
     * 
     * @param newMessage new chat message
     */
    public void addMessage(String newMessage) {
        logger.info(newMessage);
                                  
        messages.add(newMessage);
        
        /** Show up to 20 messages in a chat window          
         * */    
        if(messages.size()  > 20) {
            messages.remove(0);
        }
                    
        propertyChangeSupport.firePropertyChange("newMessage", null, newMessage + " Timestamp:" + System.currentTimeMillis());
    }
             
    public List<String> getMessages() {
        return messages;
    }
    
    public List<String> getUsers() {
        return users;
    }

    /** Recuring task to Ping chat listeners to check if they are still alive
     */
    protected class PingTask implements Runnable { 
       public void run() { 
           propertyChangeSupport.firePropertyChange("ping", null, "Ping:" + System.currentTimeMillis());   
       } 
    }

    /** Recuring task to clean chat listeners which are already gone. 
     *  Reset alive flag for chat listeners which are alive.
     */
    protected class CleanTask implements Runnable { 
       public void run() { 
           for(PropertyChangeListener chatListener: propertyChangeSupport.getPropertyChangeListeners()) {
               ChatListener listener = (ChatListener) chatListener;
               if(!listener.isAlive()) {
                   logger.info(listener.getUsername());
                   logout(listener);
               } else {
                   listener.setAlive(false);
               }
           }
       } 
    }
    
    private static ADFLogger logger = 
                ADFLogger.createADFLogger(ChatBean.class); 
}
