package management.controller.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import management.dao.ITrangCaNhanDAO;
import management.entity.Khachhang;
@Transactional
@Controller
@RequestMapping("/user/")
public class TrangCaNhanController {
	@Autowired
	private ITrangCaNhanDAO trangCaNhanDAO;

	@PostMapping(value = "profile/Edit")
    public String editKH(HttpServletRequest request, ModelMap model) throws ParseException {
        HttpSession session = request.getSession();
        String userEmail = (String) session.getAttribute("loggedInUserEmail");

        Khachhang khachhang = trangCaNhanDAO.getKhachhangByEmail(userEmail);
        String hotenkh = request.getParameter("hoten"); 
        System.out.println(hotenkh);
        String ngaysinhkh = request.getParameter("ngaysinh");
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date ngaysinhDate = fmt.parse(ngaysinhkh);
        System.out.println(ngaysinhDate);
        String sdtkh = request.getParameter("sdt");
        System.out.println(sdtkh);
        boolean gioitinhkh = Boolean.parseBoolean(request.getParameter("gioitinh"));
        System.out.println(gioitinhkh);
        List<String> errorMessage = new ArrayList<>();
        try {                     
            // Kiểm tra họ tên (họ tên không được chứa ký tự đặc biệt và số)
            if (!hotenkh.matches("^[a-zA-Z\\s]+$")) {
            	errorMessage.add("Họ tên không hợp lệ");
            }
            
            // Kiểm tra ngày sinh (nhỏ hơn ngày hiện tại và đủ 18 tuổi)
            LocalDate ngaySinh = ngaysinhDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate currentDate = LocalDate.now();
            LocalDate eighteenYearsAgo = currentDate.minusYears(18);
            
            if (ngaySinh.isAfter(currentDate) || ngaySinh.isAfter(eighteenYearsAgo)) {
            	errorMessage.add("Ngày sinh không hợp lệ.");
            }
           
            // Kiểm tra số điện thoại (số điện thoại gồm 10 chữ số)
            if (!sdtkh.matches("^\\d{10}$")) {
            	errorMessage.add("Số điện thoại không hợp lệ.");
            }
            
            if (errorMessage.isEmpty()) {
            	model.addAttribute("khachhang", khachhang);
            	model.addAttribute("errorMessage", errorMessage);
                return "user/profile";                
            }
            
            khachhang.setHotenkh(hotenkh);
            khachhang.setGioitinh(gioitinhkh);
            khachhang.setSdt(sdtkh);
            khachhang.setNgaysinh(ngaysinhDate);

            Integer temp = trangCaNhanDAO.updateKhachhang(khachhang);

            if (temp != 0) {
                model.addAttribute("successMessage", "Cập nhật thành công!");
            } else {
                model.addAttribute("errorMessage", "Cập nhật thất bại!");
            }
            model.addAttribute("khachhang", khachhang);
        } catch (Exception e) {
            model.addAttribute("khachhang", khachhang);
            model.addAttribute("errorMessage", "Lỗi!");
            return "user/trangcanhan";
        }
        return "user/trangcanhan";
    }

	@GetMapping("profile")
	public ModelAndView LoadTTCaNhan(HttpServletRequest request, ModelMap model) {
		
	    HttpSession session = request.getSession();
	    String userEmail = (String) session.getAttribute("loggedInUserEmail");
				
		Khachhang khachhang = trangCaNhanDAO.getKhachhangByEmail(userEmail);
		model.addAttribute("khachhang", khachhang);
		ModelAndView modelAndView = new ModelAndView("user/trangcanhan");

		return modelAndView;
	}
}
