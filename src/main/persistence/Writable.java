package persistence;

import org.json.JSONObject;

// Represents 
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
