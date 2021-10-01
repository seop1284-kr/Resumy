package com.proj.resumy.file.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.proj.resumy.file.domain.FileDAO;
import com.proj.resumy.file.domain.FileDTO;

@Service
public class FileService {
	
	
	@Value("${app.upload.dir:${user.home}}")
	private String uploadDir;

	FileDAO fileDao;

	@Autowired
	public void setFileDao(FileDAO fileDao) {
		this.fileDao = fileDao;
	}
	public FileDTO fileUpload(MultipartFile multipartFile, UserDetails userDetails, String memo) {
		// File.seperator 는 OS종속적이다.
		// Spring에서 제공하는 cleanPath()를 통해서 ../ 내부 점들에 대해서 사용을 억제한다

		
		// 사용자 파일 저장 디렉토리
		Path userDir = Paths
				.get(uploadDir + File.separator + userDetails.getUsername());
		
		// 저장 파일 원본 이름
		String originalName = multipartFile.getOriginalFilename();
	
		// 저장 파일 이름 랜덤 생성(uuid_originalName)
		UUID uuid = UUID.randomUUID();
		String fileSystemName = uuid.toString() + "_" + originalName;
		
		// 파일 저장 위치
		Path copyOfLocation = Paths
				.get(userDir + File.separator + StringUtils.cleanPath(fileSystemName));
		
		FileDTO fileDTO = new FileDTO(); 
		
		try {
			if (!Files.isDirectory(userDir)) { // 사용자 파일 저장 디렉토리가 없다면
				Files.createDirectories(userDir); // 폴더 생성
			}
			
			// inputStream을 가져와서
			// copyOfLocation (저장위치)로 파일을 쓴다.
			// copy의 옵션은 기존에 존재하면 REPLACE(대체한다), 오버라이딩 한다
			Files.copy(multipartFile.getInputStream(), copyOfLocation);
			fileDTO.setName(originalName);
			fileDTO.setCname(fileSystemName);
			fileDTO.setVolume((int) multipartFile.getSize());
			fileDTO.setMemo(memo);
			fileDTO.setUserid(userDetails.getUsername());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return fileDTO;
	}
	
	
	public int fileDelete(UserDetails userDetails, int[] id) {
		Path userDir = Paths
				.get(uploadDir + File.separator + userDetails.getUsername());
		System.out.println(userDir);
		
		for(int i=0; i<id.length; i++) {
			
			FileDTO dto = fileDao.selectByFid(id[i]);
			System.out.println(dto.getCname());
			File f = new File(userDir.toString(), dto.getCname());   // 삭제 대상 파일의 File 객체
			
			System.out.println("삭제시도--> " + f.getAbsolutePath());
			
			if(f.exists()) {
				if(f.delete()) {
					System.out.println("삭제 성공");
				} else {
					System.out.println("삭제 실패");
				}
			} else {
				System.out.println("파일이 존재하지 않습니다.");
			}
			
		}
		
		return 0;
		
	}
}
