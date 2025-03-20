package unit_change;

import java.util.Scanner;
import unit_change.units.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int select;

        System.out.println("===================================\n" + "단위 변환 프로그램\n\n1. 길이 2. 무게 3. 온도 4. 환율"
                 + "\n===================================\n");

        while (true) {
            System.out.print("번호를 입력하세요: ");

            try {
                select = sc.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("다시 입력해 주세요!");
                continue;
            }
        }

        switch (select) {
            case 1:
                
                break;
        
            case 2:
            
                break;
    
            case 3:
            
                break;
    
            case 4:
            
                break;
    
            default:
                break;
        }

        sc.close();
    }
}
