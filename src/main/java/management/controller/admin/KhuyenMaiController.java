package management.controller.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import management.DTO.KhuyenMaiDto;
import management.dao.IKhuyenMaiDAO;
import management.dao.ITaiKhoanDAO;
import management.entity.Ctdkm;
import management.entity.Dotkhuyenmai;
import management.entity.Mathang;
@Transactional
@Controller
@RequestMapping("/admin/promotion/")
public class KhuyenMaiController {

	@Autowired
	ITaiKhoanDAO iTaiKhoanDAO;

	@Autowired
	IKhuyenMaiDAO iKhuyenMaiDAO;

	
	  @ModelAttribute("promotionlist") public List<Dotkhuyenmai> test() {
	  List<Dotkhuyenmai> list = new ArrayList<>(); return list; }
	 

	@RequestMapping("home")
	public String tmp(ModelMap model) {
		List<Dotkhuyenmai> promotionlist = iKhuyenMaiDAO.getAllKM();
		model.addAttribute("promotionlist", promotionlist);
		

		return "Promotion";
	}

	@RequestMapping(value = "add")
	public String addKM(HttpServletRequest request, ModelMap model) throws ParseException {

		

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String ngaybdkmString = request.getParameter("ngaybd");
		Date ngaybdkm = format.parse(ngaybdkmString);
		String ngayktkmString = request.getParameter("ngaykt");
		Date ngayktkm = format.parse(ngayktkmString);
		String LydoKM = request.getParameter("lydo");
		HttpSession session = request.getSession();
		String userEmail = (String) session.getAttribute("loggedInUserEmail");
		
		try {
			
			Date currentDate = new Date();
			Date startDate = ngaybdkm;
			Date endDate = ngayktkm;



			Dotkhuyenmai promotion = new Dotkhuyenmai();

			promotion.setNgaybd(ngaybdkm);

			promotion.setNgaykt(ngayktkm);

			promotion.setLydokm(LydoKM);
			
			promotion.setNhanvien(iTaiKhoanDAO.getNhanVien_byEmail(userEmail));

			Integer temp = iKhuyenMaiDAO.insertPromotion(promotion);
			
			if (temp != 0) {
				model.addAttribute("successMessage", "Thêm thành công!");
			} else {
				model.addAttribute("errorMessage", "Thêm thất bại!");
			}

			List<Dotkhuyenmai> promotionlist = iKhuyenMaiDAO.getAllKM();
			model.addAttribute("promotionlist", promotionlist);
		} catch (Exception e) {
			List<Dotkhuyenmai> promotionlist = iKhuyenMaiDAO.getAllKM();
			model.addAttribute("promotionlist", promotionlist);
			model.addAttribute("errorMessage", "Lỗi: Thêm thất bại!");
			return "redirect:/admin/promotion/home";
		}

		return "redirect:/admin/promotion/home";

	}

	@ModelAttribute("mathang")
	public List<Mathang> getmathang() {
		return iKhuyenMaiDAO.get_All_MatHang();
	}
	
	@PostMapping("update")
    public void processFormData(
            @RequestParam("madkm") int madkm,
            @RequestParam("mamh") int mamh,
            @RequestParam("mucKhuyenMai") Double mucKhuyenMai) {
		
		
        
        boolean res = iKhuyenMaiDAO.Update_KhuyenMai(mamh, madkm, mucKhuyenMai);
        if(res) System.out.println("");
        else {
        	Boolean  c= iKhuyenMaiDAO.add_CT_KhuyenMai(mamh, madkm, mucKhuyenMai);
        	
        }
	}
	@ModelAttribute("ctdkmo")
	public List<Ctdkm> List_Ctdkm()
	{
		List<Ctdkm> ctdkm =new ArrayList<>();
		return ctdkm;
	}
	
	/*
	 * @RequestMapping("see_promotion_product") public void see(ModelMap
	 * modelMap,@RequestParam(name = "madkm", required = false) int madkm) {
	 * 
	 * List<Ctdkm> ctdkm =new
	 * ArrayList<Ctdkm>(iKhuyenMaiDAO.get_DKM_By_Id(madkm).getCtdkms());
	 * modelMap.addAttribute("ctdkmo",ctdkm); (ctdkm.size());
	 * (madkm);
	 * //(ctdkm.get(0).getId().getMamh());
	 * 
	 * 
	 * }
	 */
	
	/*
	 * @GetMapping("/ctdkm") public List<Ctdkm> getCTDKM(@RequestParam int
	 * id_madkm){ List<Ctdkm> rf = new
	 * ArrayList<Ctdkm>(iKhuyenMaiDAO.get_DKM_By_Id(id_madkm).getCtdkms());
	 * (rf.toString()); return rf; }
	 */
	
}
