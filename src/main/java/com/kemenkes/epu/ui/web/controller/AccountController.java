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

import com.kemenkes.epu.common.util.MessageType;
import com.kemenkes.epu.common.util.SpringSecurityUtil;
import com.kemenkes.epu.entity.Account;
import com.kemenkes.epu.service.AccountService;
import com.kemenkes.epu.ui.validator.AccountValidator;

@Controller
@RequestMapping("/account")
public class AccountController extends BaseController {

	@Autowired
	private AccountService accountService;

	@RequestMapping
	public String index(ModelMap map, HttpServletRequest request) {
		PaginatedListImpl paginatedList = new PaginatedListImpl(request);
		PaginatedList page = accountService.searchView(null, paginatedList);
		map.put("page", page);
		return "account/list";
	}

	@RequestMapping("/precreate")
	public String precreate(ModelMap map) {
		map.put("account", new Account());
		return "account/precreate";
	}

	@RequestMapping("/preview/create")
	public String previewCreate(@ModelAttribute Account account, BindingResult result) {
		new AccountValidator().validate(account, result);
		if (result.hasErrors()) {
			return "account/precreate";
		}
		return "account/preview-create";
	}

	@RequestMapping("/preview/edit")
	public String previewEdit(@ModelAttribute Account account, BindingResult result) {
		new AccountValidator().validate(account, result);
		if (result.hasErrors()) {
			return "account/preedit";
		}
		return "account/preview-edit";
	}

	@RequestMapping("/preedit/{accountNumber}/")
	public String preedit(@PathVariable String accountNumber,ModelMap map) {
		map.put("account",accountService.findById(accountNumber));
		return "account/preedit";
	}

	@RequestMapping("/create")
	public String create(@ModelAttribute Account account, BindingResult result, RedirectAttributes attributes, @RequestParam(required = false) boolean cancel) {

		if (cancel) {
			return "account/precreate";
		}

		new AccountValidator().validate(account, result);
		if (result.hasErrors()) {
			return "account/precreate";
		}
		account.setCreatedBy(SpringSecurityUtil.getUsername());
		account.setCreatedDate(new Date());
		accountService.create(account);
		addMessage(attributes, "account.create.success", MessageType.SUCCESS);
		return "redirect:/account";
	}

	@RequestMapping("/edit")
	public String edit(@ModelAttribute Account account, BindingResult result, RedirectAttributes attributes, @RequestParam(required = false) boolean cancel) {

		if (cancel) {
			return "account/preedit";
		}

		new AccountValidator().validate(account, result);
		if (result.hasErrors()) {
			return "account/preedit";
		}
		
		Account newAccount = accountService.findById(account.getAccountNumber());
		newAccount.setDescription(account.getDescription());
		newAccount.setName(account.getName());
		newAccount.setUpdatedBy(SpringSecurityUtil.getUsername());
		newAccount.setUpdatedDate(new Date());
		
		accountService.edit(newAccount);
		addMessage(attributes, "account.edit.success", MessageType.SUCCESS);
		return "redirect:/account";
	}

}
