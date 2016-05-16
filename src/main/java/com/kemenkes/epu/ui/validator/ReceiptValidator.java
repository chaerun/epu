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
import com.kemenkes.epu.entity.Receipt;
import com.kemenkes.epu.entity.view.ViewActivity;
import com.kemenkes.epu.service.AccountService;
import com.kemenkes.epu.service.ActivityService;
import com.kemenkes.epu.service.BalanceService;

@Component
public class ReceiptValidator implements Validator {

	@Autowired
	private AccountService accountService;

	@Autowired
	private BalanceService balanceService;

	@Autowired
	private ActivityService activityService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Receipt.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Receipt receipt = (Receipt) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "activity.code", "field.required", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "amount", "field.required", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "field.required", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.accountNumber", "field.required", "field.required");

		BigDecimal balance = balanceService.getTotalBalance();

		BigDecimal accountBalance = BigDecimal.ZERO;

		if (receipt.getAccount() != null) {
			if (StringUtils.isNotBlank(receipt.getAccount().getAccountNumber())) {
				accountBalance = accountService.getTotalBalance(receipt.getAccount().getAccountNumber());
			}
		}
		
		if(!ActivityType.RECEIPT.equals(receipt.getActivity().getType())){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "activity.code", "field.required", "field.required");
		}


		if (receipt.getAmount() != null) {

			if (receipt.getAmount().compareTo(balance) == 1) {
				errors.rejectValue("amount", "receipt.amount.invalid.realbalance", new Object[] { Constant.getFormatCurrency(balance) }, "receipt.amount.invalid.realbalance");
			}

			if (receipt.getAmount().compareTo(accountBalance) == 1) {
				errors.rejectValue("amount", "receipt.amount.invalid.accountbalance", new Object[] { Constant.getFormatCurrency(accountBalance) }, "receipt.amount.invalid.accountbalance");
			}

			ViewActivity view = activityService.findViewByCode(receipt.getActivity().getCode());
			if (view != null) {
				if (receipt.getAmount().compareTo(view.getBalance()) == 1) {
					errors.rejectValue("amount", "receipt.amount.invalid");
				}
			}
		}

	}
}
