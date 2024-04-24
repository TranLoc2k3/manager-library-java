package daoImpl;

import entity.GiaoVien;
import jakarta.persistence.EntityManager;

import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class GiaoVienDAOimpl extends UnicastRemoteObject implements dao.GiaoVienDAO{
    EntityManager em;
    public GiaoVienDAOimpl() throws Exception{
        em = jakarta.persistence.Persistence.createEntityManagerFactory("mssql")
                .createEntityManager();
    }

    @Override
    public void insert(GiaoVien g) {
        em.getTransaction().begin();
        em.persist(g);
        em.getTransaction().commit();
    }

    @Override
    public void update(GiaoVien g) {
        em.getTransaction().begin();
        em.merge(g);
        em.getTransaction().commit();
    }

    @Override
    public void delete(String maGiaoVien) {
        em.getTransaction().begin();
        GiaoVien g = em.find(GiaoVien.class, maGiaoVien);
        em.remove(g);
        em.getTransaction().commit();
    }

    @Override
    public GiaoVien getGiaoVien(String maGiaoVien) {
        return em.find(GiaoVien.class, maGiaoVien);
    }

    @Override
    public List<GiaoVien> getAll() {
        return em.createNamedQuery("GiaoVien.findAll", GiaoVien.class).getResultList();
    }
}
