package com.proj.resumy.career.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CareerList extends CareerResult {
 	@JsonProperty("data")
	List<CareerDTO> list;  // 데이터 목록
		
}