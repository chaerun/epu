package com.kemenkes.epu.ui.validator;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.kemenkes.epu.entity.Account;
import com.kemenkes.epu.entity.AccountDetail;
import com.kemenkes.epu.entity.BalanceType;
import com.kemenkes.epu.service.AccountService;
import com.kemenkes.epu.service.BalanceService;

@Component
public class AccountDetailValidator implements Validator {

	@Autowired
	private AccountService accountService;
	
	static Logger logger=Logger.getLogger(AccountDetailValidator.class);

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(AccountDetail.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		AccountDetail balance = (AccountDetail) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "field.required", "field.required");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.accountNumber", "field.required", "field.required");

		if (balance.getType() == null) {
			errors.rejectValue("type", "field.required");
		}

		if (balance.getAmount() == null) {
			errors.rejectValue("amount", "field.required");
		}

		BigDecimal total = BigDecimal.ZERO;

		Account account = balance.getAccount();

		if (account != null) {
			try {

				total = accountService.getTotalBalance(account.getAccountNumber());
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}

		}
		BigDecimal amount = balance.getAmount();
		if (amount != null) {
			if (BalanceType.CR.equals(balance.getType())) {
				if (amount.compareTo(total) == 1) {
					errors.rejectValue("amount", "balance.amount.invalid.min");
				}
			}
		}

	}

}
