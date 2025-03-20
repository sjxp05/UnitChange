package tests;

import java.awt.*;
import javax.swing.*;

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
