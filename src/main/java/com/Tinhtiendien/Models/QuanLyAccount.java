package com.Tinhtiendien.Models;

public class QuanLyAccount {
	private String khachhang_id;
	private String username;
	private String password;
	
	public QuanLyAccount() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getKhachhang_id() {
		return khachhang_id;
	}

	public void setKhachhang_id(String khachhang_id) {
		this.khachhang_id = khachhang_id;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
