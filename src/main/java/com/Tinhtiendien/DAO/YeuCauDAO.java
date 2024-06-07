package com.Tinhtiendien.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.Tinhtiendien.Entity.MapperYeuCau;
import com.Tinhtiendien.Models.YeuCau;

@Repository
public class YeuCauDAO {
	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	public List<YeuCau> getYeuCauByID(String khachhang_id){
		List<YeuCau> yeucaus = new ArrayList<YeuCau>();
		String sql = "SELECT yc.yeucau_id, yc.khachhang_id, yc.tua_de, yc.noi_dung, yc.ngay_gui, yc.trang_thai_id, tt.mo_ta AS trang_thai FROM yeucau yc \n"
				+ " JOIN trang_thai_yeucau tt ON yc.trang_thai_id = tt.trang_thai_id \n"
				+ " WHERE yc.khachhang_id = ? ORDER BY yc.ngay_gui DESC";
		
		try {
			yeucaus = jdbcTemplate.query(sql, new Object[] {khachhang_id}, new MapperYeuCau());
			System.out.println("Truy van yeu cau theo id thanh cong!!");
		} catch (DataAccessException e) {
			System.out.println("Truy van yeu cau theo id that bai!!");
		}
		
		if (yeucaus.isEmpty()) {
	        System.out.println("Không có yêu cầu nào được trả về");
	    }
		
		return yeucaus;
	}
	
	public List<YeuCau> getAllYeuCau(){
		List<YeuCau> yeucaus = new ArrayList<YeuCau>();
		String sql = "SELECT yc.yeucau_id, yc.khachhang_id, yc.tua_de, yc.noi_dung, yc.ngay_gui, yc.trang_thai_id, tt.mo_ta AS trang_thai FROM yeucau yc \n"
				+ " JOIN trang_thai_yeucau tt ON yc.trang_thai_id = tt.trang_thai_id \n"
				+ " ORDER BY yc.ngay_gui DESC";
		try {
			yeucaus = jdbcTemplate.query(sql, new MapperYeuCau());
			System.out.println("Truy van yeu cau thanh cong!!");
		} catch (DataAccessException e) {
			System.out.println("Truy van yeu cau that bai!!");
		}
		
		if (yeucaus.isEmpty()) {
	        System.out.println("Không có yêu cầu nào được trả về");
	    }
		
		return yeucaus;
	}
	
	public boolean themYeuCauMoi (String khachhang_id, String tittle, String content) {
		String sql = "insert into yeucau (khachhang_id, tua_de, noi_dung) values (?, ?, ?)";
		int check;
		try {
			check = jdbcTemplate.update(sql, new Object[] {khachhang_id, tittle, content});
			System.out.println("Them yeu cau thanh cong!!");
		}catch (DataAccessException e) {
			System.out.println("Them yeu cau that bai!!");
			return false;
		}
		return true;
	}
	
	public boolean suaYeuCau ( String tittle, String content, String yeucau_id) {
		String sql = "UPDATE yeucau SET tua_de = ?, noi_dung = ?, ngay_gui = GETDATE() WHERE yeucau_id = ?;";
		int check;
		try {
			check = jdbcTemplate.update(sql, new Object[] { tittle, content, yeucau_id});
			System.out.println("Sua yeu cau thanh cong!!");
		}catch (DataAccessException e) {
			System.out.println("Sua yeu cau that bai!!");
			return false;
		}
		return true;
	}
	
	public boolean xoaYeuCau ( String yeucau_id) {
		String sql = "DELETE yeucau WHERE yeucau_id = ?;";
		int check;
		try {
			check = jdbcTemplate.update(sql, new Object[] { yeucau_id});
			System.out.println("Xoa yeu cau thanh cong!!");
		}catch (DataAccessException e) {
			System.out.println("Xoa yeu cau that bai!!");
			return false;
		}
		return true;
	}
	
	public boolean suaTrangThai (String trang_thai_id, String yeucau_id) {
		String sql = "UPDATE yeucau SET trang_thai_id = ? WHERE yeucau_id = ?;";
		int check;
		try {
			check = jdbcTemplate.update(sql, new Object[] { trang_thai_id, yeucau_id});
			System.out.println("Sua trang thai yeu cau thanh cong!!");
		}catch (DataAccessException e) {
			System.out.println("Sua trang thai yeu cau that bai!!");
			return false;
		}
		return true;
	}
	
	public List<YeuCau> timKiemYeuCau (String khachhang_id, String ngay_gui, String tua_de, String trang_thai_id) {
		List<YeuCau> yeucaus = new ArrayList<YeuCau>();
		String sql = "SELECT yc.yeucau_id, yc.khachhang_id, yc.tua_de, yc.noi_dung, yc.ngay_gui, yc.trang_thai_id, tt.mo_ta AS trang_thai FROM yeucau yc \n"
				+ " JOIN trang_thai_yeucau tt ON yc.trang_thai_id = tt.trang_thai_id \n"
				+ " WHERE 1=1";
		List<Object> params = new ArrayList<>();
		if (khachhang_id != null && !khachhang_id.isEmpty()) {
            sql += " AND LOWER(yc.khachhang_id) LIKE LOWER(?)";
            params.add("%" + khachhang_id + "%");
        }
        if (ngay_gui != null && !ngay_gui.isEmpty()) {
            sql += " AND CAST(yc.ngay_gui AS DATE) = ?";
            params.add(ngay_gui);
        }
        if (tua_de != null && !tua_de.isEmpty()) {
            sql += " AND LOWER(yc.tua_de) LIKE LOWER(?)";
            params.add("%" + tua_de + "%");
        }
        if (trang_thai_id != null && !trang_thai_id.isEmpty()) {
            sql += " AND yc.trang_thai_id = ?";
            params.add(trang_thai_id);
        }
        sql += " ORDER BY yc.ngay_gui DESC;";
		try {
			yeucaus = jdbcTemplate.query(sql,params.toArray(), new MapperYeuCau());
			System.out.println("Tim kiem yeu cau thanh cong!!");
		}catch (DataAccessException e) {
			System.out.println("Tim kiem yeu cau that bai!!");
		}
		return yeucaus;
	}
}