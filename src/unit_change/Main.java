package unit_change;

import java.util.HashMap;
import java.util.Scanner;
import unit_change.units.*;

public class Main {
    static HashMap<String, Double> map = new HashMap<>();
    static String[] num2Unit = {};

    static String formatResult(double value) {
        String formatted = String.format("%10g", value);
        int i;

        if (formatted.indexOf(".", 0) >= 0) {
            for (i = formatted.length() - 1; i >= 0; i--) {
                if (formatted.charAt(i) != '0') {
                    break;
                }
            }
            if (formatted.charAt(i) == '.') {
                i--;
            }

            return formatted.substring(0, i + 1);
        }

        return formatted;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int select;
        int u_select;
        String selectedUnit="";
        double finding;

        System.out.println("===================================\n" + "단위 변환 프로그램\n\n1. 길이 2. 무게 3. 온도 4. 환율"
                 + "\n===================================");

        while (true) {
            System.out.print("\n번호를 입력하세요: ");
            try {
                select = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("올바르게 입력해 주세요!");
                sc.nextLine();
                continue;
            }

            switch (select) {
                case 1:
                    Length.getLength(map);
                    num2Unit = (String[]) Length.lengthUnits.clone();
                    System.out.println("\n1. m   2. mm   3. cm   4. km");
                    System.out.println("5. in(인치)   6. ft(피트)   7. yd(야드)   8. mile(마일)");
                    System.out.println("9. 자   10. 간   11. 정   12. 리   13. 해리");
                    break;

                case 2:
                    Weight.getWeight(map);
                    num2Unit = (String[]) Weight.weightUnits.clone();
                    System.out.println("\n1. kg   2. mg   3. g   4. t(톤)");
                    System.out.println("5. kt(킬로톤)   6. gr(그레인)   7. oz(온스)   8. lb(파운드)");
                    System.out.println("9. 돈   10. 냥   11. 근   12. 관");
                    break;

                case 3:
                    num2Unit = (String[]) Temperature.tempUnits.clone();
                    System.out.println("\n1. ºC(섭씨)   2. ºF(화씨)   3. K(캘빈)");
                    break;

                case 4:

                    break;

                default:
                    System.out.println("올바르게 입력해 주세요!");
                    continue;
            }
            break;
        }

        while (true) {
            try {
                System.out.print("\n변환할 값의 단위(번호)를 입력하세요: ");
                u_select = sc.nextInt();
                sc.nextLine();

                if (u_select < 1 || u_select > num2Unit.length) {
                    System.out.println("올바르게 입력해 주세요!");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("올바르게 입력해 주세요!");
                sc.nextLine();
                continue;
            }
        }
        selectedUnit = num2Unit[u_select - 1];
        
        while (true) {
            try {
                System.out.print("\n변환할 값을 입력하세요: ");
                finding = sc.nextDouble();
                sc.nextLine();
                break;
            } catch (Exception e) {
                System.out.println("올바르게 입력해 주세요!");
                sc.nextLine();
                continue;
            }
        }

        System.out.println("\n" + formatResult(finding) + " " + selectedUnit + " =>");
        System.out.println("------------------------------------");

        if (select == 3) {
            double[] results = Temperature.calculateTemp(finding, u_select);

            for (int i = 0; i < 3; i++) {
                System.out.print(formatResult(results[i]) + " ");
                System.out.println(num2Unit[i]);
            }
        } else {
            if (u_select != 1) {
                finding /= map.get(selectedUnit);
            }

            for (String i : num2Unit) {
                System.out.print(formatResult(finding * map.get(i)) + " ");
                System.out.println(i);
            }
        }

        sc.close();
    }
}
