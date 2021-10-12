package com.proj.resumy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import retrofit2.Call;
import retrofit2.Response;
import com.proj.resumy.config.RestInterface;
import com.proj.resumy.domain.NewsInfo;
import com.proj.resumy.domain.RecruitInfo;

@Service
public class APIService {
	
	private static final String CONTENT_TYPE = "Application/xml";
	
	@Autowired
	@Qualifier("indexService")
	RestInterface indexService;
	
	@Autowired
	@Qualifier("newsService")
	RestInterface newsService;
	
	// 채용정보
	public Response<RecruitInfo> recruitAPI() throws Exception {
	
		Call<RecruitInfo> call = indexService.getRecruitInfo(CONTENT_TYPE);
		// API 호출
		Response<RecruitInfo> response = call.execute();
		
		return response;
		
	}
	
	// 뉴스정보
	public Response<NewsInfo> newsAPI(String keyword) throws Exception {
		Call<NewsInfo> call2 = newsService.getNewsInfo(CONTENT_TYPE, keyword, 6, 1, "sim");
		Response<NewsInfo> response = call2.execute();
		
		return response;
	}
	
}
