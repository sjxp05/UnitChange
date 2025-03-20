package unit_change.units;

import java.util.HashMap;

public class Volume {
    public static final String[] volumeUnits = { "L", "mL(=cc)", "cm³", "m³", "in³(세제곱인치)", "ft³(세제곱피트)", "yd³(세제곱야드)",
            "gal(갤런)", "bbl(배럴)", "oz(온스)", "홉", "되", "말" };

    public static final double[] volumeValues = { 1, 1000, 1000, 0.001, 61.0237441, 0.03531466666667, 0.00130795062,
            0.264172052, 0.00629326621, 33.8140222, 5.54354454, 0.554354454, 0.0554354454 };

    public static HashMap<String, Double> getVolume(HashMap<String, Double> map) {
        for (int i = 0; i < volumeUnits.length; i++) {
            map.put(volumeUnits[i], volumeValues[i]);
        }

        return map;
    }
}
