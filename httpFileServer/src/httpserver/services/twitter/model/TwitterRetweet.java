package httpserver.services.twitter.model;

import java.util.Date;

import javax.json.Json;
import javax.json.JsonObject;


public class TwitterRetweet extends TwitterFeedItem{

	private TwitterTweet originalTweet;

	public TwitterRetweet(TwitterUser owner, TwitterTweet tweet) {
		super(owner, new Date());
		this.originalTweet = tweet;
	}

	
	public TwitterTweet getOriginalTweet(){
		return originalTweet;
	}

	@Override
	public JsonObject toJson() {
		return Json.createObjectBuilder().add("id", super.getId()).add("owner", super.getOwner()).add("date", super.postDate.toString())
				.add("tweet", originalTweet.toJson()).build();
	}


	@Override
	public String getLink() {
		return "/users/" + super.getOwner() + "/retweets/" + super.getId();
	}
}
