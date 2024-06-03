package management.assistance;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Cookie_Product extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy ID sản phẩm từ tham số yêu cầu (hoặc từ bất kỳ nguồn nào khác)
        int productID = Integer.parseInt(request.getParameter("productID"));
        
        // Tạo một cookie chứa ID sản phẩm
        Cookie productCookie = new Cookie("productID", String.valueOf(productID));
        
        // Đặt thời gian sống của cookie (nếu cần)
         productCookie.setMaxAge(3600); // Ví dụ: Cookie sống trong 1 giờ
        
        // Thêm cookie vào phản hồi
        response.addCookie(productCookie);
        
        // Chuyển hướng hoặc hiển thị thông báo thành công
        response.sendRedirect("product.jsp"); // Ví dụ: Chuyển hướng đến trang sản phẩm
    }
}