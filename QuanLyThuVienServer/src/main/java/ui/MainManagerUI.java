package ui;

import dao.SachDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;

public class MainManagerUI extends JFrame {
    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private JButton btn4;
    private JButton btn5;
    private JPanel jp1;
    private JPanel jp2;
    private JPanel jp3;
    private JPanel jp4;
    private JPanel jpp;
    private JPanel mainmanager;
    private JTable table1;
    private JTextField textField1;
    private JButton tìmButton;
    private JComboBox comboBox1;
    private JTextField textField2;
    private JButton tìmButton1;
    private JTable table2;
    private JTable table3;
    private JTextField textField4;
    private JButton tìmButton2;
    private JComboBox comboBox2;
    private JTextField textField3;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JButton cậpNhậtButton;
    private JButton trảSáchButton;
    private JButton loadFileButton;
    private JButton lưuSáchButton;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JButton thốngKêButton;
    private JButton xuấtFileButton;

    private static String url = "rmi://localhost:1234/";
    private SachDAO sachDAO;

    public MainManagerUI() {
        super();
        setTitle("Quản lý thư viện");
        setContentPane(mainmanager);
        setMinimumSize(new java.awt.Dimension(1000, 600));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);


        

        setJpanel1();
        setJpanel2();
        setJpanel3();
        setJpanel4();

        event();

        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public void setJpanel1() {
        createTable1();
    }

    public void setJpanel2() {
        createTable2();
    }

    public void setJpanel3() {
        createTable3();
    }

    public void setJpanel4() {
    }

    public void createTable1() {
        table1.setModel(new DefaultTableModel(new Object[][]{
                new Object[]{"1", "Sách 1", "Tác giả 1", "Thể loại 1", "10"},
                new Object[]{"2", "Sách 2", "Tác giả 2", "Thể loại 2", "20"},
                new Object[]{"3", "Sách 3", "Tác giả 3", "Thể loại 3", "30"},
                new Object[]{"4", "Sách 4", "Tác giả 4", "Thể loại 4", "40"},
                new Object[]{"5", "Sách 5", "Tác giả 5", "Thể loại 5", "50"},
                new Object[]{"6", "Sách 6", "Tác giả 6", "Thể loại 6", "60"},
                new Object[]{"7", "Sách 7", "Tác giả 7", "Thể loại 7", "70"},
                new Object[]{"8", "Sách 8", "Tác giả 8", "Thể loại 8", "80"},
                new Object[]{"9", "Sách 9", "Tác giả 9", "Thể loại 9", "90"},
                new Object[]{"10", "Sách 10", "Tác giả 10", "Thể loại 10", "100"}
        },
                new String[]{"Mã sách", "Tên sách", "Tác giả", "Thể loại", "Số lượng"}));

        table1.setRowHeight(50);
    }

    public void createTable2() {

        String[] header = {"MSSV", "Mã sách", "Tên sách", "Số Lượng", "Ngày mượn", "Chọn"};
        table2.setModel(new DefaultTableModel(
                                new Object[][]{
                                        {"1", "1", "Sách 1", "1", "2021-01-01", false},
                                        {"2", "2", "Sách 2", "2", "2021-01-02", false},
                                        {"3", "3", "Sách 3", "3", "2021-01-03", false},
                                        {"4", "4", "Sách 4", "4", "2021-01-04", false},
                                        {"5", "5", "Sách 5", "5", "2021-01-05", false},
                                        {"6", "6", "Sách 6", "6", "2021-01-06", false},
                                        {"7", "7", "Sách 7", "7", "2021-01-07", false},
                                        {"8", "8", "Sách 8", "8", "2021-01-08", false},
                                        {"9", "9", "Sách 9", "9", "2021-01-09", false},
                                        {"10", "10", "Sách 10", "10", "2021-01-10", false}
                                },
                                header) {
                            Class[] types = new Class[]{
                                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
                            };
                            boolean[] canEdit = new boolean[]{
                                    false, false, false, false, false, true
                            };

                            public Class getColumnClass(int columnIndex) {
                                return types[columnIndex];
                            }

                            public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit[columnIndex];
                            }
                        }
        );
        table2.setRowHeight(50);
    }

    public void createTable3() {
        table3.setModel(new DefaultTableModel(new Object[][]{
                new Object[]{"1", "Sách 1", "Tác giả 1", "Thể loại 1", "10"},
                new Object[]{"2", "Sách 2", "Tác giả 2", "Thể loại 2", "20"},
                new Object[]{"3", "Sách 3", "Tác giả 3", "Thể loại 3", "30"},
                new Object[]{"4", "Sách 4", "Tác giả 4", "Thể loại 4", "40"},
                new Object[]{"5", "Sách 5", "Tác giả 5", "Thể loại 5", "50"},
                new Object[]{"6", "Sách 6", "Tác giả 6", "Thể loại 6", "60"},
                new Object[]{"7", "Sách 7", "Tác giả 7", "Thể loại 7", "70"},
                new Object[]{"8", "Sách 8", "Tác giả 8", "Thể loại 8", "80"},
                new Object[]{"9", "Sách 9", "Tác giả 9", "Thể loại 9", "90"},
                new Object[]{"10", "Sách 10", "Tác giả 10", "Thể loại 10", "100"}
        },
                new String[]{"Mã sách", "Tên sách", "Tác giả", "Thể loại", "Số lượng"}));

        table3.setRowHeight(50);
    }
    public void event() {
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jpp.removeAll();
                jpp.add(jp1);
                jpp.revalidate();
                jpp.repaint();
            }
        });
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jpp.removeAll();
                jpp.add(jp2);
                jpp.revalidate();
                jpp.repaint();
            }
        });

        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jpp.removeAll();
                jpp.add(jp3);
                jpp.revalidate();
                jpp.repaint();
            }
        });

        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jpp.removeAll();
                jpp.add(jp4);
                jpp.revalidate();
                jpp.repaint();
            }
        });
    }

    public void eventJPanel1() {

    }



}
