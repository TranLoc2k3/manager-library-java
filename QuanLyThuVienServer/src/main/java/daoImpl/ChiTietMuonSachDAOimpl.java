package daoImpl;

import entity.ChiTietMuonSach;
import entity.Sach;
import entity.SinhVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.math.BigInteger;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedHashMap;
import java.util.List;

public class ChiTietMuonSachDAOimpl extends UnicastRemoteObject implements dao.ChiTietMuonSachDAO {
    EntityManager em;

    public ChiTietMuonSachDAOimpl() throws Exception {
        em = jakarta.persistence.Persistence.createEntityManagerFactory("mssql")
                .createEntityManager();
    }

    @Override
    public void insert(ChiTietMuonSach c) {
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
    }

    @Override
    public void update(ChiTietMuonSach c) {
        em.getTransaction().begin();
        em.merge(c);
        em.getTransaction().commit();
    }

    @Override
    public void delete(int maChiTietMuonSach) {
        em.getTransaction().begin();
        ChiTietMuonSach c = em.find(ChiTietMuonSach.class, maChiTietMuonSach);
        em.remove(c);
        em.getTransaction().commit();
    }

    @Override
    public ChiTietMuonSach getChiTietMuonSach(int maChiTietMuonSach) {
        return em.find(ChiTietMuonSach.class, maChiTietMuonSach);
    }

    @Override
    public List<ChiTietMuonSach> getAll() {
        return em.createNamedQuery("ChiTietMuonSach.findAll", ChiTietMuonSach.class).getResultList();
    }

    @Override
    public List<ChiTietMuonSach> findByTraSachFalse() throws Exception {
        return em.createNamedQuery("ChiTietMuonSach.findByTraSachFalse", ChiTietMuonSach.class)
                .getResultList();
    }

    @Override
    public List<ChiTietMuonSach> findByTraSachFalseAndSinhVien_MaSinhVienLike(String maSinhVien) throws Exception {
        return em.createNamedQuery("ChiTietMuonSach.findByTraSachFalseAndSinhVien_MaSinhVienLike", ChiTietMuonSach.class)
                .setParameter("maSinhVien", maSinhVien)
                .getResultList();
    }

    @Override
    public LinkedHashMap<Sach, Integer> getNumberBorrowedBooks(int day) throws Exception {
        List<Object[]> ls = em.createNativeQuery("SELECT maSach, COUNT(*)\n" +
                        "FROM chi_tiet_muon_sach\n" +
                        "WHERE DATEDIFF(day, ngayMuon, GETDATE()) <= :soNgay\n" +
                        "GROUP BY maSach\n" +
                        "ORDER BY COUNT(*) DESC\n"
                ).setParameter("soNgay", day)
                .getResultList();
        LinkedHashMap<Sach, Integer> map = new LinkedHashMap<>();
        for (Object[] result : ls) {
            int maSach = (Integer) result[0];
            int count = (Integer) result[1];
            Sach sach = em.find(Sach.class, maSach);
            map.put(sach, count);
        }
        return map;
    }

    @Override
    public LinkedHashMap<SinhVien, Integer> getNumberBorrowedBooksByStudent(int day) throws Exception {
        List<Object[]> ls = em.createNativeQuery("SELECT maSinhVien, COUNT(*)\n" +
                        "FROM chi_tiet_muon_sach\n" +
                        "WHERE DATEDIFF(day, ngayMuon, GETDATE()) <= :soNgay\n" +
                        "GROUP BY maSinhVien\n" +
                        "ORDER BY COUNT(*) DESC\n"
                ).setParameter("soNgay", day)
                .getResultList();
        LinkedHashMap<SinhVien, Integer> map = new LinkedHashMap<>();
        for (Object[] result : ls) {
            String maSinhVien = (String) result[0];
            int count = (Integer) result[1];
            SinhVien sinhVien = em.find(SinhVien.class, maSinhVien);
            map.put(sinhVien, count);
        }
        return map;
    }

    @Override
    public List<ChiTietMuonSach> getBooksBorrowed(String maSinhVien) throws Exception {
        return em.createNamedQuery("ChiTietMuonSach.findBySinhVien_MaSinhVienLikeAndTraSachFalse", ChiTietMuonSach.class)
                .setParameter("maSinhVien", maSinhVien)
                .getResultList();
    }
}
