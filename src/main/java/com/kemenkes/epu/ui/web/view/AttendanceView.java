package com.kemenkes.epu.ui.web.view;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.joda.time.DateTime;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.kemenkes.epu.common.util.Constant;
import com.kemenkes.epu.entity.Informant;
import com.kemenkes.epu.entity.Journey;
import com.kemenkes.epu.entity.Official;
import com.kemenkes.epu.entity.ParticipantJourney;

public class AttendanceView extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HSSFSheet sheet = workbook.getSheetAt(0);

		HSSFSheet template = workbook.getSheetAt(1);

		DateFormat dateFormat = new SimpleDateFormat("dd MMM");

		Journey journey = (Journey) model.get("journey");

		HSSFCellStyle headerStyle = getCell(sheet, 6, 1).getCellStyle();

		System.out.println(journey + "====================================");

		getCell(sheet, 2, 1).setCellValue(journey.getName());
		getCell(sheet, 3, 1).setCellValue(journey.getLocation());
		getCell(sheet, 4, 1).setCellValue(String.format("%s s/d %s", Constant.LONG_SIMPLE_DATE_FORMAT.format(journey.getStartDate()), Constant.LONG_SIMPLE_DATE_FORMAT.format(journey.getEndDate())));

		int start = 6;

		DateTime dtStart = new DateTime(journey.getStartDate());
		DateTime dtEnd = new DateTime(journey.getEndDate());

		while (dtStart.isBefore(dtEnd)) {
			getCell(sheet, 7, start).setCellStyle(headerStyle);
			getCell(sheet, 7, start).setCellValue(dateFormat.format(dtStart.toDate()));
			dtStart = dtStart.plusDays(1);
			start++;
		}
		getCell(sheet, 7, start).setCellStyle(headerStyle);
		getCell(sheet, 7, start).setCellValue(dateFormat.format(dtStart.toDate()));

		sheet.addMergedRegion(new CellRangeAddress(6, 6, 6, start));
		getCell(sheet, 6, 6).setCellStyle(headerStyle);
		getCell(sheet, 6, 6).setCellValue("Tanda Tangan");

		int i = 8;
		Collection<ParticipantJourney> participants = journey.getParticipants();

		int no = 1;
		for (ParticipantJourney participant : participants) {

			getCell(sheet, i, 1).setCellValue(no++);

			if (participant.isFlag()) {

				getCell(sheet, i, 2).setCellValue(participant.getName());
				getCell(sheet, i, 3).setCellValue("-");
			} else {

				getCell(sheet, i, 2).setCellValue(participant.getParticipant().getName());
				getCell(sheet, i, 3).setCellValue(participant.getParticipant().getCode());
			}

			if (participant.getParticipant() != null && participant.getParticipant().getGrade() != null) {
				getCell(sheet, i, 4).setCellValue(participant.getParticipant().getGrade().getName());
			} else {
				getCell(sheet, i, 4).setCellValue("-");

			}

			if (participant.getParticipant() != null && participant.getParticipant().getPosition() != null) {
				getCell(sheet, i, 5).setCellValue(participant.getParticipant().getPosition().getName());
			} else {
				getCell(sheet, i, 5).setCellValue("-");

			}

			i++;
		}

		Collection<Informant> informants = journey.getInformants();

		boolean flag = true;
		for (Informant informant : informants) {

			if (flag) {
				sheet.addMergedRegion(new CellRangeAddress(i, i, 1, start));
				getCell(sheet, i, 1).setCellStyle(headerStyle);
				getCell(sheet, i, 1).setCellValue("Narasumber");
				i++;
				flag = false;

			}

			getCell(sheet, i, 1).setCellValue(no++);
			getCell(sheet, i, 2).setCellValue(informant.getName());

			getCell(sheet, i, 3).setCellValue(informant.getNip());

			getCell(sheet, i, 4).setCellValue(informant.getGrade());

			getCell(sheet, i, 5).setCellValue(informant.getPosition());

			i++;
		}

		i += 2;

		if (model.get("official") != null && model.get("official") instanceof Official) {
			Official official = (Official) model.get("official");

			getCell(sheet, i, 1).setCellValue("Bendahara Pengeluaran");
			getCell(sheet, i + 4, 1).setCellValue("NIP." + official.getTreasurer() == null ? "-" : official.getTreasurer().getCode());
			getCell(sheet, i + 5, 1).setCellValue(official.getTreasurer() == null ? "-" : official.getTreasurer().getName());

			getCell(sheet, i, 9).setCellValue("A. N. Kuasa Pengguna Anggaran");
			getCell(sheet, i + 1, 9).setCellValue("Pejabat Pembuat Komitment");
			getCell(sheet, i + 4, 9).setCellValue("NIP." + official.getOfficial() == null ? "-" : official.getOfficial().getCode());
			getCell(sheet, i + 5, 9).setCellValue(official.getOfficial() == null ? "-" : official.getOfficial().getName());
		}

	}
}
