package com.kemenkes.epu.ui.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.kemenkes.epu.common.entity.PasswordChangeForm;
import com.kemenkes.epu.common.entity.UserForm;
import com.kemenkes.epu.common.util.Constant;
import com.kemenkes.epu.common.util.Message;
import com.kemenkes.epu.common.util.MessageType;
import com.kemenkes.epu.common.util.SpringSecurityUtil;
import com.kemenkes.epu.entity.Subdivision;
import com.kemenkes.epu.entity.User;
import com.kemenkes.epu.entity.UserRole;
import com.kemenkes.epu.service.SubdivisionService;
import com.kemenkes.epu.service.UserService;
import com.kemenkes.epu.ui.validator.PasswordChangeFormValidator;
import com.kemenkes.epu.ui.validator.UserFormValidator;
import com.kemenkes.epu.ui.validator.UserValidator;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	@Autowired
	private SubdivisionService subdivisionService;

	@Autowired
	private PasswordChangeFormValidator passwordChangeFormValidator;

	@RequestMapping
	public String index(@ModelAttribute User user, ModelMap map, HttpServletRequest request, HttpSession session) {

		User sessionUser = (User) session.getAttribute(Constant.SESSION_USER);

		if (sessionUser != null) {

			user.setSubdivision(sessionUser.getSubdivision());
			
		}

		PaginatedListImpl paginatedList = new PaginatedListImpl(request);
		PaginatedList page = userService.search(user, paginatedList);
		map.put("page", page);
		return "user/list";
	}

	@RequestMapping("/precreate")
	public String precreate(ModelMap map, HttpSession session) {

		User user = new User();

		List<UserRole> roles = new ArrayList<UserRole>();

		if (SpringSecurityUtil.haveAuthority(UserRole.ROLE_SA.getId())) {
			roles.add(UserRole.ROLE_SA);
			roles.add(UserRole.ROLE_ADMIN);
		}

		if (SpringSecurityUtil.haveAuthority(UserRole.ROLE_ADMIN.getId())) {

			user.setRole(UserRole.ROLE_USER);
			User sessionUser = (User) session.getAttribute(Constant.SESSION_USER);
			user.setSubdivision(sessionUser.getSubdivision());

		}

		List<Subdivision> subdivisions = subdivisionService.findAll();

		UserForm userForm = new UserForm();
		userForm.setUser(user);
		map.put("user", user);
		map.put("userForm", userForm);
		map.put("roles", roles);
		map.put("subdivisions", subdivisions);
		return "user/precreate";
	}

	@RequestMapping("/preedit/{username}/")
	public String preedit(@PathVariable String username, ModelMap map) {

		List<Subdivision> subdivisions = subdivisionService.findAll();
		map.put("subdivisions", subdivisions);

		List<UserRole> roles = new ArrayList<UserRole>();

		if (SpringSecurityUtil.haveAuthority(UserRole.ROLE_SA.getId())) {
			roles.add(UserRole.ROLE_SA);
			roles.add(UserRole.ROLE_ADMIN);
		}
		map.put("roles", roles);
		map.put("user", userService.findByUsername(username));
		return "user/preedit";
	}

	@RequestMapping("/predelete/{username}/")
	public String predelete(@PathVariable String username, ModelMap map) {
		map.put("user", userService.findByUsername(username));
		return "user/predelete";
	}

	@RequestMapping("/delete")
	public String delete(@ModelAttribute User user, BindingResult result, RedirectAttributes attributes) {
		userService.delete(user);
		addMessage(attributes, "user.delete.success", MessageType.SUCCESS);
		return "redirect:/user";
	}

	@RequestMapping("/password/prechange")
	public String preChangePassword(ModelMap map) {
		PasswordChangeForm passwordChangeForm = new PasswordChangeForm();
		User user = new User();
		user.setUsername(SpringSecurityUtil.getUsername());
		passwordChangeForm.setUser(user);
		map.put("passwordChangeForm", passwordChangeForm);
		return "user/password-prechange";
	}

	@RequestMapping("/password/change")
	public String preChangePassword(@ModelAttribute PasswordChangeForm passwordChangeForm, BindingResult result, ModelMap map, RedirectAttributes attributes) {
		passwordChangeFormValidator.validate(passwordChangeForm, result);

		if (result.hasErrors()) {
			return "user/password-prechange";
		}

		User user = passwordChangeForm.getUser();

		user.setUpdatedBy(SpringSecurityUtil.getUsername());
		user.setUpdatedDate(new Date());

		userService.edit(user);

		map.put(MESSAGE, new Message("user.password.change.success", MessageType.SUCCESS));
		return "user/password-change-success";
	}

	@RequestMapping("/create")
	public String create(ModelMap map, @ModelAttribute UserForm userForm, BindingResult result, RedirectAttributes attributes) {

		new UserFormValidator().validate(userForm, result);
		if (result.hasErrors()) {
			List<UserRole> roles = new ArrayList<UserRole>();

			if (SpringSecurityUtil.haveAuthority(UserRole.ROLE_SA.getId())) {
				roles.add(UserRole.ROLE_SA);
				roles.add(UserRole.ROLE_ADMIN);
			}

			List<Subdivision> subdivisions = subdivisionService.findAll();
			map.put("subdivisions", subdivisions);
			map.put("roles", roles);
			return "user/precreate";
		}

		User user = userForm.getUser();

		user.setCreatedBy(SpringSecurityUtil.getUsername());
		user.setCreatedDate(new Date());
		user.setPassword(SpringSecurityUtil.encodePassword(user.getPassword()));

		userService.create(user);

		addMessage(attributes, "user.create.success", MessageType.SUCCESS);
		return "redirect:/user";
	}

	@RequestMapping("/edit")
	public String edit(ModelMap map, @ModelAttribute User user, BindingResult result, RedirectAttributes attributes) {

		new UserValidator().validate(user, result);
		if (result.hasErrors()) {
			List<Subdivision> subdivisions = subdivisionService.findAll();
			map.put("subdivisions", subdivisions);

			List<UserRole> roles = new ArrayList<UserRole>();

			if (SpringSecurityUtil.haveAuthority(UserRole.ROLE_SA.getId())) {
				roles.add(UserRole.ROLE_SA);
				roles.add(UserRole.ROLE_ADMIN);
			}
			map.put("roles", roles);
			return "user/preedit";
		}

		User newUser = userService.findByUsername(user.getUsername());
		newUser.setSubdivision(user.getSubdivision());
		newUser.setRole(user.getRole());

		newUser.setUpdatedBy(SpringSecurityUtil.getUsername());
		newUser.setUpdatedDate(new Date());

		userService.edit(newUser);
		addMessage(attributes, "user.edit.success", MessageType.SUCCESS);
		return "redirect:/user";
	}

}
