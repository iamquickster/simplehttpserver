package httpserver;


import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import httpserver.services.twitter.controller.TwitterUserController;

/*
 * Serveur HTTP qui est responsable enregistrer different route poosible pour une requete
 */
public class HttpServer {

	private ServerSocket server;
	private Router router = new Router();


	public HttpServer(int port) {
		try {
			this.server = new ServerSocket(port);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/*
	 * Ajouter une route possible pour une requete donnée
	 */
	public void registerController(Controller controller) {
		router.add(controller);
	}

	public static void main(String[] args) {
		
		HttpServer server = null;
		if(args.length == 0 ) {
			server = new HttpServer(8080);
		} else {
			server = new HttpServer(Integer.parseInt(args[0]));
			
		}
		
		server.registerController(new TwitterUserController());

		server.start();

	}

	/*
	 * Lancer le server qui accept les requetes et les routes au service approprié
	 */
	private void start() {
		System.out.println("Listening on " + server.getLocalPort());
		while (true) {
			
			
			try {

				Socket socket = server.accept();
				InputStream in = socket.getInputStream();
					
					Request request = null;
					Response response = null;
					try {
						//créer une requete
						request = new Request(in);
						//router la requete
						response = router.route(request);
					} catch (BadRequestException e) {
						response = Response.BAD_REQUEST;
					}
					
					System.out.println(response);
					//envoyer la reponse
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

}
