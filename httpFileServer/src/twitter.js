function getUser(userId) {

	var users;
	var httpRequest = new XMLHttpRequest();
	httpRequest.onreadystatechange = function() {
		if(httpRequest.readyState == 4) {
			if(httpRequest.status == 200) {
				document.getElementById("menu").innerHTML += "<div id='suggestions' class='suggestionList'></div>";				
				users = JSON.parse(httpRequest.responseText);
				
				console.log(users);
				for(var i = 0 ; i < users.length; i++) {
					document.getElementById("suggestions").innerHTML += "<a href='#' onclick='loadUser(\"" + users[i].name + "\",\"" + users[i].link + "\")'>" + users[i].name +"</a>";
					
				}
				
			}
		}
	}
	
	httpRequest.open('GET', '/utilisateurs/' + userId + '/fil/');
	httpRequest.send();
}
function loadUser( userName ) {
	var link='/utilisateurs/' + userName + '/fil/';
	loadUser( userName, link );
}
function loadUser( userName, link ) {
	document.getElementById("suggestions").innerHTML = "";
	document.getElementById("user-picked").innerHTML = "<h1>" + userName + "</h1>";
	var httpRequest = new XMLHttpRequest();
	httpRequest.onreadystatechange = function() {
		if(httpRequest.readyState == 4) {
			if(httpRequest.status == 200) {
				var feed = JSON.parse(httpRequest.responseText);
				
				console.log(feed);
				document.getElementById("tweets").innerHTML = "";
				for(var i = 0; i < feed.length ; i++ ) {
					if(feed[i].tweet) {
						document.getElementById("tweets").innerHTML += "<li class='list-group-item reTweet' id=tweetId-" 
							+ feed[i].id + ">" + feed[i].tweet.date + ":"
							+ feed[i].owner + " RT "+ feed[i].tweet.owner+ " --> " + feed[i].tweet.message 
							+ "<a onclick=\"deleteTweet(" + feed[i].id+ ")\"><span class=\"glyphicon glyphicon-remove\"></span></a>"
							+ "</li>";
					} else {
						document.getElementById("tweets").innerHTML += "<li class='list-group-item tweet' id=\"tweetId-" + feed[i].id + "\">" + feed[i].date + ":" + feed[i].message + "</li>";
					}
				}
				// TODO Create user dashboard
				// functions : show feed + buttons to tweet and retweet
				
			}
		}
	}
	
	httpRequest.open('GET', link);
	httpRequest.send();
	loadFollows();
}

function loadFollows() {
	var userName = document.getElementById("user-picked").textContent;
	var link = "/utilisateurs/" + userName + "/abonnements/";
	var httpRequest = new XMLHttpRequest();
	httpRequest.onreadystatechange = function() {
		if(httpRequest.readyState == 4) {
			if(httpRequest.status == 200) {
				var feed = JSON.parse(httpRequest.responseText);
				
				console.log(feed);
				document.getElementById("abonnements").innerHTML = "";
				for(var i = 0; i < feed.length ; i++ ) {				
					document.getElementById("abonnements").innerHTML += "<li class='list-group-item followee' id=followeeId-" 
						+ feed[i].name + ">" + feed[i].name +
						"<a onclick=\"unfollow(" + feed[i].name+ ")\"><span class=\"glyphicon glyphicon-remove\"></span></a>"
						+ "</li>";
				}
				
			}
		}
	}
	
	httpRequest.open('GET', link);
	httpRequest.send();
}

function deleteTweet(tweetId) {

	var userName = document.getElementById("user-picked");
	var tweet = document.getElementById("tweetId-" + tweetId);
	if(tweet.class == "tweet") {
		var link = "/utilisateurs/" + userName.textContent + "/tweets/" + tweet.getAttribute('id').replace("tweetId-", "");
	} else {
		var link = "/utilisateurs/" + userName.textContent + "/retweets/" + tweet.getAttribute('id').replace("tweetId-", "");
	}
	var httpRequest = new XMLHttpRequest();
	httpRequest.onreadystatechange = function() {
		if(httpRequest.readyState == 4) {
			if(httpRequest.status == 200) {
				tweet.remove();
			}
		}
	}
	
	httpRequest.open('DELETE', link);
	httpRequest.send();
}

function createTweet() {

	var userName = document.getElementById("user-picked");
	var tweet = document.getElementById("newTweet");
	var link = "/utilisateurs/" + userName.textContent + "/tweets/";
	var httpRequest = new XMLHttpRequest();
	httpRequest.onreadystatechange = function() {
		if(httpRequest.readyState == 4) {
			if(httpRequest.status == 200) {
				var feed = JSON.parse(httpRequest.responseText);
				
							
			}
		}
	}
	httpRequest.open('POST', link);
	httpRequest.send();
	var link = "/utilisateurs/" + userName.textContent + "/fil/";
	loadUser( userName.textContent, link );
}