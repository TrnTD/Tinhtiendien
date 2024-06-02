package com.Tinhtiendien.Models;

import java.sql.Date;

public class MeasurementHistory {
	private int lichsu_do_id;
	private String khachhang_id;
	private String dongho_id;
	private Date ngay_do;
	private int chiso;
	
	public int getLichsu_do_id() {
		return lichsu_do_id;
	}
	public void setLichsu_do_id(int lichsu_do_id) {
		this.lichsu_do_id = lichsu_do_id;
	}
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
	public Date getNgay_do() {
		return ngay_do;
	}
	public void setNgay_do(Date ngay_do) {
		this.ngay_do = ngay_do;
	}
	public int getChiso() {
		return chiso;
	}
	public void setChiso(int chiso) {
		this.chiso = chiso;
	}
}
