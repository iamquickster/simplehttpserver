function getUser(userId) {

	var users;
	var httpRequest = new XMLHttpRequest();
	httpRequest.onreadystatechange = function() {
		if(httpRequest.readyState == 4) {
			if(httpRequest.status == 200) {
				document.getElementById("mainViewPort").innerHTML = "";				
				users = JSON.parse(httpRequest.responseText);
				
				console.log(users);
				for(var i = 0 ; i < users.length; i++) {
					document.getElementById("mainViewPort").innerHTML += "<div onclick='loadUser(\"" + users[i].name + "\",\"" + users[i].link + "\")'>" + users[i].name +"</div>";
					
				}
				
			}
		}
	}
	
	httpRequest.open('GET', '/utilisateurs/' + userId + '/fil/');
	httpRequest.send();
}

function loadUser( userName, link ) {
	document.getElementById("mainViewPort").innerHTML = "<div><h1>" + userName + "</h1></div>";
	var httpRequest = new XMLHttpRequest();
	httpRequest.onreadystatechange = function() {
		if(httpRequest.readyState == 4) {
			if(httpRequest.status == 200) {
				var feed = JSON.parse(httpRequest.responseText);
				
				console.log(feed);
				for(var i = 0; i < feed.length ; i++ ) {
					if(feed[i].tweet) {
						document.getElementById("mainViewPort").innerHTML += "<div class='reTweet'>" + feed[i].tweet.date + ":" + feed[i].owner + "-->" + feed[i].tweet.message + "</div>";
					} else {
						document.getElementById("mainViewPort").innerHTML += "<div class='tweet'>" + feed[i].date + ":" + feed[i].message + "</div>";
					}
				}
				// TODO Create user dashboard
				// functions : show feed + buttons to tweet and retweet
				
			}
		}
	}
	
	httpRequest.open('GET', link);
	httpRequest.send();
}