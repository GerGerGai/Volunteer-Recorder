package persistance;

import org.json.JSONObject;

// Codes inspired by "https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo"
public interface Writable {

    //EFFECTS: returns as Json Object
    JSONObject toJson();

}
