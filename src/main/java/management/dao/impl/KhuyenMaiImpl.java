package management.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import management.DTO.KhuyenMaiDto;
import management.dao.IGioHangDAO;
import management.dao.IKhuyenMaiDAO;
import management.entity.Ctdkm;
import management.entity.Dotkhuyenmai;
import management.entity.Mathang;
@Transactional
@Repository
public class KhuyenMaiImpl implements IKhuyenMaiDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	IKhuyenMaiDAO iKhuyenMaiDAO;
	
	@Autowired
	IGioHangDAO iGioHangDAO;
	
	@Override
	public Integer insertPromotion(Dotkhuyenmai km) {
		
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();

		try {
			session.save(km);
			
			t.commit();
		} catch (Exception e) {
			e.printStackTrace();
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}
	
	@Override
	public List<Dotkhuyenmai> getAllKM() {
		Session session = sessionFactory.openSession();
		String hql = "FROM Dotkhuyenmai WHERE ngaykt>=:currentDate ";
	    Query query = session.createQuery(hql);
	    query.setParameter("currentDate", new Date());
		List<Dotkhuyenmai> list = query.list();
		return list;
	}
	@Override
	public List<Mathang> get_All_MatHang() {
		Session session = sessionFactory.openSession();
		String hql = "FROM Mathang ";
	    Query query = session.createQuery(hql);
	    
		List<Mathang> list = query.list();
		return list;
	}

	@Override
	public boolean Update_KhuyenMai(int idsp, int makm, Double muckm) {
	    Session session = sessionFactory.openSession();
	    Transaction t = session.beginTransaction();
	    boolean updated = false; // Biến này để kiểm tra xem việc cập nhật đã thành công hay không
	    
	    try {
	        // Tạo truy vấn SQL để cập nhật
	        String sql = "UPDATE Ctdkm SET mucgiamgia = :mucgiamgia " +
	                     "WHERE Ctdkm.madkm = :madkm " +
	                     "AND Ctdkm.mamh = :mamh";
	        
	        // Tạo một truy vấn SQL
	        Query query = session.createSQLQuery(sql);
	        
	        // Đặt giá trị mới cho cột "mucgiamgia"
	        query.setParameter("mucgiamgia", muckm);
	        
	        // Đặt giá trị "madkm"
	        query.setParameter("madkm", makm);
	        
	        // Đặt giá trị "mamh"
	        query.setParameter("mamh", idsp);
	        
	        // Thực hiện truy vấn cập nhật và lấy số dòng bị ảnh hưởng
	        int rowsAffected = query.executeUpdate();
	        
	        // Kiểm tra nếu có ít nhất một dòng bị ảnh hưởng thì cập nhật thành công
	        if (rowsAffected > 0) {
	            updated = true;
	        }
	        
	        t.commit(); // Commit giao dịch nếu không có lỗi
	    } catch (Exception e) {
	        t.rollback(); // Rollback giao dịch nếu có lỗi
	        e.printStackTrace();
	    } finally {
	        session.close(); // Đóng phiên làm việc
	    }
	    
	    return updated; // Trả về true nếu cập nhật thành công, ngược lại false
	}


	@Override
	public boolean add_CT_KhuyenMai(int idsp, int makm, Double muckm) {
		Session session = sessionFactory.openSession();
	    Transaction t = session.beginTransaction();
	    boolean updated = false; // Biến này để kiểm tra xem việc cập nhật đã thành công hay không
	    
	    try {
	        // Tạo truy vấn SQL để cập nhật
	        String sql = "INSERT INTO ctdkm (madkm, mamh, mucgiamgia) VALUES (:madkm, :mamh, :muckm)";
	        
	        // Tạo một truy vấn SQL
	        Query query = session.createSQLQuery(sql);
	        
	        // Đặt giá trị mới cho cột "mucgiamgia"
	        query.setParameter("muckm", muckm);
	        
	        // Đặt giá trị "madkm"
	        query.setParameter("madkm", makm);
	        
	        // Đặt giá trị "mamh"
	        query.setParameter("mamh", idsp);
	        
	        // Thực hiện truy vấn cập nhật và lấy số dòng bị ảnh hưởng
	        int rowsAffected = query.executeUpdate();
	        
	        // Kiểm tra nếu có ít nhất một dòng bị ảnh hưởng thì cập nhật thành công
	        if (rowsAffected > 0) {
	            updated = true;
	        }
	        
	        t.commit(); // Commit giao dịch nếu không có lỗi
	    } catch (Exception e) {
	        t.rollback(); // Rollback giao dịch nếu có lỗi
	        e.printStackTrace();
	    } finally {
	        session.close(); // Đóng phiên làm việc
	    }
	    
	    return updated;
		
	}

	@Override
	public List<KhuyenMaiDto> get_CTDKM_By_MaKM(int makm) {
		Session session = sessionFactory.openSession();
	    Transaction t = session.beginTransaction();
	    String sql = "select Ctdkm.mamh,Ctdkm.mucgiamgia from Ctdkm where Ctdkm.madkm= :madkm";
	    Query query = session.createSQLQuery(sql);        
        query.setParameter("madkm", makm);
        List<Object[]> resultList = query.list();
        List<KhuyenMaiDto> dtos = new ArrayList<>();
        
        
        
        for (Object[] row : resultList) {
            if (row.length == 2) {
                Integer mamh = (Integer) row[0];
                Double mucgiamgia1 = (Double) row[1];
                int mucgiamgia = Double.valueOf(mucgiamgia1).intValue();
                KhuyenMaiDto dto= new KhuyenMaiDto();
                dto.setMadkm(makm);
                dto.setMatHang(mamh);
                dto.setMucGiamGia(mucgiamgia);
                dto.setTenSP(iGioHangDAO.getTenMatHangFromID(mamh)); 
                dtos.add(dto);
            }
        }
        
		return dtos;
	}
	
	@Override
	public Dotkhuyenmai get_DKM_By_Id(int id) {
	    Session session = sessionFactory.openSession();
	    Transaction t = session.beginTransaction();
	    String hql = "from Dotkhuyenmai where madkm = :id";
	    Query query = session.createQuery(hql); 
	    query.setParameter("id", id);
	    Dotkhuyenmai dkm = (Dotkhuyenmai) query.uniqueResult();
	    t.commit();
	    
	    session.close();
	    return dkm;
	}

	@Override
	public List<Mathang> get_List_MatHang_by_MaKM(int madkm) {
		List<Mathang> list_mathang = new ArrayList<>();
		
		return null;
	}
	
	
	

}
