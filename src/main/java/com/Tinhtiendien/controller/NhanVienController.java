package com.Tinhtiendien.controller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NhanVienController {
	
	@RequestMapping(value = "/nhan_vien/quan_ly_thong_tin_nguoi_dung", method = RequestMethod.GET)
	public String quanly_thongtinnguoidung() {
		
		return "employee/quan_ly_thong_tin_nguoi_dung";
	}
	
	
	@RequestMapping(value = "/nhan_vien/quan_ly_thong_tin_nguoi_dung", method = RequestMethod.POST)
	public String test_quanly_thongtinnguoidung() {
		
		return "login";
	}
}
