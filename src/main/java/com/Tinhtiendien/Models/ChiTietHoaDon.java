package com.Tinhtiendien.Models;

import java.sql.Date;

public class ChiTietHoaDon {
	private int chitiet_hoadon_id;
	private String khachhang_id;
	private int hoadon_id;
	private int chiso_cu;
	private int chiso_moi;
	private String ngay_thanhtoan;
	private String trangthai;
	
	private String ngay_tao;
	private int thang_tao;
	private int nam_tao;
	private int dien_tieu_thu;
	private int so_tien;
	private int tien_thue;
	private int tong_tien;
	
	private String loai_hoadon = "Tiền điện";
	
	private String ngay_batdau;
	private int thang_batdau;
	private int nam_batdau;

	
	public int getThang_batdau() {
		return thang_batdau;
	}

	public void setThang_batdau(int thang_batdau) {
		this.thang_batdau = thang_batdau;
	}

	public int getNam_batdau() {
		return nam_batdau;
	}

	public void setNam_batdau(int nam_batdau) {
		this.nam_batdau = nam_batdau;
	}

	public String getNgay_batdau() {
		return ngay_batdau;
	}

	public void setNgay_batdau(String ngay_batdau) {
		this.ngay_batdau = ngay_batdau;
	}

	public int getChitiet_hoadon_id() {
		return chitiet_hoadon_id;
	}

	public void setChitiet_hoadon_id(int chitiet_hoadon_id) {
		this.chitiet_hoadon_id = chitiet_hoadon_id;
	}

	public String getKhachhang_id() {
		return khachhang_id;
	}

	public void setKhachhang_id(String khachhang_id) {
		this.khachhang_id = khachhang_id;
	}

	public int getHoadon_id() {
		return hoadon_id;
	}

	public void setHoadon_id(int hoadon_id) {
		this.hoadon_id = hoadon_id;
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

	public String getNgay_tao() {
		return ngay_tao;
	}

	public void setNgay_tao(String ngay_tao) {
		this.ngay_tao = ngay_tao;
	}

	public int getThang_tao() {
		return thang_tao;
	}

	public void setThang_tao(int thang_tao) {
		this.thang_tao = thang_tao;
	}

	public int getNam_tao() {
		return nam_tao;
	}

	public void setNam_tao(int nam_tao) {
		this.nam_tao = nam_tao;
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

	public String getLoai_hoadon() {
		return loai_hoadon;
	}

	public void setLoai_hoadon(String loai_hoadon) {
		this.loai_hoadon = loai_hoadon;
	}
	
	

	
	
}
