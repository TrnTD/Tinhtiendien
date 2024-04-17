package com.Tinhtiendien.Models;

import java.sql.Date;

public class HoaDon {
	private int hoadon_id;
	private String khachhang_id;
	private Date ngay_tao;
	private int thue;
	
	public int getHoadon_id() {
		return hoadon_id;
	}
	public void setHoadon_id(int hoadon_id) {
		this.hoadon_id = hoadon_id;
	}
	public String getKhachhang_id() {
		return khachhang_id;
	}
	public void setKhachhang_id(String khachhang_id) {
		this.khachhang_id = khachhang_id;
	}
	public Date getNgay_tao() {
		return ngay_tao;
	}
	public void setNgay_tao(Date ngay_tao) {
		this.ngay_tao = ngay_tao;
	}
	public int getThue() {
		return thue;
	}
	public void setThue(int thue) {
		this.thue = thue;
	}
}

