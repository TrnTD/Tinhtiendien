package com.Tinhtiendien.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.AlternativeJdkIdGenerator;

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
				+ "inner join donghodien dhd on dhd.dongho_id = lsd.dongho_id\r\n"
				+ "where dhd.khachhang_id = ? order by lsd.ngay_do DESC";
		try {
			listMH = jdbcTemplate.query(sql, new Object[] {chuho_id}, new MapperMeasurementHistory());
			System.out.println("Truy van lich su do bang khach hang id thanh cong");
		} catch(DataAccessException e) {
			System.out.println("Truy van lich su do bang khach hang id that bai");
		}
		return listMH;
	}
	
	public List<MeasurementHistory> getLSDoTheoFirstChuhoID(){
		List<MeasurementHistory> listMH = new ArrayList<MeasurementHistory>();
		String sql = "select lsd.lichsu_do_id, dhd.khachhang_id, lsd.dongho_id, lsd.ngay_do, lsd.chiso from lichsu_do2 lsd \r\n"
				+ "inner join dong_ho_dien dhd on dhd.dongho_id = lsd.dongho_id\r\n"
				+ "where dhd.khachhang_id = (select top 1 khachhang_id from khachhang order by khachhang_id) order by lsd.ngay_do DESC";
		try {
			listMH = jdbcTemplate.query(sql, new MapperMeasurementHistory());
			System.out.println("Truy van lich su do bang khach hang id dau tien thanh cong");
		} catch(DataAccessException e) {
			System.out.println("Truy van lich su do bang khach hang id dau tien that bai");
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
		String sql = "select lsd.lichsu_do_id, lsd.dongho_id, kh.khachhang_id, lsd.ngay_do, lsd.chiso from lichsu_do lsd inner join khachhang kh on lsd.dongho_id = kh.dongho_id where kh.khachhang_id = ?";
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
	
	public boolean updateLsdFromLSDId(int chiso, /*String ngay_do,*/ int lsd_id) {
		boolean isUpdate = false;
		int result = 0;
		
		String query = "update lichsu_do2 set chiso = ? where lichsu_do_id = ?";
		
		try {
			result = jdbcTemplate.update(query, new Object[] {chiso, lsd_id});
			System.out.println("Cap nhat lich su do khach hang thanh cong");
		} catch (DataAccessException e) {
			System.out.println("Cap nhat lich su do khach hang that bai");
		}
		
		if (result > 0) {
			isUpdate = true;
		}
		
		return isUpdate;
	}
	
	public MeasurementHistory getPreviousLDSByLsdIdAndKhId(int lsd_id, String dongho_id) {
		MeasurementHistory lsd = null;
		
		String query = "select top 1 lsd.lichsu_do_id, dhd.khachhang_id, lsd.dongho_id, lsd.ngay_do, lsd.chiso from lichsu_do2 lsd\r\n"
				+ "inner join donghodien dhd on dhd.dongho_id = lsd.dongho_id\r\n"
				+ "where lsd.ngay_do < (select ngay_do from lichsu_do2 where lichsu_do_id = ?) and lsd.dongho_id = ? order by lsd.ngay_do desc";
		
		try {
			lsd = jdbcTemplate.queryForObject(query, new Object[] {lsd_id, dongho_id}, new MapperMeasurementHistory());
			System.out.println("Lay Previous lsd thanh cong!!");
			
		} catch (DataAccessException e) {
			System.out.println("Lay Previous lsd that bai!!");
		}
		
		return lsd;
	}
	
	public MeasurementHistory getNextLDSByLsdIdAndKhId(int lsd_id, String dongho_id) {
		MeasurementHistory lsd = null;
		
		String query = "select top 1 lsd.lichsu_do_id, dhd.khachhang_id, lsd.dongho_id, lsd.ngay_do, lsd.chiso from lichsu_do2 lsd\r\n"
				+ "inner join donghodien dhd on dhd.dongho_id = lsd.dongho_id\r\n"
				+ "where lsd.ngay_do > (select ngay_do from lichsu_do2 where lichsu_do_id = ?) and lsd.dongho_id = ? order by lsd.ngay_do asc";
		
		
		try {
			lsd = jdbcTemplate.queryForObject(query, new Object[] {lsd_id, dongho_id}, new MapperMeasurementHistory());
			System.out.println("Lay Next lsd thanh cong!!");
			
		} catch (DataAccessException e) {
			System.out.println("Lay Next lsd that bai!!");
		}
		
		return lsd;
	}
	
	public MeasurementHistory getLatestLsdByDongHoId(String dongho_id) {
		MeasurementHistory lsd = null;
		
		String query = "select top 1 lsd.lichsu_do_id, dhd.khachhang_id, lsd.dongho_id, lsd.ngay_do, lsd.chiso from lichsu_do2 lsd\r\n"
				+ "inner join donghodien dhd on dhd.dongho_id = lsd.dongho_id\r\n"
				+ "where dhd.dongho_id = ? order by lsd.chiso desc";
		
		try {
			lsd = jdbcTemplate.queryForObject(query, new Object[] {dongho_id}, new MapperMeasurementHistory());
			System.out.println("Lay Latest lsd thanh cong!!");
		} catch (DataAccessException e) {
			System.out.println("Lay Latest lsd that bai!!");
		}
		
		return lsd;
	}
	
	public boolean enableDelete(String ngay_do, String khachhang_id) {
		boolean isDelete = false;
	
		String result = "";
		String query = "DECLARE @Date DATE = '2024-04-01';\r\n"
				+ "DECLARE @Month INT = MONTH(@Date);\r\n"
				+ "DECLARE @Year INT = YEAR(@Date);\r\n"
				+ "\r\n"
				+ "EXEC sp_GetTrangThaiHoaDon @KhachHangID = 'KH001', @Month = @Month, @Year = @Year;";
		
		try {
			result = jdbcTemplate.queryForObject(query, new Object[] {ngay_do, ngay_do}, String.class);
//			System.out.println("Lay trang thai hoa don thanh cong");
		} catch (DataAccessException e) {
//			System.out.println("Lay trang thai hoa don that bai");
		}
		
		if (result.equals("Đã thanh toán")) {
			isDelete = false;
		} else {
			isDelete = true;
		}
		
		return isDelete;
	}
	
	public List<MeasurementHistory> searchLsdKhachhang(int page, String lsd_id, String khachhang_id, String dhd_id, String tungay, String denngay) {
		List<MeasurementHistory> list_lsd = new ArrayList<MeasurementHistory>();
		List<Object> params = new ArrayList<>();
		List<String> conditions = new ArrayList<>();
		
		String query = "";
		
		if (lsd_id.isEmpty() && khachhang_id.isEmpty() && dhd_id.isEmpty() && tungay.isEmpty() && denngay.isEmpty()) {
			query = "select lsd.lichsu_do_id, lsd.dongho_id, dhd.khachhang_id, lsd.ngay_do, lsd.chiso from lichsu_do2 lsd \r\n"
					+ "inner join donghodien dhd on dhd.dongho_id = lsd.dongho_id \r\n"
					+ "where 1=0";			
		} else {
//			query = "select lsd.lichsu_do_id, lsd.dongho_id, dhd.khachhang_id, lsd.ngay_do, lsd.chiso from lichsu_do2 lsd \r\n"
//					+ "inner join dong_ho_dien dhd on lsd.dongho_id = dhd.dongho_id \r\n"
//					+ "where 1=1";
			
			query = "exec sp_SearchLichSuDo @PageNumber = ?, @PageSize = 10,";
			params.add(page);
			
			if (!lsd_id.isEmpty()) {
				conditions.add("@LichSuDoID = ?");
				params.add(lsd_id);
			}
			
			if (!khachhang_id.isEmpty()) {
				conditions.add("@KhachHangID = ?");
				params.add(khachhang_id);
			}
			
			if (!dhd_id.isEmpty()) {
				conditions.add("@DongHoID = ?");
				params.add(dhd_id);
			}
			
			if (!tungay.isEmpty()) {
				conditions.add("@TuNgay = ?");
				params.add(tungay);
			}
			
			if (!denngay.isEmpty()) {
				conditions.add("@DenNgay = ?");
				params.add(denngay);
			}
			
			if (!conditions.isEmpty()) {
		        query += " " + String.join(", ", conditions);
		    }
		}
		
//		System.out.println(query);
		
		try {
			list_lsd = jdbcTemplate.query(query, params.toArray(), new MapperMeasurementHistory());
			System.out.println("Tim kiem lich su do khach hang thanh cong!");
		} catch (DataAccessException e) {
			System.out.println("Tim kiem lich su do khach hang that bai!");
		}
		
		return list_lsd;
	}
	
	
	public boolean addNewLsd(String dhd_id, String thang, String nam, String chiso) {
		boolean isAdd = false;
		
//		String query = "insert into lichsu_do2 (dongho_id, ngay_do, chiso) values (?, ?, ?)";
		String query = "exec sp_InsertLSD @DongHoID = ?, @Thang = ?, @Nam = ?, @ChiSo = ?";
		
		try {
			jdbcTemplate.update(query, new Object[] {dhd_id, thang, nam, chiso});
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
	
	public boolean checkExistLsdAfter(String khachhang_id, String thang, String nam) {
		int count = 0;
		
		String query = "exec sp_CheckExistLsdAfter @khachangID = ?, @thang = ?, @nam = ?";
		
		try {
			count = jdbcTemplate.queryForObject(query, new Object[] {khachhang_id, thang, nam}, Integer.class);
			System.out.println("Check ton tai lich su do thang sau thanh cong!");
		} catch (DataAccessException e) {
			System.out.println("Check ton tai lich su do thang sau that bai!");
		}
		
		if (count == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean checkExistLsdCurrent(String khachhang_id, String thang, String nam) {
		int count = 0;
		
		String query = "exec sp_CheckExistLsdCurrent @khachangID = ?, @thang = ?, @nam = ?";
		
		try {
			count = jdbcTemplate.queryForObject(query, new Object[] {khachhang_id, thang, nam}, Integer.class);
			System.out.println("Check ton tai lich su do thang hien tai thanh cong!");
		} catch (DataAccessException e) {
			System.out.println("Check ton tai lich su do thang hien tai that bai!");
		}
		
		if (count == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public int getDayFirstLSD(String dongho_id) {
		int day = 1;
		String query = "SELECT DAY(ngay_do) from lichsu_do2 where chiso = 0 and dongho_id = ?";
		
		try {
			day = jdbcTemplate.queryForObject(query, new Object[] {dongho_id}, Integer.class);
			System.out.println("Day: " + day);
		} catch (DataAccessException e) {
			System.out.println("Lay ngay khong thanh cong");
		}
		
		return day;
	}
	
	
	public List<MeasurementHistory> getLSDoTheoChuhoIDTheoThangNam(String chuho_id, String month, String year){
		List<MeasurementHistory> listMH = new ArrayList<MeasurementHistory>();
		String sql = "select lsd.lichsu_do_id, dhd.khachhang_id, lsd.dongho_id, lsd.ngay_do, lsd.chiso from lichsu_do2 lsd\r\n"
				+ "inner join donghodien dhd on dhd.dongho_id = lsd.dongho_id\r\n"
				+ "where dhd.khachhang_id = ? ";
		List<Object> params = new ArrayList<>();
		params.add(chuho_id);
		if (month != null && !month.isEmpty()) {
            sql += " AND DATEPART(MONTH, lsd.ngay_do) = ?";
            params.add(month);
        }
        if (year != null && !year.isEmpty()) {
            sql += " AND DATEPART(YEAR, lsd.ngay_do) = ?";
            params.add(year);
        }
        sql += " ORDER BY lsd.ngay_do DESC;";
        try {
			listMH = jdbcTemplate.query(sql,params.toArray(), new MapperMeasurementHistory());
			System.out.println("Tim kiem lich su do thanh cong!!");
		}catch (DataAccessException e) {
			System.out.println("Tim kiem lich su do that bai!!");
		}
		return listMH;
	}
	
	
//	============================ Phan trang ============================
	
	// Tong trang binh thuong
	public int tong_trang()
	{
		int temp = -1;
		String sql = "exec sp_GetTotalPagesAllLSD @PageSize = 10";
		try {
			  temp = jdbcTemplate.queryForObject(sql, Integer.class);	
			return temp;
		} catch (DataAccessException e) {
			System.out.println("111");
		}
		
		return temp;
	}
	
	// Tổng trang tìm kiếm
	public int tong_trang_search(String lsd_id, String khachhang_id, String dhd_id, String tungay, String denngay)
	{
		int temp = -1;
		List<Object> params = new ArrayList<>();
		List<String> conditions = new ArrayList<>();
		
		String sql = "exec sp_GetTotalPagesAllSearchLSD @PageSize = 10,";
		
		if (!lsd_id.isEmpty()) {
			conditions.add("@LichSuDoID = ?");
			params.add(lsd_id);
		}
		
		if (!khachhang_id.isEmpty()) {
			conditions.add("@KhachHangID = ?");
			params.add(khachhang_id);
		}
		
		if (!dhd_id.isEmpty()) {
			conditions.add("@DongHoID = ?");
			params.add(dhd_id);
		}
		
		if (!tungay.isEmpty()) {
			conditions.add("@TuNgay = ?");
			params.add(tungay);
		}
		
		if (!denngay.isEmpty()) {
			conditions.add("@DenNgay = ?");
			params.add(denngay);
		}
		
		if (!conditions.isEmpty()) {
	        sql += " " + String.join(", ", conditions);
	    }
		
		System.out.println(sql);
		
		try {
			temp = jdbcTemplate.queryForObject(sql, params.toArray(), Integer.class);	
			System.out.println(sql);
			return temp;
		} catch (DataAccessException e) {
			System.out.println("111");
		}
		
		return temp;
	}
	
	public List<MeasurementHistory> getAllLSDInPage(int page) {
		List<MeasurementHistory> list_lsd = new ArrayList<MeasurementHistory>();
		
		String query = "exec sp_GetPagedAllLSD @PageNumber = ?, @PageSize = 10";
		
		try {
			list_lsd = jdbcTemplate.query(query, new Object[] {page}, new MapperMeasurementHistory());
			System.out.println("Truy van tat ca lsd page " + page + " thanh cong!");
		} catch (DataAccessException e) {
			System.out.println("Truy van tat ca lsd page " + page + " that bai!");
		}
		
		return list_lsd;
	}
	
}
