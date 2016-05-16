package com.kemenkes.epu.report;

import java.math.BigDecimal;

public class RincianBiaya {

	private String kategori;
	private String perincian;
	private String keterangan;

	private BigDecimal jumlah;

	public RincianBiaya() {

	}

	public String getKategori() {
		return kategori;
	}

	public void setKategori(String kategori) {
		this.kategori = kategori;
	}

	public String getPerincian() {
		return perincian;
	}

	public void setPerincian(String perincian) {
		this.perincian = perincian;
	}

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public BigDecimal getJumlah() {
		return jumlah;
	}

	public void setJumlah(BigDecimal jumlah) {
		this.jumlah = jumlah;
	}

}
