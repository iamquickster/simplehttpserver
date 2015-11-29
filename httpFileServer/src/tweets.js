$(document).ready(function() {
			
            $("#userId").click(function(event){
            	var uri = '/utilisateurs/' + $(this).val() + '/fil/';
            	var html = "";
            	//création des balises html (vue)
            	html += "<div class=\"col-md-8\"> +
            			"<ul class=\"nav nav-pills\">" +
            			"<li role=\"presentation\" class=\"active\"><a href=\"#\">Tweets</a></li>" +
            			"<li role=\"presentation\"><a href=\"#\">Abonnements</a></li>" +
            			"</ul>" +
			            "<ul class=\"list-group\">";
            	// requete Get, elle récupère les données en Json
               $.get( 
                  uri,
                  function(data) {
                	  $.each(data, function(index, element) {
                		  html += "<li class=\"list-group-item\">" +
      							  "<p>" + element.message + "</p>" +
      							  "<a href=\"#\">" + 
            					  "<span class=\"glyphicon glyphicon-remove\"></span>" + 
          				          "</a></li>";
                      });
                  }
               );
               
               html += "</ul></div>";
               html += "<div class=\"col-md-4\"> +
  				       "<form>" +
				       "<div class=\"form-group\">"+
				       "<label for=\"exampleInputEmail1\">Tweet</label>"+
				       "<textarea class=\"form-control\" rows=\"3\" placeholder=\"Message du tweet\" required></textarea>"+
  					   "</div>"+
				       "<button type=\"submit\" class=\"btn btn-success\">Envoyer</button>"+
				       "</form>"+
				       "</div>"+
				       "</div>";
               //Injecter dans MainView de façon dynamique.
               $('#mainView').html(html);
            });
				
         });