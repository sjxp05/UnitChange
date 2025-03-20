package unit_change.units;

import java.util.HashMap;

public class Weight {
    public static final String[] weightUnits = { "kg", "mg", "g", "t(톤)", "kt(킬로톤)", "gr(그레인)", "oz(온스)", "lb(파운드)",
            "돈", "냥", "근", "관" };

    public static final double[] weightValues = { 1, 1000000, 1000, 0.001, 0.000001, 15432.3584, 35.273962, 2.204623,
            266.6666667, 26.66666667, 1.666666667, 0.2666666667 };

    public static HashMap<String, Double> getWeight(HashMap<String, Double> map) {
        for (int i = 0; i < weightUnits.length; i++) {
            map.put(weightUnits[i], weightValues[i]);
        }

        return map;
    }
}
