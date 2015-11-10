package httpserver.services.twitter.view;

import java.util.List;

import httpserver.View;
import httpserver.services.twitter.model.TwitterRetweet;

public class TwitterRetweetsView extends View {

	private List<TwitterRetweet> retweets;
	private String type;

	public TwitterRetweetsView(List<TwitterRetweet> retweets, String type) {
		this.retweets = retweets;
		this.type = type;
	}

}
