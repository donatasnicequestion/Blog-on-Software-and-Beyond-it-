package com.nicequestion.donatas.chat.model;

import java.util.List;

/** Simple Chat model definition for ADF active Chat sample 
 *  described in a blog @see http://donatas.nicequestion.com
 *  
 * @author Donatas Valys
 */
public interface Chat {
    public void login(ChatListener listener);
    public void logout(ChatListener listener);
    public void addMessage(String message);
    public List<String> getMessages();
    public List<String> getUsers();
}
