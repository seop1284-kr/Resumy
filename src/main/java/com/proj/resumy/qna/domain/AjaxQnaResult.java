package com.proj.resumy.qna.domain;

import lombok.Getter;
import lombok.Setter;

// AjaxQnaResult (고객센터 페이지, 고객센터 답변 페이지 Ajax 호출 결과) 노수빈
@Getter
@Setter
public class AjaxQnaResult {
	String status;	// 처리 결과
	String message; // 처리 메세지
	int count;		// 처리된 데이터 개수
}
