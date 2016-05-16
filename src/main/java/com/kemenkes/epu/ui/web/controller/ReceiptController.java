package com.kemenkes.epu.ui.web.controller;

import java.util.Date;

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

import com.kemenkes.epu.common.util.MessageType;
import com.kemenkes.epu.common.util.SpringSecurityUtil;
import com.kemenkes.epu.entity.Activity;
import com.kemenkes.epu.entity.ActivityType;
import com.kemenkes.epu.entity.Receipt;
import com.kemenkes.epu.entity.view.ViewAccountBalance;
import com.kemenkes.epu.entity.view.ViewActivity;
import com.kemenkes.epu.service.AccountService;
import com.kemenkes.epu.service.ActivityService;
import com.kemenkes.epu.service.BalanceService;
import com.kemenkes.epu.service.ReceiptService;
import com.kemenkes.epu.ui.validator.ReceiptValidator;

@Controller
@RequestMapping("/receipt")
public class ReceiptController extends BaseController {
	@Autowired
	private ActivityService activityService;

	@Autowired
	private ReceiptService receiptService;

	@Autowired
	private BalanceService balanceService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private ReceiptValidator receiptValidator;

	@RequestMapping("/activity")
	public String activityList(@ModelAttribute Activity activity, ModelMap map, HttpServletRequest request) {

		if (activity.getYear() == null) {
			activity.setYear(DateTime.now().getYear());
		}
		activity.setType(ActivityType.RECEIPT);

		PaginatedListImpl paginatedList = new PaginatedListImpl(request);
		PaginatedList page = activityService.searchView(activity, paginatedList);
		map.put("page", page);

		map.put("activity", activity);
		return "receipt/activity/list";
	}

	@RequestMapping("/activity/{code}/")
	public String index(@PathVariable String code, ModelMap map, HttpServletRequest request) {

		Activity activity = activityService.findByCode(code);

		Receipt receipt = new Receipt();
		receipt.setActivity(activity);

		PaginatedListImpl paginatedList = new PaginatedListImpl(request);
		PaginatedList page = receiptService.search(receipt, paginatedList);

		ViewActivity view = activityService.findViewByCode(code);

		map.put("page", page);
		map.put("activity", activity);
		map.put("receipt", receipt);
		map.put("view", view);

		return "receipt/list";
	}

	@RequestMapping("/preview")
	public String preview(@ModelAttribute Receipt receipt, BindingResult result, ModelMap map, HttpServletRequest request) {

		if (receipt.getActivity() == null) {
			return "redirect:/receipt/activity";
		}

		Activity activity = activityService.findByCode(receipt.getActivity().getCode());

		receipt.setActivity(activity);

		map.put("activity", activity);
		receiptValidator.validate(receipt, result);
		if (result.hasErrors()) {
			Receipt search = new Receipt();
			search.setActivity(activity);
			PaginatedListImpl paginatedList = new PaginatedListImpl(request);
			PaginatedList page = receiptService.search(search, paginatedList);
			map.put("page", page);
			ViewActivity view = activityService.findViewByCode(activity.getCode());
			map.put("view", view);
			return "receipt/list";
		}

		ViewAccountBalance accountBalance = accountService.getViewAccountBalance(receipt.getAccount().getAccountNumber());
		map.put("accountBalance", accountBalance);

		return "receipt/preview";
	}

	@RequestMapping("/create")
	public String create(@ModelAttribute Receipt receipt, BindingResult result, ModelMap map, RedirectAttributes attributes, @RequestParam(required = false) boolean cancel, HttpServletRequest request) {

		if (receipt.getActivity() == null) {
			return "redirect:/receipt/activity";
		}
		Activity activity = activityService.findByCode(receipt.getActivity().getCode());
		receipt.setActivity(activity);

		map.put("activity", activity);

		receiptValidator.validate(receipt, result);
		if (result.hasErrors() || cancel) {
			Receipt search = new Receipt();
			search.setActivity(activity);
			PaginatedListImpl paginatedList = new PaginatedListImpl(request);
			PaginatedList page = receiptService.search(search, paginatedList);
			ViewActivity view = activityService.findViewByCode(activity.getCode());
			map.put("view", view);
			map.put("page", page);
			return "receipt/list";
		}

		receipt.setCreatedBy(SpringSecurityUtil.getUsername());
		receipt.setCreatedDate(new Date());
		receiptService.create(receipt);

		addMessage(attributes, "receipt.create.success", MessageType.SUCCESS);
		return "redirect:/receipt/activity/" + activity.getCode() + "/";
	}

}
