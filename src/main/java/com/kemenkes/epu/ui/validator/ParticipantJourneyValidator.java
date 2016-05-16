package com.kemenkes.epu.ui.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.kemenkes.epu.entity.Journey;
import com.kemenkes.epu.entity.JourneyType;
import com.kemenkes.epu.entity.ParticipantJourney;
import com.kemenkes.epu.service.ParticipantJourneyService;

@Component
public class ParticipantJourneyValidator implements Validator {

	@Autowired
	private ParticipantJourneyService participantJourneyService;

	@Override
	public boolean supports(Class<?> clazz) {
		return ParticipantJourney.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		

		ParticipantJourney participantJourney = (ParticipantJourney) target;
		
		System.out.println(participantJourney);

		Journey journey = participantJourney.getJourney();

		if (participantJourney.isFlag()) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required", "field.required");
			participantJourney.setParticipant(null);
		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "participant.code", "field.required", "field.required");
			if (journey.getParticipants().contains(participantJourney)) {
				errors.rejectValue("participant.code", "participant.journey.already.registered");
			}
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startDate", "field.required", "field.required");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endDate", "field.required", "field.required");

		if (JourneyType.FULLBOARD.equals(journey.getType()) || JourneyType.JOURNEY_OUT.equals(journey.getType())) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fromLocation", "field.required", "field.required");
		}

	

		if (participantJourney.getStartDate() != null && participantJourney.getEndDate() != null) {

			if (participantJourney.getStartDate().getTime() < journey.getStartDate().getTime()) {

				errors.rejectValue("startDate", "participant.journey.date.valid.before");

			}
			if (participantJourney.getStartDate().getTime() > journey.getEndDate().getTime()) {
				errors.rejectValue("startDate", "participant.journey.date.valid.after");
			}

			if (participantJourney.getEndDate().getTime() < journey.getStartDate().getTime()) {
				errors.rejectValue("endDate", "participant.journey.date.valid.before");
			}

			if (participantJourney.getEndDate().getTime() > journey.getEndDate().getTime()) {
				errors.rejectValue("endDate", "participant.journey.date.valid.after");
			}

		}
		if (!participantJourney.isFlag()) {
			ParticipantJourney used = participantJourneyService.used(participantJourney);
			if (used != null) {
				try {

					errors.rejectValue("participant.code", "participant.journey.already.used", new String[] { used.getJourney().getActivity().getSubdivision().getName() }, "participant.journey.already.used");
				} catch (Exception e) {
					e.printStackTrace();
					errors.rejectValue("participant.code", "participant.journey.already.used");
				}

			}
		}
	}

}
