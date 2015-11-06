package other;
import httpserver.Controller;
import httpserver.Request;
import httpserver.Response;
import httpserver.ResponseFactory;


public class LibraryController implements Controller {
	
	Library model = new Library("/resources/catalog.json");
	ResponseFactory rf = ResponseFactory.newInstance();

	@Override
	public boolean accept(Request request) {
		return request.getUri().equals("library");
	}

	@Override
	public Response action(Request request) {
		
		LibraryView view = new LibraryView(model.filter(request.getParams()));
		
		Response response = rf.createResponse(200);
		
		response.setContent("text/html", view.toString(), "utf-8");
		
		return response;
	}

}
