package management.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import management.dao.ITaiKhoanDAO;

public class UserInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	ITaiKhoanDAO iTaiKhoanDAO;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestURI = request.getRequestURI();

		String usermail = (String) request.getSession().getAttribute("loggedInUserEmail");
		System.out.println("userMail: " + usermail);
		System.out.println("UserInterceptor");
		// Nếu chưa đăng nhập mà mau hàng
		if ((requestURI.contains("paying") && (Boolean) request.getSession().getAttribute("login") == false)
				|| (requestURI.contains("mua-ngay") && (Boolean) request.getSession().getAttribute("login") == false)) {

			response.sendRedirect("/user/login"); // trả về link đăng nhập
			return false; // Dừng xử lý yêu cầu hiện tại
		}
		// Nếu đang đăng nhập bằng admin mà url có chứa paying
		if ((requestURI.contains("paying") && iTaiKhoanDAO.get_MaQuyen_by_email(usermail) == 2)
				|| (requestURI.contains("mua-ngay") && iTaiKhoanDAO.get_MaQuyen_by_email(usermail) == 2)) {

			response.sendRedirect("/user/login"); // trả về link đăng nhập
			return false; // Dừng xử lý yêu cầu hiện tại
		}
		
		// Nếu đăng nhập là admin mà url chứa user//
		/*
		 * if (iTaiKhoanDAO.get_MaQuyen_by_email(usermail) == 2 &&
		 * requestURI.contains("user") ) { response.sendRedirect("/user/login"); // trả
		 * về link đăng nhập return false; // Dừng xử lý yêu cầu hiện tại }
		 */
		return true; // Tiếp tục xử lý yêu cầu
	}
}