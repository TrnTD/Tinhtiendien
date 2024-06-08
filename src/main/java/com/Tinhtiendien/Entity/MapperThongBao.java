package com.Tinhtiendien.Entity;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.Tinhtiendien.Models.*;

public class MapperThongBao implements RowMapper<ThongBao> {
	@Override
	public ThongBao mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		ThongBao thongbao = new ThongBao();
		ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
		
        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnName(i);
            
            switch (columnName) {
                case "thongbao_id":
                	thongbao.setThongbao_id(rs.getInt("thongbao_id"));
                    break;
                case "nhanvien_id":
                	thongbao.setNhanvien_id(rs.getString("nhanvien_id"));
                    break;
                case "khachhang_id":
                	thongbao.setKhachhang_id(rs.getString("khachhang_id"));
                    break;
                case "ngay_gui":
                	thongbao.setNgay_gui(rs.getString("ngay_gui"));
                    break;
                case "noi_dung":
                	thongbao.setNoi_dung(rs.getString("noi_dung"));
                    break;

                default:
                    break;
            }
        }
        
        return thongbao;
	}
}
