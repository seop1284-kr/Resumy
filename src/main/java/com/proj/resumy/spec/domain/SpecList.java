package com.proj.resumy.spec.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpecList extends SpecResult {
 	@JsonProperty("data")
	List<SpecDTO> list;  // 데이터 목록
		
}