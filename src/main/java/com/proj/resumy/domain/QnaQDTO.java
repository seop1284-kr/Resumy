package com.proj.resumy.domain;

import java.time.LocalDateTime;

import lombok.Data;

// DTO : Data Transfer Object
// DAO 등과 연동하여 데이터를 실어 나르는 객체
// 필요한 객체(entity) 만큼 작성


// QnaDTO(고객센터 페이지) 노수빈
@Data
public class QnaQDTO {
	private int id;   				// q_id
	private String subject;			// q_subject
	private String content;			// q_content
	private LocalDateTime regdate;	// q_regdate
	private int mid;				// mem_id
	
	public QnaQDTO() {
		super();
		System.out.println("QnaQDTO() 객체 생성");
	}
}
