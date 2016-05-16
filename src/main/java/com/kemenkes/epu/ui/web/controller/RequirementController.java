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
import com.kemenkes.epu.entity.Journey;
import com.kemenkes.epu.entity.Requirement;
import com.kemenkes.epu.service.JourneyService;
import com.kemenkes.epu.service.RequirementService;
import com.kemenkes.epu.ui.validator.RequirementValidator;

@Controller
@RequestMapping("/requirement")
public class RequirementController extends BaseController {
	@Autowired
	private RequirementService requirementService;

	@Autowired
	private JourneyService journeyService;
	
	@RequestMapping("/precreate/{id}/")
	public String precreate(@PathVariable String id, ModelMap map) {
		Journey journey = journeyService.findById(id);
		map.put("journey", journey);

		Requirement requirement = new Requirement();
		requirement.setJourney(journey);

		map.put("requirement", requirement);
		return "journey/requirement/precreate";
	}
	
	@RequestMapping("/preview")
	public String preview(@ModelAttribute Requirement requirement, BindingResult result, ModelMap map) {

		if (requirement.getJourney() == null) {
			return "redirect:/requirement/activity";
		}

		Journey journey = journeyService.findById(requirement.getJourney().getId());
		requirement.setJourney(journey);

		map.put("journey", journey);
		new RequirementValidator().validate(requirement, result);
		if (result.hasErrors()) {
			return "journey/requirement/precreate";
		}

		return "journey/requirement/preview";
	}
	
	@RequestMapping("/create")
	public String create(@ModelAttribute Requirement requirement, BindingResult result, ModelMap map, RedirectAttributes attributes, @RequestParam(required = false) boolean cancel) {

		if (requirement.getJourney() == null) {
			return "redirect:/requirement/activity";
		}
		Journey journey = journeyService.findById(requirement.getJourney().getId());
		requirement.setJourney(journey);
		
		map.put("journey", journey);

		new RequirementValidator().validate(requirement, result);
		if (result.hasErrors() || cancel) {
			return "journey/informant/precreate";
		}

		requirement.setCreatedBy(SpringSecurityUtil.getUsername());
		requirement.setCreatedDate(new Date());
		requirementService.create(requirement);

		addMessage(attributes, "requirement.journey.add.success", MessageType.SUCCESS);
		return "redirect:/requirement/precreate/" + journey.getId() + "/";
	}
	
	@RequestMapping("/predelete/{id}/")
	public String predelete(@PathVariable String id, ModelMap map) {
		Requirement requirement = requirementService.findById(id);
		map.put("requirement", requirement);
		map.put("journey", requirement.getJourney());
		return "journey/requirement/predelete";
	}

	@RequestMapping("/delete")
	public String delete(@ModelAttribute Requirement requirement, RedirectAttributes attributes) {
		requirement = requirementService.findById(requirement.getId());
		Journey journey = requirement.getJourney();
		requirementService.delete(requirement);
		addMessage(attributes, "requirement.journey.delete.success", MessageType.SUCCESS);
		return "redirect:/requirement/precreate/" + journey.getId() + "/";
	}
}
