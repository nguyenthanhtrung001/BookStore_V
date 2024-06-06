package management.controller.user;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import management.bean.ProductWithDiscount;
import management.dao.IDanhMucDao;
import management.dao.IMatHangDao;
import management.entity.Loaimh;
import management.entity.Mathang;

@Controller
@RequestMapping("/product")

public class Product_H_S_Controller {

	@Autowired
	private IMatHangDao matHangDao;
	@Autowired
	private IDanhMucDao danhMucDao;

	@GetMapping("/{a}")
	public ModelAndView productPage(ModelMap model, HttpSession ss,
			@RequestParam(name = "search", required = false) String input,
			@RequestParam(name = "category", required = false) String category,
			@PathVariable(required = false) String a, @RequestParam(name = "page", defaultValue = "1") int page) {

		List<Loaimh> list0 = danhMucDao.getAllDanhMuc();
		model.addAttribute("listOfCategories", list0);
		String urlText = "";
		String key = "";
		String value = "";
		
		System.out.println(input);
		
		if (a.equals("") == false) {

				List<Mathang> listProduct = new ArrayList<>();

				if (a.equals("search")) {
					
				listProduct = matHangDao.getMathangByPage(page, "search", input, "");
				System.out.println("-----777777------");
				
				for( Mathang mh : listProduct)
				{
					System.out.println(mh.getTenmh());
				}
					
					urlText = "Tìm Kiếm";
					key = "search";
					value = input;
	
				} else if (a.equals("search")) {

				listProduct = matHangDao.getMathangByPage(page, "search", input, "");
				urlText = "Tìm Kiếm";
				key = "search";
				value = input;
			}

			if (a.equals("all")) {

				listProduct = matHangDao.getMathangByPage(page, "all", "", "");
				urlText = "Tất Cả Sản Phẩm";
				key = "all";

			}

			if (a.equals("discount")) {

				listProduct = matHangDao.getMathangByPage(page, "discount", "", "");
				urlText = "Sản Phẩm Đang Giảm Giá";
				key = "discount";

			}

			if (a.equals("selling")) {

				listProduct = matHangDao.getMathangByPage(page, "selling", "", "");
				urlText = "Sản Phẩm Bán Chạy";
				key = "selling";
			}
			if (a.equals("category")) {

				listProduct = matHangDao.getMathangByPage(page, "category", "", category);
				key = "category";
				value = category;

			}

			if (a.equals("books")) {

				listProduct = matHangDao.getMathangByPage(page, "books", "", "");
				urlText = "Sách";
				key = "books";

			}
			if (a.equals("pens")) {

				listProduct = matHangDao.getMathangByPage(page, "pens", "", "");
				urlText = "Bút/ Viết";
				key = "pens";

			}
			if (a.equals("stationerys")) {

				listProduct = matHangDao.getMathangByPage(page, "stationerys", "", "");
				urlText = "Tập/ Vở";
				key = "stationerys";

			}
			if (a.equals("defaults")) {

				listProduct = matHangDao.getMathangByPage(page, "defaults", "", "");
				urlText = "Văn phòng phẩm khác";
				key = "defaults";

			}

			List<ProductWithDiscount> emptyList = new ArrayList<>();

			List<ProductWithDiscount> list_Product_full = new ArrayList<>();

			int productForPage = 4;

			if (!listProduct.isEmpty()) {

				for (Mathang mh : listProduct) {
					ProductWithDiscount tmp = new ProductWithDiscount();

					tmp.setMucgiamgia((int) matHangDao.getDiscount_Product(mh));
					tmp.setMathang(mh);
					tmp.setGia(matHangDao.getPrice_Product(mh));

					list_Product_full.add(tmp);

				}

				if (a.equals("category")) {

					urlText = list_Product_full.get(0).getMathang().getLoaimh().getTenloaimh();

				}

				model.addAttribute("nameCategory", urlText);
				model.addAttribute("listProduct", list_Product_full);
				model.addAttribute("key", key);
				model.addAttribute("value", value);
				model.addAttribute("numpage", page);
				if (key != null || !key.equals("")) {
					ss.setAttribute("keyurl", key);
					ss.setAttribute("valueurl", value);
				}

			} else {
				model.addAttribute("nameCategory", "");
				model.addAttribute("listProduct", emptyList);
			}

		}

		ModelAndView modelAndView = new ModelAndView("user/product");
		return modelAndView;
	}

}
