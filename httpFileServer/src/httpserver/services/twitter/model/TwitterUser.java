package httpserver.services.twitter.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class TwitterUser {
	private static int nextId;
	private static HashMap<Long, TwitterUser> users = loadUserData();
	private List<TwitterTweet> tweets = new ArrayList<TwitterTweet>();
	private List<TwitterRetweet> retweets = new ArrayList<TwitterRetweet>();
	private List<TwitterUser> followees = new ArrayList<TwitterUser>();
	private long id;
	private String name;
	
	public TwitterUser(String name) {
		this.id = TwitterUser.nextId();
		this.name = name;
	}

	private static HashMap<Long, TwitterUser> loadUserData() {
		HashMap<Long, TwitterUser> result = new HashMap<Long,TwitterUser>();
		
		TwitterUser good = new TwitterUser("The Good");
		TwitterUser bad = new TwitterUser("The Bad");
		TwitterUser ugly = new TwitterUser("The Ugly");
		
		TwitterTweet clint = good.tweet("I am Clint Eastwood!");
		TwitterTweet vanCleef = bad.tweet("I am Lee Van Cleef!");
		TwitterTweet eliWallach = ugly.tweet("I am Eli Wallach!");
		
		good.retweet(vanCleef);
		good.retweet(eliWallach);
		bad.retweet(clint);
		bad.retweet(eliWallach);
		ugly.retweet(clint);
		ugly.retweet(vanCleef);
		
		return result;
	}

	private TwitterTweet tweet(String message) {
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
		return new TwitterRetweet(this, tweet);
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
	
	public void unsubcribe(long userId) {
		for(int i = 0 ; i < followees.size(); i++ ) {
			if(followees.get(i).getId() == userId) {
				followees.remove(i);
				return;
			}
		}
	}

	private long getId() {
		return this.id;
	}


	public static TwitterUser get(long userId) {
		return users .get(userId);
	}
	
}
