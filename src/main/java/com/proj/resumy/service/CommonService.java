package com.proj.resumy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import retrofit2.Call;
import retrofit2.Response;
import com.proj.resumy.config.RestInterface;

@Service
public class CommonService {
	
	private static final String CONTENT_TYPE = "application/xml";
	
//	@Autowired
//	@Qualifier("okHttpClient")
//	RestInterface okHttpClient;
//
//	@Autowired
//	@Qualifier("commonRetrofit")
//	RestInterface commonRetrofit;

	@Autowired
	@Qualifier("restService")
	RestInterface restService;
	
	public void apiTest() throws Exception {
	
		// HTTP Body값에 넘겨줄 XML 데이터
		String xml = "생략 ...";
		
		Call<Object> call = restService.apiTest(CONTENT_TYPE, xml);
		// API 호출
		Response<Object> response = call.execute();
	}
	
}
