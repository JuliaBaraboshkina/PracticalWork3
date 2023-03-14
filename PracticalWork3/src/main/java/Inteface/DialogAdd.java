package Inteface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class DialogAdd extends JDialog {
    private JTextField textFieldName;

    private JTextField textFieldColor;

    private JTextField textFieldWeight;

    private JTextField textFieldGuarantee;

    private JTextField textFieldNumOfKeysInc;

    private JTextField textFieldDepth;
    private JTextField textFieldLoopD;
    private JTextField textFieldCost;
    private JRadioButton radioButtonV;
    private JRadioButton radioButtonN;
    private JButton buttonOk;
    private JButton buttonCancel;
    private boolean isOk;
    public DialogAdd(){
        isOk = false;
        // Создаем панель, на которую разместим все элементы интерфейса
        JPanel panel = new JPanel();

        textFieldName = new JTextField(20);
        textFieldColor = new JTextField();
        textFieldWeight = new JTextField();
        textFieldGuarantee = new JTextField();
        textFieldNumOfKeysInc = new JTextField();
        textFieldDepth = new JTextField();
        textFieldLoopD = new JTextField();
        textFieldCost = new JTextField();
        radioButtonV = new JRadioButton("Врезные замки");
        radioButtonV.addActionListener(new RadioButtonV());
        radioButtonN = new JRadioButton("Навесные замки");
        radioButtonN.addActionListener(new RadioButtonN());
        buttonOk = new JButton("Добавить");
        buttonOk.addActionListener(new ButtonOk());
        buttonCancel = new JButton("Отмена");
        buttonCancel.addActionListener(new ButtonCancel());
        // Устанавливаем компоновщик GridLayout для панели
        GridLayout experimentLayout = new GridLayout(10,2, 5, 5);
        panel.setLayout(experimentLayout);
        getContentPane().setLayout(new FlowLayout());
        getContentPane().add(panel);
        // Располагаем на панели элементы интерфейса
        panel.add(new JLabel("Название"));
        panel.add(textFieldName);
        panel.add(new JLabel("Цвет"));
        panel.add(textFieldColor);
        panel.add(new JLabel("Вес (г)"));
        panel.add(textFieldWeight);
        panel.add(new JLabel("Гарантия (мес)"));
        panel.add(textFieldGuarantee);
        panel.add(new JLabel("Кол-во ключей (шт)"));
        panel.add(textFieldNumOfKeysInc);
        panel.add(new JLabel("Глубина (мм)"));
        panel.add(textFieldDepth);
        panel.add(new JLabel("Диаметр петли (мм)"));
        panel.add(textFieldLoopD);
        panel.add(new JLabel("Цена (руб)"));
        panel.add(textFieldCost);

        panel.add(radioButtonV);
        panel.add(radioButtonN);
        panel.add(buttonOk);
        panel.add(buttonCancel);
        textFieldDepth.setEnabled(false);
        textFieldLoopD.setEnabled(false);
    }
    // Обработчик события от кнопки "Добавить"
    private class ButtonOk implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            isOk = true;
            // Проверяем, не содержат ли поля с цифрами символы
            try {
                if (radioButtonN.isSelected()) {
                    Integer.parseInt(textFieldWeight.getText());
                    Integer.parseInt(textFieldGuarantee.getText());
                    Integer.parseInt(textFieldNumOfKeysInc.getText());
                    Float.parseFloat(textFieldLoopD.getText());
                    Integer.parseInt(textFieldCost.getText());
                    dispose();
                }
            }catch (NumberFormatException ex){
                System.out.println("*");
                JOptionPane.showMessageDialog(getRootPane(),
                        "Поля должны содержать число");
            }
            try {
                if (radioButtonV.isSelected()) {
                    Integer.parseInt(textFieldWeight.getText());
                    Integer.parseInt(textFieldGuarantee.getText());
                    Integer.parseInt(textFieldNumOfKeysInc.getText());
                    Integer.parseInt(textFieldDepth.getText());
                    Integer.parseInt(textFieldCost.getText());
                    dispose();
                }
            }catch (NumberFormatException ex){
                System.out.println("*");
                JOptionPane.showMessageDialog(getRootPane(),
                        "Неверный формат ввода!");
            }
        }
    }
    // Обработчик события от кнопки "Отмена"
    private class ButtonCancel implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            isOk = false;
            dispose();
        }
    }
    // Обработчик события от кнопки "врезные замки"
    private class RadioButtonV implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            radioButtonV.setSelected(true);
            radioButtonN.setSelected(false);
            textFieldDepth.setEnabled(true);
            textFieldLoopD.setEnabled(false);
        }
    }
    // Обработчик события от кнопки "навесные замки"
    private class RadioButtonN implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            radioButtonV.setSelected(false);
            radioButtonN.setSelected(true);
            textFieldDepth.setEnabled(false);
            textFieldLoopD.setEnabled(true);
        }
    }
    // Методы-геттеры для получения информации об добавленном издании
    public boolean isOk(){
        return isOk;
    }
    public String getName(){
        return textFieldName.getText();
    }
    public String getColor(){
        return textFieldColor.getText();
    }
    public int getWeight(){
        return Integer.parseInt(textFieldWeight.getText());
    }
    public int getGuarantee(){
        return Integer.parseInt(textFieldGuarantee.getText());
    }
    public int getNumOfKeysInc(){
        return Integer.parseInt(textFieldNumOfKeysInc.getText());
    }
    public int getDepth(){
        return Integer.parseInt(textFieldDepth.getText());
    }
    public float getLoopD(){
        return Float.parseFloat(textFieldLoopD.getText());
    }
    public int getCost(){
        return Integer.parseInt(textFieldCost.getText());
    }
    public boolean isN(){
        if (radioButtonN.isSelected())
            return true;
        else
            return false;
    }
    public boolean isV(){
        if (radioButtonV.isSelected())
            return true;
        else
            return false;
    }
}
