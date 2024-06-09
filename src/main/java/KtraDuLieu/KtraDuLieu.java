package KtraDuLieu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KtraDuLieu {
	 public static boolean ktraTenDN(String username) {
	        String regex = "^[a-zA-Z0-9]+$";
	        Pattern pattern = Pattern.compile(regex);
	        Matcher matcher = pattern.matcher(username);
	        return matcher.matches();
	    }
	 
	 public static boolean ktraMatKhau(String password) {
	        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$";
	        Pattern pattern = Pattern.compile(regex);
	        Matcher matcher = pattern.matcher(password);
	        return matcher.matches();
	    }
	 
	 public static void main(String[] args) {
	        
		  Date currentDate = new Date();
	        System.out.println("Thời gian hiện tại: " + currentDate);
	 }
	 
	 public static String chuanHoaTen(String ten) {
        ten = ten.trim().replaceAll("\\s+", " ");
 
        String[] tu = ten.split(" ");
        StringBuilder tenChuanHoa = new StringBuilder();

        for (String tuHienTai : tu) {
            
            String chuanHoa = tuHienTai.substring(0, 1).toUpperCase() + tuHienTai.substring(1).toLowerCase();
            tenChuanHoa.append(chuanHoa).append(" ");
        }

        return tenChuanHoa.toString().trim();
    }
	 
	 public static boolean ktraDuLieu_Dien(String giaDien) {
		    String regex = "\\d+";
		    Pattern pattern = Pattern.compile(regex);
		    Matcher matcher = pattern.matcher(giaDien);
		    return matcher.matches();
	 }
	 
	 public static String shortenString(String str, int length) {
		 if (str.length() <= length) {
			 return str;
		 } else {
			 return str.substring(0, length) + "...";
		 }
	 }
	 
	 public static boolean isOlderThan18(String birthDateStr, String compareDateStr) {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	        // Chuyển đổi chuỗi ngày thành đối tượng LocalDate
	        LocalDate birthDate = LocalDate.parse(birthDateStr, formatter);
	        LocalDate compareDate = LocalDate.parse(compareDateStr, formatter);

	        // Tính tuổi giữa birthDate và compareDate
	        long yearsBetween = ChronoUnit.YEARS.between(birthDate, compareDate);

	        // Kiểm tra nếu số năm giữa ngày sinh và ngày so sánh lớn hơn hoặc bằng 18
	        return yearsBetween >= 18;
	    }
	 
	 public static boolean ktraGmail(String email) {
		 String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
	        Pattern pattern = Pattern.compile(regex);
	        Matcher matcher = pattern.matcher(email);
	        return matcher.matches();
	    }
	 
}