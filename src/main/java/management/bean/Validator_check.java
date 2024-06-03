package management.bean;
import java.time.LocalDate;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Validator_check {
	
	public static boolean isValidPhoneNumber(String phoneNumber) {
	    // Mẫu số điện thoại của Việt Nam: có thể bắt đầu bằng 0 hoặc +84, theo sau là 9 - 11 chữ số
	    String regex = "^(0|\\+?84)\\d{9,11}$";

	    // Tạo đối tượng Pattern từ biểu thức chính quy
	    Pattern pattern = Pattern.compile(regex);

	    // Tạo đối tượng Matcher để so khớp với mẫu
	    Matcher matcher = pattern.matcher(phoneNumber);

	    // Kiểm tra xem số điện thoại có khớp với mẫu hay không
	    return matcher.matches();
	}
	public static boolean isMinimumAge(LocalDate dateOfBirth, int minimumAge) {
        LocalDate currentDate = LocalDate.now();
        LocalDate minimumDateOfBirth = currentDate.minusYears(minimumAge);

        return dateOfBirth.isBefore(minimumDateOfBirth);
    }

}
