package unit_change;

import java.util.HashMap;
import java.util.Scanner;
import unit_change.units.*;

public class Main {
    static HashMap<String, Double> map = new HashMap<>();
    static String[] num2Unit = {};

    static String formatResult(double value) {
        String formatted = String.format("%.10g", value);
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
        int category; // 길이 넓이 등 종류 선택
        int select; // 한 종류 내에서 단위 선택
        String selectedUnit = "";
        double finding; // 단위변환 하려는 값

        System.out.println("==========================================");
        System.out.println("단위 변환 프로그램\n");
        System.out.println("1. 길이   2. 넓이   3. 부피   4. 무게");
        System.out.println("5. 온도   6. 속도(미완성)   7. 환율(미완성)");
        System.out.println("==========================================");

        while (true) {
            System.out.print("\n번호를 입력하세요: ");
            try {
                category = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("올바르게 입력해 주세요!");
                sc.nextLine();
                continue;
            }

            switch (category) {
                case 1:
                    Length.getLength(map);
                    num2Unit = (String[]) Length.lengthUnits.clone();
                    break;

                case 2:
                    Width.getWidth(map);
                    num2Unit = (String[]) Width.widthUnits.clone();
                    break;

                case 3:
                    Volume.getVolume(map);
                    num2Unit = (String[]) Volume.volumeUnits.clone();
                    break;

                case 4:
                    Weight.getWeight(map);
                    num2Unit = (String[]) Weight.weightUnits.clone();
                    break;

                case 5:
                    num2Unit = (String[]) Temperature.tempUnits.clone();
                    break;

                case 6:
                    // 미완성
                    break;

                case 7:
                    /*
                     * 수정을..... 해야되는데에.....
                     * 근데 그러려면 api 호출 공부해야되는데...
                     * api호출하려면 환율사이트에 요청도 해야되는데ㅔ......
                     * 언제하냐 ㄹㅇ
                     */
                    System.out.println("미완성입니다 돌아가세요 ㅠㅠ");
                    continue;

                default:
                    System.out.println("올바르게 입력해 주세요!");
                    continue;
            }
            break;
        }
        
        for (int i = 0; i < num2Unit.length; i++) {
            if (i % 4 == 0 && i < 12) {
                System.out.print("\n");
            }

            System.out.print((i + 1) + ". " + num2Unit[i] + "   ");
        }

        while (true) {
            try {
                System.out.print("\n\n변환할 값의 단위(번호)를 입력하세요: ");
                select = sc.nextInt();
                sc.nextLine();

                if (select < 1 || select > num2Unit.length) {
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

        selectedUnit = num2Unit[select - 1];
        
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

        if (category == 5) {
            double[] results = Temperature.calculateTemp(finding, select);

            for (int i = 0; i < 3; i++) {
                System.out.print(formatResult(results[i]) + " ");
                System.out.println(num2Unit[i]);
            }
        } else {
            if (select != 1) {
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
