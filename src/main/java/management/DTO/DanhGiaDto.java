package management.DTO;

public class DanhGiaDto {
	private int mamh;
	private int danhgia;
		
	public DanhGiaDto() {
		super();
	}

	public DanhGiaDto(int mamh, int danhgia) {
		super();
		this.mamh = mamh;
		this.danhgia = danhgia;
	}

	public int getMamh() {
		return mamh;
	}

	public void setMamh(int mamh) {
		this.mamh = mamh;
	}

	public int getDanhgia() {
		return danhgia;
	}

	public void setDanhgia(int danhgia) {
		this.danhgia = danhgia;
	}
}
