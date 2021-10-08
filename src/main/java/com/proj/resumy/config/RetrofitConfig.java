package com.proj.resumy.config;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.jaxb.JaxbConverterFactory;

@Configuration
public class RetrofitConfig {
	
	private static String API_TEST_URL = "https://openapi.work.go.kr/";
	
	@Bean(name="okHttpClient")
	public OkHttpClient okHttpClient() {
		return new OkHttpClient.Builder().connectTimeout(20, TimeUnit.SECONDS)
				.writeTimeout(60, TimeUnit.SECONDS)
				.readTimeout(60, TimeUnit.SECONDS)
				.build();
	}
	
	@Bean(name="commonRetrofit")
	public Retrofit retrofit(@Qualifier("okHttpClient") OkHttpClient client) {
		return new Retrofit.Builder().baseUrl(API_TEST_URL)
				.addConverterFactory(JaxbConverterFactory.create())
				.client(client).build();
	}
	
	@Bean(name="restService")
	public RestInterface restService(@Qualifier("commonRetrofit") Retrofit retrofit) {
		return retrofit.create(RestInterface.class);
	}
	
}
