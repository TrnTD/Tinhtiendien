package com.Tinhtiendien.controller;

import java.io.UnsupportedEncodingException;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.taglibs.standard.tag.el.fmt.RequestEncodingTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.MessageCodeFormatter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Tinhtiendien.Entity.*;
import com.Tinhtiendien.Models.*;
import com.Tinhtiendien.DAO.*;
import KtraDuLieu.KtraDuLieu;
import KtraDuLieu.isError;
import KtraDuLieu.isSuccess;

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
	
	@Autowired
	HoaDonDAO hoadonDAO= new HoaDonDAO();
	
	@Autowired
	GiaDienDAO giaDien_DAO = new GiaDienDAO();
	
	@RequestMapping(value = "/nhan_vien/quan_ly_chung", method = RequestMethod.GET)
	public String quan_ly_chung(HttpServletRequest request) {
		
//		List<HoaDon> list_doanhthu = hoadonDAO.get_doanhthu_by_year(2020);
		
		List<Integer> list_dientieuthu = hoadonDAO.get_dientieuthu_by_year(2010);
		List<Integer> list_sotien = hoadonDAO.get_doanhthu_by_year(2010);
		
//		for (HoaDon hoadon : list_doanhthu) {
//			list_dientieuthu.add(hoadon.getDien_tieu_thu());
//			list_sotien.add(hoadon.getSo_tien());
//		}
		
		request.setAttribute("list_dientieuthu", list_dientieuthu);
		request.setAttribute("list_sotien", list_sotien);
		
		
		return "employee/quan_ly_chung";
	}
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////// QUAN LY LICH THONG TIN KHACH HANG ///////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value = "/nhan_vien/quan_ly_thong_tin_khach_hang", method = RequestMethod.GET)
	public String quanly_thongtinkhachhang(Model model) {
		List<Info> listKH = infoDAO.getAllKhachHang();
		model.addAttribute("listKH", listKH);

		return "employee/quan_ly_thong_tin_khach_hang";
	}

	@RequestMapping(value = "/nhan_vien/quan_ly_thong_tin_khach_hang/them", method = RequestMethod.POST, params = {
			"hoten", "gioitinh", "ngay", "thang", "nam", "email", "sdt", "cccd", "diachi" })
	public String them_moi_thongtinnguoidung(Model model,HttpSession session,HttpServletRequest request,RedirectAttributes redirectAttributes, @RequestParam("hoten") String hoten,
			@RequestParam("gioitinh") String gioitinh, @RequestParam("ngay") String ngay,
			@RequestParam("thang") String thang, @RequestParam("nam") String nam, @RequestParam("email") String email,
			@RequestParam("sdt") String sdt, @RequestParam("cccd") String cccd, @RequestParam("diachi") String diachi) {
		boolean isError = false;
		boolean canAdd = true;
		String message = "";	
		
		// Xử lý họ tên không bị lỗi
		if (hoten != null) {
			try {
				hoten = new String(hoten.getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		hoten = KtraDuLieu.chuanHoaTen(hoten);

		// Xử lý địa chỉ không bị lỗi
		if (diachi != null) {
			try {
				diachi = new String(diachi.getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		diachi = KtraDuLieu.chuanHoaTen(diachi);
		
		if (gioitinh.equals("nam")) {
			gioitinh = "Nam";
		} else if (gioitinh.equals("nu")) {
			gioitinh = "Nữ";
		}
		String ngaysinh = nam + "-" + thang + "-" + ngay;
		
		String url = request.getHeader("Referer");
		int index = url.indexOf("/nhan_vien");
		url = url.substring(index);
		
		if (!email.isEmpty()) {
			if (infoDAO.checkEmailTrung(email) == true) {
				redirectAttributes.addFlashAttribute("err_mess_addEmail", "Email đã được sử dụng!");
				isError = true;
				canAdd = false;
			}
		}
		
		if (infoDAO.checkSoDienThoaiTrung(sdt) == true) {
			redirectAttributes.addFlashAttribute("err_mess_addPhone", "Số điện thoại đã được sử dụng!");
			isError = true;
			canAdd = false;
		} 
		
		if (infoDAO.checkCanCuocCongDanTrung(cccd) == true) {	
			redirectAttributes.addFlashAttribute("err_mess_addCCCD", "Căn cước đã được sử dụng!");
			isError = true;
			canAdd = false;
		} 
		if (canAdd == true) {
			if (infoDAO.addNewKhachHang(hoten, gioitinh, ngaysinh, email, sdt, cccd, diachi)) {
				message  = "Bạn đã thêm khách hàng thành công";
				session.setAttribute("message", message);
				session.setAttribute("isError", isError);
				return "redirect:" + url;
			} else {
				message  = "Thêm khách hàng không thành công";
				isError = true;
				session.setAttribute("message", message);
				session.setAttribute("isError", isError);
				return "redirect:" + url;
			}
		}
	
		return "redirect:" + url;
	}

	@RequestMapping(value = "/nhan_vien/quan_ly_thong_tin_khach_hang/sua", method = RequestMethod.POST, params = {
			"hoten", "gioitinh", "ngay", "thang", "nam", "email", "sdt", "cccd", "diachi", "khachhang_id" })
	public String chinh_sua_thongtinnguoidung(Model model, HttpSession session,HttpServletRequest request,RedirectAttributes redirectAttributes, @RequestParam("hoten") String hoten,
			@RequestParam("gioitinh") String gioitinh, @RequestParam("ngay") String ngay,
			@RequestParam("thang") String thang, @RequestParam("nam") String nam, @RequestParam("email") String email,
			@RequestParam("sdt") String sdt, @RequestParam("cccd") String cccd, @RequestParam("diachi") String diachi,
			@RequestParam("khachhang_id") String khachhang_id) {

		boolean isError = false;
		boolean canUpdate = true;
		String message = "";	
		
		System.out.println(khachhang_id);
		// Lấy thông tin khách hàng hiện tại
		Info currentKhachHang = infoDAO.getKhachHangByID(khachhang_id);
		
		// Xử lý họ tên không bị lỗi
		if (hoten != null) {
			try {
				hoten = new String(hoten.getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		hoten = KtraDuLieu.chuanHoaTen(hoten);

		// Xử lý địa chỉ không bị lỗi
		if (diachi != null) {
			try {
				diachi = new String(diachi.getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		diachi = KtraDuLieu.chuanHoaTen(diachi);
		
		if (gioitinh.equals("nam")) {
			gioitinh = "Nam";
		} else if (gioitinh.equals("nu")) {
			gioitinh = "Nữ";
		}
		String ngaysinh = nam + "-" + thang + "-" + ngay;
		
		String url = request.getHeader("Referer");
		int index = url.indexOf("/nhan_vien");
		url = url.substring(index);
		
		if (!currentKhachHang.getEmail().equals(email) && email != null) {
			if (infoDAO.checkEmailTrung(email) == true) {
				redirectAttributes.addFlashAttribute("err_mess_editEmail", "Email đã được sử dụng!");
				isError = true;
				canUpdate = false;
			}
		}
		
		// Kiểm tra sdt
		if (!currentKhachHang.getSdt().equals(sdt)) {
			if (infoDAO.checkSoDienThoaiTrung(sdt) == true) {
				redirectAttributes.addFlashAttribute("err_mess_editPhone", "Số điện thoại đã được sử dụng!");
				isError = true;
				canUpdate = false;
			}
		}
		//Kiểm tra CCCD
		if (!currentKhachHang.getCccd().equals(cccd)) {
			if (infoDAO.checkCanCuocCongDanTrung(cccd) == true) {
				redirectAttributes.addFlashAttribute("err_mess_editCCCD", "Căn cước đã được sử dụng!");
				isError = true;
				canUpdate = false;
			}	
		}
		
		if (canUpdate == true) {
			if (infoDAO.updateKhachHang(hoten, gioitinh, ngaysinh, email, sdt, cccd, diachi, khachhang_id)) {
				message  = "Bạn đã cập nhật khách hàng " + khachhang_id + " thành công";
				session.setAttribute("message", message);
				session.setAttribute("isError", isError);
				return "redirect:" + url;
			} else {
				message  = "Cập nhật khách hàng " + khachhang_id + " không thành công";
				isError = true;
				session.setAttribute("message", message);
				session.setAttribute("isError", isError);
				return "redirect:" + url;
			}
		}
		
		return "redirect:" + url;
	}
	
	@RequestMapping(value = "/nhan_vien/quan_ly_thong_tin_khach_hang/xoa",method = RequestMethod.POST, params = { "kh_id"})
	public String xoa_thongtinnguoidung(Model model, HttpSession session,
											@RequestParam("kh_id") String khachhang_id) {
		boolean isError = false;
		String message = "";	
		if (mdDAO.checkKhachHangInLSD(khachhang_id) == true || lgcsDAO.checkKhachHangInLGCS(khachhang_id) == true || donghodienDAO.checkKhachHangInDongHoDien(khachhang_id) == true) {
			message  = "Không thể xóa vì xung đột dữ liệu";
			isError = true;
		} else {
			if (infoDAO.deleteKhachHang(khachhang_id) == true) {
				message = "Bạn đã xóa thành công khách hàng có mã: " + khachhang_id;
			} else {
				message = "Xóa khách hàng có mã: " + khachhang_id + " không thành công";
				isError = true;
			}
		}
		
		session.setAttribute("message", message);
		session.setAttribute("isError", isError);
		
		return "redirect:/nhan_vien/quan_ly_thong_tin_khach_hang";
	}
	
	@RequestMapping(value = "/nhan_vien/quan_ly_thong_tin_khach_hang/tim_kiem", method = RequestMethod.GET)
	public String tim_kiem_khach_hang(Model model,HttpSession session, @RequestParam("search_khachhang_id") String khachhang_id, @RequestParam("search_hoten") String hoten,
			@RequestParam("search_gioitinh") String gioitinh, @RequestParam("search_ngaysinh") String ngaysinh, @RequestParam("search_email") String email,
			@RequestParam("search_sdt") String sdt, @RequestParam("search_cccd") String cccd, @RequestParam("search_diachi") String diachi
			) {
		
		if (khachhang_id.isEmpty() && hoten.isEmpty() && gioitinh.isEmpty() && ngaysinh.isEmpty() && email.isEmpty() && sdt.isEmpty() && cccd.isEmpty() && diachi.isEmpty()) {
			List<Info> listKH = null;
			model.addAttribute("listKH", listKH);
			return "employee/quan_ly_thong_tin_khach_hang";
		}
		
		if (gioitinh.equals("nam")) {
			gioitinh = "Nam";
		} else if (gioitinh.equals("nu")) {
			gioitinh = "Nữ";
		}
		List<Info> listKH = infoDAO.searchKhachHang(khachhang_id, hoten, gioitinh, ngaysinh, email, sdt, cccd, diachi);
		model.addAttribute("listKH", listKH);
		return "employee/quan_ly_thong_tin_khach_hang";
	}
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////// QUAN LY TAI KHOAN KHACH HANG ///////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



	@RequestMapping("/nhan_vien/quan_ly_tai_khoan_khach_hang")
	public String layChuKiDO(Model model, 
			@RequestParam(value ="cur_page",defaultValue = "1") int cur_page,
			@RequestParam(value = "limit",defaultValue = "10") int limit) {
//		List<QuanLyAccount> listAcc = qlaccountDAO.getAllAccountsUser();
		List<QuanLyAccount> listAcc = qlaccountDAO.getAllAccountsUserInPage(cur_page);
		model.addAttribute("list_acc", listAcc);
		int ttp = qlaccountDAO.tong_trang();
		model.addAttribute("curr_page", cur_page);
		model.addAttribute("total_page", ttp);
		return "employee/quan_ly_tai_khoan_khach_hang";
	}
	
	@RequestMapping(value = "/nhan_vien/quan_ly_tai_khoan_khach_hang/tim_kiem", method= RequestMethod.GET)
	public String tim_kiem(HttpSession session, Model model,RedirectAttributes redirectAttributes,@RequestParam("kh_id") String kh_id,
			@RequestParam(value = "all", required = false) String all) 
	{
		if ("search_all".equals(all))
		{
			int ttp = qlaccountDAO.tong_trang();
			redirectAttributes.addAttribute("curr_page", 1);
			redirectAttributes.addAttribute("limit", ttp);
			return "redirect:/nhan_vien/quan_ly_tai_khoan_khach_hang";
		}
		 
		List<QuanLyAccount> account = qlaccountDAO.getAccByKHID(kh_id);
		model.addAttribute("list_acc", account);
		model.addAttribute("search_id",kh_id);
		return "employee/quan_ly_tai_khoan_khach_hang";
	}

	@RequestMapping(value = "/nhan_vien/quan_ly_tai_khoan_khach_hang", method = RequestMethod.POST)
	public String xu_li_hanh_dong(HttpSession session, RedirectAttributes redirectAttributes,Model model,HttpServletRequest request,
			@RequestParam("selectedUsername") String username, @RequestParam("action") String action,
			@RequestParam("addUsernameId") String addUsernameId, @RequestParam("addUsername") String addUsername,
			@RequestParam("addPassWord") String addPassWord, @RequestParam("newPass") String newPass) {
		String thong_bao = null;
		addUsernameId = addUsernameId.trim();
		addUsername = addUsername.trim();
		addPassWord = addPassWord.trim();
		newPass = newPass.trim();
		String url = request.getHeader("Referer");
		action = action.replace(",", "");
		if (action.equals("edit")) {
			if (username == "")
			{
//				redirectAttributes.addFlashAttribute("tb", "Khách hàng chưa có tài khoản để chỉnh sửa!");
				redirectAttributes.addFlashAttribute("tb_err", "Khách hàng chưa có tài khoản để chỉnh sửa!");
				return "redirect:" + url;
			}
			if (newPass != "") {
				if (!KtraDuLieu.ktraMatKhau(newPass)) {
					redirectAttributes.addFlashAttribute("tbDoiMK", "Mật khẩu không đúng yêu cầu!");
				} else {

					thong_bao = qlaccountDAO.changePassword(username, newPass, thong_bao);
					redirectAttributes.addFlashAttribute("tb", thong_bao);
				}
			} else {
				redirectAttributes.addFlashAttribute("tbDoiMK", "Mật khẩu không được để trống!");
			}
		} else if (action.equals("add")) {
			if (addUsernameId == "") {
				redirectAttributes.addFlashAttribute("tbThemMKH", "Mã khách hàng không được để trống!");
				if (addUsername == "") {
					redirectAttributes.addFlashAttribute("tbThemTK", "Tên đăng nhập không được để trống!");
				}
				if (addPassWord == "") {
					redirectAttributes.addFlashAttribute("tbThemMK", "Mật khẩu không được để trống!");
				}
				return "redirect:" + url;
			}
			if (addUsername == "") {
				redirectAttributes.addFlashAttribute("tbThemTK", "Tên đăng nhập không được để trống!");
				if (addPassWord == "") {
					redirectAttributes.addFlashAttribute("tbThemMK", "Mật khẩu không được để trống!");
				}
				return "redirect:" + url;
			}
			if (addPassWord == "") {
				redirectAttributes.addFlashAttribute("tbThemMK", "Mật khẩu không được để trống!");
				return "redirect:" + url;
			}
			
			if (infoDAO.checkKhachHangIDandUsername(addUsernameId) == false) {
				redirectAttributes.addFlashAttribute("tbThemMKH", "Mã khách hàng đã có tài khoản hoặc không tồn tại!");
				if (!KtraDuLieu.ktraTenDN(addUsername)) {
					redirectAttributes.addFlashAttribute("tbThemTK", "Tên đăng nhập không hợp lệ!");
					if (!KtraDuLieu.ktraMatKhau(addPassWord)) {
						redirectAttributes.addFlashAttribute("tbThemMK", "Mật khẩu không đúng yêu cầu!");
					}
					return "redirect:" + url;
				}
				if (account.checkUsernameAccount(addUsername) == true) {
					redirectAttributes.addFlashAttribute("tbThemTK", "Tên đăng nhập đã tồn tại!");
				}
				if (!KtraDuLieu.ktraMatKhau(addPassWord)) {
					redirectAttributes.addFlashAttribute("tbThemMK", "Mật khẩu không đúng yêu cầu!");
				}
				return "redirect:" + url;
			}

			if (!KtraDuLieu.ktraTenDN(addUsername)) {
				redirectAttributes.addFlashAttribute("tbThemTK", "Tên đăng nhập không hợp lệ!");
				if (!KtraDuLieu.ktraMatKhau(addPassWord)) {
					redirectAttributes.addFlashAttribute("tbThemMK", "Mật khẩu không đúng yêu cầu!");
				}
				return "redirect:" + url;
			}
			if (account.checkUsernameAccount(addUsername) == true) {
				redirectAttributes.addFlashAttribute("tbThemTK", "Tên đăng nhập đã tồn tại!");
				if (!KtraDuLieu.ktraMatKhau(addPassWord)) {
					redirectAttributes.addFlashAttribute("tbThemMK", "Mật khẩu không đúng yêu cầu!");
				}
				return "redirect:" + url;
			}
			if (!KtraDuLieu.ktraMatKhau(addPassWord)) {
				redirectAttributes.addFlashAttribute("tbThemMK", "Mật khẩu không đúng yêu cầu!");
				return "redirect:" + url;
			}

			thong_bao = qlaccountDAO.addAcc(addUsernameId, addUsername, addPassWord, thong_bao);
			redirectAttributes.addFlashAttribute("tb", thong_bao);

		} else if (action.equals("delete")) {
			if (username == "")
			{
				redirectAttributes.addFlashAttribute("tb_err", "Khách hàng chưa có tài khoản để xoá!");
				return "redirect:" + url;
			}
			thong_bao = qlaccountDAO.deleteAcc(username, thong_bao);
			redirectAttributes.addFlashAttribute("tb", thong_bao);
		}
		System.out.println(username);
		System.out.println(action);
//		return "redirect:" + url;
		return "redirect:" + url;
	}
	
	
	@RequestMapping(value = "/nhan_vien/quan_ly_lich_ghi_chi_so_khach_hang", method = RequestMethod.GET)
	public String get_lgcs_khachhang(@RequestParam(value = "selected_makh", required = false) String selected_makh, Model model) {
		
		if (selected_makh == null) {
			selected_makh = "KH001";
		}
		
		List<Info> list_info = infoDAO.getAllKhachHang();
		List<LichGhiChiSo> list_lgcs = lgcsDAO.getTT(selected_makh);
		
		model.addAttribute("list_info", list_info);
		model.addAttribute("list_lgcs", list_lgcs);
		
		return "employee/quan_ly_lich_ghi_chi_so_khach_hang";
	}

	@RequestMapping(value = "/nhan_vien/quan_ly_lich_ghi_chi_so_khach_hang/sua", method = RequestMethod.POST)
	public String sua_lgcs_khachhang(@RequestParam("lgcs_id") int lgcs_id, @RequestParam("ngay") String ngay, @RequestParam("thang") String thang, @RequestParam("nam") int nam) {
		
		String ngay_batdau = nam + "-" + thang + "-" + ngay;
		
		if (lgcsDAO.update_lgcs_khachhang(lgcs_id, ngay_batdau)) {
			return "redirect:/nhan_vien/quan_ly_lich_ghi_chi_so_khach_hang";
		}
		
		return "redirect:/nhan_vien/quan_ly_lich_ghi_chi_so_khach_hang";
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////// QUAN LY LICH SU DO ///////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value = "/nhan_vien/quan_ly_lich_su_do_khach_hang", method = RequestMethod.GET)
	public String get_lsd_khachhang(Model model, @RequestParam(value ="cur_page",defaultValue = "1") int cur_page, 
			@RequestParam(value = "limit",defaultValue = "10") int limit) {
		
//		List<MeasurementHistory> list_lsd = mdDAO.getLSDoTheoFirstChuhoID();
		List<MeasurementHistory> list_lsd = mdDAO.getAllLSDInPage(cur_page);
		model.addAttribute("list_lsd", list_lsd);
		
		int total_page = mdDAO.tong_trang();
		
		model.addAttribute("curr_page", cur_page);
		model.addAttribute("total_page", total_page);

		return "employee/quan_ly_lich_su_do_khach_hang";
	}
	
	@RequestMapping(value = "/nhan_vien/quan_ly_lich_su_do_khach_hang/them", method = RequestMethod.POST)
	public String them_lsd_khachhang(@RequestParam("add_dongho_id") String dongho_id, @RequestParam("ngay") String ngay, @RequestParam("thang") String thang, 
			@RequestParam("nam") String nam, @RequestParam("add_chiso") String chiso, RedirectAttributes redirectAttributes,
			HttpSession session, HttpServletRequest request) {
		
		boolean isError = false;
		boolean canAdd = true;
		String message = "";
		
		if (ngay == "0" || thang == "0" || nam == "0") {
			redirectAttributes.addFlashAttribute("err_mess_addNgaydo", "Ngày đo không được để trống!");
			canAdd = false;
		}
		
		String ngaydo = "";

		if (dongho_id == "") {
			redirectAttributes.addFlashAttribute("err_mess_addKhachhangid", "Mã khách hàng không được để trống!");
			canAdd = false;
		} else {
			if (infoDAO.checkExistKhachHangByDongHoId(dongho_id) == false) {
				redirectAttributes.addFlashAttribute("err_mess_addKhachhangid", "Đồng hồ điện không tồn tại!");
				canAdd = false;
			} else {
				
				System.out.println("Ma dong ho: " + dongho_id);
				
				System.out.println("Chi so: " + chiso);
				
				
				
				MeasurementHistory latest_lsd = mdDAO.getLatestLsdByDongHoId(dongho_id);
				
				if (latest_lsd != null) {
					if (chiso != null && Integer.parseInt(chiso) < latest_lsd.getChiso()) {
						redirectAttributes.addFlashAttribute("err_mess_addChiso", "Chỉ số không được bé hơn chỉ số của tháng gần nhất!");
						canAdd = false;
					}
					
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					String date = formatter.format(latest_lsd.getNgay_do());
					if (ngay.equals("0") || thang.equals("0") || nam.equals("0")) {
						redirectAttributes.addFlashAttribute("err_mess_addNgaydo", "Ngày đo không được để trống!");
						canAdd = false;
					} else {
						LocalDate localDate = LocalDate.of(Integer.parseInt(nam), Integer.parseInt(thang), Integer.parseInt(ngay));
						
						ngaydo = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
						System.out.println("Ngay do: " + ngaydo);
						System.out.println("date: " + date);
						
						if (ngaydo.compareTo(date) <= 0) {
							redirectAttributes.addFlashAttribute("err_mess_addNgaydo", "Ngày đo không được bé hơn hoặc bằng ngày đo của tháng gần nhất!");
							canAdd = false;
						}					
						
					}
					
				}
			}
		}
		
		if (chiso == null) {
			redirectAttributes.addFlashAttribute("err_mess_addChiso", "Chỉ số không được để trống!");
			canAdd = false;
		}
		
		// http://localhost:8080/Tinhtiendien/nhan_vien/quan_ly_lich_su_do_khach_hang/tim_kiem?search_lsdid=&search_khachhangid=kh002&search_dhdid=&search_ngaydo=
		String url = request.getHeader("Referer");
		int index = url.indexOf("/nhan_vien");
		// url = /nhan_vien/quan_ly_lich_su_do_khach_hang/tim_kiem?search_lsdid=&search_khachhangid=kh002&search_dhdid=&search_ngaydo=
		url = url.substring(index);
		
		if (canAdd) {
			if (mdDAO.addNewLsd(dongho_id, ngaydo, chiso)) {
				message = "Bạn đã cập nhật chỉ số khách hàng thành công";
				session.setAttribute("message", message);
				session.setAttribute("isError", isError);
				
				return "redirect:" + url;
			}
		}
		
		
		return "redirect:" + url;
	}
	
	@RequestMapping(value = "/nhan_vien/quan_ly_lich_su_do_khach_hang/sua", method = RequestMethod.POST)
	public String sua_lsd_khachhang(@RequestParam("dongho_id") String dongho_id, @RequestParam("chiso") String chiso_jsp, 
			@RequestParam("lsd_id") int lsd_id, @RequestParam("ngay") int ngay, @RequestParam("thang") int thang, 
			@RequestParam("nam") int nam, RedirectAttributes redirectAttributes, HttpSession session, HttpServletRequest request) {
		
		int chiso = Integer.parseInt(chiso_jsp);
		
		System.out.println("Chi so:  " + chiso);
		System.out.println("lsd id: " + lsd_id);
		System.out.println("dong ho id: " + dongho_id);
		
		String ngay_do = String.format("%04d-%02d-%02d", nam, thang, ngay);
		
		System.out.println("ngay do: " + ngay_do);
		
		String message = "";
		boolean isError = false;
		
		MeasurementHistory prelsd = mdDAO.getPreviousLDSByLsdIdAndKhId(lsd_id, dongho_id);
		MeasurementHistory nextlsd = mdDAO.getNextLDSByLsdIdAndKhId(lsd_id, dongho_id);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		formatter.setLenient(false);
		
		boolean canUpdate = true;
		
		try {
			Date date = formatter.parse(ngay_do);
		} catch (ParseException e) {
			redirectAttributes.addFlashAttribute("err_mess_editNgaydo", "Ngày đo không hợp lệ!!");
			canUpdate = false;
		}
		
		
		if (prelsd != null) {
			if (chiso < prelsd.getChiso()) {
				redirectAttributes.addFlashAttribute("err_mess_editChiso", "Chỉ số phải lớn hơn hoặc bằng chỉ số tháng trước!!");
				canUpdate = false;
			}
			
			String date = formatter.format(prelsd.getNgay_do());
			
			System.out.println("ngay do: " + ngay_do);
			System.out.println("ngay do thang truoc: " + date);
			
			if (ngay_do.compareTo(date) <= 0) {
				redirectAttributes.addFlashAttribute("err_mess_editNgaydo", "Ngày đo không được nhỏ hơn hoặc bằng ngày đo của tháng trước!!");
				canUpdate = false;
			}
		}
		
		if (nextlsd != null) {
			if (chiso > nextlsd.getChiso()) {
				redirectAttributes.addFlashAttribute("err_mess_editChiso", "Chỉ số phải bé hơn hoặc bằng chỉ số tháng sau!!");
				canUpdate = false;
			}
			
			String date = formatter.format(nextlsd.getNgay_do());
			
			System.out.println("ngay do: " + ngay_do);
			System.out.println("ngay do thang sau: " + date);
			
			if (ngay_do.compareTo(date) >= 0) {
				redirectAttributes.addFlashAttribute("err_mess_editNgaydo", "Ngày đo không được lớn hơn hoặc bằng ngày đo của tháng sau!!");
				canUpdate = false;
			}
		}
		
		// http://localhost:8080/Tinhtiendien/nhan_vien/quan_ly_lich_su_do_khach_hang/tim_kiem?search_lsdid=&search_khachhangid=kh002&search_dhdid=&search_ngaydo=
		String url = request.getHeader("Referer");
		int index = url.indexOf("/nhan_vien");
		// url = /nhan_vien/quan_ly_lich_su_do_khach_hang/tim_kiem?search_lsdid=&search_khachhangid=kh002&search_dhdid=&search_ngaydo=
		url = url.substring(index);
		
		
		
		if (canUpdate) {
			if(mdDAO.updateLsdFromLSDId(chiso, ngay_do, lsd_id)) {
				message = "Bạn đã cập nhật lịch sử đo của khách hàng thành công";
				redirectAttributes.addFlashAttribute("testahihi", "just test value");
				session.setAttribute("message", message);
				session.setAttribute("isError", isError);
				
				return "redirect:" + url;
			};			
		}
		
		
		return "redirect:" + url;
	}
	
	@RequestMapping(value = "/nhan_vien/quan_ly_lich_su_do_khach_hang/xoa", method = RequestMethod.POST)
	public String xoa_lsd_khachhang(@RequestParam("lsd_id") String lsd_id, HttpServletRequest request,
			RedirectAttributes redirectAttributes, HttpSession session) {
		
		String url = request.getHeader("Referer");
		int index = url.indexOf("/nhan_vien");
		url = url.substring(index); 
		
		String message = "";
		boolean isError = false;
		
		if (mdDAO.deleteLsd(lsd_id)) {
			message = "Bạn đã xóa lịch sử đo của khách hàng thành công";
			session.setAttribute("message", message);
			session.setAttribute("isError", isError);
			
			return "redirect:" + url;
		}
		
		
		return "redirect:" + url;
	}
	
	@RequestMapping(value = "/nhan_vien/quan_ly_lich_su_do_khach_hang/tim_kiem", method = RequestMethod.GET)
	public String search_lsd_khachhang(Model model, @RequestParam(value ="cur_page",defaultValue = "1") int cur_page, 
			@RequestParam(value = "limit",defaultValue = "10") int limit, @RequestParam("search_lsdid") String lsd_id,
			@RequestParam("search_khachhangid") String khachhang_id, @RequestParam("search_dhdid") String dhd_id, @RequestParam("search_ngaydo") String ngaydo,
			@RequestParam("action") String action, HttpServletRequest request) {
		
		String url = request.getHeader("Referer");
		
		if (action.equals("search")) {
			List<MeasurementHistory> list_lsd = mdDAO.searchLsdKhachhang(cur_page, lsd_id, khachhang_id, dhd_id, ngaydo);
			
			int total_page = mdDAO.tong_trang_search(lsd_id, khachhang_id, dhd_id, ngaydo);
			
			model.addAttribute("curr_page", cur_page);
			model.addAttribute("total_page", total_page);
			model.addAttribute("list_lsd", list_lsd);			
		} else {
			return "redirect:/nhan_vien/quan_ly_lich_su_do_khach_hang";
		}
		
		return "employee/quan_ly_lich_su_do_khach_hang";
	}

	
	@RequestMapping(value = "/nhan_vien/quan_ly_lich_su_thanh_toan_khach_hang", method = RequestMethod.GET)
	public String test(Model model) {
		
		model.addAttribute("test_value", "123");
		System.out.println("test_value");
		
		return "employee/quan_ly_lich_su_thanh_toan_khach_hang";
	}
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////// QUAN LY HOA DON ///////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	@RequestMapping(value = "/nhan_vien/quan_ly_hoa_don_khach_hang", method = RequestMethod.GET)
	public String get_hoadonkhachhang(Model model) {
		
		List<HoaDon> list_hoadon = hoadonDAO.getFirstAllInfoHoaDon();
		
		model.addAttribute("list_hoadon", list_hoadon);
		
		
		return "employee/quan_ly_hoa_don_khach_hang";
	}
	
	@RequestMapping(value = "/nhan_vien/quan_ly_hoa_don_khach_hang/tim_kiem", method = RequestMethod.GET)
	public String search_hoadon_khachhang(Model model, @RequestParam("search_hoadonid") String search_hoadonid, @RequestParam("search_khachhangid") String search_khachhangid,
			 @RequestParam("search_ngaytao") String search_ngaytao,  @RequestParam("search_month") String search_month,
			 @RequestParam("search_year") String search_year,  @RequestParam("search_status") String search_status,
			 @RequestParam("action") String action) {
		
		List<HoaDon> list_hoadon = new ArrayList<>();
		
		if (search_status.equals("0")) {
			search_status = "Chưa thanh toán";
			System.out.println("Chưa thanh toán");
		} else if (search_status.equals("1")) {
			search_status = "Đã thanh toán";
			System.out.println("Đã thanh toán");
		} else {
			search_status = "";
		}
		
		
		if (action.equals("search")) {
			list_hoadon = hoadonDAO.searchHoaDonKhachHang(search_hoadonid, search_khachhangid, search_ngaytao, search_month, search_year, search_status);			
		} else {
			return "redirect:/nhan_vien/quan_ly_hoa_don_khach_hang";
		}
		
		model.addAttribute("list_hoadon", list_hoadon);
		
		return "employee/quan_ly_hoa_don_khach_hang";
	}
	
	@RequestMapping(value = "/nhan_vien/quan_ly_hoa_don_khach_hang/them", method = RequestMethod.POST)
	public String them_hoadon_khachhang(@RequestParam("add_khachhangid") String khachhang_id, @RequestParam("thang") String thang, 
			@RequestParam("nam") String nam, RedirectAttributes redirectAttributes,
			@RequestParam("add_thue") String thue, HttpSession session, HttpServletRequest request) {
		
		boolean canAdd = true;
		boolean isError = false;
		String message = "";
		
		if (thang == "0" || nam == "0") {
			redirectAttributes.addFlashAttribute("err_mess_addThoigianhoadon", "Thời gian hóa đơn không được để trống");
			canAdd = false;
		}
		
		if (khachhang_id.isEmpty()) {
			redirectAttributes.addFlashAttribute("err_mess_addKhachhangid", "Mã khách hàng không được để trống!");
			canAdd = false;
		} else {
			if (infoDAO.checkExistKhachHangById(khachhang_id) == false) {
				redirectAttributes.addFlashAttribute("err_mess_addKhachhangid", "Mã khách hàng không tồn tại!");
				canAdd = false;
			}
		}
		
		String url = request.getHeader("Referer");
		
		if (canAdd) {
			if (mdDAO.checkExistLsdBefore(khachhang_id, thang, nam)) {
				if (hoadonDAO.addNewHoaDon(khachhang_id, thang, nam, thue) ) {
					message = "Thêm hóa đơn khách hàng thành công";
					session.setAttribute("message", message);
					session.setAttribute("isError", isError);
					
					return "redirect:" + url;
				} else {
					redirectAttributes.addFlashAttribute("err_mess_addThoigianhoadon", "Thời gian này đã có hóa đơn");
				}
				
			} else {
				message = "Thêm hóa đơn khách hàng thất bại! Chưa có kết quả chỉ số đồng hồ điện cho tháng sau!";
				session.setAttribute("message", message);
				isError = true;
				session.setAttribute("isError", isError);
			}
		}
		
		
	
		return "redirect:" + url;
	}
	
	
	@RequestMapping(value = "/nhan_vien/quan_ly_hoa_don_khach_hang/sua", method = RequestMethod.POST)
	public String sua_hoadon_khachhang(@RequestParam("khachhang_id") String khachhang_id, @RequestParam("hoadon_id") String hoadon_id,
			@RequestParam("thang") String thang, @RequestParam("nam") String nam, @RequestParam("thue") String thue, 
			@RequestParam("edit_status") String edit_status, RedirectAttributes redirectAttributes, HttpSession session,
			HttpServletRequest request) {
		
		
		
		String url = request.getHeader("Referer");
		String message = "";
		boolean isError = false;
		
		System.out.println("hoadon_id: " + hoadon_id);
		System.out.println("khachang_id: " + khachhang_id);
		System.out.println("thang: " + thang);
		System.out.println("nam: " + nam);
		System.out.println("thue: " + thue);
		System.out.println("edit_status: " + edit_status);
		
		if (edit_status.equals("0")) {
			edit_status = "Chưa thanh toán";
		} else {
			edit_status = "Đã thanh toán";
		}
		
		if (hoadonDAO.editHoaDon(hoadon_id, thang, nam, thue, edit_status)) {
			message = "Bạn đã cập nhật lịch sử đo của khách hàng thành công";
			session.setAttribute("message", message);
			session.setAttribute("isError", isError);
		} else {
			redirectAttributes.addFlashAttribute("err_mess_editThoigian", "Thời gian này đã có hóa đơn");
		}
		
		
		return "redirect:" + url;
		
	}
	
	
	
	@RequestMapping(value = "/nhan_vien/quan_ly_hoa_don_khach_hang/xoa", method = RequestMethod.POST)
	public String xoa_hoadon_khachhang(@RequestParam("hoadon_id") String hoadon_id, HttpServletRequest request, RedirectAttributes redirectAttributes, HttpSession session) {
		
		String url = request.getHeader("Referer");
		String message = "";
		boolean isError = false;
		
		if (hoadonDAO.deleteHoaDon(hoadon_id)) {
			message = "Bạn đã xóa lịch sử đo của khách hàng thành công";
			session.setAttribute("message", message);
			session.setAttribute("isError", isError);

		} else {
			message = "Bạn đã xóa lịch sử đo của khách hàng thất bại";
			isError = true;
			
			session.setAttribute("message", message);
			session.setAttribute("isError", isError);
		}
		
		return "redirect:" + url;
		
	}
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////// QUAN LY GIA DIEN ///////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@RequestMapping (value = "/nhan_vien/quan_ly_gia_dien", method = RequestMethod.GET)
	public String quan_ly_gia_dien(Model model) {
		List <GiaDien> giaDien = giaDien_DAO.HienThiDanhSach();
		model.addAttribute("list_giaDien", giaDien);
		return "employee/quan_ly_gia_dien";
	}
	
	@RequestMapping(value = "/nhan_vien/quan_ly_gia_dien", method = RequestMethod.POST)
	public String quan_ly_gia_dien_xuLyHanhDong(RedirectAttributes redirectAttributes,
	        @RequestParam("action") String action,
	        @RequestParam(value = "add_bacDien", required = false) String add_bacDienstr,
	        @RequestParam(value = "add_giaDien", required = false) String add_giaDienstr,
	        @RequestParam(value = "edit_giaDien", required = false) String edit_giaDienstr,
	        @RequestParam(value = "bacDien_key", required = false) String bacDienstr) {

	    System.out.println("action: " + action);
	    System.out.println("add_bacDien: " + add_bacDienstr);
	    System.out.println("add_giaDien: " + add_giaDienstr);
	    System.out.println("edit_giaDien: " + edit_giaDienstr);
	    System.out.println("bacDien_key: " + bacDienstr);
	    boolean isAction = true;

	    try {
	        if (action.equals("add")) {
	        	// Kiểm tra null và định dạng cho bậc điện
	            if (add_bacDienstr == null || add_bacDienstr.isEmpty()) {
	                redirectAttributes.addFlashAttribute("add_bacDien_id", isError.isNull("Bậc Điện"));
	                isAction = false;
	            
	            } else if (!KtraDuLieu.ktraDuLieu_Dien(add_bacDienstr)) {
	                redirectAttributes.addFlashAttribute("add_bacDien_id", isError.isType("Bậc Điện"));
	                isAction = false;
	            }
	            // kiểm tra null và định dạng cho giá điện 
	            if (add_giaDienstr == null || add_giaDienstr.isEmpty()) {
	                redirectAttributes.addFlashAttribute("add_giaDien_id", isError.isNull("Giá Điện"));
	                isAction = false;
	            } else if (!KtraDuLieu.ktraDuLieu_Dien(add_giaDienstr)) {
	                redirectAttributes.addFlashAttribute("add_giaDien_id", isError.isType("Giá Điện"));
	                isAction = false;
	            }
	            if (isAction) {
	                try {
	                    int add_bacDien = Integer.parseInt(add_bacDienstr);
	                    int add_giaDien = Integer.parseInt(add_giaDienstr);
	                    // Kiểm tra trùng lặp bậc điện
	                    if (giaDien_DAO.checkExist(add_bacDien)) {
	                        redirectAttributes.addFlashAttribute("add_bacDien_id", isError.is_Exist("Bậc Điện"));
	                        return "redirect:/nhan_vien/quan_ly_gia_dien";
	                    }
	                    giaDien_DAO.Them(add_bacDien, add_giaDien);
	                    redirectAttributes.addFlashAttribute("message", isSuccess.isComplete("Thêm"));
	                } catch (NumberFormatException e) {
	                    redirectAttributes.addFlashAttribute("message", isError.isFormat(""));
	                    return "redirect:/nhan_vien/quan_ly_gia_dien";
	                }
	            }

	        } else if (action.equals("edit")) {
	        	// kiểm tra null
	            if (edit_giaDienstr == null || edit_giaDienstr.isEmpty()) {
	                redirectAttributes.addFlashAttribute("edit_giaDien_id", isError.isNull("Giá Điện"));
	                isAction = false;
	            // kiểm tra định dạng 
	            } else if (!KtraDuLieu.ktraDuLieu_Dien(edit_giaDienstr)) {
	                redirectAttributes.addFlashAttribute("edit_giaDien_id", isError.isType("Giá Điện"));
	                isAction = false;
	            }

	            if (isAction) {
	                try {
	                    int bacDien = Integer.parseInt(bacDienstr);
	                    int edit_giaDien = Integer.parseInt(edit_giaDienstr);
	                    giaDien_DAO.CapNhat(bacDien, edit_giaDien);
	                    redirectAttributes.addFlashAttribute("message", isSuccess.isComplete("Cập Nhật"));
	                } catch (NumberFormatException e) {
	                    redirectAttributes.addFlashAttribute("message", isError.isFormat("Giá Điện"));
	                    return "redirect:/nhan_vien/quan_ly_gia_dien";
	                }
	            }
	        } else if (action.equals("delete")) {
	            try {
	                int bacDien = Integer.parseInt(bacDienstr);
	                giaDien_DAO.Xoa(bacDien);
	                redirectAttributes.addFlashAttribute("message", isSuccess.isComplete("Xóa"));
	            } catch (NumberFormatException e) {
	                redirectAttributes.addFlashAttribute("message", isError.isFormat("Bậc Điện"));
	                return "redirect:/nhan_vien/quan_ly_gia_dien";
	            }
	        }
	    } catch (Exception e) {
	        redirectAttributes.addFlashAttribute("message", isError.isNone(""));
	    }

	    return "redirect:/nhan_vien/quan_ly_gia_dien";
	}
}
