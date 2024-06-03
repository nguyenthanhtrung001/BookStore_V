package management.dao.impl;

import java.math.BigInteger;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import management.dao.IMatHangDao;
import management.entity.Banggia;
import management.entity.Mathang;

import management.dao.IMatHangDao;

@Repository
@Transactional
public class MatHangDaoImpl implements IMatHangDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Mathang> getAllMathang() {

		Session session = sessionFactory.openSession();
		List<Mathang> list = null;
		int status=1;
		String hgl = "FROM Mathang where trangthai = ? ";
		Query query = session.createQuery(hgl);
		query.setParameter(0, status);
		list = query.list();

		
		return list;

	}

	@Override
	public List<Mathang> getProductOfCategory(String category) {
		Session session = sessionFactory.openSession();
		List<Mathang> list = null;
		String hql = "FROM Mathang M WHERE M.loaimh.id = :loaimhId";
		Query query = session.createQuery(hql);
		query.setParameter("loaimhId", Integer.parseInt(category));

		list = query.list();

		
		return list;
	}

	@Override
	public List<Mathang> getProductOfCategoryName(String slugNameCategory) {
		Session session = sessionFactory.openSession();
		List<Mathang> list = null;
		String hql = "FROM Mathang M WHERE M.loaimh.slug = :slug";
		Query query = session.createQuery(hql);
		query.setParameter("slug", slugNameCategory);

		list = query.list();
		

		return list;
	}

	@Override
	public List<Mathang> searchProductsLike(String input) {
		Session session = sessionFactory.openSession();
		List<Mathang> list = null;

		String hql = "FROM Mathang M WHERE M.tenmh LIKE :partialTenmh "
				+ "OR M.loaimh.tenloaimh LIKE :partialTenloaimh " + "OR M.chatlieu.tenvai LIKE :partialtenvai "
				+ "OR M.nhanhieu.tennh LIKE :partialtennhanhieu " + "OR M.mota LIKE :partialmota";

		Query query = session.createQuery(hql);
		query.setParameter("partialTenmh", "%" + input + "%");
		query.setParameter("partialTenloaimh", "%" + input + "%");
		query.setParameter("partialtenvai", "%" + input + "%");
		query.setParameter("partialmota", "%" + input + "%");
		query.setParameter("partialtennhanhieu", "%" + input + "%");

		list = query.list();

		session.close();
		return list;
	}

	@Override
	public List<Mathang> getMathangAndTotalSoluongTop(int top) {
		Session session = sessionFactory.openSession();

		String hql = "SELECT TOP " + top + " M.* " + "FROM mathang M " + "INNER JOIN ("
				+ "    SELECT C.MAMH, SUM(C.soluong) AS TongSoLuong " + "    FROM Ctpd C " + "    GROUP BY C.MAMH "
				+ ") AS SubQuery ON M.MAMH = SubQuery.MAMH " + "ORDER BY SubQuery.TongSoLuong DESC";

		Query query = session.createSQLQuery(hql).addEntity(Mathang.class);

		List<Mathang> resultList = query.list();

		
		session.close();
		return resultList;
	}

	@Override
	public List<Mathang> getProductHasDiscount(int top) {
		Session session = sessionFactory.openSession();
		List<Mathang> list = null;

		try {
			// Lấy ngày hiện tại
			Date currentDate = new Date();

			// Sử dụng câu truy vấn HQL để lấy danh sách Mathang có trong Ctdkm
			String hql = "SELECT DISTINCT M FROM Mathang M " +
		             "INNER JOIN M.ctdkms C " +
		             "INNER JOIN C.dotkhuyenmai D " +
		             "WHERE D.ngaykt >= :currentDate";

			Query query = session.createQuery(hql);
			query.setParameter("currentDate", currentDate);
			query.setMaxResults(top); // Giới hạn số lượng sản phẩm trả về là 8
			
			
			list = query.list();
		

			Collections.shuffle(list); // Xáo trộn danh sách
			
		
		} catch (Exception e) {
			// Xử lý exception nếu có
			e.printStackTrace();
		} finally {
			session.close();
		}

		return list;
	}

	@Override
	public int getPrice_Product(Mathang product) {
		Session session = sessionFactory.openSession();
		Date currentDate = new Date();

		String hql = "SELECT b.giamoi FROM Banggia b WHERE b.id.mamh = :mamh AND b.id.ngayapdung <= :currentDate ORDER BY b.id.ngayapdung DESC";
		Query query = session.createQuery(hql);
		query.setParameter("mamh", product.getMamh());
		query.setParameter("currentDate", currentDate);
		query.setMaxResults(1);

		Integer gia = (Integer) query.uniqueResult();

		if (gia != null) {
			return gia;
		} else {
			// Handle the case where no result is found, e.g., return a default value or
			// throw an exception.
			return 0; // Return a default value
		}

	}

	@Override
	public double getDiscount_Product(Mathang mamhToSearch) {
		Session session = sessionFactory.openSession();
		Date currentDate = new Date();

		String hql = "SELECT MAX(c.mucgiamgia) FROM Ctdkm c WHERE c.mathang.mamh = :mamh AND c.mucgiamgia > 0 AND c.dotkhuyenmai.ngaykt >= :currentDate";
		Query query = session.createQuery(hql);
		query.setParameter("mamh", mamhToSearch.getMamh());
		query.setParameter("currentDate", currentDate);
		query.setMaxResults(1);

		Double mucgiamgia = (Double) query.uniqueResult();
		if (mucgiamgia == null) {
			return 0.0;
		}

		return mucgiamgia;
	}

	@Override
	public boolean updatePrice(Banggia banggia) {

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(banggia); // Lưu đối tượng Employee vào cơ sở dữ liệu
			tx.commit(); // Xác nhận giao dịch

			session.close(); // Đóng session
			System.out.println("them tk thanh cong");
			return true;

		} catch (Exception e) {
			System.out.println("that bai tk");
			if (tx != null) {
				tx.rollback(); // Rollback nếu xảy ra lỗi
			}
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public List<Mathang> getMathangByPage(int pageNumber, String title, String input, String category) {
		Session session = sessionFactory.openSession();
		int itemsPerPage = 4;
		int startIndex = (pageNumber - 1) * itemsPerPage;
		Date currentDate = new Date();
		String hql = "";
		Query query = null;
		if (title.equals("search")) {
			if (input == null) {
				input = "";
			} else {
				hql = "FROM Mathang M WHERE M.tenmh LIKE :partialTenmh "
						+ "OR M.loaimh.tenloaimh LIKE :partialTenloaimh " + "OR M.chatlieu.tenvai LIKE :partialtenvai "
						+ "OR M.nhanhieu.tennh LIKE :partialtennhanhieu " + "OR M.mota LIKE :partialmota";

				query = session.createQuery(hql);
				query.setParameter("partialTenmh", "%" + input + "%");
				query.setParameter("partialTenloaimh", "%" + input + "%");
				query.setParameter("partialtenvai", "%" + input + "%");
				query.setParameter("partialmota", "%" + input + "%");
				query.setParameter("partialtennhanhieu", "%" + input + "%");

			}
		}
		if (title.equals("all")) {
			hql = "FROM Mathang";
			query = session.createQuery(hql);
		}
		if (title.equals("discount")) {
			hql = "SELECT M FROM Mathang M " + "INNER JOIN M.ctdkms C " + "INNER JOIN C.dotkhuyenmai D "
					+ "WHERE D.ngaykt >= :currentDate ";

			query = session.createQuery(hql);
			query.setParameter("currentDate", currentDate);
		}

		if (title.equals("selling")) {
			hql = "SELECT  M.* " + "FROM mathang M " + "INNER JOIN ("
					+ "    SELECT C.MAMH, SUM(C.soluong) AS TongSoLuong " + "    FROM Ctpd C " + "    GROUP BY C.MAMH "
					+ ") AS SubQuery ON M.MAMH = SubQuery.MAMH " + "ORDER BY SubQuery.TongSoLuong DESC";
			query = session.createSQLQuery(hql).addEntity(Mathang.class);

		}
		if (title.equals("category")) {
			hql = "FROM Mathang M WHERE M.loaimh.slug = :slug";
			query = session.createQuery(hql);
			query.setParameter("slug", category);

		}

		try {

			List<Mathang> pageData = extracted(itemsPerPage, startIndex, query);
			return pageData;
		} finally {
			session.close();
		}
	}

	@Override
	public List<Mathang> getMathangByPage_Nhan(int pageNumber, String title, String input, String category, String nhan) {
		Session session = sessionFactory.openSession();
		int itemsPerPage = 4;
		int startIndex = (pageNumber - 1) * itemsPerPage;
		Date currentDate = new Date();
		String hql = "";
		Query query = null;
		if (title.equals("search")) {
			if (input == null) {
				input = "";
			} else {
				hql = "FROM Mathang M WHERE "+ "M.nhan = :nhan " 
						+"AND (M.tenmh LIKE :partialTenmh "
					    + "OR M.loaimh.tenloaimh LIKE :partialTenloaimh " 
					    + "OR M.chatlieu.tenvai LIKE :partialtenvai "
					    + "OR M.nhanhieu.tennh LIKE :partialtennhanhieu " 
					    + "OR M.mota LIKE :partialmota) ";

				query = session.createQuery(hql);
				query.setParameter("partialTenmh", "%" + input + "%");
				query.setParameter("partialTenloaimh", "%" + input + "%");
				query.setParameter("partialtenvai", "%" + input + "%");
				query.setParameter("partialmota", "%" + input + "%");
				query.setParameter("partialtennhanhieu", "%" + input + "%");
				query.setParameter("nhan",nhan );
				System.out.println("Thịnh đã tới đấy");
			}
		}
		if (title.equals("all")) {
			hql = "FROM Mathang";
			query = session.createQuery(hql);
		}
		if (title.equals("discount")) {
			hql = "SELECT M FROM Mathang M " + "INNER JOIN M.ctdkms C " + "INNER JOIN C.dotkhuyenmai D "
					+ "WHERE D.ngaykt >= :currentDate ";

			query = session.createQuery(hql);
			query.setParameter("currentDate", currentDate);
		}

		if (title.equals("selling")) {
			hql = "SELECT  M.* " + "FROM mathang M " + "INNER JOIN ("
					+ "    SELECT C.MAMH, SUM(C.soluong) AS TongSoLuong " + "    FROM Ctpd C " + "    GROUP BY C.MAMH "
					+ ") AS SubQuery ON M.MAMH = SubQuery.MAMH " + "ORDER BY SubQuery.TongSoLuong DESC";
			query = session.createSQLQuery(hql).addEntity(Mathang.class);

		}
		if (title.equals("category")) {
			hql = "FROM Mathang M WHERE M.loaimh.slug = :slug ";
			query = session.createQuery(hql);
			query.setParameter("slug", category);

		}

		try {

			List<Mathang> pageData = extracted(itemsPerPage, startIndex, query);
			return pageData;
		} finally {
			session.close();
		}
	}
	
	
	
	private List<Mathang> extracted(int itemsPerPage, int startIndex, Query query) {
		query.setFirstResult(startIndex);
		query.setMaxResults(itemsPerPage);
		List<Mathang> pageData = query.list();
		return pageData;
	}

}
