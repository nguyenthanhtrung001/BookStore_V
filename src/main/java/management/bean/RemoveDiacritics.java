package management.bean;
import java.text.Normalizer;
import java.util.regex.Pattern;

public class RemoveDiacritics {

	 public static String removeDiacritics(String input) {
	        if (input == null) {
	            return null;
	        }
	        
	        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
	        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
	        
	        return pattern.matcher(normalized).replaceAll("").replaceAll(" ", "-").toLowerCase();
	    }

}
