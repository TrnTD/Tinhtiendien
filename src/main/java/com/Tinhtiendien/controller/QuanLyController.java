package com.Tinhtiendien.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Tinhtiendien.DAO.AccountDAO;
import com.Tinhtiendien.DAO.DongHoDienDAO;
import com.Tinhtiendien.DAO.GiaDienDAO;
import com.Tinhtiendien.DAO.HoaDonDAO;
import com.Tinhtiendien.DAO.InfoDAO;
import com.Tinhtiendien.DAO.LichGhiChiSoDAO;
import com.Tinhtiendien.DAO.MeasurementHistoryDAO;
import com.Tinhtiendien.DAO.PhuongThucThanhToanDAO;
import com.Tinhtiendien.DAO.QuanLyAccountDAO;
import com.Tinhtiendien.DAO.YeuCauDAO;
import com.Tinhtiendien.Models.*;

import KtraDuLieu.KtraDuLieu;
import KtraDuLieu.MaHoa;
import KtraDuLieu.isError;
import KtraDuLieu.isSuccess;

@Controller
public class QuanLyController {
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
	AccountDAO account = new AccountDAO();

	@Autowired
	HoaDonDAO hoadonDAO = new HoaDonDAO();

	@Autowired
	GiaDienDAO giaDien_DAO = new GiaDienDAO();

	@Autowired
	PhuongThucThanhToanDAO ptttDAO = new PhuongThucThanhToanDAO();

	@Autowired
	YeuCauDAO yeucauDAO = new YeuCauDAO();
	
	@RequestMapping(value = "/quan_ly", method = RequestMethod.GET)
	public String nhan_vien(HttpSession session, HttpServletRequest request) {
		
		if (session.getAttribute("info_quanly") == null) {
			return "redirect:/login";
		}

		
		return "redirect:/quan_ly/quan_ly_chung";
	}
	
