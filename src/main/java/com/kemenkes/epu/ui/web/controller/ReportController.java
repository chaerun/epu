package com.kemenkes.epu.ui.web.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kemenkes.epu.common.util.Constant;
import com.kemenkes.epu.common.util.Terbilang;
import com.kemenkes.epu.entity.Balance;
import com.kemenkes.epu.entity.BalanceType;
import com.kemenkes.epu.entity.Informant;
import com.kemenkes.epu.entity.Journey;
import com.kemenkes.epu.entity.JourneyType;
import com.kemenkes.epu.entity.Official;
import com.kemenkes.epu.entity.ParticipantJourney;
import com.kemenkes.epu.report.RincianBiaya;
import com.kemenkes.epu.service.BalanceService;
import com.kemenkes.epu.service.InformantService;
import com.kemenkes.epu.service.JourneyService;
import com.kemenkes.epu.service.OfficialService;
import com.kemenkes.epu.service.ParticipantJourneyService;

@Controller
@RequestMapping("/report")
public class ReportController {
	//
	@Autowired
	private JourneyService journeyService;
	//
	@Autowired
	private OfficialService officialService;

	@Autowired
	private ParticipantJourneyService participantJourneyService;

	@Autowired
	private InformantService informantService;

	@Autowired
	private BalanceService balanceService;

	@RequestMapping("/sa/up/{id}")
	public String saUp(@PathVariable String id, ModelMap map) {
		Journey journey = journeyService.findById(id);

		BigDecimal releaseAmount = journey.getTotalAmountAll();

		map.put("JUMLAH_UANG", releaseAmount);
		Terbilang terbilang = new Terbilang();

		map.put("NAMA", journey.getName());
		map.put("TERBILANG", terbilang.getTerbilang(releaseAmount) + " Rupiah");

		Integer year = DateTime.now().getYear();
		Official official = officialService.findById(year);
		if (official != null) {
			map.put("BENDAHARA", official.getTreasurer() == null ? "-" : official.getTreasurer().getName());
			map.put("BENDAHARA_NIP", official.getTreasurer() == null ? "-" : official.getTreasurer().getCode());

			map.put("PEJABAT", official.getOfficial() == null ? "-" : official.getOfficial().getName());
			map.put("PEJABAT_NIP", official.getOfficial() == null ? "-" : official.getOfficial().getCode());
		}
		return "receipt-up";
	}

	@RequestMapping("/return/{id}/")
	public String kembali(@PathVariable String id, ModelMap map) {
		Journey journey = journeyService.findById(id);

		map.put("NAME", journey.getName());
		map.put("START", journey.getStartDate());
		map.put("END", journey.getEndDate());
		map.put("LOCATION", journey.getLocation());
		map.put("START", journey.getStartDate());

		Balance search = new Balance();
		search.setAdditionalReferenceId(journey.getId());
		search.setAdditionalReferenceTable(Constant.REFF_TABLE_JOURNEY);
		search.setType(BalanceType.CR);
		List<Balance> balances = balanceService.search(search);

		BigDecimal receivedAmount = BigDecimal.ZERO;
		if (balances.size() > 0) {
			receivedAmount = balances.get(0).getAmount();
		}

		map.put("TOTAL1", receivedAmount);

		map.put("TOTAL2", receivedAmount.subtract(journey.getTotalAmountAll()));

		Terbilang terbilang = new Terbilang();
		map.put("TERBILANG1", terbilang.getTerbilang(receivedAmount) + " Rupiah");

		map.put("TERBILANG2", terbilang.getTerbilang(receivedAmount.subtract(journey.getTotalAmountAll())) + " Rupiah");

		Integer year = DateTime.now().getYear();
		Official official = officialService.findById(year);
		if (official != null) {
			map.put("BENDAHARA", official.getTreasurer() == null ? "-" : official.getTreasurer().getName());
			map.put("BENDAHARA_NIP", official.getTreasurer() == null ? "-" : official.getTreasurer().getCode());

			map.put("PEJABAT", official.getOfficial() == null ? "-" : official.getOfficial().getName());
			map.put("PEJABAT_NIP", official.getOfficial() == null ? "-" : official.getOfficial().getCode());
		}
		return "return-amount";
	}

