package com.Tinhtiendien.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Tinhtiendien.DAO.DongHoDienDAO;
import com.Tinhtiendien.DAO.InfoDAO;
import com.Tinhtiendien.DAO.QuanLyAccountDAO;

@Controller
public class HomeController {
	
	@Autowired
	QuanLyAccountDAO qlaccountDAO = new QuanLyAccountDAO();
	
	@Autowired
	DongHoDienDAO dhdDAO = new DongHoDienDAO();
	
	@Autowired
	InfoDAO infoDAO = new InfoDAO();
	
	@RequestMapping("/nguoi_dung")
	public String nguoi_dung() {
		return "user/nguoi_dung";
	}

	@RequestMapping("/nhan_vien")
	public String nhan_vien(Model model) {
		
		int ttp = qlaccountDAO.getToTalPageAllAccKhachHang();
		model.addAttribute("total_page_acc", ttp);
		int ttp1 = dhdDAO.getToTalPageAllDongHoKhachHang();
		model.addAttribute("total_page_dongho", ttp1);
		return "employee/nhan_vien";
	}
	
	@RequestMapping("/index")
	public String index(RedirectAttributes redirectAttribute, Model model) {
		
		redirectAttribute.addFlashAttribute("message", "Hello World!");
		
		return "redirect:login";
	}

}
