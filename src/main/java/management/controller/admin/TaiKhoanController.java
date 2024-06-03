package management.controller.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;

import org.hibernate.jpa.criteria.predicate.ImplicitNumericExpressionTypeDeterminer;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import management.DTO.GioHangDto;
import management.bean.Validator_check;
import management.dao.IGioHangDAO;
import management.dao.IKhachHangDAO;
import management.dao.ITaiKhoanDAO;
import management.entity.Khachhang;
import management.entity.Mathang;
import management.entity.Quyen;
import management.entity.Taikhoan;
@Transactional
@Controller
@RequestMapping("/register/")
public class TaiKhoanController {

	@Autowired
	ITaiKhoanDAO taiKhoanDAO;

	@Autowired
	IGioHangDAO gioHangDAO;

	@Autowired
	IKhachHangDAO iKhachHangDAO;

	// Tạo một model Khach Hang mới để View có thể hiểu
	@ModelAttribute("KhachHang")
	public Khachhang createKhachHangModel() {
		return new Khachhang();
	}

	// Method Get để chuyển đến view "register"
	@RequestMapping(value = "insert", method = RequestMethod.GET)
	public String insert1(HttpServletRequest request) {

		// Lấy giá trị userEmail từ session
		HttpSession session = request.getSession();
		String userEmail = (String) session.getAttribute("loggedInUserEmail");

		// Ktra neu email chua ton tai thi chuyen huong den trang dang ki tai khoan
		if (taiKhoanDAO.check_MailExist(userEmail) == false) {
			
			return "register";
		}
		if(taiKhoanDAO.get_MaQuyen_by_email(userEmail)==2) return "redirect:/admin/";
		// return "redirect:/register/cookie";
		else return "redirect:/user/index";
		//return "user/home";
	}

	// Method Post để xử lý dữ liệu của view "register" từ biểu mẫu
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public String insert2(ModelMap model, @Validated @ModelAttribute("KhachHang") Khachhang khachHang,
			HttpServletRequest request, BindingResult errors, HttpSession ss,
			@ModelAttribute("dstaikhoan") Taikhoan taiKhoan) {
		

		String ngay = request.getParameter("day");
		String thang = request.getParameter("month");
		String nam = request.getParameter("year");
		String sDate = thang + "/" + ngay + "/" + nam;
		Date date = null;
		try {
			date = new SimpleDateFormat("MM/dd/yyyy").parse(sDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		khachHang.setNgaysinh(date);
		LocalDate dateOfBirth = khachHang.getNgaysinh().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int minimumAge = 12;
		boolean kt = true;
		if (!Validator_check.isMinimumAge(dateOfBirth, minimumAge)) {
			
			errors.rejectValue("name", "KhachHang", "Bạn phải lớn hơn " + minimumAge + " tuổi để đăng ký!");
			kt = false;
		}

		if (khachHang.getHotenkh().trim().isEmpty()) {
			errors.rejectValue("name", "KhachHang", "Họ và tên không được để trống!");
			kt = false;
		}
		/*
		 * if (khachHang.getDiachi().trim().isEmpty()) {
		 * errors.rejectValue("account.password", "KhachHang",
		 * "Địa chỉ không được để trống!"); kt = false; }
		 */
		if (khachHang.getSdt().trim().isEmpty()) {
			errors.rejectValue("phoneNumber", "KhachHang", "Số điện thoại không được để trống!");
			kt = false;
		}
		if (!Validator_check.isValidPhoneNumber(khachHang.getSdt())) {
			errors.rejectValue("phoneNumber", "KhachHang", "Số điện thoại không hợp lệ!");
			kt = false;
		}

		

		Taikhoan taikhoan = new Taikhoan();
		iKhachHangDAO.createCustomer(khachHang, taikhoan, request);
		
		return "redirect:/user/index";
	}

}
