package unit_change;

import unit_change.units.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class MainWithSwing extends JFrame {
    static HashMap<String, Double> map = new HashMap<>(); // 단위 - 값 저장하는 맵
    static String[] num2Unit = {}; // 단위 목록
    static String[] categories = { "길이", "넓이", "부피", "무게", "온도", "속도", "환율" };
    static String[] units;

    JPanel ctPanel = new JPanel();
    JComboBox<String> unitChoose = new JComboBox<>();
    JTextField input = new JTextField();
    JLabel resultLb = new JLabel();
    JScrollPane resultPane = new JScrollPane(resultLb);
    JButton calcBt = new JButton("변환");

    public MainWithSwing() {
        // 전체 창 설정
        setTitle("단위 변환기");
        setSize(500, 440);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // 패널
        ctPanel.setLayout(null);
        ctPanel.setBounds(35, 80, 420, 290);
        ctPanel.setBorder(new LineBorder(Color.GRAY));
        add(ctPanel);
        ctPanel.setVisible(false);

        // 콤보박스
        unitChoose.setFont(new Font("Sans Serif", Font.PLAIN, 15));
        unitChoose.setBounds(230, 20, 100, 30);
        ctPanel.add(unitChoose);

        // 입력창
        input.setFont(new Font("Sans Serif", Font.PLAIN, 15));
        input.setBounds(20, 20, 200, 30);
        ctPanel.add(input);

        // 결과창
        resultLb.setFont(new Font("Sans Serif", Font.PLAIN, 15));

        // 결과스크롤팬
        resultPane.setBounds(20, 70, 380, 200);
        ctPanel.add(resultPane);
        resultPane.setVisible(false);

        // 변환 버튼
        calcBt.setBounds(340, 20, 60, 30);
        calcBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultPane.setVisible(true);
                if (units.length == 3) {
                    String results = "<html><body>";
                    try {
                        double finding = Double.parseDouble(input.getText());
                        double temps[] = Temperature.calculateTemp(finding, unitChoose.getSelectedIndex() + 1);

                        for (int i = 0; i < units.length; i++) {
                            results += (formatResult(temps[i]) + " " + units[i]);
                            if (i == units.length - 1) {
                                results += "</body></html>";
                            } else {
                                results += "<br>";
                            }
                        }
                        resultLb.setText(results);

                    } catch (Exception ex) {
                        resultLb.setText("");
                    }
                } else {
                    String results = "<html><body>";
                    try {
                        double finding = Double.parseDouble(input.getText());

                        if (unitChoose.getSelectedIndex() != 0) {
                            finding /= map.get(unitChoose.getItemAt(unitChoose.getSelectedIndex()));
                        }

                        for (int i = 0; i < units.length; i++) {
                            results += (formatResult(finding * map.get(units[i])) + " " + units[i]);
                            if (i == units.length - 1) {
                                results += "</body></html>";
                            } else {
                                results += "<br>";
                            }
                        }
                        resultLb.setText(results);

                    } catch (Exception ex) {
                        resultLb.setText("");
                    }
                }
            }
        });
        ctPanel.add(calcBt);

        // 분류별 버튼
        JButton[] ctButtons = new JButton[7];
        for (int i = 0; i < categories.length; i++) {
            ctButtons[i] = new JButton(categories[i]);
            ctButtons[i].setBounds(70 * i, 0, 68, 50);
            add(ctButtons[i]);

            switch (i) {
                case 0:
                    ctButtons[i].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            clearUI();
                            map = Length.getLength(map);
                            units = (String[]) Length.lengthUnits.clone();
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
                            clearUI();
                            map = Width.getWidth(map);
                            units = (String[]) Width.widthUnits.clone();
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
                            clearUI();
                            map = Volume.getVolume(map);
                            units = (String[]) Volume.volumeUnits.clone();
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
                            clearUI();
                            map = Weight.getWeight(map);
                            units = (String[]) Weight.weightUnits.clone();
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
                            clearUI();
                            units = (String[]) Temperature.tempUnits.clone();
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
                            clearUI();
                            map = Speed.getSpeed(map);
                            units = (String[]) Speed.speedUnits.clone();
                            for (String i : Speed.speedUnits) {
                                unitChoose.addItem(i);
                            }
                        }
                    });
                    break;

                case 6:
                    ctButtons[i].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            clearUI();
                        }
                    });
                    break;

                default:
                    break;
            }
        }

        setVisible(true);
    }
    
    public void clearUI() {
        input.setText("");
        unitChoose.removeAllItems();
        ctPanel.setVisible(true);
        resultPane.setVisible(false);
        map.clear();
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
