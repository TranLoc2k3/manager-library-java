package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "sinh_vien")
@NamedQueries({
        @NamedQuery(name = "SinhVien.findAll", query = "select s from SinhVien s")
})
public class SinhVien implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "maSinhVien", nullable = false)
    private String maSinhVien;
    @Column(columnDefinition = "nvarchar(55)")
    private String tenSinhVien;
    private String password;

    public SinhVien(String maSinhVien, String tenSinhVien, String password) {
        this.maSinhVien = maSinhVien;
        this.tenSinhVien = tenSinhVien;
        this.password = password;
    }

    public SinhVien() {
    }

    public String getMaSinhVien() {
        return maSinhVien;
    }

    public void setMaSinhVien(String maSinhVien) {
        this.maSinhVien = maSinhVien;
    }

    public String getTenSinhVien() {
        return tenSinhVien;
    }

    public void setTenSinhVien(String tenSinhVien) {
        this.tenSinhVien = tenSinhVien;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "SinhVien{" +
                "maSinhVien='" + maSinhVien + '\'' +
                ", tenSinhVien='" + tenSinhVien + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}