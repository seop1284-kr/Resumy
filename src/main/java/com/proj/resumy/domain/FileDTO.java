package com.proj.resumy.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class FileDTO {
	private int id; // file_id
	private String name; // file_name
	private int volume; // file_volume
	private LocalDateTime regdate; // file_regdate
	private String memo; // file_memo
	private int mid; // mem_id

	
	
	public FileDTO() {
		super();
		System.out.println("fileDTO() 객체 생성");
	}



	public FileDTO(int id, String name, int volume, LocalDateTime regdate, String memo, int mid) {
		super();
		this.id = id;
		this.name = name;
		this.volume = volume;
		this.regdate = regdate;
		this.memo = memo;
		this.mid = mid;
		System.out.println("fileDTO(...) 객체 생성");
	}

}