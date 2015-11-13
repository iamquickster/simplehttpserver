package httpserver.services.twitter.view;

import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;

import httpserver.View;
import httpserver.services.twitter.model.TwitterFeedItem;
import httpserver.services.twitter.model.TwitterUser;

public class TwitterFeedView extends View {

	private List<TwitterFeedItem> feed;
	
	public TwitterFeedView(TwitterUser twitterUser, String type) {
		
		this.feed = twitterUser.getFeed();

	}
	
	@Override
	public String toString() {
		
		JsonArrayBuilder builder = Json.createArrayBuilder();
		
		for (TwitterFeedItem item : feed) {
			builder.add(item.toJson());
		}
		return builder.build().toString();
	}
	
	
	

}
