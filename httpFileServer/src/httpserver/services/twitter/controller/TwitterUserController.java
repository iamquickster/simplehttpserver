package httpserver.services.twitter.controller;

import httpserver.Controller;
import httpserver.Request;
import httpserver.Response;
import httpserver.ResponseFactory;
import httpserver.View;
import httpserver.services.twitter.model.TwitterUser;
import httpserver.services.twitter.vue.TwitterFeedView;

public class TwitterUserController implements Controller {
	
	ResponseFactory responseFactory = ResponseFactory.newInstance();

	@Override
	public boolean accept(Request request) {
		return request.getUri().startsWith("/utilisateurs/");
	}

	@Override
	public Response action(Request request) {
		
		String[] urlTokens = request.getUri().split("/");
		
		String userId = urlTokens[2];
		String userResource = urlTokens[3];
		
		View view = null;
		Response response = null;
		
		if(userResource.equals("fil")) {
			if(request.getMethod().equalsIgnoreCase("GET")){
				//String contentType= request.getHeaders().get(Request.CONTENT_TYPE_HEADER);
				String contentType="application/json";
				view = new TwitterFeedView(TwitterUser.get(userId), contentType);
				return new Response(200, contentType, view.toString());
			} else if(request.getMethod().equalsIgnoreCase("HEAD")){
				return responseFactory.createResponse(200);
			} else {
				return responseFactory.createResponse(405);
			}
		} else if(userResource.equals("tweets")) {
			//TODO POST
			
			//TODO GET
			
			//TODO DELETE
			
			//TODO PUT
			
		} else if(userResource.equals("retweets")) {
			
			//TODO POST
			
			//TODO GET
			
			//TODO DELETE
			
			//TODO PUT
			
		} else if(userResource.equals("abonnements")) {
			
			//TODO GET
			
			//TODO DELETE
			
			//TODO PUT
			
		}
		
		return null;
	}
	
	
	
}
