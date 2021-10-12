package com.proj.resumy.service;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailHandler {
	private JavaMailSenderImpl sender;
	private MimeMessage message;
	private MimeMessageHelper messageHelper;

	// 생성자
	public MailHandler(JavaMailSenderImpl jSender) throws MessagingException {
		jSender.setHost("smtp.gmail.com");
		jSender.setPort(587);
		jSender.setUsername("resumy.ggdm");
		jSender.setPassword("resumy!1");

		/*
		 * <prop key="mail.transport.protocol">smtp</prop> <prop
		 * key="mail.smtp.auth">true</prop> <prop
		 * key="mail.smtp.starttls.enable">true</prop> <prop
		 * key="mail.debug">true</prop>
		 */
        
        
		Properties props =new Properties();
		props.put("mail.smtp.auth", true);
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.starttls.required", true);
		props.put("mail.debug", true);

		
		jSender.setJavaMailProperties(props);
		
		this.sender = jSender;
		


		
		message = jSender.createMimeMessage();
		messageHelper = new MimeMessageHelper(message, true, "UTF-8");
	}

	// 보내는 사람 이메일
	public void setFrom(String fromAddress) throws MessagingException {
		messageHelper.setFrom(fromAddress);
	}

	// 받는 사람 이메일
	public void setTo(String email) throws MessagingException {
		messageHelper.setTo(email);
	}

	// 제목
	public void setSubject(String subject) throws MessagingException {
		messageHelper.setSubject(subject);
	}

	// 메일 내용
	public void setText(String text, boolean useHtml) throws MessagingException {
		messageHelper.setText(text, useHtml);
	}

	// 첨부 파일
	public void setAttach(String displayFileName, String pathToAttachment) throws MessagingException, IOException {
		File file = new ClassPathResource(pathToAttachment).getFile();
		FileSystemResource fsr = new FileSystemResource(file);

		messageHelper.addAttachment(displayFileName, fsr);
	}

	// 이미지 삽입
	public void setInline(String contentId, String pathToInline) throws MessagingException, IOException {
		File file = new ClassPathResource(pathToInline).getFile();
		FileSystemResource fsr = new FileSystemResource(file);

		messageHelper.addInline(contentId, fsr);
	}

	// 발송
	public void send() {
		try {
			sender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}