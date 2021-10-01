package com.proj.resumy.file.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import com.proj.resumy.file.domain.FileDTO;
import com.proj.resumy.file.service.AjaxFileService;
import com.proj.resumy.file.service.FileService;

// AjaxFileController (파일관리) 하병노

@RestController
@RequestMapping("/fileAjax")
public class AjaxFileController {
	private AjaxFileService ajaxFileService;

	@Autowired
	public void setAjaxFileService(AjaxFileService ajaxFileService) {
		this.ajaxFileService = ajaxFileService;
	}
	
	private FileService fileService;

	@Autowired
	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

	public AjaxFileController() {
		System.out.println("AjaxFileController() 생성");
	}

	// 로그인한 특정 회원(mem_id)의 첨부파일 리스트

	@RequestMapping("/filelist")
	public List<FileDTO> filelist(Model model, Authentication authentication) {

		// 로그인한 사람의 정보를 담은 객체
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		List<FileDTO> filelist = new ArrayList<>();
		filelist = ajaxFileService.selectByUserID(userDetails.getUsername());

		return filelist;

//		// test용 id가 1인 사람
//		List<FileDTO> filelist = ajaxFileService.selectByMid(1);
//		System.out.println("파일리스트출력" + filelist);
//		FileDTO[] arr = new FileDTO[filelist.size()];
//
//		return filelist.toArray(arr);

	}

//	// 특정 첨부파일 (file_id) 한개 SELECT
//	@RequestMapping("/{mid}{fid}")
//	public FileDTO view(@PathVariable int id) {
//		FileDTO fileinfo = ajaxFileService.selectByFid(id);
//		
//		return null;
//		
//	}

	

	
	// 특정 회원(mem_id)의 새파일 업로드
	@PostMapping("")
	public int write(MultipartFile file, String memo, Authentication authentication) {
		int result = 0;
		int maxPostSize = 5 * 1024 * 1024; // 최대용량, 5M byte : 1Kbyte = 1024 byte, 1Mbyte = 1024Kbyte
		String encoding = "utf-8";    // 인코딩

		
		// 로그인한 사람의 정보를 담은 객체
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		FileDTO fileDTO = new FileDTO();
		
		
		fileDTO = fileService.fileUpload(file, userDetails, memo);
		result = ajaxFileService.insert(fileDTO);		
		
		return result;
	}
	
	// 특정 파일(file_id) .zip 으로 다운로드?
	
	@Value("${app.upload.dir:${user.home}}")
	private String uploadDir;
	
	@GetMapping("/download/{ids}")
	public void download(
			HttpServletRequest request,
			HttpServletResponse response,
			Authentication authentication,
			@PathVariable int[] ids) throws IOException {
		
		// 사용자 정보
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		int bufferSize = 1024 * 2;
		String ouputName = URLEncoder.encode((userDetails.getUsername() + "_첨부파일"), "utf-8");
		ZipOutputStream zos = null;
		
		
		try {
			if (request.getHeader("User-Agent").indexOf("MSIE 5.5") > -1) {
				response.setHeader("Content-Disposition", "filename=" + ouputName + ".zip" + ";");
			} else {
				response.setHeader("Content-Disposition", "attachment; filename=" + ouputName + ".zip" + ";");
			}
			
			response.setHeader("Content-Transfer-Encoding", "binary");
			
		    OutputStream os = response.getOutputStream();
		    zos = new ZipOutputStream(os); // ZipOutputStream
		    zos.setLevel(8); // 압축 레벨 - 최대 압축률은 9, 디폴트 8
		    BufferedInputStream bis = null;
		    
		    for(int i = 0; i <ids.length ; i++ ) {
		    	FileDTO fileDto = ajaxFileService.selectByFid(ids[i]); //
		    	File file = new File(uploadDir + File.separator + userDetails.getUsername(), 
		    			fileDto.getCname());
		    	
		    	bis = new BufferedInputStream(new FileInputStream(file));
		    	ZipEntry zentry = new ZipEntry(fileDto.getName());
		    	zentry.setTime(file.lastModified());
		    	zos.putNextEntry(zentry);
		    	
		    	byte[] buffer = new byte[bufferSize];
		    	int cnt = 0;
		    	while ((cnt = bis.read(buffer, 0, bufferSize)) != -1) {
		    		zos.write(buffer, 0, cnt);
		    	}
		    	zos.closeEntry();
		    }
		               
		    zos.close();
		    bis.close();
		                
		                
		} catch(Exception e){
		    e.printStackTrace();
		}


	}
 

	

	// 특정 파일(file_id) 삭제
	@DeleteMapping("")
	public String delete(int[] id, Authentication authentication) {
		String result = "fail";
		int count = 0;
	
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		fileService.fileDelete(userDetails, id);
		count = ajaxFileService.deleteByIds(id);

		System.out.println(id);
		if (count == 1) {
			result = "success";
		}
		
		return result;
		
		
		
		
	}


	
	
}
