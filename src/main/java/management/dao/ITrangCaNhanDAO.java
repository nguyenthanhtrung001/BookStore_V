package management.dao;

import management.entity.Khachhang;

public interface ITrangCaNhanDAO {
	Khachhang getKhachhangByEmail(String id);
	
	Integer updateKhachhang(Khachhang kh);
}
