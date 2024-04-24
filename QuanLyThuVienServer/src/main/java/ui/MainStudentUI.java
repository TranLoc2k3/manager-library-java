package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainStudentUI extends  JFrame{
    private JButton trảSáchButton;
    private JButton mượnSáchButton;
    private JButton đăngXuấtButton;
    private JPanel parrentPanel;
    private JPanel jp1;
    private JPanel jp2;
    private JPanel jp;
    private JTable table1;
    private JButton trảSáchButton1;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JButton tìmKiếmButton;
    private JTable table2;
    private JButton thêmButton;
    private JTable table3;
    private JButton mượnButton;

    public MainStudentUI(JFrame parent) {
        super();
        setTitle("Sinh viên");
        setContentPane(parrentPanel);
        setMinimumSize(new Dimension(1000, 600));
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

        event();
        setJpanel1();
        setJpanel2();
        mượnSáchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public void event() {
        eventPanel1();
        eventPanel2();
    }

    public void setJpanel1() {
        createTable1();
    }

    public void setJpanel2() {
        createTable2();
        createTable3();
    }

    public void createTable1() {
        table1.setModel(new DefaultTableModel(
                new Object[][] {
                        {"1", "Sách 1", "Tác giả 1", "Thể loại 1", "5", "2021-05-01", true},
                        {"2", "Sách 2", "Tác giả 2", "Thể loại 2", "5", "2021-05-01", false},
                        {"3", "Sách 3", "Tác giả 3", "Thể loại 3", "5", "2021-05-01", false},
                        {"4", "Sách 4", "Tác giả 4", "Thể loại 4", "5", "2021-05-01", false},
                        {"5", "Sách 5", "Tác giả 5", "Thể loại 5", "5", "2021-05-01", false},
                        {"6", "Sách 6", "Tác giả 6", "Thể loại 6", "5", "2021-05-01", false},
                        {"7", "Sách 7", "Tác giả 7", "Thể loại 7", "5", "2021-05-01", false},
                        {"8", "Sách 8", "Tác giả 8", "Thể loại 8", "5", "2021-05-01", false},
                        {"9", "Sách 9", "Tác giả 9", "Thể loại 9", "5", "2021-05-01", false},
                        {"10", "Sách 10", "Tác giả 10", "Thể loại 10", "5", "2021-05-01", false}
                },
                new String[] {"Mã sách", "Tên sách", "Tác giả", "Thể loại", "Số lượng", "Ngày mượn", "Chọn"}
        )
                        {
                            Class[] types = new Class [] {
                                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
                            };
                            boolean[] canEdit = new boolean [] {
                                    false, false, false, false, false, false, true
                            };
                            public Class getColumnClass(int columnIndex) {
                                return types [columnIndex];
                            }
                            public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit [columnIndex];
                            }
                        }
        );
        table1.setRowHeight(30);
    }
    public void eventPanel1() {
        trảSáchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jp.removeAll();
                jp.add(jp1);
                jp.validate();
                jp.repaint();
            }
        });
    }

    public void createTable2() {
        table2.setModel(new DefaultTableModel(
                new Object[][]{
                        {"1", "Sách 1", "Tác giả 1", "Thể loại 1", "5"},
                        {"2", "Sách 2", "Tác giả 2", "Thể loại 2", "5"},
                        {"3", "Sách 3", "Tác giả 3", "Thể loại 3", "5"},
                        {"4", "Sách 4", "Tác giả 4", "Thể loại 4", "5"},
                        {"5", "Sách 5", "Tác giả 5", "Thể loại 5", "5"},
                        {"6", "Sách 6", "Tác giả 6", "Thể loại 6", "5"},
                        {"7", "Sách 7", "Tác giả 7", "Thể loại 7", "5"},
                        {"8", "Sách 8", "Tác giả 8", "Thể loại 8", "5"},
                        {"9", "Sách 9", "Tác giả 9", "Thể loại 9", "5"},
                        {"10", "Sách 10", "Tác giả 10", "Thể loại 10", "5"}
                }, new String[]{"Mã sách", "Tên sách", "Tác giả", "Thể loại", "Số lượng"}));
        table2.setRowHeight(30);
    }

    public void createTable3() {
        table3.setModel(new DefaultTableModel(
                new Object[][]{
                        {"1", "Sách 1", "Tác giả 1", "Thể loại 1"},
                        {"2", "Sách 2", "Tác giả 2", "Thể loại 2"},
                        {"3", "Sách 3", "Tác giả 3", "Thể loại 3"},
                        {"4", "Sách 4", "Tác giả 4", "Thể loại 4"},
                        {"5", "Sách 5", "Tác giả 5", "Thể loại 5"},
                        {"6", "Sách 6", "Tác giả 6", "Thể loại 6"},
                        {"7", "Sách 7", "Tác giả 7", "Thể loại 7"},
                        {"8", "Sách 8", "Tác giả 8", "Thể loại 8"},
                        {"9", "Sách 9", "Tác giả 9", "Thể loại 9"},
                        {"10", "Sách 10", "Tác giả 10", "Thể loại 10"}
                },
                new String[]{"Mã sách", "Tên sách", "Tác giả", "Thể loại"}
        ));
        table3.setRowHeight(30);
    }


    public void eventPanel2() {
        mượnSáchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jp.removeAll();
                jp.add(jp2);
                jp.validate();
                jp.repaint();
            }
        });
    }

}
