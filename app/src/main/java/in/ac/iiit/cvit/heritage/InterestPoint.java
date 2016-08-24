package in.ac.iiit.cvit.heritage;

import java.util.HashMap;

/**
 * Created by jerin on 24/8/16.
 */
public class InterestPoint {
    private int id;
    HashMap<String, String> details;
    InterestPoint(){
        details = new HashMap<String, String>();
    }

    void set(String key, String value) {
        details.put(key, value);
    }
    String get(String key){
        return details.get(key);
    }
}
