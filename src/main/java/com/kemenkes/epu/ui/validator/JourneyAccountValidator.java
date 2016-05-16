package com.kemenkes.epu.ui.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.kemenkes.epu.entity.Journey;
import com.kemenkes.epu.entity.JourneyType;

public class JourneyAccountValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return Journey.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Journey journey = (Journey) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "participantAccount.accountNumber", "field.required", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "moderatorAccount.accountNumber", "field.required", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "informantAccount.accountNumber", "field.required", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "informantTransportAccount.accountNumber", "field.required", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "requirementAccount.accountNumber", "field.required", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "returnAmountAccount.accountNumber", "field.required", "field.required");

		if (JourneyType.FULLBOARD.equals(journey.getType()) || JourneyType.FULLDAY.equals(journey.getType())) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "packetMeetingAccount.accountNumber", "field.required", "field.required");
		}
		if (JourneyType.JOURNEY_IN.equals(journey.getType()) || JourneyType.JOURNEY_OUT.equals(journey.getType())) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "innAccount.accountNumber", "field.required", "field.required");
		}

	}
}
