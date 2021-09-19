package com.proj.resumy.qna.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Data;

// DTO : Data Transfer Object
// DAO 등과 연동하여 데이터를 실어 나르는 객체
// 필요한 객체(entity) 만큼 작성


// QnaADTO(고객센터 답글) 노수빈
@Data
public class QnaADTO {
	private int id;   				// q_id
	private String reply;			// a_reply
	private LocalDateTime regdate;	// a_regdate
	
	public String getRegdate() {
		return this.regdate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
}
