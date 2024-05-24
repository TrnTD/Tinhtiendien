package com.Tinhtiendien.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.Tinhtiendien.Entity.MapperMeasurementHistory;
import com.Tinhtiendien.Models.MeasurementHistory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Repository
public class MeasurementHistoryDAO {
	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	public List<MeasurementHistory> getLSDoTheoChuhoID(String chuho_id){
		List<MeasurementHistory> listMH = new ArrayList<MeasurementHistory>();
		String sql = "select lsd.lichsu_do_id, dhd.khachhang_id, lsd.dongho_id, lsd.ngay_do, lsd.chiso from lichsu_do2 lsd\r\n"
				+ "inner join dong_ho_dien dhd on dhd.dongho_id = lsd.dongho_id\r\n"
				+ "where dhd.khachhang_id = ? order by lsd.ngay_do DESC";
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
	
	public boolean updateLsdFromLSDId(int chiso, String ngay_do, int lsd_id) {
		boolean isUpdate = false;
		int result = 0;
		
		String query = "update lichsu_do2 set chiso = ?, ngay_do = ? where lichsu_do_id = ?";
		
		try {
			result = jdbcTemplate.update(query, new Object[] {chiso, ngay_do, lsd_id});
			System.out.println("Cap nhat lich su do khach hang thanh cong");
		} catch (DataAccessException e) {
			System.out.println("Cap nhat lich su do khach hang that bai");
		}
		
		if (result > 0) {
			isUpdate = true;
		}
		
		return isUpdate;
	}
	
	public MeasurementHistory getPreviousLDSByLsdIdAndKhId(int lsd_id, String khachhang_id) {
		MeasurementHistory lsd = null;
		
		String query = "select top 1 lsd.lichsu_do_id, dhd.khachhang_id, lsd.dongho_id, lsd.ngay_do, lsd.chiso from lichsu_do2 lsd\r\n"
				+ "inner join dong_ho_dien dhd on lsd.dongho_id = dhd.dongho_id\r\n"
				+ "where lsd.lichsu_do_id < ? and dhd.khachhang_id = ? order by lsd.ngay_do desc";
		
		try {
			lsd = jdbcTemplate.queryForObject(query, new Object[] {lsd_id, khachhang_id}, new MapperMeasurementHistory());
			System.out.println("Lay Previous lsd thanh cong!!");
			
		} catch (DataAccessException e) {
			System.out.println("Lay Previous lsd that bai!!");
		}
		
		return lsd;
	}
	
	public MeasurementHistory getNextLDSByLsdIdAndKhId(int lsd_id, String khachhang_id) {
		MeasurementHistory lsd = null;
		
		String query = "select top 1 lsd.lichsu_do_id, dhd.khachhang_id, lsd.dongho_id, lsd.ngay_do, lsd.chiso from lichsu_do2 lsd\r\n"
				+ "inner join dong_ho_dien dhd on lsd.dongho_id = dhd.dongho_id\r\n"
				+ "where lsd.lichsu_do_id > ? and dhd.khachhang_id = ? order by lsd.ngay_do asc";
		
		
		try {
			lsd = jdbcTemplate.queryForObject(query, new Object[] {lsd_id, khachhang_id}, new MapperMeasurementHistory());
			System.out.println("Lay Next lsd thanh cong!!");
			
		} catch (DataAccessException e) {
			System.out.println("Lay Next lsd that bai!!");
		}
		
		return lsd;
	}
	
	public MeasurementHistory getLatestLsdByKhachhangId(String khachhang_id) {
		MeasurementHistory lsd = null;
		
		String query = "select top 1 lsd.lichsu_do_id, dhd.khachhang_id, lsd.dongho_id, lsd.ngay_do, lsd.chiso from lichsu_do2 lsd\r\n"
				+ "inner join dong_ho_dien dhd on lsd.dongho_id = dhd.dongho_id\r\n"
				+ "where dhd.khachhang_id = ? order by lsd.chiso desc";
		
		try {
			lsd = jdbcTemplate.queryForObject(query, new Object[] {khachhang_id}, new MapperMeasurementHistory());
			System.out.println("Lay Latest lsd thanh cong!!");
		} catch (DataAccessException e) {
			System.out.println("Lay Latest lsd that bai!!");
		}
		
		return lsd;
	}
	
	public List<MeasurementHistory> searchLsdKhachhang(String lsd_id, String khachhang_id, String dhd_id, String ngaydo) {
		List<MeasurementHistory> list_lsd = new ArrayList<MeasurementHistory>();
		List<Object> params = new ArrayList<>();
		
		String query = "";
		
		if (lsd_id.isEmpty() && khachhang_id.isEmpty() && dhd_id.isEmpty() && ngaydo.isEmpty()) {
			query = "select lsd.lichsu_do_id, lsd.dongho_id, dhd.khachhang_id, lsd.ngay_do, lsd.chiso from lichsu_do2 lsd \r\n"
					+ "inner join dong_ho_dien dhd on lsd.dongho_id = dhd.dongho_id \r\n"
					+ "where dhd.khachhang_id = 'KH001'";			
		} else {
			query = "select lsd.lichsu_do_id, lsd.dongho_id, dhd.khachhang_id, lsd.ngay_do, lsd.chiso from lichsu_do2 lsd \r\n"
					+ "inner join dong_ho_dien dhd on lsd.dongho_id = dhd.dongho_id \r\n"
					+ "where 1=1";
			
			if (!lsd_id.isEmpty()) {
				query += " and lsd.lichsu_do_id = ?";
				params.add(lsd_id);
			}
			
			if (!khachhang_id.isEmpty()) {
				query += " and dhd.khachhang_id = ?";
				params.add(khachhang_id);
			}
			
			if (!dhd_id.isEmpty()) {
				query += " and lsd.dongho_id = ?";
				params.add(dhd_id);
			}
			
			if (!ngaydo.isEmpty()) {
				query += " and lsd.ngay_do = ?";
				params.add(ngaydo);
			}
		}
		
		query += " order by lsd.ngay_do DESC";
		
		try {
			list_lsd = jdbcTemplate.query(query, params.toArray(), new MapperMeasurementHistory());
			System.out.println("Tim kiem lich su do khach hang thanh cong!");
		} catch (DataAccessException e) {
			System.out.println("Tim kiem lich su do khach hang that bai!");
		}
		
		return list_lsd;
	}
	
	public boolean addNewLsd(String khachhang_id, String ngay_do, String chiso) {
		boolean isAdd = false;
		
		String query = "insert into lichsu_do2 (dongho_id, ngay_do, chiso) values ((select dongho_id from dong_ho_dien where khachhang_id = ?), ?, ?)";
		
		try {
			jdbcTemplate.update(query, new Object[] {khachhang_id, ngay_do, chiso});
			isAdd = true;
			System.out.println("Them lich su do moi thanh cong!");
		} catch (DataAccessException e) {
			System.out.println("Them lich su do moi that bai!");
		}
		
		return isAdd;
	}
	
	public boolean deleteLsd(String lsd_id) {
		boolean isDelete = false;
		String query = "delete from lichsu_do2 where lichsu_do_id = ?";
		
		try {
			jdbcTemplate.update(query, new Object[] {lsd_id});
			isDelete = true;
			System.out.println("Xoa lich su do thanh cong!");
		} catch (DataAccessException e) {
			System.out.println("Xoa lich su do that bai!");
		}
		
		
		return isDelete;
	}
	
}
