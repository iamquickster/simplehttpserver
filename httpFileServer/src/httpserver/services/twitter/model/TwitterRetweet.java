package httpserver.services.twitter.model;

import java.util.Date;

import javax.json.JsonObject;


public class TwitterRetweet extends TwitterFeedItem{

	private TwitterTweet originalTweet;

	public TwitterRetweet(TwitterUser owner, TwitterTweet tweet) {
		super(owner, new Date());
		this.originalTweet = tweet;
	}

	@Override
	public JsonObject toJson() {
		// TODO Converts this object to json
		return null;
	}
}
