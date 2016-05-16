package com.kemenkes.epu.common.util;

import java.math.BigDecimal;

public class Terbilang {

	private final String PEMBAGI_SEPULUH = "10";
	private final String PEMBAGI_SERATUS = "100";
	private final String PEMBAGI_SERIBU = "1000";
	private final String PEMBAGI_SEJUTA = "1000000";
	private final String PEMBAGI_SEMILYAR = "1000000000";
	private final String PEMBAGI_SETRILYUN = "1000000000000";

	public String getTerbilang(BigDecimal dataUang) {
		final String CONST_SPASI = " ";
		final String CONST_NOL = "0";
		final String CONST_11 = "11";
		final String CONST_19 = "19";
		final String CONST_99 = "99";
		final String CONST_199 = "199";
		final String CONST_999 = "999";
		final String CONST_1999 = "1999";
		final String CONST_999_RIBU = "999999";
		final String CONST_999_JUTA = "999999999";
		final String CONST_999_MILYAR = "999999999999";
		final String CONST_999_TRILYUN = "999999999999999";
		StringBuilder result = new StringBuilder();

		// cek apakah data minus
		if (dataUang.compareTo(BigDecimal.ZERO) < 0) {
			dataUang = dataUang.multiply(BigDecimal.ONE.negate());
			result.append("Minus");

		}

		// data dianggap tidak minus
		if (cekData(dataUang, CONST_NOL, CONST_11)) {
			result.append(CONST_SPASI).append(getSatuan(dataUang.intValue()));
		} else if (cekData(dataUang, CONST_11, CONST_19)) {
			result.append(getTerbilang(mod(dataUang, PEMBAGI_SEPULUH))).append(CONST_SPASI).append("Belas");
		} else if (cekData(dataUang, CONST_19, CONST_99)) {
			result.append(getTerbilang(div(dataUang, PEMBAGI_SEPULUH))).append(CONST_SPASI).append("Puluh").append(getTerbilang(mod(dataUang, PEMBAGI_SEPULUH)));
		} else if (cekData(dataUang, CONST_99, CONST_199)) {
			result.append(CONST_SPASI).append("Seratus").append(getTerbilang(dataUang.subtract(new BigDecimal(PEMBAGI_SERATUS))));
		} else if (cekData(dataUang, CONST_199, CONST_999)) {
			result.append(getTerbilang(div(dataUang, PEMBAGI_SERATUS))).append(CONST_SPASI).append("Ratus").append(getTerbilang(mod(dataUang, PEMBAGI_SERATUS)));
		} else if (cekData(dataUang, CONST_999, CONST_1999)) {
			result.append(CONST_SPASI).append("Seribu").append(getTerbilang(dataUang.subtract(new BigDecimal(PEMBAGI_SERIBU))));
		} else if (cekData(dataUang, CONST_1999, CONST_999_RIBU)) {
			result.append(getTerbilang(div(dataUang, PEMBAGI_SERIBU))).append(CONST_SPASI).append("Ribu").append(getTerbilang(mod(dataUang, PEMBAGI_SERIBU)));
		} else if (cekData(dataUang, CONST_999_RIBU, CONST_999_JUTA)) {
			result.append(getTerbilang(div(dataUang, PEMBAGI_SEJUTA))).append(CONST_SPASI).append("Juta").append(getTerbilang(mod(dataUang, PEMBAGI_SEJUTA)));
		} else if (cekData(dataUang, CONST_999_JUTA, CONST_999_MILYAR)) {
			result.append(getTerbilang(div(dataUang, PEMBAGI_SEMILYAR))).append(CONST_SPASI).append("Milyar").append(getTerbilang(mod(dataUang, PEMBAGI_SEMILYAR)));
		} else if (cekData(dataUang, CONST_999_MILYAR, CONST_999_TRILYUN)) {
			result.append(getTerbilang(div(dataUang, PEMBAGI_SETRILYUN))).append(CONST_SPASI).append("Trilyun").append(getTerbilang(mod(dataUang, PEMBAGI_SETRILYUN)));
		}

		return result.toString();
	}

	// ambil terbilang untuk satuan
	private String getSatuan(int data) {
		switch (data) {
		case 1:
			return "Satu";
		case 2:
			return "Dua";
		case 3:
			return "Tiga";
		case 4:
			return "Empat";
		case 5:
			return "Lima";
		case 6:
			return "Enam";
		case 7:
			return "Tujuh";
		case 8:
			return "Delapan";
		case 9:
			return "Sembilan";
		case 10:
			return "Sepuluh";
		case 11:
			return "Sebelas";
		default:
			return " ";
		}
	}

	// cek apakah data memenuhi syarat
	private boolean cekData(BigDecimal dataPembanding, String strBatasBawah, String strBatasAtas) {
		BigDecimal batasBawah = new BigDecimal(strBatasBawah);
		BigDecimal batasAtas = new BigDecimal(strBatasAtas);
		if (dataPembanding.compareTo(batasBawah) > 0) {
			if (dataPembanding.compareTo(batasAtas) <= 0) {
				return true;
			}
		}
		return false;
	}

	// mengambil sisa hasil bagi
	private BigDecimal mod(BigDecimal data, String strPembagi) {
		BigDecimal pembagi = new BigDecimal(strPembagi);
		return data.remainder(pembagi);
	}

	// mengambil hasil bagi dengan membuang angka dibelakang koma
	private BigDecimal div(BigDecimal data, String strPembagi) {
		BigDecimal pembagi = new BigDecimal(strPembagi);
		return data.divide(pembagi, 1);
	}

}
