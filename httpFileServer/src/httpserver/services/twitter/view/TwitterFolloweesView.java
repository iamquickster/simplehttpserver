package httpserver.services.twitter.view;

import java.util.ArrayList;
import java.util.List;

import httpserver.View;
import httpserver.services.twitter.model.TwitterUser;

public class TwitterFolloweesView extends View {

	private List<TwitterUser> followees;
	private String type;

	public TwitterFolloweesView(List<TwitterUser> followees, String type) {
		this.followees = followees;
		this.type = type;
	}
	
	public String toString() {
		String result="{\"followees\":[";
		for(int i=0;i<followees.size();i++){
			result+="{\"user\":{";
			result+="\"username\": \""+followees.get(i).getId()+"\",";
			result+="\"name\": \""+followees.get(i).getName()+"\"";
			result+="}}";
			if(i<followees.size()-1)
				result+=",";
		}
		result+="]}";
		return result;
	}

}
