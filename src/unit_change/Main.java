package unit_change;

import java.util.HashMap;
import java.util.Scanner;
import unit_change.units.*;

public class Main {
    static HashMap<String, Double> map = new HashMap<>();
    static String[] num2Unit = {};

    static String formatResult(double value) {
        String formatted = String.format("%.6g", value);
        int i;

        if (formatted.indexOf(".", 0) >= 0) {
            for (i = formatted.length(); i >= 0; i--) {
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
        String selectedUnit="";
        double finding;

        System.out.println("===================================\n" + "단위 변환 프로그램\n\n1. 길이 2. 무게 3. 온도 4. 환율"
                 + "\n===================================\n");

        while (true) {
            System.out.print("번호를 입력하세요: ");

            try {
                select = sc.nextInt();
                sc.nextLine();
                break;
            } catch (Exception e) {
                System.out.println("올바르게 입력해 주세요!");
                continue;
            }
        }

        switch (select) {
            case 1:
                Length.getLength(map);
                num2Unit = (String[]) Length.lengthUnits.clone();

                System.out.println("\n1. m   2. mm   3. cm   4. km\n5. in(인치)   6. ft(피트)   7. yd(야드)   8. mile(마일)");
                while (true) {
                    try {
                        System.out.print("\n변환할 값의 단위(번호)를 입력하세요: ");
                        select = sc.nextInt();
                        sc.nextLine();

                        if (select < 0 || select > 8) {
                            System.out.println("올바르게 입력해 주세요!");
                            continue;
                        }
                        break;
                    } catch (Exception e) {
                        System.out.println("올바르게 입력해 주세요!");
                        continue;
                    }
                }
                
                selectedUnit = num2Unit[select - 1];
                break;

            case 2:
                Weight.getWeight(map);
                break;

            case 3:

                break;

            case 4:

                break;

            default:
                break;
        }
        
        System.out.print("\n변환할 값을 입력하세요: ");
        finding = sc.nextDouble();

        System.out.println(finding + " " + selectedUnit + " =>");
        System.out.println("------------------------------------");

        if (select != 1) {
            finding /= map.get(selectedUnit);
        }

        for (String s : num2Unit) {
            System.out.print(formatResult(finding * map.get(s)) + " ");
            System.out.println(s);
        }

        sc.close();
    }
}