	@RequestMapping(value = "/quan_ly/quan_ly_chung", method = RequestMethod.GET)
	public String quan_ly_chung(HttpSession session, HttpServletRequest request) {
		
		if (session.getAttribute("info_quanly") == null) {
			return "redirect:/login";
		}

		List<Integer> list_dientieuthu = hoadonDAO.get_dientieuthu_by_year(2010);
		List<Integer> list_sotien = hoadonDAO.get_doanhthu_by_year(2010);
		
		request.setAttribute("list_dientieuthu", list_dientieuthu);
		request.setAttribute("list_sotien", list_sotien);
		
		
		return "manager/quan_ly_chung";
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////// QUAN LY THONG TIN NHAN VIEN //////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value = "/quan_ly/quan_ly_thong_tin_nhan_vien", method = RequestMethod.GET)
	public String quanly_thongtinnhanvien(HttpSession session, Model model,
			@RequestParam(value = "cur_page", defaultValue = "1") int cur_page,
			@RequestParam(value = "limit", defaultValue = "10") int limit) {

		if (session.getAttribute("info_quanly") == null) {
			return "redirect:/login";
		}

		List<InfoNhanVien> listNV = infoDAO.getAllPageInfoNhanVien(cur_page);

		int total_page = infoDAO.getToTalPageAllNhanVien();

		model.addAttribute("listNV", listNV);

		model.addAttribute("curr_page", cur_page);
		model.addAttribute("total_page", total_page);

		return "manager/quan_ly_thong_tin_nhan_vien";
	}

	@RequestMapping(value = "/quan_ly/quan_ly_thong_tin_nhan_vien/them", method = RequestMethod.POST, params = {
			"hoten", "gioitinh", "ngay", "thang", "nam", "email", "sdt", "cccd", "diachi" })
	public String them_moi_thongtinnhanvien(Model model, HttpSession session, HttpServletRequest request,
			RedirectAttributes redirectAttributes, @RequestParam("hoten") String hoten,
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
		int index = url.indexOf("/quan_ly");
		url = url.substring(index);

		if (!email.isEmpty()) {
			if (infoDAO.checkEmailTrung(email) == true || infoDAO.checkEmailTrungNV(email)) {
				redirectAttributes.addFlashAttribute("err_mess_addEmail", "Email đã được sử dụng!");
				isError = true;
				canAdd = false;
			}
		}

		if (infoDAO.checkSoDienThoaiTrung(sdt) == true || infoDAO.checkSoDienThoaiTrungNV(sdt)) {
			redirectAttributes.addFlashAttribute("err_mess_addPhone", "Số điện thoại đã được sử dụng!");
			isError = true;
			canAdd = false;
		}

		if (infoDAO.checkCanCuocCongDanTrung(cccd) == true || infoDAO.checkCanCuocCongDanTrungNV(cccd) == true) {
			redirectAttributes.addFlashAttribute("err_mess_addCCCD", "Căn cước đã được sử dụng!");
			isError = true;
			canAdd = false;
		}
		if (canAdd == true) {
			if (infoDAO.addNewNhanVien(hoten, gioitinh, ngaysinh, email, sdt, cccd, diachi)) {
				message = "Bạn đã thêm nhân viên thành công";
				session.setAttribute("message", message);
				session.setAttribute("isError", isError);
				return "redirect:" + url;
			} else {
				message = "Thêm nhân viên không thành công";
				isError = true;
				session.setAttribute("message", message);
				session.setAttribute("isError", isError);
				return "redirect:" + url;
			}
		}

		return "redirect:" + url;
	}

	@RequestMapping(value = "/quan_ly/quan_ly_thong_tin_nhan_vien/sua", method = RequestMethod.POST, params = { "hoten",
			"gioitinh", "ngay", "thang", "nam", "email", "sdt", "cccd", "diachi", "nhanvien_id" })
	public String chinh_sua_thongtinnhanvien(Model model, HttpSession session, HttpServletRequest request,
			RedirectAttributes redirectAttributes, @RequestParam("hoten") String hoten,
			@RequestParam("gioitinh") String gioitinh, @RequestParam("ngay") String ngay,
			@RequestParam("thang") String thang, @RequestParam("nam") String nam, @RequestParam("email") String email,
			@RequestParam("sdt") String sdt, @RequestParam("cccd") String cccd, @RequestParam("diachi") String diachi,
			@RequestParam("nhanvien_id") String nhanvien_id) {

		boolean isError = false;
		boolean canUpdate = true;
		String message = "";

		System.out.println(nhanvien_id);
		// Lấy thông tin khách hàng hiện tại
		InfoNhanVien currentNhanVien = infoDAO.getNhanVienByID(nhanvien_id);

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
		int index = url.indexOf("/quan_ly");
		url = url.substring(index);

		if (!currentNhanVien.getEmail().equals(email) && email != null) {
			if (infoDAO.checkEmailTrung(email) == true || infoDAO.checkEmailTrungNV(email) == true) {
				redirectAttributes.addFlashAttribute("err_mess_editEmail", "Email đã được sử dụng!");
				isError = true;
				canUpdate = false;
			}
		}

		// Kiểm tra sdt
		if (!currentNhanVien.getSdt().equals(sdt)) {
			if (infoDAO.checkSoDienThoaiTrung(sdt) == true || infoDAO.checkSoDienThoaiTrungNV(sdt)) {
				redirectAttributes.addFlashAttribute("err_mess_editPhone", "Số điện thoại đã được sử dụng!");
				isError = true;
				canUpdate = false;
			}
		}
		// Kiểm tra CCCD
		if (!currentNhanVien.getCccd().equals(cccd)) {
			if (infoDAO.checkCanCuocCongDanTrung(cccd) == true || infoDAO.checkCanCuocCongDanTrungNV(cccd) == true) {
				redirectAttributes.addFlashAttribute("err_mess_editCCCD", "Căn cước đã được sử dụng!");
				isError = true;
				canUpdate = false;
			}
		}

		if (canUpdate == true) {
			if (infoDAO.updateNhanVien(hoten, gioitinh, ngaysinh, email, sdt, cccd, diachi, nhanvien_id)) {
				message = "Bạn đã cập nhật nhân viên " + nhanvien_id + " thành công";
				session.setAttribute("message", message);
				session.setAttribute("isError", isError);
				return "redirect:" + url;
			} else {
				message = "Cập nhật nhân viên " + nhanvien_id + " không thành công";
				isError = true;
				session.setAttribute("message", message);
				session.setAttribute("isError", isError);
				return "redirect:" + url;
			}
		}

		return "redirect:" + url;
	}

	@RequestMapping(value = "/quan_ly/quan_ly_thong_tin_nhan_vien/xoa", method = RequestMethod.POST, params = {
			"nv_id" })
	public String xoa_thongtinnhanvien(Model model, HttpSession session, @RequestParam("nv_id") String nhanvien_id) {
		boolean isError = false;
		String message = "";
		if (infoDAO.deleteNhanVien(nhanvien_id) == true) {
			message = "Bạn đã xóa thành công nhân viên có mã: " + nhanvien_id;
		} else {
			message = "Xóa nhân viên có mã: " + nhanvien_id + " không thành công";
			isError = true;
		}

		session.setAttribute("message", message);
		session.setAttribute("isError", isError);

		return "redirect:/quan_ly/quan_ly_thong_tin_nhan_vien";
	}

	@RequestMapping(value = "/quan_ly/quan_ly_thong_tin_nhan_vien/tim_kiem", method = RequestMethod.GET)
	public String tim_kiem_nhan_vien(Model model, HttpSession session,
			@RequestParam(value = "cur_page", defaultValue = "1") int cur_page,
			@RequestParam(value = "limit", defaultValue = "10") int limit,
			@RequestParam("search_nhanvien_id") String nhanvien_id, @RequestParam("search_hoten") String hoten,
			@RequestParam("search_gioitinh") String gioitinh, @RequestParam("search_ngaysinh") String ngaysinh,
			@RequestParam("search_email") String email, @RequestParam("search_sdt") String sdt,
			@RequestParam("search_cccd") String cccd, @RequestParam("search_diachi") String diachi) {

		if (session.getAttribute("info_quanly") == null) {
			return "redirect:/login";
		}

		if (nhanvien_id.isEmpty() && hoten.isEmpty() && gioitinh.isEmpty() && ngaysinh.isEmpty() && email.isEmpty()
				&& sdt.isEmpty() && cccd.isEmpty() && diachi.isEmpty()) {
			List<InfoNhanVien> listNV = null;
			model.addAttribute("listNV", listNV);
			return "manager/quan_ly_thong_tin_nhan_vien";
		}

		if (gioitinh.equals("nam")) {
			gioitinh = "Nam";
		} else if (gioitinh.equals("nu")) {
			gioitinh = "Nữ";
		}

		List<InfoNhanVien> listNV = infoDAO.searchNhanVien(nhanvien_id, hoten, gioitinh, ngaysinh, email, sdt, cccd,
				diachi);

		int total_page = infoDAO.tong_trang_search_nhan_vien(nhanvien_id, hoten, gioitinh, ngaysinh, email, sdt, cccd,
				diachi);

		model.addAttribute("listNV", listNV);
		model.addAttribute("curr_page", cur_page);
		model.addAttribute("total_page", total_page);

		return "manager/quan_ly_thong_tin_nhan_vien";
	}
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////// QUAN LY LICH THONG TIN KHACH HANG ///////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value = "/quan_ly/quan_ly_thong_tin_khach_hang", method = RequestMethod.GET)
	public String quanly_thongtinkhachhang(HttpSession session, Model model, @RequestParam(value ="cur_page",defaultValue = "1") int cur_page, 
			@RequestParam(value = "limit",defaultValue = "10") int limit) {
		
		if (session.getAttribute("info_quanly") == null) {
			return "redirect:/login";
		}
		
		List<Info> listKH = infoDAO.getAllPageInfoKhachHang(cur_page);
		
		int total_page = infoDAO.getToTalPageAllKhachHang();
				
		model.addAttribute("listKH", listKH);
		
		model.addAttribute("curr_page", cur_page);
		model.addAttribute("total_page", total_page);


		return "manager/quan_ly_thong_tin_khach_hang";
	}
	
	@RequestMapping(value = "/quan_ly/quan_ly_thong_tin_khach_hang/them", method = RequestMethod.POST, params = {
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
		int index = url.indexOf("/quan_ly");
		url = url.substring(index);
		
		if (!email.isEmpty()) {
			if (infoDAO.checkEmailTrung(email) == true || infoDAO.checkEmailTrungNV(email) == true) {
				redirectAttributes.addFlashAttribute("err_mess_addEmail", "Email đã được sử dụng!");
				isError = true;
				canAdd = false;
			}
		}
		
		if (infoDAO.checkSoDienThoaiTrung(sdt) == true || infoDAO.checkSoDienThoaiTrungNV(sdt) == true) {
			redirectAttributes.addFlashAttribute("err_mess_addPhone", "Số điện thoại đã được sử dụng!");
			isError = true;
			canAdd = false;
		} 
		
		if (infoDAO.checkCanCuocCongDanTrung(cccd) == true || infoDAO.checkCanCuocCongDanTrungNV(cccd) == true) {	
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
	

	@RequestMapping(value = "/quan_ly/quan_ly_thong_tin_khach_hang/sua", method = RequestMethod.POST, params = {
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
		int index = url.indexOf("/quan_ly");
		url = url.substring(index);
		
		if (!currentKhachHang.getEmail().equals(email) && email != null) {
			if (infoDAO.checkEmailTrung(email) == true || infoDAO.checkEmailTrungNV(email) == true) {
				redirectAttributes.addFlashAttribute("err_mess_editEmail", "Email đã được sử dụng!");
				isError = true;
				canUpdate = false;
			}
		}
		
		// Kiểm tra sdt
		if (!currentKhachHang.getSdt().equals(sdt)) {
			if (infoDAO.checkSoDienThoaiTrung(sdt) == true || infoDAO.checkSoDienThoaiTrungNV(sdt) == true) {
				redirectAttributes.addFlashAttribute("err_mess_editPhone", "Số điện thoại đã được sử dụng!");
				isError = true;
				canUpdate = false;
			}
		}
		//Kiểm tra CCCD
		if (!currentKhachHang.getCccd().equals(cccd)) {
			if (infoDAO.checkCanCuocCongDanTrung(cccd) == true || infoDAO.checkCanCuocCongDanTrungNV(cccd) == true) {
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
	
	
	@RequestMapping(value = "/quan_ly/quan_ly_thong_tin_khach_hang/xoa",method = RequestMethod.POST, params = { "kh_id"})
	public String xoa_thongtinnguoidung(Model model, HttpSession session,
											@RequestParam("kh_id") String khachhang_id) {
		boolean isError = false;
		String message = "";	
//		if (mdDAO.checkKhachHangInLSD(khachhang_id) == true || lgcsDAO.checkKhachHangInLGCS(khachhang_id) == true || donghodienDAO.checkKhachHangInDongHoDien(khachhang_id) == true) {
//			message  = "Không thể xóa vì xung đột dữ liệu";
//			isError = true;
//		} else {
			if (infoDAO.deleteKhachHang(khachhang_id) == true) {
				message = "Bạn đã xóa thành công khách hàng có mã: " + khachhang_id;
			} else {
				message = "Xóa khách hàng có mã: " + khachhang_id + " không thành công";
				isError = true;
			}
//		}
		
		session.setAttribute("message", message);
		session.setAttribute("isError", isError);
		
		return "redirect:/nhan_vien/quan_ly_thong_tin_khach_hang";
	}
	
	@RequestMapping(value = "/quan_ly/quan_ly_thong_tin_khach_hang/tim_kiem", method = RequestMethod.GET)
	public String tim_kiem_khach_hang(Model model, HttpSession session, @RequestParam(value ="cur_page",defaultValue = "1") int cur_page, 
			@RequestParam(value = "limit",defaultValue = "10") int limit, @RequestParam("search_khachhang_id") String khachhang_id, @RequestParam("search_hoten") String hoten,
			@RequestParam("search_gioitinh") String gioitinh, @RequestParam("search_ngaysinh") String ngaysinh, @RequestParam("search_email") String email,
			@RequestParam("search_sdt") String sdt, @RequestParam("search_cccd") String cccd, @RequestParam("search_diachi") String diachi
			) {
		
		if (session.getAttribute("info_quanly") == null) {
			return "redirect:/login";
		}
		
		if (khachhang_id.isEmpty() && hoten.isEmpty() && gioitinh.isEmpty() && ngaysinh.isEmpty() && email.isEmpty() && sdt.isEmpty() && cccd.isEmpty() && diachi.isEmpty()) {
			List<Info> listKH = null;
			model.addAttribute("listKH", listKH);
			return "manager/quan_ly_thong_tin_khach_hang";
		}
		
		if (gioitinh.equals("nam")) {
			gioitinh = "Nam";
		} else if (gioitinh.equals("nu")) {
			gioitinh = "Nữ";
		}
		
		List<Info> listKH = infoDAO.searchKhachHang(khachhang_id, hoten, gioitinh, ngaysinh, email, sdt, cccd, diachi);
		
		int total_page = infoDAO.tong_trang_search(khachhang_id, hoten, gioitinh, ngaysinh, email, sdt, cccd, diachi);
		
		model.addAttribute("listKH", listKH);
		model.addAttribute("curr_page", cur_page);
		model.addAttribute("total_page", total_page);
		
		return "manager/quan_ly_thong_tin_khach_hang";
	}
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////// QUAN LY TAI KHOAN NHAN VIEN //////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



	@RequestMapping(value="/quan_ly/quan_ly_tai_khoan_nhan_vien", method= RequestMethod.GET)
	public String layTaiKhoan(Model model, RedirectAttributes redirectAttributes,
			@RequestParam(value ="cur_page",defaultValue = "1") int cur_page,
			@RequestParam(value = "limit",defaultValue = "10") int limit ) {
		
		List<QuanLyAccountNV> listAcc = qlaccountDAO.getAllAccountsNVInPage(cur_page);
		int ttp = qlaccountDAO.getToTalPageAllAccNhanVien();
		model.addAttribute("list_acc", listAcc);
		model.addAttribute("curr_page", cur_page);
		model.addAttribute("total_page", ttp);
		return "manager/quan_ly_tai_khoan_nhan_vien";
	}

	
	@RequestMapping(value = "/quan_ly/quan_ly_tai_khoan_nhan_vien/tim_kiem", method= RequestMethod.GET)
	public String tim_kiem(HttpSession session, Model model,RedirectAttributes redirectAttributes,HttpServletRequest request,
			@RequestParam(value = "nv_id") String nv_id,
			@RequestParam(value ="cur_page",defaultValue = "1") int cur_page,
			@RequestParam(value = "limit",defaultValue = "10") int limit,
			@RequestParam(value = "all", required = false) String all) 
	{
		if (session.getAttribute("info_quanly") == null) {
			return "redirect:/login";
		}
		
		if ("search_all".equals(all))
		{
			int ttp = qlaccountDAO.getToTalPageAllAccNhanVien();
			redirectAttributes.addAttribute("curr_page", 1);
			redirectAttributes.addAttribute("limit", ttp);
			return "redirect:/quan_ly/quan_ly_tai_khoan_nhan_vien";
		}
		if (nv_id == "")
		{
			String url = request.getHeader("Referer");
			redirectAttributes.addFlashAttribute("tb_rong", "Vui lòng nhập thông tin tìm kiếm");
			return "redirect:" + url;
		}
		nv_id = nv_id.trim();
		List<QuanLyAccountNV> account = qlaccountDAO.getAllAccountsNVInPageBySearch(cur_page,nv_id);
		int ttp = qlaccountDAO.getToTalPageAllAccNhanVienBySearch(nv_id);
		model.addAttribute("list_acc", account);
		model.addAttribute("search_id",nv_id);
		model.addAttribute("curr_page", cur_page);
		model.addAttribute("total_page", ttp);
		return "manager/quan_ly_tai_khoan_nhan_vien";
	}


	@RequestMapping(value = "/quan_ly/quan_ly_tai_khoan_nhan_vien", method = RequestMethod.POST)
	public String xu_li_hanh_dong_quan_li_acc(HttpSession session, RedirectAttributes redirectAttributes,Model model,HttpServletRequest request,
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
				redirectAttributes.addFlashAttribute("tb_err", "Nhân viên chưa có tài khoản để chỉnh sửa!");
				return "redirect:" + url;
			}
			if (newPass != "") {
				if (!KtraDuLieu.ktraMatKhau(newPass)) {
					redirectAttributes.addFlashAttribute("tbDoiMK", "Mật khẩu không đúng yêu cầu!");
				} else {

					thong_bao = qlaccountDAO.changePassword(username, MaHoa.getMD5Hash(newPass), thong_bao);
					redirectAttributes.addFlashAttribute("tb", thong_bao);
				}
			} else {
				redirectAttributes.addFlashAttribute("tbDoiMK", "Mật khẩu không được để trống!");
			}
		} else if (action.equals("add")) {
			if (addUsernameId == "") {
				redirectAttributes.addFlashAttribute("tbThemMNV", "Mã nhân viên không được để trống!");
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
			
			if (infoDAO.checkNhanVienIDandUsername(addUsernameId) == false) {
				redirectAttributes.addFlashAttribute("tbThemMNV", "Mã nhân viên đã có tài khoản hoặc không tồn tại!");
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

			thong_bao = qlaccountDAO.addAccNV(addUsernameId, addUsername, MaHoa.getMD5Hash(addPassWord), thong_bao);
			redirectAttributes.addFlashAttribute("tb", thong_bao);

		} else if (action.equals("delete")) {
			if (username == "")
			{
				redirectAttributes.addFlashAttribute("tb_err", "Nhân viên chưa có tài khoản để xoá!");
				return "redirect:" + url;
			}
			thong_bao = qlaccountDAO.deleteAccNhanVien(username, thong_bao);
			redirectAttributes.addFlashAttribute("tb", thong_bao);
		}
		System.out.println(username);
		System.out.println(action);
//		return "redirect:" + url;
		return "redirect:" + url;
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////// QUAN LY TAI KHOAN KHACH HANG /////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value="/quan_ly/quan_ly_tai_khoan_khach_hang", method= RequestMethod.GET)
	public String layTaiKhoanKH(Model model, RedirectAttributes redirectAttributes,
			@RequestParam(value ="cur_page",defaultValue = "1") int cur_page,
			@RequestParam(value = "limit",defaultValue = "10") int limit ) {
		
		List<QuanLyAccount> listAcc = qlaccountDAO.getAllAccountsUserInPage(cur_page);
		int ttp = qlaccountDAO.getToTalPageAllAccKhachHang();
		model.addAttribute("list_acc", listAcc);
		model.addAttribute("curr_page", cur_page);
		model.addAttribute("total_page", ttp);
		return "manager/quan_ly_tai_khoan_khach_hang";
	}
	
	
	@RequestMapping(value = "/quan_ly/quan_ly_tai_khoan_khach_hang/tim_kiem", method= RequestMethod.GET)
	public String tim_kiemKH(HttpSession session, Model model,RedirectAttributes redirectAttributes,HttpServletRequest request,
			@RequestParam(value = "kh_id") String kh_id,
			@RequestParam(value ="cur_page",defaultValue = "1") int cur_page,
			@RequestParam(value = "limit",defaultValue = "10") int limit,
			@RequestParam(value = "all", required = false) String all) 
	{
		if (session.getAttribute("info_quanly") == null) {
			return "redirect:/login";
		}
		
		if ("search_all".equals(all))
		{
			int ttp = qlaccountDAO.getToTalPageAllAccKhachHang();
			redirectAttributes.addAttribute("curr_page", 1);
			redirectAttributes.addAttribute("limit", ttp);
			return "redirect:/quan_ly/quan_ly_tai_khoan_khach_hang";
		}
		if (kh_id == "")
		{
			String url = request.getHeader("Referer");
			redirectAttributes.addFlashAttribute("tb_rong", "Vui lòng nhập thông tin tìm kiếm");
			return "redirect:" + url;
		}
		kh_id = kh_id.trim();
		List<QuanLyAccount> account = qlaccountDAO.getAllAccountsUserInPageBySearch(cur_page,kh_id);
		int ttp = qlaccountDAO.getToTalPageAllAccKhachHangBySearch(kh_id);
		model.addAttribute("list_acc", account);
		model.addAttribute("search_id",kh_id);
		model.addAttribute("curr_page", cur_page);
		model.addAttribute("total_page", ttp);
		return "manager/quan_ly_tai_khoan_khach_hang";
	}
	
	
	@RequestMapping(value = "/quan_ly/quan_ly_tai_khoan_khach_hang", method = RequestMethod.POST)
	public String xu_li_hanh_dong_quan_li_acc_khachhang(HttpSession session, RedirectAttributes redirectAttributes,Model model,HttpServletRequest request,
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

		return "redirect:" + url;
	}
	
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////QUAN LY DONG HO DIEN /////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@RequestMapping("/quan_ly/quan_ly_dong_ho_dien_khach_hang")
	public String layDongHo(Model model, @RequestParam(value = "cur_page", defaultValue = "1") int cur_page,
			@RequestParam(value = "limit", defaultValue = "10") int limit) {
		List<Info> listInf = infoDAO.getPageDongHoKhachHang(cur_page);
		model.addAttribute("list_dh", listInf);
		int ttp = infoDAO.getToTalPageAllKhachHang();
		model.addAttribute("curr_page", cur_page);
		model.addAttribute("total_page", ttp);
		return "manager/quan_ly_dong_ho_dien_khach_hang";
	}

	@RequestMapping(value = "/quan_ly/quan_ly_dong_ho_dien_khach_hang/tim_kiem", method = RequestMethod.GET)
	public String tim_kiem_dong_ho(HttpSession session, Model model,RedirectAttributes redirectAttributes,HttpServletRequest request,
			@RequestParam(value = "kh_id") String kh_id,
			@RequestParam(value = "dh_id") String dh_id,
			@RequestParam(value = "search_tungay") String tuNgay,
			@RequestParam(value = "search_denngay") String denNgay,
			@RequestParam(value ="cur_page",defaultValue = "1") int cur_page,
			@RequestParam(value = "limit",defaultValue = "10") int limit,
			@RequestParam(value = "all", required = false) String all) 
	{
		if (session.getAttribute("info_nhanvien") == null) {
			return "redirect:/login";
		}
		
		if ("search_all".equals(all))
		{
			int ttp = infoDAO.getToTalPageAllKhachHang();
			redirectAttributes.addAttribute("curr_page", 1);
			redirectAttributes.addAttribute("limit", ttp);
			return "redirect:/quan_ly/quan_ly_dong_ho_dien_khach_hang";
		}
//		if (kh_id == "" && dh_id == "")
//		{
//			String url = request.getHeader("Referer");
//			redirectAttributes.addFlashAttribute("tb_rong", "Vui lòng nhập thông tin tìm kiếm");
//			return "redirect:" + url;
//		}
		else
		{
			System.out.println(tuNgay);
			if (kh_id != "") kh_id = kh_id.trim();
			if (dh_id != "") dh_id = dh_id.trim();
			model.addAttribute("search_id_tuNgay",tuNgay);
			model.addAttribute("search_id_denNgay",denNgay);
			if (tuNgay == "") tuNgay = null;
			if (denNgay == "") denNgay = null;
			List<Info> listInf = infoDAO.getPageDongHoKhachHangBySearch(cur_page,kh_id, dh_id,tuNgay,denNgay);
			int ttp = infoDAO.getToTalPageDongHoKhachHang(kh_id,dh_id,tuNgay,denNgay);
			model.addAttribute("total_page", ttp); 
			model.addAttribute("list_dh", listInf);
			model.addAttribute("curr_page", cur_page);
			model.addAttribute("search_id_khachhang",kh_id);
			model.addAttribute("search_id_dongho",dh_id);

		}
		
		return "manager/quan_ly_dong_ho_dien_khach_hang";
	}

	@RequestMapping(value="/quan_ly/quan_ly_dong_ho_dien_khach_hang", method = RequestMethod.POST)
	public String xu_li_quan_ly_dong_ho(RedirectAttributes redirectAttributes, HttpServletRequest request,
			@RequestParam("khachHangId") String khachHangId, @RequestParam("action") String action,
			@RequestParam("doiNDK") String doiNDK) {
		String url = request.getHeader("Referer");
		String thong_bao = null;
		if (action.equals("renew")) {
			thong_bao = infoDAO.updateDongHoId(khachHangId, thong_bao);
			redirectAttributes.addFlashAttribute("tb", thong_bao);
		}
		else if (action.equals("edit"))
		{
			String temp = infoDAO.getNgayThangNamSinhKH(khachHangId);
			if (!KtraDuLieu.isOlderThan18(temp, doiNDK))
			{
				redirectAttributes.addFlashAttribute("tbDoiNDK", "Ngày đăng ký phải từ 18 tuổi trở lên!");
				return "redirect:" + url;
			}
			else
			{
				thong_bao = infoDAO.updateNgayDangKy(khachHangId, doiNDK, thong_bao);
				redirectAttributes.addFlashAttribute("tb", thong_bao);	
			}
		}
		return "redirect:" + url;
	}
	
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////QUAN LY LICH SU DO ///////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	@RequestMapping(value = "/quan_ly/quan_ly_lich_su_do_khach_hang", method = RequestMethod.GET)
	public String get_lsd_khachhang(HttpSession session, Model model,
			@RequestParam(value = "cur_page", defaultValue = "1") int cur_page,
			@RequestParam(value = "limit", defaultValue = "10") int limit) {

		if (session.getAttribute("info_quanly") == null) {
			return "redirect:/login";
		}

//List<MeasurementHistory> list_lsd = mdDAO.getLSDoTheoFirstChuhoID();
		List<MeasurementHistory> list_lsd = mdDAO.getAllLSDInPage(cur_page);
		model.addAttribute("list_lsd", list_lsd);

		int total_page = mdDAO.tong_trang();

		model.addAttribute("curr_page", cur_page);
		model.addAttribute("total_page", total_page);

		return "manager/quan_ly_lich_su_do_khach_hang";
	}
	
	
	
	@RequestMapping(value = "/quan_ly/quan_ly_lich_su_do_khach_hang/them", method = RequestMethod.POST)
	public String them_lsd_khachhang(@RequestParam("add_dongho_id") String dongho_id, @RequestParam("thang") String thang, 
			@RequestParam("nam") String nam, @RequestParam("add_chiso") String chiso, RedirectAttributes redirectAttributes,
			HttpSession session, HttpServletRequest request) {

		boolean isError = false;
		boolean canAdd = true;
		String message = "";
		
		if (thang == "0" || nam == "0") {
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
					
					if (thang.equals("0") || nam.equals("0")) {
						redirectAttributes.addFlashAttribute("err_mess_addNgaydo", "Ngày đo không được để trống!");
						canAdd = false;
					} else {
						int ngay = mdDAO.getDayFirstLSD(dongho_id);
						LocalDate localDate = LocalDate.of(Integer.parseInt(nam), Integer.parseInt(thang), ngay);
						
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
			if (mdDAO.addNewLsd(dongho_id, thang, nam, chiso)) {
				message = "Bạn đã thêm chỉ số khách hàng thành công";
				session.setAttribute("message", message);
				session.setAttribute("isError", isError);
				
				return "redirect:" + url;
			}
		}
		
		
		return "redirect:" + url;
	}
	
	
	@RequestMapping(value = "/quan_ly/quan_ly_lich_su_do_khach_hang/xoa", method = RequestMethod.POST)
	public String xoa_lsd_khachhang(@RequestParam("lsd_id") String lsd_id, HttpServletRequest request,
			RedirectAttributes redirectAttributes, HttpSession session) {

		String url = request.getHeader("Referer");
		int index = url.indexOf("/quan_ly");
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
	
	
	@RequestMapping(value = "/quan_ly/quan_ly_lich_su_do_khach_hang/tim_kiem", method = RequestMethod.GET)
	public String search_lsd_khachhang(HttpSession session, Model model,
			@RequestParam(value = "cur_page", defaultValue = "1") int cur_page,
			@RequestParam(value = "limit", defaultValue = "10") int limit, @RequestParam("search_lsdid") String lsd_id,
			@RequestParam("search_khachhangid") String khachhang_id, @RequestParam("search_dhdid") String dhd_id,
			@RequestParam("search_tungay") String tungay, @RequestParam("search_denngay") String denngay,
			@RequestParam("action") String action, HttpServletRequest request) {

		if (session.getAttribute("info_quanly") == null) {
			return "redirect:/login";
		}

		String url = request.getHeader("Referer");

		if (action.equals("search")) {
			List<MeasurementHistory> list_lsd = mdDAO.searchLsdKhachhang(cur_page, lsd_id, khachhang_id, dhd_id, tungay,
					denngay);

			int total_page = mdDAO.tong_trang_search(lsd_id, khachhang_id, dhd_id, tungay, denngay);

			model.addAttribute("curr_page", cur_page);
			model.addAttribute("total_page", total_page);
			model.addAttribute("list_lsd", list_lsd);
		} else {
			return "redirect:/quan_ly/quan_ly_lich_su_do_khach_hang";
		}

		return "manager/quan_ly_lich_su_do_khach_hang";
	}
	
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////// QUAN LY HOA DON //////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value = "/quan_ly/quan_ly_hoa_don_khach_hang", method = RequestMethod.GET)
	public String get_hoadonkhachhang(HttpSession session, Model model,
			@RequestParam(value = "cur_page", defaultValue = "1") int cur_page,
			@RequestParam(value = "limit", defaultValue = "10") int limit) {

		if (session.getAttribute("info_quanly") == null) {
			return "redirect:/login";
		}

		List<HoaDon> list_hoadon = hoadonDAO.getAllPageHoaDon(cur_page);

		List<PhuongThucThanhToan> list_pttt = ptttDAO.getAllPTTT();

		int total_page = hoadonDAO.tong_trang_hoadon();

		model.addAttribute("curr_page", cur_page);
		model.addAttribute("total_page", total_page);
		model.addAttribute("list_hoadon", list_hoadon);
		model.addAttribute("list_pttt", list_pttt);

		return "manager/quan_ly_hoa_don_khach_hang";
	}
	
	
	@RequestMapping(value = "/quan_ly/quan_ly_hoa_don_khach_hang/tim_kiem", method = RequestMethod.GET)
	public String search_hoadon_khachhang(HttpSession session, Model model,
			@RequestParam("search_hoadonid") String search_hoadonid,
			@RequestParam("search_khachhangid") String search_khachhangid, @RequestParam("search_tungay") String tungay,
			@RequestParam("search_denngay") String denngay, @RequestParam("search_month") String search_month,
			@RequestParam("search_year") String search_year, @RequestParam("search_status") String search_status,
			@RequestParam("action") String action, @RequestParam(value = "cur_page", defaultValue = "1") int cur_page,
			@RequestParam(value = "limit", defaultValue = "10") int limit) {

		if (session.getAttribute("info_quanly") == null) {
			return "redirect:/login";
		}

		List<HoaDon> list_hoadon = new ArrayList<>();

		List<PhuongThucThanhToan> list_pttt = ptttDAO.getAllPTTT();

		if (search_month.equals("-1")) {
			search_month = "";
		}
		if (search_year.equals("-1")) {
			search_year = "";
		}

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
			list_hoadon = hoadonDAO.searchHoaDonKhachHang(cur_page, search_hoadonid, search_khachhangid, tungay,
					denngay, search_month, search_year, search_status);

			int total_page = hoadonDAO.tong_trang_search_hoadon(search_hoadonid, search_khachhangid, tungay, denngay,
					search_month, search_year, search_status);

			model.addAttribute("curr_page", cur_page);
			model.addAttribute("total_page", total_page);
		} else {
			return "redirect:/quan_ly/quan_ly_hoa_don_khach_hang";
		}

		model.addAttribute("list_hoadon", list_hoadon);
		model.addAttribute("list_pttt", list_pttt);

		return "manager/quan_ly_hoa_don_khach_hang";
	}
	
	
	@RequestMapping(value = "/quan_ly/quan_ly_hoa_don_khach_hang/them", method = RequestMethod.POST)
	public String them_hoadon_khachhang(@RequestParam("add_khachhangid") String khachhang_id, @RequestParam("thang") String thang, 
			@RequestParam("nam") String nam, RedirectAttributes redirectAttributes,
			@RequestParam("add_thue") String thue, HttpSession session, HttpServletRequest request) {

		if (session.getAttribute("info_nhanvien") == null) {
			return "redirect:/login";
		}
		
		InfoNhanVien infoNhanVien = (InfoNhanVien) session.getAttribute("info_nhanvien");
		String nhanvien_id = infoNhanVien.getNhanvien_id();
		
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
			if (mdDAO.checkExistLsdAfter(khachhang_id, thang, nam) && mdDAO.checkExistLsdCurrent(khachhang_id, thang, nam)) {
				if (hoadonDAO.addNewHoaDon(nhanvien_id, khachhang_id, thang, nam, thue) ) {
					message = "Thêm hóa đơn khách hàng thành công";
					session.setAttribute("message", message);
					session.setAttribute("isError", isError);
					
					return "redirect:" + url;
				} else {
					redirectAttributes.addFlashAttribute("err_mess_addThoigianhoadon", "Thời gian này đã có hóa đơn");
				}
				
			} else if (!mdDAO.checkExistLsdCurrent(khachhang_id, thang, nam)) {
				message = "Thêm hóa đơn khách hàng thất bại! Chưa có kết quả chỉ số đồng hồ điện của khách hàng " + khachhang_id + " cho tháng " + thang + " năm " + nam + "!";
				session.setAttribute("message", message);
				isError = true;
				session.setAttribute("isError", isError);
			} else if (!mdDAO.checkExistLsdAfter(khachhang_id, thang, nam)) {
				message = "Thêm hóa đơn khách hàng thất bại! Chưa có kết quả chỉ số đồng hồ điện của khách hàng " + khachhang_id + "cho tháng sau!";
				session.setAttribute("message", message);
				isError = true;
				session.setAttribute("isError", isError);
			} 
		}
		
		
	
		return "redirect:" + url;
	}
	
	
	
	@RequestMapping(value = "/quan_ly/quan_ly_hoa_don_khach_hang/sua", method = RequestMethod.POST)
	public String sua_hoadon_khachhang(@RequestParam("khachhang_id") String khachhang_id, @RequestParam("hoadon_id") String hoadon_id,
			@RequestParam("thang") String thang, @RequestParam("nam") String nam, @RequestParam("thue") String thue, 
			@RequestParam("edit_status") String edit_status, @RequestParam("editPTTT") String editPTTT,
			RedirectAttributes redirectAttributes, HttpSession session, HttpServletRequest request) {

		String url = request.getHeader("Referer");
		String message = "";
		boolean isError = false;
		
		System.out.println("hoadon_id: " + hoadon_id);
		System.out.println("khachang_id: " + khachhang_id);
		System.out.println("thang: " + thang);
		System.out.println("nam: " + nam);
		System.out.println("thue: " + thue);
		System.out.println("edit_status: " + edit_status);
		System.out.println("edit PTTT: " + editPTTT);
		
		if (edit_status.equals("0")) {
			edit_status = "Chưa thanh toán";
		} else {
			edit_status = "Đã thanh toán";
		}
		
		if (hoadonDAO.editHoaDon(hoadon_id, thang, nam, thue, edit_status, editPTTT)) {
			message = "Bạn đã cập nhật lịch sử đo của khách hàng thành công";
			session.setAttribute("message", message);
			session.setAttribute("isError", isError);
		} else {
			redirectAttributes.addFlashAttribute("err_mess_editThoigian", "Thời gian này đã có hóa đơn");
		}
		
		
		return "redirect:" + url;

	}
	
	
	@RequestMapping(value = "/quan_ly/quan_ly_hoa_don_khach_hang/xoa", method = RequestMethod.POST)
	public String xoa_hoadon_khachhang(@RequestParam("hoadon_id") String hoadon_id, HttpServletRequest request,
			RedirectAttributes redirectAttributes, HttpSession session) {

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
	
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////QUAN LY LICH SU THANH TOAN ///////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	@RequestMapping(value = "/quan_ly/quan_ly_lich_su_thanh_toan_khach_hang", method = RequestMethod.GET)
	public String get_lichsuthanhtoan_khachhang(HttpSession session, Model model, @RequestParam(value ="cur_page",defaultValue = "1") int cur_page, 
			@RequestParam(value = "limit",defaultValue = "10") int limit) {
		
		if (session.getAttribute("info_quanly") == null) {
			return "redirect:/login";
		}
		
		List<HoaDon> list_hoadon = hoadonDAO.getAllPageLSThanhToan(cur_page);
		List<PhuongThucThanhToan> list_pttt = ptttDAO.getAllPTTT();
		
		int total_page = hoadonDAO.tong_trang_lsthanhtoan();
		
		model.addAttribute("curr_page", cur_page);
		model.addAttribute("total_page", total_page);
		model.addAttribute("list_hoadon", list_hoadon);
		
		model.addAttribute("list_pttt", list_pttt);
		
		
		return "employee/quan_ly_lich_su_thanh_toan_khach_hang";
	}
	
	
	@RequestMapping(value = "/quan_ly/quan_ly_lich_su_thanh_toan_khach_hang/tim_kiem", method = RequestMethod.GET)
	public String search_lichsuthanhtoan_khachhang(HttpSession session, Model model, @RequestParam("search_hoadonid") String search_hoadonid, 
			 @RequestParam("search_khachhangid") String search_khachhangid, @RequestParam("search_tungay") String tungay, 
			 @RequestParam("search_denngay") String denngay,  @RequestParam("search_month") String search_month,
			 @RequestParam("search_year") String search_year,  @RequestParam("search_pttt") String search_pttt,
			 @RequestParam("action") String action, @RequestParam(value ="cur_page",defaultValue = "1") int cur_page, 
			 @RequestParam(value = "limit",defaultValue = "10") int limit) {
		
		if (session.getAttribute("info_nhanvien") == null) {
			return "redirect:/login";
		}
		
		List<HoaDon> list_hoadon = hoadonDAO.getAllPageLSThanhToan(cur_page);
		List<PhuongThucThanhToan> list_pttt = ptttDAO.getAllPTTT();
	
		
		if (search_month.equals("-1")) {
			search_month = "";
		}
		if (search_year.equals("-1")) {
			search_year = "";
		}
		
		if (search_pttt.equals("-1")) {
			search_pttt = "";
		}
		
		if (action.equals("search")) {
			list_hoadon = hoadonDAO.searchLSThanhToan(cur_page, search_hoadonid, search_khachhangid, tungay, denngay, search_month, search_year, search_pttt);	
			
			int total_page = hoadonDAO.tong_trang_search_lsthanhtoan(search_hoadonid, search_khachhangid, tungay, denngay, search_month, search_year, search_pttt);
			
			model.addAttribute("curr_page", cur_page);
			model.addAttribute("total_page", total_page);
		} else {
			return "redirect:/quan_ly/quan_ly_lich_su_thanh_toan_khach_hang";
		}
		
		
		model.addAttribute("list_hoadon", list_hoadon);
		model.addAttribute("list_pttt", list_pttt);
		
		
		return "manager/quan_ly_lich_su_thanh_toan_khach_hang";
	}
	
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////QUAN LY GIA DIEN /////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value = "/quan_ly/quan_ly_gia_dien", method = RequestMethod.GET)
	public String quan_ly_gia_dien(HttpSession session, Model model) {

		if (session.getAttribute("info_quanly") == null) {
			return "redirect:/login";
		}

		List<GiaDien> giaDien = giaDien_DAO.HienThiDanhSach();
		model.addAttribute("list_giaDien", giaDien);
		return "manager/quan_ly_gia_dien";
	}

	@RequestMapping(value = "/quan_ly/quan_ly_gia_dien", method = RequestMethod.POST)
	public String quan_ly_gia_dien_xuLyHanhDong(RedirectAttributes redirectAttributes,
			@RequestParam("action") String action, @RequestParam("add_bacDien") String add_bacDienstr,
			@RequestParam("add_giaDien") String add_giaDienstr, @RequestParam("edit_giaDien") String edit_giaDienstr,
			@RequestParam("bacDien_key") String bacDienstr) {

		boolean isAction = true;

		if (action.equals("add")) {
			System.out.println("action: " + action);
			System.out.println("add_bacDien: " + add_bacDienstr);
			System.out.println("add_giaDien: " + add_giaDienstr);

// Kiểm tra null và định dạng cho bậc điện
			if (add_bacDienstr == null || add_bacDienstr.isEmpty()) {
				redirectAttributes.addFlashAttribute("add_bacDien_id", isError.isNull("Bậc Điện"));
				return "redirect:/quan_ly/quan_ly_gia_dien";
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
						return "redirect:/quan_ly/quan_ly_gia_dien";
					}
// Kiểm tra bậc điện và giá điện trước đó
					GiaDien Previous = giaDien_DAO.getPreviousPrice(add_bacDien);
					GiaDien Next = giaDien_DAO.getNextPrice(add_bacDien);
					if (Previous != null) {
						if (add_bacDien - Previous.getBacDien() > 1) {
							redirectAttributes.addFlashAttribute("add_bacDien_id", isError.isNotValid("Bậc Điện"));
							return "redirect:/quan_ly/quan_ly_gia_dien";
						}
						if (add_giaDien <= Previous.getGiaDien()) {
							redirectAttributes.addFlashAttribute("add_giaDien_id",
									isError.isPrevious("Giá Điện Phải Lớn Hơn"));
							return "redirect:/quan_ly/quan_ly_gia_dien";
						}
					}

					if (Next != null) {
						if (add_giaDien >= Next.getGiaDien()) {
							redirectAttributes.addFlashAttribute("add_giaDien_id",
									isError.isNext("Giá Điện Phải Nhỏ Hơn"));
							return "redirect:/quan_ly/quan_ly_gia_dien";
						}
					}
					giaDien_DAO.Them(add_bacDien, add_giaDien);
					redirectAttributes.addFlashAttribute("message", isSuccess.isComplete("Thêm"));
				} catch (NumberFormatException e) {
					redirectAttributes.addFlashAttribute("message", isError.isFailed("Thêm"));
					return "redirect:/quan_ly/quan_ly_gia_dien";
				}
			}

		} else if (action.equals("edit")) {
			GiaDien Previous = giaDien_DAO.getPreviousPrice(Integer.parseInt(bacDienstr));
			GiaDien Next = giaDien_DAO.getNextPrice(Integer.parseInt(bacDienstr));

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
					if (Previous != null) {
						if (edit_giaDien < Previous.getGiaDien()) {
							redirectAttributes.addFlashAttribute("edit_giaDien_id",
									isError.isPrevious("Giá Tiền Phải Lớn Hơn Giá Tiền Bậc Điện"));
							return "redirect:/quan_ly/quan_ly_gia_dien";
						}
					}
					if (Next != null) {
						if (edit_giaDien > Next.getGiaDien()) {
							redirectAttributes.addFlashAttribute("edit_giaDien_id",
									isError.isNext("Giá Tiền Phải Nhỏ Hơn Giá Tiền Bậc Điện"));
							return "redirect:/quan_ly/quan_ly_gia_dien";
						}
					}
					giaDien_DAO.CapNhat(bacDien, edit_giaDien);
					redirectAttributes.addFlashAttribute("message", isSuccess.isComplete("Cập Nhật"));
				} catch (NumberFormatException e) {
					redirectAttributes.addFlashAttribute("message", isError.isFormat(isError.isFailed("Cập Nhật")));
					return "redirect:/quan_ly/quan_ly_gia_dien";
				}
			}
		} else if (action.equals("delete")) {
			try {
				int bacDien = Integer.parseInt(bacDienstr);
				giaDien_DAO.Xoa(bacDien);
				redirectAttributes.addFlashAttribute("message", isSuccess.isComplete("Xóa"));
			} catch (NumberFormatException e) {
				redirectAttributes.addFlashAttribute("message", isError.isFailed("Xóa"));
				return "redirect:/quan_ly/quan_ly_gia_dien";
			}
		}
		return "redirect:/quan_ly/quan_ly_gia_dien";
	}
	
	

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////// QUAN LY YEU CAU /////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value = "/quan_ly/quan_ly_yeu_cau_khach_hang", method = RequestMethod.GET)
	public String quanly_yeucau_khachhang(HttpSession session, Model model) {

		if (session.getAttribute("info_quanly") == null) {
			return "redirect:/login";
		}

		List<YeuCau> listYC = yeucauDAO.getAllYeuCau();
		model.addAttribute("listYC", listYC);
		return "manager/quan_ly_yeu_cau_khach_hang";
	}

	@RequestMapping(value = "/quan_ly/quan_ly_yeu_cau_khach_hang/sua_tt", method = RequestMethod.POST)
	public String suatt_yeucau_khachhang(Model model, HttpSession session,
			@RequestParam("trangthai") String trang_thai_id, @RequestParam("yeucau_id") String yeucau_id) {
		boolean isError = false;
		String message = "";
		if (yeucauDAO.suaTrangThai(trang_thai_id, yeucau_id) == true) {
			message = "Sửa trạng thái yêu cầu thành công";
		} else {
			message = "Sửa yêu cầu không thành công";
			isError = true;
		}
		session.setAttribute("message", message);
		session.setAttribute("isError", isError);
		return "redirect:/quan_ly/quan_ly_yeu_cau_khach_hang";
	}

	@RequestMapping(value = "/quan_ly/quan_ly_yeu_cau_khach_hang/tim_kiem", method = RequestMethod.GET)
	public String timKiemYeuCau(HttpSession session, Model model,
			@RequestParam("search_khachhang_id") String khachhang_id, @RequestParam("search_ngay_gui") String ngay_gui,
			@RequestParam("search_tua_de") String tua_de, @RequestParam("search_trang_thai") String trang_thai_id) {

		if (session.getAttribute("info_quanly") == null) {
			return "redirect:/login";
		}

		if (khachhang_id.isEmpty() && ngay_gui.isEmpty() && tua_de.isEmpty() && trang_thai_id.isEmpty()) {
			List<YeuCau> listYC = null;
			model.addAttribute("listYC", listYC);
			return "manager/quan_ly_yeu_cau_khach_hang";
		}
		List<YeuCau> listYC = yeucauDAO.timKiemYeuCau(khachhang_id, ngay_gui, tua_de, trang_thai_id);
		model.addAttribute("listYC", listYC);
		return "manager/quan_ly_yeu_cau_khach_hang";
	}
	
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////// QUAN LY TAI KHOAN ////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value = "/quan_ly/quan_ly_tai_khoan", method = RequestMethod.GET)
	public String thong_tin_quan_ly (HttpSession session, InfoNhanVien nhanVien  ) {
	
		if (session.getAttribute("info_quanly") == null) {
		return "redirect:/login";
		}
		
		nhanVien = (InfoNhanVien) session.getAttribute("info_quanly");
		
		return "manager/quan_ly_tai_khoan";
		}
	
	@RequestMapping(value = "/quan_ly/quan_ly_tai_khoan", method = RequestMethod.POST)
	public String doi_mat_khau_nhan_vien(HttpSession session, RedirectAttributes redirectAttributes, 
		@RequestParam("oldpassword") String oldpassword,
		@RequestParam("newpassword") String newpassword,
		@RequestParam("renewpassword") String renewpassword) {
		InfoNhanVien info = (InfoNhanVien) session.getAttribute("info_quanly");
		String username = info.getUsername();	 
		// Kiểm tra mật khẩu cũ
		if (account.checkOldPassword(username, MaHoa.getMD5Hash(oldpassword))) {
		// Kiểm tra định dạng mật khẩu mới
		if (KtraDuLieu.ktraMatKhau(newpassword)) {
		// Đảm bảo mật khẩu mới không trùng với mật khẩu cũ
		if (!newpassword.equals(oldpassword)) {
		// Kiểm tra mật khẩu mới và xác nhận mật khẩu có khớp không
		if (renewpassword.equals(newpassword)) {
		// Thay đổi mật khẩu
		account.changePassword(username, MaHoa.getMD5Hash(newpassword));
		//Thông báo lỗi
		} else {redirectAttributes.addFlashAttribute("renewpassmessage", isError.isSame("Mật Khẩu Mới"));return "redirect:/quan_ly/quan_ly_tai_khoan";}
		} else {redirectAttributes.addFlashAttribute("newpassmessage", isError.isNotSame("Mật Khẩu"));return "redirect:/quan_ly/quan_ly_tai_khoan";}
		} else {redirectAttributes.addFlashAttribute("newpassmessage", isError.isType("Mật Khẩu Mới"));return "redirect:/quan_ly/quan_ly_tai_khoan";}
		} else {redirectAttributes.addFlashAttribute("oldpassmessage", isError.isIncorrect("Mật Khẩu Cũ"));return "redirect:/quan_ly/quan_ly_tai_khoan";}
		
		redirectAttributes.addFlashAttribute("mess", isSuccess.isComplete("Đổi Mật Khẩu"));
		return "manager/quan_ly_tai_khoan";
		}
	}
