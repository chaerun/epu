package com.kemenkes.epu.service;

import java.util.List;

import org.displaytag.pagination.PaginatedList;
import org.displaytag.pagination.PaginatedListImpl;

import com.kemenkes.epu.entity.Participant;

public interface ParticipantService {

	public Participant findByCode(String id);

	public List<Participant> findAll();

	public void delete(Participant participant);

	public void create(Participant participant);

	public void edit(Participant participant);

	public PaginatedList search(Participant participant, PaginatedListImpl paginatedList);
}
