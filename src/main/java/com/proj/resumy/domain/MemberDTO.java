package com.proj.resumy.domain;

import java.time.LocalDateTime;

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
	private char[] birthday;// mem_birthday
	private String address;	// mem_address
	private boolean career;	// mem_career
	private LocalDateTime regdtm;	// reg_dtm
	private LocalDateTime modydtm;	// mody_dtm
	
	public boolean getLev() {
		return lev;
	}

	
}
