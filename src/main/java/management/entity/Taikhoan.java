package management.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "taikhoan")
public class Taikhoan implements java.io.Serializable {


	@Id
	@Column(name = "EMAIL", unique = true, nullable = false, length = 500)
	private String email;

	@OneToOne(fetch = FetchType.EAGER, mappedBy = "taikhoan")
	private Khachhang khachhang;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MAQUYEN")
	private Quyen quyen;

	@Column(name = "MATKHAU")
	private String matkhau;
	@Column(name = "TRANGTHAI")
	private int trangthai;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "taikhoan")
	private Set<Danhgia> danhgias = new HashSet<Danhgia>(0);

	@OneToOne(fetch = FetchType.EAGER, mappedBy = "taikhoan")
	private Nhanvien nhanvien;

	public Taikhoan() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Nhanvien getNhanvien() {
		return nhanvien;
	}

	public void setNhanvien(Nhanvien nhanvien) {
		this.nhanvien = nhanvien;
	}

	public Khachhang getKhachhang() {
		return this.khachhang;
	}

	public void setKhachhang(Khachhang khachhang) {
		this.khachhang = khachhang;
	}

	public Quyen getQuyen() {
		return this.quyen;
	}

	public void setQuyen(Quyen quyen) {
		this.quyen = quyen;
	}

	public String getMatkhau() {
		return this.matkhau;
	}

	public void setMatkhau(String matkhau) {
		this.matkhau = matkhau;
	}

	public Set<Danhgia> getDanhgias() {
		return this.danhgias;
	}

	public void setDanhgias(Set<Danhgia> danhgias) {
		this.danhgias = danhgias;
	}

	public int getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(int trangthai) {
		this.trangthai = trangthai;
	}
	
}

