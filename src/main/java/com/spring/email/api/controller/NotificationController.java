package com.spring.email.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.email.api.dto.EmailRequest;
import com.spring.email.api.dto.EmailResponse;
import com.spring.email.api.service.EmailService;

@RestController
@RequestMapping(value = "/Notification")
public class NotificationController {

	@Autowired
	private EmailService service;

	@RequestMapping(value = "/sendEmail")
	public EmailResponse sendEmail(@RequestBody EmailRequest request) {
		EmailResponse response = service.sendMail(request.getTo(),
				request.getSubject(), request.getMailBody());
		return response;
	}

	@RequestMapping(value = "/sendEmailWithAttachement")
	public EmailResponse sendEmailWithAttachement(
			@RequestBody EmailRequest request) {
		EmailResponse response = service.sendEmailWithAttachement(
				request.getTo(), request.getSubject(), request.getMailBody(),
				request.getFileName(), request.getFilePath());
		return response;
	}
}
