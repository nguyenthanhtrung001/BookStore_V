package management.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import management.DTO.ProductDTO;

@Entity
@DiscriminatorValue("Stationery")
public class Stationery extends Mathang {
	
    private String soTrangTap;
    private String loaiOly;
    private String loaiTap;
    
    
    
    
	public Stationery() {
		super();
	}
	
	public Stationery(ProductDTO pro) {
		super(pro.getTenSPMoi(), pro.getMoTa(), 1);

		Loaimh loaiSP = new Loaimh();
		Nhanhieu nhanHieu = new Nhanhieu();

		// cần sửa
		loaiSP.setMaloaimh(1);
		nhanHieu.setManh(1);

		super.setLoaimh(loaiSP);
		super.setNhanhieu(nhanHieu);
		
		this.soTrangTap = pro.getSoTrangTap();
		this.loaiOly = pro.getLoaiOly();
		this.loaiTap = pro.getLoaiTap();
	}
	
	
	
	public Stationery(String soTrangTap, String loaiOly, String loaiTap) {
		super();
		this.soTrangTap = soTrangTap;
		this.loaiOly = loaiOly;
		this.loaiTap = loaiTap;
	}

	public String getSoTrangTap() {
		return soTrangTap;
	}
	public void setSoTrangTap(String soTrangTap) {
		this.soTrangTap = soTrangTap;
	}
	public String getLoaiOly() {
		return loaiOly;
	}
	public void setLoaiOly(String loaiOly) {
		this.loaiOly = loaiOly;
	}
	public String getLoaiTap() {
		return loaiTap;
	}
	public void setLoaiTap(String loaiTap) {
		this.loaiTap = loaiTap;
	}
   
    
    
	
    
}
