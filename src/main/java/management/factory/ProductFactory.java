package management.factory;

import management.entity.Mathang;
import management.DTO.ProductDTO;
import management.entity.Book;
import management.entity.DefaultMathang;
import management.entity.Pen;
import management.entity.Stationery;

public class ProductFactory {
    
    public static Mathang createProduct(ProductDTO product) {
        if ("book".equalsIgnoreCase(product.getLoaiSP())) {
            return new Book(product);
        } else if ("pen".equalsIgnoreCase(product.getLoaiSP())) {
            return new Pen(product);
        }else if ("stationery".equalsIgnoreCase(product.getLoaiSP())) {
            return new Stationery(product);
        }else {
        	return new DefaultMathang(product);
        }
    }
        
}
