package management.controller.admin;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import management.DTO.ProductDTO;
import management.DTO.SPNhapDto;
import management.bean.ProductWithDiscount;
import management.controller.ReadPython;
import management.dao.IMatHangDao;
import management.dao.INhapHangDao;

import management.entity.Chatlieu;

import management.dao.IThanhToanDAO;

import management.DTO.SPNhapDto;

import management.entity.Ctpn;
import management.entity.CtpnId;
import management.entity.Hinhanhmh;
import management.entity.Loaimh;
import management.entity.Mathang;
import management.entity.Nhacungcap;
import management.entity.Nhanhieu;
import management.entity.Phieunhap;
import management.entity.Size;
import management.factory.ProductFactory;

@Controller
@Transactional
@RequestMapping("/admin")
public class NhapHangController {

	@Autowired
	private INhapHangDao nhapHangDao;

	@Autowired
	private IMatHangDao matHangDao;

	@Autowired
	private ReadPython readPython;
	
	@GetMapping("/")
	public ModelAndView showhome(ModelMap model) {
		List<Mathang> listtmp = matHangDao.getMathangAndTotalSoluongTop(6);
		List<ProductWithDiscount> list_P_Sale = new ArrayList<>();

		for (Mathang mh : listtmp) {

			ProductWithDiscount tmp = new ProductWithDiscount();
			tmp.setMucgiamgia((int) matHangDao.getDiscount_Product(mh));

			tmp.setMathang(mh);
			tmp.setGia(matHangDao.getPrice_Product(mh));

			list_P_Sale.add(tmp);

		}

		model.addAttribute("listProductSale", list_P_Sale);
		ModelAndView mav = new ModelAndView("admin/home");
		return mav;
	}

	@GetMapping("/nhap-hang")
	public ModelAndView show() {
		ModelAndView mav = new ModelAndView("admin/nhapHang");

		// Lấy thông tin từ CSDL
		List<Mathang> dsMatHang = nhapHangDao.layDSMatHang();
		List<Size> dsSize = nhapHangDao.layDSSize();
		List<Nhacungcap> dsNCC = nhapHangDao.layDSNhaCungCap();
		List<Loaimh> dsLoaiSP = nhapHangDao.layDSLoaiSP();
		List<Chatlieu> dsChatLieu = nhapHangDao.layDSChatlieu();
		List<Nhanhieu> dsNhanHieu = nhapHangDao.layDSNhanHieu();

		// Thêm thông tin vào model
		mav.addObject("dsMatHang", dsMatHang);
		mav.addObject("dsNCC", dsNCC);
		mav.addObject("dsSize", dsSize);
		mav.addObject("dsLoaiSP", dsLoaiSP);
		mav.addObject("dsChatLieu", dsChatLieu);
		mav.addObject("dsNhanHieu", dsNhanHieu);

		// Kiểm tra
		System.out.println(dsNCC.get(0).getTenncc());

		return mav;
	}

	@PostMapping("/them-pn")
	public ModelAndView themPN(@RequestBody List<SPNhapDto> dsSPNhap, @RequestParam("ncc") int maNCC,
			HttpServletRequest request) {

		System.out.println("Ma ncc: " + maNCC);

		Date ngayHienTai = new Date();

		Phieunhap pn = new Phieunhap();

		HttpSession session = request.getSession();
		String userEmail = (String) session.getAttribute("loggedInUserEmail");
		
		pn.setNgaynhap(ngayHienTai);
		pn.setNhacungcap(nhapHangDao.layNCC(maNCC));
		pn.setNhanvien(nhapHangDao.layNhanVien(userEmail));
		nhapHangDao.themPhieuNhap(pn);

		List<Ctpn> ctpns = new ArrayList<Ctpn>();

		for (SPNhapDto sp : dsSPNhap) {
			System.out.println("Sp trong pn: " + sp.toString());

			Ctpn tmp = new Ctpn();

//	        Ctsize ctsize = nhapHangDao.layCtsize(sp.getProductID(), sp.getSizeID());

			CtpnId ctpnId = new CtpnId();

			ctpnId.setMapn(pn.getMapn());
			ctpnId.setMamh(sp.getProductID());
			ctpnId.setMasize(sp.getSizeID());

			tmp.setDongia(sp.getGiaNhap() * 1000);
			tmp.setId(ctpnId);
			tmp.setSoluong(sp.getProductQuantity());
//	        tmp.setCtsize(ctsize);

			ctpns.add(tmp);

			// Thêm ctsize nếu như chưa tồn tại để ràng buộc khóa ngoại cho ctpn
			nhapHangDao.themCtSize(sp.getProductID(), sp.getSizeID());

		}

		nhapHangDao.themCtpn(ctpns);

		ModelAndView mav = new ModelAndView("admin/nhapHang");

		return mav;
	}

