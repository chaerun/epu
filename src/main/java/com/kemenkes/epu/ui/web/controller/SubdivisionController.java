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
import com.kemenkes.epu.entity.Subdivision;
import com.kemenkes.epu.service.SubdivisionService;
import com.kemenkes.epu.ui.validator.SubdivisionValidator;

@Controller
@RequestMapping("/subdivision")
public class SubdivisionController extends BaseController {

	@Autowired
	private SubdivisionService subdivisionService;

	@RequestMapping
	public String index(@ModelAttribute Subdivision subdivision, ModelMap map, HttpServletRequest request) {

		PaginatedListImpl paginatedList = new PaginatedListImpl(request);
		PaginatedList page = subdivisionService.search(subdivision, paginatedList);
		map.put("page", page);
		return "subdivision/list";
	}

	@RequestMapping("/precreate")
	public String precreate(ModelMap map) {
		map.put("subdivision", new Subdivision());
		return "subdivision/precreate";
	}

	@RequestMapping("/preedit/{id}/")
	public String preedit(@PathVariable String id, ModelMap map) {
		map.put("subdivision", subdivisionService.findByCode(id));
		return "subdivision/preedit";
	}

	@RequestMapping("/predelete/{id}/")
	public String predelete(@PathVariable String id, ModelMap map) {
		map.put("subdivision", subdivisionService.findByCode(id));
		return "subdivision/predelete";
	}

	@RequestMapping("/delete")
	public String delete(@ModelAttribute Subdivision subdivision, BindingResult result, RedirectAttributes attributes) {

		subdivisionService.delete(subdivision);
		addMessage(attributes, "subdivision.delete.success", MessageType.SUCCESS);
		return "redirect:/subdivision";
	}

	@RequestMapping("/create")
	public String create(@ModelAttribute Subdivision subdivision, BindingResult result, RedirectAttributes attributes) {

		new SubdivisionValidator().validate(subdivision, result);
		if (result.hasErrors()) {
			return "subdivision/precreate";
		}
		subdivision.setCreatedBy(SpringSecurityUtil.getUsername());
		subdivision.setCreatedDate(new Date());

		subdivisionService.create(subdivision);

		addMessage(attributes, "subdivision.create.success", MessageType.SUCCESS);
		return "redirect:/subdivision";
	}

	@RequestMapping("/edit")
	public String edit(@ModelAttribute Subdivision subdivision, BindingResult result, RedirectAttributes attributes) {

		new SubdivisionValidator().validate(subdivision, result);
		if (result.hasErrors()) {
			return "subdivision/preedit";
		}

		Subdivision newSubdivision = subdivisionService.findByCode(subdivision.getCode());

		newSubdivision.setName(subdivision.getName());
		newSubdivision.setUpdatedBy(SpringSecurityUtil.getUsername());
		newSubdivision.setUpdatedDate(new Date());

		subdivisionService.edit(newSubdivision);
		
		
		
		addMessage(attributes, "subdivision.edit.success", MessageType.SUCCESS);
		return "redirect:/subdivision";
	}

}
