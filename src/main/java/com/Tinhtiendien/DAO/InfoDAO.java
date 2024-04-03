package com.Tinhtiendien.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.Tinhtiendien.Entity.MapperInfo;
import com.Tinhtiendien.Models.*;

@Repository
public class InfoDAO {
	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	public Info getAllInfo(String username) {
		String sql = "select * from chuho where username = ?";
		Info info = null;
		try {
			info = jdbcTemplate.queryForObject(sql, new Object[] {username}, new MapperInfo());
			System.out.println("Truy van thong tin chu ho thanh cong");
		} catch (DataAccessException e) {
			System.out.println("Truy van thong tin chu ho that bai");
		}
		
		return info;
	}
}
