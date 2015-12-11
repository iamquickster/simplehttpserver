Auteurs:
	Pascal Feo FEOP09088803
	Nicolas Rivet Rivn13098901
	Chakib Tchantchane Tchc25049201

----------------HttpServer----------------
Cette application est un serveur Http qui permet à un utilisateur Twitter
d'obtenir des ressources associées à son compte. L'utilisateur peux faire
ses requêtes directement avec un fureteur en spécifiant le nom 
de la ressource. 

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


API REST JSON:
	
	GET /utilisateurs/{utilisateurId}/fil/
		Obtient le fil de tweets(feed) de l'utilisateur donne
		
	GET /utilisateurs/{utilisateurId}/tweets/
		Obtient les tweets de l'utilisateur donné.
	
	POST /utilisateurs/{utilisateurId}/tweets/
		Ajoute un nouveau tweet avec le message dans la requete
	
	GET /utilisateurs/{utilisateurId}/tweets/{tweetId}/
		Obtient un des tweet de l'utilisateur donné.
	
	DELETE /utilisateurs/{utilisateurId}/tweets/{tweetId}/
		Supprime un des tweet de l'utilisateur donné.
	
	PUT /utilisateurs/{utilisateurId}/tweets/{tweetId}/
		Modifie un des tweet de l'utilisateur donné.
		
	GET /utilisateurs/{utilisateurId}/retweets
		Obtient les retweets de l'utilisateur donné.
	
	POST /utilisateurs/{utilisateurId}/retweets?tweetId={tweetID}/
		Retweet du tweet avec identifiant {tweetId}
		**TweetId est obligatoire**
	
	GET /utilisateurs/{utilisateurId}/retweets/{retweetId}/
		Obtient un des retweet de l'utilisateur donné.
	
	DELETE /utilisateurs/{utilisateurId}/retweets/{retweetId}/
		Supprime un des retweet de l'utilisateur donné.
		
	GET /utilisateurs/{utilisateurId}/abonnements/
		Obtient les abonnement de l'utilisateur donné.
		
	GET /utilisateurs/{utilisateurId}/abonnements/{abonnementId}/
		Obtient un des abonnement de l'utilisateur donné.
	
	DELETE /utilisateurs/{utilisateurId}/abonnements/{abonnementId}/
		Supprime un des abonnement de l'utilisateur donné.
	
	PUT /utilisateurs/{utilisateurId}/abonnements/{abonnementId}/
		Modifie un des abonnements de l'utilisateur donné.
	

Le port par défaut du server est 8080 mais il peut être spécifier lors du lancement.

Le serveur utilise un Router qui réparti les requêtes aux Controlleurs. 
Le Controlleur TwitterUserController est responsable de parser la requêtes et choisir
la vue associé. Le controlleur cherche les Objets du model nécéssaire et les passe à la vue approrié.
Ensuite, la vue converti le model en json ( model.toJson() ) et retourne une représentation json
de l'objet. S'il n'a rien à retourner, une page HTML est retourner avec le statut HTTP.

Les réponses sont générer par ResponseFactory, modifier par le Controlleur et envoyer par le HttpServeur.

Un controlleur peu être enregistrer avec la méthode Router.add(Controller),
Il faut simplement implémenter les méthodes accept() et action() de Controlleur.

L'interface HyperMedia permet de définir le lien associé à un object model.
L'interface JsonSerializable permet de convertir un objet model en JSON.

Les données de test sont loader par TwitterUser.loadData(), et peuvent être obtenu
par la méthode TwitterUser.get(userId) et TwitterFeedItem.get(itemId)

===============Build==================

Avec Eclipse:

1. Import...>>Existing Projects into Workspace
2. Choisir le répertoire racine httpFileServer
3. Right Click HttpServer>>Run as Java Application
	Note: Si vous voulez spécifier un port, ajouter le numéro de port Run Configurations>> Arguments
4. Ouvrer un fureteur à la page localhost:[port]/index.html

