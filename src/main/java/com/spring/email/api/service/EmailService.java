package com.spring.email.api.service;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.spring.email.api.dto.EmailResponse;

/**
 * @author BASANTA
 *
 */
@Service
public class EmailService {

	private JavaMailSender javaMailSender;

	@Autowired
	public EmailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	// Plain Text mail
	public EmailResponse sendMail(String toEmail, String subject, String message) {
		EmailResponse response = new EmailResponse();
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(toEmail);
		mailMessage.setSubject(subject);
		mailMessage.setText(message);
		mailMessage.setFrom("java.gyan.mantra@gmail.com");

		if (mailMessage != null) {
			javaMailSender.send(mailMessage);
			response.setFlag(true);
			response.setResponse("Success");
		} else {
			response.setFlag(false);
			response.setResponse("Failure");
		}
		return response;
	}

	// Mail with attachement
	public EmailResponse sendEmailWithAttachement(String toEmail,
			String subject, String message, String fileName, String filePath) {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		EmailResponse response = new EmailResponse();
		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
					mimeMessage, true);
			mimeMessageHelper.setSubject(subject);
			mimeMessageHelper.setFrom("java.gyan.mantra@gmail.com");
			mimeMessageHelper.setTo(toEmail);
			mimeMessageHelper.setText(message);
			mimeMessageHelper.addAttachment(fileName, new File(filePath));
			javaMailSender.send(mimeMessageHelper.getMimeMessage());
			response.setFlag(true);
			response.setResponse("Success");
		} catch (MessagingException e) {
			response.setFlag(false);
			response.setResponse("Failure");
			System.out.println(e.getLocalizedMessage());
		}
		return response;
	}
}
