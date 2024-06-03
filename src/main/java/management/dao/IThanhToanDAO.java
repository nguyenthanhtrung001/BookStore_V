package management.dao;

import java.util.List;

import management.entity.Ctpd;
import management.entity.Ctsize;
import management.entity.CtsizeId;
import management.entity.Khachhang;
import management.entity.Phieudat;

public interface IThanhToanDAO {

	public Ctsize layCtSize(int mamh, String size) ;
	public Ctsize layCtSize(CtsizeId id) ;
	public void themPhieuDat(Phieudat pd) ;
	public Khachhang layKhachHangTheoGmail(String gmail);
	public Khachhang layKhachHangTheoId(int id);
	public void themCtpd(List<Ctpd> ctpds);
	public Phieudat layPhieudatTheoId(int id);
	public void capNhatSLSP(Ctsize ctsize, int slMua);
}
