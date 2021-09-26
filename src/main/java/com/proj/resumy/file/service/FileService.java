package com.proj.resumy.file.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
   @Value("${app.upload.dir:${user.home}}")
   private String uploadDir;

   public void fileUpload(MultipartFile multipartFile) {
      // File.seperator 는 OS종속적이다.
      // Spring에서 제공하는 cleanPath()를 통해서 ../ 내부 점들에 대해서 사용을 억제한다
      Path copyOfLocation = Paths.get(uploadDir + File.separator + StringUtils.cleanPath(multipartFile.getOriginalFilename()));
      try {
         // inputStream을 가져와서
         // copyOfLocation (저장위치)로 파일을 쓴다.
         // copy의 옵션은 기존에 존재하면 REPLACE(대체한다), 오버라이딩 한다
         Files.copy(multipartFile.getInputStream(), copyOfLocation, StandardCopyOption.REPLACE_EXISTING);
      } catch (IOException e) {
         e.printStackTrace();
      }

   }
}