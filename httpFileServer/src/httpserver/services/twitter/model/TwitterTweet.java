package httpserver.services.twitter.model;

import java.util.Date;

import javax.json.JsonObject;

public class TwitterTweet extends TwitterFeedItem{


	String message = null;
	
	private void setMessage(String message) {
		if(message.length() > 140) {
			throw new IllegalArgumentException();
		} else {
			this.message = message;
		}
	}
	
	public TwitterTweet(TwitterUser twitterUser, String message) throws IllegalArgumentException {
		super(twitterUser, new Date());
		this.setMessage(message);
	}

	@Override
	public JsonObject toJson() {
		// TODO Converts this object to json
		return null;
	}


	
	
}
