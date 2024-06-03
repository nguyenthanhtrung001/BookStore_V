package management.dao.impl;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import management.dao.INhapHangDao;
import management.entity.Chatlieu;
import management.entity.Ctpn;
import management.entity.Ctsize;
import management.entity.CtsizeId;
import management.entity.Hinhanhmh;
import management.entity.Loaimh;
import management.entity.Mathang;
import management.entity.Nhacungcap;
import management.entity.Nhanhieu;
import management.entity.Nhanvien;
import management.entity.Phieunhap;
import management.entity.Size;

@Repository
@Transactional
public class NhapHangDaoImpl implements INhapHangDao {

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public List<Mathang> layDSMatHang() {
        Session session = sessionFactory.openSession(); // Sử dụng openSession() thay vì openSession()
        try {
            String hql = "FROM Mathang";
            Query query = session.createQuery(hql);
            List<Mathang> dsmh = (List<Mathang>) query.list();
            System.out.println("Lấy danh sách mặt hàng thành công ");
            return dsmh;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
        	session.close();
        }
    }

    @Override
    public Ctsize layCtsize(int mamh, int masize) {
        Session session = sessionFactory.openSession(); // Sử dụng openSession() thay vì openSession()
        try {
            String hql = "FROM Ctsize where id.mamh= :mamh AND id.masize= :masize";
            Query query = session.createQuery(hql);
            query.setParameter("mamh", mamh);
            query.setParameter("masize", masize);
            Ctsize ct = (Ctsize) query.uniqueResult();
            System.out.println("Dao: " + ct);
            return ct;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
        	session.close();
        }
    }

    @Override
    public List<Size> layDSSize() {
        Session session = sessionFactory.openSession(); // Sử dụng openSession() thay vì openSession()
        try {
            String hql = "FROM Size";
            Query query = session.createQuery(hql);
            List<Size> dsSize = (List<Size>) query.list();
            System.out.println("Lấy danh sách size thành công ");
            return dsSize;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
        	session.close();
        }
    }

    @Override
    public List<Nhacungcap> layDSNhaCungCap() {
        Session session = sessionFactory.openSession(); // Sử dụng openSession() thay vì openSession()
        try {
            String hql = "FROM Nhacungcap";
            Query query = session.createQuery(hql);
            List<Nhacungcap> dsNCC = (List<Nhacungcap>) query.list();
            return dsNCC;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
        	session.close();
        }
    }

    @Override
    public Nhanvien layNhanVien(int manv) {
        Session session = sessionFactory.openSession(); // Sử dụng openSession() thay vì openSession()
        try {
            String hql = "FROM Nhanvien where manv= :manv";
            Query query = session.createQuery(hql);
            query.setParameter("manv", manv);
            Nhanvien nv = (Nhanvien) query.uniqueResult();
            return nv;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
        	session.close();
        }
    }

