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
@Table(name = "hoadon", uniqueConstraints = @UniqueConstraint(columnNames = "MAPD"))
public class Hoadon implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SOHD", unique = true, nullable = false)
    private int sohd;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MANV", nullable = false)
    private Nhanvien nhanvien;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MAPD", referencedColumnName = "MAPD")
    private Phieudat phieudat;

    @Temporal(TemporalType.DATE)
    @Column(name = "NGAYIN", length = 10)
    private Date ngayin;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hoadon")
    private Set<Cthd> cthds = new HashSet<Cthd>(0);

    public Hoadon() {
    }

	public Hoadon(int sohd, Nhanvien nhanvien, Phieudat phieudat, Date ngayin, Set<Cthd> cthds) {
		super();
		this.sohd = sohd;
		this.nhanvien = nhanvien;
		this.phieudat = phieudat;
		this.ngayin = ngayin;
		this.cthds = cthds;
	}

	public int getSohd() {
		return sohd;
	}

	public void setSohd(int sohd) {
		this.sohd = sohd;
	}

	public Nhanvien getNhanvien() {
		return nhanvien;
	}

	public void setNhanvien(Nhanvien nhanvien) {
		this.nhanvien = nhanvien;
	}

	public Phieudat getPhieudat() {
		return phieudat;
	}

	public void setPhieudat(Phieudat phieudat) {
		this.phieudat = phieudat;
	}

	public Date getNgayin() {
		return ngayin;
	}

	public void setNgayin(Date ngayin) {
		this.ngayin = ngayin;
	}

	public Set<Cthd> getCthds() {
		return cthds;
	}

	public void setCthds(Set<Cthd> cthds) {
		this.cthds = cthds;
	}

   
    
    
    
}
