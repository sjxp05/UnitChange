package unit_change.units;

import java.util.HashMap;
// import java.net.HttpURLConnection;
// import java.net.URL;

public class Currency {
    public static final String[] monetaryUnits = { "₩", "$", "¥(일본)", "¥(중국)", "€" };

    public static final double[] currentExchange = {};

    public static void getAPI() {
        // URL url = new URL("API URL");
        // HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    }
    
    public static HashMap<String, Double> getExchange(HashMap<String, Double> map) {
        for (int i = 0; i < monetaryUnits.length; i++) {
            map.put(monetaryUnits[i], currentExchange[i]);
        }

        return map;
    }
}
