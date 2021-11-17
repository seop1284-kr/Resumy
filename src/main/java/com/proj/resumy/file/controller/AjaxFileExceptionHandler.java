package com.proj.resumy.file.controller;

import javax.naming.SizeLimitExceededException;

import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

@RestControllerAdvice(annotations = RestController.class)
public class AjaxFileExceptionHandler {
	
	@ExceptionHandler(MultipartException.class)
	@ResponseStatus(value = HttpStatus.PAYLOAD_TOO_LARGE)
	@ResponseBody
	public String handleMultipartException(MultipartException ex) {
		//SizeLimitExceededException d;
		System.out.println("handleMultipartException()");
		ex.printStackTrace();
		return "fileTooLarge";
//		return "redirect:/myp";
	}

	@ExceptionHandler(SizeLimitExceededException.class)
	@ResponseStatus(value = HttpStatus.PAYLOAD_TOO_LARGE)
	public ResponseEntity<ServiceException> handleSizeLimitExceededException(SizeLimitExceededException ex) {
		//SizeLimitExceededException d;
		System.out.println("handleSizeLimitExceededException()");
		ex.printStackTrace();
		return null;
	}
	
}
