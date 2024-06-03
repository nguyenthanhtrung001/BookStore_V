package management.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import management.DTO.KhuyenMaiDto;
import management.entity.Ctdkm;
import management.entity.Dotkhuyenmai;
import management.entity.Mathang;


public interface IKhuyenMaiDAO {
	public List<Dotkhuyenmai> getAllKM();
	public Integer insertPromotion(Dotkhuyenmai km);
	public List<Mathang> get_All_MatHang();
	public boolean Update_KhuyenMai(int idsp,int makm,Double muckm);
	public boolean add_CT_KhuyenMai(int idsp,int makm,Double muckm);
	public List<KhuyenMaiDto> get_CTDKM_By_MaKM(int makm);
	public Dotkhuyenmai get_DKM_By_Id(int id);
	public List<Mathang> get_List_MatHang_by_MaKM(int madkm);
}
