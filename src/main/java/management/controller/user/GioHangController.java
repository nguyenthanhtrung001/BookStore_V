package management.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import management.DTO.GioHangDto;
import management.DTO.Product_Paying;
import management.DTO.ThanhToanDto;
import management.dao.IDonHangDao;
import management.dao.IGioHangDAO;
import management.entity.Mathang;
@Transactional
@Controller
@RequestMapping("user/")
public class GioHangController {

	@Autowired
	IGioHangDAO iGioHangDAO;

	@Autowired
	IDonHangDao donHangDao;

	/*
	 * @RequestMapping("/chi-tiet-sp/{id}") public ModelAndView
	 * CTSP(@PathVariable("id") int id) throws ServletException, IOException {
	 * ModelAndView mav = new ModelAndView("user/chiTietSP"); Mathang mh =
	 * donHangDao.layMatHangTheoID(id); int gia = donHangDao.LayGiaSP(id);
	 * mav.addObject("gia", gia);
	 * 
	 * mav.addObject("mh", mh);
	 * 
	 * return mav; }
	 * 
	 * @PostMapping("/chi-tiet-sp/them") public void themSanPhamVaoGioHang(Model
	 * model, @RequestParam("id") int id, @RequestParam("size") String size,
	 * HttpServletResponse response) { // Đoạn mã xử lý thêm sản phẩm vào giỏ hàng ở
	 * đây String maSPTmp = "productID" + String.valueOf(id) + "+" + size;
	 * 
	 * Cookie cookie = new Cookie(maSPTmp, "1"); // Đặt path của cookie
	 * cookie.setPath("/"); // Đặt path chung cho tất cả các đường dẫn
	 * response.addCookie(cookie);
	 * 
	 * cookie.setMaxAge(60 * 60 * 24 * 30); // 1 tháng
	 * 
	 * }
	 * 
	 * @RequestMapping("products") protected String doGet(HttpServletRequest
	 * request, HttpServletResponse response) throws ServletException, IOException {
	 * // Tạo các cookie Cookie cookie1 = new Cookie("productID1+S", "1"); Cookie
	 * cookie2 = new Cookie("productID1+M", "1"); Cookie cookie3 = new
	 * Cookie("productID1+L", "1"); Cookie cookie4 = new Cookie("productID1+XL",
	 * "1"); Cookie cookie5 = new Cookie("productID2+S", "1"); Cookie cookie6 = new
	 * Cookie("productID2+M", "1"); Cookie cookie7 = new Cookie("productID2+L",
	 * "1"); Cookie cookie8 = new Cookie("productID2+XL", "1"); Cookie cookie9 = new
	 * Cookie("productID3+S", "1"); Cookie cookie10 = new Cookie("productID3+M",
	 * "1"); Cookie cookie11= new Cookie("productID3+L", "1"); Cookie cookie12 = new
	 * Cookie("productID3+XL", "1"); Cookie cookie13 = new Cookie("productID4+S",
	 * "1"); Cookie cookie14 = new Cookie("productID4+M", "1"); Cookie cookie15 =
	 * new Cookie("productID4+L", "1"); Cookie cookie16 = new
	 * Cookie("productID4+XL", "1"); cookie1.setPath("/"); // Đặt path chung cho tất
	 * cả các đường dẫn cookie2.setPath("/"); // Đặt path chung cho tất cả các đường
	 * dẫn cookie3.setPath("/"); // Đặt path chung cho tất cả các đường dẫn
	 * cookie4.setPath("/"); // Đặt path chung cho tất cả các đường dẫn
	 * cookie5.setPath("/"); // Đặt path chung cho tất cả các đường dẫn
	 * cookie6.setPath("/"); // Đặt path chung cho tất cả các đường dẫn
	 * cookie7.setPath("/"); // Đặt path chung cho tất cả các đường dẫn
	 * cookie8.setPath("/"); // Đặt path chung cho tất cả các đường dẫn
	 * cookie9.setPath("/"); // Đặt path chung cho tất cả các đường dẫn
	 * cookie10.setPath("/"); // Đặt path chung cho tất cả các đường dẫn
	 * cookie11.setPath("/"); // Đặt path chung cho tất cả các đường dẫn
	 * cookie12.setPath("/"); // Đặt path chung cho tất cả các đường dẫn
	 * cookie13.setPath("/"); // Đặt path chung cho tất cả các đường dẫn
	 * cookie14.setPath("/"); // Đặt path chung cho tất cả các đường dẫn
	 * cookie15.setPath("/"); // Đặt path chung cho tất cả các đường dẫn
	 * cookie16.setPath("/"); // Đặt path chung cho tất cả các đường dẫn
	 * 
	 * 
	 * // Đặt thời gian sống cho các cookie (tùy chọn)
	 * 
	 * 
	 * cookie1.setMaxAge(60 * 60 * 24); cookie2.setMaxAge(60 * 60 * 24);
	 * cookie3.setMaxAge(60 * 60 * 24); cookie4.setMaxAge(60 * 60 * 24);
	 * cookie5.setMaxAge(60 * 60 * 24); cookie6.setMaxAge(60 * 60 * 24);
	 * cookie7.setMaxAge(60 * 60 * 24); cookie8.setMaxAge(60 * 60 * 24);
	 * cookie9.setMaxAge(60 * 60 * 24); cookie10.setMaxAge(60 * 60 * 24);
	 * cookie11.setMaxAge(60 * 60 * 24); cookie12.setMaxAge(60 * 60 * 24);
	 * cookie13.setMaxAge(60 * 60 * 24); cookie14.setMaxAge(60 * 60 * 24);
	 * cookie15.setMaxAge(60 * 60 * 24); cookie16.setMaxAge(60 * 60 * 24);
	 * response.addCookie(cookie1); response.addCookie(cookie2);
	 * response.addCookie(cookie3); response.addCookie(cookie4);
	 * response.addCookie(cookie5); response.addCookie(cookie6);
	 * response.addCookie(cookie7); response.addCookie(cookie8);
	 * response.addCookie(cookie9); response.addCookie(cookie10);
	 * response.addCookie(cookie11); response.addCookie(cookie12);
	 * response.addCookie(cookie13); response.addCookie(cookie14);
	 * response.addCookie(cookie15); response.addCookie(cookie16);
	 * 
	 * 
	 * 
	 * // Gửi thông báo thành công
	 * response.getWriter().write("Các cookie đã được tạo thành công!");
	 * 
	 * return "redirect:giohang"; }
	 */

