package daoImpl;

import entity.SinhVien;
import jakarta.persistence.EntityManager;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class SinhVienDAOimpl extends UnicastRemoteObject implements dao.SinhVienDAO {
    EntityManager em;

    public SinhVienDAOimpl() throws RemoteException {
        em = jakarta.persistence.Persistence.createEntityManagerFactory("mssql")
                .createEntityManager();
    }

    @Override
    public void insert(SinhVien s) {
        em.getTransaction().begin();
        em.persist(s);
        em.getTransaction().commit();
    }

    @Override
    public void update(SinhVien s) {
        em.getTransaction().begin();
        em.merge(s);
        em.getTransaction().commit();
    }

    @Override
    public void delete(int maSinhVien) {
        em.getTransaction().begin();
        SinhVien s = em.find(SinhVien.class, maSinhVien);
        em.remove(s);
        em.getTransaction().commit();
    }

    @Override
    public SinhVien getSinhVien(String maSinhVien) {
        return em.find(SinhVien.class, maSinhVien);
    }

    @Override
    public List<SinhVien> getAll() {
        return em.createNamedQuery("SinhVien.findAll", SinhVien.class).getResultList();
    }
}
