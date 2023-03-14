package Inteface;

import Shop.LockBase;
import Shop.MortiseLocks;
import Shop.Padlocks;
import Shop.ShopManagement;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FrameApp extends JFrame {
    private ShopManagement shopManagement;
    private JPanel panelAction;
    private JPanel panelInformation;
    private JButton buttonAdd;
    private JButton buttonDel;
    private JButton buttonFind;
    private JTextField textField;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scrollPane;
    private JTextField textFieldName;
    private JTextField textFieldColor;
    private JTextField textFieldWeight;
    private JTextField textFieldGuarantee;
    private JTextField textFieldNumOfKeysInc;
    private JTextField textFieldDepth;
    private JTextField textFieldLoopD;
    private JTextField textFieldCost;
    private JTextField textType;
    private JButton buttonCopyAll;
    private JButton buttonCopySelect;
    public FrameApp(ShopManagement shopManagement){
        super("Система учета товаров для магазина, продающего замки");
        this.shopManagement = shopManagement;
        panelAction = new JPanel();
        panelAction.setLayout(new FlowLayout());
        JPanel panelNew = new JPanel();
        panelNew = new JPanel();
        panelNew.setLayout(new BoxLayout(panelNew, BoxLayout.Y_AXIS));
        buttonAdd = new JButton("Добавить запись");
        buttonAdd.addActionListener(new ButtonAdd());
        buttonDel = new JButton("Удалить запись");
        buttonDel.addActionListener(new ButtonDel());
        textField = new JTextField();
        buttonFind = new JButton("Найти");
        buttonFind.addActionListener(new ButtonFind());
        buttonCopyAll = new JButton("В буф.обмена все");
        buttonCopyAll.addActionListener(new ButtonCopyAll());
        buttonCopySelect = new JButton("В буф.обмена помеч.");
        buttonCopySelect.addActionListener(new ButtonCopySelect());
        panelNew.add(buttonAdd);
        panelNew.add(buttonDel);
        panelNew.add(textField);
        panelNew.add(buttonFind);
        panelNew.add(buttonCopyAll);
        panelNew.add(buttonCopySelect);
        panelAction.add(panelNew);
        getContentPane().add(BorderLayout.EAST, panelAction);
        model = new DefaultTableModel();
        table = new JTable(model){
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };
        // Таблица состоит из четырех столбцов
        model.addColumn("ID");
        model.addColumn("Название");
        ListSelectionModel cellSelectionModel = table.getSelectionModel();
        cellSelectionModel.addListSelectionListener(new MyListSelect());
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);
        getContentPane().add(BorderLayout.CENTER, scrollPane);
        JPanel panelInformation = new JPanel();
        JPanel panelElements = new JPanel();
        textFieldName = new JTextField(20);
        textFieldColor = new JTextField();
        textFieldWeight = new JTextField();
        textFieldGuarantee = new JTextField();
        textFieldNumOfKeysInc = new JTextField();
        textFieldDepth = new JTextField();
        textFieldLoopD = new JTextField();
        textFieldCost = new JTextField();
        textType = new JTextField();
        GridLayout experimentLayout = new GridLayout(10,2, 5, 5);
        panelElements.setLayout(experimentLayout);
        panelElements.add(new JLabel("Название"));
        panelElements.add(textFieldName);
        panelElements.add(new JLabel("Цвет"));
        panelElements.add(textFieldColor);
        panelElements.add(new JLabel("Вес (г)"));
        panelElements.add(textFieldWeight);
        panelElements.add(new JLabel("Гарантия (мес)"));
        panelElements.add(textFieldGuarantee);
        panelElements.add(new JLabel("Кол-во ключей (шт)"));
        panelElements.add(textFieldNumOfKeysInc);
        panelElements.add(new JLabel("Глубина (мм) "));
        panelElements.add(textFieldDepth);
        panelElements.add(new JLabel("Диаметр петли (мм) "));
        panelElements.add(textFieldLoopD);
        panelElements.add(new JLabel("Тип замка"));
        panelElements.add(textType);
        panelElements.add(new JLabel("Цена (руб) "));
        panelElements.add(textFieldCost);

        panelInformation.setLayout(new FlowLayout());
        panelInformation.add(panelElements);
        getContentPane().add(BorderLayout.SOUTH, panelInformation);
    }


    // Отработчик события от таблицы (списка). Событие о выборе элемента таблицы
    private class MyListSelect implements ListSelectionListener{
        @Override
        public void valueChanged(ListSelectionEvent e) {
            int[] ss = table.getSelectedRows();
            if (ss.length == 1){
                int id = (Integer)model.getValueAt(ss[0], 0);
                LockBase cardBase = shopManagement.getOf(id);
                if (cardBase == null)
                    return;
                textFieldName.setText(cardBase.getName());
                textFieldColor.setText(cardBase.getColor());
                textFieldWeight.setText(Float.toString(cardBase.getWeight()));
                textFieldGuarantee.setText(Integer.toString( cardBase.getGuarantee()));
                textFieldNumOfKeysInc.setText(Integer.toString( cardBase.getNumberOfKeysIncluded()));
                textFieldCost.setText(Integer.toString(cardBase.getCost()));
                if (cardBase.getClass().getName() == MortiseLocks.class.getName()){
                    textType.setText("Врезной");
                    MortiseLocks CardV = (MortiseLocks)cardBase;
                    textFieldDepth.setText(Integer.toString(CardV.getDepth()));
                    textFieldLoopD.setText("-");
                }
                if (cardBase.getClass().getName() == Padlocks.class.getName()){
                    textType.setText("Навесной");
                    Padlocks CardN = (Padlocks)cardBase;
                    textFieldLoopD.setText(Float.toString(CardN.getLoopDi()));
                    textFieldDepth.setText("-");
                }
            }else {
                textFieldName.setText("");
                textFieldColor.setText("");
                textFieldWeight.setText("");
                textFieldGuarantee.setText("");
                textFieldNumOfKeysInc.setText("");
                textType.setText("");
                textFieldDepth.setText("");
                textFieldLoopD.setText("");
                textFieldCost.setText("");
            }
        }
    }
    private class ButtonAdd implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            // Отображаем диалоговое окно "Добавить запись"
            DialogAdd dialogAdd = new DialogAdd();
            dialogAdd.setModal(true);
            dialogAdd.setSize(600, 400);
            dialogAdd.setVisible(true);
            if (dialogAdd.isOk()){
                if (dialogAdd.isV()){
                    // Добавляем запись о врезных замках базу записей
                    shopManagement.addLock(dialogAdd.getName(), dialogAdd.getWeight(),
                            dialogAdd.getColor(), dialogAdd.getGuarantee(),
                            dialogAdd.getNumOfKeysInc(),dialogAdd.getCost(),dialogAdd.getDepth());

                    MortiseLocks cardAdd =  (MortiseLocks)shopManagement.getAll(shopManagement.getCount() - 1); // getCard
                    Object[] addRow = new Object[]{cardAdd.getId(), cardAdd.getName(),
                            Integer.toString(cardAdd.getWeight()), cardAdd.getColor(),
                            Integer.toString(cardAdd.getGuarantee()),Integer.toString(cardAdd.getNumberOfKeysIncluded()),
                            Integer.toString(cardAdd.getCost()), Integer.toString(cardAdd.getDepth())};
                    model.addRow(addRow);
                }
                if (dialogAdd.isN()){
                    // Добавляем запись о навесных замках в базу записей
                    shopManagement.addLock(dialogAdd.getName(), dialogAdd.getWeight(),
                            dialogAdd.getColor(), dialogAdd.getGuarantee(), dialogAdd.getNumOfKeysInc(),
                            dialogAdd.getCost(), dialogAdd.getLoopD());
                    // Добавляем запись о навесных замках в список
                    Padlocks cardAdd = (Padlocks)shopManagement.getAll(shopManagement.getCount() - 1);
                    Object[] addRow = new Object[]{cardAdd.getId(), cardAdd.getName(),
                            Integer.toString(cardAdd.getWeight()), cardAdd.getColor(),
                            Integer.toString(cardAdd.getGuarantee()), Integer.toString(cardAdd.getNumberOfKeysIncluded()),
                            Integer.toString(cardAdd.getCost()),Float.toString(cardAdd.getLoopDi())};
                    model.addRow(addRow);
                }
            }
        }
    }
    private class ButtonDel implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int[] ss = table.getSelectedRows();
            if (ss.length == 1){
                int idSelect = (Integer)model.getValueAt(ss[0], 0);
                model.removeRow(ss[0]);
                shopManagement.removeCard(idSelect);
            }
        }
    }
    private class ButtonFind implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (textField.getText().isEmpty())
                return;
            ArrayList<LockBase> cards = shopManagement.findCard(textField.getText());
            table.clearSelection();
            for (int i = 0; i < cards.size(); i++) {
                for (int j = 0; j < table.getRowCount(); j++){
                    if ((Integer)table.getValueAt(j, 0) == cards.get(i).getId()){
                        table.addRowSelectionInterval(j, j);
                    }
                }
            }
        }
    }
    // Копирует в буфер обмена список всех товаров
    private class ButtonCopyAll implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String str = "Ассортимент магазина:\n";
            for (int i = 0; i < shopManagement.getCount(); i++){
                LockBase lockBase = shopManagement.getAll(i);
                str = str + lockBase.getInfo() + "\n";
            }
            StringSelection selection = new StringSelection(str);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);
        }
    }
    // Копирует в буфер обмена список всех выделенных товаров
    private class ButtonCopySelect implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int[] ss = table.getSelectedRows();
            String str = "Ассортимент магазина:\n";
            for (int i = 0; i < ss.length; i++){
                int id = (Integer)model.getValueAt(ss[i], 0);
                LockBase cardBase = shopManagement.getOf(id);
                str = str + cardBase.getInfo() + "\n";
            }
            StringSelection selection = new StringSelection(str);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);
        }
    }
}