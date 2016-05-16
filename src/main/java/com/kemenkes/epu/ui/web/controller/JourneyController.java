package com.kemenkes.epu.ui.web.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.displaytag.pagination.PaginatedList;
import org.displaytag.pagination.PaginatedListImpl;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kemenkes.epu.common.entity.JourneyTypeForm;
import com.kemenkes.epu.common.util.Constant;
import com.kemenkes.epu.common.util.MessageType;
import com.kemenkes.epu.common.util.SpringSecurityUtil;
import com.kemenkes.epu.entity.Activity;
import com.kemenkes.epu.entity.ActivityType;
import com.kemenkes.epu.entity.Balance;
import com.kemenkes.epu.entity.BalanceType;
import com.kemenkes.epu.entity.Journey;
import com.kemenkes.epu.entity.JourneyStatus;
import com.kemenkes.epu.entity.JourneyType;
import com.kemenkes.epu.entity.User;
import com.kemenkes.epu.entity.view.ViewActivity;
import com.kemenkes.epu.service.AccountService;
import com.kemenkes.epu.service.ActivityService;
import com.kemenkes.epu.service.BalanceService;
import com.kemenkes.epu.service.JourneyService;
import com.kemenkes.epu.ui.validator.JourneyTypeFormValidator;
import com.kemenkes.epu.ui.validator.JourneyValidator;

@Controller
@RequestMapping("/journey")
public class JourneyController extends BaseController {

	@Autowired
	private ActivityService activityService;

	@Autowired
	private JourneyService journeyService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private BalanceService balanceService;

	@RequestMapping("/activity")
	public String activityList(@ModelAttribute Activity activity, ModelMap map, HttpServletRequest request,HttpSession session) {

		if (activity.getYear() == null) {
			activity.setYear(DateTime.now().getYear());
		}
		activity.setType(ActivityType.JOURNEY);

		User sessionUser = (User) session.getAttribute(Constant.SESSION_USER);
		activity.setSubdivision(sessionUser.getSubdivision());
		
		PaginatedListImpl paginatedList = new PaginatedListImpl(request);
		PaginatedList page = activityService.search(activity, paginatedList);
		map.put("page", page);

		map.put("activity", activity);
		return "journey/activity/list";
	}

	@RequestMapping("/detail/{id}/")
	public String detail(@PathVariable String id, ModelMap map) {
		Journey journey = journeyService.findById(id);
		map.put("journey", journey);
		map.put("activity", journey.getActivity());

		Balance search = new Balance();
		search.setAdditionalReferenceId(journey.getId());
		search.setAdditionalReferenceTable(Constant.REFF_TABLE_JOURNEY);
		search.setType(BalanceType.CR);
		List<Balance> balances = balanceService.search(search);

		if (balances.size() > 0) {
			map.put("received", balances.get(0).getAmount());
		}

		return "journey/detail";
	}

	@RequestMapping("/activity/{code}/")
	public String index(@PathVariable String code, ModelMap map, HttpServletRequest request) {

		Activity activity = activityService.findByCode(code);

		Journey journey = new Journey();
		journey.setActivity(activity);

		PaginatedListImpl paginatedList = new PaginatedListImpl(request);
		PaginatedList page = journeyService.search(journey, paginatedList);
		map.put("page", page);
		map.put("activity", activity);
		map.put("view", activityService.findViewByCode(journey.getActivity().getCode()));
		return "journey/list";
	}

	@RequestMapping("/precreate/type/{code}")
	public String precreateType(@PathVariable String code, @ModelAttribute JourneyTypeForm journeyTypeForm, ModelMap map, HttpServletRequest request) {

		Activity activity = activityService.findByCode(code);
		Journey journey = new Journey();
		journey.setActivity(activity);

		JourneyType[] types = JourneyType.values();

		map.put("types", types);

		journeyTypeForm.setActivity(activity);
		map.put("journeyTypeForm", journeyTypeForm);
		map.put("activity", activity);

		return "journey/precreate-type";
	}

	@RequestMapping("/precreate/form")
	public String precreateForm(@ModelAttribute JourneyTypeForm journeyTypeForm, BindingResult result, ModelMap map) {

		if (journeyTypeForm.getActivity() == null) {
			return "redirect:/journey/activity";
		}

		new JourneyTypeFormValidator().validate(journeyTypeForm, result);
		if (result.hasErrors()) {
			Activity activity = activityService.findByCode(journeyTypeForm.getActivity().getCode());
			Journey journey = new Journey();
			journey.setActivity(activity);
			JourneyType[] types = JourneyType.values();

			map.put("types", types);
			map.put("activity", activity);
			return "journey/precreate-type";
		}

		Journey journey = new Journey();
		journey.setType(journeyTypeForm.getType());
		journey.setActivity(journeyTypeForm.getActivity());
		Activity activity = activityService.findByCode(journeyTypeForm.getActivity().getCode());
		map.put("activity", activity);

		map.put("journey", journey);
		return "journey/precreate-form";
	}

