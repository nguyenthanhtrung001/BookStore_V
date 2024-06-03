package management.dao;

import java.util.List;

import management.entity.Chatlieu;
import management.entity.Ctpn;
import management.entity.Ctsize;
import management.entity.Hinhanhmh;
import management.entity.Loaimh;
import management.entity.Mathang;
import management.entity.Nhacungcap;
import management.entity.Nhanhieu;
import management.entity.Nhanvien;
import management.entity.Phieunhap;
import management.entity.Size;

public interface INhapHangDao{
	
	public List<Mathang> layDSMatHang();
	
	public Mathang layMatHang(int mamh) ;
	
	public Size laySize(int masize) ;
	
	public Ctsize layCtsize(int mamh, int masize);
	
	public List<Size> layDSSize();
	
	public List<Nhanhieu> layDSNhanHieu();
	
	public List<Chatlieu> layDSChatlieu();
	
	public List<Loaimh> layDSLoaiSP();
	
	public void themCtSize(int manh,int masize) ;

	public List<Nhacungcap> layDSNhaCungCap();
	
	public Nhacungcap layNCC(int maNCC);
	
	public void themPhieuNhap(Phieunhap pn, List<Ctpn> ctpns);
	
	public Nhanvien layNhanVien(int manv);
	
	public void themPhieuNhap(Phieunhap pn);
	
	public void themSLTuPN(Ctsize ctsize, int sl);
	
	public void themCtpn(List<Ctpn> ctpns);
	
	public void themSPMoi(Mathang mh) ;
	
	public void themSizeMoi(Size s) ;
	
	public void themAnhMoi(Hinhanhmh anh) ;
	
	public String layTenNhanHieu(int manh) ;
	
	public String layTenChatLieu(int macl) ;
	
	public Nhanvien layNhanVien(String email) ;

	
}
