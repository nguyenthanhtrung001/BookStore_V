package management.facade;

import java.util.HashMap;
import java.util.Map;


import management.entity.Book;
import management.entity.Mathang;
import management.entity.Pen;
import management.entity.Stationery;

public class ProductFacade {
	private final Map<Integer, Mathang> products = new HashMap<>();

    public void addProduct(Mathang mathang) {
       
                products.put(mathang.getMamh(), mathang);
                
        
    }

    
    public Book getBook(int id) {
    	if (products.get(id) instanceof Book)
    	{
    		return (Book)products.get(id);
    	}
        return null;
    }
    public Stationery getStationery(int id) {
    	if (products.get(id) instanceof Stationery)
    	{
    		return (Stationery)products.get(id);
    	}
        return null;
    }
    
    public Pen getPen(int id) {
    	if (products.get(id) instanceof Pen)
    	{
    		return (Pen)products.get(id);
    	}
        return null;
    }
   

    public void deleteProduct(String id) {
        products.remove(id);
    }
}
