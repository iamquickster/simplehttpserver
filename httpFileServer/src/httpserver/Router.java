package httpserver;

import java.util.List;

public class Router {

	private List<Controller> routes;
	
	private ResponseFactory responseFactory = ResponseFactory.newInstance();

	public void add(Controller controller) {
		this.routes.add(controller);
	}

	/*
	 * Route une requete à la première Route qui l'accepte
	 * Sinon une reponse 404 est générer
	 */
	public Response route(Request request) {
		if(request.getUri().equals("/")) {
			return responseFactory.createResponse(403);
		}
		for (Controller route : this.routes) {
			if (route.accept(request)) {
				return route.action(request);
			}
		}
		return responseFactory.createResponse(404);
	}

}
