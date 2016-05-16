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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kemenkes.epu.common.entity.DateFilter;
import com.kemenkes.epu.common.util.MessageType;
import com.kemenkes.epu.common.util.SpringSecurityUtil;
import com.kemenkes.epu.entity.Balance;
import com.kemenkes.epu.entity.BalanceType;
import com.kemenkes.epu.service.BalanceService;
import com.kemenkes.epu.ui.validator.BalanceValidator;
import com.kemenkes.epu.ui.validator.DateFilterValidator;

@Controller
@RequestMapping("/balance")
public class BalanceController extends BaseController {

	@Autowired
	private BalanceService balanceService;
	
	@Autowired
	private BalanceValidator balanceValidator;

	@RequestMapping
	public String index(@ModelAttribute DateFilter dateFilter, BindingResult result,ModelMap map, HttpServletRequest request) {
		new DateFilterValidator().validate(dateFilter, result);
		if (result.hasErrors()) {
			// Do nothing.
		}
		
		PaginatedListImpl paginatedList = new PaginatedListImpl(request);
		PaginatedList page = balanceService.search(null, dateFilter, paginatedList);
		map.put("page", page);
		
		map.put("credit",balanceService.sum(BalanceType.CR, dateFilter));
		map.put("debit",balanceService.sum(BalanceType.DB, dateFilter));
		

		map.put("total",balanceService.getTotalBalance());
		
		return "balance/list";
	}

	@RequestMapping("/precreate")
	public String precreate(ModelMap map) {
		map.put("balance", new Balance());
		return "balance/precreate";
	}

	@RequestMapping("/preview")
	public String preview(@ModelAttribute Balance balance, BindingResult result) {
		balanceValidator.validate(balance, result);
		if (result.hasErrors()) {
			return "balance/precreate";
		}

		return "balance/preview";
	}

	@RequestMapping("/create")
	public String create(@ModelAttribute Balance balance, BindingResult result, RedirectAttributes attributes, @RequestParam(required = false) boolean cancel) {
		if (cancel) {
			return "balance/precreate";
		}

		balanceValidator.validate(balance, result);
		if (result.hasErrors()) {
			return "balance/precreate";
		}
		balance.setCreatedBy(SpringSecurityUtil.getUsername());
		balance.setCreatedDate(new Date());
		balanceService.create(balance);
		addMessage(attributes, "balance.create.success", MessageType.SUCCESS);
		return "redirect:/balance";
	}

}
