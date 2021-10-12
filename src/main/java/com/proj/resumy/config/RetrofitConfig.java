package com.proj.resumy.config;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.jaxb.JaxbConverterFactory;

@Configuration
public class RetrofitConfig {
	
	private static String API_FOR_INDEX = "https://openapi.work.go.kr/";
	private static String API_FOR_NEWS = "https://openapi.naver.com/";
	
	@Bean(name="okHttpClient")
	public OkHttpClient okHttpClient() {
		return new OkHttpClient.Builder().connectTimeout(20, TimeUnit.SECONDS)
				.writeTimeout(60, TimeUnit.SECONDS)
				.readTimeout(60, TimeUnit.SECONDS)
				.build();
	}

	
	@Bean(name="commonRetrofit")
	public Retrofit retrofit(@Qualifier("okHttpClient") OkHttpClient client) {
		return new Retrofit.Builder().baseUrl(API_FOR_INDEX)
				.addConverterFactory(JaxbConverterFactory.create())
				.client(client).build();
	}
	
	@Bean(name="indexService")
	public RestInterface indexService(@Qualifier("commonRetrofit") Retrofit retrofit) {
		return retrofit.create(RestInterface.class);
	}
	
	// 헤더 설정
	// news
	@Bean(name="newsOkHttpClient")
	public OkHttpClient newsOkHttpClient() {
		return new OkHttpClient.Builder()
				.addInterceptor(chain -> {
				    Request request = chain.request()
				    		.newBuilder()
				    		.addHeader("X-Naver-Client-Id", "2e512yp04pPGicjh6nRs")
				    		.addHeader("X-Naver-Client-Secret", "XFrkzznL5S")
				    		.build();
				    return chain.proceed(request);}
				)
				.connectTimeout(20, TimeUnit.SECONDS)
				.writeTimeout(60, TimeUnit.SECONDS)
				.readTimeout(60, TimeUnit.SECONDS)
				.build();
	}
	
	// url에 헤더 붙이기
	// news
	@Bean(name="newsRetrofit")
	public Retrofit newsRetrofit(@Qualifier("newsOkHttpClient") OkHttpClient client) {
		return new Retrofit.Builder().baseUrl(API_FOR_NEWS)
				.addConverterFactory(JaxbConverterFactory.create())
				.client(client).build();
	}
	
	// api 실행
	// news
	@Bean(name="newsService")
	public RestInterface companyService(@Qualifier("newsRetrofit") Retrofit retrofit) {
		return retrofit.create(RestInterface.class);
	}
	
}
