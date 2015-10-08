


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

public class Response {

	private static final String DEFAULT_RESPONSE_LINE = "HTTP/1.1 200 OK\r\n";
	private static final String CONTENT_TYPE_HEADER = "Content-Type";
	public static final String HTML_CONTENT_TYPE = "text/html";
	private static final String CONTENT_LENGTH_HEADER = "Content-Length";
	private static final String DATE_HEADER = "Date";
	private String responseLine;
	private Map<String,String> headers = new HashMap<String, String>();
	private String body;
	private Map<Integer, String> responseLines = new HashMap<Integer, String>();
	public ResponseFactory responseFactory = ResponseFactory.newInstance();
	private SimpleDateFormat rfc1123 = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.CANADA);;
	
	public Response() { 
		this.responseLines.put(200, "HTTP/1.1 200 OK\r\n");
		this.responseLines.put(400, "HTTP/1.1 400 Bad Request\r\n");
		this.responseLines.put(403, "HTTP/1.1 403 Forbidden\r\n");
		this.responseLines.put(404, "HTTP/1.1 404 Not Found\r\n");
		this.responseLines.put(500, "HTTP/1.1 500 Internal Server Error\r\n");
	}


	public Response(String body) {
		this();
		this.responseLine = getDefaultResponseLine();
		this.headers = getDefaultHeaders();
		this.body = body;
	}

	public Response(int i, String contentType, String content) {
		this();
		this.responseLine = this.responseLines.get(i);
		this.headers.put(CONTENT_TYPE_HEADER, contentType);
		this.headers.put(CONTENT_LENGTH_HEADER, Integer.toString(content.getBytes().length));
		this.headers.put(DATE_HEADER, rfc1123.format(new Date()));
		this.body = content;
	}

	private Map<String, String> getDefaultHeaders() {
		return new HashMap<String, String>();
	}

	private String getDefaultResponseLine() {
		return DEFAULT_RESPONSE_LINE;
	}
	
	public void setBody(String body) {
		headers.put(CONTENT_LENGTH_HEADER, Integer.toString(body.getBytes().length));
		this.body = body;
	}
	
	@Override
	public String toString() {
		String response = responseLine;
		
		for (Entry<String, String> header : headers.entrySet()) {
			response += header.getKey() + ": " + header.getValue() + "\r\n";
		}
		response += "\r\n";
		
		response += body;
		return response;
		
	}


	public void setContent(String contentType, String content) {
		this.headers.put(CONTENT_TYPE_HEADER, contentType);
		setBody(content);
	}
	
}
