package com.Tinhtiendien.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.Tinhtiendien.Models.*;

public class MapperDongHoDien implements RowMapper<DongHoDien>{
	@Override
	public DongHoDien mapRow(ResultSet rs, int rowNum) throws SQLException {
		DongHoDien donghodien = new DongHoDien();
		
		donghodien.setDongho_id(rs.getString("dongho_id"));
		donghodien.setKhachhang_id(rs.getString("khachhang_id"));
		donghodien.setNgay_dangky(rs.getDate("ngay_dangky"));
		return donghodien;
	} 
}
