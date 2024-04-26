package com.Tinhtiendien.Models;

import java.sql.Date;

public class InfoNhanVien {
	private String nhanvien_id;
	private String username;
	private String hovaten;
	private String email;
	private String sdt;
	
	
	public String getNhanvien_id() {
		return nhanvien_id;
	}
	public void setNhanvien_id(String nhanvien_id) {
		this.nhanvien_id = nhanvien_id;
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

	
}
