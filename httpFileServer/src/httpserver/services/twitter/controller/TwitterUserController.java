package httpserver.services.twitter.controller;

import java.util.ArrayList;
import java.util.List;

import httpserver.Controller;
import httpserver.Request;
import httpserver.Response;
import httpserver.ResponseFactory;
import httpserver.View;
import httpserver.services.twitter.model.TwitterFeedItem;
import httpserver.services.twitter.model.TwitterRetweet;
import httpserver.services.twitter.model.TwitterTweet;
import httpserver.services.twitter.model.TwitterUser;
import httpserver.services.twitter.view.TwitterFeedView;
import httpserver.services.twitter.view.TwitterFolloweesView;
import httpserver.services.twitter.view.TwitterRetweetsView;
import httpserver.services.twitter.view.TwitterTweetsView;

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
	

		TwitterUser user = TwitterUser.get(userId);

		View view = null;
		Response response = null;

		/*
		if(userResource.equals("fil")) {
			if(request.getMethod().equalsIgnoreCase("GET")){
				//String contentType= request.getHeaders().get(Request.CONTENT_TYPE_HEADER);
				String contentType="application/json";
				view = new TwitterFeedView(TwitterUser.get(userId), contentType);
				return new Response(200, contentType, view.toString());
			} else if(request.getMethod().equalsIgnoreCase("HEAD")){

*/
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
					response.setHeader("Location", request.getUri() + "/" + tweetId);
					return response;
				} else {
					return responseFactory.createResponse(404);
				}
			}

			// GET tweet
			if (request.getMethod().equals("GET")) {
				if (urlTokens.length == 4) {

					view = new TwitterTweetsView(user.getTweets(), "text/json");
					return new Response(200, "text/json", view.toString());

				}
				if (urlTokens.length == 5) {

					List<TwitterTweet> tweets = new ArrayList<TwitterTweet>();
					long tweetId = Long.parseLong(urlTokens[4]);
					TwitterTweet tweet = null;
					for (TwitterTweet i : user.getTweets()) {
						if (i.getId() == tweetId) {
							tweet = i;
							tweets.add(i);
							break;
						}
					}
					view = new TwitterTweetsView(tweets, "text/json");
					return new Response(200, "text/json", view.toString());

				} else {
					return responseFactory.createResponse(404);
				}
			}

			// DELETE - delete a tweet
			if (request.getMethod().equals("DELETE")) {
				if (urlTokens.length == 4) {

					long tweetId = Long.parseLong(urlTokens[3]);
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
			}

			// PUT - modify a tweet
			if (request.getMethod().equals("PUT")) {
				if (urlTokens.length == 4) {

					long tweetId = Long.parseLong(urlTokens[3]);
					int i = 0;
					for (i = 0; i < user.getTweets().size(); i++) {
						if (user.getTweets().get(i).getId() == tweetId) {
							break;
						}
					}
					if (i == user.getTweets().size()) {
						return responseFactory.createResponse(404);
					} else {

						TwitterTweet modifiedTweet = new TwitterTweet(user, request.getContent());
						user.getTweets().set(i, modifiedTweet);
						return responseFactory.createResponse(200);

					}
				} else {
					return responseFactory.createResponse(404);
				}
			}

		} else if (userResource.equals("retweets")) {

			// TODO Post retweet - adds a retweet to user profile
			if (request.getMethod().equals("POST")) {

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
					List<TwitterRetweet> retweets = new ArrayList<TwitterRetweet>();
					long tweetId = Long.parseLong(urlTokens[4]);
					for (TwitterRetweet i : user.getRetweets()) {
						if (i.getId() == tweetId) {
							retweets.add(i);
							break;
						}
					}
					view = new TwitterRetweetsView(retweets, "text/json");
					return new Response(200, "text/json", view.toString());

				} else {
					return responseFactory.createResponse(404);
				}
				

			} else

			// TODO DELETE - deletes the retweet in the url
			if (request.getMethod().equals("DELETE")) {

			} else

			// TODO PUT - replaces the body of a retweet by the body of the request
			if (request.getMethod().equals("PUT")) {
				
			}

		} else if (userResource.equals("abonnements")) {

			if (request.getMethod().equals("GET")) {
				if (urlTokens.length == 4) {
					view = new TwitterFolloweesView(user.getFollowees(), "text/json");
					return new Response(200, "text/json", view.toString());
				}else if(urlTokens.length == 5){
					List<TwitterUser> folowees = new ArrayList<TwitterUser>();
					String UserId = urlTokens[4];
					for (TwitterUser i : user.getFollowees()) {
						if (i.getId().equals(UserId) ) {
							folowees.add(i);
							break;
						}
					}
					view = new TwitterFolloweesView(folowees, "text/json");
					return new Response(200, "text/json", view.toString());
				}
			}

			// TODO DELETE

			// TODO PUT

		}

		return null;
	}

}
