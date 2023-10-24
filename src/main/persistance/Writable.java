package persistance;

import org.json.JSONObject;

public interface Writable {

    //EFFECTS: returns a Json Object
    JSONObject toJson();

}
