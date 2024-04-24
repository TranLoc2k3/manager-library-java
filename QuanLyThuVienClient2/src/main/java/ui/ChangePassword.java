package ui;

import at.favre.lib.crypto.bcrypt.BCrypt;
import dao.GiaoVienDAO;
import dao.SinhVienDAO;
import entity.GiaoVien;
import entity.SinhVien;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;

public class ChangePassword extends JFrame{
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JButton đổiMậtKhẩuQuảnButton;
    private JButton đổiMậtKhẩuSinhButton;
    private JPanel changePWPanel;
    private JButton quayLạiĐăngNhậpButton;
    private JPasswordField passwordField3;
    private GiaoVienDAO giaoVienDao;
    private SinhVienDAO sinhVienDao;
    private static final String url = "rmi://localhost:1234/";

    public ChangePassword() {
        super();
        setTitle("Change Password");
        setContentPane(changePWPanel);
        setMinimumSize(new Dimension(600, 350));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

        try {
            sinhVienDao = (SinhVienDAO) Naming.lookup(url + "sinhvien");
            giaoVienDao = (GiaoVienDAO) Naming.lookup(url + "giaovien");
        } catch (Exception e) {
            e.printStackTrace();
        }




        quayLạiĐăngNhậpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginForm();
                setVisible(false);
            }
        });
        đổiMậtKhẩuQuảnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = textField1.getText();
                String password = new String(passwordField1.getPassword());
                String newPassword = new String(passwordField2.getPassword());
                String rePassword = new String(passwordField3.getPassword());
                if (username.equals("") || password.equals("") || newPassword.equals("") || rePassword.equals("")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
                    return;
                }

                try {
                    GiaoVien gv = giaoVienDao.getGiaoVien(username);
                    if (gv == null) {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy giáo viên");
                        return;
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

                if (!newPassword.equals(rePassword)) {
                    JOptionPane.showMessageDialog(null, "Mật khẩu mới không khớp");
                    return;
                }

                try {
                    GiaoVien gv = giaoVienDao.getGiaoVien(username);
                    BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), gv.getPassword());
                    if (result.verified) {
                        BCrypt.Result result2 = BCrypt.verifyer().verify(newPassword.toCharArray(), gv.getPassword());
                        if (result2.verified) {
                            JOptionPane.showMessageDialog(null, "Mật khẩu mới không được trùng với mật khẩu cũ");
                            return;
                        }
                        String hashed = BCrypt.withDefaults().hashToString(12, newPassword.toCharArray());
                        gv.setPassword(hashed);
                        giaoVienDao.update(gv);
                        JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công");
                    } else {
                        JOptionPane.showMessageDialog(null, "Sai mật khẩu cũ");
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        đổiMậtKhẩuSinhButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = textField1.getText();
                String password = new String(passwordField1.getPassword());
                String newPassword = new String(passwordField2.getPassword());
                String rePassword = new String(passwordField3.getPassword());
                if (username.equals("") || password.equals("") || newPassword.equals("") || rePassword.equals("")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
                    return;
                }

                try {
                    SinhVien sv = sinhVienDao.getSinhVien(username);
                    if (sv == null) {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy sinh viên");
                        return;
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

                if (!newPassword.equals(rePassword)) {
                    JOptionPane.showMessageDialog(null, "Mật khẩu mới không khớp");
                    return;
                }

                try {
                    SinhVien sv = sinhVienDao.getSinhVien(username);
                    BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), sv.getPassword());
                    if (result.verified) {
                        BCrypt.Result result2 = BCrypt.verifyer().verify(newPassword.toCharArray(), sv.getPassword());
                        if (result2.verified) {
                            JOptionPane.showMessageDialog(null, "Mật khẩu mới không được trùng với mật khẩu cũ");
                            return;
                        }
                        String hashed = BCrypt.withDefaults().hashToString(12, newPassword.toCharArray());
                        sv.setPassword(hashed);
                        sinhVienDao.update(sv);
                        JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công");
                    } else {
                        JOptionPane.showMessageDialog(null, "Sai mật khẩu cũ");
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public static void main(String[] args) {
        new ChangePassword();
    }


}
