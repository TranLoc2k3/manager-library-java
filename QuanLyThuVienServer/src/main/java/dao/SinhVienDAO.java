package dao;

import entity.SinhVien;

import java.rmi.Remote;
import java.util.List;

public interface SinhVienDAO extends Remote {
    public void insert(SinhVien s) throws Exception;
    public void update(SinhVien s) throws Exception;
    public void delete(int maSinhVien) throws Exception;
    public SinhVien getSinhVien(String maSinhVien) throws Exception;
    public List<SinhVien> getAll() throws Exception;
    
}
