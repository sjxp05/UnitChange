package unit_change.units;

public class Temperature {
    public static final String[] tempUnits = { "ºC(섭씨)", "ºF(화씨)", "K(캘빈)" };

    public static double[] calculateTemp(double finding, int select) {
        double[] results = new double[3];
        results[select - 1] = finding;

        if (select == 1) {
            results[1] = (finding * 9 / 5) + 32;
            results[2] = finding + 273.15;
        } else if (select == 2) {
            results[0] = (finding - 32) * 5 / 9;
            results[2] = results[0] + 273.15;
        } else {
            results[0] = finding - 273.15;
            results[1] = (results[0] * 9 / 5) + 32;
        }

        return results;
    }
    
}
