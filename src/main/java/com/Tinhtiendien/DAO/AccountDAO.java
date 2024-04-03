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
		String sql = "select * from taikhoan where username = ? and password = ?";
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
		String query = "select role from taikhoan where username = ?";
		
		try {
			role = jdbcTemplate.queryForObject(query, Integer.class, username);	
			return role;
		} catch (DataAccessException e) {
			System.out.println("Truy van role that bai!!");
		}
		
		// neu khong tim thay role => tra ve -1
		return role;
	}
	
	public Account getAllInfo(String username) {
		Account account = null;
		String sql = "select * from chuho where username = ?";
		
		try {
			account = jdbcTemplate.queryForObject(sql, new Object[] {username}, new MapperAccount());
			System.out.println("Lay thong tin chu ho thanh cong!!");
		} catch (DataAccessException e) {
			System.out.println("Lay thong tin chu ho that bai!!");
		}
		
		return account;
	}
	
	
}
