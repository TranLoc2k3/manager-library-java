package entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "chi_tiet_muon_sach")
@NamedQueries({
        @NamedQuery(name = "ChiTietMuonSach.findAll", query = "select c from ChiTietMuonSach c"),
        @NamedQuery(name = "ChiTietMuonSach.findByTraSachFalse", query = "select c from ChiTietMuonSach c where c.traSach = false order by c.ngayMuon desc, c.sinhVien.maSinhVien"),
        @NamedQuery(name = "ChiTietMuonSach.findByTraSachFalseAndSinhVien_MaSinhVienLike", query = "select c from ChiTietMuonSach c where c.traSach = false and c.sinhVien.maSinhVien like :maSinhVien"),
        @NamedQuery(name = "ChiTietMuonSach.findBySinhVien_MaSinhVienLikeAndTraSachFalse", query = "select c from ChiTietMuonSach c where c.sinhVien.maSinhVien like :maSinhVien and c.traSach = false")
})
public class ChiTietMuonSach implements  java.io.Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_muon_sach", nullable = false)
    private int maMuonSach;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "maSach")
    private Sach sach;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "maSinhVien")
    private SinhVien sinhVien;

    private Date ngayMuon;

    private Date ngayTra;

    private boolean traSach;

    private int soLuong;

    public ChiTietMuonSach(int maMuonSach, Sach sach, SinhVien sinhVien, Date ngayMuon, Date ngayTra, boolean traSach, int soLuong) {
        this.maMuonSach = maMuonSach;
        this.sach = sach;
        this.sinhVien = sinhVien;
        this.ngayMuon = ngayMuon;
        this.ngayTra = ngayTra;
        this.traSach = traSach;
        this.soLuong = soLuong;
    }

    public ChiTietMuonSach() {
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getMaMuonSach() {
        return maMuonSach;
    }

    public void setMaMuonSach(int maMuonSach) {
        this.maMuonSach = maMuonSach;
    }

    public Sach getSach() {
        return sach;
    }

    public void setSach(Sach sach) {
        this.sach = sach;
    }

    public SinhVien getSinhVien() {
        return sinhVien;
    }

    public void setSinhVien(SinhVien sinhVien) {
        this.sinhVien = sinhVien;
    }

    public Date getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(Date ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public Date getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
    }

    public boolean isTraSach() {
        return traSach;
    }

    public void setTraSach(boolean traSach) {
        this.traSach = traSach;
    }

    @Override
    public String toString() {
        return "ChiTietMuonSach{" +
                "maMuonSach='" + maMuonSach + '\'' +
                ", sach=" + sach.getMaSach() +
                ", sinhVien=" + sinhVien.getMaSinhVien() +
                ", ngayMuon=" + ngayMuon +
                ", ngayTra=" + ngayTra +
                ", traSach=" + traSach +
                '}';
    }
}