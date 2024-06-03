package management.bean;

import management.entity.Mathang;

public class ProductWithDiscount {

	private Mathang mathang;
	private int mucgiamgia;
	private int gia;
	private int giamoi;
	

	public Mathang getMathang() {
		return mathang;
	}

	

	public ProductWithDiscount() {
		super();
	}

	public void setMathang(Mathang mathang) {
		this.mathang = mathang;
	}

	
	
	public int getGia() {
		return gia;
	}

	public void setGia(int gia) {
		this.gia = gia;
	}



	public int getMucgiamgia() {
		return mucgiamgia;
	}



	public void setMucgiamgia(int mucgiamgia) {
		this.mucgiamgia = mucgiamgia;
	}



	public int getGiamoi() {
		return gia-(gia*mucgiamgia/100);
	}



	public void setGiamoi(int giamoi) {
		this.giamoi = giamoi;
	}

	

	
	
	
}
