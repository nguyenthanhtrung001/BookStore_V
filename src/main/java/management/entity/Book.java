package management.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import management.DTO.ProductDTO;

@Entity
@DiscriminatorValue("Book")
public class Book extends Mathang {
    private String tacgia;
    private String nhaXuatBan;
    private int namXuatBan;
    private int soTrang;
    
  
    
    
   
	public Book(ProductDTO pro) {
		super(pro.getTenSPMoi(),pro.getMoTa(),1);
		
		Loaimh loaiSP= new Loaimh();
    	Nhanhieu nhanHieu= new Nhanhieu();
    	
    
    	loaiSP.setMaloaimh(Integer.parseInt(pro.getTheLoaiSach()));
    	nhanHieu.setManh(Integer.parseInt(pro.getNhanHieu()));
    	
    	
		super.setLoaimh(loaiSP);
		super.setNhanhieu(nhanHieu);
		this.tacgia = pro.getTacGia();
		this.nhaXuatBan = pro.getNhaXuatBan();
		this.namXuatBan = Integer.parseInt(pro.getNamXuatBan());
		this.soTrang = Integer.parseInt(pro.getSoTrang()); ;
	}
	
	
	public Book() {
		super();
	}


	public Book(String tacgia, String nhaXuatBan, int namXuatBan, int soTrang) {
		super();
		this.tacgia = tacgia;
		this.nhaXuatBan = nhaXuatBan;
		this.namXuatBan = namXuatBan;
		this.soTrang = soTrang;
	}


	public String getTacgia() {
		return tacgia;
	}
	public void setTacgia(String tacgia) {
		this.tacgia = tacgia;
	}
	public String getNhaXuatBan() {
		return nhaXuatBan;
	}
	public void setNhaXuatBan(String nhaXuatBan) {
		this.nhaXuatBan = nhaXuatBan;
	}
	public int getNamXuatBan() {
		return namXuatBan;
	}
	public void setNamXuatBan(int namXuatBan) {
		this.namXuatBan = namXuatBan;
	}
	public int getSoTrang() {
		return soTrang;
	}
	public void setSoTrang(int soTrang) {
		this.soTrang = soTrang;
	}
    

    
}
