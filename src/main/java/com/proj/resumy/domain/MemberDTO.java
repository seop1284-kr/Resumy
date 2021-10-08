package com.proj.resumy.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Data;

@Data
public class MemberDTO {
	private int id;			// mem_id
	private String userid;	// mem_userid
	private String pw;		// mem_pw
	private String name;	// mem_name
	private String email;	// mem_email
	private int gender;		// mem_gender
	private String phone;	// mem_phone
	private LocalDate birthday;// mem_birthday
	private String address;	// mem_address
	private boolean career;	// mem_career
	private LocalDateTime regdtm;	// reg_dtm
	
	public String getBirthday() {
		if(birthday == null) {
			return null;
		}
		return this.birthday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	
	public String getRegdtm() {
		if(regdtm == null) {
			regdtm = LocalDateTime.now();
		}
		return this.regdtm.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
	}
	
	public void setBirthday(String birthday) {
		if (birthday.equals("")) {
			return;
		}
		LocalDate date = LocalDate.parse(birthday, DateTimeFormatter.ISO_DATE);
		
		this.birthday = date;
	}
	
}
