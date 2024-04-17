package com.Tinhtiendien.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.Tinhtiendien.Models.*;

public class MapperChiTietHoaDon1 implements RowMapper<ChiTietHoaDon> {
	@Override
	public ChiTietHoaDon mapRow(ResultSet rs, int rowNum) throws SQLException {
		ChiTietHoaDon cthd = new ChiTietHoaDon();
		
		cthd.setKhachhang_id(rs.getString("khachhang_id"));
		cthd.setHoadon_id(rs.getInt("hoadon_id"));
		cthd.setChiso_cu(rs.getInt("chiso_cu"));
		cthd.setChiso_moi(rs.getInt("chiso_moi"));
		cthd.setDien_tieu_thu(rs.getInt("dien_tieu_thu"));
		cthd.setNgay_thanhtoan(rs.getString("ngay_thanhtoan"));
		cthd.setTrangthai(rs.getString("trangthai"));
		cthd.setSo_tien(rs.getInt("so_tien"));
		cthd.setTien_thue(rs.getInt("tien_thue"));
		cthd.setTong_tien(rs.getInt("tong_tien"));
		cthd.setNgay_tao(rs.getString("ngay_tao"));
		cthd.setThang_tao(rs.getInt("thang_tao"));
		cthd.setNam_tao(rs.getInt("nam_tao"));
		
		return cthd;
	}
}
