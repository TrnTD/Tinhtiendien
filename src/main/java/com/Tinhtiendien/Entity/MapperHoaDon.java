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
		hoadon.setDongho_id(rs.getInt("dongho_id"));
		hoadon.setNgay_tao(rs.getDate("ngay_tao"));
		hoadon.setMonth_bill(rs.getInt("month_bill"));
		hoadon.setYear_bill(rs.getInt("year_bill"));
		hoadon.setNgay_batdau(rs.getString("ngay_batdau"));
		hoadon.setNgay_ketthuc(rs.getString("ngay_ketthuc"));
		hoadon.setChiso_cu(rs.getInt("chiso_cu"));
		hoadon.setChiso_moi(rs.getInt("chiso_moi"));
		hoadon.setDien_tieu_thu(rs.getInt("dien_tieu_thu"));
		hoadon.setSo_tien(rs.getInt("so_tien"));
		hoadon.setTien_thue(rs.getInt("tien_thue"));
		hoadon.setTong_tien(rs.getInt("tong_tien"));
		hoadon.setThue(rs.getInt("thue"));
		hoadon.setNgay_thanhtoan(rs.getString("ngay_thanhtoan"));
		hoadon.setTrangthai(rs.getString("trangthai"));
		
		return hoadon;
	}
}
