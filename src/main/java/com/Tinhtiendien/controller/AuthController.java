package com.Tinhtiendien.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.Random;

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
import KtraDuLieu.*;

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
		} else if (accountDAO.checkExistAccount(username, MaHoa.getMD5Hash(password))) {
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
				System.out.print("Dang nhap vao nhan vien thanh cong");
				
				InfoNhanVien infonv = infoDAO.getAllInfoNhanVien(username);
				
				redirectAttribute.addFlashAttribute("hoten", infonv.getHovaten());
				redirectAttribute.addFlashAttribute("makh", infonv.getNhanvien_id());
				redirectAttribute.addFlashAttribute("email", infonv.getEmail());
				redirectAttribute.addFlashAttribute("sdt", infonv.getSdt());

				session.setAttribute("info_nhanvien", infonv);

				
				return "redirect:nhan_vien/";
			} else if (accountDAO.getRole(username) == 1) {
				System.out.print("Dang nhap vao quan ly thanh cong");
				
				InfoNhanVien infoql = infoDAO.getAllInfoNhanVien(username);
				
				redirectAttribute.addFlashAttribute("hoten", infoql.getHovaten());
				redirectAttribute.addFlashAttribute("makh", infoql.getNhanvien_id());
				redirectAttribute.addFlashAttribute("email", infoql.getEmail());
				redirectAttribute.addFlashAttribute("sdt", infoql.getSdt());

				session.setAttribute("info_quanly", infoql);
				return "redirect:quan_ly/";
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
					if (KtraDuLieu.ktraMatKhau(password)){
						// Check mật khẩu và nhập lại mật khẩu có trùng ko
						// Đúng thì đăng kí, sai thì báo lỗi
						if (password.equals(repassword)) {
							accountDAO.register(khachhangid, username, MaHoa.getMD5Hash(password));
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
	
	
	public String randomPass() {
		int length = 10;
		
		String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
		String digits = "0123456789";
		String specialCharacters = "#?.!@$%^&*-";
		
		Random random = new Random();
		
		List<Character> randomString = new ArrayList<>();
	    randomString.add(upperCaseLetters.charAt(random.nextInt(upperCaseLetters.length())));
	    randomString.add(lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length())));
	    randomString.add(digits.charAt(random.nextInt(digits.length())));
	    randomString.add(specialCharacters.charAt(random.nextInt(specialCharacters.length())));
	    
	    String allCharacters = upperCaseLetters + lowerCaseLetters + digits + specialCharacters;
	    for (int i = 4; i < length; i++) {
	        randomString.add(allCharacters.charAt(random.nextInt(allCharacters.length())));
	    }

	      // Trộn ngẫu nhiên các ký tự để đảm bảo sự phân bố đều
	    Collections.shuffle(randomString);

	      // Chuyển đổi List<Character> thành chuỗi
	    StringBuilder result = new StringBuilder();
	    for (char c : randomString) {
	        result.append(c);
	    }
	    
	    return result.toString();
		
	}
	
	@RequestMapping(value = "/forgotpass", method = RequestMethod.POST)
	public String xu_li(@RequestParam("email") String email, Model model) {
		QuanLyAccount acc = new QuanLyAccount(); 
		
		if (KtraDuLieu.ktraGmail(email)) {
			acc = qlaccountDAO.getAccByEmail(email);
			
			if (acc == null) {
				model.addAttribute("tbloi", "Email không tồn tại hoặc chưa được đăng ký");
				System.out.println("Email không tồn tại hoặc chưa được đăng ký");
			} else {
				String newPass =  randomPass();
				if (qlaccountDAO.changePass(acc.getUsername(), MaHoa.getMD5Hash(newPass))) {
					System.out.println(acc.getUsername());
					
					Email.gui_matkhau(email, acc.getUsername(), newPass);
					model.addAttribute("tbthanhcong", "Tài khoản và mật khẩu đã gửi về email, vui lòng kiểm tra!!");
					
				}
				
			}
			
		} else {
			model.addAttribute("tbloi", "Email không đúng định dạng");
		}
		
		
		return "forgotPass";
	}
}

