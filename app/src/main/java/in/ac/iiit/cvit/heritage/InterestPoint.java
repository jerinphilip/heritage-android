package in.ac.iiit.cvit.heritage;

import java.util.HashMap;

public class InterestPoint {

    private int _id;
    private HashMap<String, String> details;

    public InterestPoint() {
        details = new HashMap<String, String>();
    }

    void set(String key, String value) {
        details.put(key, value);
    }

    String get(String key) {
        return details.get(key);
    }
}
