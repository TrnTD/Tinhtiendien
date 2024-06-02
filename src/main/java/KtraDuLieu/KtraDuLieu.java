package KtraDuLieu;

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
}