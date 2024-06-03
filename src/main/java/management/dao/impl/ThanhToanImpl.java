package management.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import management.dao.IThanhToanDAO;
import management.entity.Ctpd;
import management.entity.CtpdId;
import management.entity.Ctsize;
import management.entity.CtsizeId;
import management.entity.Khachhang;
import management.entity.Phieudat;

@Repository
@Transactional
public class ThanhToanImpl implements IThanhToanDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Ctsize layCtSize(int mamh, String size) {
		Session session = sessionFactory.openSession();
		try {
			String hql = "FROM Ctsize where mathang.mamh = :mamh AND size.tensize= :size";
			Query query = session.createQuery(hql);

			query.setParameter("mamh", mamh);
			query.setParameter("size", size);
			Ctsize ctSize = (Ctsize) query.uniqueResult();
			Hibernate.initialize(ctSize.getMathang().getHinhanhmhs());
			System.out.println("Dao: " + ctSize.toString());
			return ctSize;
		} finally {
			session.close();
		}
	}

	@Override
	public Ctsize layCtSize(CtsizeId id) {
		Session session = sessionFactory.openSession();
		try {
			String hql = "FROM management.entity.Ctsize AS ctsize WHERE ctsize.id.mamh = :mamh AND ctsize.id.masize = :masize"
					+ "";
			Query query = session.createQuery(hql);

			query.setParameter("mamh", id.getMamh());
			query.setParameter("masize", id.getMasize());

			Ctsize ctSize = (Ctsize) query.uniqueResult();
			System.out.println("Dao: " + ctSize.toString());
			return ctSize;
		} finally {
			session.close();
		}
	}

	@Override
	public void themPhieuDat(Phieudat pd) {

		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(pd);
		transaction.commit();
		session.close();
	}

	@Override
	public Khachhang layKhachHangTheoGmail(String gmail) {
		Session session = sessionFactory.openSession();
		try {
			String hql = "FROM Khachhang where taikhoan.email = :gmail";
			Query query = session.createQuery(hql);
			query.setParameter("gmail", gmail);
			Khachhang kh = (Khachhang) query.uniqueResult();
			return kh;
		} finally {
			session.close();
		}
	}

	@Override
	public Khachhang layKhachHangTheoId(int id) {
		Session session = sessionFactory.openSession();
		try {
			String hql = "FROM Khachhang where makh = :makh";
			Query query = session.createQuery(hql);
			query.setParameter("makh", id);
			Khachhang kh = (Khachhang) query.uniqueResult();
			return kh;
		} finally {
			session.close();
		}
	}

	@Override
	public void themCtpd(List<Ctpd> ctpds) {

		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		
		try {
		transaction = session.beginTransaction();

		for (Ctpd sp : ctpds) {
			session.save(sp);
			System.out.println("Show them ctps: " + sp);
		}

		transaction.commit();
		}catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		session.close();

	}

	@Override
	public Phieudat layPhieudatTheoId(int id) {
		Session session = sessionFactory.openSession();
		try {
			String hql = "FROM Phieudat where mapd = :id";
			Query query = session.createQuery(hql);
			query.setParameter("id", id);
			Phieudat pd = (Phieudat) query.uniqueResult();
			return pd;
		} finally {
			session.close();
		}
	}

	public void capNhatSLSP(Ctsize ctsize, int slMua) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			int slConLai = ctsize.getSoluong() - slMua;
			ctsize.setSoluong(slConLai);
			session.update(ctsize);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback(); // Rollback nếu có lỗi
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

}
