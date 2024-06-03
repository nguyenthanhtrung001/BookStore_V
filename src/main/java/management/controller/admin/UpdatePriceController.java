package management.controller.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;



import management.bean.ProductWithDiscount;
import management.dao.IDanhMucDao;
import management.dao.IMatHangDao;
import management.dao.ITaiKhoanDAO;
import management.entity.Banggia;
import management.entity.BanggiaId;
import management.entity.Loaimh;
import management.entity.Mathang;
import management.entity.Nhanvien;
@Transactional
@Controller
@RequestMapping("/admin/update/")
public class UpdatePriceController {

	
	@Autowired
	IMatHangDao matHangDao;
	@Autowired
	IDanhMucDao danhMucDao;
	@Autowired 
	ITaiKhoanDAO taiKhoanDAO;
	
	
	
	
	
	public List<ProductWithDiscount> listPro() {

		List<Mathang> listProduct = matHangDao.getAllMathang();
		List<ProductWithDiscount> list_P_D = new ArrayList<>();
		for (Mathang mh : listProduct) {
			ProductWithDiscount tmp = new ProductWithDiscount();
			tmp.setMucgiamgia((int) matHangDao.getDiscount_Product(mh));

			tmp.setMathang(mh);
			tmp.setGia(matHangDao.getPrice_Product(mh));

			list_P_D.add(tmp);

		}
		return list_P_D;
	}

	public List<Loaimh> listCategory() {

		List<Loaimh> listCate = danhMucDao.getAllDanhMuc();
		return listCate;
	}
	
	@GetMapping("price")
	public ModelAndView update(ModelMap model) {
		

		model.addAttribute("listProduct", listPro());
		model.addAttribute("listCategory", listCategory());
		ModelAndView modelAndView = new ModelAndView("admin/updatePrice");
		return modelAndView;
		
	}
	
	@PostMapping("{priceType}/successful")
    public ModelAndView updateSuccessful(HttpServletRequest request,
    		@RequestParam(name = "productID",  required = false) String[] productIDs,
    									 @PathVariable(required = false) String priceType,
    									 @RequestParam(name = "productList", required = false) String[] productoOfCate,
                                         @RequestParam(name = "newPrice",required = false) Integer  newPrice,
                                         @RequestParam(name= "newPriceList",required = false) Integer  newPriceList ) {
        
		 Date currentDate = new Date();
		 ModelAndView modelAndView = new ModelAndView("admin/updatePrice");
		HttpSession session = request.getSession();
		String userEmail = (String) session.getAttribute("loggedInUserEmail");
		System.out.println("email:"+userEmail);
		Nhanvien nv=taiKhoanDAO.getNhanVien_byEmail(userEmail);
		
		String tbCOLOR="alert alert-danger";
		String tb="Vui lòng đang nhập vào hệ thống";
	    String tbCOLOR1="alert alert-success";
	    String tb1="CẬP NHẬT THÀNH CÔNG";
	    
        modelAndView.addObject("listProduct", listPro());
        modelAndView.addObject("listCategory", listCategory());
		if(nv!=null) {
			Integer manv=nv.getManv();
			 if(priceType.equals("price"))
			 {
				
				 extracted(currentDate, productIDs, newPrice,manv);
				 	modelAndView.addObject("tbC", tbCOLOR1);
			        modelAndView.addObject("tb", tb1);
			 }
			 else if(priceType.equals("pricelist")) {
				extracted(productoOfCate, newPriceList, currentDate,manv);
				 modelAndView.addObject("tbC", tbCOLOR1);
			     modelAndView.addObject("tb", tb1);
			 }
			  return modelAndView;
		}
		
		 
		 modelAndView.addObject("tbC", tbCOLOR);
	     modelAndView.addObject("tb", tb);
		
       
		  return modelAndView;
        
       
      
        
       

      
    }

	private void extracted(String[] productoOfCate, int newPriceList, Date currentDate,int manv) {
		for(String idCategory:productoOfCate)
		{
			List<Mathang> listP = matHangDao.getProductOfCategory(idCategory);
			for(Mathang product:listP) {
				
		        	Banggia tbp= new Banggia();
		            BanggiaId tbpi= new BanggiaId();
		            
		            tbpi.setMamh(product.getMamh());
		            tbpi.setManv(manv);
		            tbpi.setNgayapdung(currentDate);
		            
		            tbp.setId(tbpi);
		            tbp.setGiamoi(newPriceList);
		           
		            matHangDao.updatePrice(tbp);
		        
			}
		}
	}

	private void extracted(Date currentDate, String[] listUpdate, int price,int manv) {
		for (String productID : listUpdate) {
        	
        	Banggia tbp= new Banggia();
            BanggiaId tbpi= new BanggiaId();
            
            tbpi.setMamh(Integer.parseInt(productID));
            tbpi.setManv(manv);
            tbpi.setNgayapdung(currentDate);
            
            tbp.setId(tbpi);
            tbp.setGiamoi(price);
           
            matHangDao.updatePrice(tbp);
        }
	}
	
	
}
