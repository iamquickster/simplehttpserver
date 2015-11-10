package httpserver;

import javax.json.JsonObject;

public interface JsonSerializable {
	public JsonObject toJson();
}
