package com.Tinhtiendien.Models;

import java.sql.Date;

public class DongHoDien {
	private int dongho_id;
	private String khachhang_id;
	private Date ngay_dangky;
	private int thang_dangky;
	private int nam_dangky;
	
	public int getDongho_id() {
		return dongho_id;
	}
	public void setDongho_id(int dongho_id) {
		this.dongho_id = dongho_id;
	}
	public String getKhachhang_id() {
		return khachhang_id;
	}
	public void setKhachhang_id(String khachhang_id) {
		this.khachhang_id = khachhang_id;
	}
	public Date getNgay_dangky() {
		return ngay_dangky;
	}
	public void setNgay_dangky(Date ngay_dangky) {
		this.ngay_dangky = ngay_dangky;
	}
	public int getThang_dangky() {
		return thang_dangky;
	}
	public void setThang_dangky(int thang_dangky) {
		this.thang_dangky = thang_dangky;
	}
	public int getNam_dangky() {
		return nam_dangky;
	}
	public void setNam_dangky(int nam_dangky) {
		this.nam_dangky = nam_dangky;
	}
	
	
}
