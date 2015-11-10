package httpserver.services.twitter.view;

import httpserver.View;
import httpserver.services.twitter.model.TwitterTweet;

public class TwitterTweetView extends View {

	private TwitterTweet tweet;
	private String type;

	public TwitterTweetView(TwitterTweet tweet, String type) {
		this.tweet = tweet;
		this.type = type;
	}

}
