import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ResourceRoute implements Route {

	private static final String RESOURCE_DIR = "/resources";
	private ResponseFactory responseFactory = ResponseFactory.newInstance();

	@Override
	public boolean accept(Request request) {
		if(request.getUri().matches("\\/*")) {
			return false;
		}
		System.out.println("Path veryfied: " + RESOURCE_DIR + request.getUri());
		return this.getClass().getResource(RESOURCE_DIR + request.getUri())!=null;
	}

	@Override
	public Response action(Request request) {
		
		// get resource
		InputStream resourceStream = this.getClass().getResourceAsStream(RESOURCE_DIR + request.getUri());
		
		
		if (resourceStream == null) {
			return responseFactory.createResponse(404);
		} else {
			try {
				
			BufferedReader contentStream = new BufferedReader(new InputStreamReader(resourceStream));
			String contentLine = contentStream.readLine();
			String content = "";
			while (contentLine != null) {
				content += contentLine;
				contentLine = contentStream.readLine();
			}
			
			contentStream.close();
			String contentType = getContentType(request.getUri());
			
			Response response = responseFactory.createResponse(200);
			response.setContent(contentType, content);
			
			return response;
			} catch ( IOException e) {
				return responseFactory.createResponse(500);
			}
		}
	}

	private String getContentType(String uri) {

		String extention=uri.substring(uri.lastIndexOf(".")+1);
		String contentType;
		if(extention.equals("avi")){
			contentType="video/avi";
		}else if(extention.equals("bin")){
			contentType="application/x-binary";
		}else if(extention.equals("bmp")){
			contentType="image/bmp";
		}else if(extention.equals("txt")){
			contentType="text/plain";
		}else if(extention.equals("class" )){
			contentType="application/java";
		}else if(extention.equals("cpp") ){
			contentType="text/x-c";
		}else if(extention.equals("css" )){
			contentType="text/css";
		}else if(extention.equals("css" )){
			contentType="application/msword";
		}else if(extention.equals("gz") ||extention.equals("gzip") ){
			contentType="application/x-gzip";
		}else if(extention.equals("jpeg") ||extention.equals("jpg") ){
			contentType="image/jpeg";
		}else if(extention.equals("js") ){
			contentType="text/javascript";
		}else if(extention.equals("mp3") ){
			contentType="audio/mpeg3";
		}else if(extention.equals("o") ){
			contentType="application/octet-stream";
		}else if(extention.equals("pdf") ){
			contentType="application/pdf";
		}else if(extention.equals("png") ){
			contentType="image/png";
		}else if(extention.equals("ppt") ){
			contentType="application/powerpoint";
		}else if(extention.equals("py") ){
			contentType="text/x-script.phyton";
		}else if(extention.equals("sh" )){
			contentType="text/x-script.sh";
		}else if(extention.equals("xls") ){
			contentType="application/x-excel";
		}else if(extention.equals("xml" )){
			contentType="application/xml";
		}else if(extention.equals("zip") ){
			contentType="application/zip";
		}else
			contentType="text/html";
		return contentType;
	}

}
