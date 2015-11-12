package httpserver.services.twitter.view;

import java.util.ArrayList;
import java.util.List;

import httpserver.View;
import httpserver.services.twitter.model.TwitterRetweet;
import httpserver.services.twitter.model.TwitterTweet;

public class TwitterRetweetsView extends View {

	private List<TwitterRetweet> retweets;
	private String type;

	public TwitterRetweetsView(List<TwitterRetweet> retweets, String type) {
		this.retweets = retweets;
		this.type = type;
	}
	
	public String toString() {
		String result="{\"retweets\":[";
		for(int i=0;i<retweets.size();i++){
			result+="{\"retweeter\":\""+retweets.get(i).getOwner()+"\",";
			result+="\"tweet\":{";
			result+="\"owner\": \""+retweets.get(i).getOriginalTweet().getOwner()+"\",";
			result+="\"message\": \""+retweets.get(i).getOriginalTweet().getMessage()+"\",";
			result+="\"date\": \""+retweets.get(i).getOriginalTweet().getDate().toString()+"\"";
			result+="}}";
			if(i<retweets.size()-1)
				result+=",";
		}
		result+="]}";
		return result;
	}

}
