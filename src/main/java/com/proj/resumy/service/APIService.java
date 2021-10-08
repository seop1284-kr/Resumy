package com.proj.resumy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import retrofit2.Call;
import retrofit2.Response;
import com.proj.resumy.config.RestInterface;
import com.proj.resumy.domain.RecruitInfo;

@Service
public class APIService {
	
	private static final String CONTENT_TYPE = "Application/xml";
	
	@Autowired
	@Qualifier("restService")
	RestInterface restService;
	
	public Response<RecruitInfo> apiTest() throws Exception {
	
		Call<RecruitInfo> call = restService.apiTest(CONTENT_TYPE);
		
		// API 호출
		Response<RecruitInfo> response = call.execute();
		
		return response;
		
	}
	
}
