package management.dao;

import java.util.List;

import management.entity.Ctpd;
import management.entity.Mathang;
import management.entity.Phieudat;



public interface IAprioriDao {
	public List<Mathang> getLayDSSPDAMUA(int makh);
	public List<Mathang> getLayDSSP();
	public List<Phieudat> getLayDSHD(int makh);
	public List<Ctpd> getLayDSCTHD(int makh);
	public double  getLAYDANHGIA(int mamh);
	public Mathang  getMHById(int mamh);
	
	
}



