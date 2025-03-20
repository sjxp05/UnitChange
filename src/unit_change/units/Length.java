package unit_change.units;

import java.util.HashMap;

public class Length {
    public static String[] lengthUnits = { "m", "mm", "cm", "km", "in(인치)", "ft(피트)", "yd(야드)", "mile(마일)" };

    public static HashMap<String, Double> getLength(HashMap<String, Double> map) {
        map.put("m", 1.0);
        map.put("mm", 1000.0);
        map.put("cm", 100.0);
        map.put("km", 0.001);
        map.put("in(인치)", 39.370079);
        map.put("ft(피트)", 3.28084);
        map.put("yd(야드)", 1.093613);
        map.put("mile(마일)", 0.000621);

        return map;
    }
}
