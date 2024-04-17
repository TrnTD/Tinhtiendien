package com.Tinhtiendien.Models;

import java.sql.Date;

public class MeasurementHistory {
	private int lichsu_do_id;
	private int dongho_id;
	private String khachhang_id;
	private Date ngay_do;
	private float chiso;
	
	public int getLichsu_do_id() {
		return lichsu_do_id;
	}
	public void setLichsu_do_id(int lichsu_do_id) {
		this.lichsu_do_id = lichsu_do_id;
	}
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
	public Date getNgay_do() {
		return ngay_do;
	}
	public void setNgay_do(Date ngay_do) {
		this.ngay_do = ngay_do;
	}
	public float getChiso() {
		return chiso;
	}
	public void setChiso(float chiso) {
		this.chiso = chiso;
	}
}
