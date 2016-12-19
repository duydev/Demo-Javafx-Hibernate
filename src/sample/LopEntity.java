package sample;

import javax.persistence.*;

/**
 * Created by DuyDev on 19/12/2016.
 */
@Entity
@Table(name = "Lop", schema = "dbo", catalog = "QLSinhVien")
public class LopEntity {
    private String maLop;
    private String tenLop;
    private Integer siSo;

    @Id
    @Column(name = "MaLop")
    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    @Basic
    @Column(name = "TenLop")
    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    @Basic
    @Column(name = "SiSo")
    public Integer getSiSo() {
        return siSo;
    }

    public void setSiSo(Integer siSo) {
        this.siSo = siSo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LopEntity lopEntity = (LopEntity) o;

        if (maLop != null ? !maLop.equals(lopEntity.maLop) : lopEntity.maLop != null) return false;
        if (tenLop != null ? !tenLop.equals(lopEntity.tenLop) : lopEntity.tenLop != null) return false;
        if (siSo != null ? !siSo.equals(lopEntity.siSo) : lopEntity.siSo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = maLop != null ? maLop.hashCode() : 0;
        result = 31 * result + (tenLop != null ? tenLop.hashCode() : 0);
        result = 31 * result + (siSo != null ? siSo.hashCode() : 0);
        return result;
    }
}
