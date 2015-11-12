package httpserver;


/*
 * Frabrique de réponse HTTP
 * Crée des templates de réponse HTTP
 */
public class ResponseFactory {

	public static ResponseFactory newInstance() {
		return new ResponseFactory();
	}

	public Response createResponse(int i) {

		switch (i) {
		case 200:
			return new Response(200, Response.HTML_CONTENT_TYPE, "<h1>200 ok</h1>");
		case 400:
			return new Response(400, Response.HTML_CONTENT_TYPE, "<h1>400 Bad Request</h1>");
		case 403:
			return new Response(403,  Response.HTML_CONTENT_TYPE, "<h1>403 Forbidden</h1>");
		case 404:
			return new Response(404,  Response.HTML_CONTENT_TYPE, "<h1>404 Not Found</h1>");
		case 405:
			return new Response(405,  Response.HTML_CONTENT_TYPE, "<h1>404 Method not allowed</h1>");
		default:
			return new Response(404,  Response.HTML_CONTENT_TYPE, "<h1>404 Not Found</h1>");
		}

	}

}
