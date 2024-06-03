package management.dao;

import java.util.List;

import management.entity.Banggia;
import management.entity.Mathang;

public interface IMatHangDao {
	public List<Mathang> getAllMathang();
	public List<Mathang> getProductOfCategory(String category);
	public List<Mathang> getProductOfCategoryName(String nameCategory);
	public List<Mathang> searchProductsLike(String input);
	public List<Mathang> getMathangAndTotalSoluongTop(int top);
	public List<Mathang> getProductHasDiscount(int top);
	public int getPrice_Product(Mathang product);
	public double getDiscount_Product(Mathang product);
	public boolean updatePrice(Banggia banggia);
	public List<Mathang> getMathangByPage(int pageNumber,String title,String search, String category);
	
	public List<Mathang> getMathangByPage_Nhan(int pageNumber,String title,String search, String category, String nhan);
	
	
}