	@RequestMapping("/normatif/{id}/")
	public String normatif(@PathVariable String id, ModelMap map) {

		Journey journey = journeyService.findById(id);

		System.out.println("-----------------------" + journey);
		map.put("journey", journey);
		Integer year = DateTime.now().getYear();

		Official official = officialService.findById(year);

		map.put("official", official);

		if (JourneyType.FULLDAY.equals(journey.getType()) || JourneyType.JOURNEY_IN.equals(journey.getType())) {
			return "shortNormatifView";
		} else {

			return "longNormatifView";
		}
	}

	@RequestMapping("/moderator/{id}/")
	public String moderator(@PathVariable String id, ModelMap map) {

		Journey journey = journeyService.findById(id);

		System.out.println("-----------------------" + journey);
		map.put("journey", journey);
		Integer year = DateTime.now().getYear();

		Official official = officialService.findById(year);

		map.put("official", official);
		return "shortHonorModeratorView";
	}

	@RequestMapping("/narasumber/{id}/")
	public String narasumber(@PathVariable String id, ModelMap map) {

		Journey journey = journeyService.findById(id);

		System.out.println("-----------------------" + journey);
		map.put("journey", journey);
		Integer year = DateTime.now().getYear();

		Official official = officialService.findById(year);

		map.put("official", official);
		return "shortHonorNarasumberView";
	}

	@RequestMapping("/actlist/{id}/")
	public String actlist(@PathVariable String id, ModelMap map) {

		Journey journey = journeyService.findById(id);

		System.out.println("-----------------------" + journey);
		map.put("journey", journey);
		Integer year = DateTime.now().getYear();

		Official official = officialService.findById(year);

		map.put("official", official);
		return "shortListActivityView";
	}

	@RequestMapping("/attendance/{id}/")
	public String attendance(@PathVariable String id, ModelMap map) {

		Journey journey = journeyService.findById(id);

		System.out.println("-----------------------" + journey);
		map.put("journey", journey);
		Integer year = DateTime.now().getYear();

		Official official = officialService.findById(year);

		map.put("official", official);
		return "attendanceView";
	}

	@RequestMapping("/detailjourney/participant/{id}/")
	public String detailjourney(@PathVariable String id, ModelMap map) {

		ParticipantJourney participant = participantJourneyService.findById(id);

		RincianBiaya pesawat = new RincianBiaya();
		pesawat.setKategori("TRANSPORT");
		pesawat.setPerincian("Pesawat (PP)");
		BigDecimal planeTotal = participant.getPlaneBackAmount().add(participant.getPlaneGoAmount()).add(participant.getPlaneBackTax()).add(participant.getPlaneGoTax());
		pesawat.setJumlah(planeTotal);

		RincianBiaya taksi = new RincianBiaya();
		taksi.setKategori("TRANSPORT");
		taksi.setPerincian("Taksi (PP)");
		BigDecimal taksiTotal = participant.getTaxiGoAmount().add(participant.getTaxiBackAmount());
		taksi.setJumlah(taksiTotal);

		RincianBiaya uangHarian = new RincianBiaya();
		uangHarian.setKategori("UANG HARIAN");
		uangHarian.setPerincian(String.format("%d hari x Rp. %s", participant.getDays(), Constant.DECIMAL_FORMAT.format(participant.getDailyAmount())));
		uangHarian.setJumlah(participant.getDailyAmount().multiply(new BigDecimal(participant.getDays())));

		// RincianBiaya inn = new RincianBiaya();
		// inn.setKategori("BIAYA PENGINAPAN");
		// inn.setPerincian(String.format("%d hari x Rp. %s",
		// participant.getJourney().getDays(),
		// Constant.DECIMAL_FORMAT.format(participant.getJourney().getInnAmount())));
		// inn.setJumlah(participant.getJourney().getInnAmount().multiply(new
		// BigDecimal(participant.getJourney().getDays())));

		List<RincianBiaya> list = new ArrayList<RincianBiaya>();

		list.add(pesawat);
		list.add(taksi);
		list.add(uangHarian);
		// list.add(inn);

		// BigDecimal total =
		// BigDecimal.ZERO.add(pesawat.getJumlah()).add(taksi.getJumlah()).add(uangHarian.getJumlah()).add(inn.getJumlah());
		BigDecimal total = BigDecimal.ZERO.add(pesawat.getJumlah()).add(taksi.getJumlah()).add(uangHarian.getJumlah());
		if (Boolean.TRUE.equals(participant.getModerator())) {

			RincianBiaya moderator = new RincianBiaya();
			moderator.setKategori("HONOR MODERATOR");
			moderator.setPerincian(String.format("%d hari x Rp. %s", participant.getDays(), Constant.DECIMAL_FORMAT.format(participant.getModeratorAmount())));
			moderator.setJumlah(participant.getModeratorAmount().multiply(new BigDecimal(participant.getDays())));
			list.add(moderator);

			RincianBiaya ppn = new RincianBiaya();
			ppn.setKategori("HONOR MODERATOR");
			ppn.setPerincian(String.format("%s x Rp. %s", String.valueOf(participant.getModeratorPpn() + " %"), Constant.DECIMAL_FORMAT.format(moderator.getJumlah())));

			BigDecimal ppnx = moderator.getJumlah().multiply(new BigDecimal((double) ((double) participant.getModeratorPpn() / 100)));
			ppnx = (ppnx.setScale(0, RoundingMode.FLOOR));
			ppn.setJumlah(ppnx);
			//
			list.add(ppn);
			//
			total = total.add(moderator.getJumlah().subtract(ppn.getJumlah()));
		}

		Integer year = DateTime.now().getYear();
		Official official = officialService.findById(year);
		Terbilang terbilang = new Terbilang();
		map.put("TERBILANG", terbilang.getTerbilang(total) + " Rupiah");

		map.put("TOTAL", total);
		map.put("dataSource", list);


		if (official != null) {
			map.put("BENDAHARA", official.getTreasurer() == null ? "-" : official.getTreasurer().getName());
			map.put("BENDAHARA_NIP", official.getTreasurer() == null ? "-" : official.getTreasurer().getCode());
			
			map.put("PEJABAT", official.getOfficial() == null ? "-" : official.getOfficial().getName());
			map.put("PEJABAT_NIP", official.getOfficial() == null ? "-" : official.getOfficial().getCode());

			map.put("NAMA",participant.isFlag() ? participant.getName() : participant.getParticipant().getName());
			map.put("NIP", participant.isFlag() ? "-" : participant.getParticipant().getCode());
		}
		map.put("DESCRIPTION", participant.getJourney().getName());
		map.put("START", participant.getJourney().getStartDate());
		map.put("END", participant.getJourney().getEndDate());
		map.put("LOCATION", participant.getJourney().getLocation());

		return "participantDetailJourney";
	}

