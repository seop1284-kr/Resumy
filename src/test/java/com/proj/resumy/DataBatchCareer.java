package com.proj.resumy;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DataBatchCareer {
	// JDBC 관련 기본 객체 변수들 선언
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null; // executeQuery(), SELECT 결과
	int cnt = 0; // executeUpdate(), DML 결과

	// MySQL
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver"; // JDBC 드라이버 클래스
	public static final String URL = "jdbc:mysql://localhost:3306/resumy"; // DB 서버 정보
	public static final String USERID = "userdm"; // DB 사용자 계정 정보
	public static final String USERPW = "1234";
	
	public static final String SQL_RESUMY_CAREER_INSERT = "insert into `hr_career` (cr_company, cr_hiredate, cr_leavedate, cr_post, mem_id) values (?, ?, ?, ?, ?)";
	
	// 경력사항 테이블
	@Test
	void genDataCar() {
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERID, USERPW);
			
			// 테스트용 dummy 데이터 만들기
			pstmt = conn.prepareStatement(SQL_RESUMY_CAREER_INSERT);
			LocalDate now = LocalDate.now();
			//LocalDate leavedate = LocalDate.now();
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			//SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			int num = 10;
			for(int i = 0; i < num; i++) {
				
				pstmt.setString(1, String.format("company%02d", i));  				// 회사명
				pstmt.setString(2, now.format(formatter));							// 재직일
				pstmt.setString(3, now.format(formatter));		// 퇴사일 추가 해야함	// 퇴사일
				pstmt.setString(4, String.format("post%02d", i));					// 직급/직책
				pstmt.setInt(5, i);
				cnt += pstmt.executeUpdate();
				}
			System.out.println(cnt + "개 의 데이터가 INSERT 되었습니다");
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
