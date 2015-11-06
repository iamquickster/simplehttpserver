package other;
import java.text.MessageFormat;
import java.util.List;

import javax.json.JsonObject;

public class LibraryView {

	private String htmlView;

	public LibraryView(List<JsonObject> model) {
		StringBuilder view = new StringBuilder();
		view.append("<!DOCTYPE html>");
		view.append("<html>");
		view.append("<head>");
		view.append("</head>");
		view.append("<body>");
		view.append("<table>");
		view.append("<tr><td>ID</td><td>TITLE</td><td>ARTIST</td><td>INSTOCK</td><td>PRICE</td><td>YEAR</td></tr>");
		for (JsonObject item : model) {
			view.append(MessageFormat
					.format("<tr><td>{0}</td><td>{1}</td><td>{2}</td><td>{3}</td><td>{4}</td><td>{5}</td></tr>",
							item.getInt("id"), item.getString("title"),
							item.getString("artist"),
							item.getString("instock"), item.getString("price"),
							item.getString("year")));
		}
		view.append("</table>");
		view.append("</body>");
		view.append("</html>");
		
		this.htmlView = view.toString();

	}
	
	public String toString() {
		return htmlView;
	}

}
