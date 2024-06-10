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
	
	public int getNamDangKy(String khachhang_id) {
		int nam = -1;
		String query = "select YEAR(ngay_dangky) as nam_dangky from khachhang where khachhang_id = ?";
		
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
			String sql = "select * from khachhang where binary_checksum(khachhang_id) = binary_checksum(?) AND username is null";
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
	
	
	public boolean checkNhanVienIDandUsername(String nhanvien_id) {
		String sql = "select * from nhanvien where nhanvien_id = ? AND username is null";
		InfoNhanVien info = null;
		try {
			info = jdbcTemplate.queryForObject(sql, new Object[] {nhanvien_id}, new MapperInfoNhanVien());
		} catch (DataAccessException e) {
			System.out.println("Truy van nhanvien that bai");
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
	
	public int getTotalKhachHang() {
		int result = 0;
		
		String query = "select count(*) from khachhang";
		
		try {
			result = jdbcTemplate.queryForObject(query, Integer.class);
		} catch (DataAccessException e) {
			
		}
		
		return result;
	}
	
	public List<InfoNhanVien> getAllNhanVien(){
		List<InfoNhanVien> infos = new  ArrayList<InfoNhanVien>();
		String sql = "select * from nhanvien where nhanvien_id NOT IN ('QL001')";
		try {
			infos = jdbcTemplate.query(sql, new MapperInfoNhanVien());
			System.out.println("Truy van tat ca thong tin nhan vien thanh cong");
		} catch (DataAccessException e) {
			System.out.println("Truy van tat ca thong tin nhan vien that bai");
		}
		
		if (infos.size() == 0) {
			System.out.println("Khong co nhan vien de truy van");
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
	
	public boolean addNewNhanVien(String hoten, String gioitinh, String ngaysinh, String email, String sdt, String cccd, String diachi) {
		boolean isAdd = false;
		String sql = "insert into nhanvien (hovaten, gioitinh, ngaythangnam_sinh, email, sdt, cccd, diachi) "
				+ "values (?, ?, ?, ?, ?, ?, ?)";
		try {
			jdbcTemplate.update(sql, new Object[] {hoten, gioitinh, ngaysinh, email, sdt, cccd, diachi});
			isAdd = true;
			System.out.println("Them nhan vien moi thanh cong");
		}
		catch (DataAccessException e){
			System.out.println("Them nhan vien moi that bai");
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
	
	public boolean updateNhanVien(String hoten, String gioitinh, String ngaysinh, String email, String sdt, String cccd, String diachi, String nhanvien_id) {
		boolean isUpdate = false;
		
		String sql = "UPDATE nhanvien"
				+ " SET hovaten = ?, gioitinh = ?, ngaythangnam_sinh = ?, email =?, sdt =?, cccd = ?, diachi = ?"
				+ " WHERE nhanvien_id = ?";
		try {
			jdbcTemplate.update(sql, new Object[] {hoten, gioitinh, ngaysinh, email, sdt, cccd, diachi, nhanvien_id});
			isUpdate = true;
			System.out.println("Chinh sua nhan vien thanh cong");
		}
		catch (DataAccessException e){
			System.out.println("Chinh sua nhan vien that bai");
		}
		
		return isUpdate;
	}
	
	public boolean deleteKhachHang(String khachhang_id) {
		String sql = "exec sp_DeleteKhachHang @khachhang_id = ?";
		try {
			jdbcTemplate.update(sql, new Object[] {khachhang_id});
			System.out.println("Xoa khach hang thanh cong");
			return true;
		} catch (DataAccessException e) {
			System.out.println("Xoa khach hang that bai");
			return false;
		}

	}
	
	public boolean deleteNhanVien(String nhanvien_id) {
		String sql = "exec sp_DeleteNhanVienByNhanVienId @nhanvien_id = ?";
		try {
			jdbcTemplate.update(sql, new Object[] {nhanvien_id});
			System.out.println("Xoa nhan vien thanh cong");
			return true;
		} catch (DataAccessException e) {
			System.out.println("Xoa nhan vien that bai");
			return false;
		}

	}
	
	public boolean checkSoDienThoaiTrung (String sdt) {
		String sql = "select * from khachhang where sdt = ?";

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
	
	public boolean checkSoDienThoaiTrungNV (String sdt) {
		String sql = "select * from nhanvien where sdt = ?";
		System.out.println("select * from nhanvien where sdt = " + sdt);
		InfoNhanVien info = null;
		try {
			info = jdbcTemplate.queryForObject(sql, new Object[] {sdt}, new MapperInfoNhanVien());
		} catch (DataAccessException e) {
			System.out.println("Truy van nhanvien bang sdt that bai");
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
	
	public boolean checkCanCuocCongDanTrungNV (String cccd) {
		String sql = "select * from nhanvien where cccd = ?";
		System.out.println("select * from nhanvien where cccd = " + cccd);
		InfoNhanVien info = null;
		try {
			info = jdbcTemplate.queryForObject(sql, new Object[] {cccd}, new MapperInfoNhanVien());
		} catch (DataAccessException e) {
			System.out.println("Truy van nhanvien bang cccd that bai");
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
	
	public InfoNhanVien getNhanVienByID(String nhanvien_id){
		InfoNhanVien info = null;
		String sql = "select * from nhanvien where nhanvien_id = ?";
		try {
			info = jdbcTemplate.queryForObject(sql,new Object[] {nhanvien_id}, new MapperInfoNhanVien());
			System.out.println("Truy van thong tin nhan vien thanh cong");
		} catch (DataAccessException e) {
			System.out.println("Truy van thong tin nhan vien that bai");
		}
		
		if (info == null) {
			System.out.println("Khong co nhanvien de truy van");
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
	
	public boolean checkEmailTrungNV (String email) {
		String sql = "select * from nhanvien where email = ?";
		System.out.println("select * from nhanvien where email = " + email);
		InfoNhanVien info = null;
		try {
			info = jdbcTemplate.queryForObject(sql, new Object[] {email}, new MapperInfoNhanVien());
		} catch (DataAccessException e) {
			System.out.println("Truy van nhanvien bang email that bai");
			return false;
		}
		
		if (info == null) {
			return false;
		}
		
		return true;
	}
	
	public List<Info> searchKhachHang (String khachhang_id, String hoten, String gioitinh, String ngaysinh, String email, String sdt, String cccd, String diachi){
		List<Info> infos = new  ArrayList<Info>();
		List<Object> params = new ArrayList<>();
		List<String> conditions = new ArrayList<>();

		String sql = "exec sp_SearchInfoKhachHang @PageNumber = 1, @PageSize = 10,";

		if (!khachhang_id.isEmpty()) {
			conditions.add("@KhachHangID = ?");
			params.add(khachhang_id);
		}
		
		if (!hoten.isEmpty()) {
			conditions.add("@Hoten = N'" + hoten + "'");
		}
		
		if (!gioitinh.isEmpty()) {
			conditions.add("@GioiTinh = N'" + gioitinh + "'");
		}
		
		if (!ngaysinh.isEmpty()) {
			conditions.add("@NgaySinh = ?");
			params.add(ngaysinh);
		}
		
		if (!email.isEmpty()) {
			conditions.add("@Email = ?");
			params.add(email);
		}
		
		if (!sdt.isEmpty()) {
			conditions.add("@Sdt = ?");
			params.add(sdt);
		}
		
		if (!cccd.isEmpty()) {
			conditions.add("@Cccd = ?");
			params.add(cccd);
		}
		
		if (!diachi.isEmpty()) {
			conditions.add("@DiaChi = N'" + diachi + "'");
		}
		
		if (!conditions.isEmpty()) {
	        sql += " " + String.join(", ", conditions);
	    }
        
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
	
	
	public List<InfoNhanVien> searchNhanVien (String nhanvien_id, String hoten, String gioitinh, String ngaysinh, String email, String sdt, String cccd, String diachi){
		List<InfoNhanVien> infos = new  ArrayList<InfoNhanVien>();
		List<Object> params = new ArrayList<>();
		List<String> conditions = new ArrayList<>();

		String sql = "exec sp_SearchInfoNhanVien @PageNumber = 1, @PageSize = 10,";

		if (!nhanvien_id.isEmpty()) {
			conditions.add("@NhanVienID = ?");
			params.add(nhanvien_id);
		}
		
		if (!hoten.isEmpty()) {
			conditions.add("@Hoten = N'" + hoten + "'");
		}
		
		if (!gioitinh.isEmpty()) {
			conditions.add("@GioiTinh = N'" + gioitinh + "'");
		}
		
		if (!ngaysinh.isEmpty()) {
			conditions.add("@NgaySinh = ?");
			params.add(ngaysinh);
		}
		
		if (!email.isEmpty()) {
			conditions.add("@Email = ?");
			params.add(email);
		}
		
		if (!sdt.isEmpty()) {
			conditions.add("@Sdt = ?");
			params.add(sdt);
		}
		
		if (!cccd.isEmpty()) {
			conditions.add("@Cccd = ?");
			params.add(cccd);
		}
		
		if (!diachi.isEmpty()) {
			conditions.add("@DiaChi = N'" + diachi + "'");
		}
		
		if (!conditions.isEmpty()) {
	        sql += " " + String.join(", ", conditions);
	    }
        
        try {
			infos = jdbcTemplate.query(sql,params.toArray(), new MapperInfoNhanVien());
			System.out.println("Tim kiem thong tin nhan vien thanh cong");
		} catch (DataAccessException e) {
			System.out.println("Tim kiem thong tin nhan vien that bai");
		}
        
        if (infos.size() == 0) {
			System.out.println("Khong co nhan vien can tim");
		}
	        
		return infos;
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
		String sql = "UPDATE khachhang SET ngay_dangky = ? where khachhang_id = ?";
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
	
	public List<Info> getAccBySearch(String khachhang_id,String dongho_id)
	{
		String sql = "select * from khachhang where khachhang_id like '%' + ? + '%' and dongho_id like '%' + ? + '%'";

		List<Info> account = new ArrayList<Info>();
		try {

			account = jdbcTemplate.query(sql,new Object[] {khachhang_id,dongho_id} ,new MapperInfo());
			System.out.println("Truy van tai khoan nguoi dung thanh cong!!");
		} catch (DataAccessException e) {
			System.out.println("Truy van tai khoan nguoi dung that bai!!");
		}
		
		if (account.isEmpty()) {
	        System.out.println("Không có tài khoản nào được trả về");

	    }
		return account;
	}



	
	
//	============================================== Phan trang ==================================
	// Tong trang binh thuong
	public int getToTalPageAllKhachHang()
	{
		int temp = -1;
		String sql = "exec sp_GetTotalPagesAllAccKhachHang @PageSize = 10";
		try {
			  temp = jdbcTemplate.queryForObject(sql, Integer.class);	
			return temp;
		} catch (DataAccessException e) {
			System.out.println("111");
		}
		return temp;
	}
	
	
	public int getToTalPageAllNhanVien()
	{
		int temp = -1;
		String sql = "exec sp_GetTotalPagesAllAccNhanVien @PageSize = 10";
		try {
			  temp = jdbcTemplate.queryForObject(sql, Integer.class);	
			  System.out.println(temp);
			return temp;
		} catch (DataAccessException e) {
			System.out.println("111");
		}
		
		return temp;
	}
	
	
	public int getToTalPageDongHoKhachHang(String khid, String dhid,String tuNgay, String denNgay)
	{
		int temp = -1;
		String sql = "exec sp_GetTotalPagesAllDongHoKhachHangSearch @PageSize = 10, @KhachHangId = ?, @DongHoId = ?,@TuNgay = ?, @DenNgay = ? ";
		try {
			  temp = jdbcTemplate.queryForObject(sql,new Object[]{khid,dhid,tuNgay,denNgay}, Integer.class);	
			return temp;
		} catch (DataAccessException e) {
			System.out.println("111");
		}
		return temp;
	}
	
	
	
	
	public List<Info> getPageDongHoKhachHangBySearch(int page ,String khid, String dhid,String tuNgay, String denNgay)
	{
		List<Info> listdh = new ArrayList<Info>();
		String sql = "exec sp_GetPagedAllDongHoKhachHangSearch  @PageNumber = ?, @PageSize = 10, @KhachHangId = ?, @DongHoId = ?,@TuNgay = ?, @DenNgay = ? ";
		try {
			  listdh = jdbcTemplate.query(sql,new Object[]{page, khid,dhid,tuNgay,denNgay},new MapperInfo());	
			  System.out.println("Truy van dong ho dien nguoi dung thanh cong!!");
		} catch (DataAccessException e) {
			System.out.println("1123");
		}
		if (listdh.isEmpty()) {
	        System.out.println("Truy van dong ho dien nguoi dung thất bại!!");

	    }
		return listdh;
	}

	
	public List<Info> getPageDongHoKhachHang(int page)
	{
		List<Info> listdh = new ArrayList<Info>();
		String sql = "exec sp_GetPagedAllInfoKhachHang  @PageNumber = ?, @PageSize = 10";
		try {
			  listdh = jdbcTemplate.query(sql,new Object[]{page},new MapperInfo());	
			  System.out.println("Truy van dong ho dien nguoi dung thanh cong!!");
		} catch (DataAccessException e) {
			System.out.println("122311");
		}
		if (listdh.isEmpty()) {
	        System.out.println("Truy van dong ho dien nguoi dung thất bại!!");

	    }
		return listdh;
	}
	
	
	
	public String getNgayThangNamSinhKH(String khid)
	{
		String ntns = null;
		String sql = "select  ngaythangnam_sinh from khachhang where khachhang_id = ?";
		
		try {
			ntns = jdbcTemplate.queryForObject(sql,new Object[]{khid}, String.class);	
			return ntns;
		} catch (DataAccessException e) {
			System.out.println("111");
		}
		return ntns;
	}



	
	// Tổng trang tìm kiếm
	public int tong_trang_search(String khachhang_id, String hoten, String gioitinh, String ngaysinh, String email, String sdt, String cccd, String diachi)
	{
		int temp = -1;
		List<Object> params = new ArrayList<>();
		List<String> conditions = new ArrayList<>();
		
		String sql = "exec sp_GetTotalPagesAllSearchInfoKhachHang @PageSize = 10,";
		
		if (!khachhang_id.isEmpty()) {
			conditions.add("@KhachHangID = ?");
			params.add(khachhang_id);
		}
		
		if (!hoten.isEmpty()) {
			conditions.add("@Hoten = N'" + hoten + "'");
		}
		
		if (!gioitinh.isEmpty()) {
			conditions.add("@GioiTinh = N'" + gioitinh + "'");
		}
		
		if (!ngaysinh.isEmpty()) {
			conditions.add("@NgaySinh = ?");
			params.add(ngaysinh);
		}
		
		if (!email.isEmpty()) {
			conditions.add("@Email = ?");
			params.add(email);
		}
		
		if (!sdt.isEmpty()) {
			conditions.add("@Sdt = ?");
			params.add(sdt);
		}
		
		if (!cccd.isEmpty()) {
			conditions.add("@Cccd = ?");
			params.add(cccd);
		}
		
		if (!diachi.isEmpty()) {
			conditions.add("@DiaChi = N'" + diachi + "'");
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
	
	
	public int tong_trang_search_nhan_vien(String nhanvien_id, String hoten, String gioitinh, String ngaysinh, String email, String sdt, String cccd, String diachi)
	{
		int temp = -1;
		List<Object> params = new ArrayList<>();
		List<String> conditions = new ArrayList<>();
		
		String sql = "exec sp_GetTotalPagesAllSearchInfoNhanVien @PageSize = 10,";
		
		if (!nhanvien_id.isEmpty()) {
			conditions.add("@KhachHangID = ?");
			params.add(nhanvien_id);
		}
		
		if (!hoten.isEmpty()) {
			conditions.add("@Hoten = N'" + hoten + "'");
		}
		
		if (!gioitinh.isEmpty()) {
			conditions.add("@GioiTinh = N'" + gioitinh + "'");
		}
		
		if (!ngaysinh.isEmpty()) {
			conditions.add("@NgaySinh = ?");
			params.add(ngaysinh);
		}
		
		if (!email.isEmpty()) {
			conditions.add("@Email = ?");
			params.add(email);
		}
		
		if (!sdt.isEmpty()) {
			conditions.add("@Sdt = ?");
			params.add(sdt);
		}
		
		if (!cccd.isEmpty()) {
			conditions.add("@Cccd = ?");
			params.add(cccd);
		}
		
		if (!diachi.isEmpty()) {
			conditions.add("@DiaChi = N'" + diachi + "'");
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
	
	
	public List<Info> getAllPageInfoKhachHang(int page) {
		List<Info> list_info = new ArrayList<Info>();
		
		String query = "exec sp_GetPagedAllInfoKhachHang @PageNumber = ?, @PageSize = 10";
		
		try {
			list_info = jdbcTemplate.query(query, new Object[] {page}, new MapperInfo());
			System.out.println("Truy van tat ca hoa don page " + page + " thanh cong");
		} catch (DataAccessException e) {
			System.out.println("Truy van tat ca hoa don page " + page + " that bai");
		}
		
		return list_info;
	}
	
	public List<InfoNhanVien> getAllPageInfoNhanVien(int page) {
		List<InfoNhanVien> list_info = new ArrayList<InfoNhanVien>();
		
		String query = "exec sp_GetPagedAllInfoNhanVien @PageNumber = ?, @PageSize = 10";
		
		try {
			list_info = jdbcTemplate.query(query, new Object[] {page}, new MapperInfoNhanVien());
			System.out.println("Truy van tat ca nhan vien page " + page + " thanh cong");
		} catch (DataAccessException e) {
			System.out.println("Truy van tat ca nhan vien page " + page + " that bai");
		}
		
		return list_info;
	}
	
	
	
	

}
