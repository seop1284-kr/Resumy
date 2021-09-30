package com.proj.resumy.career.domain;

import java.time.LocalDateTime;

import lombok.Data;

// DTO : Data Transfer Object
// DAO 등과 연동하여 데이터를 실어 나르는 객체
// 필요한 객체(entity) 만큼 작성

@Data
public class CareerDTO {
	
	private String company;				// cr_company
	private String hiredate;			// cr_crHiredate
	private String leavedate;	    	// cr_crLeavedate
	private String lvreason;			// cr_crLvreason
	private String post;				// cr_crcrPost
	private String dept;				// cr_crDept
	private String companyArea;			// cr_crArea
	private int salary;					// cr_crSalary
	private String work;				// cr_crWork
	private int id;   					// cr_id
	private String userid;				// mem_userid
	
	public CareerDTO() {
		super();
		System.out.println("CareerDTO() 객체 생성");
	}


	public CareerDTO(String company, String hiredate,String leavedate, String lvreason, String post, String dept, String companyArea, int salary, String work, int id, String userid) {
		super();
		this.company = company;
		this.hiredate = hiredate;
		this.leavedate = leavedate;
		this.lvreason = lvreason;
		this.post = post;
		this.dept = dept;
		this.companyArea = companyArea;
		this.salary = salary;
		this.work = work;
		this.id = id;
		this.userid = userid;
		System.out.println("Career(...) 객체 생성");
	}
}
