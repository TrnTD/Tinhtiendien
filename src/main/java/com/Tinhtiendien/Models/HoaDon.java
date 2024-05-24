package com.Tinhtiendien.Models;

import java.sql.Date;

public class HoaDon {
	private int hoadon_id;
	private String khachhang_id;
	private int dongho_id;
	private Date ngay_tao;
	private int month_bill;
	private String ngay_batdau;
	private String ngay_ketthuc;
	private int chiso_cu;
	private int chiso_moi;
	private int dien_tieu_thu;
	private int so_tien;
	private int tien_thue;
	private int tong_tien;
	private int year_bill;
	private int thue;
	private String ngay_thanhtoan;
	private String trangthai;
	
	private String loai_hoadon = "Tiền điện";
	
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
	public int getDongho_id() {
		return dongho_id;
	}
	public void setDongho_id(int dongho_id) {
		this.dongho_id = dongho_id;
	}
	public Date getNgay_tao() {
		return ngay_tao;
	}
	public void setNgay_tao(Date ngay_tao) {
		this.ngay_tao = ngay_tao;
	}
	public int getMonth_bill() {
		return month_bill;
	}
	public void setMonth_bill(int month_bill) {
		this.month_bill = month_bill;
	}
	public String getNgay_batdau() {
		return ngay_batdau;
	}
	public void setNgay_batdau(String ngay_batdau) {
		this.ngay_batdau = ngay_batdau;
	}
	public String getNgay_ketthuc() {
		return ngay_ketthuc;
	}
	public void setNgay_ketthuc(String ngay_ketthuc) {
		this.ngay_ketthuc = ngay_ketthuc;
	}
	public int getChiso_cu() {
		return chiso_cu;
	}
	public void setChiso_cu(int chiso_cu) {
		this.chiso_cu = chiso_cu;
	}
	public int getChiso_moi() {
		return chiso_moi;
	}
	public void setChiso_moi(int chiso_moi) {
		this.chiso_moi = chiso_moi;
	}
	public int getDien_tieu_thu() {
		return dien_tieu_thu;
	}
	public void setDien_tieu_thu(int dien_tieu_thu) {
		this.dien_tieu_thu = dien_tieu_thu;
	}
	public int getSo_tien() {
		return so_tien;
	}
	public void setSo_tien(int so_tien) {
		this.so_tien = so_tien;
	}
	public int getTien_thue() {
		return tien_thue;
	}
	public void setTien_thue(int tien_thue) {
		this.tien_thue = tien_thue;
	}
	public int getTong_tien() {
		return tong_tien;
	}
	public void setTong_tien(int tong_tien) {
		this.tong_tien = tong_tien;
	}
	public int getYear_bill() {
		return year_bill;
	}
	public void setYear_bill(int year_bill) {
		this.year_bill = year_bill;
	}
	public int getThue() {
		return thue;
	}
	public void setThue(int thue) {
		this.thue = thue;
	}
	public String getNgay_thanhtoan() {
		return ngay_thanhtoan;
	}
	public void setNgay_thanhtoan(String ngay_thanhtoan) {
		this.ngay_thanhtoan = ngay_thanhtoan;
	}
	public String getTrangthai() {
		return trangthai;
	}
	public void setTrangthai(String trangthai) {
		this.trangthai = trangthai;
	}
	public String getLoai_hoadon() {
		return loai_hoadon;
	}
	public void setLoai_hoadon(String loai_hoadon) {
		this.loai_hoadon = loai_hoadon;
	}

}

