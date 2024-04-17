package com.Tinhtiendien.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.Tinhtiendien.Models.*;

public class MapperDongHoDien implements RowMapper<DongHoDien>{
	@Override
	public DongHoDien mapRow(ResultSet rs, int rowNum) throws SQLException {
		DongHoDien donghodien = new DongHoDien();
		
		donghodien.setDongho_id(rs.getInt("dongho_id"));
		donghodien.setKhachhang_id(rs.getString("khachhang_id"));
		donghodien.setNgay_dangky(rs.getDate("ngay_dangky"));
		donghodien.setThang_dangky(rs.getInt("thang_dangky"));
		donghodien.setNam_dangky(rs.getInt("nam_dangky"));
		
		return donghodien;
	} 
}
