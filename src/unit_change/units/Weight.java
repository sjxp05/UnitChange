package unit_change.units;

import java.util.HashMap;

public class Weight {
    public static final String[] weightUnits = { "kg", "mg", "g", "t(톤)", "kt(킬로톤)", "gr(그레인)", "oz(온스)", "lb(파운드)",
            "돈", "냥", "근", "관" };

    public static HashMap<String, Double> getWeight(HashMap<String, Double> map) {
        map.put("kg", 1.0);
        map.put("mg", 1000000.0);
        map.put("g", 1000.0);
        map.put("t(톤)", 0.001);
        map.put("kt(킬로톤)", 0.000001);
        map.put("gr(그레인)", 15432.3584);
        map.put("oz(온스)", 35.273962);
        map.put("lb(파운드)", 2.204623);
        map.put("돈", 266.6666667);
        map.put("냥", 26.66666667);
        map.put("근", 1.666666667);
        map.put("관", 0.2666666667);

        return map;
    }
}
