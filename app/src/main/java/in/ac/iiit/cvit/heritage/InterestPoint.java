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

    double distance(double iLat, double iLong){
        double pLat, pLong;
        double dLat, dLong;
        double sum;

        pLat = Double.parseDouble(details.get("lat"));
        pLong = Double.parseDouble(details.get("long"));

        /* Eucleadean distance. Should work. */
        dLat = pLat - iLat;
        dLong = pLong - iLong;
        sum = dLat * dLat + dLong * dLong;
        return Math.sqrt(sum);
    }
}
