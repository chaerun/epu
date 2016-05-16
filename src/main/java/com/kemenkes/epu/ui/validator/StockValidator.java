package com.kemenkes.epu.ui.validator;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.kemenkes.epu.common.util.Constant;
import com.kemenkes.epu.entity.ActivityType;
import com.kemenkes.epu.entity.Stock;
import com.kemenkes.epu.entity.view.ViewActivity;
import com.kemenkes.epu.service.AccountService;
import com.kemenkes.epu.service.ActivityService;
import com.kemenkes.epu.service.BalanceService;

@Component
public class StockValidator implements Validator {

	@Autowired
	private AccountService accountService;

	@Autowired
	private BalanceService balanceService;

	@Autowired
	private ActivityService activityService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Stock.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Stock stock = (Stock) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "activity.code", "field.required", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "amount", "field.required", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "field.required", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.accountNumber", "field.required", "field.required");

		BigDecimal balance = balanceService.getTotalBalance();

		BigDecimal accountBalance = BigDecimal.ZERO;

		if (stock.getAccount() != null) {
			if (StringUtils.isNotBlank(stock.getAccount().getAccountNumber())) {
				accountBalance = accountService.getTotalBalance(stock.getAccount().getAccountNumber());
			}
		}
		
		if(!ActivityType.STOCK.equals(stock.getActivity().getType())){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "activity.code", "field.required", "field.required");
		}

		if (stock.getAmount() != null) {

			if (stock.getAmount().compareTo(balance) == 1) {
				errors.rejectValue("amount", "stock.amount.invalid.realbalance", new Object[] { Constant.getFormatCurrency(balance) }, "stock.amount.invalid.realbalance");
			}

			if (stock.getAmount().compareTo(accountBalance) == 1) {
				errors.rejectValue("amount", "stock.amount.invalid.accountbalance", new Object[] { Constant.getFormatCurrency(accountBalance) }, "stock.amount.invalid.accountbalance");
			}

			ViewActivity view = activityService.findViewByCode(stock.getActivity().getCode());
			if (view != null) {
				if (stock.getAmount().compareTo(view.getBalance()) == 1) {
					errors.rejectValue("amount", "stock.amount.invalid");
				}
			}
		}

	}
}
