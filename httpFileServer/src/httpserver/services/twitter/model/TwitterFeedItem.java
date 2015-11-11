package httpserver.services.twitter.model;

import java.util.Date;

public class TwitterFeedItem implements Comparable<TwitterFeedItem>{
	
	private static int nextId = 1;
	private long id;
	
	Date postDate;
	private TwitterUser owner;

	public TwitterFeedItem(TwitterUser twitterUser, Date date) {
		this.postDate = date;
		this.owner = twitterUser;
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
}
