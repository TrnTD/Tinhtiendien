package com.Tinhtiendien.Entity;


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.Tinhtiendien.Models.*;

public class MapperPhuongThucThanhToan implements RowMapper<PhuongThucThanhToan> {
	
	@Override
	public PhuongThucThanhToan mapRow(ResultSet rs, int rowNum) throws SQLException {
		PhuongThucThanhToan pttt = new PhuongThucThanhToan();
		ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
		
        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnName(i);
            
            switch (columnName) {
                case "phuongthuc_id":
                    pttt.setPhuongthuc_id(rs.getInt("phuongthuc_id"));
                    break;
                case "ten_phuongthuc":
                    pttt.setTen_phuongthuc(rs.getString("ten_phuongthuc"));
                    break;

                default:
                    break;
            }
        }
        
        return pttt;
	}
}
