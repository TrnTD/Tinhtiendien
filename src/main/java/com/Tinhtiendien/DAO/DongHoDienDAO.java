package com.Tinhtiendien.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.Tinhtiendien.Entity.MapperDongHoDien;
import com.Tinhtiendien.Models.*;

@Repository
public class DongHoDienDAO {
	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	public int getNamDangKy(String khachhang_id) {
		int nam = -1;
		String query = "select YEAR(ngay_dangky) as nam_dangky from dong_ho_dien where khachhang_id = ?";
		
		try {
			nam = jdbcTemplate.queryForObject(query, Integer.class, khachhang_id);
			System.out.println("Truy van nam tu dong ho dien thanh cong!");
		} catch (DataAccessException e) {
			System.out.println("Truy van nam tu dong ho dien that bai!");
		}
		
		if (nam == -1) {
			System.out.println("Khong co nam nao duoc tra ve");
		}
		
		return nam;
	}
	
	public int getMaDongHo(String khachhang_id) {
		int ma = -1;
		
		String query = "select dongho_id from dong_ho_dien where khachhang_id = ?";
		
		try {
			ma = jdbcTemplate.queryForObject(query, Integer.class, khachhang_id);
			System.out.println("Lay ma dong ho dien tu ma khach hang thanh cong");
		} catch (DataAccessException e) {
			System.out.println("Lay ma dong ho dien tu ma khach hang that bai");
		}
		
		if (ma == -1) {
			System.out.println("Khong ma dong ho nao duoc tra ve");
		}
		
		return ma;
	}
	
	public boolean checkKhachHangInDongHoDien(String khachhang_id) {
		String sql = "select * from dong_ho_dien where khachhang_id = ?";
		List<DongHoDien> dhd = null;
		try {
			dhd = jdbcTemplate.query(sql, new Object[] {khachhang_id}, new MapperDongHoDien());
			System.out.println("Truy van thong tin dong ho dien bang id thanh cong");
		} catch (DataAccessException e) {
			System.out.println("Truy van thong tin dong ho dien bang id that bai");
		}
		
		if (dhd != null && dhd.size() == 0) {
			return false;
		}	
		return true;
	}
}