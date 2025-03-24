package unit_change;

import unit_change.units.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class MainWithSwing extends JFrame {
    HashMap<String, Double> map = new HashMap<>(); // 단위 - 값 저장하는 맵
    String[] categories = { "길이", "넓이", "부피", "무게", "온도", "속도", "데이터양" }; // 버튼 텍스트로 쓸 배열
    String[] unitList; // 단위 목록
    int currentCt = -1; // 현재 표시되고 있는 카테고리

    JPanel ctPanel = new JPanel(); // 입력창, 콤보박스, 버튼, 결과창 모두 넣은 패널
    JComboBox<String> unitChoose = new JComboBox<>(); // 단위 선택 콤보박스
    JTextField input = new JTextField(); // 변환할 값 입력
    JLabel resultLb = new JLabel(); // 결과 표시 라벨
    JScrollPane resultPane = new JScrollPane(resultLb); // 긴 라벨을 표시하기 위한 스크롤팬
    JButton calcBt = new JButton("변환"); // 변환 수행 버튼

    public MainWithSwing() {
        // 전체 창 설정
        setTitle("단위 변환기");
        setSize(520, 440);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // 패널
        ctPanel.setLayout(null);
        ctPanel.setBounds(45, 80, 420, 290);
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

        // 변환 버튼
        calcBt.setBounds(340, 20, 60, 30);
        calcBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultPane.setVisible(true);
                String results = "<html><body>";
                double finding;
                try {
                    finding = Double.parseDouble(input.getText());

                    if (currentCt == 4) { // 온도 계산
                        double temps[] = Temperature.calculateTemp(finding, unitChoose.getSelectedIndex() + 1);

                        for (int i = 0; i < unitList.length; i++) {
                            results += (formatResult(temps[i]) + " " + unitList[i]);
                            if (i == unitList.length - 1) {
                                results += "</body></html>";
                            } else {
                                results += "<br>";
                            }
                        }
                        resultLb.setText(results);

                    } else if (currentCt == 6) { // 데이터양 계산
                        double data[] = Data.calculateData(finding, unitChoose.getSelectedIndex() + 1);

                        for (int i = 0; i < unitList.length; i++) {
                            results += (formatResult(data[i]) + " " + unitList[i]);
                            if (i == unitList.length - 1) {
                                results += "</body></html>";
                            } else {
                                results += "<br>";
                            }
                        }
                        resultLb.setText(results);

                    } else {
                        if (unitChoose.getSelectedIndex() != 0) {
                            finding /= map.get(unitChoose.getItemAt(unitChoose.getSelectedIndex()));
                        }

                        for (int i = 0; i < unitList.length; i++) {
                            results += (formatResult(finding * map.get(unitList[i])) + " " + unitList[i]);
                            if (i == unitList.length - 1) {
                                results += "</body></html>";
                            } else {
                                results += "<br>";
                            }
                        }
                        resultLb.setText(results);
                    }
                } catch (Exception ex) {
                    resultLb.setText("");
                }
            }
        });
        ctPanel.add(calcBt);

        // 분류별 버튼
        JButton[] ctButtons = new JButton[7];
        for (int i = 0; i < categories.length; i++) {
            ctButtons[i] = new JButton(categories[i]);
            ctButtons[i].setFont(new Font("Sans Serif", Font.BOLD, 12));
            if (i == 6) {
                ctButtons[i].setBounds(70 * i, 0, 88, 50);
            } else {
                ctButtons[i].setBounds(70 * i, 0, 68, 50);
            }
            add(ctButtons[i]);

            switch (i) {
                case 0: // 길이
                    ctButtons[i].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            clearUI(0);
                            map = Length.getLength(map);
                            unitList = (String[]) Length.lengthUnits.clone();
                            for (String i : Length.lengthUnits) {
                                unitChoose.addItem(i);
                            }
                        }
                    });
                    break;

                case 1: // 넓이
                    ctButtons[i].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            clearUI(1);
                            map = Width.getWidth(map);
                            unitList = (String[]) Width.widthUnits.clone();
                            for (String i : Width.widthUnits) {
                                unitChoose.addItem(i);
                            }
                        }
                    });
                    break;

                case 2: // 부피
                    ctButtons[i].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            clearUI(2);
                            map = Volume.getVolume(map);
                            unitList = (String[]) Volume.volumeUnits.clone();
                            for (String i : Volume.volumeUnits) {
                                unitChoose.addItem(i);
                            }
                        }
                    });
                    break;

                case 3: // 무게
                    ctButtons[i].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            clearUI(3);
                            map = Weight.getWeight(map);
                            unitList = (String[]) Weight.weightUnits.clone();
                            for (String i : Weight.weightUnits) {
                                unitChoose.addItem(i);
                            }
                        }
                    });
                    break;

                case 4: // 온도
                    ctButtons[i].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            clearUI(4);
                            unitList = (String[]) Temperature.tempUnits.clone();
                            for (String i : Temperature.tempUnits) {
                                unitChoose.addItem(i);
                            }
                        }
                    });
                    break;

                case 5: // 속도
                    ctButtons[i].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            clearUI(5);
                            map = Speed.getSpeed(map);
                            unitList = (String[]) Speed.speedUnits.clone();
                            for (String i : Speed.speedUnits) {
                                unitChoose.addItem(i);
                            }
                        }
                    });
                    break;

                case 6: // 데이터양
                    ctButtons[i].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            clearUI(6);
                            unitList = (String[]) Data.dataUnits.clone();
                            for (String i : Data.dataUnits) {
                                unitChoose.addItem(i);
                            }
                        }
                    });
                    break;

                default:
                    break;
            }
        }

        setVisible(true);
    }
    
    // 다른 카테고리 선택했을 시 입력한 내용 지우기
    void clearUI(int newCt) {
        if (currentCt != newCt) {
            currentCt = newCt;
            input.setText("");
            resultLb.setText("");
            unitChoose.removeAllItems();
            ctPanel.setVisible(true);
            map.clear();
        }
    }
    
    // 숫자 형식 이쁘게 바꾸기
    String formatResult(double value) {
        String formatted = String.format("%.9g", value);
        int i;

        if (formatted.indexOf(".", 0) >= 0 && formatted.indexOf("e", 0) < 0) {
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
