package ui;

import dao.ChiTietMuonSachDAO;
import dao.SachDAO;
import entity.ChiTietMuonSach;
import entity.Sach;
import entity.SinhVien;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.Naming;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import javax.swing.JFileChooser;

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
    private JButton thêmButton;
    private JButton trảSáchButton;
    private JButton loadFileButton;
    private JButton lưuSáchButton;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JButton thốngKêButton;
    private JButton updateBtn;
    private JButton xuấtFileButton;
    private JPanel jpchart;
    private JLabel userName;

    private static final String url = "rmi://localhost:1234/";

    private SachDAO sachDAO;
    private ChiTietMuonSachDAO chiTietMuonSachDAO;

    public MainManagerUI(String name) {
        super();
        setTitle("Main Manager");
        setContentPane(mainmanager);
        setMinimumSize(new java.awt.Dimension(1000, 600));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);


        try {
            sachDAO = (SachDAO) Naming.lookup(url + "sach");
            chiTietMuonSachDAO = (ChiTietMuonSachDAO) Naming.lookup(url + "chitietmuonsach");
        } catch (Exception e) {
            e.printStackTrace();
        }

        userName.setText(name);
        initGUI();

        event();

        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new LoginForm();
            }
        });
        tìmButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String typeSearch = comboBox1.getSelectedItem().toString();
                String keyWord = textField1.getText();
                List<Sach> ls = null;
                switch (typeSearch) {
                    case "Tên sách":
                        try {
                            ls = sachDAO.getSachByTenSach(keyWord);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    case "Tác giả":
                        try {
                            ls = sachDAO.getSachByTacGia(keyWord);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    case "Thể loại":
                        try {
                            ls = sachDAO.getSachByTheLoai(keyWord);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                }
                Object[][] data = new Object[ls.size()][5];

                for (int i = 0; i < ls.size(); i++) {
                    Sach s = ls.get(i);
                    data[i][0] = s.getMaSach();
                    data[i][1] = s.getTenSach();
                    data[i][2] = s.getTacGia();
                    data[i][3] = s.getTheloai();
                    data[i][4] = s.getSoLuong();
                }

                DefaultTableModel model1 = (DefaultTableModel) table1.getModel();
                model1.setRowCount(0);
                for (int i = 0; i < ls.size(); i++) {
                    model1.addRow(data[i]);
                }
            }
        });

        tìmButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mssv = textField2.getText();
                if (mssv.equals("")) {
                    createTable2();
                    return;
                }
                try {
                    List<ChiTietMuonSach> ls = chiTietMuonSachDAO.findByTraSachFalseAndSinhVien_MaSinhVienLike(mssv);
                    Object[][] data = new Object[ls.size()][7];

                    for (int i = 0; i < ls.size(); i++) {
                        ChiTietMuonSach c = ls.get(i);
                        data[i][0] = c.getMaMuonSach();
                        data[i][1] = c.getSinhVien().getMaSinhVien();
                        data[i][2] = c.getSach().getMaSach();
                        data[i][3] = c.getSach().getTenSach();
                        data[i][4] = c.getSoLuong();
                        data[i][5] = c.getNgayMuon();
                        data[i][6] = true;
                    }

                    DefaultTableModel model2 = (DefaultTableModel) table2.getModel();
                    model2.setRowCount(0);
                    for (int i = 0; i < ls.size(); i++) {
                        model2.addRow(data[i]);
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        trảSáchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChiTietMuonSach c = new ChiTietMuonSach();
                Sach sach = new Sach();
                for (int i = 0; i < table2.getRowCount(); i++) {
                    if (table2.getValueAt(i, 6).equals(true)) {
                        try {
                            c = chiTietMuonSachDAO.getChiTietMuonSach(Integer.parseInt(table2.getValueAt(i, 0).toString()));

                            sach = sachDAO.getSach(c.getSach().getMaSach());
                            sach.setSoLuong(sach.getSoLuong() + c.getSoLuong());
                            sachDAO.update(sach);

                            c.setTraSach(true);
                            c.setNgayTra(new java.sql.Date(System.currentTimeMillis()));
                            chiTietMuonSachDAO.update(c);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
                createTable2();
            }
        });

        table3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowSelected = table3.getSelectedRow();

                textField3.setText(table3.getValueAt(rowSelected, 0).toString());
                textField5.setText(table3.getValueAt(rowSelected, 1).toString());
                textField6.setText(table3.getValueAt(rowSelected, 2).toString());
                textField7.setText(table3.getValueAt(rowSelected, 3).toString());
                textField8.setText(table3.getValueAt(rowSelected, 4).toString());

                updateBtn.setEnabled(true);
                thêmButton.setText("Clear");

            }
        });

        thêmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String stateBtn = thêmButton.getText();
                if (stateBtn.equals("Clear")) {
                    textField3.setText("Tự động sinh mã");
                    textField5.setText("");
                    textField6.setText("");
                    textField7.setText("");
                    textField8.setText("");
                    updateBtn.setEnabled(false);
                    thêmButton.setText("Thêm");
                    return;
                } else if (stateBtn.equals("Thêm")) {
                    Sach s = new Sach();
                    if (textField5.getText().equals("") || textField6.getText().equals("") || textField7.getText().equals("") || textField8.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
                        return;
                    }
                    s.setTenSach(textField5.getText());
                    s.setTacGia(textField6.getText());
                    s.setTheloai(textField7.getText());
                    s.setSoLuong(Integer.parseInt(textField8.getText()));
                    try {
                        sachDAO.insert(s);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    setJpanel3();
                }
            }
        });
        tìmButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String typeSearch = comboBox2.getSelectedItem().toString();
                String keyWord = textField4.getText();
                List<Sach> ls = new ArrayList<Sach>();
                switch (typeSearch) {
                    case "Mã sách":
                        try {
                            Sach sach = sachDAO.getSach(Integer.parseInt(keyWord));
                            ls.add(sach);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    case "Tên sách":
                        try {
                            ls = sachDAO.getSachByTenSach(keyWord);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    case "Tác giả":
                        try {
                            ls = sachDAO.getSachByTacGia(keyWord);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    case "Thể loại":
                        try {
                            ls = sachDAO.getSachByTheLoai(keyWord);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                }
                Object[][] data = new Object[ls.size()][5];

                for (int i = 0; i < ls.size(); i++) {
                    Sach s = ls.get(i);
                    data[i][0] = s.getMaSach();
                    data[i][1] = s.getTenSach();
                    data[i][2] = s.getTacGia();
                    data[i][3] = s.getTheloai();
                    data[i][4] = s.getSoLuong();
                }

                DefaultTableModel model3 = (DefaultTableModel) table3.getModel();
                model3.setRowCount(0);
                for (int i = 0; i < ls.size(); i++) {
                    model3.addRow(data[i]);
                }
            }
        });
        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField5.getText().equals("") || textField6.getText().equals("") || textField7.getText().equals("") || textField8.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
                    return;
                }
                Sach s = new Sach();
                s.setMaSach(Integer.parseInt(textField3.getText()));
                s.setTenSach(textField5.getText());
                s.setTacGia(textField6.getText());
                s.setTheloai(textField7.getText());
                s.setSoLuong(Integer.parseInt(textField8.getText()));
                int soLuong = Integer.parseInt(textField8.getText());
                if (soLuong <= 0) {
                    JOptionPane.showMessageDialog(null, "Số lượng không hợp lệ!");
                    return;
                }
                try {
                    sachDAO.update(s);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                setJpanel3();
            }
        });
        loadFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (loadFileButton.getText().equals("Load File")) {
                    JFileChooser fileChooser = new JFileChooser();
                    int returnValue = fileChooser.showOpenDialog(null);
                    if (returnValue == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                            String line;
                            reader.readLine();
                            DefaultTableModel model = (DefaultTableModel) table3.getModel();
                            model.setRowCount(0);
                            while ((line = reader.readLine()) != null) {
                                String[] parts = line.split(",");

                                model.addRow(new Object[]{"Tự động sinh mã", parts[0], parts[1], parts[2], parts[3]});
                            }
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }

                        loadFileButton.setText("Trở về");
                        lưuSáchButton.setEnabled(true);

                    }
                } else if (loadFileButton.getText().equals("Trở về")) {
                    setJpanel3();
                }
            }
        });
        lưuSáchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sach s = new Sach();
                for (int i = 0; i < table3.getRowCount(); i++) {
                    for (int j = 0; j < table3.getRowCount(); j++) {
                        s.setTenSach(table3.getValueAt(i, 1).toString());
                        s.setTacGia(table3.getValueAt(i, 2).toString());
                        s.setTheloai(table3.getValueAt(i, 3).toString());
                        s.setSoLuong(Integer.parseInt(table3.getValueAt(i, 4).toString()));
                    }
                    try {
                        sachDAO.insert(s);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
                setJpanel3();
            }
        });
        thốngKêButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jpchart.removeAll();
                String typeSearch = comboBox3.getSelectedItem().toString();
                String time = comboBox4.getSelectedItem().toString();
                int day = 0;
                switch (time) {
                    case "1 Tuần":
                        day = 7;
                        break;
                    case "1 Tháng":
                        day = 30;
                        break;
                    case "6 Tháng":
                        day = 180;
                        break;
                    case "1 Năm":
                        day = 365;
                        break;
                }
                if (typeSearch.equals("Số lần sách mượn")) {
                    try {
                        LinkedHashMap<Sach, Integer> ls = chiTietMuonSachDAO.getNumberBorrowedBooks(day);
                        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
                        for (Sach s : ls.keySet()) {
                            dataset.addValue(ls.get(s), "Số lần mượn", s.getTenSach());
                        }
                        JFreeChart chart = ChartFactory.createBarChart(
                                "Số lần mượn sách trong " + time, // chart title
                                "Sách", // domain axis label
                                "Số lần mượn", // range axis label
                                dataset, // data
                                PlotOrientation.VERTICAL, // orientation
                                true, // include legend
                                true, // tooltips
                                false // urls
                        );

                        ChartPanel chartPanel = new ChartPanel(chart);
                        jpchart.add(chartPanel);
                        jpchart.revalidate();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                } else if (typeSearch.equals("Sinh viên muợn sách")) {
                    try {
                        LinkedHashMap<SinhVien, Integer> ls = chiTietMuonSachDAO.getNumberBorrowedBooksByStudent(day);
                        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
                        for (SinhVien s : ls.keySet()) {
                            dataset.addValue(ls.get(s), "Số lần mượn", s.getTenSinhVien());
                        }
                        JFreeChart chart = ChartFactory.createBarChart(
                                "Số lần mượn sách theo sinh viên trong " + time,
                                "Sinh viên",
                                "Số lần mượn",
                                dataset, // data
                                PlotOrientation.VERTICAL,
                                true, // include legend
                                true, // tooltips
                                false // urls

                        );
                        ChartPanel chartPanel = new ChartPanel(chart);
                        jpchart.add(chartPanel);
                        jpchart.revalidate();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }


            }
        });
        xuấtFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Chọn vị trí lưu File");

                int userSelection = fileChooser.showSaveDialog(null);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    String typeSearch = comboBox3.getSelectedItem().toString();
                    String time = comboBox4.getSelectedItem().toString();
                    int day = 0;
                    switch (time) {
                        case "1 Tuần":
                            day = 7;
                            break;
                        case "1 Tháng":
                            day = 30;
                            break;
                        case "6 Tháng":
                            day = 180;
                            break;
                        case "1 Năm":
                            day = 365;
                            break;
                    }
                    Calendar calendar = Calendar.getInstance();
                    Calendar calendar2 = Calendar.getInstance();
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    calendar2.add(Calendar.DATE, -day);
                    if (typeSearch.equals("Số lần sách mượn")) {

                        try {
                            Writer writer = Files.newBufferedWriter(Paths.get(fileToSave.getAbsolutePath()), StandardCharsets.UTF_8);
                            writer.write('\ufeff');
                            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
                            csvPrinter.printRecord("SỐ LIỆU THỐNG KÊ SỐ LẦN MƯỢN SÁCH ( " + format.format(calendar2.getTime()) + " - " + format.format(calendar.getTime()) + " )" );
                            csvPrinter.printRecord("Mã Sách", "Tên sách", "Số lần mượn sách");
                            LinkedHashMap<Sach, Integer> ls = chiTietMuonSachDAO.getNumberBorrowedBooks(day);
                            for (Sach s : ls.keySet()) {
                                csvPrinter.printRecord(s.getMaSach(), s.getTenSach(), ls.get(s));
                            }
                            csvPrinter.flush();
                            writer.close();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    } else if (typeSearch.equals("Sinh viên muợn sách")) {
                        System.out.println("Runnig here");
                        try {
                            Writer writer = Files.newBufferedWriter(Paths.get(fileToSave.getAbsolutePath()), StandardCharsets.UTF_8);
                            writer.write('\ufeff');
                            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
                            csvPrinter.printRecord("SỐ LIỆU THỐNG KÊ SINH VIÊN MƯỢN SÁCH ( " + format.format(calendar2.getTime()) + " - " + format.format(calendar.getTime()) + " )");
                            csvPrinter.printRecord("Mã sinh viên", "Tên sinh viên", "Số lần mượn sách");
                            LinkedHashMap<SinhVien, Integer> ls = chiTietMuonSachDAO.getNumberBorrowedBooksByStudent(day);
                            for (SinhVien s : ls.keySet()) {
                                csvPrinter.printRecord(s.getMaSinhVien(), s.getTenSinhVien(), ls.get(s));
                            }
                            csvPrinter.flush();
                            writer.close();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            }
        });
    }

    public void initGUI() {

        setJpanel1();
        setJpanel2();
        setJpanel3();
        setJpanel4();

    }

    public void setJpanel1() {
        comboBox1.setModel(new DefaultComboBoxModel(new String[]{"Tên sách", "Tác giả", "Thể loại"}));
        textField1.setText("");
        createTable1();
    }

    public void setJpanel2() {
        textField2.setText("");
        createTable2();
    }

    public void setJpanel3() {
        comboBox2.setModel(new DefaultComboBoxModel(new String[]{"Mã sách", "Tên sách", "Tác giả", "Thể loại"}));
        textField3.setText("Tự động sinh mã");
        textField3.setEditable(false);
        textField4.setText("");
        textField5.setText("");
        textField6.setText("");
        textField7.setText("");
        textField8.setText("");

        updateBtn.setEnabled(false);
        thêmButton.setText("Thêm");
        lưuSáchButton.setEnabled(false);
        loadFileButton.setText("Load File");
        createTable3();
    }

    public void setJpanel4() {
        comboBox3.setModel(new DefaultComboBoxModel(new String[]{"Số lần sách mượn", "Sinh viên muợn sách"}));
        comboBox4.setModel(new DefaultComboBoxModel(new String[]{"1 Tuần", "1 Tháng", "6 Tháng", "1 Năm"}));
        try {
            LinkedHashMap<Sach, Integer> ls = chiTietMuonSachDAO.getNumberBorrowedBooks(7);
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (Sach s : ls.keySet()) {
                dataset.addValue(ls.get(s), "Số lần mượn", s.getTenSach());
            }
            JFreeChart chart = ChartFactory.createBarChart(
                    "Số lần mượn sách trong 1 Tuần", // chart title
                    "Sách", // domain axis label
                    "Số lần mượn", // range axis label
                    dataset, // data
                    PlotOrientation.VERTICAL, // orientation
                    true, // include legend
                    true, // tooltips
                    false // urls
            );

            ChartPanel chartPanel = new ChartPanel(chart);
            jpchart.add(chartPanel);
            jpchart.revalidate();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void createTable1() {
        String[] header = {"Mã sách", "Tên sách", "Tác giả", "Thể loại", "Số lượng"};
        try {
            List<Sach> ls = sachDAO.getAll();
            Object[][] data = new Object[ls.size()][5];
            for (int i = 0; i < ls.size(); i++) {
                Sach s = ls.get(i);
                data[i][0] = s.getMaSach();
                data[i][1] = s.getTenSach();
                data[i][2] = s.getTacGia();
                data[i][3] = s.getTheloai();
                data[i][4] = s.getSoLuong();
            }
            table1.setModel(new DefaultTableModel(data, header) {
                Class[] types = new Class[]{java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class};
                boolean[] canEdit = new boolean[]{false, false, false, false, false};

                public Class getColumnClass(int columnIndex) {
                    return types[columnIndex];
                }

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        table1.setRowHeight(50);
    }

    public void createTable2() {
        String[] header = {"Mã mượn sách", "MSSV", "Mã sách", "Tên sách", "Số Lượng", "Ngày mượn", "Chọn"};
        try {
            List<ChiTietMuonSach> ls = chiTietMuonSachDAO.findByTraSachFalse();
            Object[][] data = new Object[ls.size()][7];
            for (int i = 0; i < ls.size(); i++) {
                ChiTietMuonSach c = ls.get(i);
                data[i][0] = c.getMaMuonSach();
                data[i][1] = c.getSinhVien().getMaSinhVien();
                data[i][2] = c.getSach().getMaSach();
                data[i][3] = c.getSach().getTenSach();
                data[i][4] = c.getSoLuong();
                data[i][5] = c.getNgayMuon();
                data[i][6] = false;
            }
            table2.setModel(new DefaultTableModel(data, header) {
                Class[] types = new Class[]{java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class};
                boolean[] canEdit = new boolean[]{false, false, false, false, false, false, true};

                public Class getColumnClass(int columnIndex) {
                    return types[columnIndex];
                }

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        table2.setRowHeight(50);
    }

    public void createTable3() {

        String[] header = {"Mã sách", "Tên sách", "Tác giả", "Thể loại", "Số lượng"};
        try {
            List<Sach> ls = sachDAO.getAll();
            Object[][] data = new Object[ls.size()][5];
            for (int i = 0; i < ls.size(); i++) {
                Sach s = ls.get(i);
                data[i][0] = s.getMaSach();
                data[i][1] = s.getTenSach();
                data[i][2] = s.getTacGia();
                data[i][3] = s.getTheloai();
                data[i][4] = s.getSoLuong();
            }
            table3.setModel(new DefaultTableModel(data, header) {
                Class[] types = new Class[]{java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class};
                boolean[] canEdit = new boolean[]{false, false, false, false, false};

                public Class getColumnClass(int columnIndex) {
                    return types[columnIndex];
                }

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        table3.setRowHeight(50);
    }

    public void event() {
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jpp.removeAll();
                setJpanel1();
                jpp.add(jp1);
                jpp.revalidate();
                jpp.repaint();
            }
        });
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jpp.removeAll();
                setJpanel2();
                jpp.add(jp2);
                jpp.revalidate();
                jpp.repaint();
            }
        });

        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jpp.removeAll();
                setJpanel3();
                jpp.add(jp3);
                jpp.revalidate();
                jpp.repaint();
            }
        });

        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jpp.removeAll();
                setJpanel4();
                jpp.add(jp4);
                jpp.revalidate();
                jpp.repaint();
            }
        });
    }

    public void eventJPanel1() {

    }

    public static void main(String[] args) {
//        new MainManagerUI();
    }


}
