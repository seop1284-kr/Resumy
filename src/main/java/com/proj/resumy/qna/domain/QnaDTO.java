package com.proj.resumy.qna.domain;

import lombok.Data;

@Data
public class QnaDTO {
	private QnaQDTO qdto;
	private String name;
	private QnaADTO adto;
}
