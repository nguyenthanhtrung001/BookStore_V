package management.dao.impl;

import java.net.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import management.dao.IKhachHangDAO;
import management.dao.ITaiKhoanDAO;
import management.entity.Khachhang;
import management.entity.Quyen;
import management.entity.Taikhoan;

@Repository
@Transactional
public class KhachHangimpl implements IKhachHangDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private ITaiKhoanDAO iTaiKhoanDAO;

	@Override
	public void setTaiKhoanDAO(ITaiKhoanDAO iTaiKhoanDAO) {
		this.iTaiKhoanDAO = iTaiKhoanDAO;
	}

	

	@Override
	public void createCustomer(Khachhang khachhang, Taikhoan taikhoan,HttpServletRequest request) {
		
		Quyen quyen =  new Quyen(1, "Khach hang");
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			HttpSession s = request.getSession();
			String userEmail = (String) s.getAttribute("loggedInUserEmail");
			//taikhoan.setTentk(userEmail);
			taikhoan.setTrangthai(1);
			taikhoan.setQuyen(quyen);
			taikhoan.setEmail(userEmail);
			
			khachhang.setTaikhoan(taikhoan);
			session.save(taikhoan);
			session.save(khachhang);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

}
