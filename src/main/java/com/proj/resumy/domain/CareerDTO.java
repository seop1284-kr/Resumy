package com.proj.resumy.domain;

import java.time.LocalDateTime;

import lombok.Data;

// DTO : Data Transfer Object
// DAO 등과 연동하여 데이터를 실어 나르는 객체
// 필요한 객체(entity) 만큼 작성

@Data
public class CareerDTO {
	
	private int id;   					// cr_id
	private String company;				// cr_company
	private LocalDateTime hiredate;		// cr_hiredate
	private LocalDateTime leavedate;	// cr_leavedate
	private String lvreason;			// cr_lvreason
	private String post;				// cr_post
	private String dept;				// cr_dept
	private String area;				// cr_area
	private int salary;					// cr_salary
	private String work;				// cr_work
	private int mid;					// mem_id
	
	public CareerDTO() {
		super();
		System.out.println("CareerDTO() 객체 생성");
	}


	public CareerDTO(int id, String company, LocalDateTime hiredate,LocalDateTime leavedate, String lvreason, String post, String dept, String area, int salary, String work, int mid) {
		super();
		this.id = id;
		this.company = company;
		this.hiredate = hiredate;
		this.leavedate = leavedate;
		this.lvreason = lvreason;
		this.post = post;
		this.dept = dept;
		this.area = area;
		this.salary = salary;
		this.work = work;
		this.mid = mid;
		System.out.println("IntroDTO(...) 객체 생성");
	}
}
