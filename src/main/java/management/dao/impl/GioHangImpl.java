
package management.dao.impl;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.loader.custom.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import management.dao.IGioHangDAO;
import management.entity.Mathang;

@Repository
@Transactional
public class GioHangImpl implements IGioHangDAO {

	@Autowired
	private SessionFactory sessionFactory;

	// Lấy mặt hàng từ id có dạng là 1 chuỗi " productID+id "
	@Override
	public String getTenMatHangFromID(int id) {
		String tenMatHang = null;
		Session s = sessionFactory.openSession();
		String hql = "select b.tenmh from Mathang b where b.mamh = :mamh";
		Query query = s.createQuery(hql);
		try {
			query.setParameter("mamh", id);
			tenMatHang = (String) query.uniqueResult();
		} finally {
			s.close();
		}
		return tenMatHang;
	}

	@Override
	public int get_Price_From_ID(int id) {

		// Mở phiên làm việc
		Session session = sessionFactory.openSession();

		try {
			// Sử dụng HQL để truy vấn giá sản phẩm
			String hql = "SELECT bg.giamoi FROM Banggia bg WHERE bg.id.ngayapdung <= :currentDate AND bg.mathang.mamh =:mamh ORDER BY bg.id.ngayapdung DESC";
			Query query = session.createQuery(hql);
			Timestamp currentDate = new Timestamp(new Date().getTime());
			query.setParameter("currentDate", currentDate);
			query.setParameter("mamh", id);
			query.setMaxResults(1); // Lấy bản ghi đầu tiên (ngày gần nhất)

			
			int price_int = (int) query.uniqueResult();

			return price_int;
		} finally {
			// Đóng phiên làm việc
			session.close();

		}

	}

	@Override
	public int get_Max_SoLuong_CTSize(int idproduct, String sizename) {
		Session session = sessionFactory.openSession();

		try {
			// Truy vấn idsize từ tên sizename
			Query sizeQuery = session.createQuery("SELECT s.masize FROM Size s WHERE s.tensize = :tensize");
			sizeQuery.setParameter("tensize", sizename);
			int idSize = (int) sizeQuery.uniqueResult();
			
			
			
			// Nếu tìm thấy idsize, tiếp tục truy vấn soluong từ idsp và idsize
			Query soluongQuery = session.createQuery(
					"SELECT cts.soluong FROM Ctsize cts WHERE cts.id.mamh = :idproduct AND cts.id.masize = :idSize");
			soluongQuery.setParameter("idproduct", idproduct);
			soluongQuery.setParameter("idSize", idSize);
			int soluong = (int) soluongQuery.uniqueResult();
			return soluong;

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			session.close();
		}
		return 0;
	}

	// Lấy giá sau khi giảm
	@Override
	public double getDiscount_Product(int mamh) {
		Session session = sessionFactory.openSession();
		Date currentDate = new Date();

		String hql = "SELECT c.mucgiamgia FROM Ctdkm c WHERE c.mathang.mamh = :mamh AND c.mucgiamgia > 0 AND c.dotkhuyenmai.ngaykt >= :currentDate ";
		Query query = session.createQuery(hql);
		query.setParameter("mamh", mamh);
		query.setParameter("currentDate", currentDate);
		query.setMaxResults(1);

		Double mucgiamgia = (Double) query.uniqueResult();
		
		
		  if (mucgiamgia == null) { return 0; }

		return mucgiamgia;
	}

	
	
	
}

