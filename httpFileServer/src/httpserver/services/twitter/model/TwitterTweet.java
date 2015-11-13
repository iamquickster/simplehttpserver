package httpserver.services.twitter.model;

import java.util.Date;

import javax.json.Json;
import javax.json.JsonObject;

public class TwitterTweet extends TwitterFeedItem {

	String message = null;

	public void setMessage(String message) {
		if (message.length() > 140) {
			throw new IllegalArgumentException();
		} else {
			this.message = message;
		}
	}

	public TwitterTweet(TwitterUser twitterUser, String message) throws IllegalArgumentException {
		super(twitterUser, new Date());
		this.setMessage(message);
	}

	public Date getDate() {
		return postDate;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public JsonObject toJson() {
		return Json.createObjectBuilder().add("id", super.getId()).add("owner", super.getOwner()).add("date", super.postDate.toString())
				.add("message", this.message).build();
	}

	@Override
	public String getLink() {
		return "/utilisateurs/" + super.getOwner() + "/tweets/" + super.getId();
	}

}
