package com.Tinhtiendien.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.Tinhtiendien.Models.*;
import com.Tinhtiendien.Entity.*;

@Repository
public class AccountDAO {
	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	public List<Account> getAllAccounts() {
		List<Account> listAccount = new ArrayList<Account>();
		String sql = "select * from taikhoan";
		
		try {
			listAccount = jdbcTemplate.query(sql, new MapperAccount());
			System.out.println("Truy van tat ca tai khoan thanh cong!!");
		} catch (DataAccessException e) {
			System.out.println("Truy van tat ca tai khoan that bai!!");
		}
		
		if (listAccount.isEmpty()) {
	        System.out.println("Không có tài khoản nào được trả về");
	    }
		
		return listAccount;
	}
	
	public boolean checkExistAccount(String username, String password) {
//		String sql = "select * from taikhoan where username = ? and password = ?";
		String sql = "EXEC sp_CheckLogin @username = ?, @password = ?";
		List<Account> list = new ArrayList<Account>();
		
		try {
			list = jdbcTemplate.query(sql, new Object[] {username, password}, new MapperAccount());
		} catch (DataAccessException e) {
			System.out.println("Truy van account that bai");
		}
		
		if (list.size() == 0) {
			return false;
		}
		
		return true;
	}
	
	public int getRole(String username) {
		int role = -1;
//		String query = "select role from taikhoan where username = ? or khachhang_id = ?";
		String query = "exec sp_GetRole @username = ?";
		
		try {
			role = jdbcTemplate.queryForObject(query, Integer.class, username);	
			return role;
		} catch (DataAccessException e) {
			System.out.println("Truy van role that bai!!");
		}
		
		// neu khong tim thay role => tra ve -1
		return role;
	}
	
	public void register (String khachhang_id, String username, String password) {
		String sql = "EXEC sp_UpdateUsernameAndInsertIntoTaikhoan @khachhang_id = ?, @username = ?, @password = ?, @role = null";
		int result = 0;
		try {
			result = jdbcTemplate.update(sql, new Object[] {khachhang_id, username, password});
		} catch (DataAccessException e) {
			System.out.println("Su dung sp that bai");
		}
		
	}

	public boolean checkUsernameAccount(String username) {
			List<Account> list = new ArrayList<Account>();
			String sql = "select * from taikhoan where username = ?";
			try {
				list = jdbcTemplate.query(sql, new Object[] {username}, new MapperAccount());
			} catch (DataAccessException e) {
				System.out.println("Truy van taikhoan that bai");
			}
			
			if (list.size() == 0) {
				return false;
			}
			return true;
	}
	
	public void changePassword (String username, String newpassword) {
		String sql = "UPDATE taikhoan SET password = ? WHERE username = ?"; 
		int result = 0;
		try {
			result = jdbcTemplate.update(sql, new Object[] {newpassword, username});
		} catch (DataAccessException e) {
			System.out.println("Doi mat khau that bai");
		}
		
	}
	
	public boolean checkOldPassword (String username, String oldpassword) {
		String sql = "select * from taikhoan where username = ? and password = ?";
		Account account = null;
		try {
			account = jdbcTemplate.queryForObject(sql, new Object[] { username, oldpassword}, new MapperAccount());
			System.out.println("Truy van mat khau cu thanh cong");
		} catch (DataAccessException e) {
			System.out.println("Truy van mat khau cu that bai");
		}
		
		if (account == null) {
			return false;
		}
		return true;
	}
	
	
}
