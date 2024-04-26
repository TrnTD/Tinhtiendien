package com.Tinhtiendien.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.Tinhtiendien.Models.Account;
import com.Tinhtiendien.Models.QuanLyAccount;

public class MapperQuanLyAccount implements RowMapper<QuanLyAccount> {
	@Override
	public QuanLyAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
		QuanLyAccount account = new QuanLyAccount();
		account.setKhachhang_id(rs.getString("khachhang_id"));
		account.setUsername(rs.getString("username"));
		account.setPassword(rs.getString("password"));
		return account;
	}
}
