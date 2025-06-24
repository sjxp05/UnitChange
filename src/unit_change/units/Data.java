package unit_change.units;

public class Data {
    public static final String[] dataUnits = { "bit", "B", "KB", "MB", "GB", "TB", "PB", "EB" };

    public static double[] calculateData(double finding, int select) {
        double[] calculated = new double[8];
        calculated[select] = finding;

        if (select == 0) { // bit 단위 선택시(비트->바이트 변환 미리 해주기)
            calculated[1] = calculated[0] / 8;
            for (int i = 2; i < 8; i++) {
                calculated[i] = calculated[i - 1] / 1024;
            }

        } else { // Byte 단위 선택시(바이트->비트 변환 필요)
            for (int i = select - 1; i >= 1; i--) {
                calculated[i] = calculated[i + 1] * 1024;
            }

            calculated[0] = calculated[1] * 8;

            for (int i = select + 1; i < 8; i++) {
                calculated[i] = calculated[i - 1] / 1024;
            }
        }

        return calculated;
    }
}
