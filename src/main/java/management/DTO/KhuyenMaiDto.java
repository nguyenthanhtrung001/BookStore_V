package management.DTO;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import management.dao.IKhuyenMaiDAO;


public class KhuyenMaiDto {
	@Autowired
	IKhuyenMaiDAO iKhuyenMaiDAO;

	private int matHang;
	private String tenSP;
	private int mucGiamGia;
	private int madkm;
	public int getMatHang() {
		return matHang;
	}
	public void setMatHang(int matHang) {
		this.matHang = matHang;
	}
	public String getTenSP() {
		return tenSP;
	}
	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}
	public int getMucGiamGia() {
		return mucGiamGia;
	}
	public void setMucGiamGia(int mucGiamGia) {
		this.mucGiamGia = mucGiamGia;
	}
	public int getMadkm() {
		return madkm;
	}
	public void setMadkm(int madkm) {
		this.madkm = madkm;
	}
	public KhuyenMaiDto(int matHang, String tenSP, int mucGiamGia, int madkm) {
		super();
		this.matHang = matHang;
		this.tenSP = tenSP;
		this.mucGiamGia = mucGiamGia;
		this.madkm = madkm;
	}
	public KhuyenMaiDto() {
		super();
	}
	
	
	
	
	
	
}
