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
import com.kemenkes.epu.entity.Stock;
import com.kemenkes.epu.entity.view.ViewAccountBalance;
import com.kemenkes.epu.entity.view.ViewActivity;
import com.kemenkes.epu.service.AccountService;
import com.kemenkes.epu.service.ActivityService;
import com.kemenkes.epu.service.BalanceService;
import com.kemenkes.epu.service.StockService;
import com.kemenkes.epu.ui.validator.StockValidator;

@Controller
@RequestMapping("/stock")
public class StockController extends BaseController {
	@Autowired
	private ActivityService activityService;

	@Autowired
	private StockService stockService;

	@Autowired
	private BalanceService balanceService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private StockValidator stockValidator;

	@RequestMapping("/activity")
	public String activityList(@ModelAttribute Activity activity, ModelMap map, HttpServletRequest request) {

		if (activity.getYear() == null) {
			activity.setYear(DateTime.now().getYear());
		}
		activity.setType(ActivityType.STOCK);

		PaginatedListImpl paginatedList = new PaginatedListImpl(request);
		PaginatedList page = activityService.searchView(activity, paginatedList);
		map.put("page", page);

		map.put("activity", activity);
		return "stock/activity/list";
	}

	@RequestMapping("/activity/{code}/")
	public String index(@PathVariable String code, ModelMap map, HttpServletRequest request) {

		Activity activity = activityService.findByCode(code);

		Stock stock = new Stock();
		stock.setActivity(activity);

		PaginatedListImpl paginatedList = new PaginatedListImpl(request);
		PaginatedList page = stockService.search(stock, paginatedList);

		ViewActivity view = activityService.findViewByCode(code);

		map.put("page", page);
		map.put("activity", activity);
		map.put("stock", stock);
		map.put("view", view);

		return "stock/list";
	}

	@RequestMapping("/preview")
	public String preview(@ModelAttribute Stock stock, BindingResult result, ModelMap map, HttpServletRequest request) {

		if (stock.getActivity() == null) {
			return "redirect:/stock/activity";
		}

		Activity activity = activityService.findByCode(stock.getActivity().getCode());

		stock.setActivity(activity);

		map.put("activity", activity);
		stockValidator.validate(stock, result);
		if (result.hasErrors()) {
			Stock search = new Stock();
			search.setActivity(activity);
			PaginatedListImpl paginatedList = new PaginatedListImpl(request);
			PaginatedList page = stockService.search(search, paginatedList);
			map.put("page", page);
			ViewActivity view = activityService.findViewByCode(activity.getCode());
			map.put("view", view);
			return "stock/list";
		}

		ViewAccountBalance accountBalance = accountService.getViewAccountBalance(stock.getAccount().getAccountNumber());
		map.put("accountBalance", accountBalance);

		return "stock/preview";
	}

	@RequestMapping("/create")
	public String create(@ModelAttribute Stock stock, BindingResult result, ModelMap map, RedirectAttributes attributes, @RequestParam(required = false) boolean cancel, HttpServletRequest request) {

		if (stock.getActivity() == null) {
			return "redirect:/stock/activity";
		}
		Activity activity = activityService.findByCode(stock.getActivity().getCode());
		stock.setActivity(activity);

		map.put("activity", activity);

		stockValidator.validate(stock, result);
		if (result.hasErrors() || cancel) {
			Stock search = new Stock();
			search.setActivity(activity);
			PaginatedListImpl paginatedList = new PaginatedListImpl(request);
			PaginatedList page = stockService.search(search, paginatedList);
			ViewActivity view = activityService.findViewByCode(activity.getCode());
			map.put("view", view);
			map.put("page", page);
			return "stock/list";
		}

		stock.setCreatedBy(SpringSecurityUtil.getUsername());
		stock.setCreatedDate(new Date());
		stockService.create(stock);

		addMessage(attributes, "stock.create.success", MessageType.SUCCESS);
		return "redirect:/stock/activity/" + activity.getCode() + "/";
	}

}
