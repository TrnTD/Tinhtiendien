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
		infonv.setHovaten(rs.getNString("hovaten"));
		infonv.setGioitinh(rs.getString("gioitinh"));
		infonv.setNgaythangnam_sinh(rs.getDate("ngaythangnam_sinh"));
		infonv.setEmail(rs.getString("email"));
		infonv.setSdt(rs.getString("sdt"));
		infonv.setCccd(rs.getString("cccd"));
		infonv.setDiachi(rs.getNString("diachi"));
		
		return infonv;
	}
}
