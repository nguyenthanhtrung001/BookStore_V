package management.dao;

import javax.servlet.http.HttpServletRequest;

import management.entity.Khachhang;
import management.entity.Taikhoan;

public interface IKhachHangDAO {
	
	void createCustomer(Khachhang khachhang,Taikhoan taikhoan,HttpServletRequest request);
	void setTaiKhoanDAO(ITaiKhoanDAO iTaiKhoanDAO);
}
