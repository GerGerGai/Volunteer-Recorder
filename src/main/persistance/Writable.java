package persistance;

import org.json.JSONObject;

public interface Writable {

    //EFFECTS: returns as Json Object
    JSONObject toJson();

}
