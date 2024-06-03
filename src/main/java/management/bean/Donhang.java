package management.bean;

import java.util.Date;

public class Donhang {
	private int mamh;
	private String tenSP;
	private int soluong;
	private Date ngaydat;
	private double tonggia;
	private String size;
	private double mucgiamgia;
	private int danhgia;
	
	public Donhang() {
		
	}
	
	public Donhang(int mamh, String tenSP, int soluong, Date ngaydat, double tonggia, String size, double mucgiamgia, int danhgia) {
		super();
		this.mamh = mamh;
		this.tenSP = tenSP;
		this.soluong = soluong;
		this.ngaydat = ngaydat;
		this.tonggia = tonggia;
		this.size = size;
		this.mucgiamgia = mucgiamgia;
		this.danhgia = danhgia;
	}
	public int getMamh() {
		return mamh;
	}

	public void setMamh(int mamh) {
		this.mamh = mamh;
	}

	public String getTenSP() {
		return tenSP;
	}
	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}
	public int getSoluong() {
		return soluong;
	}
	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
	public Date getNgaydat() {
		return ngaydat;
	}
	public void setNgaydat(Date ngaydat) {
		this.ngaydat = ngaydat;
	}

	public double getTonggia() {
		return tonggia;
	}

	public void setTonggia(double tonggia) {
		this.tonggia = tonggia;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public double getMucgiamgia() {
		return mucgiamgia;
	}

	public void setMucgiamgia(double mucgiamgia) {
		this.mucgiamgia = mucgiamgia;
	}

	public int getDanhgia() {
		return danhgia;
	}

	public void setDanhgia(int danhgia) {
		this.danhgia = danhgia;
	}
}