    @Override
    public void themPhieuNhap(Phieunhap pn, List<Ctpn> ctpns) {
        Session session = sessionFactory.openSession(); // Sử dụng openSession() thay vì openSession()
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback nếu có lỗi
            }
            e.printStackTrace();
        }finally {
        	session.close();
        }
    }

    @Override
    public Nhacungcap layNCC(int maNCC) {
        Session session = sessionFactory.openSession(); // Sử dụng openSession() thay vì openSession()
        try {
            String hql = "FROM Nhacungcap where mancc= :mancc";
            Query query = session.createQuery(hql);
            query.setParameter("mancc", maNCC);
            Nhacungcap ncc = (Nhacungcap) query.uniqueResult();
            return ncc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
        	session.close();
        }
    }

    @Override
    public void themPhieuNhap(Phieunhap pn) {
        Session session = sessionFactory.openSession(); // Sử dụng openSession() thay vì openSession()
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(pn);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }finally {
        	session.close();
        }
    }

    @Override
    public void themCtSize(int mamh, int masize) {
        Session session = sessionFactory.openSession(); // Sử dụng openSession() thay vì openSession()
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            // Kiểm tra ctsize tồn tại hay chưa, nếu chưa tồn tại thì tạo mới
            if (layCtsize(mamh, masize) == null) {
                CtsizeId id = new CtsizeId();
                id.setMamh(mamh);
                id.setMasize(masize);

                Ctsize ctsize = new Ctsize();
                ctsize.setId(id);
                ctsize.setSoluong(0);

                session.save(ctsize);
                transaction.commit();
                System.out.println("Dao them cts: " + ctsize);
            }
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }finally {
        	session.close();
        }
    }

    @Override
    public void themSLTuPN(Ctsize ctsize, int sl) {
        Session session = sessionFactory.openSession(); // Sử dụng openSession() thay vì openSession()
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            int slMoi = ctsize.getSoluong() + sl;
            ctsize.setSoluong(slMoi);
            session.update(ctsize);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }finally {
        	session.close();
        }
    }

    @Override
    public void themCtpn(List<Ctpn> ctpns) {
        Session session = sessionFactory.openSession(); // Sử dụng openSession() thay vì openSession()
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            for (Ctpn ctpn : ctpns) {
                Ctsize ctsize = layCtsize(ctpn.getId().getMamh(), ctpn.getId().getMasize());
                themSLTuPN(ctsize, ctpn.getSoluong());
                session.save(ctpn);
            }
            transaction.commit();
        } catch (Exception e) {
        	System.out.println("ở đây");
            e.printStackTrace();
            transaction.rollback();
        }finally {
        	session.close();
        }
    }

    @Override
    public Mathang layMatHang(int mamh) {
        Session session = sessionFactory.openSession(); // Sử dụng openSession() thay vì openSession()
        try {
            String hql = "FROM Mathang where mamh= :mamh";
            Query query = session.createQuery(hql);
            query.setParameter("mamh", mamh);
            Mathang mh = (Mathang) query.uniqueResult();
            return mh;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
        	session.close();
        }
    }

    @Override
    public Size laySize(int masize) {
        Session session = sessionFactory.openSession(); // Sử dụng openSession() thay vì openSession()
        try {
            String hql = "FROM Size where masize= :masize";
            Query query = session.createQuery(hql);
            query.setParameter("masize", masize);
            Size size = (Size) query.uniqueResult();
            return size;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
        	session.close();
        }
    }

    @Override
    public List<Chatlieu> layDSChatlieu() {
        Session session = sessionFactory.openSession(); // Sử dụng openSession() thay vì openSession()
        try {
            String hql = "FROM Chatlieu";
            Query query = session.createQuery(hql);
            List<Chatlieu> ds = (List<Chatlieu>) query.list();
            return ds;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
        	session.close();
        }
    }

    @Override
    public List<Loaimh> layDSLoaiSP() {
        Session session = sessionFactory.openSession(); // Sử dụng openSession() thay vì openSession()
        try {
            String hql = "FROM Loaimh";
            Query query = session.createQuery(hql);
            List<Loaimh> ds = (List<Loaimh>) query.list();
            return ds;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Nhanhieu> layDSNhanHieu() {
        Session session = sessionFactory.openSession(); // Sử dụng openSession() thay vì openSession()
        try {
            String hql = "FROM Nhanhieu";
            Query query = session.createQuery(hql);
            List<Nhanhieu> ds = (List<Nhanhieu>) query.list();
            return ds;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void themSPMoi(Mathang mh) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(mh);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    @Override
    public void themSizeMoi(Size s) {
        Session session = sessionFactory.openSession(); // Sử dụng openSession() thay vì openSession()
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(s);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback(); // Kiểm tra xem transaction đã được khởi tạo chưa trước khi rollback
            }
        }finally {
        	session.close();
        }
    }

    @Override
    public void themAnhMoi(Hinhanhmh anh) {
        Session session = sessionFactory.openSession(); // Sử dụng openSession() thay vì openSession()
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(anh);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback(); // Kiểm tra xem transaction đã được khởi tạo chưa trước khi rollback
            }
        }finally {
        	session.close();
        }
    }

	@Override
	public String layTenNhanHieu(int manh) {
		Session session = sessionFactory.openSession(); // Sử dụng openSession() thay vì openSession()
        try {
            String hql = "select tennh FROM Nhanhieu where manh =:manh";
            Query query = session.createQuery(hql);
            query.setParameter("manh", manh);
            String tenNH =  (String) query.uniqueResult();
            return tenNH;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
        	session.close();
        }

	}

	@Override
	public String layTenChatLieu(int macl) {
		Session session = sessionFactory.openSession(); // Sử dụng openSession() thay vì openSession()
        try {
            String hql = "select tenvai FROM Chatlieu where macl =:macl";
            Query query = session.createQuery(hql);
            query.setParameter("macl", macl);
            String tenCL =  (String) query.uniqueResult();
            return tenCL;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
        	session.close();
        }
	}
    
	@Override
	public Nhanvien layNhanVien(String email) {
		Session session = sessionFactory.openSession(); // Sử dụng openSession() thay vì openSession()
        try {
            String hql = " FROM Nhanvien where taikhoan.email =:email";
            Query query = session.createQuery(hql);
            query.setParameter("email", email);
            Nhanvien nv =  (Nhanvien) query.uniqueResult();
            return nv;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
        	session.close();
        }
	}
    
}
