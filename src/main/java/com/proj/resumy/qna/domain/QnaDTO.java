package com.proj.resumy.qna.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QnaDTO {
	private QnaQDTO qdto;
	private String name;
	private QnaADTO adto;
}
