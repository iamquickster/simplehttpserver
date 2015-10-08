


public interface Route {
	public boolean accept(Request request);
	
	public Response action(Request request);
}
