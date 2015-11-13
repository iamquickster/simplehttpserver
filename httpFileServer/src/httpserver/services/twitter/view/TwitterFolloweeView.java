package httpserver.services.twitter.view;

import httpserver.View;
import httpserver.services.twitter.model.TwitterUser;

public class TwitterFolloweeView extends View {

	private TwitterUser followee;

	public TwitterFolloweeView(TwitterUser followee, String type) {
		this.followee = followee;
	}
	
	@Override
	public String toString() {
		return followee.toJson().toString();
		
	}

}
