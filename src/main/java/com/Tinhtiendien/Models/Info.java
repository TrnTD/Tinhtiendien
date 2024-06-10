package com.Tinhtiendien.Models;

import java.sql.Date;

public class Info {
	private String khachhang_id;
	private String username;
	private String hovaten;
	private String gioitinh;
	private Date ngaythangnam_sinh;
	private String email;
	private String sdt;
	private String cccd;
	private String diachi;
	
	public Info() {
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

	public String getHovaten() {
		return hovaten;
	}

	public void setHovaten(String hovaten) {
		this.hovaten = hovaten;
	}

	public String getGioitinh() {
		return gioitinh;
	}

	public void setGioitinh(String gioitinh) {
		this.gioitinh = gioitinh;
	}

	public Date getNgaythangnam_sinh() {
		return ngaythangnam_sinh;
	}

	public void setNgaythangnam_sinh(Date ngaythangnam_sinh) {
		this.ngaythangnam_sinh = ngaythangnam_sinh;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getCccd() {
		return cccd;
	}

	public void setCccd(String cccd) {
		this.cccd = cccd;
	}

	public String getDiachi() {
		return diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}
	
	
	
	
}
