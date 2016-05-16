package com.kemenkes.epu.ui.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.kemenkes.epu.entity.ParticipantJourney;

public class ModeratorValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ParticipantJourney.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ParticipantJourney participantJourney = (ParticipantJourney) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "field.required", "field.required");

		int hours = participantJourney.getModeratorHours();
		
		if(participantJourney.getJourney().getModerators().contains(participantJourney)){
			errors.rejectValue("id", "moderator.journey.already.registered");
		}

		if (hours < 0 || hours > 24) {

			errors.reject("moderatorHours", "validation.hour.range");
		}

		int percent = participantJourney.getModeratorPpn();

		if (percent < 0 || percent > 100) {
			errors.reject("moderatorPpn", "validation.percent.range");
		}

	}

}
