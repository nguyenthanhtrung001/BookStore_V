package management.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import management.dao.IAprioriDao;
import management.dao.IDanhMucDao;
import management.dao.IMatHangDao;

import management.entity.Ctpd;
import management.entity.Danhgia;
import management.entity.Loaimh;
import management.entity.Mathang;
import management.entity.Phieudat;

@Repository
@Transactional
public class AprioriDaoImpl implements IAprioriDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Mathang> getLayDSSPDAMUA(int makh) {
		Session session = sessionFactory.openSession();
		List<Mathang> list = null;
		String hql = "SELECT  DISTINCT ctpd.ctsize.mathang FROM Ctpd ctpd WHERE ctpd.phieudat.khachhang.makh = :customerId";
		Query query = session.createQuery(hql);
		 query.setParameter("customerId", makh);

		list = query.list();
		

		return list;
		
		
	
	}

	@Override
	public List<Mathang> getLayDSSP() {
		Session session = sessionFactory.openSession();
		List<Mathang> list = null;
		int status=1;
		String hgl = "FROM Mathang where trangthai = ? ";
		Query query = session.createQuery(hgl);
		query.setParameter(0, status);
		list = query.list();

		System.out.println("Danh sách sản phẩm đã mua");
		
		for( Mathang mh: list)
		{
			System.out.println(mh.getTenmh());
		}
		return list;
	}

	@Override
	public List<Phieudat> getLayDSHD(int makh) {
		Session session = sessionFactory.openSession();
		List<Phieudat> list = null;
		int status=1;
		String hgl = "FROM Phieudat where trangthai = ? and makh=? ";
		Query query = session.createQuery(hgl);
		query.setParameter(0, status);
		query.setParameter(1, makh);
		list = query.list();
		
		
		return list;
	}

	@Override
	public List<Ctpd> getLayDSCTHD(int makh) {
		
		Session session = sessionFactory.openSession();
		List<Ctpd> list = null;
		String hgl = "FROM Ctpd ctpd where phieudat.khachhang.makh=? ";
		Query query = session.createQuery(hgl);
		query.setParameter(0, makh);
		list = query.list();
		

		
		return list;
	}

	@Override
	public double getLAYDANHGIA(int mamh) {
		
//		Session session = sessionFactory.openSession();
//		
//		String hql = "SELECT CAST(AVG(d.danhgia) AS double) FROM Danhgia d WHERE d.id.mamh = :mamh";
//
//		Query query = session.createQuery(hql);
//		query.setParameter("mamh", mamh);
//		
//		Double average = (Double) query.uniqueResult();
		
		Mathang mh=getMHById(mamh);
		Double average = 0.0;
		Double sum = 0.0;
			for (Danhgia dg :mh.getDanhgias()) {
				sum += dg.getDanhgia();
			}
		 average = sum*1.0/mh.getDanhgias().size()*1.0;

		
        return average != null ? average : 0.0; 
	}

	@Override
	public Mathang getMHById(int mamh) {
		Session session = sessionFactory.openSession();
		Mathang mh= null;
		String hql = "FROM Mathang M WHERE M.mamh = :mamh";
		Query query = session.createQuery(hql);
		query.setParameter("mamh", mamh);

		mh = (Mathang) query.uniqueResult();

		
		return mh;
	}

	

}
