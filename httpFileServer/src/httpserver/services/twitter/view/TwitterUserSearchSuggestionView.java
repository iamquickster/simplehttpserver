package httpserver.services.twitter.view;

import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

import httpserver.View;
import httpserver.services.twitter.model.TwitterUser;

public class TwitterUserSearchSuggestionView extends View {

	private List<TwitterUser> users;
	private String type;

	public TwitterUserSearchSuggestionView(List<TwitterUser> list, String type) {
		this.users = list;
		this.type = type;
	}
	
	public String toString() {
		
		JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
		for(TwitterUser user : users) {
			JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();
			jsonBuilder.add("name", user.getName()).add("link", user.getLink() + "/fil");
			jsonArrayBuilder.add(jsonBuilder.build());
		}
		
		return jsonArrayBuilder.build().toString();
		
	}
	
}
