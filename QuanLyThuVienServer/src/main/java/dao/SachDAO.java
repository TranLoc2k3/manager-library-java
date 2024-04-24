package dao;

import entity.Sach;

import java.rmi.Remote;
import java.util.List;

public interface SachDAO extends Remote {
    public void insert(Sach s) throws Exception;
    public void update(Sach s) throws Exception;
    public void delete(int maSach) throws Exception;
    public Sach getSach(int maSach) throws Exception;
    public List<Sach> getAll() throws Exception;
    public List<Sach> getSachByTenSach(String tenSach) throws Exception;
    public List<Sach> getSachByTacGia(String tacGia) throws Exception;
    public List<Sach> getSachByTheLoai(String theLoai) throws Exception;
}
