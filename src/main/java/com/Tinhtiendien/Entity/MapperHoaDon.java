package com.Tinhtiendien.Entity;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.Tinhtiendien.Models.*;

public class MapperHoaDon implements RowMapper<HoaDon> {
	
	@Override
	public HoaDon mapRow(ResultSet rs, int rowNum) throws SQLException {
		HoaDon hoadon = new HoaDon();
		
		hoadon.setHoadon_id(rs.getInt("hoadon_id"));
		hoadon.setKhachhang_id(rs.getString("khachhang_id"));
		hoadon.setNgay_tao(rs.getDate("ngay_tao"));
		hoadon.setThue(rs.getInt("thue"));
		
		return hoadon;
	}
}
