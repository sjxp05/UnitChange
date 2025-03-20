package unit_change.units;

import java.util.HashMap;

public class Speed {
    public static final String[] speedUnits = { "m/s", "km/h", "km/s", "in/s", "ft/s", "mi/s", "mi/h", "mach(마하)" };

    public static final double[] speedValues = { 1, 3.6, 0.001, 39.3700787, 3.2808399, 0.000621371192, 2.23693629,
            0.00294117647 };

    public static HashMap<String, Double> getSpeed(HashMap<String, Double> map) {
        for (int i = 0; i < speedUnits.length; i++) {
            map.put(speedUnits[i], speedValues[i]);
        }

        return map;
    }
}
