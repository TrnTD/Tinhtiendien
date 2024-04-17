package com.Tinhtiendien.Models;

public class LichGhiChiSo {
	private String nam;
	private String thang;
	private String ngay_thang_nam;
	
	
	public LichGhiChiSo(String nam, String thang, String ngay_thang_nam) {
		this.nam = nam;
		this.thang = thang;
		this.ngay_thang_nam = ngay_thang_nam;
	}


	public LichGhiChiSo() {
	}


	public String getNam() {
		return nam;
	}


	public void setNam(String nam) {
		this.nam = nam;
	}


	public String getThang() {
		return thang;
	}


	public void setThang(String thang) {
		this.thang = thang;
	}


	public String getNgay_thang_nam() {
		return ngay_thang_nam;
	}


	public void setNgay_thang_nam(String ngay_thang_nam) {
		this.ngay_thang_nam = ngay_thang_nam;
	}
}
