package management.bean;

import java.util.List;

import management.entity.Phieudat;
import management.entity.Size;

public class DonhangInfo {
	private Phieudat phieudat;
    private List<Donhang> listDonhangForPhieuDat;
	public DonhangInfo(Phieudat phieudat, List<Donhang> listDonhangForPhieuDat) {
		super();
		this.phieudat = phieudat;
		this.listDonhangForPhieuDat = listDonhangForPhieuDat;
	}
	public DonhangInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Phieudat getPhieudat() {
		return phieudat;
	}
	public void setPhieudat(Phieudat phieudat) {
		this.phieudat = phieudat;
	}
	public List<Donhang> getListDonhangForPhieuDat() {
		return listDonhangForPhieuDat;
	}
	public void setListDonhangForPhieuDat(List<Donhang> listDonhangForPhieuDat) {
		this.listDonhangForPhieuDat = listDonhangForPhieuDat;
	}
}
