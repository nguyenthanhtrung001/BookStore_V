package management.DTO;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class ProductDTO {

	private String tenSPMoi;
	private String loaiSP;
	private String tacGia;
	private String nhaXuatBan;
	private String namXuatBan;
	private String soTrang;
	private String mauSac;
	private String loaiBut;
	private String loaiTap;
	private String loaiOly;
	private String soTrangTap;
	private String moTa;
	private String nhanHieu;
	private String theLoaiSach;
	private List<MultipartFile> hinhAnh;

	public ProductDTO() {
		super();
	}
	

	public ProductDTO(String tenSPMoi, String loaiSP, String tacGia, String nhaXuatBan, String namXuatBan,
			String soTrang, String mauSac, String loaiBut, String loaiTap, String loaiOly, String soTrangTap,
			String moTa, String nhanHieu, String theLoaiSach, List<MultipartFile> hinhAnh) {
		super();
		this.tenSPMoi = tenSPMoi;
		this.loaiSP = loaiSP;
		this.tacGia = tacGia;
		this.nhaXuatBan = nhaXuatBan;
		this.namXuatBan = namXuatBan;
		this.soTrang = soTrang;
		this.mauSac = mauSac;
		this.loaiBut = loaiBut;
		this.loaiTap = loaiTap;
		this.loaiOly = loaiOly;
		this.soTrangTap = soTrangTap;
		this.moTa = moTa;
		this.nhanHieu = nhanHieu;
		this.theLoaiSach = theLoaiSach;
		this.hinhAnh = hinhAnh;
	}


	public String getLoaiBut() {
		return loaiBut;
	}

	public void setLoaiBut(String loaiBut) {
		this.loaiBut = loaiBut;
	}

	public String getNhanHieu() {
		return nhanHieu;
	}

	public void setNhanHieu(String nhanHieu) {
		this.nhanHieu = nhanHieu;
	}

	public String getTheLoaiSach() {
		return theLoaiSach;
	}

	public void setTheLoaiSach(String theLoaiSach) {
		this.theLoaiSach = theLoaiSach;
	}

	public String getTenSPMoi() {
		return tenSPMoi;
	}

	public void setTenSPMoi(String tenSPMoi) {
		this.tenSPMoi = tenSPMoi;
	}

	public String getLoaiSP() {
		return loaiSP;
	}

	public void setLoaiSP(String loaiSP) {
		this.loaiSP = loaiSP;
	}

	public String getTacGia() {
		return tacGia;
	}

	public void setTacGia(String tacGia) {
		this.tacGia = tacGia;
	}

	public String getNhaXuatBan() {
		return nhaXuatBan;
	}

	public void setNhaXuatBan(String nhaXuatBan) {
		this.nhaXuatBan = nhaXuatBan;
	}

	public String getNamXuatBan() {
		return namXuatBan;
	}

	public void setNamXuatBan(String namXuatBan) {
		this.namXuatBan = namXuatBan;
	}

	public String getSoTrang() {
		return soTrang;
	}

	public void setSoTrang(String soTrang) {
		this.soTrang = soTrang;
	}

	public String getMauSac() {
		return mauSac;
	}

	public void setMauSac(String mauSac) {
		this.mauSac = mauSac;
	}

	public String getloaiBut() {
		return loaiBut;
	}

	public void setloaiBut(String loaiBut) {
		this.loaiBut = loaiBut;
	}

	public String getLoaiTap() {
		return loaiTap;
	}

	public void setLoaiTap(String loaiTap) {
		this.loaiTap = loaiTap;
	}

	public String getLoaiOly() {
		return loaiOly;
	}

	public void setLoaiOly(String loaiOly) {
		this.loaiOly = loaiOly;
	}

	public String getSoTrangTap() {
		return soTrangTap;
	}

	public void setSoTrangTap(String soTrangTap) {
		this.soTrangTap = soTrangTap;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public List<MultipartFile> getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(List<MultipartFile> hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

}
