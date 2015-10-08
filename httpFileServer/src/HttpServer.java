
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class HttpServer {

	private ServerSocket server;
	private List<Route> routes = new ArrayList<Route>();
	private ResponseFactory responseFactory = ResponseFactory.newInstance();

	public HttpServer(int port) {
		try {
			this.server = new ServerSocket(port);
			registerRoute(new ResourceRoute());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private void registerRoute(Route route) {
		routes.add(route);
	}

	public static void main(String[] args) {
		
		HttpServer server = null;
		if(args.length == 0 ) {
			server = new HttpServer(8080);
		} else {
			server = new HttpServer(Integer.parseInt(args[0]));
			
		}


		server.start();

	}

	private void start() {
		System.out.println("Listening on " + server.getLocalPort());
		while (true) {
			
			
			try {

				Socket socket = server.accept();
				InputStream in = socket.getInputStream();
					
					Request request = null;
					Response response = null;
					try {
						request = new Request(in);
						response = route(request);
					} catch (BadRequestException e) {
						// TODO Auto-generated catch block
						response = responseFactory.createResponse(400);
					}
					
					System.out.println(response);
					PrintWriter out = new PrintWriter(socket.getOutputStream());
					out.print(response.toString());
					out.flush();
					out.close();
					
					in.close();

			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

	private Response route(Request request) {
		if(request.getUri().equals("/")) {
			return responseFactory.createResponse(403);
		}
		for (Route route : this.routes) {
			if (route.accept(request)) {
				return route.action(request);
			}
		}
		return responseFactory.createResponse(404);
	}

}
