package management.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import management.dao.IDanhMucDao;
import management.dao.IMatHangDao;
import management.entity.Loaimh;
import management.entity.Mathang;

@Repository
@Transactional
public class DanhMucDaoImpl implements IDanhMucDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Loaimh> getAllDanhMuc() {
		Session session = sessionFactory.openSession();
		List<Loaimh> list = null;
		String hgl = "FROM Loaimh";
		Query query = session.createQuery(hgl);
		list = query.list();

		
		return list;
	}

}
