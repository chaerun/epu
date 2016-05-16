package com.kemenkes.epu.service;

import java.util.List;

import com.kemenkes.epu.entity.ParticipantJourney;

public interface ParticipantJourneyService {
	public ParticipantJourney findById(String id);

	public void delete(ParticipantJourney participantJourney);

	public void create(ParticipantJourney participantJourney);

	public void update(ParticipantJourney participantJourney);

	public List<ParticipantJourney> search(ParticipantJourney participantJourney);

	public ParticipantJourney used(ParticipantJourney participantJourney);
}
