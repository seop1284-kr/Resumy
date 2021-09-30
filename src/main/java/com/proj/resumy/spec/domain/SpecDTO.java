package com.proj.resumy.spec.domain;

import java.time.LocalDateTime;

import lombok.Data;

// DTO : Data Transfer Object
// DAO 등과 연동하여 데이터를 실어 나르는 객체
// 필요한 객체(entity) 만큼 작성

@Data
public class SpecDTO {

private int id;   				// spec_id
private String cat;				// spec_cat_cd '01: 초등학교 02: 중학교 03: 고등학교 04: 대학교.대학원'
private String name;			// spec_name
private String area;			// spec_area
private String major;			// spec_major
private String university;		// spec_university '01: 2,3년제 02: 4년제  03: 대학원(석사) 04: 대학원(박사)'
private LocalDateTime regdate;	// reg_dtm
private LocalDateTime modydate;	// mody_dtm
private String userid;			// mem_userid

public SpecDTO() {
	super();
	System.out.println("SpecDTO() 객체 생성");
}


public SpecDTO(int id,  String cat, String name, String area, String major, String university, LocalDateTime regdate, LocalDateTime modydate, String userid) {
	super();
	this.id = id;
	this.cat = cat;
	this.name = name;
	this.area = area;
	this.major = major;
	this.university = university;
	this.regdate = regdate;
	this.modydate = modydate;
	this.userid = userid;
	System.out.println("Spec(...) 객체 생성");
}
}
