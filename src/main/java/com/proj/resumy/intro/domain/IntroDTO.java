package com.proj.resumy.intro.domain;

import java.time.LocalDateTime;

import lombok.Data;

// DTO : Data Transfer Object
// DAO 등과 연동하여 데이터를 실어 나르는 객체
// 필요한 객체(entity) 만큼 작성


//자소서 DTO 김진섭
@Data
public class IntroDTO {
	private int id;   				// intr_id
	private String title;			// intr_title
	private LocalDateTime regdate;	// intr_regdate
	private boolean pub;			// intr_public
	private boolean fin;			// intr_finish
	private LocalDateTime modydate;	// mody_dtm
	private String userid;				// mem_userid
	
	public IntroDTO() {
		super();
		System.out.println("IntroDTO() 객체 생성");
	}


	public IntroDTO(int id, String title, LocalDateTime regdate, boolean pub, boolean fin, LocalDateTime modydate, String userid) {
		super();
		this.id = id;
		this.title = title;
		this.regdate = regdate;
		this.pub = pub;
		this.fin = fin;
		this.modydate = modydate;
		this.userid = userid;
		System.out.println("IntroDTO(...) 객체 생성");
	}
}
