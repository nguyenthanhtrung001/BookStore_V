package management.DTO;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import management.dao.IGioHangDAO;
import management.entity.Mathang;


@ComponentScan
public class GioHangDto {
	
	@Autowired
	private IGioHangDAO iGioHangDAO; 
	
	private int idProduct;
	private int soLuong;
	private String size;
	public int getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public GioHangDto() {
		super();
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	
	
	
	public GioHangDto(IGioHangDAO iGioHangDAO, int idProduct, int soLuong, String size) {
		super();
		this.iGioHangDAO = iGioHangDAO;
		this.idProduct = idProduct;
		this.soLuong = soLuong;
		this.size = size;
	}
	
	// Lấy tên mặt hàng từ id mặt hàng
	public String setTenMHString()
	{
		return iGioHangDAO.getTenMatHangFromID(this.getIdProduct());
	}
	
	// Lấy giá theo kiểu int của mặt hàng
	public int get_Price_int()
	{
		return iGioHangDAO.get_Price_From_ID(this.getIdProduct());
	}
	// Lấy giá theo kiểu String của mặt hàng
	public String get_Price_String()
	{
		return String.valueOf(get_Price_int());
	}
	
	// Định dạng cho giá cảu sản phẩm
	public String Format_Of_Price() {
	    try {
	    	String price_tmp = get_Price_String();
	        double price = Double.parseDouble(price_tmp); // Chuyển đổi chuỗi thành số double
	        DecimalFormat df = new DecimalFormat("#,###,###,###"); // Định dạng số theo đơn vị tiền tệ
	        return df.format(price) + " VND";
	    } catch (NumberFormatException e) {
	        // Xử lý nếu chuỗi không thể chuyển đổi thành số
	        return "Không hợp lệ";
	    }
	}
	
	
	public int totalPrice()
	{
		int price_tmp = get_Price_int()*this.getSoLuong();
		return price_tmp;
	}
	
	public int Max_Quantity()
	{
//		return iGioHangDAO.get_Max_SoLuong_CTSize(this.getIdProduct(),this.getSize());
		return iGioHangDAO.get_Max_SoLuong_CTSize(this.getIdProduct(),this.getSize());
	}
	
	public int get_MucGiamGia_Used()
	{
		return (int) iGioHangDAO.getDiscount_Product(this.getIdProduct());
	}
}
