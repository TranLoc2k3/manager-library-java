package ui;

import dao.ChiTietMuonSachDAO;
import dao.SachDAO;
import dao.SinhVienDAO;
import entity.ChiTietMuonSach;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.rmi.Naming;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import entity.ChiTietMuonSach;
import entity.Sach;
import entity.SinhVien;

public class MainStudentUI extends JFrame {
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
    private JLabel userName;
    private JLabel mssv;
    private JLabel date;
    private ChiTietMuonSachDAO chiTietMuonSachDAO;
    private SachDAO sachDAO;
    private SinhVienDAO sinhVienDAO;
    private Map<Integer, Boolean> existedBooks = new HashMap<>();
    private static final String url = "rmi://localhost:1234/";

    public MainStudentUI(String studentID, String username) {
        super();
        setTitle("Sinh viên");
        setContentPane(parrentPanel);
        setMinimumSize(new Dimension(1000, 600));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);


        try {
            chiTietMuonSachDAO = (ChiTietMuonSachDAO) Naming.lookup(url + "chitietmuonsach");
            sachDAO = (SachDAO) Naming.lookup(url + "sach");
            sinhVienDAO = (SinhVienDAO) Naming.lookup(url + "sinhvien");
        } catch (Exception e) {
            e.printStackTrace();
        }

        userName.setText(username);
        mssv.setText(studentID);
        date.setText(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        event();
        setJpanel1();
        setJpanel2();
        đăngXuấtButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new LoginForm();
            }
        });
        tìmKiếmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String typeSearch = comboBox1.getSelectedItem().toString();
                String keyWord = textField1.getText();
                System.out.println(typeSearch + keyWord);
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

                DefaultTableModel model1 = (DefaultTableModel) table2.getModel();
                model1.setRowCount(0);
                for (int i = 0; i < ls.size(); i++) {
                    model1.addRow(data[i]);
                }
            }
        });
        table2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
        thêmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table2.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn sách cần mượn");
                    return;
                }
                String maSach = table2.getValueAt(row, 0).toString();
                int ms = Integer.parseInt(maSach);
                if (existedBooks.containsKey(ms)) {
                    JOptionPane.showMessageDialog(null, "Sách đang trong danh sách mượn!");
                    return;
                }
                int soLuong = table2.getValueAt(row, 4).hashCode();
                if (soLuong == 0) {
                    JOptionPane.showMessageDialog(null, "Sách đã hết");
                    return;
                }
                soLuong--;
                table2.setValueAt(soLuong, row, 4);


                String tenSach = table2.getValueAt(row, 1).toString();
                String tacGia = table2.getValueAt(row, 2).toString();
                String theLoai = table2.getValueAt(row, 3).toString();


                DefaultTableModel model = (DefaultTableModel) table3.getModel();
                model.addRow(new Object[]{maSach, tenSach, tacGia, theLoai});
                existedBooks.put(ms, true);



            }
        });
        mượnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) table3.getModel();
                if (model.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn sách cần mượn");
                    return;
                }

                for (int i = 0; i < model.getRowCount(); i++) {
                    ChiTietMuonSach ct = new ChiTietMuonSach();

                    String maSach = model.getValueAt(i, 0).toString();
                    Sach sach = new Sach();
                    SinhVien sv = new SinhVien();
                    String maSinhVien = mssv.getText();
                    try {
                        sach = sachDAO.getSach(Integer.parseInt(maSach));
                        sv = sinhVienDAO.getSinhVien(maSinhVien);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }

                    ct.setSach(sach);
                    ct.setSinhVien(sv);
                    ct.setNgayMuon(new java.sql.Date(System.currentTimeMillis()));
                    ct.setSoLuong(1);
                    try {
                        chiTietMuonSachDAO.insert(ct);
                        sach.setSoLuong(sach.getSoLuong() - 1);
                        sachDAO.update(sach);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }

                JOptionPane.showMessageDialog(null, "Mượn sách thành công");
                DefaultTableModel model1 = (DefaultTableModel) table3.getModel();
                model1.setRowCount(0);
            }
        });
        trảSáchButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) table1.getModel();
                for (int i = 0; i < model.getRowCount(); i++) {
                    boolean check = (boolean) model.getValueAt(i, 7);
                    if (check) {
                        int maMuonSach = Integer.parseInt(model.getValueAt(i, 0).toString());
                        try {
                            ChiTietMuonSach ct = chiTietMuonSachDAO.getChiTietMuonSach(maMuonSach);
                            ct.setNgayTra(new java.sql.Date(System.currentTimeMillis()));
                            ct.setTraSach(true);
                            chiTietMuonSachDAO.update(ct);

                            Sach sach = sachDAO.getSach(ct.getSach().getMaSach());
                            sach.setSoLuong(sach.getSoLuong() + ct.getSoLuong());
                            sachDAO.update(sach);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
                createTable1();
                JOptionPane.showMessageDialog(null, "Trả sách thành công");
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
        comboBox1.setModel(new DefaultComboBoxModel(new String[]{"Tên sách", "Tác giả", "Thể loại"}));
        existedBooks.clear();
        try {
            List<ChiTietMuonSach> ls = chiTietMuonSachDAO.getBooksBorrowed(mssv.getText());
            for (ChiTietMuonSach ct : ls) {
                existedBooks.put(ct.getSach().getMaSach(), true);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for (Map.Entry<Integer, Boolean> entry : existedBooks.entrySet()) {
            System.out.println(entry.getKey());
        }
        createTable2();
        createTable3();
    }

    public void createTable1() {
        try {
            List<ChiTietMuonSach> ls = chiTietMuonSachDAO.getBooksBorrowed(mssv.getText());
            String[] header = {"Mã CT Mượn", "Mã sách", "Tên sách", "Tác giả", "Thể loại", "Số lượng", "Ngày mượn", "Chọn"};
            DefaultTableModel model = new DefaultTableModel(header, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return column == this.getColumnCount() - 1;
                }

                @Override
                public Class<?> getColumnClass(int columnIndex) {
                    if (columnIndex == this.getColumnCount() - 1) {
                        return Boolean.class;
                    } else {
                        return String.class;
                    }
                }
            };
            table1.setModel(model);

            for (ChiTietMuonSach ct : ls) {
                Object[] row = new Object[8];
                row[0] = ct.getMaMuonSach();
                row[1] = ct.getSach().getMaSach();
                row[2] = ct.getSach().getTenSach();
                row[3] = ct.getSach().getTacGia();
                row[4] = ct.getSach().getTheloai();
                row[5] = ct.getSoLuong();
                row[6] = ct.getNgayMuon();
                row[7] = true;
                model.addRow(row);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        table1.setRowHeight(30);
    }

    public void eventPanel1() {
        trảSáchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jp.removeAll();
                setJpanel1();
                jp.add(jp1);
                jp.validate();
                jp.repaint();
            }
        });
    }

    public void createTable2() {
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
            table2.setModel(new DefaultTableModel(data, header) {
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
        table2.setRowHeight(30);
    }

    public void createTable3() {
        table3.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Mã sách", "Tên sách", "Tác giả", "Thể loại"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        table3.setRowHeight(30);
    }


    public void eventPanel2() {
        mượnSáchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jp.removeAll();
                setJpanel2();
                jp.add(jp2);
                jp.validate();
                jp.repaint();
            }
        });
    }

}
