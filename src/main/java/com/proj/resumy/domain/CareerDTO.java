package com.proj.resumy.domain;

import java.time.LocalDateTime;

import lombok.Data;

// DTO : Data Transfer Object
// DAO 등과 연동하여 데이터를 실어 나르는 객체
// 필요한 객체(entity) 만큼 작성

@Data
public class CareerDTO {
	
	private int crId;   					// cr_crId
	private String crCompany;				// cr_crCompany
	private LocalDateTime crHiredate;		// cr_crHiredate
	private LocalDateTime crLeavedate;	// cr_crLeavedate
	private String crLvreason;			// cr_crLvreason
	private String crPost;				// cr_crcrPost
	private String crDept;				// cr_crDept
	private String crArea;				// cr_crArea
	private int crSalary;					// cr_crSalary
	private String crWork;				// cr_crWork
	private int memId;					// mem_id
	
	public CareerDTO() {
		super();
		System.out.println("CareerDTO() 객체 생성");
	}


	public CareerDTO(int crId, String crCompany, LocalDateTime crHiredate,LocalDateTime crLeavedate, String crLvreason, String crPost, String crDept, String crArea, int crSalary, String crWork, int memId) {
		super();
		this.crId = crId;
		this.crCompany = crCompany;
		this.crHiredate = crHiredate;
		this.crLeavedate = crLeavedate;
		this.crLvreason = crLvreason;
		this.crPost = crPost;
		this.crDept = crDept;
		this.crArea = crArea;
		this.crSalary = crSalary;
		this.crWork = crWork;
		this.memId = memId;
		System.out.println("IntroDTO(...) 객체 생성");
	}
}
