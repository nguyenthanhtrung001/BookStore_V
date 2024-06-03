package management.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "nhanvien", uniqueConstraints = {
        @UniqueConstraint(columnNames = "EMAIL"),
        @UniqueConstraint(columnNames = "SDT") })
public class Nhanvien implements java.io.Serializable {

    @Id
    @Column(name = "MANV", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int manv;

    @Column(name = "TENNV", nullable = false, columnDefinition = "nvarchar(100)")
    private String tennv;

    @Column(name = "GIOITINH")
    private Boolean gioitinh;

    @Temporal(TemporalType.DATE)
    @Column(name = "NGAYSINH", nullable = false, length = 10)
    private Date ngaysinh;

    @Column(name = "DIACHI", nullable = false,columnDefinition = "nvarchar(200)")
    private String diachi;

    @Column(name = "SDT", unique = true, nullable = false, length = 15)
    private String sdt;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EMAIL", referencedColumnName = "EMAIL", nullable = false)
    private Taikhoan taikhoan;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "nhanvien")
    private Set<Hoadon> cthds = new HashSet<Hoadon>(0);
   

    public Nhanvien() {
    }


	public Nhanvien(int manv, String tennv, Boolean gioitinh, Date ngaysinh, String diachi, String sdt,
			Taikhoan taikhoan, Set<Hoadon> cthds) {
		super();
		this.manv = manv;
		this.tennv = tennv;
		this.gioitinh = gioitinh;
		this.ngaysinh = ngaysinh;
		this.diachi = diachi;
		this.sdt = sdt;
		this.taikhoan = taikhoan;
		this.cthds = cthds;
	}


	public int getManv() {
		return manv;
	}


	public void setManv(int manv) {
		this.manv = manv;
	}


	public String getTennv() {
		return tennv;
	}


	public void setTennv(String tennv) {
		this.tennv = tennv;
	}


	public Boolean getGioitinh() {
		return gioitinh;
	}


	public void setGioitinh(Boolean gioitinh) {
		this.gioitinh = gioitinh;
	}


	public Date getNgaysinh() {
		return ngaysinh;
	}


	public void setNgaysinh(Date ngaysinh) {
		this.ngaysinh = ngaysinh;
	}


	public String getDiachi() {
		return diachi;
	}


	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}


	public String getSdt() {
		return sdt;
	}


	public void setSdt(String sdt) {
		this.sdt = sdt;
	}


	public Taikhoan getTaikhoan() {
		return taikhoan;
	}


	public void setTaikhoan(Taikhoan taikhoan) {
		this.taikhoan = taikhoan;
	}


	public Set<Hoadon> getCthds() {
		return cthds;
	}


	public void setCthds(Set<Hoadon> cthds) {
		this.cthds = cthds;
	}


    
  
}

