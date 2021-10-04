package com.proj.resumy.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberList extends MemberResult {
 	@JsonProperty("data")
	List<MemberDTO> list;  // 데이터 목록
		
}