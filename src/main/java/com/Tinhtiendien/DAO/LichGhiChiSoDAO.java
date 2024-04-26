package com.Tinhtiendien.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.Tinhtiendien.Entity.*;
import com.Tinhtiendien.Models.*;

@Repository
public class LichGhiChiSoDAO {
	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	public List<LichGhiChiSo> getTT(String khachhang_id)
	{
		String sql = "select * from lich_ghi_chi_so where khachhang_id = ? order by ngay_batdau DESC";
		List<LichGhiChiSo> lsckd = null;
		try
		{
			lsckd = jdbcTemplate.query(sql, new Object[]{khachhang_id}, new MapperLichGhiChiSo());
			System.out.println("Lay thanh cong lich ghi chi so");
		}
		catch (DataAccessException e) {
			System.out.println("Truy van thong tin that bai");
		}
		
		return lsckd;
	}
	
	public boolean checkKhachHangInLGCS(String khachhang_id) {
		String sql = "select * from lich_ghi_chi_so where khachhang_id = ?";
		List<LichGhiChiSo> lgcs = null;
		try {
			lgcs = jdbcTemplate.query(sql, new Object[] {khachhang_id}, new MapperLichGhiChiSo());
			System.out.println("Truy van thong tin lich ghi chi so bang id thanh cong");
		} catch (DataAccessException e) {
			System.out.println("Truy van thong tin lich ghi chi so bang id that bai");
		}
		
		if (lgcs != null && lgcs.size() == 0) {
			return false;
		}	
		return true;
	}
}
