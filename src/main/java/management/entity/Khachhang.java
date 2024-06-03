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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
    name = "khachhang",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "EMAIL"),
        
        @UniqueConstraint(columnNames = "SDT")
       
    }
)
public class Khachhang implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MAKH", unique = true, nullable = false)
    private int makh;

    @Column(name = "HOTENKH", nullable = true, columnDefinition = "nvarchar(100)")
    private String hotenkh;

   

    @Column(name = "GIOITINH")
    private Boolean gioitinh;

    @Temporal(TemporalType.DATE)
    @Column(name = "NGAYSINH", nullable = true, length = 10)
    private Date ngaysinh;

    @Column(name = "DIACHI", nullable = true, columnDefinition = "nvarchar(100)")
    private String diachi;

    @Column(name = "SDT", unique = true, nullable = true, length = 15)
    private String sdt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "khachhang")
    private Set<Phieudat> phieudats = new HashSet<Phieudat>(0);

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EMAIL", referencedColumnName = "EMAIL", nullable = false)
    private Taikhoan taikhoan;

    public Khachhang() {
    }

    
    
	public Khachhang(int makh, String hotenkh, Boolean gioitinh, Date ngaysinh, String diachi, String sdt,
			Set<Phieudat> phieudats, Taikhoan taikhoan) {
		super();
		this.makh = makh;
		this.hotenkh = hotenkh;
		this.gioitinh = gioitinh;
		this.ngaysinh = ngaysinh;
		this.diachi = diachi;
		this.sdt = sdt;
		this.phieudats = phieudats;
		this.taikhoan = taikhoan;
	}



	public int getMakh() {
		return makh;
	}

	public void setMakh(int makh) {
		this.makh = makh;
	}

	public String getHotenkh() {
		return hotenkh;
	}

	public void setHotenkh(String hotenkh) {
		this.hotenkh = hotenkh;
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


	public Set<Phieudat> getPhieudats() {
		return phieudats;
	}

	public void setPhieudats(Set<Phieudat> phieudats) {
		this.phieudats = phieudats;
	}

	public Taikhoan getTaikhoan() {
		return taikhoan;
	}

	public void setTaikhoan(Taikhoan taikhoan) {
		this.taikhoan = taikhoan;
	}



}
