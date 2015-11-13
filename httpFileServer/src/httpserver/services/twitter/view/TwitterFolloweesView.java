package httpserver.services.twitter.view;

import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;

import httpserver.View;
import httpserver.services.twitter.model.TwitterUser;

public class TwitterFolloweesView extends View {

	private List<TwitterUser> followees;
	private String type;

	public TwitterFolloweesView(List<TwitterUser> followees, String type) {
		this.followees = followees;
		this.type = type;
	}
	
	public String toString() {
		JsonArrayBuilder builder = Json.createArrayBuilder();
		for (TwitterUser followee : followees){
			builder.add(followee.toJson());
		}
		return builder.build().toString();
	}

}
