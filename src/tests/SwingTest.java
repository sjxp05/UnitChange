package tests;

import java.awt.*;
import javax.swing.*;

/* 별 기능은 없고 연습용으로 만들어놓은 것으로 추정되니 무시하셔도 됩니다 */
public class SwingTest extends JFrame {
    public SwingTest() {
        setTitle("New frame");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container pane = getContentPane();
        pane.setLayout(null);

        JButton bt1 = new JButton("click me");
        bt1.setLocation(30, 20);
        bt1.setSize(100,50);
        pane.add(bt1);

        setVisible(true);
    }

    public static void main(String[] args) {
        new SwingTest();
    }
}
