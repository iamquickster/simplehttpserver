package httpserver.services.twitter.view;

import httpserver.View;
import httpserver.services.twitter.model.TwitterRetweet;

public class TwitterRetweetView extends View {
	
	private String type;
	private TwitterRetweet retweet;

	public TwitterRetweetView(TwitterRetweet retweet, String type) {
		this.retweet = retweet;
		this.type = type;
	}
	
	public String toString() {
		return retweet.toJson().toString();
	}

}
