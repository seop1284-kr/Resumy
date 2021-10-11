package com.proj.resumy.config;

import com.proj.resumy.domain.RecruitInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

//공채 속보 API
/*
기업로고 <regLogImgNm>
채용업체명 <empBusiNm>
채용 분야 <empWantedTypeNm>
지원자격(경력) <empWantedCareerNm>
지원자격(학력) <empWantedEduNm>
근무지 <workRegionNm>
채용시작일 <empWantedStdt>
채용마감일 <empWantedEndt>

채용기업 홈페이지 <empWantedHomepg>
채용사이트URL <empWantedHomepgDetail>

URL :
https://openapi.work.go.kr/opi/opi/opia/dhsOpenEmpInfoAPI.do?authKey=WNKTM9J2OCLG1EI81VOVZ2VR1HJ&callTp=L&returnType=XML&&startPage=1&display=100
*/


public interface RestInterface {
	
	// 워크넷 공채 속보 API Key
	String API_KEY = "WNKTM9J2OCLG1EI81VOVZ2VR1HJ";
			
	// 반환 타입은 Call<타입>의 제네릭 형태
	@GET("opi/opi/opia/dhsOpenEmpInfoAPI.do?authKey=" + API_KEY + "&callTp=L&returnType=XML&&startPage=1&display=100&sortOrderBy=asc")
	Call<RecruitInfo> getRcruitInfo(@Header("content-type") String contentType);

}
