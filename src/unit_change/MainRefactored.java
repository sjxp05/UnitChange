package unit_change;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

import javax.swing.*;
import unit_change.units.*;

public class MainRefactored extends JFrame implements ActionListener, MouseListener {

    JMenuBar menuBar = new JMenuBar();
    JMenu[] categories = new JMenu[8];
    volatile int currentCtg = -1;

    JPanel panel = new JPanel();
    JLabel initialLb = new JLabel("분류를 선택하여 단위 변환을 시작해 보세요!");
    JTextField input = new JTextField();
    JComboBox<String> unitChoose = new JComboBox<>();
    JButton calcBt = new JButton("변환");
    JLabel resultPane = new JLabel();
    JScrollPane resultScroll = new JScrollPane(resultPane);

    String[] unitList;
    HashMap<String, Double> unitMap = new HashMap<>();

    MainRefactored() {
        setTitle("단위변환기");
        setSize(460, 400);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // 첫화면 텍스트
        initialLb.setFont(new Font("Sans Serif", Font.PLAIN, 15));
        initialLb.setForeground(Color.gray);
        initialLb.setHorizontalAlignment(JLabel.CENTER);
        initialLb.setBounds(23, 163, 400, 15);
        add(initialLb);

        // 메인 패널
        panel.setBounds(22, 20, 400, 300);
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createLineBorder(Color.gray));
        panel.setOpaque(true);
        add(panel);
        panel.setVisible(false);

        // 메뉴, 메뉴바 설정하기
        String[] categoryNames = { "길이", "넓이", "부피", "무게", "온도", "속도", "데이터양", "환율" };

        for (int i = 0; i < categoryNames.length; i++) {
            categories[i] = new JMenu(categoryNames[i]);
            categories[i].addMouseListener(this);
            menuBar.add(categories[i]);
        }

        setJMenuBar(menuBar);

        // 입력창
        input.setFont(new Font("Sans Serif", Font.PLAIN, 15));
        input.setBounds(25, 20, 150, 30);
        panel.add(input);

        // 콤보박스
        unitChoose.setFont(new Font("Sans Serif", Font.PLAIN, 15));
        unitChoose.setBounds(185, 20, 100, 30);
        panel.add(unitChoose);

        // 계산 버튼
        calcBt.setFont(new Font("Sans Serif", Font.PLAIN, 15));
        calcBt.addActionListener(this);
        calcBt.setBounds(295, 20, 78, 30);
        panel.add(calcBt);

        // 결과창
        resultPane.setFont(new Font("Sans Serif", Font.PLAIN, 15));
        resultPane.setHorizontalAlignment(JLabel.LEFT);
        resultPane.setVerticalAlignment(JLabel.CENTER);

        // 스크롤 설정
        resultScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        resultScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        resultScroll.setBounds(25, 60, 350, 220);
        panel.add(resultScroll);

        setVisible(true);
    }

    // 카테고리 다시 골랐을 때 초기화
    void clear() {
        unitChoose.removeAllItems();
        unitMap.clear();
        input.setText("");
        resultPane.setText("");

        initialLb.setVisible(false);
        panel.setVisible(true);
        repaint();
    }

    // 결과창에 단위변환한 결과 한줄씩 프린트
    void printResults(ArrayList<Double> results) {

        StringBuffer sbf = new StringBuffer("<html><body>");

        for (int i = 0; i < results.size(); i++) {
            sbf.append("&nbsp;&nbsp;" + formatResult(results.get(i)) + " " + unitList[i]);

            if (i == results.size() - 1) {
                sbf.append("</body></html>");
            } else {
                sbf.append("<br>");
            }
        }

        // 결과창에 반영
        resultPane.setText(sbf.toString());
    }

    // 숫자 보기좋게 포맷
    String formatResult(double result) {

        String formatted = String.format("%.9g", result);
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

    @Override
    public void actionPerformed(ActionEvent e) { // 계산 버튼 눌렀을 때

        double toCalculate = 0.0; // 입력받을 수
        int selectedUnit = unitChoose.getSelectedIndex(); // 사용자가 선택중인 단위
        ArrayList<Double> results = new ArrayList<>(); // 모든 단위별 결과값

        try {
            toCalculate = Double.parseDouble(input.getText());
        } catch (Exception ex) {
            clear();
            return;
        }

        switch (currentCtg) {
            case 4:
                double[] temps = Temperature.calculateTemp(toCalculate, selectedUnit);
                for (double value : temps) {
                    results.add(value);
                }
                break;

            case 6:
                double[] data = Data.calculateData(toCalculate, selectedUnit);
                for (double value : data) {
                    results.add(value);
                }
                break;

            default:
                if (selectedUnit != 0) {
                    toCalculate /= unitMap.get((String) unitChoose.getSelectedItem());
                }

                for (String name : unitList) {
                    results.add(toCalculate * unitMap.get(name));
                }

                break;
        }

        printResults(results);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JMenu source = (JMenu) e.getSource();
        clear();

        for (int i = 0; i < categories.length; i++) {
            if (source == categories[i]) {
                currentCtg = i;
                break;
            }
        }

        switch (currentCtg) {
            case 0:
                unitList = (String[]) Length.lengthUnits.clone();
                unitMap = Length.getLength(unitMap);
                break;

            case 1:
                unitList = (String[]) Width.widthUnits.clone();
                unitMap = Width.getWidth(unitMap);
                break;

            case 2:
                unitList = (String[]) Volume.volumeUnits.clone();
                unitMap = Volume.getVolume(unitMap);
                break;

            case 3:
                unitList = (String[]) Weight.weightUnits.clone();
                unitMap = Weight.getWeight(unitMap);
                break;

            case 4:
                unitList = (String[]) Temperature.tempUnits.clone();
                break;

            case 5:
                unitList = (String[]) Speed.speedUnits.clone();
                unitMap = Speed.getSpeed(unitMap);
                break;

            case 6:
                unitList = (String[]) Data.dataUnits.clone();
                break;

            default:
                panel.setVisible(false);
                initialLb.setVisible(true);
                return;
        }

        for (String unit : unitList) {
            unitChoose.addItem(unit);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    public static void main(String[] args) {
        new MainRefactored();
    }
}
