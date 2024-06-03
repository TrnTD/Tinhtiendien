package com.Tinhtiendien.DAO;

import java.lang.invoke.CallSite;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.Tinhtiendien.Entity.*;
import com.Tinhtiendien.Models.*;
import com.fasterxml.jackson.databind.deser.impl.JDKValueInstantiators;

@Repository
public class HoaDonDAO {
	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	public List<HoaDon> getAllInfoHoaDon(String khachhang_id) {
		List<HoaDon> list_hoadon = new ArrayList<HoaDon>();
		String sql = "exec sp_GetChiTietHoaDonByKhachHangID2 @KhachHangID = ?";

		try {
			list_hoadon = jdbcTemplate.query(sql,  new Object[] {khachhang_id}, new MapperHoaDon());
			System.out.println("Truy van hoa don tu ma khach hang thanh cong");
			
		} catch (DataAccessException e) {
			System.out.println("Truy van hoa don tu ma khach hang that bai");
		}
		
		if (list_hoadon.isEmpty()) {
			System.out.println("Khong co hoa don nao lay tu ma khach hang duoc tra ve");
		}
		
		return list_hoadon;
	}
	
	
	public HoaDon getAllInfoHoaDonByDate(String khachhang_id, int thang, int nam) {
		HoaDon hoadon = null;
		
		String sql = "exec sp_GetChiTietHoaDonByKhachHangIDAndMonthAndYear2 @KhachHangID = ?, @month = ?, @year = ?";
		
		try {
			hoadon = jdbcTemplate.queryForObject(sql,  new Object[] {khachhang_id, thang, nam}, new MapperHoaDon());
			System.out.println("Truy van thong tin hoa don khach hang tu thang va nam thanh cong!");
		} catch (DataAccessException e) {
			System.out.println("Truy van thong tin hoa don khach hang tu thang va nam that bai!");
		}
		
		if (hoadon == null) {
			System.out.println("Khong co thong tin hoa don khach hang tu thang va nam duoc tra ve!");
		}
		
		return hoadon;
	}
	
	public List<Integer> get_2YearsNearest(String khachhang_id) {
		String query = "select distinct top 2 year_bill from hoa_don2 where khachhang_id = ? and trangthai = N'Đã thanh toán'";
		
		List<Integer> list_year = new ArrayList<>();
		
		try {
			list_year = jdbcTemplate.queryForList(query, Integer.class, khachhang_id);
		} catch (DataAccessException e) {
			System.out.println("Test");
		}
		
		return list_year;
	}
	
	public List<HoaDon> getAllInfoHoaDonByYear(String khachhang_id, int year) {
		String query = "exec sp_GetChiTietHoaDonByKhachHangIDAndYear @KhachHangID = ?, @year = ?";
		
		List<HoaDon> list_hoadon = new ArrayList<HoaDon>();
		
		try {
			list_hoadon = jdbcTemplate.query(query, new Object[] {khachhang_id, year}, new MapperHoaDon());
			System.out.println("Truy van hoa don tu ma khach hang va nam thanh cong");
		} catch (DataAccessException e) {
			System.out.println("Truy van hoa don tu ma khach hang va nam that bai");
		}
		
		return list_hoadon;
	}
	
	public List<Integer> get_doanhthu_by_year(int year) {
        String query = "exec sp_GetChiTietHoaDonByYear @year = ?";

        List<Integer> list_doanhthu;

        try {
            list_doanhthu = jdbcTemplate.query(query, new Object[]{year}, new RowMapper<Integer>() {
                @Override
                public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return rs.getInt("so_tien");
                }
            });
            System.out.println("Lay doanh thu thanh cong");
        } catch (DataAccessException e) {
            System.out.println("Lay doanh thu that bai");
            list_doanhthu = new ArrayList<>();
        }

