package httpserver.services.twitter.view;

import java.util.List;

import httpserver.View;
import httpserver.services.twitter.model.TwitterFeedItem;
import httpserver.services.twitter.model.TwitterUser;

public class TwitterFeedView extends View {

	private List<TwitterFeedItem> feed;
	private String type;

	public TwitterFeedView(List<TwitterFeedItem> feed, String type) {
		this.feed = feed;
		this.type = type;
	}
	
	public String toString() {
		// TODO return json representation of the user tweets
		// Note : call toJson on model objects
		return "";
	}

}
