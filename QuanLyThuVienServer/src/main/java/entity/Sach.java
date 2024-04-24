package entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "Sach")
@NamedQueries({
        @NamedQuery(name = "Sach.findAll", query = "select s from Sach s"),
        @NamedQuery(name = "Sach.findByTenSachLike", query = "select s from Sach s where s.tenSach like CONCAT('%', :tenSach, '%')"),
        @NamedQuery(name = "Sach.findByTacGiaLike", query = "select s from Sach s where s.tacGia like CONCAT('%', :tacGia, '%')"),
        @NamedQuery(name = "Sach.findByTheLoaiLike", query = "select s from Sach s where s.theloai like CONCAT('%', :theLoai, '%')")

})
public class Sach implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "MaSach", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maSach;

    @Column(columnDefinition = "nvarchar(55)")
    private String tenSach;

    @Column(columnDefinition = "nvarchar(55)")
    private String tacGia;

    @Column(columnDefinition = "nvarchar(55)")
    private String theloai;
    private int soLuong;


    public Sach(String tenSach, String tacGia, String theloai, int soLuong) {
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.theloai = theloai;
        this.soLuong = soLuong;
    }

    public Sach() {
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getTheloai() {
        return theloai;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    @Override
    public String toString() {
        return "Sach{" +
                "maSach='" + maSach + '\'' +
                ", tenSach='" + tenSach + '\'' +
                ", tacGia='" + tacGia + '\'' +
                ", theloai='" + theloai + '\'' +
                ", soLuong=" + soLuong +
                '}';
    }

    // Script insert data Sach in sql sever

}