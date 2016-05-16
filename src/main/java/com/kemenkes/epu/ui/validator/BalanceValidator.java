package com.kemenkes.epu.ui.validator;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.kemenkes.epu.entity.Balance;
import com.kemenkes.epu.entity.BalanceType;
import com.kemenkes.epu.service.BalanceService;

@Component
public class BalanceValidator implements Validator {

	@Autowired
	private BalanceService balanceService;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(Balance.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Balance balance = (Balance) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "field.required", "field.required");

		if (balance.getType() == null) {
			errors.rejectValue("type", "field.required");
		}

		if (balance.getAmount() == null) {
			errors.rejectValue("amount", "field.required");
		}

		BigDecimal total = balanceService.getTotalBalance();

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
