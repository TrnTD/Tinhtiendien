package com.Tinhtiendien.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Tinhtiendien.Entity.*;
import com.Tinhtiendien.Models.*;
import com.Tinhtiendien.DAO.*;

@Controller
public class NhanVienController {

	@Autowired
	InfoDAO infoDAO = new InfoDAO();

	@Autowired
	QuanLyAccountDAO qlaccountDAO = new QuanLyAccountDAO();
	
	@Autowired
	DongHoDienDAO donghodienDAO = new DongHoDienDAO();
	
	@Autowired
	MeasurementHistoryDAO mdDAO = new MeasurementHistoryDAO();
	
	@Autowired
	LichGhiChiSoDAO lgcsDAO = new LichGhiChiSoDAO();
	
	@Autowired
	AccountDAO account= new AccountDAO();

	@RequestMapping(value = "/nhan_vien/quan_ly_thong_tin_khach_hang", method = RequestMethod.GET)
	public String quanly_thongtinkhachhang(Model model) {
		List<Info> listKH = infoDAO.getAllKhachHang();
		model.addAttribute("listKH", listKH);

		return "employee/quan_ly_thong_tin_khach_hang";
	}

	@RequestMapping(value = "/nhan_vien/quan_ly_thong_tin_khach_hang/them", method = RequestMethod.POST, params = {
			"hoten", "gioitinh", "ngay", "thang", "nam", "email", "sdt", "cccd", "diachi" })
	public String them_moi_thongtinnguoidung(Model model, @RequestParam("hoten") String hoten,
			@RequestParam("gioitinh") String gioitinh, @RequestParam("ngay") String ngay,
			@RequestParam("thang") String thang, @RequestParam("nam") String nam, @RequestParam("email") String email,
			@RequestParam("sdt") String sdt, @RequestParam("cccd") String cccd, @RequestParam("diachi") String diachi) {
		// Xử lý họ tên không bị lỗi
		if (hoten != null) {
			try {
				hoten = new String(hoten.getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		// Xử lý địa chỉ không bị lỗi
		if (diachi != null) {
			try {
				diachi = new String(diachi.getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		if (gioitinh.equals("nam")) {
			gioitinh = "Nam";
		} else if (gioitinh.equals("nu")) {
			gioitinh = "Nữ";
		}
		String ngaysinh = nam + "-" + thang + "-" + ngay;
		
		String message = "";
		if (infoDAO.addNewKhachHang(hoten, gioitinh, ngaysinh, email, sdt, cccd, diachi)) {
			message  = "Bạn đã thêm khách hàng thành công";
		} else {
			message  = "Bạn đã thêm khách hàng thất bại";
		}
			
		model.addAttribute("message", message);
		
		return "employee/thong_bao";
	}

	@RequestMapping(value = "/nhan_vien/quan_ly_thong_tin_khach_hang/sua", method = RequestMethod.POST, params = {
			"hoten", "gioitinh", "ngay", "thang", "nam", "email", "sdt", "cccd", "diachi", "khachhang_id" })
	public String chinh_sua_thongtinnguoidung(Model model, @RequestParam("hoten") String hoten,
			@RequestParam("gioitinh") String gioitinh, @RequestParam("ngay") String ngay,
			@RequestParam("thang") String thang, @RequestParam("nam") String nam, @RequestParam("email") String email,
			@RequestParam("sdt") String sdt, @RequestParam("cccd") String cccd, @RequestParam("diachi") String diachi,
			@RequestParam("khachhang_id") String khachhang_id) {

		// Xử lý họ tên không bị lỗi
		if (hoten != null) {
			try {
				hoten = new String(hoten.getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		// Xử lý địa chỉ không bị lỗi
		if (diachi != null) {
			try {
				diachi = new String(diachi.getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		if (gioitinh.equals("nam")) {
			gioitinh = "Nam";
		} else if (gioitinh.equals("nu")) {
			gioitinh = "Nữ";
		}
		String ngaysinh = nam + "-" + thang + "-" + ngay;

		infoDAO.updateKhachHang(hoten, gioitinh, ngaysinh, email, sdt, cccd, diachi, khachhang_id);

		model.addAttribute("message", "Bạn đã chỉnh sửa thành công khách hàng có mã: ");
		model.addAttribute("khachhang_id", khachhang_id);
		return "employee/thong_bao";
	}
	
	@RequestMapping(value = "/nhan_vien/quan_ly_thong_tin_khach_hang/xoa",method = RequestMethod.POST, params = { "kh_id"})
	public String xoa_thongtinnguoidung(Model model,
											@RequestParam("kh_id") String khachhang_id) {
		if (mdDAO.checkKhachHangInLSD(khachhang_id) == true || lgcsDAO.checkKhachHangInLGCS(khachhang_id) == true || donghodienDAO.checkKhachHangInDongHoDien(khachhang_id) == true) {
			model.addAttribute("message", "Không thể xóa vì xung đột dữ liệu");
			return "employee/thong_bao";
		} else {
			infoDAO.deleteKhachHang(khachhang_id);
			model.addAttribute("message", "Bạn đã xóa thành công khách hàng có mã: ");
			model.addAttribute("khachhang_id", khachhang_id);
			return "employee/thong_bao";
		}
	}
	
	
	
	

	@RequestMapping("/nhan_vien/quan_ly_tai_khoan_khach_hang")
	public String layChuKiDO(HttpSession session, Model model) {
		List<QuanLyAccount> listAcc = qlaccountDAO.getAllAccountsUser();

		model.addAttribute("list_acc", listAcc);

		return "employee/quan_ly_tai_khoan_khach_hang";
	}

	@RequestMapping(value = "/nhan_vien/quan_ly_tai_khoan_khach_hang", method = RequestMethod.POST)
	public String xu_li_hanh_dong(HttpSession session, Model model, @RequestParam("selectedUsername") String username,
			@RequestParam("action") String action, @RequestParam("addUsernameId") String addUsernameId,
			@RequestParam("addUsername") String addUsername, @RequestParam("addPassWord") String addPassWord,
			@RequestParam("newPass") String newPass) {
		if (action.equals("edit")) {
			if (newPass != "") {
				qlaccountDAO.changePassword(username, newPass);
			}
		} else if (action.equals("add")) {
			if(infoDAO.checkKhachHangIDandUsername(addUsernameId)== true && account.checkUsernameAccount(addUsername) == false ) {
				account.register(addUsernameId, addUsername, addPassWord);
			}
		} else {
			qlaccountDAO.deleteAcc(username);
		}
		
		return "redirect:/nhan_vien/quan_ly_tai_khoan_khach_hang";
	}
}
