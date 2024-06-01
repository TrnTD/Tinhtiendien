package com.Tinhtiendien.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.Collections;
import java.util.Comparator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.Tinhtiendien.DAO.*;
import com.Tinhtiendien.DAO.*;
import com.Tinhtiendien.Models.*;

@Controller
public class KhachHangController {
	
	@Autowired
	AccountDAO accountDAO = new AccountDAO();
	
	@Autowired
	ChiTietHoaDonDAO chitiethoadonDAO = new ChiTietHoaDonDAO();
	
	@Autowired
	DongHoDienDAO donghodienDAO = new DongHoDienDAO();
	
	@Autowired
	MeasurementHistoryDAO mdDAO = new MeasurementHistoryDAO();
	
	@Autowired
	LichGhiChiSoDAO lgcsDAO = new LichGhiChiSoDAO();
	
	@Autowired
	HoaDonDAO hoadonDAO = new HoaDonDAO();
	
	@RequestMapping(value = "/nguoi_dung/quan_ly_chung", method = RequestMethod.GET)
	public String quan_ly_chung(HttpSession session, Model model, RedirectAttributes redirectAttributes,
			HttpServletRequest request) throws JsonProcessingException, ServletException, IOException {
        
        Info info = (Info) session.getAttribute("info_khachhang");
		String makh = info.getKhachhang_id();
		
		List<Integer> list_2YearNearest = hoadonDAO.get_2YearsNearest(makh);
		
		List<HoaDon> list_hoadon_lastyear = null;
		List<HoaDon> list_hoadon_currentyear = null;
		
		ArrayList<Integer> list_chiso_lastyear = new ArrayList<Integer>();
		ArrayList<Integer> list_chiso_currentyear = new ArrayList<Integer>();
		
		ArrayList<Integer> list_tien_lastyear = new ArrayList<Integer>();
		ArrayList<Integer> list_tien_currentyear = new ArrayList<Integer>();
		
		
		if (list_2YearNearest.size() == 1) {
			list_hoadon_currentyear = hoadonDAO.getAllInfoHoaDonByYear(makh, list_2YearNearest.get(0));
			
//			Collections.reverse(list_hoadon_currentyear);
			
			for (HoaDon hoadon : list_hoadon_currentyear) {
				list_chiso_currentyear.add(hoadon.getDien_tieu_thu());
				list_tien_currentyear.add(hoadon.getTong_tien());
			}
			
			request.setAttribute("list_chiso_currentyear", list_chiso_currentyear);
			request.setAttribute("list_tien_currentyear", list_tien_currentyear);
			
		} else {
			list_hoadon_lastyear = hoadonDAO.getAllInfoHoaDonByYear(makh, list_2YearNearest.get(0));
			list_hoadon_currentyear = hoadonDAO.getAllInfoHoaDonByYear(makh, list_2YearNearest.get(1));
			
//			Collections.reverse(list_hoadon_lastyear);
//			Collections.reverse(list_hoadon_currentyear);
			
			for (HoaDon hoadon : list_hoadon_lastyear) {
				list_chiso_lastyear.add(hoadon.getDien_tieu_thu());
				list_tien_lastyear.add(hoadon.getTong_tien());
			}
			
			for (HoaDon hoadon : list_hoadon_currentyear) {
				list_chiso_currentyear.add(hoadon.getDien_tieu_thu());
				list_tien_currentyear.add(hoadon.getTong_tien());
			}
			
			request.setAttribute("list_chiso_lastyear", list_chiso_lastyear);
			request.setAttribute("list_tien_lastyear", list_tien_lastyear);
			
			request.setAttribute("list_chiso_currentyear", list_chiso_currentyear);
			request.setAttribute("list_tien_currentyear", list_tien_currentyear);
		}
		
		request.setAttribute("list_2YearNearest", list_2YearNearest);

		return "user/quan_ly_chung";
	}
	
	
	@RequestMapping(value = "/nguoi_dung/thong_tin_hoa_don", method = RequestMethod.GET)
	public String thong_tin_hoa_don(HttpSession session, Model model) {
		Info info = (Info) session.getAttribute("info_khachhang");
		String makh = info.getKhachhang_id();
		List<HoaDon> list_hoadon = hoadonDAO.getAllInfoHoaDon(makh);
		
		
		model.addAttribute("list_hoadon", list_hoadon);
		return "user/thong_tin_hoa_don";
	}
	
	
	
	@RequestMapping(value = "/nguoi_dung/tra_cuu_hoa_don", method = RequestMethod.GET)
	public String render_tra_cuu_hoa_don(HttpSession session, Model model) {

		Info info = (Info) session.getAttribute("info_khachhang");
        String makh = info.getKhachhang_id();
		
		int nam_dangky = donghodienDAO.getNamDangKy(makh);
		
		model.addAttribute("nam_dangky", nam_dangky);
		
		return "user/tra_cuu_hoa_don";
	}
	
