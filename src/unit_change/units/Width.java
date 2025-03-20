package unit_change.units;

import java.util.HashMap;

public class Width {
    public static final String[] widthUnits = { "m²", "km²", "a(아르)", "ha(헥타르)", "ft²(제곱피트)", "yd²(제곱야드)", "ac(에이커)",
            "평" };

    public static final double[] widthValues = { 1, 0.000001, 0.01, 0.0001, 10.7639104, 1.19599005, 0.000247105381,
            0.3025 };

    public static HashMap<String, Double> getWidth(HashMap<String, Double> map) {
        for (int i = 0; i < widthUnits.length; i++) {
            map.put(widthUnits[i], widthValues[i]);
        }

        return map;
    }
}
