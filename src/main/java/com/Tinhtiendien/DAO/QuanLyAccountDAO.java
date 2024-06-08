package com.Tinhtiendien.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.Tinhtiendien.Entity.MapperAccount;
import com.Tinhtiendien.Entity.MapperQuanLyAccount;
import com.Tinhtiendien.Models.Account;
import com.Tinhtiendien.Models.QuanLyAccount;

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
	
//	========================================= Quên Pass ========================== 
	public QuanLyAccount getAccByEmail(String email)
	{
		QuanLyAccount acc = new QuanLyAccount();
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
