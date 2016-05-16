package com.kemenkes.epu.report;

import java.math.BigDecimal;

public class InHonorNarasumber {
	private String nama;
	private String nip;
	private String pangkat;
	private String jabatan;
	private String perhitungan;
	private BigDecimal honor;
	private BigDecimal pph;

	private BigDecimal jumlah;
	private BigDecimal jumlahTerima;

	public InHonorNarasumber() {
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getPangkat() {
		return pangkat;
	}

	public void setPangkat(String pangkat) {
		this.pangkat = pangkat;
	}

	public String getJabatan() {
		return jabatan;
	}

	public void setJabatan(String jabatan) {
		this.jabatan = jabatan;
	}

	public BigDecimal getHonor() {
		return honor;
	}

	public void setHonor(BigDecimal honor) {
		this.honor = honor;
	}

	public BigDecimal getPph() {
		return pph;
	}

	public void setPph(BigDecimal pph) {
		this.pph = pph;
	}

	public String getNip() {
		return nip;
	}

	public void setNip(String nip) {
		this.nip = nip;
	}

	public BigDecimal getJumlah() {
		return jumlah;
	}

	public void setJumlah(BigDecimal jumlah) {
		this.jumlah = jumlah;
	}

	public BigDecimal getJumlahTerima() {
		return jumlahTerima;
	}

	public void setJumlahTerima(BigDecimal jumlahTerima) {
		this.jumlahTerima = jumlahTerima;
	}

	public String getPerhitungan() {
		return perhitungan;
	}

	public void setPerhitungan(String perhitungan) {
		this.perhitungan = perhitungan;
	}

}
