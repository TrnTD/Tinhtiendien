package com.Tinhtiendien.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.Tinhtiendien.DAO.*;
import com.Tinhtiendien.Models.*;

@Controller
public class AuthController {

	@Autowired
	AccountDAO accountDAO = new AccountDAO();

	@Autowired
	InfoDAO infoDAO = new InfoDAO();

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String render_login() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, params = { "username", "password" })
	public String login(@RequestParam("username") String username, @RequestParam("password") String password,
			Model model) {
		if (username.isEmpty() || password.isEmpty()) {
			return "redirect:login";
		} else if (accountDAO.checkExistAccount(username, password)) {
			if (accountDAO.getRole(username) == 2) {
				System.out.print("Dang nhap vao chu ho thanh cong");
				Info info = infoDAO.getAllInfo(username);

				model.addAttribute("hoten", info.getHovaten());
				model.addAttribute("makh", info.getChuho_id());
				model.addAttribute("diachi", info.getDiachi());
				model.addAttribute("email", info.getEmail());
				model.addAttribute("sdt", info.getSdt());

				return "user/nguoi_dung";
			} else if (accountDAO.getRole(username) == 1) {
				System.out.print("Dang nhap vao quan ly thanh cong");
				return "employee/nhan_vien";
			}
		}

		return "redirect:login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, params = "sdt")
	public String login(@RequestParam("sdt") String sdt) {
		if (sdt.isEmpty()) {
			return "login";
		} else if (sdt.equals("0123456789")) {
			return "redirect:home";
		}

		return "redirect:login";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String render_register() {
		return "register";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String render_home() {
		return "redirect:../Tinhtiendien";
	}
}

