package httpserver.services.twitter.model;

import java.util.Date;


public class TwitterRetweet extends TwitterFeedItem{

	private TwitterTweet originalTweet;

	public TwitterRetweet(TwitterUser owner, TwitterTweet tweet) {
		super(owner, new Date());
		this.originalTweet = tweet;
	}
	
	public TwitterTweet getOriginalTweet(){
		return originalTweet;
	}
}
