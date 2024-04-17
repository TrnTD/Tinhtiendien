package com.Tinhtiendien.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.Tinhtiendien.Entity.*;
import com.Tinhtiendien.Models.*;
@Repository
public class ChiTietHoaDonDAO {
	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	public List<ChiTietHoaDon> getAllInfoChiTietHoaDon(String khachhang_id) {
		List<ChiTietHoaDon> list_cthd = new ArrayList<ChiTietHoaDon>();
		String sql = "exec sp_GetChiTietHoaDonByKhachHangID @KhachHangID = ?";
		System.out.println(khachhang_id);
		try {
			list_cthd = jdbcTemplate.query(sql, new Object[] {khachhang_id}, new MapperChiTietHoaDon1());
			System.out.println("Truy van thong tin chi tiet hoa don thanh cong!!");
		} catch (DataAccessException e) {
			System.out.println("Truy van thong tin chi tiet hoa don that bai!");
		}
		
		if (list_cthd.isEmpty()) {
			System.out.println("Khong co chi tiet hoa don nao duoc tra ve!");
		}
		
		return list_cthd;
	}
	
	public ChiTietHoaDon getAllInfoHoaDon(String khachhang_id, int thang, int nam) {
		ChiTietHoaDon hoadon = null;
		String sql = "exec sp_GetChiTietHoaDonBykhachhangIDAndMonthAndYear @KhachHangID = ?, @ThangTao = ?, @NamTao = ?";
		System.out.println("thang:" + thang);
		System.out.println("thang:" + nam);
		try {
			hoadon = jdbcTemplate.queryForObject(sql, new Object[] {khachhang_id, thang, nam}, new MapperChiTietHoaDon1());
			System.out.println("Truy van thong tin hoa don thanh cong!!");
		} catch (DataAccessException e) {
			System.out.println("Truy van thong tin hoa don that bai!!");
		}
		
		if (hoadon == null) {
			System.out.println("Khong co hoa don nao duoc tra ve!");
		}
		
		return hoadon;
	}
	
}
