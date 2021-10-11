package com.proj.resumy.config;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jaxb.JaxbConverterFactory;

@Configuration
public class RetrofitConfig {
	
	private static String API_FOR_INDEX = "https://openapi.work.go.kr/";	
	
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
	
	@Bean(name="companyService")
	public RestInterface companyService(@Qualifier("commonRetrofit") Retrofit retrofit) {
		return retrofit.create(RestInterface.class);
	}
	
}
