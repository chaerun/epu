package com.kemenkes.epu.ui.web.controller;

import java.math.BigDecimal;
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
import com.kemenkes.epu.entity.Journey;
import com.kemenkes.epu.entity.ParticipantJourney;
import com.kemenkes.epu.service.JourneyService;
import com.kemenkes.epu.service.ParticipantJourneyService;
import com.kemenkes.epu.ui.validator.ModeratorValidator;

@Controller
@RequestMapping("/moderator")
public class ModeratorController extends BaseController {

	@Autowired
	private ParticipantJourneyService participantJourneyService;

	@Autowired
	private JourneyService journeyService;

	@RequestMapping("/precreate/{id}/")
	public String precreate(@PathVariable String id, ModelMap map) {
		Journey journey = journeyService.findById(id);
		map.put("activity", journey.getActivity());
		map.put("journey", journey);

		ParticipantJourney participantJourney = new ParticipantJourney();
		map.put("participantJourney", participantJourney);
		map.put("participantJourneys", journey.getParticipants());

		return "journey/moderator/precreate";
	}

	@RequestMapping("/predelete/{id}/")
	public String predelete(@PathVariable String id, ModelMap map) {
		ParticipantJourney participantJourney = participantJourneyService.findById(id);
		
		Journey journey = journeyService.findById(participantJourney.getJourney().getId());
		
		map.put("participantJourney", participantJourney);
		map.put("journey", journey);
		return "journey/moderator/predelete";
	}
	
	@RequestMapping("/view/{id}/")
	public String view(@PathVariable String id, ModelMap map) {
		ParticipantJourney participantJourney = participantJourneyService.findById(id);
		
		Journey journey = journeyService.findById(participantJourney.getJourney().getId());
		
		map.put("participantJourney", participantJourney);
		map.put("journey", journey);
		return "journey/moderator/view";
	}

	@RequestMapping("/delete")
	public String delete(@ModelAttribute ParticipantJourney participantJourney, RedirectAttributes attributes) {
		participantJourney = participantJourneyService.findById(participantJourney.getId());
		Journey journey = participantJourney.getJourney();
		
		participantJourney.setModeratorAmount(BigDecimal.ZERO);
		participantJourney.setModeratorHours(0);
		participantJourney.setModeratorPpn(0);
		participantJourney.setModerator(false);
		participantJourney.setUpdatedBy(SpringSecurityUtil.getUsername());
		participantJourney.setUpdatedDate(new Date());

		participantJourneyService.update(participantJourney);
		
		addMessage(attributes, "moderator.journey.delete.success", MessageType.SUCCESS);
		return "redirect:/moderator/precreate/" + journey.getId() + "/";
	}

	@RequestMapping("/preview")
	public String preview(@ModelAttribute ParticipantJourney participantJourney, BindingResult result, ModelMap map) {

		ParticipantJourney old = participantJourneyService.findById(participantJourney.getId());
		Journey journey = journeyService.findById(old.getJourney().getId());
		map.put("journey", journey);
		participantJourney.setJourney(journey);
		old.setModeratorAmount(participantJourney.getModeratorAmount());
		old.setModeratorHours(participantJourney.getModeratorHours());
		old.setModeratorPpn(participantJourney.getModeratorPpn());
		old.setModerator(true);
		old.setJourney(journey);
		new ModeratorValidator().validate(old, result);
		if (result.hasErrors()) {

			map.put("participantJourneys", journey.getParticipants());
			return "journey/moderator/precreate";
		}

		old.setModeratorAmount(participantJourney.getModeratorAmount());
		old.setModeratorHours(participantJourney.getModeratorHours());
		old.setModeratorPpn(participantJourney.getModeratorPpn());

		map.put("participantJourney", old);
		return "journey/moderator/preview";
	}

	@RequestMapping("/create")
	public String create(@ModelAttribute ParticipantJourney participantJourney, BindingResult result, ModelMap map, RedirectAttributes attributes, @RequestParam(required = false) boolean cancel) {

		ParticipantJourney old = participantJourneyService.findById(participantJourney.getId());
		Journey journey = journeyService.findById(participantJourney.getJourney().getId());
		map.put("journey", journey);

		participantJourney.setJourney(journey);
		old.setModeratorAmount(participantJourney.getModeratorAmount());
		old.setModeratorHours(participantJourney.getModeratorHours());
		old.setModeratorPpn(participantJourney.getModeratorPpn());
		old.setModerator(true);
		old.setJourney(journey);

		new ModeratorValidator().validate(old, result);
		if (result.hasErrors() || cancel) {

			map.put("participantJourneys", journey.getParticipants());
			return "journey/moderator/precreate";
		}

		old.setUpdatedBy(SpringSecurityUtil.getUsername());
		old.setUpdatedDate(new Date());

		participantJourneyService.update(old);

		addMessage(attributes, "moderator.journey.add.success", MessageType.SUCCESS);
		return "redirect:/moderator/precreate/" + journey.getId() + "/";
	}

}
