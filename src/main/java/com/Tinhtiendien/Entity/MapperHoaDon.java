package com.Tinhtiendien.Entity;


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.Tinhtiendien.Models.*;

public class MapperHoaDon implements RowMapper<HoaDon> {
	
	@Override
	public HoaDon mapRow(ResultSet rs, int rowNum) throws SQLException {
		HoaDon hoadon = new HoaDon();
		
		ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
		
        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnName(i);
            
            switch (columnName) {
                case "hoadon_id":
                    hoadon.setHoadon_id(rs.getInt("hoadon_id"));
                    break;
                case "khachhang_id":
                    hoadon.setKhachhang_id(rs.getString("khachhang_id"));
                    break;
                case "dongho_id":
                    hoadon.setDongho_id(rs.getString("dongho_id"));
                    break;
                case "ngay_tao":
                    hoadon.setNgay_tao(rs.getString("ngay_tao"));
                    break;
                case "month_bill":
                    hoadon.setMonth_bill(rs.getInt("month_bill"));
                    break;
                case "year_bill":
                    hoadon.setYear_bill(rs.getInt("year_bill"));
                    break;
                case "ngay_batdau":
                    hoadon.setNgay_batdau(rs.getString("ngay_batdau"));
                    break;
                case "ngay_ketthuc":
                    hoadon.setNgay_ketthuc(rs.getString("ngay_ketthuc"));
                    break;
                case "chiso_cu":
                    hoadon.setChiso_cu(rs.getInt("chiso_cu"));
                    break;
                case "chiso_moi":
                    hoadon.setChiso_moi(rs.getInt("chiso_moi"));
                    break;
                case "dien_tieu_thu":
                    hoadon.setDien_tieu_thu(rs.getInt("dien_tieu_thu"));
                    break;
                case "so_tien":
                    hoadon.setSo_tien(rs.getInt("so_tien"));
                    break;
                case "tien_thue":
                    hoadon.setTien_thue(rs.getInt("tien_thue"));
                    break;
                case "tong_tien":
                    hoadon.setTong_tien(rs.getInt("tong_tien"));
                    break;
                case "thue":
                    hoadon.setThue(rs.getInt("thue"));
                    break;
                case "ngay_thanhtoan":
                    hoadon.setNgay_thanhtoan(rs.getString("ngay_thanhtoan"));
                    break;
                case "trangthai":
                    hoadon.setTrangthai(rs.getString("trangthai"));
                    break;
                case "phuongthuc_id":
                    hoadon.setPhuongthuc_id(rs.getInt("phuongthuc_id"));
                    break;
                case "ten_phuongthuc":
                	hoadon.setTen_phuongthuc(rs.getString("ten_phuongthuc"));
                default:
                    // Handle unexpected columns if necessary
                    break;
            }
        }
        
        return hoadon;
    }
}
