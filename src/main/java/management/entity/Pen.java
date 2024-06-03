package management.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import management.DTO.ProductDTO;

@Entity
@DiscriminatorValue("Pen")
public class Pen extends Mathang {
	
    private String mauSac;
    
    private String loaiBut;
    
    
	public Pen() {
		super();
	}
	
	public Pen(ProductDTO pro) {
		
		super(pro.getTenSPMoi(),pro.getMoTa(),1);
		Loaimh loaiSP= new Loaimh();
    	Nhanhieu nhanHieu= new Nhanhieu();
    	
    	// cần sửa
    	loaiSP.setMaloaimh(1);
    	nhanHieu.setManh(1);
    	
    	super.setLoaimh(loaiSP);
		super.setNhanhieu(nhanHieu);
		
		this.mauSac = pro.getMauSac();
		this.loaiBut = pro.getloaiBut();
	}
	
	
	public Pen(String mauSac, String loaiBut) {
		super();
		this.mauSac = mauSac;
		this.loaiBut = loaiBut;
	}

	public String getMauSac() {
		return mauSac;
	}
	public void setMauSac(String mauSac) {
		this.mauSac = mauSac;
	}

	public String getLoaiBut() {
		return loaiBut;
	}
	public void setLoaiBut(String loaiBut) {
		this.loaiBut = loaiBut;
	}

    
}
