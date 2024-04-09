package com.Tinhtiendien.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {
	@RequestMapping("/nguoi_dung")
	public String nguoi_dung() {
		return "user/nguoi_dung";
	}

	@RequestMapping("/nhan_vien")
	public String nhan_vien(Model model) {
		model.addAttribute("message", "Hello world!");
		return "employee/nhan_vien";
	}
	
	@RequestMapping("/index")
	public ModelAndView index(RedirectAttributes redirectAttribute) {
		ModelAndView mav = new ModelAndView("redirect:login");
		redirectAttribute.addFlashAttribute("message", "Hello World!");
		
		return mav;
	}

}
