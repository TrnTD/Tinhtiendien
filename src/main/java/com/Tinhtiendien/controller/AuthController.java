package com.Tinhtiendien.controller;

import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Tinhtiendien.DAO.*;
import com.Tinhtiendien.Models.*;
import GuiMail.*;

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
			RedirectAttributes redirectAttribute, HttpSession session, Model model) {
		if (username.isEmpty() || password.isEmpty()) {
			return "redirect:login";
		} else if (accountDAO.checkExistAccount(username, password)) {
			if (accountDAO.getRole(username) == 3) {
				System.out.println("Dang nhap vao khach hang thanh cong");
				Info info = infoDAO.getAllInfoKhachHang(username);

				redirectAttribute.addFlashAttribute("hoten", info.getHovaten());
				redirectAttribute.addFlashAttribute("makh", info.getKhachhang_id());
				redirectAttribute.addFlashAttribute("diachi", info.getDiachi());
				redirectAttribute.addFlashAttribute("email", info.getEmail());
				redirectAttribute.addFlashAttribute("sdt", info.getSdt());

				session.setAttribute("info_khachhang", info);
				
				return "redirect:nguoi_dung/quan_ly_chung";
			} else if (accountDAO.getRole(username) == 2) {
				System.out.print("Dang nhap vao quan ly thanh cong");
				
				InfoNhanVien infonv = infoDAO.getAllInfoNhanVien(username);
				
				redirectAttribute.addFlashAttribute("hoten", infonv.getHovaten());
				redirectAttribute.addFlashAttribute("makh", infonv.getNhanvien_id());
				redirectAttribute.addFlashAttribute("email", infonv.getEmail());
				redirectAttribute.addFlashAttribute("sdt", infonv.getSdt());

				session.setAttribute("info_nhanvien", infonv);

				
				return "redirect:nhan_vien/";
			}
		} else {
			model.addAttribute("message_error", "Tài khoản / mã khách hàng hoặc mật khẩu không đúng");
			return "login";
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
	
	@RequestMapping(value = "/register", method = RequestMethod.POST, params = { "khachhangid", "username", "password", "repassword" })
	public String register(Model model,
							@RequestParam("khachhangid") String khachhangid,
							@RequestParam("username") String username,
							@RequestParam("password") String password,
							@RequestParam("repassword") String repassword
							) {
		// Tạo 1 regex để check mật khẩu
		String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[#?.!@$%^&*-]).{6,}$";
		boolean isValid = Pattern.matches(regex, password);
		
		// Nếu Chuho_ID tồn tại và username tương ứng null thì tiếp, sai thì báo lỗi
		if (infoDAO.checkKhachHangIDandUsername(khachhangid)) {
			// Nếu username có trong bảng Tài Khoản thì báo lỗi, sai thì tiếp
			if (accountDAO.checkUsernameAccount(username)) {
				String messageUN = "Tên đăng nhập đã được sử dụng";
				model.addAttribute("messageUN",messageUN);
				return "register";
			} else {
				// Nếu username có trong bảng Chủ Hộ thì báo lỗi, sai thì tiếp
				if (infoDAO.checkUsernameKhachHang(username)) {
					String messageUN = "Tên đăng nhập đã được sử dụng";
					model.addAttribute("messageUN",messageUN);
					return "register";
				} else {
					// Check mật khẩu có đúng định dạng không
					// Đúng thì làm tiếp, sai thì báo lỗi
					if (isValid){
						// Check mật khẩu và nhập lại mật khẩu có trùng ko
						// Đúng thì đăng kí, sai thì báo lỗi
						if (password.equals(repassword)) {
							accountDAO.register(khachhangid, username, password);
							try {
							    Thread.sleep(5000);
							    model.addAttribute("message_success", "Đăng ký thành công, đang chuyển tới đăng nhập");
							} catch (InterruptedException e) {
							    
							}
							return "redirect:login";
						} else {
							String messagePW = "Vui lòng nhập lại đúng mật khẩu";
							model.addAttribute("messagePW", messagePW);
							return "register";
						}
					} else {
						String messageMK = "Vui lòng nhập đúng định dạng mật khẩu";
						model.addAttribute("messageMK", messageMK);
						return "register";
					}
				}
			}
		} else {
			String messageMKH = "Mã khách không tồn tại hoặc đã có tài khoản";
			model.addAttribute("messageMKH",messageMKH);
			return "register";
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String render_home() {
		return "redirect:../Tinhtiendien/login";
	}
	
	@RequestMapping(value = "/forgotpass", method = RequestMethod.GET)
	public String render_forgot() {
		return "forgotPass";
	}
	
	@Autowired
	QuanLyAccountDAO qlaccountDAO = new QuanLyAccountDAO();
	
	
	@RequestMapping(value = "/forgotpass", method = RequestMethod.POST)
	public String xu_li(@RequestParam("email") String email) {
		QuanLyAccount acc = new QuanLyAccount();
		acc = qlaccountDAO.getAccByEmail(email);
		System.out.println(acc.getUsername());
		System.out.println(acc.getPassword());
		Email.gui_matkhau(email, acc.getUsername(),acc.getPassword());
		
		return "forgotPass";
	}
}

