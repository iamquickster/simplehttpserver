package httpserver.services.twitter.view;

import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;

import httpserver.View;
import httpserver.services.twitter.model.TwitterRetweet;

public class TwitterRetweetsView extends View {

	private List<TwitterRetweet> retweets;
	private String type;

	public TwitterRetweetsView(List<TwitterRetweet> retweets, String type) {
		this.retweets = retweets;
		this.type = type;
	}
	
	public String toString() {
		JsonArrayBuilder builder = Json.createArrayBuilder();
		for (TwitterRetweet retweet : retweets){
			builder.add(retweet.toJson());
		}
		return builder.build().toString();
	}

}
