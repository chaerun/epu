package com.kemenkes.epu.ui.web.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.displaytag.pagination.PaginatedList;
import org.displaytag.pagination.PaginatedListImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kemenkes.epu.common.util.Constant;
import com.kemenkes.epu.common.util.MessageType;
import com.kemenkes.epu.common.util.SpringSecurityUtil;
import com.kemenkes.epu.entity.Balance;
import com.kemenkes.epu.entity.BalanceType;
import com.kemenkes.epu.entity.Journey;
import com.kemenkes.epu.entity.JourneyStatus;
import com.kemenkes.epu.entity.JourneyType;
import com.kemenkes.epu.entity.view.ViewAccountBalance;
import com.kemenkes.epu.service.AccountService;
import com.kemenkes.epu.service.ActivityService;
import com.kemenkes.epu.service.BalanceService;
import com.kemenkes.epu.service.JourneyService;

@Controller
@RequestMapping("/sa")
public class SuperAdminController extends BaseController {

	@Autowired
	private JourneyService journeyService;

	@Autowired
	private ActivityService activityService;

	@Autowired
	private BalanceService balanceService;

	@Autowired
	private AccountService accountService;

	@RequestMapping("/journey")
	public String activityList(ModelMap map, HttpServletRequest request) {

		Journey journey = new Journey();
		journey.setStatus(JourneyStatus.PENDING);

		PaginatedListImpl paginatedList = new PaginatedListImpl(request);
		PaginatedList page = journeyService.search(journey, paginatedList);
		map.put("page", page);

		return "sa/journey/list";
	}
	
	
	@RequestMapping("/receiptup")
	public String receiptup(ModelMap map, HttpServletRequest request) {

		Journey journey = new Journey();
		journey.setStatus(JourneyStatus.IN_PROGRESS);

		PaginatedListImpl paginatedList = new PaginatedListImpl(request);
		PaginatedList page = journeyService.search(journey, paginatedList);
		map.put("page", page);

		
		return "sa/up/list";
	}
//	
	
	@RequestMapping("/pendingcomplete")
	public String pendingcomplete(ModelMap map, HttpServletRequest request) {

		Journey journey = new Journey();
		journey.setStatus(JourneyStatus.PENDING_COMPLETE);

		PaginatedListImpl paginatedList = new PaginatedListImpl(request);
		PaginatedList page = journeyService.search(journey, paginatedList);
		map.put("page", page);

		return "sa/list";
	}
	
	@RequestMapping("/pendingcomplete/detail/{id}/")
	public String pendingcompletedetail(@PathVariable String id, ModelMap map, HttpServletRequest request) {

		Journey journey = journeyService.findById(id);
		map.put("journey", journey);
		map.put("activity", journey.getActivity());
		map.put("view", activityService.findViewByCode(journey.getActivity().getCode()));
		
		Balance search = new Balance();
		search.setAdditionalReferenceId(journey.getId());
		search.setAdditionalReferenceTable(Constant.REFF_TABLE_JOURNEY);
		search.setType(BalanceType.CR);
		List<Balance> balances = balanceService.search(search);

		if (balances.size() > 0) {
			map.put("received", balances.get(0).getAmount());
		}
		
		return "sa/detail";
	}

	@RequestMapping("/journey/detail/{id}/")
	public String detail(@PathVariable String id, ModelMap map, HttpServletRequest request) {

		Journey journey = journeyService.findById(id);
		map.put("journey", journey);
		map.put("activity", journey.getActivity());
		map.put("view", activityService.findViewByCode(journey.getActivity().getCode()));
		return "sa/journey/detail";
	}
	
