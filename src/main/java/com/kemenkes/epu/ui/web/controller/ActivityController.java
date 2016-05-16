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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kemenkes.epu.common.entity.ActivityAccountForm;
import com.kemenkes.epu.common.util.MessageType;
import com.kemenkes.epu.common.util.SpringSecurityUtil;
import com.kemenkes.epu.entity.Account;
import com.kemenkes.epu.entity.Activity;
import com.kemenkes.epu.entity.ActivitySource;
import com.kemenkes.epu.entity.ActivityType;
import com.kemenkes.epu.entity.Subdivision;
import com.kemenkes.epu.service.AccountService;
import com.kemenkes.epu.service.ActivityService;
import com.kemenkes.epu.service.SubdivisionService;
import com.kemenkes.epu.ui.validator.ActivityAccountFormValidator;
import com.kemenkes.epu.ui.validator.ActivityValidator;

@Controller
@RequestMapping("/activity")
public class ActivityController extends BaseController {

	@Autowired
	private ActivityService activityService;

	@Autowired
	private SubdivisionService subdivisionService;

	@Autowired
	private AccountService accountService;

	@RequestMapping
	public String index(@ModelAttribute Activity activity, ModelMap map, HttpServletRequest request) {

		PaginatedListImpl paginatedList = new PaginatedListImpl(request);
		PaginatedList page = activityService.search(activity, paginatedList);
		map.put("page", page);
		return "activity/list";
	}

	@RequestMapping("/precreate")
	public String precreate(ModelMap map) {

		Activity activity = new Activity();
		activity.setYear(DateTime.now().getYear());

		ActivitySource[] sources = ActivitySource.values();
		ActivityType[] types = ActivityType.values();

		List<Subdivision> subdivisions = subdivisionService.findAll();

		map.put("sources", sources);
		map.put("types", types);
		map.put("subdivisions", subdivisions);
		map.put("activity", activity);
		return "activity/precreate";
	}

	@RequestMapping("/account/{code}/")
	public String accountList(@PathVariable String code, ModelMap map) {

		Activity activity = activityService.findByCode(code);
		map.put("activity", activity);
		return "activity/account/list";
	}

	@RequestMapping("/account/preadd/{code}/")
	public String accountPreAdd(@PathVariable String code, ModelMap map) {

		Activity activity = activityService.findByCode(code);

		ActivityAccountForm form = new ActivityAccountForm();
		form.setActivity(activity);

		List<Account> accounts = accountService.findAll();

		map.put("form", form);
		map.put("accounts", accounts);

		return "activity/account/preadd";
	}

	@RequestMapping("/account/preview")
	public String accountPreview(ModelMap map, @ModelAttribute ActivityAccountForm form, BindingResult result) {
		new ActivityAccountFormValidator().validate(form, result);
		if (result.hasErrors()) {
			Activity activity = activityService.findByCode(form.getActivity().getCode());
			form.setActivity(activity);
			List<Account> accounts = accountService.findAll();
			map.put("accounts", accounts);
			map.put("form", form);
			return "activity/account/preadd";
		}

		Activity activity = activityService.findByCode(form.getActivity().getCode());
		Account account = accountService.findById(form.getAccount().getAccountNumber());
		form.setActivity(activity);
		form.setAccount(account);
		map.put("form", form);
		return "activity/account/preview";
	}

	@RequestMapping("/account/add")
	public String accountAdd(@ModelAttribute ActivityAccountForm form, BindingResult result, ModelMap map, RedirectAttributes attributes, @RequestParam(required = false) boolean cancel) {
		new ActivityAccountFormValidator().validate(form, result);
		if (cancel || result.hasErrors()) {
			Activity activity = activityService.findByCode(form.getActivity().getCode());
			form.setActivity(activity);
			List<Account> accounts = accountService.findAll();
			map.put("accounts", accounts);
			map.put("form", form);

			return "activity/account/preadd";

		}

		Activity activity = activityService.findByCode(form.getActivity().getCode());
		Account account = accountService.findById(form.getAccount().getAccountNumber());

		activity.setUpdatedBy(SpringSecurityUtil.getUsername());
		activity.setUpdatedDate(new Date());
		activity.getAccounts().add(account);

		activityService.update(activity);

		addMessage(attributes, "account.add.success", MessageType.SUCCESS);
		return String.format("redirect:/activity/account/%s/", activity.getCode());
	}

	@RequestMapping("/preview")
	public String preview(ModelMap map, @ModelAttribute Activity activity, BindingResult result) {
		new ActivityValidator().validate(activity, result);
		if (result.hasErrors()) {

			ActivitySource[] sources = ActivitySource.values();
			ActivityType[] types = ActivityType.values();
			List<Subdivision> subdivisions = subdivisionService.findAll();

			map.put("sources", sources);
			map.put("types", types);
			map.put("subdivisions", subdivisions);
			return "activity/precreate";
		}

		if (activity.getSubdivision() != null) {
			Subdivision subdivision = subdivisionService.findByCode(activity.getSubdivision().getCode());
			activity.setSubdivision(subdivision);
		}
		return "activity/preview";
	}

	@RequestMapping("/create")
	public String create(@ModelAttribute Activity activity, BindingResult result, ModelMap map, RedirectAttributes attributes, @RequestParam(required = false) boolean cancel) {
		new ActivityValidator().validate(activity, result);
		if (cancel || result.hasErrors()) {
			ActivitySource[] sources = ActivitySource.values();
			ActivityType[] types = ActivityType.values();
			List<Subdivision> subdivisions = subdivisionService.findAll();

			map.put("sources", sources);
			map.put("types", types);
			map.put("subdivisions", subdivisions);
			return "activity/precreate";

		}

		activity.setCreatedBy(SpringSecurityUtil.getUsername());
		activity.setCreatedDate(new Date());
		activityService.create(activity);
		addMessage(attributes, "activity.create.success", MessageType.SUCCESS);
		return "redirect:/activity";
	}

}
