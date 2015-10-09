----------------HttpServer----------------
Cette application est un serveur Http qui permet à un utilisateur 
d'obtenir des ressources ébergé sur ce dernier. L'utilisateur peux faire
ses requêtes directement avec un fureteur en spécifiant le nom 
de la ressource dans url. 

Exemple : Localhost:8080/fun.txt

Le format de la ressource retourné est prédefini par le serveur. Il se fit de l'extention
du fichier pour choisir le Content-Type afin de déléguer la responsabilité au fureteur de
choisir comment utiliser la ressource.

Voici les formats supportés:
	avi, bin, bmp, txt,	class, cpp,	css, gz, jpeg, jpg, js,	mp3,o,pdf,png,ppt,py,sh,xls,xml,zip
	
Si l'extension de la ressource n'est pas la liste ci-dessus, le format est text/html

Le port par défaut du server est 8080 mais il peut être spécifier lors du lancement.

Exemple : ./HttpServer.exe 8081

Le serveur route les requêtes avec des Object qui implémente l'interface Route.
Une route identifie les requetes qu'elle accept et generer une réponse que le serveur envoie.
Si deux route accept une requête, la première qui à été enregistrer répond à la requêtes.

Les réponses sont générer par ResponseFactory, modifier par La Route et envoyer par le HttpServeur.

Une nouvelle Route peu être enregistrer avec la méthode HttpServer.registerRoute(),
Il faut simplement implémenter les méthodes accept() et action() de Route.

===============Build==================

Avec Eclipse:

1. Import...>>Existing Projects into Workspace
2. Choisir le répertoire racine httpFileServer
3. Right Click HttpServer>>Run as Java Application
	Note: Si vous voulez spécifier un port, ajouter le numéro de port Run Configurations>> Arguments

=============Testes==================

Dans un fureteur essayez ces url:
http://localhost:8080/				---->	Forbidden 403
http://localhost:8080/fun.txt		---->	fun!
http://localhost:8080/fun.zip		---->	Téléchargement du ficher fun.zip
http://localhost:8080/fun.zip?id=2	---->	Téléchargement du ficher fun.zip
http://localhost:8080/not.fun		---->	Not Found 404

Pour testé d'autres type de fichier, ajoutez les au package "resources"
