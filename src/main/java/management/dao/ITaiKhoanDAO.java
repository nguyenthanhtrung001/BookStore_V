package management.dao;




import javax.servlet.http.HttpServletRequest;

import management.entity.Khachhang;
import management.entity.Nhanvien;
import management.entity.Taikhoan;


public interface ITaiKhoanDAO {
	boolean check_MailExist(String mail);
	Taikhoan addTaiKhoan( HttpServletRequest request);
	public Nhanvien getNhanVien_byEmail(String nv);
	public int get_MaQuyen_by_email(String email);
	public Khachhang get_khachHang_byEmail(String nv);
}
