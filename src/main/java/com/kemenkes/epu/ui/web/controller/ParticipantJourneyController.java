package com.kemenkes.epu.ui.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kemenkes.epu.common.util.MessageType;
import com.kemenkes.epu.common.util.SpringSecurityUtil;
import com.kemenkes.epu.entity.Journey;
import com.kemenkes.epu.entity.Participant;
import com.kemenkes.epu.entity.ParticipantJourney;
import com.kemenkes.epu.service.JourneyService;
import com.kemenkes.epu.service.ParticipantJourneyService;
import com.kemenkes.epu.service.ParticipantService;
import com.kemenkes.epu.ui.validator.ParticipantJourneyValidator;

@Controller
@RequestMapping("/participantjourney")
public class ParticipantJourneyController extends BaseController {

	@Autowired
	private ParticipantJourneyService participantJourneyService;

	@Autowired
	private JourneyService journeyService;

	@Autowired
	private ParticipantService participantService;

	@Autowired
	private ParticipantJourneyValidator participantJourneyValidator;

	@RequestMapping("/precreate/{id}/")
	public String precreate(@PathVariable String id, ModelMap map,HttpSession session) {
		Journey journey = journeyService.findById(id);
		map.put("activity", journey.getActivity());
		map.put("journey", journey);

		ParticipantJourney participantJourney = new ParticipantJourney();
		
		ParticipantJourney sessionParticipantJourney = (ParticipantJourney) session.getAttribute(ParticipantJourney.class.getName());
		
		if(sessionParticipantJourney!=null){
			participantJourney = sessionParticipantJourney;
			participantJourney.setParticipant(null);
			participantJourney.setName(null);
		}
		
		
		participantJourney.setJourney(journey);
		participantJourney.setStartDate(journey.getStartDate());
		participantJourney.setEndDate(journey.getEndDate());
		map.put("participantJourney", participantJourney);

		List<Participant> participants = participantService.findAll();
		
		System.out.println("----------"+journey.getParticipants().size());
		
		
		map.put("participants", participants);

		return "journey/participant/precreate";
	}

	@RequestMapping("/view/{id}/")
	public String view(@PathVariable String id, ModelMap map) {
		ParticipantJourney participantJourney = participantJourneyService.findById(id);
		map.put("participantJourney", participantJourney);
		map.put("journey", participantJourney.getJourney());
		map.put("activity", participantJourney.getJourney().getActivity());
		return "journey/participant/view";
	}

	@RequestMapping("/predelete/{id}/")
	public String predelete(@PathVariable String id, ModelMap map) {
		ParticipantJourney participantJourney = participantJourneyService.findById(id);
		map.put("participantJourney", participantJourney);
		map.put("journey", participantJourney.getJourney());
		map.put("activity", participantJourney.getJourney().getActivity());
		return "journey/participant/predelete";
	}

	@RequestMapping("/delete")
	public String delete(@ModelAttribute ParticipantJourney participantJourney, RedirectAttributes attributes) {
		participantJourney = participantJourneyService.findById(participantJourney.getId());
		Journey journey = participantJourney.getJourney();
		participantJourneyService.delete(participantJourney);
		addMessage(attributes, "participant.journey.delete.success", MessageType.SUCCESS);
		return "redirect:/participantjourney/precreate/" + journey.getId() + "/";
	}

	@RequestMapping("/preview")
	public String preview(@ModelAttribute ParticipantJourney participantJourney, BindingResult result, ModelMap map) {

		if (participantJourney.getJourney() == null) {
			return "redirect:/journey/activity";
		}

		Journey journey = journeyService.findById(participantJourney.getJourney().getId());
		participantJourney.setJourney(journey);
		map.put("journey", journey);
		map.put("activity", journey.getActivity());
		
		System.out.println("aaaa"+participantJourney);
		
		participantJourneyValidator.validate(participantJourney, result);
		
		for(ObjectError error:result.getAllErrors()){
			
			System.out.println(error.getObjectName() + error.getDefaultMessage());
		}
		
		if (result.hasErrors()) {
			List<Participant> participants = participantService.findAll();
			map.put("participants", participants);
			return "journey/participant/precreate";
		}

		if(!participantJourney.isFlag()){
			Participant participant = participantService.findByCode(participantJourney.getParticipant().getCode());
			participantJourney.setParticipant(participant);
		}
		return "journey/participant/preview";
	}

	@RequestMapping("/create")
	public String create(@ModelAttribute ParticipantJourney participantJourney, BindingResult result, ModelMap map, RedirectAttributes attributes, @RequestParam(required = false) boolean cancel,HttpSession session) {

		if (participantJourney.getJourney() == null) {
			return "redirect:/journey/activity";
		}
		Journey journey = journeyService.findById(participantJourney.getJourney().getId());
		participantJourney.setJourney(journey);
		map.put("journey", journey);
		map.put("activity", journey.getActivity());

		participantJourneyValidator.validate(participantJourney, result);
		
		for(ObjectError error:result.getAllErrors()){
			
			System.out.println(error.getObjectName() + error.getDefaultMessage());
		}
	
		if (result.hasErrors() || cancel) {
			List<Participant> participants = participantService.findAll();
			map.put("participants", participants);
			return "journey/participant/precreate";
		}

		participantJourney.setCreatedBy(SpringSecurityUtil.getUsername());
		participantJourney.setCreatedDate(new Date());
		participantJourneyService.create(participantJourney);

		session.setAttribute(ParticipantJourney.class.getName(), participantJourney);
		
		addMessage(attributes, "participant.journey.add.success", MessageType.SUCCESS);
		return "redirect:/participantjourney/precreate/" + journey.getId() + "/";
	}
	

}
