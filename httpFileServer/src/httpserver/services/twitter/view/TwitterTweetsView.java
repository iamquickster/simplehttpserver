package httpserver.services.twitter.view;

import java.util.List;

import httpserver.View;
import httpserver.services.twitter.model.TwitterTweet;

public class TwitterTweetsView extends View {

	private String type;
	private List<TwitterTweet> tweets;

	public TwitterTweetsView(List<TwitterTweet> list, String type) {
		this.tweets = list;
		this.type = type;
	}
	
	public String toString() {
		String result="{\"tweets\":[";
		for(int i=0;i<tweets.size();i++){
			result+="{ \"tweet\":{";
			result+="\"owner\": \""+tweets.get(i).getOwner()+"\",";
			result+="\"message\": \""+tweets.get(i).getMessage()+"\",";
			result+="\"date\": \""+tweets.get(i).getDate().toString()+"\"";
			result+="}}";
			if(i<tweets.size()-1)
				result+=",";
		}
		result+="]}";
		return result;
	}

}
