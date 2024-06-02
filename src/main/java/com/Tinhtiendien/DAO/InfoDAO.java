package com.Tinhtiendien.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.Tinhtiendien.Entity.MapperInfo;
import com.Tinhtiendien.Entity.MapperInfoNhanVien;
import com.Tinhtiendien.Models.*;

@Repository
public class InfoDAO {
	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	public Info getAllInfoKhachHang(String username) {
		String sql = "select * from khachhang where username = ? or khachhang_id = ?";
		Info info = null;
		try {
			info = jdbcTemplate.queryForObject(sql, new Object[] {username, username}, new MapperInfo());
			System.out.println("Truy van thong tin khach hang thanh cong 1");
		} catch (DataAccessException e) {
			System.out.println("Truy van thong tin khach hang that bai 1");
		}
		
		return info;
	}
	
	public InfoNhanVien getAllInfoNhanVien(String username) {
		String sql = "select * from nhanvien where username = ? or nhanvien_id = ?";
		InfoNhanVien infonv = null;
		try {
			infonv = jdbcTemplate.queryForObject(sql, new Object[] {username, username}, new MapperInfoNhanVien());
			System.out.println("Truy van thong tin khach hang thanh cong 1");
		} catch (DataAccessException e) {
			System.out.println("Truy van thong tin khach hang that bai 1");
		}
		
		return infonv;
	}
	
	public boolean checkUsernameKhachHang(String username) {
		String sql = "select * from khachhang where username = ?";
		List<Info> infos = null;
		try {
			infos = jdbcTemplate.query(sql, new Object[] {username}, new MapperInfo());
			System.out.println("Truy van thong tin khach hang thanh cong 2");
		} catch (DataAccessException e) {
			System.out.println("Truy van thong tin khach hang that bai 2");
		}
		
		if (infos != null && infos.size() == 0) {
			return false;
		}
		
		return true;
	}

	public boolean checkKhachHangIDandUsername(String khachhangid) {
			String sql = "select * from khachhang where khachhang_id = ? AND username is null";
			Info info = null;
			try {
				info = jdbcTemplate.queryForObject(sql, new Object[] {khachhangid}, new MapperInfo());
			} catch (DataAccessException e) {
				System.out.println("Truy van khachhang that bai");
			}
			
			if (info == null) {
				return false;
			}
			return true;
		}
	
	public boolean checkExistKhachHangById(String khachhang_id) {
		
		String query = "select * from khachhang where khachhang_id = ?";
		Info info = null;
		
		try {
			info = jdbcTemplate.queryForObject(query, new Object[] {khachhang_id}, new MapperInfo());
			System.out.println("Ton tai khach hang voi khachhang_id: " + khachhang_id);
		} catch (DataAccessException e) {
			System.out.println("Co loi khi truy van khachhang_id: " + khachhang_id);
		}
		
		if (info != null) {
			return true;
		}
		
		return false;
	}
	
	public boolean checkExistKhachHangByDongHoId(String dongho_id) {
		String query = "select * from khachhang where dongho_id = ?";
		Info info = null;
		
		try {
			info = jdbcTemplate.queryForObject(query, new Object[] {dongho_id}, new MapperInfo());
			System.out.println("Ton tai khach hang voi khachhang_id: " + dongho_id);
		} catch (DataAccessException e) {
			System.out.println("Co loi khi truy van khachhang_id: " + dongho_id);
		}
		
		if (info != null) {
			return true;
		}
		
		return false;
	}
	
	public List<Info> getAllKhachHang(){
		List<Info> infos = new  ArrayList<Info>();
		String sql = "select * from khachhang";
		try {
			infos = jdbcTemplate.query(sql, new MapperInfo());
			System.out.println("Truy van tat ca thong tin khach hang thanh cong");
		} catch (DataAccessException e) {
			System.out.println("Truy van tat ca thong tin khach hang that bai");
		}
		
		if (infos.size() == 0) {
			System.out.println("Khong co khach hang de truy van");
		}
		
		return infos;
	}
	
	public boolean addNewKhachHang(String hoten, String gioitinh, String ngaysinh, String email, String sdt, String cccd, String diachi) {
		boolean isAdd = false;
		String sql = "insert into khachhang (hovaten, gioitinh, ngaythangnam_sinh, email, sdt, cccd, diachi) "
				+ "values (?, ?, ?, ?, ?, ?, ?)";
		try {
			jdbcTemplate.update(sql, new Object[] {hoten, gioitinh, ngaysinh, email, sdt, cccd, diachi});
			isAdd = true;
			System.out.println("Them khach hang moi thanh cong");
		}
		catch (DataAccessException e){
			System.out.println("Them khach hang moi that bai");
		}
		
		return isAdd;
	}
	
