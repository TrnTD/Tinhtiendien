package com.Tinhtiendien.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.Tinhtiendien.Models.MeasurementHistory;

public class MapperMeasurementHistory implements RowMapper<MeasurementHistory> {
	@Override
	public MeasurementHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
		MeasurementHistory mh = new MeasurementHistory();
		
		mh.setLichsu_do_id(rs.getInt("lichsu_do_id"));
		mh.setDongho_id(rs.getInt("dongho_id"));
		mh.setKhachhang_id(rs.getString("khachhang_id"));
		mh.setNgay_do(rs.getDate("ngay_do"));
		mh.setChiso(rs.getInt("chiso"));
		
		return mh;
	}
}
