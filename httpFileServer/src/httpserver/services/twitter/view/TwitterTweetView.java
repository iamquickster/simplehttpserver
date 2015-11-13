package httpserver.services.twitter.view;

import httpserver.View;
import httpserver.services.twitter.model.TwitterTweet;

public class TwitterTweetView extends View{
	private String type;
	private TwitterTweet tweet;

	public TwitterTweetView(TwitterTweet tweet, String type) {
		this.tweet = tweet;
		this.type = type;
	}
	
	public String toString() {
		return tweet.toJson().toString();
	}
}