	public boolean updateKhachHang(String hoten, String gioitinh, String ngaysinh, String email, String sdt, String cccd, String diachi, String khachhang_id) {
		boolean isUpdate = false;
		
		String sql = "UPDATE khachhang"
				+ " SET hovaten = ?, gioitinh = ?, ngaythangnam_sinh = ?, email =?, sdt =?, cccd = ?, diachi = ?"
				+ " WHERE khachhang_id = ?";
		try {
			jdbcTemplate.update(sql, new Object[] {hoten, gioitinh, ngaysinh, email, sdt, cccd, diachi, khachhang_id});
			isUpdate = true;
			System.out.println("Chinh sua khach hang thanh cong");
		}
		catch (DataAccessException e){
			System.out.println("Chinh sua khach hang that bai");
		}
		
		return isUpdate;
	}
	
	public boolean deleteKhachHang(String khachhang_id) {
		String sql = "delete khachhang where khachhang_id = ? AND username is null";
		try {
			jdbcTemplate.update(sql, new Object[] {khachhang_id});
			System.out.println("Xoa khach hang thanh cong");
			return true;
		} catch (DataAccessException e) {
			System.out.println("Xoa khach hang that bai");
			return false;
		}

	}
	
	public boolean checkSoDienThoaiTrung (String sdt) {
		String sql = "select * from khachhang where sdt = ?";
		System.out.println("select * from khachhang where sdt = " + sdt);
		Info info = null;
		try {
			info = jdbcTemplate.queryForObject(sql, new Object[] {sdt}, new MapperInfo());
		} catch (DataAccessException e) {
			System.out.println("Truy van khachhang bang sdt that bai");
		}
		
		if (info == null) {
			return false;
		}
		
		return true;
	}
	
	public boolean checkCanCuocCongDanTrung (String cccd) {
		String sql = "select * from khachhang where cccd = ?";
		System.out.println("select * from khachhang where cccd = " + cccd);
		Info info = null;
		try {
			info = jdbcTemplate.queryForObject(sql, new Object[] {cccd}, new MapperInfo());
		} catch (DataAccessException e) {
			System.out.println("Truy van khachhang bang cccd that bai");
		}
		
		if (info == null) {
			return false;
		}
		return true;
	}
	
	public Info getKhachHangByID(String khachhang_id){
		Info info = null;
		String sql = "select * from khachhang where khachhang_id = ?";
		try {
			info = jdbcTemplate.queryForObject(sql,new Object[] {khachhang_id}, new MapperInfo());
			System.out.println("Truy van thong tin khach hang thanh cong");
		} catch (DataAccessException e) {
			System.out.println("Truy van thong tin khach hang that bai");
		}
		
		if (info == null) {
			System.out.println("Khong co khach hang de truy van");
		}
		
		return info;
	}
	
	public boolean checkEmailTrung (String email) {
		String sql = "select * from khachhang where email = ?";
		System.out.println("select * from khachhang where sdt = " + email);
		Info info = null;
		try {
			info = jdbcTemplate.queryForObject(sql, new Object[] {email}, new MapperInfo());
		} catch (DataAccessException e) {
			System.out.println("Truy van khachhang bang email that bai");
		}
		
		if (info == null) {
			return false;
		}
		
		return true;
	}
	
	public List<Info> searchKhachHang (String khachhang_id, String hoten, String gioitinh, String ngaysinh, String email, String sdt, String cccd, String diachi){
		List<Info> infos = new  ArrayList<Info>();
		 String sql = "SELECT * FROM khachhang WHERE 1=1";
		 List<Object> params = new ArrayList<>();

	        if (khachhang_id != null && !khachhang_id.isEmpty()) {
	            sql += " AND LOWER(khachhang_id) LIKE LOWER(?)";
	            params.add("%" + khachhang_id + "%");
	        }
	        if (hoten != null && !hoten.isEmpty()) {
	            sql += " AND LOWER(hovaten) LIKE LOWER(?)";
	            params.add("%" + hoten + "%");
	        }
	        if (gioitinh != null && !gioitinh.isEmpty()) {
	            sql += " AND gioitinh = ?";
	            params.add(gioitinh);
	        }
	        if (ngaysinh != null && !ngaysinh.isEmpty()) {
	            sql += " AND ngaythangnam_sinh = ?";
	            params.add(ngaysinh);
	        }
	        if (email != null && !email.isEmpty() && !email.equals(".")) {
	        	sql += " AND LOWER(email) LIKE LOWER(?)";
	            params.add("%" + email + "%");
	        } else if (email.equals(".")) {
	        	sql += " AND email = ''";
	        }
	        if (sdt != null && !sdt.isEmpty()) {
	            sql += " AND sdt = ?";
	            params.add(sdt);
	        }
	        if (cccd != null && !cccd.isEmpty()) {
	            sql += " AND cccd = ?";
	            params.add(cccd);
	        }
	        if (diachi != null && !diachi.isEmpty()) {
	            sql += " AND LOWER(diachi) LIKE LOWER(?)";
	            params.add("%" + diachi + "%");
	        }
	        
	        System.out.println(sql);
	        
	        try {
				infos = jdbcTemplate.query(sql,params.toArray(), new MapperInfo());
				System.out.println("Tim kiem thong tin khach hang thanh cong");
			} catch (DataAccessException e) {
				System.out.println("Tim kiem thong tin khach hang that bai");
			}
	        
	        if (infos.size() == 0) {
				System.out.println("Khong co khach hang can tim");
			}
	        
		return infos;
	}

}
