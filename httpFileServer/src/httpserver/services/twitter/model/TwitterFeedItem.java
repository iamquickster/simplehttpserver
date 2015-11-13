package httpserver.services.twitter.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import httpserver.HyperMedia;
import httpserver.JsonSerializable;

public abstract class TwitterFeedItem implements Comparable<TwitterFeedItem>, JsonSerializable, HyperMedia{
	
	private static int nextId = 1;
	private static Map<Long, TwitterFeedItem> items = new HashMap<Long, TwitterFeedItem>();
	private long id;
	
	Date postDate;
	private TwitterUser owner;

	public TwitterFeedItem(TwitterUser twitterUser, Date date) {
		this.postDate = date;
		this.owner = twitterUser;
		id=nextId();
		TwitterFeedItem.items.put(id, this);
		
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
