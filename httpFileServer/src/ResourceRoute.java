import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/*
 * Route qui retourne une ressource, si elle existe dans le répertoir /resources, dans une Reponse HTTP
 * Accept seulement les méthodes GET 
 */
public class ResourceRoute implements Route {

	private static final String RESOURCE_DIR = "/resources";
	private ResponseFactory responseFactory = ResponseFactory.newInstance();

	/*
	 * Verifie si la requete est un GET et que une ressource avec le meme nom que URL existe
	 * (non-Javadoc)
	 * @see Route#accept(Request)
	 */
	@Override
	public boolean accept(Request request) {
		if(!request.getMethod().equals("GET")) {
			return false;
		}
		if(request.getUri().matches("\\/*")) {
			return false;
		}
		System.out.println("Path veryfied: " + RESOURCE_DIR + request.getUri());
		return this.getClass().getResource(RESOURCE_DIR + request.getUri())!=null;
	}
	/*
	 * Retourne une reponse HTTP contenant le fichier pointer par l'url de la 
	 * requete et identifie le format de ce dernier.
	 * (non-Javadoc)
	 * @see Route#action(Request)
	 */
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
			response.setContent(contentType, content, "utf-8");
			
			return response;
			} catch ( IOException e) {
				return responseFactory.createResponse(500);
			}
		}
	}

	private String getContentType(String uri) {

		String extention=uri.substring(uri.lastIndexOf(".")+1);
		String contentType;
		if(extention.equalsIgnoreCase("avi")){
			contentType="video/avi";
		}else if(extention.equalsIgnoreCase("bin")){
			contentType="application/x-binary";
		}else if(extention.equalsIgnoreCase("bmp")){
			contentType="image/bmp";
		}else if(extention.equalsIgnoreCase("txt")){
			contentType="text/plain";
		}else if(extention.equalsIgnoreCase("class" )){
			contentType="application/java";
		}else if(extention.equalsIgnoreCase("cpp") ){
			contentType="text/x-c";
		}else if(extention.equalsIgnoreCase("css" )){
			contentType="text/css";
		}else if(extention.equalsIgnoreCase("css" )){
			contentType="application/msword";
		}else if(extention.equalsIgnoreCase("gz") ||extention.equalsIgnoreCase("gzip") ){
			contentType="application/x-gzip";
		}else if(extention.equalsIgnoreCase("jpeg") ||extention.equalsIgnoreCase("jpg") ){
			contentType="image/jpeg";
		}else if(extention.equalsIgnoreCase("js") ){
			contentType="text/javascript";
		}else if(extention.equalsIgnoreCase("mp3") ){
			contentType="audio/mpeg3";
		}else if(extention.equalsIgnoreCase("o") ){
			contentType="application/octet-stream";
		}else if(extention.equalsIgnoreCase("pdf") ){
			contentType="application/pdf";
		}else if(extention.equalsIgnoreCase("png") ){
			contentType="image/png";
		}else if(extention.equalsIgnoreCase("ppt") ){
			contentType="application/powerpoint";
		}else if(extention.equalsIgnoreCase("py") ){
			contentType="text/x-script.phyton";
		}else if(extention.equalsIgnoreCase("sh" )){
			contentType="text/x-script.sh";
		}else if(extention.equalsIgnoreCase("xls") ){
			contentType="application/x-excel";
		}else if(extention.equalsIgnoreCase("xml" )){
			contentType="application/xml";
		}else if(extention.equalsIgnoreCase("zip") ){
			contentType="application/zip";
		}else
			contentType="text/html";
		return contentType;
	}

}
