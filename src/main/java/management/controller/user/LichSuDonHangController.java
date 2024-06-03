package management.controller.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import management.bean.Donhang;
import management.bean.DonhangInfo;
import management.dao.ILichSuDonHangDAO;
import management.entity.Mathang;
import management.entity.Phieudat;
@Transactional
@Controller
@RequestMapping("/user/")
public class LichSuDonHangController {

	@Autowired
	private ILichSuDonHangDAO lichSuDonHangDAO;

	@GetMapping("history")
	public ModelAndView history(HttpServletRequest request, ModelMap model) {

		// Lấy thông tin phiên đăng nhập của người dùng
		HttpSession session = request.getSession();
		String userEmail = (String) session.getAttribute("loggedInUserEmail");

		// Lấy mã khách hàng dựa trên địa chỉ email
		int makh = lichSuDonHangDAO.getMaKHbyEmail(userEmail);

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());

		// Tạo danh sách chứa thông tin đơn hàng
		List<DonhangInfo> listDonhang = new ArrayList<>();

		// Lấy danh sách các phiếu đặt dựa trên mã khách hàng
		List<Phieudat> listPhieuDat = lichSuDonHangDAO.getAllPhieuDatByMaKH(makh);

		// Duyệt qua các phiếu đặt để lấy thông tin
		for (Phieudat phieudat : listPhieuDat) {
			DonhangInfo donhangInfo = new DonhangInfo();
			donhangInfo.setPhieudat(phieudat);

			// Tạo danh sách đơn hàng dựa trên phiếu đặt
			List<Donhang> listDonhangForPhieuDat = new ArrayList<>();

			List<Integer> mapdList = new ArrayList<>();
			List<Integer> maspList = new ArrayList<>();
			List<Integer> masizeList = new ArrayList<>();

			// Lấy danh sách các mã sản phẩm dựa trên mã phiếu đặt
			List<Integer> listMaSP = lichSuDonHangDAO.getAllMaSPbyMaPD(phieudat.getMapd());

			// Duyệt qua danh sách mã sản phẩm
			for (int masp : listMaSP) {
				List<Integer> listMaSize = lichSuDonHangDAO.getAllMaSizebyMaSPandMaPD(masp, phieudat.getMapd());
				int danhgia = lichSuDonHangDAO.getDanhgia(masp, userEmail);
				String listRating = makh + "," + masp + "," + danhgia + "," + timeStamp;
				String tmp = saveRatingRecord(listRating);

				// Duyệt qua danh sách mã size
				for (int masize : listMaSize) {
					// Thêm giá trị vào danh sách tương ứng
					mapdList.add(phieudat.getMapd());
					maspList.add(masp);
					masizeList.add(masize);
				}
			}

			// Duyệt qua danh sách 'mapdList', 'maspList', và 'masizeList'
			for (int i = 0; i < mapdList.size(); i++) {
				int mapd = mapdList.get(i);
				int masp = maspList.get(i);
				int masize = masizeList.get(i);

				// Lấy thông tin sản phẩm, ngày đặt, size, giá và khuyến mãi
				Mathang mh = lichSuDonHangDAO.layMatHangTheoID(masp);
				int sl = lichSuDonHangDAO.getSoluongSp(masp, mapd, masize);
				Date ngaydat = lichSuDonHangDAO.getNgaydatByMaMH(mapd);
				int gia = lichSuDonHangDAO.getPriceByMaMH(masp, ngaydat);
				String size = lichSuDonHangDAO.getMucSizebyMaSize(masize);
				double khuyenmai = lichSuDonHangDAO.getKhuyenMai(masp, ngaydat);
				int danhgia = lichSuDonHangDAO.getDanhgia(masp, userEmail);

				// Tạo đối tượng Donhang và thêm vào danh sách
				Donhang dh = new Donhang();
				dh.setMamh(masp);
				dh.setTenSP(mh.getTenmh());
				dh.setSoluong(sl);
				dh.setTonggia(gia * sl);
				dh.setNgaydat(ngaydat);
				dh.setSize(size);
				dh.setMucgiamgia(khuyenmai);
				dh.setDanhgia(danhgia);

				listDonhangForPhieuDat.add(dh);
			}

			donhangInfo.setListDonhangForPhieuDat(listDonhangForPhieuDat);
			listDonhang.add(donhangInfo);
		}

		ModelAndView modelAndView = new ModelAndView("user/lichsudonhang");
		modelAndView.addObject("listDonhang", listDonhang);
		return modelAndView;
	}

	public String saveRatingRecord(String red) {
		String s = null;
		String str = null;
		try {

			// run the Unix "ps -ef" command
			// using the Runtime exec method:

			String cmd = "python D:\\HK7\\PhatTrienHeThongThongMinh\\WebThoiTrang_final\\WebThoiTranng\\src\\main\\java\\python\\add-to-csv.py "
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
