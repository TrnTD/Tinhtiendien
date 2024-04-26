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
	
	public void changePassword (String username, String newpassword) {
		String sql = "UPDATE taikhoan SET password = ? WHERE username = ?"; 
		int result = 0;
		try {
			result = jdbcTemplate.update(sql, new Object[] {newpassword, username});
			System.out.println("Doi mat khau thanh cong");
		} catch (DataAccessException e) {
			System.out.println("Doi mat khau that bai");
		}
	}
	
	public void deleteAcc (String username) {
		String sql = "exec sp_DeleteTaikhoan @username = ?"; 
		int result = 0;
		try {
			result = jdbcTemplate.update(sql, new Object[] {username});
			System.out.println("Xoa tai khoan thanh cong");
		} catch (DataAccessException e) {
			System.out.println("Xoa tai khoan that bai");
		}
	}
	
	public void addAcc (String khachhang_id,String username,String password) {
		String sql = "exec sp_UpdateUsernameAndInsertIntoTaikhoan @khachhang_id = ? , @username =  ?, @password = ?, @role = null;";
		int result = 0;
		try {
			result = jdbcTemplate.update(sql, new Object[] {khachhang_id,username,password});
			System.out.println("Them tai khoan thanh cong");
		} catch (DataAccessException e) {
			System.out.println("Them tai khoan that bai");
		}
	}
}
