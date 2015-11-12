package httpserver.services.twitter.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class TwitterUser {
	private static int nextId;
	private static HashMap<String, TwitterUser> users = loadUserData();
	private List<TwitterTweet> tweets = new ArrayList<TwitterTweet>();
	private List<TwitterRetweet> retweets = new ArrayList<TwitterRetweet>();
	private List<TwitterUser> followees = new ArrayList<TwitterUser>();
	private String id;
	private String name;
	
	public TwitterUser(String name,String username) {
		this.id= username;
		//this.id = TwitterUser.nextId();
		this.name = name;
	}
	public static List<TwitterUser> getUsers(){
		return new ArrayList<TwitterUser>(users.values());
	}

	private static HashMap<String, TwitterUser> loadUserData() {
		HashMap<String, TwitterUser> result = new HashMap<String,TwitterUser>();
		
		TwitterUser good = new TwitterUser("The Good","theGood");
		TwitterUser bad = new TwitterUser("The Bad","theBad");
		TwitterUser ugly = new TwitterUser("The Ugly","theUgly");
		
		TwitterTweet clint = good.tweet("I am Clint Eastwood!");
		TwitterTweet vanCleef = bad.tweet("I am Lee Van Cleef!");
		TwitterTweet eliWallach = ugly.tweet("I am Eli Wallach!");
		
		good.retweet(vanCleef);
		good.retweet(eliWallach);
		bad.retweet(clint);
		bad.retweet(eliWallach);
		ugly.retweet(clint);
		ugly.retweet(vanCleef);
		
		ugly.follow(good);
		ugly.follow(bad);
		good.follow(ugly);
		
		result.put(good.id, good);
		result.put(bad.id, bad);
		result.put(ugly.id, ugly);
		return result;
	}

	public TwitterTweet tweet(String message) {
		TwitterTweet result = new TwitterTweet(this, message);
		this.tweets.add(result);
		return result;
	}

	private static long nextId() {
		return nextId++;
	}

	public List<TwitterTweet> getTweets() {
		return this.tweets;
	}
	
	public List<TwitterRetweet> getRetweets() {
		return this.retweets;
	}
	
	public List<TwitterFeedItem> getFeed() {
		ArrayList<TwitterFeedItem> result = new ArrayList<TwitterFeedItem>(tweets);
		result.addAll(retweets);
		Collections.sort(result);
		return result;
	}
	
	public boolean removeTweet(long id){
		for(int i = 0 ; i < tweets.size(); i++ ) {
			if(tweets.get(i).getId() == id) {
				tweets.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public TwitterRetweet retweet(TwitterTweet tweet) {
		TwitterRetweet result = new TwitterRetweet(this, tweet);
		this.retweets.add(result);
		return result;
	}
	
	public boolean removeRetweet(long id){
		for(int i = 0 ; i < retweets.size(); i++ ) {
			if(retweets.get(i).getId() == id) {
				retweets.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public List<TwitterUser> getFollowees() {
		return this.followees;
	}
	
	public void follow(TwitterUser user) {
		this.followees.add(user);
	}
	
	public void unsubcribe(String userId) {
		for(int i = 0 ; i < followees.size(); i++ ) {
			if(followees.get(i).getId() == userId) {
				followees.remove(i);
				return;
			}
		}
	}

	public String getId() {
		return this.id;
	}

	public String getName() {
		return name;
	}

	public static TwitterUser get(String userId) {
		return users .get(userId);
	}
	
}