        return list_doanhthu;
    }
	
	public List<Integer> get_dientieuthu_by_year(int year) {
        String query = "exec sp_GetChiTietHoaDonByYear @year = ?";

        List<Integer> list_doanhthu;

        try {
            list_doanhthu = jdbcTemplate.query(query, new Object[]{year}, new RowMapper<Integer>() {
                @Override
                public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return rs.getInt("dien_tieu_thu");
                }
            });
            System.out.println("Lay dien tieu thu thanh cong");
        } catch (DataAccessException e) {
            System.out.println("Lay dien tieu thu that bai");
            list_doanhthu = new ArrayList<>();
        }

        return list_doanhthu;
    } 
	
	public boolean checkExistHoaDonByHoaDonIdAndTime(String hoadon_id, String thang, String nam) {
		int count = 0;
		
		String query = "select count(*) from hoa_don2 where hoadon_id = ? and month_bill = ? and year_bill = ?";
		
		try {
			count = jdbcTemplate.queryForObject(query, new Object[] {hoadon_id, thang, nam}, Integer.class);
			System.out.println("Check ton tai hoa don thanh cong!");
		} catch (DataAccessException e) {
			System.out.println("Check ton tai hoa don that bai!");
		}
		
		if (count == 0) {
			return false;
		}
		
		return true;
	}
	
	public List<HoaDon> searchHoaDonKhachHang(String hoadon_id, String khachhang_id, String ngaytao, String month_bill, String year_bill, String search_status) {
		
		List<HoaDon> list_hoadon = new ArrayList<HoaDon>();
		List<Object> params = new ArrayList<>();
		List<String> conditions = new ArrayList<>();
		
		String query = "";
		
		if (month_bill.equals("-1")) {
			month_bill = "";
		}
		if (year_bill.equals("-1")) {
			year_bill = "";
		}
		if (search_status.equals("-1")) {
			search_status = "";
		}
		
		if (hoadon_id.isEmpty() && khachhang_id.isEmpty() && ngaytao.isEmpty() && month_bill.isEmpty() && year_bill.isEmpty() && search_status.isEmpty()) {
			list_hoadon = null;
			return list_hoadon;
		} else {
			query = "exec sp_SearchHoaDon @PageNumber = 1, @PageSize = 10,";
			
			if (!hoadon_id.isEmpty()) {
				conditions.add("@HoaDonID = ?");
				params.add(hoadon_id);
			}
			
			if (!khachhang_id.isEmpty()) {
				conditions.add("@KhachHangID = ?");
				params.add(khachhang_id);
			}
			
			if (!ngaytao.isEmpty()) {
				conditions.add("@NgayTao = ?");
				params.add(ngaytao);
			}
			
			if (!month_bill.isEmpty()) {
				conditions.add("@Month_Bill = ?");
				params.add(month_bill);
			}
			
			if (!year_bill.isEmpty()) {
				conditions.add("@Year_Bill = ?");
				params.add(year_bill);
			}
			
			if (!search_status.isEmpty()) {
				conditions.add("@Status = N'" + search_status + "'");
			}
			
			if (!conditions.isEmpty()) {
				query += " " + String.join(", ", conditions);
		    }
		}
		
		
//		System.out.println(query);
		
		try {
			list_hoadon = jdbcTemplate.query(query, params.toArray(), new MapperHoaDon());
			System.out.println("Tim kiem hoa don khach hang thanh cong");
		} catch (DataAccessException e) {
			System.out.println("Tim kiem hoa don khach hang that bai");
		}
		
		return list_hoadon;
	}
	
	public boolean addNewHoaDon(String khachhang_id, String thang, String nam, String thue) {
		boolean isAdd = false;
		
		String query = "insert into hoa_don2 (khachhang_id, ngay_tao, month_bill, year_bill, ngay_thanhtoan, thue) values (?, getdate(), ?, ?, null, ?)";
		
		try {
			jdbcTemplate.update(query, new Object[] {khachhang_id.toUpperCase(), thang, nam, thue});
			isAdd = true;
			System.out.println("them hoa don moi thanh cong");
		} catch (DataAccessException e) {
			System.out.println("Them hoa don moi that bai");
		}
		
		return isAdd;
	}
	
	public boolean editHoaDon(String hoadon_id, String thang, String nam, String thue, String status, String pttt) {
		boolean isEdit = false;
		
		String query = "";
		
		if (!checkExistHoaDonByHoaDonIdAndTime(hoadon_id, thang, nam)) {
			query = "update hoa_don2 set month_bill = ?, year_bill = ?, thue = ?, trangthai = N'" + status + "', phuongthuc_id = ? where hoadon_id = ?";	
			
			try {
				jdbcTemplate.update(query, new Object[] {thang, nam, thue, pttt, hoadon_id});
				System.out.println("Cap nhat hoa don thanh cong!");
				isEdit = true;
			} catch (DataAccessException e) {
				System.out.println("Cap nhat hoa don that bai!");
				
			}
		} else {
			query = "update hoa_don2 set thue = ?, trangthai = N'" + status + "', phuongthuc_id = ? where hoadon_id = ?";	
			
			try {
				jdbcTemplate.update(query, new Object[] {thue, pttt, hoadon_id});
				System.out.println("Cap nhat hoa don thanh cong!");
				isEdit = true;
			} catch (DataAccessException e) {
				System.out.println("Cap nhat hoa don that bai!");
				
			}
		}
		
		
		
		return isEdit;
	}
	
	public boolean deleteHoaDon(String hoadon_id) {
		boolean isDelete = false;
		
		String query = "delete from hoa_don2 where hoadon_id = ?";
		
		try {
			jdbcTemplate.update(query, new Object[] {hoadon_id});
			isDelete = true;
			System.out.println("Xoa hoa don khach hang thanh cong!");
		} catch (DataAccessException e) {
			System.out.println("Xoa hoa don khach hang that bai!");
		}
		
		return isDelete;
	}
	
	// -------------------------------- Phan trang -----------------------------------
	
	public List<HoaDon> getAllPageHoaDon(int page) {
		List<HoaDon> list_hoadon = new ArrayList<HoaDon>();
		
		String query = "exec sp_GetPagedAllHoaDon @PageNumber = ?, @PageSize = 10";
		
		try {
			list_hoadon = jdbcTemplate.query(query, new Object[] {page}, new MapperHoaDon());
			System.out.println("Truy van tat ca hoa don page " + page + " thanh cong");
		} catch (DataAccessException e) {
			System.out.println("Truy van tat ca hoa don page " + page + " that bai");
		}
		
		return list_hoadon;
	}
	
	public int tong_trang_hoadon() {
		int temp = -1;
		
		String query = "exec sp_GetTotalPagesAllHoaDon @PageSize = 10";
		try {
			temp = jdbcTemplate.queryForObject(query, Integer.class);
			System.out.println("Tong so trang cua hoa don la: " + temp);
		} catch (DataAccessException e) {
			System.out.println("Lay tong so trang cua hoa don that bai");
		}
		
		return temp;
	}
	
	public int tong_trang_search_hoadon(String hoadon_id, String khachhang_id, String ngaytao, String month_bill, String year_bill, String search_status)
	{
		int temp = -1;
		List<Object> params = new ArrayList<>();
		List<String> conditions = new ArrayList<>();
		
		String sql = "exec sp_GetTotalPagesAllSearchHoaDon @PageSize = 10,";
		
		if (!hoadon_id.isEmpty()) {
			conditions.add("@HoaDonID = ?");
			params.add(hoadon_id);
		}
		
		if (!khachhang_id.isEmpty()) {
			conditions.add("@KhachHangID = ?");
			params.add(khachhang_id);
		}
		
		if (!ngaytao.isEmpty()) {
			conditions.add("@NgayTao = ?");
			params.add(ngaytao);
		}
		
		if (!month_bill.isEmpty()) {
			conditions.add("@Month_Bill = ?");
			params.add(month_bill);
		}
		
		if (!year_bill.isEmpty()) {
			conditions.add("@Year_Bill = ?");
			params.add(year_bill);
		}
		
		if (!search_status.isEmpty()) {
			conditions.add("@Status = N'" + search_status + "'");
		}
		
		if (!conditions.isEmpty()) {
	        sql += " " + String.join(", ", conditions);
	    }
		
		
		try {
			temp = jdbcTemplate.queryForObject(sql, params.toArray(), Integer.class);
		} catch (DataAccessException e) {
			System.out.println("111");
		}
		
		return temp;
	}
	
	
	// LS Thanh Toan
	public int tong_trang_lsthanhtoan() {
		int temp = -1;
		
		String query = "exec sp_GetTotalPagesAllLSThanhToan @PageSize = 10";
		try {
			temp = jdbcTemplate.queryForObject(query, Integer.class);
			System.out.println("Tong so trang cua ls thanh toan la: " + temp);
		} catch (DataAccessException e) {
			System.out.println("Lay tong so trang cua ls thanh toan that bai");
		}
		
		return temp;
	}
	
	public List<HoaDon> getAllPageLSThanhToan(int page) {
		List<HoaDon> list_hoadon = new ArrayList<HoaDon>();
		
		String query = "exec sp_GetPagedAllLSThanhToan @PageNumber = ?, @PageSize = 10";
		
		try {
			list_hoadon = jdbcTemplate.query(query, new Object[] {page}, new MapperHoaDon());
			System.out.println("Truy van tat ca hoa don page " + page + " thanh cong");
		} catch (DataAccessException e) {
			System.out.println("Truy van tat ca hoa don page " + page + " that bai");
		}
		
		return list_hoadon;
	}
	
	
}
