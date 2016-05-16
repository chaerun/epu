package com.kemenkes.epu.ui.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.displaytag.pagination.PaginatedList;
import org.displaytag.pagination.PaginatedListImpl;
import org.joda.time.DateTime;
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
import com.kemenkes.epu.entity.Official;
import com.kemenkes.epu.entity.Participant;
import com.kemenkes.epu.service.OfficialService;
import com.kemenkes.epu.service.ParticipantService;
import com.kemenkes.epu.ui.validator.OfficialValidator;

@Controller
@RequestMapping("/official")
public class OfficialController extends BaseController {
	@Autowired
	private OfficialService officialService;

	@Autowired
	private ParticipantService participantService;

	@RequestMapping
	public String index(@ModelAttribute Official official, ModelMap map, HttpServletRequest request) {

		PaginatedListImpl paginatedList = new PaginatedListImpl(request);
		PaginatedList page = officialService.search(official, paginatedList);
		map.put("page", page);
		return "official/list";
	}

	@RequestMapping("/precreate")
	public String precreate(ModelMap map) {

		List<Participant> participants = participantService.findAll();
		Official official = new Official();
		
		official.setYear(DateTime.now().getYear());

		map.put("official", official);
		map.put("participants", participants);
		return "official/precreate";
	}

	@RequestMapping("/predelete/{id}/")
	public String predelete(@PathVariable Integer year, ModelMap map) {
		map.put("official", officialService.findById(year));
		return "official/predelete";
	}

	@RequestMapping("/delete")
	public String delete(@ModelAttribute Official official, BindingResult result, RedirectAttributes attributes) {

		officialService.delete(official);
		addMessage(attributes, "official.delete.success", MessageType.SUCCESS);
		return "redirect:/official";
	}

	@RequestMapping("/create")
	public String create(ModelMap map, @ModelAttribute Official official, BindingResult result, RedirectAttributes attributes) {

		new OfficialValidator().validate(official, result);
		if (result.hasErrors()) {
			List<Participant> participants = participantService.findAll();

			map.put("participants", participants);
			return "official/precreate";
		}
		official.setCreatedBy(SpringSecurityUtil.getUsername());
		official.setCreatedDate(new Date());

		officialService.create(official);

		addMessage(attributes, "official.create.success", MessageType.SUCCESS);
		return "redirect:/official";
	}

}
