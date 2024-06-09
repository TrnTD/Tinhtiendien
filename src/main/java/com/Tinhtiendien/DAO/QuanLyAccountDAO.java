package com.Tinhtiendien.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.Tinhtiendien.Entity.*;
import com.Tinhtiendien.Models.*;

@Repository
public class QuanLyAccountDAO {
	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	public List<QuanLyAccount> getAllAccountsUser() {
		List<QuanLyAccount> listAccount = new ArrayList<QuanLyAccount>();
		String sql = "SELECT k.khachhang_id, t.*\r\n"
				+ "FROM khachhang AS k\r\n"
				+ "LEFT JOIN taikhoan AS t ON k.username = t.username ";
		
		try {
			listAccount = jdbcTemplate.query(sql, new MapperQuanLyAccount());
			System.out.println("Truy van tat ca tai khoan nguoi dung thanh cong!!");
		} catch (DataAccessException e) {
			System.out.println("Truy van tat ca tai khoan nguoi dung that bai!!");
		}
		
		if (listAccount.isEmpty()) {
	        System.out.println("Không có tài khoản nào được trả về");
	    }
		
		return listAccount;
	}
	
	public List<QuanLyAccount> getAccByKHID(String khachhang_id)
	{
		String sql = "SELECT k.khachhang_id, t.*\r\n"
				+ "FROM khachhang AS k\r\n"
				+ "LEFT JOIN taikhoan AS t ON k.username = t.username\r\n"
				+ "WHERE khachhang_id = ? ";
		List<QuanLyAccount> account = new ArrayList<QuanLyAccount>();
		try {
			account = jdbcTemplate.query(sql,new Object[] {khachhang_id} ,new MapperQuanLyAccount());
			System.out.println("Truy van khoan nguoi dung thanh cong!!");
		} catch (DataAccessException e) {
			System.out.println("Truy van tai khoan nguoi dung that bai!!");
		}
		
		if (account.isEmpty()) {
	        System.out.println("Không có tài khoản nào được trả về");

	    }
		return account;
	}
	
	
	public List<QuanLyAccountNV> getAccNVByKHID(String nhanvien_id)
	{
		String sql = "SELECT n.nhanvien_id, t.*\r\n"
				+ " FROM nhanvien AS n\r\n"
				+ " LEFT JOIN taikhoan AS t ON n.username = t.username\r\n"
				+ " WHERE nhanvien_id = ? ";
		List<QuanLyAccountNV> account = new ArrayList<QuanLyAccountNV>();
		try {
			account = jdbcTemplate.query(sql,new Object[] {nhanvien_id} ,new MapperQuanLyAccountNV());
			System.out.println("Truy van khoan nhan vien thanh cong!!");
		} catch (DataAccessException e) {
			System.out.println("Truy van tai khoan nhan vien that bai!!");
		}
		
		if (account.isEmpty()) {
	        System.out.println("Không có tài khoản nào được trả về");

	    }
		return account;
	}
	
	
	public String changePassword (String username, String newpassword,String thong_bao) {
		String sql = "UPDATE taikhoan SET password = ? WHERE username = ?"; 
		int result = 0;
		try {
			result = jdbcTemplate.update(sql, new Object[] {newpassword, username});
			thong_bao= "Đổi mật khẩu thành công!";
			System.out.println("Doi mat khau thanh cong");
		} catch (DataAccessException e) {
			System.out.println("Doi mat khau that bai");
		}
		return thong_bao;
	}
	
	
	public boolean changePass(String username, String newpass) {
		String query = "update taikhoan set password = ? where username = ?";
		int result = 0;
		
		try {
			result = jdbcTemplate.update(query, new Object[] {newpass, username});
		} catch (DataAccessException e) {
			
		}
		
		if (result != 0) {
			return true;
		}
		
		return false;
	}
	
	
	public String deleteAcc (String username,String thong_bao) {
		String sql = "exec sp_DeleteTaikhoan @username = ?"; 
		int result = 0;
		try {
			result = jdbcTemplate.update(sql, new Object[] {username});
			thong_bao= "Xoá tài khoản thành công!";
			System.out.println("Xoa tai khoan thanh cong");
		} catch (DataAccessException e) {
			System.out.println("Xoa tai khoan that bai");
		}
		return thong_bao;
	}
	
	
	public String deleteAccNhanVien (String username,String thong_bao) {
		String sql = "exec sp_DeleteTaikhoanNhanVien @username = ?"; 
		int result = 0;
		try {
			result = jdbcTemplate.update(sql, new Object[] {username});
			thong_bao= "Xoá tài khoản thành công!";
			System.out.println("Xoa tai khoan thanh cong");
		} catch (DataAccessException e) {
			System.out.println("Xoa tai khoan that bai");
		}
		return thong_bao;
	}
	
	
	public String addAcc (String khachhang_id,String username,String password,String thong_bao) {
		String sql = "exec sp_UpdateUsernameAndInsertIntoTaikhoan @khachhang_id = ? , @username =  ?, @password = ?, @role = null;";
		int result = 0;
		try {
			result = jdbcTemplate.update(sql, new Object[] {khachhang_id,username,password});
			thong_bao = "Thêm tài khoản thành công";
			System.out.println("Them tai khoan thanh cong");
		} catch (DataAccessException e) {
			System.out.println("Them tai khoan that bai");
		}
		return thong_bao;
	}
	
	
	public String addAccNV (String nhanvien_id,String username,String password,String thong_bao) {
		String sql = "exec sp_UpdateUsernameAndInsertIntoTaikhoan_nhanvien @nhanvien_id = ? , @username =  ?, @password = ?, @role = null;";
		int result = 0;
		try {
			result = jdbcTemplate.update(sql, new Object[] {nhanvien_id,username,password});
			thong_bao = "Thêm tài khoản thành công";
			System.out.println("Them tai khoan thanh cong");
		} catch (DataAccessException e) {
			System.out.println("Them tai khoan that bai");
		}
		return thong_bao;
	}
	
/* ===================================== Phân Trang =========================== */
	
