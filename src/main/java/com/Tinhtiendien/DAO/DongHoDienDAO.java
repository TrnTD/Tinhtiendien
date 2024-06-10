package com.Tinhtiendien.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.Tinhtiendien.Entity.MapperDongHoDien;
import com.Tinhtiendien.Models.*;

@Repository
public class DongHoDienDAO {
	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	public int getNamDangKy(String khachhang_id) {
		int nam = -1;
		String query = "select YEAR(ngay_dangky) as nam_dangky from donghodien where khachhang_id = ?";
		
		try {
			nam = jdbcTemplate.queryForObject(query, Integer.class, khachhang_id);
			System.out.println("Truy van nam tu dong ho dien thanh cong!");
		} catch (DataAccessException e) {
			System.out.println("Truy van nam tu dong ho dien that bai!");
		}
		
		if (nam == -1) {
			System.out.println("Khong co nam nao duoc tra ve");
		}
		
		return nam;
	}
	
	public String getMaDongHo(String khachhang_id) {
		String ma = "";
		
		String query = "select dongho_id from donghodien where khachhang_id = ?";
		
		try {
			ma = jdbcTemplate.queryForObject(query, String.class, khachhang_id);
			System.out.println("Lay ma dong ho dien tu ma khach hang thanh cong");
		} catch (DataAccessException e) {
			System.out.println("Lay ma dong ho dien tu ma khach hang that bai");
		}
		
		if (ma == null) {
			System.out.println("Khong co ma dong ho nao duoc tra ve");
		}
		
		return ma;
	}
	
	public boolean checkKhachHangInDongHoDien(String khachhang_id) {
		String sql = "select * from donghodien where khachhang_id = ?";
		List<DongHoDien> dhd = null;
		try {
			dhd = jdbcTemplate.query(sql, new Object[] {khachhang_id}, new MapperDongHoDien());
			System.out.println("Truy van thong tin dong ho dien bang id thanh cong");
		} catch (DataAccessException e) {
			System.out.println("Truy van thong tin dong ho dien bang id that bai");
		}
		
		if (dhd != null && dhd.size() == 0) {
			return false;
		}	
		return true;
	}
	
	public String updateDongHoId(String khachhang_id,String thong_bao) {
		String sql = "exec sp_UpdateNewDongHoId @khachhang_id = ?";
		int result = 0;
		try {
			result =jdbcTemplate.update(sql,new Object[] {khachhang_id});
			thong_bao= "Cập nhập đồng hồ thành công!";
			System.out.println("Cập nhập đồng hồ thành công!");
		} catch (DataAccessException e) {
			thong_bao= "Cập nhập đồng hồ thất bại!";
		}
		
		return thong_bao;
	}
	
	public String updateNgayDangKy(String khachhang_id,String ngay,String thong_bao) {
		String sql = "UPDATE donghodien SET ngay_dangky = ? where khachhang_id = ?";
		int result = 0;
		try {
			result =jdbcTemplate.update(sql,new Object[] {ngay,khachhang_id});
			thong_bao= "Cập nhập ngày đăng ký thành công!";
			System.out.println("Cập nhập ngày đăng ký thành công!");
		} catch (DataAccessException e) {
			thong_bao= "Cập nhập ngày đăng ký thất bại!";
		}
		return thong_bao;
	}
	
//	========================== Phân trang ========================================
	public int getToTalPageAllDongHoKhachHang()
	{
		int temp = -1;
		String sql = "exec sp_GetTotalPagesAllDongHoKhachHang @PageSize = 10";
		try {
			  temp = jdbcTemplate.queryForObject(sql, Integer.class);	
			return temp;
		} catch (DataAccessException e) {
			System.out.println("111");
		}
		return temp;
	}
	public List<DongHoDien> getPageAllDongHoKhachHang(int page)
	{
		List<DongHoDien> listdh = new ArrayList<DongHoDien>();
		String sql = "exec sp_GetPagedAllDongHoKhachHang @PageNumber = ? , @PageSize = 10";
		try {
			  listdh = jdbcTemplate.query(sql,new Object[]{page},new MapperDongHoDien());	
			  System.out.println("Truy van dong ho dien nguoi dung thanh cong!!");
		} catch (DataAccessException e) {
			System.out.println("111");
		}
		if (listdh.isEmpty()) {
	        System.out.println("Truy van dong ho dien nguoi dung thất bại!!");

	    }
		return listdh;
	}
	
	public int getToTalPageDongHoKhachHang(String khid, String dhid,String tuNgay, String denNgay)
	{
		int temp = -1;
		String sql = "exec sp_GetTotalPagesAllDongHoKhachHangSearch @PageSize = 3, @KhachHangId = ?, @DongHoId = ?,@TuNgay = ?, @DenNgay = ? ";
		try {
			  temp = jdbcTemplate.queryForObject(sql,new Object[]{khid,dhid,tuNgay,denNgay}, Integer.class);	
			return temp;
		} catch (DataAccessException e) {
			System.out.println("111");
		}
		return temp;
	}
	
	public List<DongHoDien> getPageDongHoKhachHangBySearch(int page ,String khid, String dhid,String tuNgay, String denNgay)
	{
		List<DongHoDien> listdh = new ArrayList<DongHoDien>();
		String sql = "exec sp_GetPagedAllDongHoKhachHangSearch  @PageNumber = ?, @PageSize = 3, @KhachHangId = ?, @DongHoId = ?,@TuNgay = ?, @DenNgay = ? ";
		try {
			  listdh = jdbcTemplate.query(sql,new Object[]{page, khid,dhid,tuNgay,denNgay},new MapperDongHoDien());	
			  System.out.println("Truy van dong ho dien nguoi dung thanh cong!!");
		} catch (DataAccessException e) {
			System.out.println("111");
		}
		if (listdh.isEmpty()) {
	        System.out.println("Truy van dong ho dien nguoi dung thất bại!!");

	    }
		return listdh;
	}
}