package management.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import management.DTO.DanhGiaDto;
import management.bean.Donhang;
import management.bean.DonhangInfo;
import management.dao.ILichSuDonHangDAO;
import management.entity.Danhgia;
import management.entity.DanhgiaId;
import management.entity.Mathang;
import management.entity.Phieudat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Transactional
@Controller
@RequestMapping("/user")
public class DanhGiaController {

	@Autowired
	private ILichSuDonHangDAO lichSuDonHangDAO;

	@RequestMapping("/danhGia/{mapd}")
	public ModelAndView rateProduct(@PathVariable("mapd") int mapd, HttpServletRequest request) {
		// Lấy thông tin phiên đăng nhập của người dùng
		HttpSession session = request.getSession();
		String userEmail = (String) session.getAttribute("loggedInUserEmail");

		// Lấy mã khách hàng dựa trên địa chỉ email
		int makh = lichSuDonHangDAO.getMaKHbyEmail(userEmail);

		Phieudat phieudat = lichSuDonHangDAO.getPhieuDat(mapd);

		// Lấy danh sách các mã sản phẩm dựa trên mã phiếu đặt
		List<Integer> listMaSP = lichSuDonHangDAO.getAllMaSPbyMaPD(mapd);
		List<Danhgia> dadanhgialist = new ArrayList<>();
		List<Danhgia> chuadanhgialist = new ArrayList<>();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());

		// Lặp qua danh sách mã sản phẩm và lấy số sao đánh giá tương ứng
		for (int masp : listMaSP) {
			int danhgia = lichSuDonHangDAO.getDanhgia(masp, userEmail);
			Danhgia rating = new Danhgia();
			DanhgiaId ratingid = new DanhgiaId();
			ratingid.setMamh(masp);
			ratingid.setTentk(userEmail);
			rating.setId(ratingid);
			ratingid.setMamh(masp);
			ratingid.setTentk(userEmail);
			rating.setId(ratingid);
			String listRating = makh + "," + masp + "," + danhgia + "," + timeStamp;
			if (danhgia != 0) {
				rating.setDanhgia(danhgia);
				dadanhgialist.add(rating);
				String tmp = saveRatingRecord(listRating);
			} else {
				chuadanhgialist.add(rating);
			}
		}

		ModelAndView modelAndView = new ModelAndView("user/danhgia");
		modelAndView.addObject("phieudat", phieudat);
		modelAndView.addObject("dadanhgialist", dadanhgialist);
		modelAndView.addObject("chuadanhgialist", chuadanhgialist);

		return modelAndView;
	}

	@PostMapping("/rated")
	@ResponseBody
	public String rateProduct(HttpServletRequest request, ModelMap model, @RequestBody List<DanhGiaDto> danhGiaData) {

		// Lấy thông tin phiên đăng nhập của người dùng
		HttpSession session = request.getSession();
		String userEmail = (String) session.getAttribute("loggedInUserEmail");
		
		// Lấy mã khách hàng dựa trên địa chỉ email
		int makh = lichSuDonHangDAO.getMaKHbyEmail(userEmail);
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());

		try {
			for (DanhGiaDto danhgia : danhGiaData) {
				int mamh = danhgia.getMamh();
				int danhgiaValue = danhgia.getDanhgia();
				String listRating = makh + "," + mamh + "," + danhgiaValue + "," + timeStamp;
				
				// Kiểm tra xem sản phẩm đã được đánh giá chưa bằng cách truy vấn cơ sở dữ liệu
				boolean isProductRated = lichSuDonHangDAO.isProductRated(mamh, userEmail);

				boolean saveResult;

				if (isProductRated) {
					// Nếu sản phẩm đã được đánh giá trước đó, thực hiện cập nhật đánh giá
					saveResult = lichSuDonHangDAO.updateRating(mamh, userEmail, danhgiaValue);
					String tmp = saveRatingRecord(listRating);
				} else {
					// Nếu sản phẩm chưa được đánh giá, thực hiện thêm bản ghi mới
					saveResult = lichSuDonHangDAO.saveRating(mamh, userEmail, danhgiaValue);
					String tmp = saveRatingRecord(listRating);
				}

			}
			return "Success";
		} catch (Exception e) {
			return "Error";
		}
	}

	public String saveRatingRecord(String red) {
		String s = null;
		String str = null;
		try {

			// run the Unix "ps -ef" command
			// using the Runtime exec method:

			String cmd = "python D:\\GitHub\\WebThoiTrang\\WebThoiTranng\\src\\main\\java\\python\\add-to-csv.py "
					+ red;
			Process p = Runtime.getRuntime().exec(cmd);

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

			// read the output from the command
			System.out.println("Here is the standard output of the command:\n");
			while ((s = stdInput.readLine()) != null) {
				System.out.println(s);

				str = s;
			}

		} catch (IOException e) {
			System.out.println("exception happened - here's what I know: ");
			e.printStackTrace();
			// System.exit(-1);
		}
		return str;

	}
}
