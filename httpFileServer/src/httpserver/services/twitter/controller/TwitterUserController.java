package httpserver.services.twitter.controller;

import java.util.ArrayList;
import java.util.List;

import httpserver.Controller;
import httpserver.Request;
import httpserver.Response;
import httpserver.ResponseFactory;
import httpserver.View;
import httpserver.services.twitter.model.TwitterRetweet;
import httpserver.services.twitter.model.TwitterTweet;
import httpserver.services.twitter.model.TwitterUser;
import httpserver.services.twitter.view.TwitterFeedView;
import httpserver.services.twitter.view.TwitterFolloweeView;
import httpserver.services.twitter.view.TwitterFolloweesView;
import httpserver.services.twitter.view.TwitterRetweetView;
import httpserver.services.twitter.view.TwitterRetweetsView;
import httpserver.services.twitter.view.TwitterTweetView;
import httpserver.services.twitter.view.TwitterTweetsView;
import httpserver.services.twitter.view.TwitterUserSearchSuggestionView;

public class TwitterUserController implements Controller {

	ResponseFactory responseFactory = ResponseFactory.newInstance();

	@Override
	public boolean accept(Request request) {
		return request.getUri().startsWith("/utilisateurs/");
	}

	@Override
	public Response action(Request request) {

		String[] urlTokens = request.getUri().split("/");
		// /utilisateur/ not valid
		if(urlTokens.length == 2) {
			return responseFactory.createResponse(404);
		}
		String userId = urlTokens[2];
		// /utilisateur/{userId}/ not valid
		if(urlTokens.length == 3) {
			return responseFactory.createResponse(404);
		}
		
		String userResource = urlTokens[3];
	

		TwitterUser user = TwitterUser.get(userId);
		
		View view = null;
		Response response = null;

		// user does not exist
		if(user == null ) {
			view = new TwitterUserSearchSuggestionView(TwitterUser.search(userId), "text/json");
			return new Response(200, "text/json", view.toString());
		}

		if (userResource.equals("fil")) {
			if (request.getMethod().equalsIgnoreCase("GET")) {
				view = new TwitterFeedView(TwitterUser.get(userId), "text/json");
				return new Response(200, "text/json", view.toString());
			} else if (request.getMethod().equalsIgnoreCase("HEAD")) {
				return responseFactory.createResponse(200);
			} else {
				return responseFactory.createResponse(405);
			}
		} else if (userResource.equals("tweets")) {

			// Post tweet
			if (request.getMethod().equals("POST")) {
				if (urlTokens.length == 4) {
					long tweetId = user.tweet(request.getContent()).getId();
					response = responseFactory.createResponse(201);
					response.setHeader("Location", request.getUri() + tweetId);
					return response;
				} else {
					return responseFactory.createResponse(405);
				}
			} else

			// GET tweet
			if (request.getMethod().equals("GET")) {
				if (urlTokens.length == 4) {

					view = new TwitterTweetsView(user.getTweets(), "text/json");
					return new Response(200, "text/json", view.toString());

				}
				if (urlTokens.length == 5) {

					List<TwitterTweet> tweets = new ArrayList<TwitterTweet>();
					long tweetId = Long.parseLong(urlTokens[4]);
					for (TwitterTweet i : user.getTweets()) {
						if (i.getId() == tweetId) {
							view = new TwitterTweetView(i, "text/json");
							break;
						}
					}
					if(view == null ) { 
						return responseFactory.createResponse(404);
					}
					return new Response(200, "text/json", view.toString());

				} else {
					return responseFactory.createResponse(404);
				}
			} else 

			// DELETE - delete a tweet
			if (request.getMethod().equals("DELETE")) {
				if (urlTokens.length == 5) {

					long tweetId = Long.parseLong(urlTokens[4]);
					int i = 0;
					for (i = 0; i < user.getTweets().size(); i++) {
						if (user.getTweets().get(i).getId() == tweetId) {
							break;
						}
					}
					if (i == user.getTweets().size()) {
						return responseFactory.createResponse(404);
					} else {
						if (user.getTweets().remove(i) == null) {
							return responseFactory.createResponse(404);
						} else {
							return responseFactory.createResponse(200);
						}
					}
				} else {
					return responseFactory.createResponse(404);
				}
			} else

			// PUT - modify a tweet
			if (request.getMethod().equals("PUT")) {
				if (urlTokens.length == 5) {

					long tweetId = Long.parseLong(urlTokens[4]);
					int i = 0;
					for (i = 0; i < user.getTweets().size(); i++) {
						if (user.getTweets().get(i).getId() == tweetId) {
							user.getTweets().get(i).setMessage(request.getContent());
							break;
						}
					}
					if (i == user.getTweets().size()) {
						return responseFactory.createResponse(404);
					} else {

						return responseFactory.createResponse(200);

					}
				} else {
					return responseFactory.createResponse(404);
				}
			} else {
				return responseFactory.createResponse(405);
			}

		} else if (userResource.equals("retweets")) {

			if (request.getMethod().equals("POST")) {
				long tweetId = Long.parseLong(request.getParams().get("tweetId"));
				user.retweet((TwitterTweet) TwitterTweet.get(tweetId));
				response = responseFactory.createResponse(201);
				response.setHeader("Location", request.getUri() + tweetId);
				return response;
			} else

			// GET retweets - returns representation of the retweets of a user
			// if an tweetid is provided only that tweet is returned (not in a list)
			if (request.getMethod().equals("GET")) {
				// GET tweet
				
				if (urlTokens.length == 4) {

					view = new TwitterRetweetsView(user.getRetweets(), "text/json");
					return new Response(200, "text/json", view.toString());

				}
				if (urlTokens.length == 5) {
					long tweetId = Long.parseLong(urlTokens[4]);
					view = new TwitterRetweetView((TwitterRetweet) TwitterRetweet.get(tweetId), "text/json");
					return new Response(200, "text/json", view.toString());

				}else {
					return responseFactory.createResponse(404);
				}
				

			} else

			
			if (request.getMethod().equals("DELETE")) {
				long tweetId = Long.parseLong(urlTokens[4]);
				for (TwitterRetweet i : user.getRetweets()) {
					if (i.getId() == tweetId) {
						user.getRetweets().remove(i);
						break;
					}
				}
				return responseFactory.createResponse(200);
			} else {
				return responseFactory.createResponse(405);
			}


		} else if (userResource.equals("abonnements")) {

			if (request.getMethod().equals("GET")) {
				if (urlTokens.length == 4) {
					view = new TwitterFolloweesView(user.getFollowees(), "text/json");
					return new Response(200, "text/json", view.toString());
				}else if(urlTokens.length == 5){
					String UserId = urlTokens[4];
					for (TwitterUser i : user.getFollowees()) {
						if (i.getId().equals(UserId) ) {
							view = new TwitterFolloweeView(i, "text/json");
							break;
						}
					}
					if(view == null) {
						return responseFactory.createResponse(404);
					}
					return new Response(200, "text/json", view.toString());
				}
			}else if (request.getMethod().equals("DELETE")) {		
				if(urlTokens.length == 5){
					String UserId = urlTokens[4];
					for (TwitterUser i : user.getFollowees()) {
						if (i.getId().equals(UserId) ) {
							user.getFollowees().remove(i);
							break;
						}
					}
					return responseFactory.createResponse(200);
				}
			}

			else if(request.getMethod().equals("PUT")){
				String UserId = urlTokens[4];
				boolean follow=false;
				for (TwitterUser i : user.getFollowees()) {
					if (i.getId().equals(UserId) ) {
						follow=true;
						break;
					}
				}
				if(!follow){
					for (TwitterUser i : TwitterUser.getUsers()) {
						if (i.getId().equals(UserId) ) {
							user.getFollowees().add(i);
							break;
						}
					}
				}
				return responseFactory.createResponse(200);
			} else {
				return responseFactory.createResponse(405);
			}

		}

		return responseFactory.createResponse(404);
	}

}
