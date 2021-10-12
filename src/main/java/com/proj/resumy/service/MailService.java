package com.proj.resumy.service;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	
	private final JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
	
	public void mailSend(HttpSession session, String userEmail) {
		try {
			MailHandler mailHandler = new MailHandler(javaMailSenderImpl);
			Random random = new Random(System.currentTimeMillis());
			long start = System.currentTimeMillis();
			
			int result = 100000 + random.nextInt(900000);
			
			// 받는 사람
			mailHandler.setTo(userEmail);
			// 보내는 사람
			mailHandler.setFrom("seop12842@gmail.com");
			// 제목
			mailHandler.setSubject("인증번호입니다.");
			// HTML Layout
			String htmlContent = "<p>인증번호 : + " + result + "<p>";
			mailHandler.setText(htmlContent, true);
			
			mailHandler.send();
			
			long end = System.currentTimeMillis();
			
			session.setAttribute("" + userEmail, result);
			System.out.println("실행 시간 : " + (end - start) / 1000.0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean emailCertification(HttpSession session, String userEmail, int inputCode) {
		try {
			int generationCode = (int) session.getAttribute(userEmail);
			
			if (generationCode == inputCode) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	
}
