package com.Tinhtiendien.Models;

import java.sql.Date;
public class YeuCau {
	private int yeucau_id;
	private String khachhang_id;
	private String tua_de;
	private String noi_dung;
	private String noi_dung_rut_gon;
	private Date ngay_gui;
	private String gio_gui;
	private int trang_thai_id;
	private String trang_thai;
	public YeuCau() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getGio_gui() {
		return gio_gui;
	}
	public void setGio_gui(String gio_gui) {
		this.gio_gui = gio_gui;
	}
	public String getNoi_dung_rut_gon() {
		return noi_dung_rut_gon;
	}
	public void setNoi_dung_rut_gon(String noi_dung_rut_gon) {
		this.noi_dung_rut_gon = noi_dung_rut_gon;
	}
	public String getTrang_thai() {
		return trang_thai;
	}

	public void setTrang_thai(String trang_thai) {
		this.trang_thai = trang_thai;
	}

	public int getYeucau_id() {
		return yeucau_id;
	}
	public void setYeucau_id(int yeucau_id) {
		this.yeucau_id = yeucau_id;
	}
	public String getKhachhang_id() {
		return khachhang_id;
	}
	public void setKhachhang_id(String khachhang_id) {
		this.khachhang_id = khachhang_id;
	}
	public String getTua_de() {
		return tua_de;
	}
	public void setTua_de(String tua_de) {
		this.tua_de = tua_de;
	}
	public String getNoi_dung() {
		return noi_dung;
	}
	public void setNoi_dung(String noi_dung) {
		this.noi_dung = noi_dung;
	}
	public Date getNgay_gui() {
		return ngay_gui;
	}
	public void setNgay_gui(Date ngay_gui) {
		this.ngay_gui = ngay_gui;
	}
	public int getTrang_thai_id() {
		return trang_thai_id;
	}
	public void setTrang_thai_id(int trang_thai_id) {
		this.trang_thai_id = trang_thai_id;
	}
}