	@RequestMapping("/pendingapprove/{id}/")
	public String pendingapprove(@PathVariable String id, ModelMap map, RedirectAttributes attributes) {

		Journey journey = journeyService.findById(id);

		Balance search = new Balance();
		search.setAdditionalReferenceId(journey.getId());
		search.setAdditionalReferenceTable(Constant.REFF_TABLE_JOURNEY);
		search.setType(BalanceType.CR);
		List<Balance> balances = balanceService.search(search);

		BigDecimal returnAmount=BigDecimal.ZERO;
		if (balances.size() > 0) {
			returnAmount=balances.get(0).getAmount();
		}
		
		if (JourneyStatus.PENDING_COMPLETE.equals(journey.getStatus())) {
			journey.setStatus(JourneyStatus.COMPLETED);
			journey.setUpdatedBy(SpringSecurityUtil.getUsername());
			journey.setUpdatedDate(new Date());

			journeyService.approveComplete(journey,returnAmount.subtract(journey.getTotalAmountAll()));
		}
		addMessage(attributes, "Perjalanan telah selesai.", MessageType.SUCCESS);
		return "redirect:/sa/pendingcomplete";
	}

	@RequestMapping("/approve/{id}/")
	public String approve(@PathVariable String id, ModelMap map, RedirectAttributes attributes) {

		Journey journey = journeyService.findById(id);

		BigDecimal participantAmount = journey.getTotalAmountParticipant();

		BigDecimal moderatorAmount = journey.getTotalAmountModerator();

		BigDecimal informantAmount = journey.getTotalAmountHonorInformant();
		

		BigDecimal informantTransportAmount = journey.getTotalAmountTransportInformant();
		
		BigDecimal packetMeetingAmount = journey.getPacketMeetingAmount();
		
		
		System.out.println("test---------------"+informantTransportAmount);
		
		
		BigDecimal requirementAmount = journey.getTotalAmountRequirement();

		BigDecimal innAmount = journey.getTotalAmountInn();

		if(journey.getInformantTransportAccount() == null){
			addMessage(attributes, "Nomor Akun Transport Narasumber belum di isi.", MessageType.ERROR);
			return "redirect:/sa/journey/detail/" + journey.getId() + "/";
			
		}

		HashMap<String, BigDecimal> val = new HashMap<String, BigDecimal>();

		val.put(journey.getParticipantAccount().getAccountNumber(), participantAmount);

		if (val.containsKey(journey.getModeratorAccount().getAccountNumber())) {
			BigDecimal bd = val.get(journey.getModeratorAccount().getAccountNumber());
			val.put(journey.getModeratorAccount().getAccountNumber(), bd.add(moderatorAmount));
		} else {
			val.put(journey.getModeratorAccount().getAccountNumber(), moderatorAmount);

		}

		if (val.containsKey(journey.getInformantAccount().getAccountNumber())) {
			BigDecimal bd = val.get(journey.getInformantAccount().getAccountNumber());
			val.put(journey.getInformantAccount().getAccountNumber(), bd.add(informantAmount));
		} else {
			val.put(journey.getInformantAccount().getAccountNumber(), informantAmount);

		}
		
		if (val.containsKey(journey.getInformantTransportAccount().getAccountNumber())) {
			BigDecimal bd = val.get(journey.getInformantTransportAccount().getAccountNumber());
			val.put(journey.getInformantTransportAccount().getAccountNumber(), bd.add(informantTransportAmount));
		} else {
			val.put(journey.getInformantTransportAccount().getAccountNumber(), informantTransportAmount);
		}
		
		
		if (val.containsKey(journey.getPacketMeetingAccount().getAccountNumber())) {
			BigDecimal bd = val.get(journey.getInformantTransportAccount().getAccountNumber());
			val.put(journey.getPacketMeetingAccount().getAccountNumber(), bd.add(packetMeetingAmount));
		} else {
			val.put(journey.getPacketMeetingAccount().getAccountNumber(), packetMeetingAmount);
		}
		
		

		if (val.containsKey(journey.getRequirementAccount().getAccountNumber())) {
			BigDecimal bd = val.get(journey.getRequirementAccount().getAccountNumber());
			val.put(journey.getRequirementAccount().getAccountNumber(), bd.add(requirementAmount));
		} else {
			val.put(journey.getRequirementAccount().getAccountNumber(), requirementAmount);

		}
		if (JourneyType.JOURNEY_IN.equals(journey.getType()) || JourneyType.JOURNEY_OUT.equals(journey.getType())) {
			if (val.containsKey(journey.getInnAccount().getAccountNumber())) {
				BigDecimal bd = val.get(journey.getInnAccount().getAccountNumber());
				val.put(journey.getInnAccount().getAccountNumber(), bd.add(innAmount));
			} else {
				val.put(journey.getInnAccount().getAccountNumber(), requirementAmount);

			}
		}
		
		
		for(String key:val.keySet()){
			ViewAccountBalance vab = accountService.getViewAccountBalance(key);
			
			System.out.println("key--------------"+key);
			System.out.println("-----------------------------"+vab);
			System.out.println("--------------"+vab.getAmount());
			System.out.println("-------------------"+val.get(key));
			if (val.get(key).compareTo(vab.getAmount()) == 1) {
				addMessage(attributes, "Saldo Akun kurang. Nomor Akun " + vab.getAccountNumber(), MessageType.ERROR);
				return "redirect:/sa/journey/detail/" + journey.getId() + "/";
			}
		}
		
		
		
//		BigDecimal partAc = accountService.getViewAccountBalance(journey.getParticipantAccount().getAccountNumber()).getAmount();
//
//		BigDecimal modAcc = accountService.getViewAccountBalance(journey.getModeratorAccount().getAccountNumber()).getAmount();
//
//		BigDecimal infAcc = accountService.getViewAccountBalance(journey.getInformantAccount().getAccountNumber()).getAmount();
//
//		BigDecimal reqAcc = accountService.getViewAccountBalance(journey.getRequirementAccount().getAccountNumber()).getAmount();
//
//		BigDecimal innAcc = accountService.getViewAccountBalance(journey.getInnAccount().getAccountNumber()).getAmount();
//
//		BigDecimal totalAcc = balanceService.getTotalBalance();
//
//		if (participantAmount.compareTo(partAc) == 1) {
//			addMessage(attributes, "Saldo Akun Peserta kurang.", MessageType.ERROR);
//			return "redirect:/sa/journey/detail/" + journey.getId() + "/";
//		}
//
//		if (moderatorAmount.compareTo(modAcc) == 1) {
//			addMessage(attributes, "Saldo Akun Moderator kurang.", MessageType.ERROR);
//			return "redirect:/sa/journey/detail/" + journey.getId() + "/";
//		}
//
//		if (informantAmount.compareTo(infAcc) == 1) {
//			addMessage(attributes, "Saldo Akun Narasumber kurang.", MessageType.ERROR);
//			return "redirect:/sa/journey/detail/" + journey.getId() + "/";
//		}
//
//		if (requirementAmount.compareTo(reqAcc) == 1) {
//			addMessage(attributes, "Saldo Akun Kebutuhan Lain kurang.", MessageType.ERROR);
//			return "redirect:/sa/journey/detail/" + journey.getId() + "/";
//		}
//
//		if (JourneyType.JOURNEY_IN.equals(journey.getType()) || JourneyType.JOURNEY_OUT.equals(journey.getType())) {
//			if (innAmount.compareTo(innAcc) == 1) {
//				addMessage(attributes, "Saldo Akun Penginapan kurang.", MessageType.ERROR);
//				return "redirect:/sa/journey/detail/" + journey.getId() + "/";
//			}
//		}
//
//		if (totalAmount.compareTo(totalAcc) == 1) {
//			addMessage(attributes, "Perjalanan Telah Berhasil di setujui.", MessageType.ERROR);
//
//			return "redirect:/sa/journey/detail/" + journey.getId() + "/";
//
//		}

		if (JourneyStatus.PENDING.equals(journey.getStatus())) {
			journey.setStatus(JourneyStatus.IN_PROGRESS);
			journey.setUpdatedBy(SpringSecurityUtil.getUsername());
			journey.setUpdatedDate(new Date());

			journeyService.approve(journey);
		}
		addMessage(attributes, "Perjalanan Telah Berhasil di ajukan.", MessageType.SUCCESS);
		return "redirect:/sa/journey";
	}


}
