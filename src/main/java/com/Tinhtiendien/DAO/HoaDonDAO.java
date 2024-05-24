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
public class HoaDonDAO {
	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	public List<HoaDon> getAllInfoHoaDon(String khachhang_id) {
		List<HoaDon> list_hoadon = new ArrayList<HoaDon>();
		String sql = "exec sp_GetChiTietHoaDonByKhachHangID2 @KhachHangID = ?";
		System.out.print(khachhang_id);
		
		try {
			list_hoadon = jdbcTemplate.query(sql,  new Object[] {khachhang_id}, new MapperHoaDon());
			System.out.println("Truy van hoa don tu ma khach hang thanh cong");
			
		} catch (DataAccessException e) {
			System.out.println("Truy van hoa don tu ma khach hang that bai");
		}
		
		if (list_hoadon.isEmpty()) {
			System.out.println("Khong co hoa don nao lay tu ma khach hang duoc tra ve");
		}
		
		return list_hoadon;
	}
	
	public HoaDon getAllInfoHoaDonByDate(String khachhang_id, int thang, int nam) {
		HoaDon hoadon = null;
		
		String sql = "exec sp_GetChiTietHoaDonByKhachHangIDAndMonthAndYear2 @KhachHangID = ?, @month = ?, @year = ?";
		
		try {
			hoadon = jdbcTemplate.queryForObject(sql,  new Object[] {khachhang_id, thang, nam}, new MapperHoaDon());
			System.out.println("Truy van thong tin hoa don khach hang tu thang va nam thanh cong!");
		} catch (DataAccessException e) {
			System.out.println("Truy van thong tin hoa don khach hang tu thang va nam that bai!");
		}
		
		if (hoadon == null) {
			System.out.println("Khong co thong tin hoa don khach hang tu thang va nam duoc tra ve!");
		}
		
		return hoadon;
	}
}
