package com.Tinhtiendien.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.Tinhtiendien.Entity.MapperHoaDon;
import com.Tinhtiendien.Entity.MapperInfo;
import com.Tinhtiendien.Entity.MapperInfoNhanVien;
import com.Tinhtiendien.Entity.MapperThongBao;
import com.Tinhtiendien.Models.*;

@Repository
public class ThongBaoDAO {
	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	public List<ThongBao> getThongBaoByKhachhangId(String khachhang_id) {
		List<ThongBao> list_thongbao = new ArrayList<ThongBao>();
		
		String query = "select format(tbhd.ngay_gui, 'dd-MM-yyyy HH:mm') as ngay_gui, tbhd.noi_dung from  thongbao_hoadon tbhd\r\n"
				+ "inner join hoa_don2 hd on tbhd.hoadon_id = hd.hoadon_id\r\n"
				+ "where hd.khachhang_id = ? order by ngay_gui desc";
		
		try {
			list_thongbao = jdbcTemplate.query(query, new Object[] {khachhang_id}, new MapperThongBao());
			System.out.println("Truy van thanh cong thong bao khach hang");
		} catch (DataAccessException e) {
			System.out.println("Truy van that bai thong bao khach hang");
		}
		
		return list_thongbao;
	}
}