	@RequestMapping("/preview/form")
	public String previewForm(@ModelAttribute Journey journey, BindingResult result, ModelMap map, RedirectAttributes attributes, @RequestParam(required = false) boolean cancel) {

		if (journey.getActivity() == null) {
			return "redirect:/journey/activity";
		}

		if (cancel) {
			Activity activity = activityService.findByCode(journey.getActivity().getCode());
			journey.setActivity(activity);
			JourneyType[] types = JourneyType.values();
			JourneyTypeForm journeyTypeForm = new JourneyTypeForm();
			journeyTypeForm.setActivity(activity);
			journeyTypeForm.setType(journey.getType());

			map.put("journeyTypeForm", journeyTypeForm);
			map.put("activity", activity);
			map.put("types", types);
			return "journey/precreate-type";
		}

		new JourneyValidator().validate(journey, result);

		if (result.hasErrors()) {
			Activity activity = activityService.findByCode(journey.getActivity().getCode());
			map.put("activity", activity);
			return "journey/precreate-form";
		}

		Activity activity = activityService.findByCode(journey.getActivity().getCode());
		journey.setActivity(activity);
		if (JourneyType.FULLBOARD.equals(journey.getType()) || JourneyType.FULLDAY.equals(journey.getType())) {
			journey.setPacketMeetingAccount(accountService.findById(journey.getPacketMeetingAccount().getAccountNumber()));
		}
		if (JourneyType.JOURNEY_IN.equals(journey.getType()) || JourneyType.JOURNEY_OUT.equals(journey.getType())) {
			journey.setInnAccount(accountService.findById(journey.getInnAccount().getAccountNumber()));
		}
		journey.setParticipantAccount(accountService.findById(journey.getParticipantAccount().getAccountNumber()));
		journey.setModeratorAccount(accountService.findById(journey.getModeratorAccount().getAccountNumber()));
		journey.setInformantAccount(accountService.findById(journey.getInformantAccount().getAccountNumber()));
		journey.setInformantTransportAccount(accountService.findById(journey.getInformantTransportAccount().getAccountNumber()));
		journey.setRequirementAccount(accountService.findById(journey.getRequirementAccount().getAccountNumber()));

		journey.setReturnAmountAccount(accountService.findById(journey.getReturnAmountAccount().getAccountNumber()));

		return "journey/preview-form";
	}

	@RequestMapping("/create/form")
	public String createForm(@ModelAttribute Journey journey, BindingResult result, ModelMap map, RedirectAttributes attributes, @RequestParam(required = false) boolean cancel) {

		if (journey.getActivity() == null) {
			return "redirect:/journey/activity";
		}

		if (cancel) {
			Activity activity = activityService.findByCode(journey.getActivity().getCode());
			journey.setActivity(activity);
			map.put("activity", activity);
			return "journey/precreate-form";
		}

		new JourneyValidator().validate(journey, result);
		if (result.hasErrors()) {
			return "journey/precreate-form";
		}
		journey.setCreatedBy(SpringSecurityUtil.getUsername());
		journey.setCreatedDate(new Date());
		journey.setStatus(JourneyStatus.CREATE_NEW);
		journeyService.create(journey);

		return "redirect:/journey/create/success/" + journey.getActivity().getCode() + "/";
	}

	@RequestMapping("/create/success/{code}")
	public String success(@PathVariable String code, ModelMap map) {
		Activity activity = activityService.findByCode(code);

		map.put("activity", activity);
		return "journey/success-form";
	}

	@RequestMapping("/release/{id}/")
	public String release(@PathVariable String id, ModelMap map, RedirectAttributes attributes) {

		Journey journey = journeyService.findById(id);

		Journey search = new Journey();

		search.setActivity(journey.getActivity());
		search.setStatus(JourneyStatus.PENDING);

		List<Journey> journeys = journeyService.search(search);

		ViewActivity view = activityService.findViewByCode(journey.getActivity().getCode());

		if (view.getBalance().compareTo(journey.getTotalAmountAll()) == -1) {
			addMessage(attributes, "Saldo Kegiatan Tidak mencukupi.", MessageType.ERROR);
			return "redirect:/journey/detail/" + journey.getId() + "/";
		}

		if (journeys.size() > 0) {

			addMessage(attributes, "Masih ada status perjalanan yang 'Pending'.", MessageType.ERROR);
			return "redirect:/journey/detail/" + journey.getId() + "/";
		}

		if (JourneyStatus.CREATE_NEW.equals(journey.getStatus())) {
			journey.setStatus(JourneyStatus.PENDING);
			journey.setUpdatedBy(SpringSecurityUtil.getUsername());
			journey.setUpdatedDate(new Date());

			journeyService.update(journey);
		}
		addMessage(attributes, "Perjalanan Telah Berhasil di ajukan.", MessageType.SUCCESS);
		return "redirect:/journey/activity/" + journey.getActivity().getCode() + "/";
	}

	@RequestMapping("/releasecomplete/{id}/")
	public String releasecomplete(@PathVariable String id, ModelMap map, RedirectAttributes attributes) {

		Journey journey = journeyService.findById(id);

		Balance search = new Balance();
		search.setAdditionalReferenceId(journey.getId());
		search.setAdditionalReferenceTable(Constant.REFF_TABLE_JOURNEY);
		search.setType(BalanceType.CR);
		List<Balance> balances = balanceService.search(search);

		BigDecimal receivedAmount = BigDecimal.ZERO;

		if (balances.size() > 0) {
			receivedAmount = balances.get(0).getAmount();
		}

		if (journey.getTotalAmountAll().compareTo(receivedAmount) == 1) {
			addMessage(attributes, "Biaya Perjalanan melebihi dana yang diterima", MessageType.ERROR);
			return "redirect:/journey/detail/" + journey.getId() + "/";
		}


		if (JourneyStatus.IN_PROGRESS.equals(journey.getStatus())) {
			journey.setStatus(JourneyStatus.PENDING_COMPLETE);
			journey.setUpdatedBy(SpringSecurityUtil.getUsername());
			journey.setUpdatedDate(new Date());

			journeyService.update(journey);
		}
		addMessage(attributes, "Perjalanan Telah Berhasil di Selesaikan, Menunggu Persetujuan Super Admin untuk Pengembalian Dana.", MessageType.SUCCESS);
		return "redirect:/journey/activity/" + journey.getActivity().getCode() + "/";
	}

}
