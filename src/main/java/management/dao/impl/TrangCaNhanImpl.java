package management.dao.impl;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import management.dao.ITrangCaNhanDAO;
import management.entity.Khachhang;

@Transactional
@Repository
public class TrangCaNhanImpl implements ITrangCaNhanDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Khachhang getKhachhangByEmail(String id) {
	    Session session = sessionFactory.openSession();	
	    try {
	        String hql = "FROM Khachhang kh WHERE kh.taikhoan.email =:id";
	        Query query = session.createQuery(hql);
			query.setParameter("id", id);
	        Khachhang kh = (Khachhang) query.uniqueResult();
	        return kh;
	    } catch (HibernateException ex) {
	        System.out.println("Lỗi: " + ex.getMessage());
	        return null;
	    }finally {
			session.close();
		}
	    
	}
	
	@Override
	public Integer updateKhachhang(Khachhang kh) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(kh);
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
}
