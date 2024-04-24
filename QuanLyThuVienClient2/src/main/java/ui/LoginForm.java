package ui;

import dao.GiaoVienDAO;
import dao.SinhVienDAO;
import entity.GiaoVien;
import entity.SinhVien;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.Naming;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class LoginForm extends JFrame {
    private JTextField textField1;
    private JPasswordField password1;
    private JButton login1;
    private JButton login2;
    private JPanel loginPanel;
    private JLabel changePassword;

    private static String url = "rmi://localhost:1234/";
    private SinhVienDAO sinhVienDao;
    private GiaoVienDAO giaoVienDao;

    public LoginForm() {
        super();
        setTitle("Login");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(600, 350));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

        // Add Dao
        try {
            sinhVienDao = (SinhVienDAO) Naming.lookup(url + "sinhvien");
            giaoVienDao = (GiaoVienDAO) Naming.lookup(url + "giaovien");
        } catch (Exception e) {
            e.printStackTrace();
        }

        login1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = textField1.getText();
                String password = new String(password1.getPassword());

                try {
                    GiaoVien gv = giaoVienDao.getGiaoVien(username);
                    if (gv != null) {
                        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), gv.getPassword());
                        if (result.verified) {
                            setVisible(false);
                            new MainManagerUI(gv.getTenGiaoVien());
                        } else {
                            JOptionPane.showMessageDialog(null, "Sai mật khẩu");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy giáo viên");
                    }

                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        login2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = textField1.getText();
                String password = new String(password1.getPassword());

                try {
                    SinhVien sv = sinhVienDao.getSinhVien(username);
                    if (sv != null) {
                        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), sv.getPassword());
                        if (result.verified) {
                            setVisible(false);
                            new MainStudentUI(sv.getMaSinhVien(), sv.getTenSinhVien());
                        } else {
                            JOptionPane.showMessageDialog(null, "Sai mật khẩu");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy sinh viên");
                    }

                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        changePassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                new ChangePassword();
                setVisible(false);
            }
        });
        changePassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                changePassword.setText("<html><u>" + changePassword.getText() + "</u></html>");            }
        });
        changePassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                changePassword.setText("Đổi mật khẩu");
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
