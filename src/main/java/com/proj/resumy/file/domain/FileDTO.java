package com.proj.resumy.file.domain;

import java.time.LocalDateTime;

import lombok.Data;

//AjaxFileController (파일관리) 하병노

@Data
public class FileDTO {
	private int id; // file_id
	private String name; // file_name
	private int volume; // file_volume
	private LocalDateTime regdate; // file_regdate
	private String memo; // file_memo
	private String userid; // mem_userid

	
	
	public FileDTO() {
		super();
		System.out.println("fileDTO() 객체 생성");
	}



	public FileDTO(int id, String name, int volume, LocalDateTime regdate, String memo, String userid) {
		super();
		this.id = id;
		this.name = name;
		this.volume = volume;
		this.regdate = regdate;
		this.memo = memo;
		this.userid = userid;
		System.out.println("fileDTO(...) 객체 생성");
	}

}
