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
	
	public List<HoaDon> getFirstAllInfoHoaDon() {
		List<HoaDon> list_hoadon = new ArrayList<HoaDon>();
		String sql = "declare @KhachangID varchar(50)\r\n"
				+ "select top 1 @KhachangID = khachhang_id from khachhang order by khachhang_id\r\n"
				+ "exec sp_GetChiTietHoaDonByKhachHangID2 @KhachHangID = @KhachangID";

		try {
			list_hoadon = jdbcTemplate.query(sql, new MapperHoaDon());
			System.out.println("Truy van hoa don tu tu ma khach hang dau tien thanh cong");
			
		} catch (DataAccessException e) {
			System.out.println("Truy van hoa don tu tu ma khach hang dau tien that bai");
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
			query = "SELECT \r\n"
					+ "		hd.hoadon_id,\r\n"
					+ "		hd.khachhang_id,\r\n"
					+ "		--lsd1.lichsu_do_id,\r\n"
					+ "		lsd1.dongho_id,\r\n"
					+ "		format(hd.ngay_tao, 'dd-MM-yyyy HH:mm:ss') as ngay_tao,\r\n"
					+ "		hd.month_bill,\r\n"
					+ "		hd.year_bill,\r\n"
					+ "		lsd1.ngay_do as ngay_batdau,\r\n"
					+ "		lsd2.ngay_do as ngay_ketthuc,\r\n"
					+ "		lsd1.chiso AS chiso_cu,\r\n"
					+ "		lsd2.chiso AS chiso_moi,\r\n"
					+ "		(lsd2.chiso - lsd1.chiso) as dien_tieu_thu,\r\n"
					+ "		dbo.CalculateSum(lsd1.chiso, lsd2.chiso) as so_tien,\r\n"
					+ "		dbo.CalculateTax2(hd.hoadon_id, lsd1.chiso, lsd2.chiso) as tien_thue,\r\n"
					+ "		(dbo.CalculateSum(lsd1.chiso, lsd2.chiso) + dbo.CalculateTax2(hd.hoadon_id, lsd1.chiso, lsd2.chiso)) as tong_tien,\r\n"
					+ "		hd.thue,\r\n"
					+ "		FORMAT(hd.ngay_thanhtoan, 'dd-MM-yyyy') as ngay_thanhtoan,\r\n"
					+ "		hd.trangthai\r\n"
					+ "	FROM \r\n"
					+ "		hoa_don2 hd\r\n"
					+ "	INNER JOIN \r\n"
					+ "		dong_ho_dien dhd ON hd.khachhang_id = dhd.khachhang_id\r\n"
					+ "	INNER JOIN \r\n"
					+ "		lichsu_do2 lsd1 ON hd.month_bill = month(lsd1.ngay_do) AND hd.year_bill = year(lsd1.ngay_do) AND dhd.dongho_id = lsd1.dongho_id\r\n"
					+ "	LEFT JOIN \r\n"
					+ "		lichsu_do2 lsd2 ON hd.month_bill + 1 = month(lsd2.ngay_do) AND hd.year_bill = year(lsd2.ngay_do) AND dhd.dongho_id = lsd2.dongho_id\r\n"
					+ "								OR (hd.month_bill = 12 AND 1 = month(lsd2.ngay_do) AND hd.year_bill + 1 = year(lsd2.ngay_do))\r\n"
					+ "	WHERE 1=1";
			
			if (!hoadon_id.isEmpty()) {
				query += " and hd.hoadon_id = ?";
				params.add(hoadon_id);
			}
			if (!khachhang_id.isEmpty()) {
				query += " and hd.khachhang_id = ?";
				params.add(khachhang_id);
			}
			if (!ngaytao.isEmpty()) {
				query += " and hd.ngay_tao = ?";
				params.add(ngaytao);
			}
			if (!month_bill.isEmpty()) {
				query += " and hd.month_bill = ?";
				params.add(month_bill);
			}
			if (!year_bill.isEmpty()) {
				query += " and hd.year_bill = ?";
				params.add(year_bill);
			}
			if (!search_status.isEmpty()) {
				System.out.println("search_status: " + search_status);
				query += " and hd.trangthai = N'" + search_status + "'";
//				params.add("N'" + search_status + "'");
			}
		}
		
		query += " ORDER BY hd.ngay_tao DESC";
		
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
	
	public boolean editHoaDon(String hoadon_id, String thang, String nam, String thue, String status) {
		boolean isEdit = false;
		
		String query = "";
		
		if (!checkExistHoaDonByHoaDonIdAndTime(hoadon_id, thang, nam)) {
			query = "update hoa_don2 set ngay_tao = getdate(), month_bill = ?, year_bill = ?, thue = ?, trangthai = N'" + status + "' where hoadon_id = ?";	
			
			try {
				jdbcTemplate.update(query, new Object[] {thang, nam, thue, hoadon_id});
				System.out.println("Cap nhat hoa don thanh cong!");
				isEdit = true;
			} catch (DataAccessException e) {
				System.out.println("Cap nhat hoa don that bai!");
				
			}
		} else {
			query = "update hoa_don2 set ngay_tao = getdate(), thue = ?, trangthai = N'" + status + "' where hoadon_id = ?";	
			
			try {
				jdbcTemplate.update(query, new Object[] {thue, hoadon_id});
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
	
	
}
