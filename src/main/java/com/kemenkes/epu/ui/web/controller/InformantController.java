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
import com.kemenkes.epu.entity.Informant;
import com.kemenkes.epu.entity.Journey;
import com.kemenkes.epu.service.InformantService;
import com.kemenkes.epu.service.JourneyService;
import com.kemenkes.epu.ui.validator.InformantValidator;

@Controller
@RequestMapping("/informant")
public class InformantController extends BaseController {

	@Autowired
	private InformantService informantService;

	@Autowired
	private JourneyService journeyService;

	@RequestMapping("/precreate/{id}/")
	public String precreate(@PathVariable String id, ModelMap map) {
		Journey journey = journeyService.findById(id);
		map.put("journey", journey);

		Informant informant = new Informant();
		informant.setJourney(journey);
		informant.setStartDate(journey.getStartDate());
		informant.setEndDate(journey.getEndDate());

		map.put("informant", informant);
		return "journey/informant/precreate";
	}

	@RequestMapping("/preview")
	public String preview(@ModelAttribute Informant informant, BindingResult result, ModelMap map) {

		if (informant.getJourney() == null) {
			return "redirect:/journey/activity";
		}

		Journey journey = journeyService.findById(informant.getJourney().getId());
		informant.setJourney(journey);

		map.put("journey", journey);
		new InformantValidator().validate(informant, result);
		if (result.hasErrors()) {
			return "journey/informant/precreate";
		}

		return "journey/informant/preview";
	}

	@RequestMapping("/create")
	public String create(@ModelAttribute Informant informant, BindingResult result, ModelMap map, RedirectAttributes attributes, @RequestParam(required = false) boolean cancel) {

		if (informant.getJourney() == null) {
			return "redirect:/informant/activity";
		}
		Journey journey = journeyService.findById(informant.getJourney().getId());
		informant.setJourney(journey);
		map.put("journey", journey);

		new InformantValidator().validate(informant, result);
		if (result.hasErrors() || cancel) {
			return "journey/informant/precreate";
		}

		informant.setCreatedBy(SpringSecurityUtil.getUsername());
		informant.setCreatedDate(new Date());
		informantService.create(informant);

		addMessage(attributes, "informant.journey.add.success", MessageType.SUCCESS);
		return "redirect:/informant/precreate/" + journey.getId() + "/";
	}

	@RequestMapping("/view/{id}/")
	public String view(@PathVariable String id, ModelMap map) {
		Informant informant = informantService.findById(id);
		map.put("informant", informant);
		map.put("journey", informant.getJourney());
		return "journey/informant/view";
	}

	@RequestMapping("/predelete/{id}/")
	public String predelete(@PathVariable String id, ModelMap map) {
		Informant informant = informantService.findById(id);
		map.put("informant", informant);
		map.put("journey", informant.getJourney());
		return "journey/informant/predelete";
	}

	@RequestMapping("/delete")
	public String delete(@ModelAttribute Informant informant, RedirectAttributes attributes) {
		informant = informantService.findById(informant.getId());
		Journey journey = informant.getJourney();
		informantService.delete(informant);
		addMessage(attributes, "informant.journey.delete.success", MessageType.SUCCESS);
		return "redirect:/informant/precreate/" + journey.getId() + "/";
	}

}
