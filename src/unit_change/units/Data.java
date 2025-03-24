package unit_change.units;

public class Data {
    public static final String[] dataUnits = { "bit", "B", "KB", "MB", "GB", "TB", "PB", "EB" };

    public static double[] calculateData(double finding, int select) {
        double[] calculated = new double[8];
        calculated[select - 1] = finding;

        if (select == 1) {
            calculated[1] = calculated[0] / 8;
            for (int i = 2; i < 8; i++) {
                calculated[i] = calculated[i - 1] / 1024;
            }
        } else {
            for (int i = select - 2; i >= 1; i--) {
                calculated[i] = calculated[i + 1] * 1024;
            }

            calculated[0] = calculated[1] * 8;

            for (int i = select; i < 8; i++) {
                calculated[i] = calculated[i - 1] / 1024;
            }
        }

        return calculated;
    }
}
