package httpserver.services.twitter.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.json.JsonObject;
import javax.json.JsonValue;

import httpserver.JsonSerializable;

public abstract class TwitterFeedItem implements Comparable<TwitterFeedItem>, JsonSerializable{
	
	private static int nextId = 1;
	private static Map<Long, TwitterFeedItem> items = new HashMap<Long, TwitterFeedItem>();
	private long id;
	
	Date postDate;
	private TwitterUser owner;

	public TwitterFeedItem(TwitterUser twitterUser, Date date) {
		this.postDate = date;
		this.owner = twitterUser;
		TwitterFeedItem.items.put(id, this);
		id=nextId();
	}


	@Override
	public int compareTo(TwitterFeedItem other) {
		return postDate.compareTo(other.getDate());
	}


	private Date getDate() {
		return postDate;
	}
	
	public long getId() {
		return this.id;
	}
	
	static long nextId() {
		return nextId ++;
	}

	public String getOwner(){
		return owner.getName();
	}


	public static TwitterFeedItem get(long id) {
		return items.get(new Long(id));
	}
	
	
}