	public int getToTalPageAllAccKhachHang()
	{
		int temp = -1;
		String sql = "exec sp_GetTotalPagesAllAccKhachHang @PageSize = 10";
		try {
			  temp = jdbcTemplate.queryForObject(sql, Integer.class);	
			return temp;
		} catch (DataAccessException e) {
			System.out.println("111");
		}
		return temp;
	}
	
	
	public int getToTalPageAllAccNhanVien()
	{
		int temp = -1;
		String sql = "exec sp_GetTotalPagesAllAccNhanVien @PageSize = 10";
		try {
			  temp = jdbcTemplate.queryForObject(sql, Integer.class);	
			  System.out.println(temp);
			return temp;
		} catch (DataAccessException e) {
			System.out.println("111");
		}
		return temp;
	}
	
	
	public List<QuanLyAccount> getAllAccountsUserInPage(int page) {
		List<QuanLyAccount> listAccount = new ArrayList<QuanLyAccount>();
		String sql = "exec sp_GetPagedAllAccKhachHang @PageNumber = ?, @PageSize = 10";
		
		try {
			listAccount = jdbcTemplate.query(sql,new Object[] {page} ,new MapperQuanLyAccount());
			System.out.println("Truy van tat ca tai khoan nguoi dung thanh cong!!");
		} catch (DataAccessException e) {
			System.out.println("Truy van tat ca tai khoan nguoi dung that bai!!");
		}
		
		if (listAccount.isEmpty()) {
	        System.out.println("Không có tài khoản nào được trả về");
	    }
		
		return listAccount;
	}
	
	
	public List<QuanLyAccountNV> getAllAccountsNVInPage(int page) {
		List<QuanLyAccountNV> listAccount = new ArrayList<QuanLyAccountNV>();
		String sql = "exec sp_GetPagedAllAccNhanVien @PageNumber = ?, @PageSize = 10";
		
		try {
			listAccount = jdbcTemplate.query(sql,new Object[] {page} ,new MapperQuanLyAccountNV());
			System.out.println("Truy van tat ca tai khoan nhan vien thanh cong!!");
		} catch (DataAccessException e) {
			System.out.println("Truy van tat ca tai khoan nhan vien that bai!!");
		}
		
		if (listAccount.isEmpty()) {
	        System.out.println("Không có tài khoản nào được trả về");
	    }
		
		return listAccount;
	}
	
	
	public List<QuanLyAccount> getAllAccountsUserInPageBySearch(int page,String khid) {
		List<QuanLyAccount> listAccount = new ArrayList<QuanLyAccount>();
		String sql = "EXEC sp_GetPagedAllAccKhachHangSearch @PageNumber = ?, @PageSize = 10, @KhachHangId = ?;";
		
		try {
			listAccount = jdbcTemplate.query(sql,new Object[] {page,khid} ,new MapperQuanLyAccount());
			System.out.println("Truy van tat ca tai khoan nguoi dung theo tim kiem thanh cong!!");
		} catch (DataAccessException e) {
			System.out.println("Truy van tat ca tai khoan nguoi dung theo tim kiem that bai!!");
		}
		
		if (listAccount.isEmpty()) {
	        System.out.println("Không có tài khoản nào được trả về");
	    }
		return listAccount;
	}
	
	
	public List<QuanLyAccountNV> getAllAccountsNVInPageBySearch(int page,String nvid) {
		List<QuanLyAccountNV> listAccount = new ArrayList<QuanLyAccountNV>();
		String sql = "EXEC sp_GetPagedAllAccNhanVienSearch @PageNumber = ?, @PageSize = 10, @NhanVienId = ?;";
		
		try {
			listAccount = jdbcTemplate.query(sql,new Object[] {page,nvid} ,new MapperQuanLyAccountNV());
			System.out.println("Truy van tat ca tai khoan nhan vien theo tim kiem thanh cong!!");
		} catch (DataAccessException e) {
			System.out.println("Truy van tat ca tai khoan nhan vien theo tim kiem that bai!!");
		}
		
		if (listAccount.isEmpty()) {
	        System.out.println("Không có tài khoản nào được trả về");
	    }
		return listAccount;
	}
	
	
	public int getToTalPageAllAccKhachHangBySearch(String khid)
	{
		int temp = -1;
		String sql = "exec sp_GetTotalPagesAllAccKhachHangSearch @PageSize = 10, @KhachHangId = ?";
		try {
			  temp = jdbcTemplate.queryForObject(sql,new Object[]{khid}, Integer.class);	
			return temp;
		} catch (DataAccessException e) {
			System.out.println("111");
		}
		return temp;
	}
	
	
	public int getToTalPageAllAccNhanVienBySearch(String nvid)
	{
		int temp = -1;
		String sql = "exec sp_GetTotalPagesAllAccNhanVienSearch @PageSize = 10, @NhanVienId = ?";
		try {
			  temp = jdbcTemplate.queryForObject(sql,new Object[]{nvid}, Integer.class);	
			return temp;
		} catch (DataAccessException e) {
			System.out.println("111");
		}
		return temp;
	}
	
//	========================================= Quên Pass ========================== 
	public QuanLyAccount getAccByEmail(String email)
	{
		QuanLyAccount acc = null;
		String sql = "SELECT k.khachhang_id, t.*\r\n"
				+ "FROM khachhang AS k\r\n"
				+ "LEFT JOIN taikhoan AS t ON k.username = t.username \r\n"
				+ "where k.email = ? ";
		try {
			  acc = jdbcTemplate.queryForObject(sql,new Object[]{email},new MapperQuanLyAccount());	
			  System.out.println("Truy van tai khoan nguoi dung thanh cong!!");
		} catch (DataAccessException e) {
			System.out.println("111");
		}
		
		if (acc == null) {
	        System.out.println("Truy van tai khoan nguoi dung thất bại!!");

	    }
		return acc;
	}
}
