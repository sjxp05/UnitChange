package unit_change.units;

import java.util.HashMap;

public class Length {
    public static final String[] lengthUnits = { "m", "mm", "cm", "km", "in(인치)", "ft(피트)", "yd(야드)", "mile(마일)", "자",
            "간", "정", "리", "해리" };

    public static HashMap<String, Double> getLength(HashMap<String, Double> map) {
        map.put("m", 1.0);
        map.put("mm", 1000.0);
        map.put("cm", 100.0);
        map.put("km", 0.001);
        map.put("in(인치)", 39.370079);
        map.put("ft(피트)", 3.28084);
        map.put("yd(야드)", 1.093613);
        map.put("mile(마일)", 0.000621);
        map.put("자", 3.3);
        map.put("간", 0.55);
        map.put("정", 0.009167);
        map.put("리", 0.002546);
        map.put("해리", 0.00054);

        return map;
    }
}
