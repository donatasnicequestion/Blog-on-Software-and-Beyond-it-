package com.nicequestion.donatas.chat.model;

import java.beans.PropertyChangeListener;

/** Simple Chat listener model for the ADF active Chat sample
 * described in a blog @see http://donatas.nicequestion.com
 * 
 * @author Donatas Valys
 */
public interface ChatListener extends PropertyChangeListener {
    public void setUsername(String username);
    public String getUsername();
    public boolean isAlive();
    public void setAlive(boolean alive);
}
