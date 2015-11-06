package httpserver.services.twitter.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TwitterFeed {
	List<TwitterTweet> feed = new ArrayList<TwitterTweet>();
	
	public boolean add(TwitterTweet tweet) {
		feed.add(tweet);
		Collections.sort(feed);
		return true;
	}
	
	public List<TwitterTweet> getFeed() {
		return feed;
	}
}
