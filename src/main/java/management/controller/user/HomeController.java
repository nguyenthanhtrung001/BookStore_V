package management.controller.user;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import management.bean.ProductWithDiscount;
import management.bean.RemoveDiacritics;
import management.controller.Apriori;
import management.controller.ReadPython;
import management.dao.IAprioriDao;
import management.dao.IDanhMucDao;
import management.dao.IMatHangDao;
import management.dao.ITaiKhoanDAO;
import management.entity.Loaimh;
import management.entity.Mathang;
import management.login_google.UserGoogleDto;



@Transactional
@Controller
@RequestMapping("/user")
public class HomeController {
	
	
	@Autowired
	private IDanhMucDao danhMucDao;
	@Autowired
	private IMatHangDao matHangDao;
	
	@Autowired
	private Apriori apriori;
	@Autowired
	private ITaiKhoanDAO taiKhoanDAO;
	// test
	private List<Mathang> listmhdanhgia = new ArrayList<Mathang>();

	@GetMapping("/index")
	public ModelAndView home(ModelMap model,HttpServletRequest request) throws IOException, InterruptedException {
		HttpSession session = request.getSession();
		String userEmail = (String) session.getAttribute("loggedInUserEmail");
		
		if(userEmail !=null) session.setAttribute("login", true);
		else session.setAttribute("login", false);
		
		/*
		 * Boolean loginValue = (Boolean) session.getAttribute("login"); // boolean
		 * isLoggedin = loginValue != null && loginValue.booleanValue();
		 * System.out.println(loginValue);
		 */
		List<Loaimh> listCategory=danhMucDao.getAllDanhMuc();
		
		List<Mathang>listtmp=matHangDao.getMathangAndTotalSoluongTop(6);
		List<ProductWithDiscount>list_P_Sale=new ArrayList<>();
		
		for(Mathang mh: listtmp) {
			
			ProductWithDiscount tmp = new ProductWithDiscount();
			tmp.setMucgiamgia((int)matHangDao.getDiscount_Product(mh));
			
			tmp.setMathang(mh);
			tmp.setGia(matHangDao.getPrice_Product(mh));
			
			
			
			list_P_Sale.add(tmp);
			
		}
		
		model.addAttribute("listProductSale", list_P_Sale);
		model.addAttribute("listCategory", listCategory);
		
		List<Mathang>listP_Discount=matHangDao.getProductHasDiscount(8);
		
		
		List<ProductWithDiscount>list_P_D=new ArrayList<>();
		
		for(Mathang mh: listP_Discount) {
			ProductWithDiscount tmp = new ProductWithDiscount();
			tmp.setMucgiamgia((int)matHangDao.getDiscount_Product(mh));
			if(tmp.getMucgiamgia()==0) continue;
			tmp.setMathang(mh);
			tmp.setGia(matHangDao.getPrice_Product(mh));
			
			
			
			list_P_D.add(tmp);
			
		}
		model.addAttribute("listDiscount", list_P_D);
		
		
		ModelAndView modelAndView = new ModelAndView("user/home");
		
		//test okok
		
		List<ProductWithDiscount>list_P_smarts=new ArrayList<>();
		
		System.out.println("email:"+userEmail);

		
		try {
			if (userEmail != null) {
				int makh = taiKhoanDAO.get_khachHang_byEmail(userEmail).getMakh();
				System.out.println("mamkh:"+makh);
				
				listmhdanhgia = apriori.Apriori(makh);// 1 là ma kh
				for (Mathang mh : listmhdanhgia) {

					ProductWithDiscount tmp = new ProductWithDiscount();
					tmp.setMucgiamgia((int) matHangDao.getDiscount_Product(mh));

					tmp.setMathang(mh);
					tmp.setGia(matHangDao.getPrice_Product(mh));
					list_P_smarts.add(tmp);
				}
			}
			
			
			
			model.addAttribute("listProductSmart", list_P_smarts);
			return modelAndView;
		} catch (Exception e) {
			
			e.printStackTrace();
		}	

		
		/*
		 * String listMHStr = getRecommendation(1 + ""); String tmp = listMHStr; tmp =
		 * tmp.replace("[", ""); tmp = tmp.replace("]", ""); String[] elements =
		 * tmp.split(", "); System.out.println("test cai j nef troiwf :" + elements);
		 * 
		 * for (String element : elements) { System.out.println(element); }
		 */
		
		
		return modelAndView;
	}
	

	
	@RequestMapping("log-out")
	public String logOut(HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		Boolean userEmail = (Boolean) session.getAttribute("login");
		
		if (userEmail != null) {
			 session.setAttribute("loggedInUserEmail", null);
	        // Nếu userEmail là null, bạn có thể xóa nó khỏi session như sau:
	        session.setAttribute("login", false);
	    }
		return "redirect:/user/index";
	}

	@RequestMapping("login")
	public String login()
	{
		return "redirect:https://accounts.google.com/o/oauth2/auth?scope=email%20profile&hl=vi&redirect_uri=http://localhost:8080/login_google/LoginGoogleHandler&response_type=code&client_id=242828357380-bmi9embekgk0pqkcl14jqt35g80letup.apps.googleusercontent.com&approval_prompt=force";
	}
	
}

