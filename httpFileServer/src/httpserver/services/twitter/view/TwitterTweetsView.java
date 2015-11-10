package httpserver.services.twitter.view;

import java.util.List;

import httpserver.View;
import httpserver.services.twitter.model.TwitterTweet;
import httpserver.services.twitter.model.TwitterUser;

public class TwitterTweetsView extends View {

	private String type;
	private List<TwitterTweet> tweets;

	public TwitterTweetsView(List<TwitterTweet> list, String type) {
		this.tweets = list;
		this.type = type;
	}
	
	public String toString() {
		// TODO return json representation of the user tweets
		// Note : call toJson on model objects
		return "";
	}

}
