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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kemenkes.epu.common.entity.DateFilter;
import com.kemenkes.epu.common.util.MessageType;
import com.kemenkes.epu.common.util.SpringSecurityUtil;
import com.kemenkes.epu.entity.Account;
import com.kemenkes.epu.entity.AccountDetail;
import com.kemenkes.epu.entity.BalanceType;
import com.kemenkes.epu.service.AccountDetailService;
import com.kemenkes.epu.service.AccountService;
import com.kemenkes.epu.ui.validator.AccountDetailValidator;
import com.kemenkes.epu.ui.validator.DateFilterValidator;

@Controller
@RequestMapping("/accountdetail")
public class AccountDetailController extends BaseController {
	@Autowired
	private AccountDetailService accountDetailService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private AccountDetailValidator accountDetailValidator;
	
	@RequestMapping("/{accountNumber}/")
	public String index(@PathVariable String accountNumber, @ModelAttribute AccountDetail accountDetail, @ModelAttribute DateFilter dateFilter, BindingResult result, ModelMap map, HttpServletRequest request) {

		new DateFilterValidator().validate(dateFilter, result);
		accountDetail.setAccount(new Account(accountNumber));

		PaginatedListImpl paginatedList = new PaginatedListImpl(request);
		PaginatedList page = accountDetailService.search(accountDetail, dateFilter, paginatedList);
		map.put("page", page);

		map.put("credit", accountDetailService.sum(BalanceType.CR, accountDetail, dateFilter));
		map.put("debit", accountDetailService.sum(BalanceType.DB, accountDetail, dateFilter));

		return "accountdetail/list";
	}
	
	@RequestMapping("/view")
	public String view( @ModelAttribute DateFilter dateFilter, BindingResult result, ModelMap map, HttpServletRequest request) {

		new DateFilterValidator().validate(dateFilter, result);

		PaginatedListImpl paginatedList = new PaginatedListImpl(request);
		PaginatedList page = accountDetailService.search(null, dateFilter, paginatedList);
		map.put("page", page);

		map.put("credit", accountDetailService.sum(BalanceType.CR, null, dateFilter));
		map.put("debit", accountDetailService.sum(BalanceType.DB, null, dateFilter));

		return "accountdetail/view";
	}
	

	@RequestMapping("/precreate/{accountNumber}/")
	public String precreate(@PathVariable String accountNumber, ModelMap map) {

		AccountDetail accountDetail = new AccountDetail();

		Account account = accountService.findById(accountNumber);

		accountDetail.setAccount(account);
		map.put("accountDetail", accountDetail);
		return "accountdetail/precreate";
	}

	@RequestMapping("/preview/{accountNumber}/")
	public String preview(@PathVariable String accountNumber, @ModelAttribute AccountDetail accountDetail, BindingResult result) {

		accountDetailValidator.validate(accountDetail, result);
		if (result.hasErrors()) {
			return "accountdetail/precreate";
		}
		accountDetail.setAccount(accountService.findById(accountNumber));
		return "accountdetail/preview";
	}

	@RequestMapping("/create/{accountNumber}/")
	public String create(@PathVariable String accountNumber, @ModelAttribute AccountDetail accountDetail, BindingResult result, RedirectAttributes attributes, @RequestParam(required = false) boolean cancel) {

		accountDetail.setAccount(accountService.findById(accountNumber));
		if (cancel) {
			return "accountdetail/precreate";
		}

		accountDetailValidator.validate(accountDetail, result);
		if (result.hasErrors()) {
			return "accountdetail/precreate";
		}
		accountDetail.setCreatedBy(SpringSecurityUtil.getUsername());
		accountDetail.setCreatedDate(new Date());
		accountDetailService.create(accountDetail);
		addMessage(attributes, "accountDetail.create.success", MessageType.SUCCESS);
		return "redirect:/accountdetail/" + accountNumber + "/";
	}

}
