----------------HttpServer----------------
<<<<<<< HEAD

<<<<<<< HEAD
Authors: Pascal Feo, Chakib TchantChane and Nicolas Rivet
This applciation is a basic server which show the inner workings of a
single page website with a REST API. The example implemented here is a
fictitious twitter account that is able to post and subscribe to other
users.
It is not a description of the inner workings of Twitter but an example
of how to implement a HTTP Server using JAVA.

The API REST:
=======
Exemple : Localhost:8080/index.html

Page d'acceuil:
La page d'acceuil est une interface Ajax qui permet de:
	Trouver un utilisateur
	Afficher le fil d'un utilisateur
	Soumettre un tweet
	Supprimer un tweet
	S'abonner à un utilisateur
	Se désabonner à un utilisateur

Les utilisateurs déjà inclus dans le système sont:
	TheGood
	TheBad
	TheUgly
	
La logique des appeles AJAX sont dans le fichier twitter.js, et le stylesheet est twitter.css.

La page est accessible de l'url "/index.html".
=======
>>>>>>> be1c63a90dbf6661334b445527fd9ea13c14212d

Authors: Pascal Feo, Chakib TchantChane and Nicolas Rivet
This applciation is a basic server which show the inner workings of a
single page website with a REST API. The example implemented here is a
fictitious twitter account that is able to post and subscribe to other
users.
It is not a description of the inner workings of Twitter but an example
of how to implement a HTTP Server using JAVA.

<<<<<<< HEAD
API REST JSON:
>>>>>>> d6441de7509a2fc52aa31d65067d2189c94023c0
=======
The API REST:
>>>>>>> be1c63a90dbf6661334b445527fd9ea13c14212d
	
	GET /users/{userId}/feed/
		Get user feed
		
	GET /users/{userId}/tweets/
		Obtient les tweets de l'utilisateur donné.
	
	POST /users/{userId}/tweets/
		Ajoute un nouveau tweet avec le message dans la requete
	
	GET /users/{userId}/tweets/{tweetId}/
		Obtient un des tweet de l'utilisateur donné.
	
	DELETE /users/{userId}/tweets/{tweetId}/
		Supprime un des tweet de l'utilisateur donné.
	
	PUT /users/{userId}/tweets/{tweetId}/
		Modifie un des tweet de l'utilisateur donné.
		
	GET /users/{userId}/retweets
		Obtient les retweets de l'utilisateur donné.
	
	POST /users/{userId}/retweets?tweetId={tweetID}/
		Retweet du tweet avec identifiant {tweetId}
		**TweetId est obligatoire**
	
	GET /users/{userId}/retweets/{retweetId}/
		Obtient un des retweet de l'utilisateur donné.
	
	DELETE /users/{userId}/retweets/{retweetId}/
		Supprime un des retweet de l'utilisateur donné.
		
	GET /users/{userId}/followees/
		Obtient les abonnement de l'utilisateur donné.
		
	GET /users/{userId}/followees/{followeeId}/
		Obtient un des abonnement de l'utilisateur donné.
	
	DELETE /users/{userId}/followees/{followeeId}/
		Supprime un des abonnement de l'utilisateur donné.
	
	PUT /users/{userId}/followees/{followeeId}/
		Modifie un des abonnements de l'utilisateur donné.
	

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
<<<<<<< HEAD
<<<<<<< HEAD
=======
	Note: Si vous voulez spécifier un port, ajouter le numéro de port Run Configurations>> Arguments
4. Ouvrer un fureteur à la page localhost:[port]/index.html
>>>>>>> d6441de7509a2fc52aa31d65067d2189c94023c0
=======
>>>>>>> be1c63a90dbf6661334b445527fd9ea13c14212d

