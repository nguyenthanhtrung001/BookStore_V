package management.dao.impl;


import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import management.dao.ITaiKhoanDAO;
import management.entity.Khachhang;
import management.entity.Nhanvien;
import management.entity.Quyen;
import management.entity.Taikhoan;

@Repository
@Transactional
public class TaiKhoanImpl implements ITaiKhoanDAO{

	
	@Autowired
	 SessionFactory sessionFactory;
	
	@Override
	public boolean check_MailExist(String mail) {
		Session session = sessionFactory.openSession();
	    String hql = "SELECT COUNT(*) FROM Taikhoan WHERE email = :email";
	    Query query =  session.createQuery(hql);
	    query.setParameter("email", mail);
	    
	    // Sử dụng uniqueResult để lấy số lượng kết quả (1 hoặc 0)
	    Long count = (Long)  query.uniqueResult();
	    
	    session.close();
	    
	 // Kiểm tra kết quả truy vấn
	    if (count != null) {
	        // Nếu count > 0, tức là đã tồn tại tentk trong cơ sở dữ liệu
	        return count > 0;
	    } else {
	        // Trường hợp kết quả truy vấn là null
	        // Bạn có thể xử lý nó theo cách bạn muốn, ví dụ, bạn có thể trả về false hoặc ném một ngoại lệ tùy theo yêu cầu của ứng dụng.
	        return false;
	    }
		
	}



	// Phai lay duoc TaiKhoan tu controller r moi them vao, day chi moi lay duoc email
	@Override
	public Taikhoan addTaiKhoan(HttpServletRequest request) {
       
        HttpSession session = request.getSession();

        Taikhoan taikhoan= new Taikhoan();
        // Lấy email từ session
        String userEmail = (String) session.getAttribute("loggedInUserEmail");
        //taikhoan.setTentk(userEmail);
        taikhoan.setEmail(userEmail);
		
        
        Session ss = sessionFactory.openSession();
	   
	    try {
	        ss.beginTransaction();
	        ss.save(taikhoan); // Lưu đối tượng Employee vào cơ sở dữ liệu
	        ss.getTransaction().commit(); // Xác nhận giao dịch
	       
	       
	        
	        
	    } catch (Exception e) {
	    	
	        if (ss.getTransaction() != null) {
	            ss.getTransaction().rollback(); // Rollback nếu xảy ra lỗi
	        }
	        e.printStackTrace();
	        
	    } finally {
	        if (session != null) {
	            ss.close(); // Đóng session	            
	        }
	        
	    }
	    return taikhoan;	    
	}
	@Override
	public Nhanvien getNhanVien_byEmail(String email)
	{
		Nhanvien nhanvien = new Nhanvien();
		Session s = sessionFactory.openSession();
		String hql = "from Nhanvien nv  where nv.taikhoan.email = :email";
		Query query = s.createQuery(hql);
		try {
			query.setParameter("email", email);
			
			nhanvien = (Nhanvien) query.uniqueResult();
		} finally {
			s.close();
		}
		return nhanvien;
	}



	@Override
	public int get_MaQuyen_by_email(String email) {
		int maquyen = 0;
		Session session= sessionFactory.openSession();
		String hql = "select maquyen from Taikhoan  where email = :email and trangthai = 1";
		Query query=session.createSQLQuery(hql);
		try {
			query.setParameter("email", email);
			
			maquyen = (Integer) query.uniqueResult();
		} finally {
			session.close();
		}
		return maquyen;
	}



	@Override
	public Khachhang get_khachHang_byEmail(String email) {
		Khachhang khachhang = new Khachhang();
		Session s = sessionFactory.openSession();
		String hql = "from Khachhang kh  where kh.taikhoan.email = :email";
		Query query = s.createQuery(hql);
		try {
			query.setParameter("email", email);
			
			khachhang = (Khachhang) query.uniqueResult();
		} finally {
			s.close();
		}
		return khachhang;
	}
	
	
}
