package unit_change;

import unit_change.units.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.*;

public class MainWithSwing extends JFrame {
    static HashMap<String, Double> map = new HashMap<>(); // 단위 - 값 저장하는 맵
    static String[] num2Unit = {}; // 단위 목록
    static String[] categories = { "길이", "넓이", "부피", "무게", "온도", "속도", "환율" };

    public MainWithSwing() {
        setTitle("단위 변환기");
        setSize(505, 450);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container pane = getContentPane();
        pane.setLayout(null);

        // JPanel ctPanel = new JPanel();
        // ctPanel.setBounds(40, 80, 420, 290);
        // pane.add(ctPanel); 얘 어카지

        JComboBox<String> unitChoose = new JComboBox<>();
        unitChoose.setFont(new Font("Sans Serif", Font.PLAIN, 15));
        unitChoose.setBounds(270, 100, 100, 30);
        pane.add(unitChoose);

        JButton calcBt = new JButton("변환");
        calcBt.setBounds(380, 100, 60, 30);
        pane.add(calcBt);

        JTextField input = new JTextField();
        input.setFont(new Font("Sans Serif", Font.PLAIN, 15));
        input.setBounds(60, 100, 200, 30);
        pane.add(input);

        JScrollPane resultPane = new JScrollPane();
        resultPane.setBounds(60, 150, 380, 200);
        pane.add(resultPane);
        resultPane.setVisible(false);

        JButton[] ctButtons = new JButton[7];
        for (int i = 0; i < categories.length; i++) {
            ctButtons[i] = new JButton(categories[i]);
            ctButtons[i].setBounds(70 * i, 0, 70, 50);
            pane.add(ctButtons[i]);

            switch (i) {
                case 0:
                    ctButtons[i].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            unitChoose.removeAllItems();
                            map = Length.getLength(map);
                            for (String i : Length.lengthUnits) {
                                unitChoose.addItem(i);
                            }
                        }
                    });
                    break;

                case 1:
                    ctButtons[i].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            unitChoose.removeAllItems();
                            map = Width.getWidth(map);
                            for (String i : Width.widthUnits) {
                                unitChoose.addItem(i);
                            }
                        }
                    });
                    break;

                case 2:
                    ctButtons[i].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            unitChoose.removeAllItems();
                            map = Volume.getVolume(map);
                            for (String i : Volume.volumeUnits) {
                                unitChoose.addItem(i);
                            }
                        }
                    });
                    break;

                case 3:
                    ctButtons[i].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            unitChoose.removeAllItems();
                            map = Weight.getWeight(map);
                            for (String i : Weight.weightUnits) {
                                unitChoose.addItem(i);
                            }
                        }
                    });
                    break;

                case 4:
                    ctButtons[i].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            unitChoose.removeAllItems();
                            for (String i : Temperature.tempUnits) {
                                unitChoose.addItem(i);
                            }
                        }
                    });
                    break;

                case 5:
                    ctButtons[i].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            unitChoose.removeAllItems();
                            map = Speed.getSpeed(map);
                            for (String i : Speed.speedUnits) {
                                unitChoose.addItem(i);
                            }
                        }
                    });
                    break;

                case 6:

                    break;

                default:
                    break;
            }
        }

        setVisible(true);
    }
    
    static String formatResult(double value) {
        String formatted = String.format("%.9g", value);
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
        new MainWithSwing();
    }
}
