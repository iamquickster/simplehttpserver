package httpserver;


/*
 * Interface qui identifie une route possible pour un requete (Request)
 */
public interface Controller {
	/*
	 * retourne vrai si cette route peux accepter la requete "request"
	 * si non faux
	 */
	public boolean accept(Request request);
	
	/*
	 * CETTE METHOD DOIT ETRE UTILISER APRES AVOIR ÉTÉ VALIDER PAR accept()
	 * Crée un réponse HTTP pour la requete "request"
	 */
	public Response action(Request request);
}
