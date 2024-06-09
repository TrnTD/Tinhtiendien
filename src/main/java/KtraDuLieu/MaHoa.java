package KtraDuLieu;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MaHoa {
	public static String getMD5Hash(String input) {
        try {
            // Khởi tạo MessageDigest với thuật toán MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Thêm dữ liệu cần băm vào
            md.update(input.getBytes());

            // Thực hiện quá trình băm và trả về kết quả là một mảng byte
            byte[] digest = md.digest();

            // Chuyển đổi mảng byte thành dạng hex string
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }

            // Trả về chuỗi đã được mã hóa
            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
