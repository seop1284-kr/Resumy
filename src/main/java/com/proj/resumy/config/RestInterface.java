package com.proj.resumy.config;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RestInterface {

	// 반환 타입은 Call<타입>의 제네릭 형태
	@POST("api/test")
	Call<Object> apiTest(@Header("content-type") String contentType, @Body String params);

}
