package management.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import management.entity.Ctsize;
import management.entity.CtsizeId;

public class ThanhToanDto {

	private Ctsize CtSize;
	private int soLuong;
	private int donGia;
	private int donGiaKM;

	public ThanhToanDto() {
		super();
	}

	public ThanhToanDto(Ctsize ctSize, int soLuong, int donGia, int donGiaKM) {
		super();
		CtSize = ctSize;
		this.soLuong = soLuong;
		this.donGia = donGia;
		this.donGiaKM = donGiaKM;
	}

	@JsonProperty("ctsizeid")
	public CtsizeId getCtsizeId() {
		return CtSize.getId();
	}

	public void setCtsizeId(CtsizeId ctsizeId) {
	    if (CtSize == null) {
	        CtSize = new Ctsize(); // Tạo một đối tượng Ctsize nếu nó chưa tồn tại
	    }
	    CtSize.setId(ctsizeId); // Gán giá trị ctsizeid cho CtSize
	}
	
	@JsonIgnore
	public Ctsize getCtSize() {
		return CtSize;
	}

	public void setCtSize(Ctsize ctSize) {
		this.CtSize = ctSize;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public int getDonGia() {
		return donGia;
	}

	public void setDonGia(int donGia) {
		this.donGia = donGia;
	}

	public int getDonGiaKM() {
		return donGiaKM;
	}

	public void setDonGiaKM(int donGiaKM) {
		this.donGiaKM = donGiaKM;
	}

	@Override
	public String toString() {
		return "ThanhToanDto [CtSize=" + CtSize + ", soLuong=" + soLuong + ", donGia=" + donGia + ", donGiaKM="
				+ donGiaKM + "]";
	}

}
