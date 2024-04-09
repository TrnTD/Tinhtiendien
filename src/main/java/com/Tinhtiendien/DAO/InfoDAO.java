package com.Tinhtiendien.DAO;

import java.util.List;

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
		String sql = "select * from chuho where username = ? or chuho_id = ?";
		Info info = null;
		try {
			info = jdbcTemplate.queryForObject(sql, new Object[] {username, username}, new MapperInfo());
			System.out.println("Truy van thong tin chu ho thanh cong 1");
		} catch (DataAccessException e) {
			System.out.println("Truy van thong tin chu ho that bai 1");
		}
		
		return info;
	}
	
	public boolean checkUsernameChuHo(String username) {
		String sql = "select * from chuho where username = ?";
		List<Info> infos = null;
		try {
			infos = jdbcTemplate.query(sql, new Object[] {username}, new MapperInfo());
			System.out.println("Truy van thong tin chu ho thanh cong 2");
		} catch (DataAccessException e) {
			System.out.println("Truy van thong tin chu ho that bai 2");
		}
		
		if (infos != null && infos.size() == 0) {
			return false;
		}
		
		return true;
	}

	public boolean checkChuHoIDandUsername(String chuhoid) {
			String sql = "select * from chuho where chuho_id = ? AND username is null";
			Info info = null;
			try {
				info = jdbcTemplate.queryForObject(sql, new Object[] {chuhoid}, new MapperInfo());
			} catch (DataAccessException e) {
				System.out.println("Truy van chuho that bai");
			}
			
			if (info == null) {
				return false;
			}
			return true;
		}

}
