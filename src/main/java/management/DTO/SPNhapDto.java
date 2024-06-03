package management.DTO;

public class SPNhapDto {

	private Integer productID;
    private String productName;
    private Integer sizeID;
    private String productSize;
    private Integer giaNhap;
    private Integer productQuantity;
    
    
    
	public SPNhapDto() {
		
	}

	public SPNhapDto(Integer productID, String productName, Integer sizeID, String productSize, Integer giaNhap,
			Integer productQuantity) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.sizeID = sizeID;
		this.productSize = productSize;
		this.giaNhap = giaNhap;
		this.productQuantity = productQuantity;
	}

	public Integer getProductID() {
		return productID;
	}

	public void setProductID(Integer productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getSizeID() {
		return sizeID;
	}

	public void setSizeID(Integer sizeID) {
		this.sizeID = sizeID;
	}

	public String getProductSize() {
		return productSize;
	}

	public void setProductSize(String productSize) {
		this.productSize = productSize;
	}

	public Integer getGiaNhap() {
		return giaNhap;
	}

	public void setGiaNhap(Integer giaNhap) {
		this.giaNhap = giaNhap;
	}

	public Integer getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(Integer productQuantity) {
		this.productQuantity = productQuantity;
	}

	@Override
	public String toString() {
		return "SPNhapDto [productID=" + productID + ", productName=" + productName + ", sizeID=" + sizeID
				+ ", productSize=" + productSize + ", giaNhap=" + giaNhap + ", productQuantity=" + productQuantity
				+ "]";
	}
    
    
}
