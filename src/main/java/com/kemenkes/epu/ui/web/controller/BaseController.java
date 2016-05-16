package com.kemenkes.epu.ui.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kemenkes.epu.common.util.Constant;
import com.kemenkes.epu.common.util.Message;
import com.kemenkes.epu.common.util.MessageType;

public abstract class BaseController {
	protected final String MESSAGE = "message";
	private static Logger logger = Logger.getLogger(BaseController.class);

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);

		SimpleDateFormat dateFormat=new SimpleDateFormat(Constant.DATE_FORMAT);
		dateFormat.setLenient(false);
		CustomDateEditor customDateEditor = new CustomDateEditor(dateFormat, false);
		binder.registerCustomEditor(String.class, stringtrimmer);
		binder.registerCustomEditor(Date.class, customDateEditor);
	}

	protected void addMessage(RedirectAttributes attributes, String text, MessageType type) {
		attributes.addFlashAttribute(MESSAGE, new Message(text, type));
	}

	@ExceptionHandler({ DataAccessException.class })
	protected ModelAndView dataAccessException(DataAccessException dae, HttpServletRequest request) {
		logger.error(dae.getMessage(), dae);
		ModelAndView mav = new ModelAndView();
		Throwable throwable = dae.getCause();
		List<Message> messages = new ArrayList<Message>();
		while (throwable != null) {
			messages.add(new Message(throwable.getLocalizedMessage(), MessageType.ERROR));
			throwable = throwable.getCause();
		}
		mav.addObject("messages", messages);
		mav.addObject("url", request.getRequestURL());
		mav.setViewName("exception/data-access-exception");
		return mav;
	}

	@ExceptionHandler(Exception.class)
	protected ModelAndView exception(Exception e, HttpServletRequest request) {
		logger.error(e.getMessage(), e);
		ModelAndView mav = new ModelAndView();
		Throwable throwable = e.getCause();
		List<Message> messages = new ArrayList<Message>();
		while (throwable != null) {
			messages.add(new Message(throwable.getLocalizedMessage(), MessageType.ERROR));
			throwable = throwable.getCause();
		}
		mav.addObject("messages", messages);
		mav.addObject("url", request.getRequestURL());
		mav.setViewName("exception/exception");
		return mav;
	}
}
