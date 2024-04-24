package dao;

import entity.GiaoVien;

import java.rmi.Remote;
import java.util.List;

public interface GiaoVienDAO extends Remote {
    public void insert(GiaoVien g) throws Exception;
    public void update(GiaoVien g) throws Exception;
    public void delete(String maGiaoVien) throws Exception;
    public GiaoVien getGiaoVien(String maGiaoVien) throws Exception;
    public List<GiaoVien> getAll() throws Exception;
}
