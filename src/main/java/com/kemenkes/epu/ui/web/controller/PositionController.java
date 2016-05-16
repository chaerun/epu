package com.kemenkes.epu.ui.web.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

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
import com.kemenkes.epu.entity.Position;
import com.kemenkes.epu.service.PositionService;
import com.kemenkes.epu.ui.validator.PositionValidator;

@Controller()
@RequestMapping("/position")
public class PositionController extends BaseController {
	@Autowired
	private PositionService positionService;

	@RequestMapping
	public String index(@ModelAttribute Position position, ModelMap map, HttpServletRequest request) {

		PaginatedListImpl paginatedList = new PaginatedListImpl(request);
		PaginatedList page = positionService.search(position, paginatedList);
		map.put("page", page);
		return "position/list";
	}

	@RequestMapping("/precreate")
	public String precreate(ModelMap map) {
		map.put("position", new Position());
		return "position/precreate";
	}

	@RequestMapping("/preedit/{id}/")
	public String preedit(@PathVariable String id, ModelMap map) {
		map.put("position", positionService.findById(id));
		return "position/preedit";
	}
	

	@RequestMapping("/predelete/{id}/")
	public String predelete(@PathVariable String id, ModelMap map) {
		map.put("position", positionService.findById(id));
		return "position/predelete";
	}
	
	@RequestMapping("/delete")
	public String delete(@ModelAttribute Position position, BindingResult result, RedirectAttributes attributes) {

		positionService.delete(position);
		addMessage(attributes, "position.delete.success", MessageType.SUCCESS);
		return "redirect:/position";
	}

	@RequestMapping("/create")
	public String create(@ModelAttribute Position position, BindingResult result, RedirectAttributes attributes) {

		new PositionValidator().validate(position, result);
		if (result.hasErrors()) {
			return "position/precreate";
		}
		position.setCreatedBy(SpringSecurityUtil.getUsername());
		position.setCreatedDate(new Date());

		positionService.create(position);

		addMessage(attributes, "position.create.success", MessageType.SUCCESS);
		return "redirect:/position";
	}

	@RequestMapping("/edit")
	public String edit(@ModelAttribute Position position, BindingResult result, RedirectAttributes attributes) {

		new PositionValidator().validate(position, result);
		if (result.hasErrors()) {
			return "position/preedit";
		}

		Position newPosition = positionService.findById(position.getId());
		newPosition.setName(position.getName());

		newPosition.setUpdatedBy(SpringSecurityUtil.getUsername());
		newPosition.setUpdatedDate(new Date());

		positionService.edit(newPosition);
		addMessage(attributes, "position.edit.success", MessageType.SUCCESS);
		return "redirect:/position";
	}

}