	@PostMapping("them-sp-moi")
	public ResponseEntity<String> themSPMoi(@ModelAttribute ProductDTO productDTO) throws IOException, InterruptedException {

		Mathang mh = ProductFactory.createProduct(productDTO);

		nhapHangDao.themSPMoi(mh);

		String rp = mh.getMamh() + "_ " + mh.getTenmh();

		return ResponseEntity.ok(rp);
	}

	@PostMapping("them-size-moi")
	public ResponseEntity<String> themSizeMoi(@RequestParam("sizeMoi") String sizeMoi) {

		System.out.println(sizeMoi);

		Size size = new Size();

		size.setTensize(sizeMoi);

		nhapHangDao.themSizeMoi(size);

		String rp = size.getMasize() + "_ " + size.getTensize();
		System.out.println(rp);
		System.out.println(size.toString());

		return ResponseEntity.ok(rp);
	}

	@PostMapping("/them-anh-sp")
	public ResponseEntity<String> themAnhMoi(@RequestParam("mamh") int mamh, @RequestParam("url") String url,
			@RequestParam("tenFile") String tenFile, @RequestParam("token") String token)
			throws UnsupportedEncodingException {

		ProductDTO pro= new ProductDTO();
		Mathang mh = ProductFactory.createProduct(pro);
		mh.setMamh(mamh);

		String url_fb = "https://firebasestorage.googleapis.com/v0/b/webthoitrang-cfe5c.appspot.com/o/images%2Fproducts%2F";
		url = url_fb + tenFile + "?alt=media&token=" + token;

		Hinhanhmh anh = new Hinhanhmh();
		anh.setMathang(mh);
		anh.setDuongdan(url);

		nhapHangDao.themAnhMoi(anh);

		return ResponseEntity.ok("Thành công thêm ảnh mới: " + url);
	}

	@GetMapping("/tmp")
	public ModelAndView tmp() {
		return new ModelAndView("/admin/tmp");
	}

	public double chatLieu_to_double(String tenChatLieu) {
		Double Material = 0D;
		switch (tenChatLieu) {

		case "COTTON":
			Material = 1D;
			break;
		case "KAKI":
			Material = 2/3D;
			break;
		case "JEAN":
			Material = 1/3D;
			break;
		
		default:

			break;
		}

		return Material;
	}

	public double nhanHieu_to_double(String tenNhanHieu) {

		Double ProductBrand = 0D;

		switch (tenNhanHieu) {

		case "LOUIS VUITTON":
			ProductBrand = 1D;
			break;
		case "GUCCI":
			ProductBrand = 0.8D;
			break;
		case "HERMES":
			ProductBrand = 0.6D;
			break;
		case "PRADA":
			ProductBrand = 0.4D;
			break;
		case "CHANEL":
			ProductBrand = 0.2D;
			break;
		default:

			break;
		}
		return ProductBrand;
	}

	public double cachLam_to_double(String cachLam) {
		Double ProductionWay = 0D;
		switch (cachLam) {

		case "Handmade":
			ProductionWay = 1D;
			break;
		case "Machine Made":
			ProductionWay = 0D;
			break;
		default:

			break;
		}

		return ProductionWay;

	}
}
