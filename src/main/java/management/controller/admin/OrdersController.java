package management.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import management.DTO.ThanhToanDto;
import management.controller.admin.state.CancelledOrderState;
import management.controller.admin.state.CompletedOrderState;
import management.controller.admin.state.ConfirmOrderState;
import management.controller.admin.state.NewOrderState;
import management.controller.admin.state.OrderContext;
import management.controller.admin.state.ProcessingOrderState;
import management.dao.IDonHangDao;
import management.dao.impl.DonHangDaoImpl;
import management.entity.Ctpd;
import management.entity.Phieudat;

@Controller
@Transactional
@RequestMapping("/admin/orders")
public class OrdersController {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@Autowired
	private IDonHangDao donHangDao;

	@GetMapping("")
	public ModelAndView listOrders(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("admin/orders");

		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Phieudat");
		List<Phieudat> orders = query.list();

		session.close();

		PagedListHolder<Phieudat> pagedListHolder = new PagedListHolder<>(orders);
		int page = ServletRequestUtils.getIntParameter(request, "page", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(10); // Adjust the page size as needed

		modelAndView.addObject("pagedListHolder", pagedListHolder);

		return modelAndView;
	}

	// CTPD PHUC
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewOrder(HttpServletRequest request, @PathVariable("id") String id) {
		ModelAndView modelAndView = new ModelAndView("admin/viewOrder");
		Session session = sessionFactory.openSession();
		Integer orderId = Integer.parseInt(id);

		Query query = session.createQuery("from Phieudat where id = :id");
		query.setParameter("id", orderId);
		Phieudat order = (Phieudat) query.uniqueResult(); // Lấy ra một phiếu đặt duy nhất

		// CTPD
		Query query1 = session.createQuery("from Ctpd where mapd = :id");
		query1.setParameter("id", orderId);
		List<Ctpd> orders = query1.list();

		System.out.println(orders);

		PagedListHolder<Ctpd> pagedListHolders = new PagedListHolder<>(orders);
		int page = ServletRequestUtils.getIntParameter(request, "page", 0);
		pagedListHolders.setPage(page);
		pagedListHolders.setPageSize(10); // Adjust the page size as needed

		session.close();
		modelAndView.addObject("order", order); // Thêm phiếu đặt vào model để truyền sang JSP
		modelAndView.addObject("pagedListHolders", pagedListHolders);

		return modelAndView;
	}

	// STATE PHUC
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String changeOrderState(ModelMap model, HttpServletRequest request, @PathVariable("id") String id)
			throws IOException {

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			int trangthai = Integer.parseInt(request.getParameter("trangthai"));

			Query query = session.createQuery("from Phieudat where id = :id");
			query.setParameter("id", Integer.parseInt(id));
			Phieudat order = (Phieudat) query.uniqueResult();

			if (trangthai == 4) { // ADMIN XÁC NHẬN ĐƠN HÀNG ĐÃ XONG
				order.setTrangthai(trangthai);
				session.update(order);
			}

			OrderContext context;
			switch (order.getTrangthai()) {
			case 0:
				context = new OrderContext(new CancelledOrderState());
				break;
			case 1:
				context = new OrderContext(new NewOrderState());
				break;
			case 2:
				context = new OrderContext(new ConfirmOrderState());
				break;
			case 3:
				context = new OrderContext(new ProcessingOrderState());
				break;
			case 4:
				context = new OrderContext(new CompletedOrderState());
				break;
			default:
				throw new IllegalArgumentException("Invalid order state");
			}

			context.changeState(order, trangthai);

			session.update(order);
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		} finally {
			session.close();
		}

		return "redirect:/admin/orders/" + id;
	}

//    @RequestMapping("/tmp")
//    public String getTotalPrice()
//    {
//    	 Session session = sessionFactory.openSession();
//         Query query = session.createQuery("from Phieudat");
//         List<Phieudat> orders = query.list();
//         session.close();
//         
//         for(Phieudat pd:orders) {
//        	 List<Ctpd> ctpds = donHangDao.layDSSPCuaPD(pd.getMapd());
//        	 for(Ctpd ctpd: ctpds) {
//        		 session = sessionFactory.openSession();
//        		  query = session.createQuery("select mathang from Ctpd where mapd =: mapd");
//        		  query.setParameter("mapd", ctpd.getId().getMapd());
//        		  List<Integer> resultList = query.list();
//        		  session.close();
//        		  for(Integer x: resultList)
//        		  {
//        			  System.out.println("Mã sản phẩm: "+x+"\n");
//        		  }
//        	 }
////        	 int soLuongSP = 0;
////     		int tongTien = 0;
////     		List<ThanhToanDto> DSSP = new ArrayList<ThanhToanDto>();
////     		
////     		for(Ctpd ctpd: ctpds) {
////
////     			System.out.println("Mã phiếu đặt: "+ctpd.getId().getMapd()+"\n");
////     			
////     			ThanhToanDto sp = new ThanhToanDto();
////     			sp.setCtSize(ctpd.getCtsize());
////     			sp.setDonGia(donHangDao.LayGiaSP(ctpd.getId().getMamh()));
////     			System.out.println("Đơn giá của sản phẩm: "+ctpd.getId().getMamh()+"là"+ sp.getDonGia());
////     			sp.setDonGiaKM(0);
////     			sp.setSoLuong(ctpd.getSoluong());
////     			System.out.println("SỐ lượng của sản phẩm: "+ctpd.getId().getMamh()+"là"+ sp.getSoLuong());
////
////     			DSSP.add(sp);
////     			soLuongSP += ctpd.getSoluong();
////     			tongTien += ctpd.getSoluong() * donHangDao.LayGiaSP(ctpd.getId().getMamh());
////     		}
//// 			System.out.println("Tổng tiền là : "+tongTien+"------\n");
//;
//         }
//				
//		
//		
//    	return "";
//    }

	@RequestMapping("/tmp")
	// GET TOTAL PRICE OF ORDER
	public String getTotalPrice() {

		float totalPrice = 0;

		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Phieudat");
		List<Phieudat> orders = query.list();
		session.close();
		System.out.println(orders.toString() + " lephuc12 ");
		System.out.println(orders + " lephuc123 ");
		
		for (Phieudat pd : orders) {
			List<Ctpd> ctpds = donHangDao.layDSSPCuaPD(pd.getMapd());

			for (Ctpd ctpd : ctpds) {
//				int donGia = donHangDao.LayGiaSP(ctpd.getId().getMamh());
//  			totalPrice += donGia * ctpd.getSoluong();		
				totalPrice += 5;
			}

			System.out.println(totalPrice + " levanphuc /n");
		}
		return "";
	}

}
