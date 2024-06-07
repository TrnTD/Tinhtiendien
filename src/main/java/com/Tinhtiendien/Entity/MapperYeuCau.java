package com.Tinhtiendien.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.springframework.jdbc.core.RowMapper;

import com.Tinhtiendien.Models.*;

import KtraDuLieu.KtraDuLieu;

public class MapperYeuCau implements RowMapper<YeuCau> {
	@Override
	public YeuCau mapRow(ResultSet rs, int rowNum) throws SQLException {
		YeuCau yeucau = new YeuCau();
		yeucau.setYeucau_id(rs.getInt("yeucau_id"));
		yeucau.setKhachhang_id(rs.getString("khachhang_id"));
		yeucau.setTua_de(rs.getNString("tua_de"));
		yeucau.setNoi_dung(rs.getNString("noi_dung"));
		yeucau.setNoi_dung_rut_gon(KtraDuLieu.shortenString(yeucau.getNoi_dung(), 10));
		yeucau.setNgay_gui(rs.getDate("ngay_gui"));
		String gio_gui = rs.getTime("ngay_gui").toString();
		yeucau.setGio_gui(gio_gui.substring(0, 8));
		yeucau.setTrang_thai_id(rs.getInt("trang_thai_id"));;
		yeucau.setTrang_thai(rs.getNString("trang_thai"));
		return yeucau;
	}
}