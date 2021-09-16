package com.proj.resumy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.junit.jupiter.api.Test;

public class DataBatchSpec {
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
	
	public static final String SQL_RESUMY_SPEC_INSERT = "insert into `hr_spec_info` (spec_id, spec_cat_cd, spec_name, spec_area, mody_dtm, reg_dtm, mem_id) values (?, ?, ?, ?, ?, ?, ?)";
	
	public static final String[] SHCOOL = { "초등학교", "중학교", "고등학교", "대학교.대학권"};
	
	// 회원학력사항 테이블
	@Test
	void genDataSpec() {
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERID, USERPW);
			
			// 테스트용 dummy 데이터 만들기
			pstmt = conn.prepareStatement(SQL_RESUMY_SPEC_INSERT);
			LocalDate now = LocalDate.now();
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			
			int num = 10;
			Random rand = new Random();
			for(int i = 0; i < num; i++) {
				
				pstmt.setInt(1, i);
				pstmt.setString(2, SHCOOL[rand.nextInt(SHCOOL.length)]);  			// '01: 초등학교 02: 중학교 03: 고등학교 04: 대학교.대학원'
				pstmt.setString(3, String.format("school%02d", i)); 				// 학교명
				pstmt.setString(4, String.format("area%02d", i)); 					// 지역명
				pstmt.setString(5, now.format(formatter));							// 등록일시
				pstmt.setString(6, now.format(formatter));							// 수정일시
				pstmt.setInt(7, i);
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