	@RequestMapping("/participant/riil/{id}/")
	public String participantRiil(@PathVariable String id, ModelMap map) {

		ParticipantJourney participant = participantJourneyService.findById(id);

		List<RincianBiaya> list = new ArrayList<RincianBiaya>();

		Integer year = DateTime.now().getYear();
		Official official = officialService.findById(year);

		map.put("dataSource", list);

		RincianBiaya taksi = new RincianBiaya();
		taksi.setKategori("TRANSPORT");
		taksi.setPerincian("Taksi (PP)");
		BigDecimal taksiTotal = participant.getTaxiGoAmount().add(participant.getTaxiBackAmount());
		taksi.setJumlah(taksiTotal);

		list.add(taksi);

		map.put("TOTAL", taksiTotal);
		if (official != null) {
			map.put("BENDAHARA", official.getTreasurer() == null ? "-" : official.getTreasurer().getName());
			map.put("BENDAHARA_NIP", official.getTreasurer() == null ? "-" : official.getTreasurer().getCode());

			map.put("PEJABAT", official.getOfficial() == null ? "-" : official.getOfficial().getName());
			map.put("PEJABAT_NIP", official.getOfficial() == null ? "-" : official.getOfficial().getCode());
		}

		map.put("NAMA", participant.isFlag() ? participant.getName() : participant.getParticipant().getName());
		map.put("NIP", participant.isFlag() ? "" : participant.getParticipant().getCode());

		map.put("DESCRIPTION", participant.getJourney().getName());
		map.put("START", participant.getJourney().getStartDate());
		map.put("END", participant.getJourney().getEndDate());
		map.put("LOCATION", participant.getJourney().getLocation());

		return "riil";
	}

