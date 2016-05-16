package com.kemenkes.epu.ui.web.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kemenkes.epu.common.util.MessageType;
import com.kemenkes.epu.common.util.SpringSecurityUtil;
import com.kemenkes.epu.entity.Activity;
import com.kemenkes.epu.entity.Journey;
import com.kemenkes.epu.entity.JourneyType;
import com.kemenkes.epu.service.AccountService;
import com.kemenkes.epu.service.ActivityService;
import com.kemenkes.epu.service.JourneyService;
import com.kemenkes.epu.ui.validator.JourneyAccountValidator;

@Controller
@RequestMapping("/journeyaccount")
public class JourneyAccountController extends BaseController {

	@Autowired
	private JourneyService journeyService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private ActivityService activityService;

	@RequestMapping("/preedit/{id}/")
	public String precreate(@PathVariable String id, ModelMap map) {
		Journey journey = journeyService.findById(id);
		map.put("activity", journey.getActivity());
		map.put("journey", journey);

		return "journey/account/preedit";
	}

	@RequestMapping("/preview")
	public String preview(@ModelAttribute Journey journey, BindingResult result, ModelMap map, RedirectAttributes attributes, @RequestParam(required = false) boolean cancel) {

		Journey old = journeyService.findById(journey.getId());
		if (JourneyType.FULLBOARD.equals(old.getType()) || JourneyType.FULLDAY.equals(old.getType())) {
			old.setPacketMeetingAccount(journey.getPacketMeetingAccount());
		}
		if (JourneyType.JOURNEY_IN.equals(old.getType()) || JourneyType.JOURNEY_OUT.equals(old.getType())) {
			old.setInnAccount(journey.getInnAccount());
		}
		old.setParticipantAccount(journey.getParticipantAccount());
		old.setModeratorAccount(journey.getModeratorAccount());
		old.setInformantAccount(journey.getInformantAccount());
		old.setInformantTransportAccount(journey.getInformantTransportAccount());
		old.setRequirementAccount(journey.getRequirementAccount());
		old.setReturnAmountAccount(journey.getReturnAmountAccount());

		new JourneyAccountValidator().validate(journey, result);
		
		if (result.hasErrors()||cancel) {

			Activity activity = activityService.findByCode(old.getActivity().getCode());
			map.put("activity", activity);
			return "journey/account/preedit";
		}
		journey = old;
		if (JourneyType.FULLBOARD.equals(journey.getType()) || JourneyType.FULLDAY.equals(journey.getType())) {
			journey.setPacketMeetingAccount(accountService.findById(journey.getPacketMeetingAccount().getAccountNumber()));
		}
		if (JourneyType.JOURNEY_IN.equals(journey.getType()) || JourneyType.JOURNEY_OUT.equals(journey.getType())) {
			journey.setInnAccount(accountService.findById(journey.getInnAccount().getAccountNumber()));
		}
		journey.setParticipantAccount(accountService.findById(journey.getParticipantAccount().getAccountNumber()));
		journey.setModeratorAccount(accountService.findById(journey.getModeratorAccount().getAccountNumber()));
		journey.setInformantAccount(accountService.findById(journey.getInformantAccount().getAccountNumber()));
		journey.setInformantTransportAccount(accountService.findById(journey.getInformantTransportAccount().getAccountNumber()));
		journey.setRequirementAccount(accountService.findById(journey.getRequirementAccount().getAccountNumber()));
		journey.setReturnAmountAccount(accountService.findById(journey.getReturnAmountAccount().getAccountNumber()));
	
		map.put("journey", journey);
		return "journey/account/preview";
	}
	
	
	@RequestMapping("/create")
	public String createForm(@ModelAttribute Journey journey, BindingResult result, ModelMap map, RedirectAttributes attributes, @RequestParam(required = false) boolean cancel) {
		Journey old = journeyService.findById(journey.getId());
		if (JourneyType.FULLBOARD.equals(old.getType()) || JourneyType.FULLDAY.equals(old.getType())) {
			old.setPacketMeetingAccount(journey.getPacketMeetingAccount());
		}
		if (JourneyType.JOURNEY_IN.equals(old.getType()) || JourneyType.JOURNEY_OUT.equals(old.getType())) {
			old.setInnAccount(journey.getInnAccount());
		}
		old.setParticipantAccount(journey.getParticipantAccount());
		old.setModeratorAccount(journey.getModeratorAccount());
		old.setInformantAccount(journey.getInformantAccount());
		old.setInformantTransportAccount(journey.getInformantTransportAccount());
		old.setRequirementAccount(journey.getRequirementAccount());
		old.setReturnAmountAccount(journey.getReturnAmountAccount());
		journey = old;

		new JourneyAccountValidator().validate(journey, result);
		if (result.hasErrors()||cancel) {
			map.put("journey", journey);
			map.put("activity", old.getActivity());
			return "journey/account/preedit";
		}
		old.setUpdatedBy(SpringSecurityUtil.getUsername());
		old.setUpdatedDate(new Date());
		journeyService.update(old);

		addMessage(attributes, "Nomor akun berhasil diubah.", MessageType.SUCCESS);
		
		return "redirect:/journey/detail/" + journey.getId() + "/";
	}

}
