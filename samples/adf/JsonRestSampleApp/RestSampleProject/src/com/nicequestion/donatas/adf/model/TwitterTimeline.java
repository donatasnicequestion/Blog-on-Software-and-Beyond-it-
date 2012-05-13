package com.nicequestion.donatas.adf.model;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


/**
 * Twitter timeline POJO exposed as JSF2 manged bean.
 * 
 * Contains the last 20 tweets of a twitter user identified by screenName.
 * 
 * @author Donatas Valys
 */
@ManagedBean @ViewScoped
public class TwitterTimeline {

    /** 
     * ScreenName of twitter user initialized with default value
     */
    private String screenName = "adf_emg";

    /**
     * List of tweets for the user 
     */
    private List<Tweet> tweetList;
    
    public TwitterTimeline() {
        super();
        tweetList = new ArrayList<Tweet>();
    }

    public void setTweetList(List<Tweet> tweetList) {
        this.tweetList = tweetList;
    }

    public List<Tweet> getTweetList() {
        return tweetList;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getScreenName() {
        return screenName;
    }
}
