package Server;

import dao.ChiTietMuonSachDAO;
import dao.GiaoVienDAO;
import dao.SachDAO;
import dao.SinhVienDAO;
import daoImpl.ChiTietMuonSachDAOimpl;
import daoImpl.GiaoVienDAOimpl;
import daoImpl.SachDAOimpl;
import daoImpl.SinhVienDAOimpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {
    private static final String url = "rmi://localhost:1234/";
    public static void main(String[] args) throws RemoteException {

        LocateRegistry.createRegistry(1234);
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mssql");

//        EntityManager em = Persistence.createEntityManagerFactory("mssql").createEntityManager();
//        EntityManagerFactory = e
        try {
            SinhVienDAO sinhVienDao = new SinhVienDAOimpl();
            GiaoVienDAO giaoVienDao = new GiaoVienDAOimpl();
            SachDAO sachDAO = new SachDAOimpl();
            ChiTietMuonSachDAO chiTietMuonSachDAO = new ChiTietMuonSachDAOimpl();

            Naming.bind(url + "sinhvien", sinhVienDao);
            Naming.bind(url + "giaovien", giaoVienDao);
            Naming.bind(url + "sach", sachDAO);
            Naming.bind(url + "chitietmuonsach", chiTietMuonSachDAO);

            System.out.println("Server is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
