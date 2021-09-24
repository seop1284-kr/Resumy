package com.proj.resumy.intro.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.proj.resumy.fed.domain.IntroFedDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IntroResult {
	IntroDTO intro;				// 선택 자소서 정보
	List<IntroConDTO> conList;  // 선택 자소서 내용 리스트
	List<IntroFedDTO> fedList;	// 선택 자소서 피드백 리스트 
	
}
