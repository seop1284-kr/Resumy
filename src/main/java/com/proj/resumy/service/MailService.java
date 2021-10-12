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
			mailHandler.setFrom("resumy.ggdm@gmail.com");
			// 제목
			mailHandler.setSubject("RESUMY 회원가입 인증 메일입니다.");
			// HTML Layout
			String htmlContent = "아래의 인증번호 6자리를 인증번호 입력칸에 넣어주시고, '확인하기' 버튼을 눌러주셔야 인증이 완료됩니다.<br> 인증번호 : " + result;
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
