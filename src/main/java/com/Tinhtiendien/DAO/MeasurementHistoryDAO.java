package com.Tinhtiendien.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.Tinhtiendien.Entity.MapperMeasurementHistory;
import com.Tinhtiendien.Models.MeasurementHistory;

import java.util.ArrayList;
import java.util.List;
@Repository
public class MeasurementHistoryDAO {
	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	public List<MeasurementHistory> getLSDoTheoChuhoID(String chuho_id){
		List<MeasurementHistory> listMH = new ArrayList<MeasurementHistory>();
		String sql = "select lsd.lichsu_do_id, lsd.dongho_id, dhd.khachhang_id, lsd.ngay_do, lsd.chiso from lichsu_do2 lsd inner join dong_ho_dien dhd on lsd.dongho_id = dhd.dongho_id where dhd.khachhang_id = ?";
		try {
			listMH = jdbcTemplate.query(sql, new Object[] {chuho_id}, new MapperMeasurementHistory());
			System.out.println("Truy van lich su do bang khach hang id thanh cong");
		} catch(DataAccessException e) {
			System.out.println("Truy van lich su do bang khach hang id that bai");
		}
		return listMH;
	}
	
	public List<MeasurementHistory> getAllLSDo(){
		List<MeasurementHistory> listMH = new ArrayList<MeasurementHistory>();
		String sql = "select * from lichsu_do";
		try {
			listMH = jdbcTemplate.query(sql, new MapperMeasurementHistory());
			System.out.println("Truy van lich su do thanh cong");
		} catch(DataAccessException e) {
			System.out.println("Truy van lich su do that bai");
		}
		return listMH;
	}
	
	public boolean checkKhachHangInLSD(String khachhang_id) {
		String sql = "select lsd.lichsu_do_id, lsd.dongho_id, dhd.khachhang_id, lsd.ngay_do, lsd.chiso from lichsu_do lsd inner join dong_ho_dien dhd on lsd.dongho_id = dhd.dongho_id where dhd.khachhang_id = ?";
		List<MeasurementHistory> lsd = null;
		try {
			lsd = jdbcTemplate.query(sql, new Object[] {khachhang_id}, new MapperMeasurementHistory());
			System.out.println("Truy van thong tin lich su do bang id thanh cong");
		} catch (DataAccessException e) {
			System.out.println("Truy van thong tin lich su do bang id that bai");
		}
		
		if (lsd != null && lsd.size() == 0) {
			return false;
		}	
		return true;
	}
}
