package com.Tinhtiendien.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.Tinhtiendien.Models.*;

public class MapperInfoNhanVien implements RowMapper<InfoNhanVien>{
	@Override
	public InfoNhanVien mapRow(ResultSet rs, int rowNum) throws SQLException {
		InfoNhanVien infonv = new InfoNhanVien();
		
		infonv.setNhanvien_id(rs.getString("nhanvien_id"));
		infonv.setUsername(rs.getString("username"));
		infonv.setHovaten(rs.getString("hovaten"));
		infonv.setEmail(rs.getString("email"));
		infonv.setSdt(rs.getString("sdt"));	
		
		return infonv;
	}
}
