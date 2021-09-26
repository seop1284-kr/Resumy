//package com.proj.resumy.file.controller;
//
//import java.util.Enumeration;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.multipart.MultipartHttpServletRequest;
//import org.springframework.web.multipart.MultipartRequest;
//import org.springframework.web.servlet.ModelAndView;
//
//@Controller
//public class FileUploadController {
//	// 파일 저장 위치 지정
//	private static final String CURR_FILE_REPO_PATH = "C:\\DevRoot\\Resumy\\Resumy\\files";
//	
//	@RequestMapping(value="/form")
//	public String form() {		//업로드 창인 uploadFrom.jsp를 반환
//		return "uploadForm";
//	}
//	
//	@RequestMapping(value="/upload", method = RequestMethod.POST)
//	public ModelAndView upload(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)throws Exception{
//		multipartRequest.setCharacterEncoding("utf-8");
//		
//		Map map = new HashMap(); //매개변수 정보와 파일 정보를 저장할 Map 생성
//		Enumeration enu=multipartRequest.getParameterNames();
//		
//		// 전송된 매개변수 값을 키/밸류 로 Map에 저장
//		while(enu.hasMoreElements()) {
//			String name = (String) enu.nextElement();
//			String value = multipartRequest.getParameter(name);
//			map.put(name, value);
//		}
//		
//		List filelist= fileProcess(multipartRequest);
//		map.put("filelist", filelist);
//		
//		
//		return null;
//	}
//}
