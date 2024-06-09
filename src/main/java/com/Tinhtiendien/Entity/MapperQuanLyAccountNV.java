package com.Tinhtiendien.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.Tinhtiendien.Models.QuanLyAccountNV;

public class MapperQuanLyAccountNV implements RowMapper<QuanLyAccountNV> {
	@Override
	public QuanLyAccountNV mapRow(ResultSet rs, int rowNum) throws SQLException {
		QuanLyAccountNV account = new QuanLyAccountNV();
		account.setNhanvien_id(rs.getString("nhanvien_id"));
		account.setUsername(rs.getString("username"));
		account.setPassword(rs.getString("password"));
		return account;
	}
}
