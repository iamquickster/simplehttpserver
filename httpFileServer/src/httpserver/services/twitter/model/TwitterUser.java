package httpserver.services.twitter.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;

import httpserver.HyperMedia;
import httpserver.JsonSerializable;

public class TwitterUser implements JsonSerializable, HyperMedia {
	private static HashMap<String, TwitterUser> users = loadUserData();
	private List<TwitterTweet> tweets = new ArrayList<TwitterTweet>();
	private List<TwitterRetweet> retweets = new ArrayList<TwitterRetweet>();
	private List<TwitterUser> followees = new ArrayList<TwitterUser>();
	private String name;

	public TwitterUser(String name) {
		this.name = name;
	}

	public static List<TwitterUser> getUsers() {
		return new ArrayList<TwitterUser>(users.values());
	}

	private static HashMap<String, TwitterUser> loadUserData() {
		HashMap<String, TwitterUser> result = new HashMap<String, TwitterUser>();

		TwitterUser good = new TwitterUser("TheGood");
		TwitterUser bad = new TwitterUser("TheBad");
		TwitterUser ugly = new TwitterUser("TheUgly");
		
		good.follow(bad);
		good.follow(ugly);
		
		bad.follow(good);
		bad.follow(ugly);
		
		ugly.follow(good);
		ugly.follow(bad);

		TwitterTweet clint = good.tweet("I am Clint Eastwood!");
		TwitterTweet vanCleef = bad.tweet("I am Lee Van Cleef!");
		TwitterTweet eliWallach = ugly.tweet("I am Eli Wallach!");

		good.retweet(vanCleef);
		good.retweet(eliWallach);
		bad.retweet(clint);
		bad.retweet(eliWallach);
		ugly.retweet(clint);
		ugly.retweet(vanCleef);


		result.put(good.getId(), good);
		result.put(bad.getId(), bad);
		result.put(ugly.getId(), ugly);
		return result;
	}

	public TwitterTweet tweet(String message) {
		TwitterTweet result = new TwitterTweet(this, message);
		this.tweets.add(result);
		return result;
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

	public boolean removeTweet(long id) {
		for (int i = 0; i < tweets.size(); i++) {
			if (tweets.get(i).getId() == id) {
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

	public boolean removeRetweet(long id) {
		for (int i = 0; i < retweets.size(); i++) {
			if (retweets.get(i).getId() == id) {
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
		for (int i = 0; i < followees.size(); i++) {
			if (followees.get(i).getId() == userId) {
				followees.remove(i);
				return;
			}
		}
	}

	public String getId() {
		return this.name;
	}

	public String getName() {
		return name;
	}

	public static TwitterUser get(String userId) {
		return users.get(userId);
	}

	@Override
	public JsonObject toJson() {

		return Json.createObjectBuilder().add("name", name).add("tweets", getLink() + "/tweets/")
				.add("retweets", getLink() + "/retweets/").add("abonnements", getLink() + "/abonnements/").build();
	}

	@Override
	public String getLink() {
		return "/utilisateurs/" + name ;
	}

	public static List<TwitterUser> search(String userId) {
		List<TwitterUser> result = new ArrayList<TwitterUser>();
		
		for (String userName : TwitterUser.users.keySet()) {
			if(userName.contains(userId)) { 
				result.add(users.get(userName));
			}
		}
		return result;
	}

}
