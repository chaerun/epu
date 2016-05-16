package com.kemenkes.epu.ui.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.displaytag.pagination.PaginatedList;
import org.displaytag.pagination.PaginatedListImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kemenkes.epu.common.util.MessageType;
import com.kemenkes.epu.common.util.SpringSecurityUtil;
import com.kemenkes.epu.entity.Grade;
import com.kemenkes.epu.entity.Participant;
import com.kemenkes.epu.entity.Position;
import com.kemenkes.epu.service.GradeService;
import com.kemenkes.epu.service.ParticipantService;
import com.kemenkes.epu.service.PositionService;
import com.kemenkes.epu.ui.validator.ParticipantValidator;

@Controller()
@RequestMapping("/participant")
public class ParticipantController extends BaseController {
	@Autowired
	private ParticipantService participantService;

	@Autowired
	private GradeService gradeService;

	@Autowired
	private PositionService positionService;

	@RequestMapping
	public String index(@ModelAttribute Participant participant, ModelMap map, HttpServletRequest request) {

		PaginatedListImpl paginatedList = new PaginatedListImpl(request);
		PaginatedList page = participantService.search(participant, paginatedList);
		map.put("page", page);

		return "participant/list";
	}

	@RequestMapping("/precreate")
	public String precreate(ModelMap map) {
		map.put("participant", new Participant());

		List<Position> positions = positionService.findAll();
		map.put("positions", positions);

		List<Grade> grades = gradeService.findAll();
		map.put("grades", grades);

		return "participant/precreate";
	}

	@RequestMapping("/preedit/{id}/")
	public String preedit(@PathVariable String id, ModelMap map) {
		map.put("participant", participantService.findByCode(id));
		List<Position> positions = positionService.findAll();
		map.put("positions", positions);

		List<Grade> grades = gradeService.findAll();
		map.put("grades", grades);
		return "participant/preedit";
	}

	@RequestMapping("/predelete/{id}/")
	public String predelete(@PathVariable String id, ModelMap map) {
		map.put("participant", participantService.findByCode(id));
		return "participant/predelete";
	}

	@RequestMapping("/delete")
	public String delete(@ModelAttribute Participant participant, BindingResult result, RedirectAttributes attributes) {

		participantService.delete(participant);
		addMessage(attributes, "participant.delete.success", MessageType.SUCCESS);
		return "redirect:/participant";
	}

	@RequestMapping("/create")
	public String create(@ModelAttribute Participant participant, BindingResult result, RedirectAttributes attributes) {

		new ParticipantValidator().validate(participant, result);
		if (result.hasErrors()) {
			return "participant/precreate";
		}
		participant.setCreatedBy(SpringSecurityUtil.getUsername());
		participant.setCreatedDate(new Date());

		if (StringUtils.isBlank(participant.getGrade().getId())) {
			participant.setGrade(null);
		}
		if (StringUtils.isBlank(participant.getPosition().getId())) {
			participant.setPosition(null);
		}

		participantService.create(participant);

		addMessage(attributes, "participant.create.success", MessageType.SUCCESS);
		return "redirect:/participant";
	}

	@RequestMapping("/edit")
	public String edit(@ModelAttribute Participant participant, BindingResult result, RedirectAttributes attributes) {

		new ParticipantValidator().validate(participant, result);
		if (result.hasErrors()) {
			return "participant/preedit";
		}

		if (StringUtils.isBlank(participant.getGrade().getId())) {
			participant.setGrade(null);
		}
		if (StringUtils.isBlank(participant.getPosition().getId())) {
			participant.setPosition(null);
		}

		Participant newParticipant = participantService.findByCode(participant.getCode());
		
		newParticipant.setName(participant.getName());
		newParticipant.setGrade(participant.getGrade());
		newParticipant.setPosition(participant.getPosition());
		
		newParticipant.setUpdatedBy(SpringSecurityUtil.getUsername());
		newParticipant.setUpdatedDate(new Date());

		participantService.edit(newParticipant);
		addMessage(attributes, "participant.edit.success", MessageType.SUCCESS);
		return "redirect:/participant";
	}
}
