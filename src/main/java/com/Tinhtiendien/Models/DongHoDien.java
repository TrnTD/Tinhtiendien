package com.Tinhtiendien.Models;

import java.sql.Date;

public class DongHoDien {
	private String dongho_id;
	private String khachhang_id;
	private Date ngay_dangky;
	
	public String getDongho_id() {
		return dongho_id;
	}
	public void setDongho_id(String dongho_id) {
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
	
	
}