	@RequestMapping("giohang")
	public ModelAndView createGioHangDto(HttpServletRequest request, HttpServletResponse response) {

		List<GioHangDto> gioHangDto = new ArrayList<GioHangDto>();
		ModelAndView modelAndView = new ModelAndView("user/cart");
		Cookie[] cookies = request.getCookies();

		if (cookies == null)
			System.out.println("Cookie null r");
		if (cookies != null) {

			// Tạo một danh sách lưu trữ các cookie có tên bắt đầu bằng "productID"
			// và giá trị là chuỗi gồm 2 số nguyên được ngăn cách bởi dấu ","

			for (Cookie cookie : cookies) {

				if (cookie.getName().startsWith("productID")) {

					// Lấy name của cookie là productId

					String cookieName = cookie.getName();
					System.out.println("Cookie null r"+cookieName);
					// Tìm vị trí của "productID" trong chuỗi
					int indexOfProductID = cookieName.indexOf("productID");
					if (indexOfProductID != -1) {
						try {
							// Lấy phần từ sau "productID" đến cuối chuỗi
							String value = cookieName.substring(indexOfProductID + "productID".length()); // Sẽ lấy
																											// "1+SM"
							// Bây giờ bạn có thể tách "1" và "SM" từ giá trị này.
							String[] parts = value.split("\\+");
							int firstPart = 0; // id
							String secondPart = ""; // size
							if (parts.length == 2) {
								firstPart = Integer.parseInt(parts[0]); // id
								secondPart = parts[1]; // size
							}

							GioHangDto gioHangDto2 = new GioHangDto(iGioHangDAO, firstPart, 1, secondPart); // soluong
																											// auto la 1

							gioHangDto.add(gioHangDto2);

						} catch (NumberFormatException e) {
							e.printStackTrace();
							System.out.println("62 - GioHang Controller");
						}

					}

				}

			}

		}

		modelAndView.addObject("GioHang", gioHangDto);
		return modelAndView;
	}

