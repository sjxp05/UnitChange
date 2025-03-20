package unit_change.units;

import java.util.HashMap;

public class Length {
    public static final String[] lengthUnits = { "m", "mm", "cm", "km", "in(인치)", "ft(피트)", "yd(야드)", "mile(마일)", "자",
            "간", "정", "리", "해리" };

    public static final double[] lengthValues = { 1, 1000, 100, 0.001, 39.3700787, 3.2808399, 1.0936133, 0.000621371192,
            3.3, 0.55, 0.009166666667, 0.0025462963, 0.000539956803 };

    public static HashMap<String, Double> getLength(HashMap<String, Double> map) {
        for (int i = 0; i < lengthUnits.length; i++) {
            map.put(lengthUnits[i], lengthValues[i]);
        }

        return map;
    }
}
