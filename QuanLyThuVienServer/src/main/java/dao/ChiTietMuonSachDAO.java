package dao;

import entity.ChiTietMuonSach;
import entity.Sach;
import entity.SinhVien;

import java.rmi.Remote;
import java.util.LinkedHashMap;
import java.util.List;

public interface ChiTietMuonSachDAO extends Remote {
    public void insert(entity.ChiTietMuonSach c) throws Exception;
    public void update(entity.ChiTietMuonSach c) throws Exception;
    public void delete(int maChiTietMuonSach) throws Exception;
    public entity.ChiTietMuonSach getChiTietMuonSach(int maChiTietMuonSach) throws Exception;
    public java.util.List<entity.ChiTietMuonSach> getAll() throws Exception;
    public List<ChiTietMuonSach> findByTraSachFalse() throws Exception;
    public List<ChiTietMuonSach> findByTraSachFalseAndSinhVien_MaSinhVienLike(String maSinhVien) throws Exception;
    public LinkedHashMap<Sach, Integer> getNumberBorrowedBooks(int day) throws Exception;
    public LinkedHashMap<SinhVien, Integer> getNumberBorrowedBooksByStudent(int day) throws Exception;
    public List<ChiTietMuonSach> getBooksBorrowed(String maSinhVien) throws Exception;
}
