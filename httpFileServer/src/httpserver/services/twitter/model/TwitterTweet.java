package httpserver.services.twitter.model;

import java.util.Date;

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


	
	
}
