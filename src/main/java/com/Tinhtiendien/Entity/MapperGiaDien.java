package com.Tinhtiendien.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.Tinhtiendien.Models.*;

public class MapperGiaDien implements RowMapper<GiaDien>{
	@Override
	public GiaDien mapRow(ResultSet rs, int rowNum) throws SQLException {
		GiaDien GiaDien = new GiaDien();
		GiaDien.setBacDien(rs.getInt("bac"));
		GiaDien.setGiaDien(rs.getInt("giatien"));
		return GiaDien;
	} 
}