
import at.favre.lib.crypto.bcrypt.BCrypt;
import dao.ChiTietMuonSachDAO;
import dao.GiaoVienDAO;
import dao.SachDAO;
import dao.SinhVienDAO;
import daoImpl.ChiTietMuonSachDAOimpl;
import daoImpl.GiaoVienDAOimpl;
import daoImpl.SachDAOimpl;
import daoImpl.SinhVienDAOimpl;
import entity.ChiTietMuonSach;
import entity.Sach;
import entity.SinhVien;
import ui.LoginForm;
import ui.MainManagerUI;
import ui.MainStudentUI;

import java.rmi.RemoteException;
import java.util.LinkedHashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        ChiTietMuonSachDAO chiTietMuonSachDAO = new ChiTietMuonSachDAOimpl();
        List<ChiTietMuonSach> ls = chiTietMuonSachDAO.getBooksBorrowed("21100321");
        for (ChiTietMuonSach c: ls) {
            System.out.println(c.toString());
        }
    }
}