	@RequestMapping(value = "/nguoi_dung/tra_cuu_hoa_don", method = RequestMethod.POST)
    public String get_tra_cuu_hoa_don(HttpSession session, Model model, HttpServletResponse response, @RequestParam("month") int thang, @RequestParam("year") int nam) {

        Info info = (Info) session.getAttribute("info_khachhang");
        String makh = info.getKhachhang_id();

        int nam_dangky = donghodienDAO.getNamDangKy(makh);
		
		model.addAttribute("nam_dangky", nam_dangky);
        
        HoaDon hoadon = hoadonDAO.getAllInfoHoaDonByDate(makh, thang, nam);
        
        boolean issetHoaDon = false;
        if (hoadon != null) {
        	issetHoaDon = true;
        }
        
        model.addAttribute("hoadon", hoadon);
        model.addAttribute("issetHoaDon", issetHoaDon);
        
		return "/user/tra_cuu_hoa_don";
    }
	
	@RequestMapping(value = "/nguoi_dung/lich_su_thanh_toan", method = RequestMethod.GET)
	public String lich_su_thanh_toan(HttpSession session, Model model) {
		
		Info info = (Info) session.getAttribute("info_khachhang");
		String makh = info.getKhachhang_id();
		
		List<HoaDon> list_lstt = hoadonDAO.getAllInfoHoaDon(makh);
		
		model.addAttribute("list_lstt", list_lstt);
		
		return "/user/lich_su_thanh_toan";
		
	}
	
	@RequestMapping("/nguoi_dung/lich_ghi_chi_so")
	public String layChuKiDO(HttpSession session, Model model) {
		Info info = (Info) session.getAttribute("info_khachhang");
		String makh = info.getKhachhang_id();
		
		List<LichGhiChiSo> lckd = lgcsDAO.getTT(makh); 

		model.addAttribute("list_ckd", lckd);
		return "user/lich_ghi_chi_so";
	} 
	

	
	@RequestMapping(value = "/nguoi_dung/lich_su_do", method = RequestMethod.GET)
	public String getLichsu_do(HttpSession session, Model model) {
		
		Info info = (Info) session.getAttribute("info_khachhang");
		String makh = info.getKhachhang_id();
		
		List<MeasurementHistory> listLSDo = new ArrayList<MeasurementHistory>();
		listLSDo = mdDAO.getLSDoTheoChuhoID(makh);
		
		model.addAttribute("listLSDo", listLSDo);
		return "user/lich_su_do";
	}

	@RequestMapping(value = "/nguoi_dung/quan_ly_tai_khoan", method = RequestMethod.GET)
	public String quan_ly_tai_khoan(HttpSession session, Model model) {
		
		Info info = (Info) session.getAttribute("info_khachhang");
		String makh = info.getKhachhang_id();
		
		int ma_dongho = donghodienDAO.getMaDongHo(makh);
		model.addAttribute("ma_dongho", ma_dongho);
		
		return "user/quan_ly_tai_khoan";
	}
	
	// đổi mật khẩu  
	@RequestMapping(value = "/nguoi_dung/quan_ly_tai_khoan", method = RequestMethod.POST, params = { "oldpassword", "newpassword", "renewpassword" })
	public String doi_mat_khau(HttpSession session , Model model, 
			@RequestParam("oldpassword") String oldpassword,
			@RequestParam("newpassword") String newpassword,
			@RequestParam("renewpassword") String renewpassword) {
		
		Info info = (Info) session.getAttribute("info_khachhang");
		String username = info.getUsername();
		
		String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[#?.!@$%^&*-]).{6,}$";
		boolean isValid = Pattern.matches(regex, newpassword);
		boolean passwordChanged = false;

		if (accountDAO.checkOldPassword(username, oldpassword)) {
			if(isValid) {
				if (newpassword.equals(oldpassword)) {
					model.addAttribute("newpassmessage", "Mật khẩu không được trùng với mật khẩu cũ");
				} else {
					if (newpassword.equals(renewpassword)) {
						accountDAO.changePassword(username, newpassword);
						passwordChanged = true;
					} else {
						model.addAttribute("renewpassmessage", "Vui lòng nhập lại đúng mật khẩu");
					}
				}
			} else {
				model.addAttribute("newpassmessage", "Mật khẩu mới không đúng định dạng");
			}
		} else {
			model.addAttribute("oldpassmessage", "Mật khẩu cũ không đúng, vui lòng nhập đúng mật khẩu");
		}	
		
		model.addAttribute("passwordChanged", passwordChanged);
		
		return "user/quan_ly_tai_khoan";
	}
}
