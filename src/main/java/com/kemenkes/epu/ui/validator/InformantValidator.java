package com.kemenkes.epu.ui.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.kemenkes.epu.entity.Informant;
import com.kemenkes.epu.entity.Journey;
import com.kemenkes.epu.entity.JourneyType;

public class InformantValidator  implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Informant.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Informant informant = (Informant) target;

		Journey journey = informant.getJourney();
		
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "journey.id", "field.required", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nip", "field.required", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "grade", "field.required", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "position", "field.required", "field.required");
		
		if (JourneyType.FULLBOARD.equals(journey.getType()) || JourneyType.JOURNEY_OUT.equals(journey.getType())) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fromLocation", "field.required", "field.required");
		}
		
		if (journey.getInformants().contains(informant)) {
			errors.rejectValue("name", "informant.journey.already.registered");
		}
		
		if (informant.getStartDate() != null && informant.getEndDate() != null) {

			if (informant.getStartDate().getTime() < journey.getStartDate().getTime()) {

				errors.rejectValue("startDate", "participant.journey.date.valid.before");

			}
			if (informant.getStartDate().getTime() > journey.getEndDate().getTime()) {
				errors.rejectValue("startDate", "participant.journey.date.valid.after");
			}

			if (informant.getEndDate().getTime() < journey.getStartDate().getTime()) {
				errors.rejectValue("endDate", "participant.journey.date.valid.before");
			}

			if (informant.getEndDate().getTime() > journey.getEndDate().getTime()) {
				errors.rejectValue("endDate", "participant.journey.date.valid.after");
			}

		}
	}

}
