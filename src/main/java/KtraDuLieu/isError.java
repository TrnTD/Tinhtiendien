package KtraDuLieu;

public class isError {
	
	public static String isNull (String str){
		return str + " Không Được Để Trống!!!";
	}
	public static String isType (String str) {
		return str + " Sai Định Dạng Kiểu Dữ Liệu!!!";
	}
	public static String isData (String str) {
		return str + " Xung Đột Dữ Liệu!!!";
	}
	public static String isEmpty (String str) {
		return str + " Rỗng!!!";
	}
	public static String isNone (String str) {
		return str + " Lỗi Không Xác Định!!!";
	}
	public static String isSql (String str) {
		return str + " Lỗi Truy Vấn Cơ Sở Dữ Liệu!!!";
	}
	public static String isFailed (String action) {
		return action + " Thất Bại!!!";
	}
	public static String is_Exist (String str) {
		return str + " Đã Tồn Tại!!!";
	}
	public static String isFormat (String str) {
		return str + "Định Dạng Dữ Liệu Không Hợp Lệ!!!";
	}
	public static String isIncorrect (String str) {
		return str + " Không Đúng!!!";
	}
	public static String isNotSame (String str) {
		return str + " Không Được Trùng " + str + " Trước Đó!!!";
	}
	public static String isSame (String str) {
		return str + " Phải Trùng " + str + " Trước Đó!!!";
	}
	public static String isNext (String str) {
		return str + " Sau Đó!!!";
	}
	public static String isPrevious (String str) {
		return str + " Trước Đó!!!";
	}
	public static String isNotValid (String str) {
		return str + " Không Hợp Lệ!!!";
	}
}