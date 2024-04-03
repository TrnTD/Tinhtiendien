package com.Tinhtiendien.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	@RequestMapping("/nguoi_dung")
	public String nguoi_dung() {
		return "user/nguoi_dung";
	}

	@RequestMapping("/nhan_vien")
	public String nhan_vien() {
		return "employee/nhan_vien";
	}
}

