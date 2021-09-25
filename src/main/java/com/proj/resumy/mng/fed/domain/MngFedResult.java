package com.proj.resumy.mng.fed.domain;

import java.util.List;

import com.proj.resumy.fed.domain.IntroFedDTO;
import com.proj.resumy.intro.domain.IntroDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MngFedResult {
	IntroDTO introDto;	// 자소서 정보
	IntroFedDTO fedDto;	// 피드백 정보
	
}