	@RequestMapping("/detailjourney/informant/{id}/")
	public String detailjourneyinformant(@PathVariable String id, ModelMap map) {

		Informant informant = informantService.findById(id);
//
		RincianBiaya pesawat = new RincianBiaya();
		pesawat.setKategori("TRANSPORT");
		pesawat.setPerincian("Pesawat (PP)");
		BigDecimal planeTotal = informant.getPlaneBackAmount().add(informant.getPlaneGoAmount()).add(informant.getPlaneBackTax()).add(informant.getPlaneGoTax());
		pesawat.setJumlah(planeTotal);

		RincianBiaya taksi = new RincianBiaya();
		taksi.setKategori("TRANSPORT");
		taksi.setPerincian("Taksi (PP)");
		BigDecimal taksiTotal = informant.getTaxiGoAmount().add(informant.getTaxiBackAmount());
		taksi.setJumlah(taksiTotal);

		List<RincianBiaya> list = new ArrayList<RincianBiaya>();

		list.add(pesawat);
		list.add(taksi);
		// list.add(inn);

		BigDecimal total = BigDecimal.ZERO.add(pesawat.getJumlah()).add(taksi.getJumlah());
		
//		BigDecimal total = BigDecimal.ZERO;
		

		RincianBiaya honor = new RincianBiaya();
		honor.setKategori("HONOR NARASUMBER");
		honor.setPerincian(String.format("%d hari x Rp. %s x %s jam", informant.getDays(), Constant.DECIMAL_FORMAT.format(informant.getAmount()), informant.getHours()));
		honor.setJumlah(informant.getAmount().multiply(new BigDecimal(informant.getDays())).multiply(new BigDecimal(informant.getHours())));
		list.add(honor);
		//
		RincianBiaya ppn = new RincianBiaya();
		ppn.setKategori("HONOR NARASUMBER");
		ppn.setPerincian(String.format("%s x Rp. %s", String.valueOf(informant.getPpn() + " %"), Constant.DECIMAL_FORMAT.format(honor.getJumlah())));
		//
		BigDecimal ppnx = honor.getJumlah().multiply(new BigDecimal((double) ((double) informant.getPpn() / 100)));
		ppnx = (ppnx.setScale(0, RoundingMode.FLOOR));
		ppn.setJumlah(ppnx);

		list.add(ppn);
		//
		total = total.add(honor.getJumlah().subtract(ppn.getJumlah()));

		Integer year = DateTime.now().getYear();
		Official official = officialService.findById(year);
		Terbilang terbilang = new Terbilang();
		map.put("TERBILANG", terbilang.getTerbilang(total) + " Rupiah");

		map.put("TOTAL", total);
		map.put("dataSource", list);

		if (official != null) {
			map.put("BENDAHARA", official.getTreasurer() == null ? "-" : official.getTreasurer().getName());
			map.put("BENDAHARA_NIP", official.getTreasurer() == null ? "-" : official.getTreasurer().getCode());

			map.put("PEJABAT", official.getOfficial() == null ? "-" : official.getOfficial().getName());
			map.put("PEJABAT_NIP", official.getOfficial() == null ? "-" : official.getOfficial().getCode());
		}

		map.put("NAMA", informant.getName());
		map.put("NIP", informant.getNip());

		map.put("DESCRIPTION", informant.getJourney().getName());
		map.put("START", informant.getJourney().getStartDate());
		map.put("END", informant.getJourney().getEndDate());
		map.put("LOCATION", informant.getJourney().getLocation());

		return "participantDetailJourney";
	}

	@RequestMapping("/informant/riil/{id}/")
	public String informantRiil(@PathVariable String id, ModelMap map) {

		Informant informant = informantService.findById(id);

		System.out.println(informant + "-----------------------------------------");

		List<RincianBiaya> list = new ArrayList<RincianBiaya>();

		RincianBiaya taksi = new RincianBiaya();
		taksi.setKategori("TRANSPORT");
		taksi.setPerincian("Taksi (PP)");
		BigDecimal taksiTotal = informant.getTaxiGoAmount().add(informant.getTaxiBackAmount());
		taksi.setJumlah(taksiTotal);

		list.add(taksi);

		map.put("TOTAL", taksiTotal);
		map.put("dataSource", list);
		Integer year = DateTime.now().getYear();
		Official official = officialService.findById(year);
		if (official != null) {
			map.put("BENDAHARA", official.getTreasurer() == null ? "-" : official.getTreasurer().getName());
			map.put("BENDAHARA_NIP", official.getTreasurer() == null ? "-" : official.getTreasurer().getCode());

			map.put("PEJABAT", official.getOfficial() == null ? "-" : official.getOfficial().getName());
			map.put("PEJABAT_NIP", official.getOfficial() == null ? "-" : official.getOfficial().getCode());
		}
		map.put("NAMA", informant.getName());
		map.put("NIP", informant.getNip());

		map.put("DESCRIPTION", informant.getJourney().getName());
		map.put("START", informant.getJourney().getStartDate());
		map.put("END", informant.getJourney().getEndDate());
		map.put("LOCATION", informant.getJourney().getLocation());

		return "riil";
	}

	//
}
