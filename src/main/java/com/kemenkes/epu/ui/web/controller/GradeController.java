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
import com.kemenkes.epu.entity.Grade;
import com.kemenkes.epu.service.GradeService;
import com.kemenkes.epu.ui.validator.GradeValidator;

@Controller()
@RequestMapping("/grade")
public class GradeController extends BaseController {
	@Autowired
	private GradeService gradeService;

	@RequestMapping
	public String index(@ModelAttribute Grade grade, ModelMap map, HttpServletRequest request) {

		PaginatedListImpl paginatedList = new PaginatedListImpl(request);
		PaginatedList page = gradeService.search(grade, paginatedList);
		map.put("page", page);
		return "grade/list";
	}

	@RequestMapping("/precreate")
	public String precreate(ModelMap map) {
		map.put("grade", new Grade());
		return "grade/precreate";
	}

	@RequestMapping("/preedit/{id}/")
	public String preedit(@PathVariable String id, ModelMap map) {
		map.put("grade", gradeService.findById(id));
		return "grade/preedit";
	}

	@RequestMapping("/predelete/{id}/")
	public String predelete(@PathVariable String id, ModelMap map) {
		map.put("grade", gradeService.findById(id));
		return "grade/predelete";
	}

	@RequestMapping("/delete")
	public String delete(@ModelAttribute Grade grade, BindingResult result, RedirectAttributes attributes) {

		gradeService.delete(grade);
		addMessage(attributes, "grade.delete.success", MessageType.SUCCESS);
		return "redirect:/grade";
	}

	@RequestMapping("/create")
	public String create(@ModelAttribute Grade grade, BindingResult result, RedirectAttributes attributes) {

		new GradeValidator().validate(grade, result);
		if (result.hasErrors()) {
			return "grade/precreate";
		}
		grade.setCreatedBy(SpringSecurityUtil.getUsername());
		grade.setCreatedDate(new Date());

		gradeService.create(grade);

		addMessage(attributes, "grade.create.success", MessageType.SUCCESS);
		return "redirect:/grade";
	}

	@RequestMapping("/edit")
	public String edit(@ModelAttribute Grade grade, BindingResult result, RedirectAttributes attributes) {

		new GradeValidator().validate(grade, result);
		if (result.hasErrors()) {
			return "grade/preedit";
		}

		Grade newGrade = gradeService.findById(grade.getId());
		newGrade.setName(grade.getName());

		newGrade.setUpdatedBy(SpringSecurityUtil.getUsername());
		newGrade.setUpdatedDate(new Date());

		gradeService.edit(newGrade);
		addMessage(attributes, "grade.edit.success", MessageType.SUCCESS);
		return "redirect:/grade";
	}

}
