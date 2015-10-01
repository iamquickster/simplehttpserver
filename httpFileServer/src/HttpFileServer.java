import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class HttpFileServer {

	private static final String FILE_NOT_FOUND_RESPONSE = null;

	private static final String ROOT_DIR = "/";
	private static final String FORBIDDEN_403 = "HTTP/1.1 403 Forbidden\r\n";
	private static final String RESOURCE_DIR = "/resources";
	private ServerSocket serverSocket;

	public HttpFileServer(int i) {
		try {
			this.serverSocket = new ServerSocket(8080);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
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
				e.printStackTrace();
			}

		}

	}

	private void consumeRequest(Socket request) {

		Map<String, String> metadata = new HashMap<String, String>();

		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()));

			String nextLine;

			// parse Protocol 
			// ex: GET /fun.txt HTTP/1.1
			nextLine = in.readLine();
			if(nextLine == null) {
				return;
			}
			String[] protocolTokens = nextLine.split(" ");

			String response = null;

			if (protocolTokens[1].equals(ROOT_DIR)) {
				
				String body = "<!DOCTYPE html><html><body><h1>403 - FORBIDDEN</h1></body></html>";
				response = FORBIDDEN_403;
				response += "Content-Length: " + body.length() + "\r\n";
				response += "Content-Type: text/html\r\n\r\n";
				response += body;
			} else {

				// get resource
				InputStream resourceStream = this.getClass().getResourceAsStream(RESOURCE_DIR + protocolTokens[1]);

				if (resourceStream == null) {
					//TODO create file not found response
					response = FILE_NOT_FOUND_RESPONSE;
				} else {
					BufferedReader contentStream = new BufferedReader(new InputStreamReader(resourceStream));
					String contentLine = contentStream.readLine();
					String content = "";
					while (contentLine != null) {
						content += contentLine;
						contentLine = contentStream.readLine();
					}

					String type = getContentType(protocolTokens[1]);
					response = getResourceResponse(content, type);
				}

			}
			// Open output stream
			PrintWriter output = new PrintWriter(request.getOutputStream());
			
			// send content
			output.println(response);
			output.flush();
			output.close();
			
			in.close();

		} catch (IOException e) {
			// TODO return 500 response for internal error
			e.printStackTrace();
		}
	}

	private String getContentType(String string) {
		// TODO Get file type based on extension and return proper Content-Length
		return "text/html";
	}

	private String getResourceResponse(String content, String type) {
		// TODO Auto-generated method stub

		SimpleDateFormat rfc1123 = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.CANADA);

		String response = "HTTP/1.1 200 OK\r\n";
		response += "Content-Type: " + type + "\r\n";
		response += "Date: " + rfc1123.format(new Date()) + "\r\n";
		response += "Content-Length: " + content.length() + "\r\n";
		response += "\r\n";
		response += content;

		return response;
	}

}
