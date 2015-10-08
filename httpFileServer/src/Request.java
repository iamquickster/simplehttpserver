


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Request {

	private static final String CONTENT_LENGTH_HEADER = "Content-Length";
	private String method;
	private String uri;
	private String version;
	private String body;
	private Map<String, String> headers = new HashMap<String, String>();
	private String params;
	private String anchor;

	public Request(InputStream inputStream) throws BadRequestException {

		BufferedReader in = new BufferedReader(new InputStreamReader(
				inputStream));
		try {
			String requestLine = in.readLine();
			if (requestLine == null ) {
				throw new BadRequestException();
			}
			String[] requestLineTokens = requestLine.split(" ");
			if(requestLineTokens.length != 3) {
				throw new BadRequestException();
			}
			this.method = requestLineTokens[0];
			String[] uriTokens = requestLineTokens[1].split("\\?");
			this.uri = uriTokens[0];
			
			this.version = requestLineTokens[2];

			String headerLine = in.readLine();
			String[] headerTokens = null;
			while (!headerLine.equals("")) {
				headerTokens = headerLine.split(": ");
				headers.put(headerTokens[0], headerTokens[1]);
				headerLine = in.readLine();
			}
			
			int contentLength  = 0;
			
			if(headers.get(Request.CONTENT_LENGTH_HEADER) != null) {
				contentLength = Integer.parseInt(headers.get(Request.CONTENT_LENGTH_HEADER));
			}
			
			char[] buff = new char[contentLength];
			
			in.read(buff, 0, buff.length);
			
			
			
			this.body = new String(buff);
			
			System.out.println(this);
			
		} catch (IOException e) {
			e.printStackTrace();
			throw new BadRequestException();
		}
	}


	public String getUri() {
		return uri;
	}
	
	public String toString() {
		String request = method + " " + uri + " " + version + "\r\n";
		
		
		for (Entry<String, String> header : headers.entrySet()) {
			request += header.getKey() + ": " + header.getValue() + "\r\n";
		}
		request += "\r\n";
		
		request += body;
		return request;
	}

}
