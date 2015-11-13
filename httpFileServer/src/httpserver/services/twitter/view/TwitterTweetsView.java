package httpserver.services.twitter.view;

import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;

import httpserver.View;
import httpserver.services.twitter.model.TwitterTweet;

public class TwitterTweetsView extends View {

	private String type;
	private List<TwitterTweet> tweets;

	public TwitterTweetsView(List<TwitterTweet> list, String type) {
		this.tweets = list;
		this.type = type;
	}
	
	public String toString() {
		JsonArrayBuilder builder = Json.createArrayBuilder();
		for (TwitterTweet tweet : tweets){
			builder.add(tweet.toJson());
		}
		return builder.build().toString();
	}

}
