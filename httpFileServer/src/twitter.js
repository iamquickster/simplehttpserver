function init() {
}

function getUser(userId) {
	var users;
	var httpRequest = new XMLHttpRequest();
	httpRequest.onreadystatechange = function() {
		if(httpRequest.readyState == 4) {
			if(httpRequest.status == 200) {
				document.getElementById("suggestions").innerHTML = "";				
				users = JSON.parse(httpRequest.responseText);
				
				console.log(users);
				for(var i = 0 ; i < users.length; i++) {
					document.getElementById("suggestions").innerHTML += "<a href='#' onclick='loadUser(\"" + users[i].name + "\",\"" + users[i].link + "\")'>" + users[i].name +"</a>";
				}
				
			}
		}
	}
	
	httpRequest.open('GET', '/users/' + userId + '/feed/');
	httpRequest.send();
	
	
}
function loadUser( userName ) {
	var link='/users/' + userName + '/feed/';
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
						document.getElementById("tweets").innerHTML += "<li class='list-group-item tweet' id=\"tweetId-" + feed[i].id 
							+ "\">" + feed[i].date + ":" + feed[i].message 
							+ "<a onclick=\"deleteTweet(" + feed[i].id+ ")\"><span class=\"glyphicon glyphicon-remove\"></span></a>"
							+ "</li>";
					}
				}
				
			}
		}
	}
	
	httpRequest.open('GET', link);
	httpRequest.send();
	loadFollows();
	document.getElementById("user-content").style.display = "block";
}

function loadFollows() {
	var userName = document.getElementById("user-picked").textContent;
	var link = "/users/" + userName + "/followees/";
	var httpRequest = new XMLHttpRequest();
	httpRequest.onreadystatechange = function() {
		if(httpRequest.readyState == 4) {
			if(httpRequest.status == 200) {
				var feed = JSON.parse(httpRequest.responseText);
				
				console.log(feed);
				document.getElementById("followees").innerHTML = "";
				for(var i = 0; i < feed.length ; i++ ) {				
					document.getElementById("followees").innerHTML += "<li class='list-group-item followee' id=followeeId-" 
						+ feed[i].name + ">" + feed[i].name +
						"<a onclick=\"unfollow('" + feed[i].name+ "')\"><span class=\"glyphicon glyphicon-remove\"></span></a>"
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
		var link = "/users/" + userName.textContent + "/tweets/" + tweet.getAttribute('id').replace("tweetId-", "");
	} else {
		var link = "/users/" + userName.textContent + "/retweets/" + tweet.getAttribute('id').replace("tweetId-", "");
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

function unfollow(followeeId) {
	var userName = document.getElementById("user-picked");
	var followee = document.getElementById("followeeId-" + followeeId);
	var link = "/users/" + userName.textContent + "/followees/" + followee.getAttribute('id').replace("followeeId-", "");
	var httpRequest = new XMLHttpRequest();
	httpRequest.onreadystatechange = function() {
		if(httpRequest.readyState == 4) {
			if(httpRequest.status == 200) {
				followee.remove();
			}
		}
	}
	httpRequest.open('DELETE', link);
	httpRequest.send();
}

function createTweet() {

	var userName = document.getElementById("user-picked");
	var tweet = document.getElementById("newTweet");
	var link = "/users/" + userName.textContent + "/tweets/";
	var httpRequest = new XMLHttpRequest();
	httpRequest.onreadystatechange = function() {
		if(httpRequest.readyState == 4) {
			if(httpRequest.status == 200) {
				loadUser( userName.textContent, "/users/" + userName.textContent + "/feed/" );
			}
		}
	}
	httpRequest.open('POST', link);
	httpRequest.send(tweet.value);
}

function getFollowee(userId) {
	$('#followSuggestions').empty();
	var users;
	var httpRequest = new XMLHttpRequest();
	httpRequest.onreadystatechange = function() {
		if(httpRequest.readyState == 4) {
			if(httpRequest.status == 200) {
				document.getElementById("followSuggestions").innerHTML = "";				
				users = JSON.parse(httpRequest.responseText);
				
				console.log(users);
				for(var i = 0 ; i < users.length; i++) {
					document.getElementById("followSuggestions").innerHTML += "<a href='#' onclick='followUser(\"" + users[i].name + "\")'>" + users[i].name +"</a>";
				}
				
			}
		}
	}
	
	httpRequest.open('PUT', '/users/' + userId + '/feed/');
	httpRequest.send();
	
	
}

function followUser(userId) {

	var userName = document.getElementById("user-picked");
	var link = "/users/" + userName.textContent + "/followees/" + userId;
	var httpRequest = new XMLHttpRequest();
	httpRequest.onreadystatechange = function() {
		if(httpRequest.readyState == 4) {
			if(httpRequest.status == 200) {
				loadUser( userName.textContent, "/users/" + userName.textContent + "/feed/" );
			}
		}
	}
	httpRequest.open('PUT', link);
	httpRequest.send();
}