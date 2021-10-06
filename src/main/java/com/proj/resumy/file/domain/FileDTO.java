package com.proj.resumy.file.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

//AjaxFileController (파일관리) 하병노

@Data
public class FileDTO {
	private int id; // file_id
	private String name; // file_name
	private String cname; // file_cname
	private int volume; // file_volume
	private LocalDateTime regdate; // file_regdate
	private String memo; // file_memo
	private String userid; // mem_userid

	public String getRegdate() {
		if(regdate == null) {
			regdate = LocalDateTime.now();
		}
		return this.regdate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
	}
	
	// 사이즈 정형화
	public String size2String() {
        Integer unit = 1024;
        if (volume < unit) {
            return String.format("(%d B)", volume);
        }
        int exp = (int) (Math.log(volume) / Math.log(unit));
 
        return String.format("(%.0f %s)", volume / Math.pow(unit, exp), "KMGTPE".charAt(exp - 1));
    }
	
	public FileDTO() {
		super();
//		System.out.println("fileDTO() 객체 생성");
	}



	public FileDTO(int id, String name, String cname, int volume, LocalDateTime regdate, String memo, String userid) {
		super();
		this.id = id;
		this.name = name;
		this.name = cname;
		this.volume = volume;
		this.regdate = regdate;
		this.memo = memo;
		this.userid = userid;
		System.out.println("fileDTO(...) 객체 생성");
	}

}
