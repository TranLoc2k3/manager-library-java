package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "giao_vien")
@NamedQueries({
        @NamedQuery(name = "GiaoVien.findAll", query = "select g from GiaoVien g"),
        @NamedQuery(name = "GiaoVien.findByMaGiaoVien", query = "select g from GiaoVien g where g.maGiaoVien = :maGiaoVien")
})
public class GiaoVien implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "maGiaoVien", nullable = false)
    private String maGiaoVien;

    @Column(columnDefinition = "nvarchar(55)")
    private String tenGiaoVien;
    private String password;

    public GiaoVien(String maGiaoVien, String tenGiaoVien, String password) {
        this.maGiaoVien = maGiaoVien;
        this.tenGiaoVien = tenGiaoVien;
        this.password = password;
    }

    public GiaoVien() {
    }

    public String getMaGiaoVien() {
        return maGiaoVien;
    }

    public void setMaGiaoVien(String maGiaoVien) {
        this.maGiaoVien = maGiaoVien;
    }

    public String getTenGiaoVien() {
        return tenGiaoVien;
    }

    public void setTenGiaoVien(String tenGiaoVien) {
        this.tenGiaoVien = tenGiaoVien;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "GiaoVien{" +
                "maGiaoVien=" + maGiaoVien +
                ", tenGiaoVien='" + tenGiaoVien + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}