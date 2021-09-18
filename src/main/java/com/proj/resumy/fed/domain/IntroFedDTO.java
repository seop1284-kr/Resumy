package com.proj.resumy.fed.domain;

import java.time.LocalDateTime;

import lombok.Data;

// 자소서 피드백 DTO 김진섭
@Data
public class IntroFedDTO {
	private int id;						// fb_id
	private String userid;				// fb_userid
	private String fb_content;			// fb_content
	private LocalDateTime fb_regdate;	// fb_regdate
	private int intr_id;				// intr_id
	
	public IntroFedDTO() {
		super();
		System.out.println("IntroFedDTO() 객체 생성");
	}

	public IntroFedDTO(int id, String userid, String fb_content, LocalDateTime fb_regdate, int intr_id) {
		super();
		this.id = id;
		this.userid = userid;
		this.fb_content = fb_content;
		this.fb_regdate = fb_regdate;
		this.intr_id = intr_id;
		System.out.println("IntroFedDTO(...) 객체 생성");

	}



}
