package httpserver.services.twitter.view;

import java.util.ArrayList;
import java.util.List;

import httpserver.View;
import httpserver.services.twitter.model.TwitterRetweet;
import httpserver.services.twitter.model.TwitterTweet;
import httpserver.services.twitter.model.TwitterUser;

public class TwitterFeedView extends View {

	private List<TwitterTweet> tweets = new ArrayList<TwitterTweet>();
	private List<TwitterTweet> orderedTweets = new ArrayList<TwitterTweet>();
	
	public TwitterFeedView(TwitterUser twitterUser, String string) {
		List<TwitterTweet> userTweets = twitterUser.getTweets();
		List<TwitterRetweet> retweets = twitterUser.getRetweets();
		for(int i=0;i<userTweets.size();i++){
			tweets.add(userTweets.get(i));
		}
		for(int i=0;i<retweets.size();i++){
			tweets.add(retweets.get(i).getOriginalTweet());
		}
		
		//sort
		for(int i=0;i<tweets.size();i++){
			int j;
			boolean found=false;
			for(j=0;j<orderedTweets.size()&&!found;j++){
				found=tweets.get(i).getDate().before(orderedTweets.get(j).getDate());
				if(found){
					orderedTweets.add(j, tweets.get(i));
				}
			}
			if(!found)
				orderedTweets.add(tweets.get(i));
		}
	}
	
	private String createJson(){
		String result="{\"tweets\":[";
		for(int i=0;i<orderedTweets.size();i++){
			result+="{ \"tweet\":{";
			result+="\"owner\": \""+orderedTweets.get(i).getOwner()+"\",";
			result+="\"message\": \""+orderedTweets.get(i).getMessage()+"\",";
			result+="\"date\": \""+orderedTweets.get(i).getDate().toString()+"\"";
			result+="}}";
			if(i<orderedTweets.size()-1)
				result+=",";
		}
		result+="]}";
		return result;
	}
	
	@Override
	public String toString() {
		return createJson();
	}
	
	
	

}
