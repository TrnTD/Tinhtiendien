package com.Tinhtiendien.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.Tinhtiendien.Models.*;

public class MapperLichGhiChiSo implements RowMapper<LichGhiChiSo> {
	@Override
	public LichGhiChiSo mapRow(ResultSet rs, int rowNum) throws SQLException
	{
		LichGhiChiSo ck = new LichGhiChiSo();
		
		ck.setLichghi_id(rs.getString("lichghi_id"));
		ck.setKhachhang_id(rs.getString("khachhang_id"));
		String temp = rs.getString("ngay_batdau");
		String []temp1 = temp.split("-");
		ck.setNam(temp1[0]);
		ck.setThang(temp1[1]);
		ck.setNgay_thang_nam(temp1[2]+"-"+ck.getThang()+"-"+ck.getNam());
		
		return ck;
	}
}
