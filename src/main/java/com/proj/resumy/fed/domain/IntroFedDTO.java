package com.proj.resumy.fed.domain;

import java.time.LocalDateTime;

import lombok.Data;

// 자소서 피드백 DTO 김진섭
@Data
public class IntroFedDTO {
	private int id;						// fb_id
	private String userid;				// fb_userid
	private String content;			// fb_content
	private LocalDateTime regdate;	// fb_regdate
	private int iid;				// intr_id
	
	public IntroFedDTO() {
		super();
		System.out.println("IntroFedDTO() 객체 생성");
	}

	public IntroFedDTO(int id, String userid, String content, LocalDateTime regdate, int iid) {
		super();
		this.id = id;
		this.userid = userid;
		this.content = content;
		this.regdate = regdate;
		this.iid = iid;
		System.out.println("IntroFedDTO(...) 객체 생성");

	}



}
