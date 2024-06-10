package com.Tinhtiendien.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.Tinhtiendien.Models.*;

public class MapperInfo implements RowMapper<Info> {
	@Override
	public Info mapRow(ResultSet rs, int rowNum) throws SQLException {
		Info info = new Info();
		
		info.setKhachhang_id(rs.getString("khachhang_id"));
		info.setUsername(rs.getString("username"));
		info.setHovaten(rs.getString("hovaten"));
		info.setGioitinh(rs.getString("gioitinh"));
		info.setNgaythangnam_sinh(rs.getDate("ngaythangnam_sinh"));
		info.setEmail(rs.getString("email"));
		info.setSdt(rs.getString("sdt"));
		info.setCccd(rs.getString("cccd"));
		info.setDiachi(rs.getString("diachi"));
		return info;
	}
}
