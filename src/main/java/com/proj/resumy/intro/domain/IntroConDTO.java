package com.proj.resumy.intro.domain;

import java.time.LocalDateTime;

import lombok.Data;

// 자소서 내용 DTO 김진섭
@Data
public class IntroConDTO {
	private int id;				// intr_c_id
	private String question;	// intr_question
	private String content;		// intr_content
	private int iid;			// intr_id
	
	public IntroConDTO() {
		super();
		//System.out.println("IntroConDTO() 객체 생성");
	}

	public IntroConDTO(int id, String question, String content, int iid) {
		super();
		this.id = id;
		this.question = question;
		this.content = content;
		this.iid = iid;
		//System.out.println("IntroConDTO(...) 객체 생성");

	}


}
