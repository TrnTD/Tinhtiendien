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
}