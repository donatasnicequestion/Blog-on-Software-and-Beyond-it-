package com.nicequestion.donatas.adf.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import oracle.adf.model.BindingContext;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 *  Adapter (exposed as ADF Bean Data Control) to tranform string with tweets 
 *  from JSON format to custom domain model (TwitterTimeline) and 
 *  to enriche them with emotions using ADF method binding of URL Data Control.
 *  
 *  The implementation depends on page 
 *  definition showTweetsPageDef.xml 
 *
 *  @author Donatas Valys
 *  
 *  Leverages JSON reference implementtion for parsing.
 *  JSON is a light-weight, language independent, data interchange format.
 *  @link http://www.JSON.org/
 *  
 *  Feeling analyzer service is kindly 
 *  provided by Enrique López-Mañas
 *  @link http://www.neo-tech.es/wp/?p=99
 *  
 */
public class ShowTweetsPageAdapter {

    public static final String TWITTER_DATE_FORMAT = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TWITTER_DATE_FORMAT, Locale.ENGLISH);

    public ShowTweetsPageAdapter() {
        super();
    }

    /** Extract value of polarity from  feeling analyzer service JSON response.
     *  Example JSON String: {"results":{"text":"Sample text","polarity":2,"query":"NO_QUERY"}}
     *
     * @param feelingJsonString JSON String
     * @return polarity extracted from JSON String
     */
    private Integer extractPolarityFromJSONString(String feelingJsonString) throws JSONException {

        JSONObject feeling = new JSONObject(feelingJsonString);
        JSONObject results = feeling.getJSONObject("results");
        Integer polarity = results.getInt("polarity");

        return polarity;
    }

    /** Transform tweets from JSON string to custom domain model object TwitterTimeline
     * and enrich them with emotions
     * 
     * @param tweetsJsonString twitter timeline as string JSON format
     * @return twitter timeline as custom domain object TwitterTimeline 
     * @throws JSONException
     * @throws ParseException
     */
    public TwitterTimeline transformTwitterJSONString2Tweets(String tweetsJsonString) throws JSONException,
                                                                                             ParseException {
        /*
         * Get an instance of method binding named loadData1 (feeling analyzer service)
         * of current binding container
         * The implementation is therefore COUPLED with a page definition
         */
        BindingContext context = BindingContext.getCurrent();
        BindingContainer bindings = context.getCurrentBindingsEntry();
        OperationBinding operation = bindings.getOperationBinding("loadData1");

        // Initialize list of tweets
        List<Tweet> tweetList = new ArrayList<Tweet>();
        // Parse JSON String : create JSON Array
        JSONArray tweetsArray = new JSONArray(tweetsJsonString);

        // Loop over the JSONArray of tweets. 
        // NOTE A method binding of external remote service is executed for each tweet. 
        // Impact on performance should be considered by using this technique in a loop. In this sample
        // the impact is limited due maximum expected count (20) of a tweets in  JSON string.
        for (int i = 0; i < tweetsArray.length(); i++) {

            // Create custom domain object Tweet from JSON Object
            Tweet tweet = new Tweet();
            tweet.setMessage((tweetsArray.getJSONObject(i)).getString("text"));
            tweet.setDate(simpleDateFormat.parse((tweetsArray.getJSONObject(i)).getString("created_at")));

            // Enrich the tweet with emotion: begin
            
            // Put the text as an input parameter value for feelings service
            operation.getParamsMap().put("Text", tweet.getMessage());
            // Call feeling analyzer method binding
            operation.execute();
            Object operationResult = operation.getResult();

            if (operationResult != null) {
                // operationResult is an instance of ADF framework anonymous inner class 
                // which (fortunatly) implements Iterator interface
                Iterator result = (Iterator)operationResult;

                // Only one result item with a single column is expected by design
                while (result.hasNext()) {
                    Map map = (Map)result.next();
                    // Get feeling analyzer response
                    String feelingJsonString = (String)map.get("Column0");
                    // Extract a polarity from JSON
                    Integer feeling = extractPolarityFromJSONString(feelingJsonString);
                    // Enrich the tweet with it
                    tweet.setFeeling(feeling);
                }

            }
            
            // Enrich the tweet with emotion: end
            
            // Add tweet to the list
            tweetList.add(tweet);

            // Print a message to show what's going on
            System.out.println("Message:" + tweet.getMessage());

        }
        // Create TwitterTimeline containing list of tweets to return
        TwitterTimeline twitterTimeline = new TwitterTimeline();
        twitterTimeline.setTweetList(tweetList);

        return twitterTimeline;

    }

}
