package com.Tinhtiendien.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.Tinhtiendien.Entity.*;
import com.Tinhtiendien.Models.*;

@Repository
public class PhuongThucThanhToanDAO {
	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	public List<PhuongThucThanhToan> getAllPTTT() {
		List<PhuongThucThanhToan> list_pttt = new ArrayList<PhuongThucThanhToan>();
		String query = "select * from phuongthuc_thanhtoan";
		
		try {
			list_pttt = jdbcTemplate.query(query, new MapperPhuongThucThanhToan());
			System.out.println("Truy van tat ca phuong thuc thanh toan thanh cong");
		} catch (DataAccessException e) {
			System.err.println("Truy van tat ca phuong thuc thanh toan that bai");
		}
		
		return list_pttt;
	}
}