	// Xử lí remove cookie khi sản phẩm bị xóa khỏi giỏ hàng
	@PostMapping("delete")
	public String processPayment(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("idProductToDelete") int idProductToDelete,
			@RequestParam("sizeToDelete") String sizeToDelete) {

		// ("Id Product to Delete: " + idProductToDelete);
		// ("Size to Delete: " + sizeToDelete);
		// ("productID"+String.valueOf(idProductToDelete)+sizeToDelete);
		Cookie[] cookies = request.getCookies();
		
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("productID" + String.valueOf(idProductToDelete) + "+" + sizeToDelete)) {
					// ("Xoa duoc r");

					cookie.setPath("/");
					// Đặt thời gian sống của cookie thành 0 để xóa nó
					cookie.setMaxAge(0);

					// Thêm cookie đã được đặt lại thời gian sống vào đối tượng HttpServletResponse
					response.addCookie(cookie);
				}
			}
		}
		return "home";

	}

	// trả về url tạm
	@RequestMapping("home")
	public String temp() {
		return "Promotion";
	}

	// Lấy danh sách id và số lượng được chọn ở giỏ hàng
//	@PostMapping("paying")
//	public String handlePayment(@RequestParam("selectedProducts") String selectedProductsJSON, Model model) {
//		ObjectMapper objectMapper = new ObjectMapper();
//		try {
//		    // Phân tích chuỗi JSON thành danh sách đối tượng Product_Paying
//		    List<Product_Paying> selectedProducts = objectMapper.readValue(selectedProductsJSON,
//		            new TypeReference<List<Product_Paying>>() {});
//		    (selectedProducts.size()+"sizejk");
//		    for(Product_Paying product_Paying : selectedProducts)
//		    {
//		    	(product_Paying.getIdProduct());
//		    }
//		    List<ThanhToanDto> list_dssp = new ArrayList<>();
//		    // Duyệt qua danh sách sản phẩm và truy cập idProduct, quantity, và size
//		    for (Product_Paying product : selectedProducts) {
//		        ThanhToanDto thanhToanDto = new ThanhToanDto(product.getQuantity(), product.getSize(), product.getUnitPrice(), product.getTotalPrice());
//		        list_dssp.add(thanhToanDto);
//		    }
//
//		    for (ThanhToanDto x : list_dssp) {
//		        (x.getSize());
//		    }
//
//		    model.addAttribute("dssp", list_dssp);
//
//		} catch (Exception e) {
//		    e.printStackTrace();
//		}
//			
////		try {
////            // Khởi tạo ObjectMapper để phân tích chuỗi JSON
////            ObjectMapper objectMapper = new ObjectMapper();
////            (selectedProductsJSON);
////            List<ThanhToanDto> list_dssp = new ArrayList<>();
////            // Phân tích chuỗi JSON thành một JsonNode (cấu trúc dữ liệu JSON)
////            JsonNode jsonNode = objectMapper.readTree(selectedProductsJSON);
////
////            // Kiểm tra xem JsonNode có phải là một mảng (array) không
////            if (jsonNode.isArray()) {
////            	(jsonNode.size());
////                // Duyệt qua từng phần tử trong mảng và in ra
////                for (JsonNode item : jsonNode) {
////                	int id = item.get("idProduct").asInt();
////                	int soluong = item.get("quantity").asInt();
////                	String sizeString = item.get("size").asText();
////                	int dongia = item.get("unitPrice").asInt();
////                	int thanhtien = item.get("totalPrice").asInt();
////                	
////                	
////                    (item.toString());
////                    (id);
////                    ThanhToanDto thanhToanDto = new ThanhToanDto(soluong,sizeString,dongia,thanhtien);
////                	list_dssp.add(thanhToanDto);
////                }
////            }
////            
////            model.addAttribute("dssp", list_dssp);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//		return"home";
//}
	

}