package com.Tinhtiendien.controller;

import java.io.UnsupportedEncodingException;
import java.net.http.HttpRequest;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	public String them_moi_thongtinnguoidung(Model model,HttpSession session, @RequestParam("hoten") String hoten,
			@RequestParam("gioitinh") String gioitinh, @RequestParam("ngay") String ngay,
			@RequestParam("thang") String thang, @RequestParam("nam") String nam, @RequestParam("email") String email,
			@RequestParam("sdt") String sdt, @RequestParam("cccd") String cccd, @RequestParam("diachi") String diachi) {
		boolean isError = false;
		String message = "";	
		
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
		
		if (infoDAO.checkSoDienThoaiTrung(sdt) == true) {
			message = "Số điện thoại đã được sử dụng";
			isError = true;
		} else {
			if (infoDAO.checkCanCuocCongDanTrung(cccd) == true) {
				message = "Căn cước công dân đã được sử dụng";
				isError = true;
			} else {
				if (infoDAO.addNewKhachHang(hoten, gioitinh, ngaysinh, email, sdt, cccd, diachi)) {
					message  = "Bạn đã thêm khách hàng thành công";
				} else {
					message  = "Bạn đã thêm khách hàng thất bại";
					isError = true;
				}
			}
		}
			
		session.setAttribute("message", message);
		session.setAttribute("isError", isError);
		
		return "redirect:/nhan_vien/quan_ly_thong_tin_khach_hang";
	}

	@RequestMapping(value = "/nhan_vien/quan_ly_thong_tin_khach_hang/sua", method = RequestMethod.POST, params = {
			"hoten", "gioitinh", "ngay", "thang", "nam", "email", "sdt", "cccd", "diachi", "khachhang_id" })
	public String chinh_sua_thongtinnguoidung(Model model, HttpSession session, @RequestParam("hoten") String hoten,
			@RequestParam("gioitinh") String gioitinh, @RequestParam("ngay") String ngay,
			@RequestParam("thang") String thang, @RequestParam("nam") String nam, @RequestParam("email") String email,
			@RequestParam("sdt") String sdt, @RequestParam("cccd") String cccd, @RequestParam("diachi") String diachi,
			@RequestParam("khachhang_id") String khachhang_id) {

		boolean isError = false;
		String message = "";	
		
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
		
		// Nếu sdt mới và cccd mới trùng với cái cũ
		if (currentKhachHang.getSdt().equals(sdt) && currentKhachHang.getCccd().equals(cccd)) {
			System.out.print("Lỗi lỗi lỗi");
			if (infoDAO.updateKhachHang(hoten, gioitinh, ngaysinh, email, sdt, cccd, diachi, khachhang_id)) {
				message  = "Bạn đã cập nhật thông tin khách hàng thành công";
			} else {
				message  = "Bạn đã cập nhật thông tin khách hàng thất bại";
				isError = true;
			}
		} else {
			// Nếu sdt mới trùng với cái cũ và cccd ko trùng
			if (currentKhachHang.getSdt().equals(sdt) && !currentKhachHang.getCccd().equals(cccd)) {
				if (infoDAO.checkCanCuocCongDanTrung(cccd) == true) {
					message = "Căn cước công dân đã được sử dụng";
					isError = true;
				} else {
					if (infoDAO.updateKhachHang(hoten, gioitinh, ngaysinh, email, sdt, cccd, diachi, khachhang_id)) {
						message  = "Bạn đã cập nhật thông tin khách hàng thành công";
					} else {
						message  = "Bạn đã cập nhật thông tin khách hàng thất bại";
						isError = true;
					}
				}
				// Nếu sdt mới không trùng với cái cũ và cccd trùng
			} else if (!currentKhachHang.getSdt().equals(sdt) && currentKhachHang.getCccd().equals(cccd)) {
				if (infoDAO.checkSoDienThoaiTrung(sdt) == true) {
					message = "Số điện thoại đã được sử dụng";
					isError = true;
				} else {
					if (infoDAO.updateKhachHang(hoten, gioitinh, ngaysinh, email, sdt, cccd, diachi, khachhang_id)) {
						message  = "Bạn đã cập nhật thông tin khách hàng thành công";
					} else {
						message  = "Bạn đã cập nhật thông tin khách hàng thất bại";
						isError = true;
					}
				}
				// Nếu sdt mới và cccd mới đều không trùng với cái cũ
			} else {
				if (infoDAO.checkSoDienThoaiTrung(sdt) == true) {
					message = "Số điện thoại đã được sử dụng";
					isError = true;
					System.out.println("Lỗi sdt");
				} else {
					if (infoDAO.checkCanCuocCongDanTrung(cccd) == true) {
						message = "Căn cước công dân đã được sử dụng";
						isError = true;
						System.out.println("Lỗi cccd");
					} else {
						if (infoDAO.updateKhachHang(hoten, gioitinh, ngaysinh, email, sdt, cccd, diachi, khachhang_id)) {
							message  = "Bạn đã cập nhật thông tin khách hàng thành công";
						} else {
							message  = "Bạn đã cập nhật thông tin khách hàng thất bại";
							isError = true;
						}
					}
				}
			}
		}
		
		session.setAttribute("message", message);
		session.setAttribute("isError", isError);
		
		return "redirect:/nhan_vien/quan_ly_thong_tin_khach_hang";
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



	@RequestMapping("/nhan_vien/quan_ly_tai_khoan_khach_hang")
	public String layChuKiDO(HttpSession session, Model model) {
		List<QuanLyAccount> listAcc = qlaccountDAO.getAllAccountsUser();

		model.addAttribute("list_acc", listAcc);

		return "employee/quan_ly_tai_khoan_khach_hang";
	}

	@RequestMapping(value = "/nhan_vien/quan_ly_tai_khoan_khach_hang", method = RequestMethod.POST)
	public String xu_li_hanh_dong(HttpSession session, RedirectAttributes redirectAttributes,
			@RequestParam("selectedUsername") String username, @RequestParam("action") String action,
			@RequestParam("addUsernameId") String addUsernameId, @RequestParam("addUsername") String addUsername,
			@RequestParam("addPassWord") String addPassWord, @RequestParam("newPass") String newPass) {
		String thong_bao = null;
		if (action.equals("edit")) {
			if (username == "")
			{
				redirectAttributes.addFlashAttribute("tb", "Khách hàng chưa có tài khoản để chỉnh sửa!");
				return "redirect:/nhan_vien/quan_ly_tai_khoan_khach_hang";
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
				return "redirect:/nhan_vien/quan_ly_tai_khoan_khach_hang";
			}
			if (addUsername == "") {
				redirectAttributes.addFlashAttribute("tbThemTK", "Tên đăng nhập không được để trống!");
				if (addPassWord == "") {
					redirectAttributes.addFlashAttribute("tbThemMK", "Mật khẩu không được để trống!");
				}
				return "redirect:/nhan_vien/quan_ly_tai_khoan_khach_hang";
			}
			if (addPassWord == "") {
				redirectAttributes.addFlashAttribute("tbThemMK", "Mật khẩu không được để trống!");
				return "redirect:/nhan_vien/quan_ly_tai_khoan_khach_hang";
			}
			
			if (infoDAO.checkKhachHangIDandUsername(addUsernameId) == false) {
				redirectAttributes.addFlashAttribute("tbThemMKH", "Mã khách hàng đã có tài khoản hoặc không tồn tại!");
				if (!KtraDuLieu.ktraTenDN(addUsername)) {
					redirectAttributes.addFlashAttribute("tbThemTK", "Tên đăng nhập không hợp lệ!");
					if (!KtraDuLieu.ktraMatKhau(addPassWord)) {
						redirectAttributes.addFlashAttribute("tbThemMK", "Mật khẩu không đúng yêu cầu!");
					}
					return "redirect:/nhan_vien/quan_ly_tai_khoan_khach_hang";
				}
				if (account.checkUsernameAccount(addUsername) == true) {
					redirectAttributes.addFlashAttribute("tbThemTK", "Tên đăng nhập đã tồn tại!");
				}
				if (KtraDuLieu.ktraMatKhau(addPassWord)) {
					redirectAttributes.addFlashAttribute("tbThemMK", "Mật khẩu không đúng yêu cầu!");
				}
				return "redirect:/nhan_vien/quan_ly_tai_khoan_khach_hang";

			}

			if (!KtraDuLieu.ktraTenDN(addUsername)) {
				redirectAttributes.addFlashAttribute("tbThemTK", "Tên đăng nhập không hợp lệ!");
				if (!KtraDuLieu.ktraMatKhau(addPassWord)) {
					redirectAttributes.addFlashAttribute("tbThemMK", "Mật khẩu không đúng yêu cầu!");
				}
				return "redirect:/nhan_vien/quan_ly_tai_khoan_khach_hang";
			}
			if (account.checkUsernameAccount(addUsername) == true) {
				redirectAttributes.addFlashAttribute("tbThemTK", "Tên đăng nhập đã tồn tại!");
				if (!KtraDuLieu.ktraMatKhau(addPassWord)) {
					redirectAttributes.addFlashAttribute("tbThemMK", "Mật khẩu không đúng yêu cầu!");
				}
				return "redirect:/nhan_vien/quan_ly_tai_khoan_khach_hang";
			}
			if (!KtraDuLieu.ktraMatKhau(addPassWord)) {
				redirectAttributes.addFlashAttribute("tbThemMK", "Mật khẩu không đúng yêu cầu!");
				return "redirect:/nhan_vien/quan_ly_tai_khoan_khach_hang";
			}

			thong_bao = qlaccountDAO.addAcc(addUsernameId, addUsername, addPassWord, thong_bao);
			redirectAttributes.addFlashAttribute("tb", thong_bao);

		} else {
			thong_bao = qlaccountDAO.deleteAcc(username, thong_bao);
			redirectAttributes.addFlashAttribute("tb", thong_bao);
		}
		
		System.out.println(username);
		System.out.println(action);
		
		return "redirect:/nhan_vien/quan_ly_tai_khoan_khach_hang";
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
	
	
	///////////////////////// QUAN LY LICH SU DO ////////////////////////////////
	
	@RequestMapping(value = "/nhan_vien/quan_ly_lich_su_do_khach_hang", method = RequestMethod.GET)
	public String get_lsd_khachhang(@RequestParam(value = "selected_makh", required = false) String selected_makh, Model model) {

		if (selected_makh == null) {
			selected_makh = "KH001";
		}
		
		List<MeasurementHistory> list_lsd = mdDAO.getLSDoTheoChuhoID(selected_makh);
		List<Info> list_info = infoDAO.getAllKhachHang();
		
		model.addAttribute("list_lsd", list_lsd);
		model.addAttribute("list_info", list_info);
		
		
		
		return "employee/quan_ly_lich_su_do_khach_hang";
	}
	
	@RequestMapping(value = "/nhan_vien/quan_ly_lich_su_do_khach_hang/them", method = RequestMethod.POST)
	public String them_lsd_khachhang(@RequestParam("add_khachhangid") String khachhang_id, @RequestParam("ngay") String ngay, @RequestParam("thang") String thang, 
			@RequestParam("nam") String nam, @RequestParam("add_chiso") String chiso, RedirectAttributes redirectAttributes,
			HttpSession session, HttpServletRequest request) {
		
		boolean isError = false;
		boolean canAdd = true;
		String message = "";
		
		String ngaydo = nam + "-" + thang + "-" + ngay;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		if (khachhang_id == "") {
			redirectAttributes.addFlashAttribute("err_mess_addKhachhangid", "Mã khách hàng không được để trống!");
			canAdd = false;
		} else {
			if (infoDAO.checkExistKhachHangById(khachhang_id) == false) {
				redirectAttributes.addFlashAttribute("err_mess_addKhachhangid", "Mã khách hàng không tồn tại!");
				canAdd = false;
			} else {
				
				System.out.println("Ma khach hang: " + khachhang_id);
				System.out.println("Ngay do: " + ngaydo);
				System.out.println("Chi so: " + chiso);
				
				MeasurementHistory latest_lsd = mdDAO.getLatestLsdByKhachhangId(khachhang_id);
				
				if (latest_lsd != null) {
					if (chiso != null && Integer.parseInt(chiso) < latest_lsd.getChiso()) {
						redirectAttributes.addFlashAttribute("err_mess_addChiso", "Chỉ số không được bé hơn chỉ số của tháng gần nhất!");
						canAdd = false;
					}
					
					String date = formatter.format(latest_lsd.getNgay_do());
					if (ngaydo.compareTo(date) <= 0) {
						redirectAttributes.addFlashAttribute("err_mess_addNgaydo", "Ngày đo không được bé hơn hoặc bằng ngày đo của tháng gần nhất!");
						canAdd = false;
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
			if (mdDAO.addNewLsd(khachhang_id, ngaydo, chiso)) {
				message = "Bạn đã cập nhật chỉ số khách hàng thành công";
				session.setAttribute("message", message);
				session.setAttribute("isError", isError);
				
				return "redirect:" + url;
			}
		}
		
		
		return "redirect:" + url;
	}
	
	@RequestMapping(value = "/nhan_vien/quan_ly_lich_su_do_khach_hang/sua", method = RequestMethod.POST)
	public String sua_lsd_khachhang(@RequestParam("khachhang_id") String khachhang_id, @RequestParam("chiso") String chiso_jsp, 
			@RequestParam("lsd_id") int lsd_id, @RequestParam("ngay") int ngay, @RequestParam("thang") int thang, 
			@RequestParam("nam") int nam, RedirectAttributes redirectAttributes, HttpSession session, HttpServletRequest request) {
		
		int chiso = Integer.parseInt(chiso_jsp);
		
		System.out.println("Chi so:  " + chiso);
		System.out.println("lsd id: " + lsd_id);
		System.out.println("khach hang id: " + khachhang_id);
		
		String ngay_do = String.format("%04d-%02d-%02d", nam, thang, ngay);
		
		System.out.println("ngay do: " + ngay_do);
		
		String message = "";
		boolean isError = false;
		
		MeasurementHistory prelsd = mdDAO.getPreviousLDSByLsdIdAndKhId(lsd_id, khachhang_id);
		MeasurementHistory nextlsd = mdDAO.getNextLDSByLsdIdAndKhId(lsd_id, khachhang_id);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		boolean canUpdate = true;
		
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
	public String search_lsd_khachhang(Model model, @RequestParam("search_lsdid") String lsd_id,
			@RequestParam("search_khachhangid") String khachhang_id, @RequestParam("search_dhdid") String dhd_id, @RequestParam("search_ngaydo") String ngaydo) {
		
		
		List<MeasurementHistory> list_lsd = mdDAO.searchLsdKhachhang(lsd_id, khachhang_id, dhd_id, ngaydo);
		model.addAttribute("list_lsd", list_lsd);
		
		return "employee/quan_ly_lich_su_do_khach_hang";
	}
	
	public static void main(String[] args) {
		String date1 = "2020-1-1";
		String date2 = "2019-10-2";
		
		if (date1.compareTo(date2) > 0) {
			System.out.println("date1 lon hon date2");
		} else {
			System.out.println("nguoc lai");
		}
	}
}
