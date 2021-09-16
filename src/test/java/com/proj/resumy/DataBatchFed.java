package com.proj.resumy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

public class DataBatchFed {
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
	
	public static final String SQL_RESUMY_FED_INSERT = "insert into `intr_feedback` (fb_userid, fb_content, intr_id) values (?, ?, ?)";
	
	@Test
	// 회원정보 테이블
	void genDataMem() {
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERID, USERPW);
			
			// 테스트용 dummy 데이터 만들기
			pstmt = conn.prepareStatement(SQL_RESUMY_FED_INSERT);
			
			int num = 10;
			for(int i = 0; i < num; i++) {
				pstmt.setString(1, String.format("user%02d", i));
				pstmt.setString(2, String.format("content%02d", i));
				pstmt.setInt(3, i);
		
				cnt += pstmt.executeUpdate();
			}
			System.out.println(cnt + "개 의 피드백 데이터가 INSERT 되었습니다");
			
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
