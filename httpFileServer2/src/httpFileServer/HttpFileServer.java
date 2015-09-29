package httpFileServer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;


public class HttpFileServer {

	private ServerSocket serverSocket;

	public HttpFileServer(int i) {
		try {
			this.serverSocket = new ServerSocket(8080);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HttpFileServer server = new HttpFileServer(8080);
		server.start();
	}

	private void start() {
		System.out.println("Server stated. Listening on port " + serverSocket.getLocalPort() + "...");
		
		while (true) {
			try {
				Socket request = serverSocket.accept();
				
				consumeRequest(request);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

	private void consumeRequest(Socket request) {
		// TODO Auto-generated method stub
		
		Map<String, String> metadata = new HashMap<String, String>();
		
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()));
		
			String nextLine;
			
			//parse Protocol ex: GET /tutorials/other/top-20-mysql-best-practices/ HTTP/1.1
			nextLine = in.readLine();
			String[] protocalTokens = nextLine.split(" ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
