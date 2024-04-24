package daoImpl;

import entity.Sach;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class SachDAOimpl extends UnicastRemoteObject implements dao.SachDAO {
    EntityManager em;


    public SachDAOimpl() throws RemoteException {
        em = jakarta.persistence.Persistence.createEntityManagerFactory("mssql")
                .createEntityManager();
    }



    @Override
    public void insert(Sach s) {
        em.getTransaction().begin();
        em.persist(s);
        em.getTransaction().commit();
    }

    @Override
    public void update(Sach s) {
        em.getTransaction().begin();
        em.merge(s);
        em.getTransaction().commit();
    }

    @Override
    public void delete(int maSach) {
        em.getTransaction().begin();
        Sach s = em.find(Sach.class, maSach);
        em.remove(s);
        em.getTransaction().commit();
    }

    @Override
    public Sach getSach(int maSach) {
        return em.find(Sach.class, maSach);
    }

    @Override
    public List<Sach> getAll() {
        return em.createNamedQuery("Sach.findAll", Sach.class).getResultList();
    }

    @Override
    public List<Sach> getSachByTenSach(String tenSach) throws Exception {
        return em.createNamedQuery("Sach.findByTenSachLike", Sach.class)
                .setParameter("tenSach", tenSach)
                .getResultList();
    }

    @Override
    public List<Sach> getSachByTacGia(String tacGia) throws Exception {
        return em.createNamedQuery("Sach.findByTacGiaLike", Sach.class)
                .setParameter("tacGia", tacGia)
                .getResultList();
    }

    @Override
    public List<Sach> getSachByTheLoai(String theLoai) throws Exception {
        return em.createNamedQuery("Sach.findByTheLoaiLike", Sach.class)
                .setParameter("theLoai", theLoai)
                .getResultList();
    }
}
