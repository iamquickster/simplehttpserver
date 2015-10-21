

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class Library {

	private JsonArray catalog;

	public Library(String catalogFile) {
					JsonReader rdr = Json.createReader(this.getClass().getResourceAsStream("/resources/" + catalogFile));
					
					this.catalog = rdr.readArray();
	}

	public static void main(String[] args) {
		Library catalog = new Library("catalog.json");
		catalog.prettyCount();
		catalog.dateFilter(1990);
		catalog.inStockFilter();
	}

	private List<JsonObject> inStockFilter() {
		ArrayList<JsonObject> result = new ArrayList<JsonObject>();
		System.out.println("Albums in stock:");
		boolean empty = true;
		for (JsonObject album: catalog.getValuesAs(JsonObject.class)) {
			if(album.getBoolean("instock")) {
				empty = false;
				result.add(album);
				System.out.println(album.getJsonString("title") + ": " + album.getJsonNumber("price") + "$");
			}
		}
		
		if(empty) {
			System.out.println("None.");
		}
		return result;
	}

	private List<JsonObject> dateFilter(int from) {
		ArrayList<JsonObject> result = new ArrayList<JsonObject>();
		System.out.println("Albums after " + from + ":");
		boolean empty = true;
		for (JsonObject album: catalog.getValuesAs(JsonObject.class)) {
			if(album.getJsonNumber("year").intValue() >= from) {
				empty = false;
				result.add(album);
				System.out.println(album.getJsonString("title"));
			}
		}
		
		if(empty) {
			System.out.println("None.");
		}
		return result;
	}

	private void prettyCount() {
		System.out.println("There is " + catalog.size() + " albums in the catalog.");
	}

	public List<JsonObject> priceFilter(double lower, double higher) {
		ArrayList<JsonObject> result = new ArrayList<JsonObject>();
		System.out.println("Albums between " + lower + " to " + higher);
		boolean empty = true;
		for (JsonObject album: catalog.getValuesAs(JsonObject.class)) {
			if(album.getJsonNumber("price").doubleValue() >= lower && album.getJsonNumber("price").doubleValue() <= higher ) {
				empty = false;
				result.add(album);
				System.out.println(album.getJsonString("title"));
			}
		}
		
		if(empty) {
			System.out.println("None.");
		}
		return result;
	}

	public List<JsonObject> filter(Map<String, String> params) {
		List<JsonObject> result = new ArrayList<JsonObject>();
		if (params.isEmpty()) {
			for (Object item : catalog.toArray()) {
				result.add((JsonObject)item);
				
			}
		}
		if(params.get("inStock") != null && params.get("inStock").equals("true")) {
			result.addAll(inStockFilter());
		}
		if(params.get("price") != null) {
			result.addAll(priceFilter(Double.parseDouble(params.get("price").split("-")[0]),Double.parseDouble(params.get("price").split("-")[1] )));
		}
		if(params.get("date")!=null) {
			result.addAll(priceFilter(Double.parseDouble(params.get("date").split("-")[0]),Double.parseDouble(params.get("date").split("-")[1] )));
		}
		return result;
	}

}
