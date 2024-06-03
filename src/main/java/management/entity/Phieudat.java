package management.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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

@Entity
@Table(name = "phieudat")
public class Phieudat implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MAPD", unique = true, nullable = false)
	private int mapd;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MAKH", nullable = false)
	private Khachhang khachhang;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "NGAYDAT")
	private Date ngaydat;

	@Column(name = "HOTENNGUOINHAN", nullable = true, columnDefinition = "nvarchar(100)")
	private String hotennguoinhan;

	@Column(name = "DIACHI", nullable = true, columnDefinition = "nvarchar(100)")
	private String diachi;

	@Column(name = "SDT", nullable = true, length = 15)
	private String sdt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "NGAYGIOGIAO")
	private Date ngaygiogiao;

	@Column(name = "TRANGTHAI")
	private int trangthai;

	@OneToOne(mappedBy = "phieudat", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Hoadon hoadon;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "phieudat")
	private Set<Ctpd> ctpds = new HashSet<Ctpd>(0);

	public Phieudat() {
	}

	public Phieudat(int mapd, Khachhang khachhang, Date ngaydat, String hotennguoinhan, String diachi, String sdt,
			Date ngaygiogiao, int trangthai, Hoadon hoadon, Set<Ctpd> ctpds) {
		super();
		this.mapd = mapd;
		this.khachhang = khachhang;
		this.ngaydat = ngaydat;
		this.hotennguoinhan = hotennguoinhan;
		this.diachi = diachi;
		this.sdt = sdt;
		this.ngaygiogiao = ngaygiogiao;
		this.trangthai = trangthai;
		this.hoadon = hoadon;
		this.ctpds = ctpds;
	}

	public Hoadon getHoadon() {
		return hoadon;
	}

	public void setHoadon(Hoadon hoadon) {
		this.hoadon = hoadon;
	}

	public int getMapd() {
		return this.mapd;
	}

	public void setMapd(int mapd) {
		this.mapd = mapd;
	}

	public Khachhang getKhachhang() {
		return this.khachhang;
	}

	public void setKhachhang(Khachhang khachhang) {
		this.khachhang = khachhang;
	}

	public Date getNgaydat() {
		return this.ngaydat;
	}

	public void setNgaydat(Date ngaydat) {
		this.ngaydat = ngaydat;
	}

	public String getHotennguoinhan() {
		return this.hotennguoinhan;
	}

	public void setHotennguoinhan(String hotennguoinhan) {
		this.hotennguoinhan = hotennguoinhan;
	}

	public String getDiachi() {
		return this.diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	public String getSdt() {
		return this.sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public Date getNgaygiogiao() {
		return this.ngaygiogiao;
	}

	public void setNgaygiogiao(Date ngaygiogiao) {
		this.ngaygiogiao = ngaygiogiao;
	}

	public int getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(int trangthai) {
		this.trangthai = trangthai;
	}

	public Set<Ctpd> getCtpds() {
		return this.ctpds;
	}

	public void setCtpds(Set<Ctpd> ctpds) {
		this.ctpds = ctpds;
	}
}
