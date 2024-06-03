package management.dao;




public interface IGioHangDAO {
	public String getTenMatHangFromID(int id);
	public int get_Price_From_ID(int idProduct);
	public int get_Max_SoLuong_CTSize(int idproduct,String size);
	public double getDiscount_Product(int mamh);
}
