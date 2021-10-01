package com.proj.resumy.qna.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

// AjaxQnaPaging(고객센터 페이지, 고객센터 답변 페이지 페이징) 노수빈
@Getter
@Setter
public class AjaxQnaPaging extends AjaxQnaResult {
	@JsonProperty("page")
	int page;	// 현재 페이지 번호
	@JsonProperty("pagerows")
	int pageRows;	// 한 페이지 당 보여지는 데이터 개수
	@JsonProperty("totalcnt")
	int totalCnt;	// 전체 데이터 개수
	@JsonProperty("totalpage")
	int totalPage;	// 전체 페이지 개수
	@JsonProperty("writepages")
	int writePage;	// 한 페이징 당 페이지 개수
	@JsonProperty("data")
	List<QnaDTO> list;	// 데이터
}
