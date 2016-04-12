----------------HttpServer----------------

Authors: Pascal Feo, Chakib TchantChane and Nicolas Rivet
This applciation is a basic server which show the inner workings of a
single page website with a REST API. The example implemented here is a
fictitious twitter account that is able to post and subscribe to other
users.
It is not a description of the inner workings of Twitter but an example
of how to implement a HTTP Server using JAVA.

The API REST:
	
	GET /users/{userId}/feed/
		Get user feed
		
	GET /users/{userId}/tweets/
		Get user Tweets
	
	POST /users/{userId}/tweets/
		Post a tweet with the message in the body
	
	GET /users/{userId}/tweets/{tweetId}/
		Get a tweet from a user
	
	DELETE /users/{userId}/tweets/{tweetId}/
		Delete a tweet from a user
	
	PUT /users/{userId}/tweets/{tweetId}/
		Modify a tweet
		
	GET /users/{userId}/retweets
		Get retweets of a user
	
	POST /users/{userId}/retweets?tweetId={tweetID}/
		Post a retweet retweet
	
	GET /users/{userId}/retweets/{retweetId}/
		Get a retweet
	
	DELETE /users/{userId}/retweets/{retweetId}/
		Delete a retweet
		
	GET /users/{userId}/followees/
		Get the followees of a user
		
	GET /users/{userId}/followees/{followeeId}/
		Get a followee
	
	DELETE /users/{userId}/followees/{followeeId}/
		Unfollow a user
	
	PUT /users/{userId}/followees/{followeeId}/
		Modify a subscription
	

The port is 8080 by default but it can be specified at launch.

The server uses a Router to send the requests to the right Controller. The Controller then
parses the query and obtains the needed information from the model. the model is passed to
the view which generates a representation (JSON for REST calls) which is sent to the user.

The responses are created with the ResponseFactory.

Controllers can be registered through Router.add(Controller)

The HyperMedia interface allows to associate a link to a model object (HATEOS)

The test data is loaded with TwitterUser.loadData() and can be obtained via TwitterUser.get(userId)
and TwitterFeedItem.get(itemId)

===============Build==================

With Eclipse:

1. Import...>>Existing Projects into Workspace
2. Add javax.json-1.0.jar au BuildPath
3. Right Click HttpServer>>Run as Java Application

