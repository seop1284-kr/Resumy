package com.proj.resumy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

import org.junit.jupiter.api.Test;

public class DataBatchQnaA {
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
	
	public static final String SQL_RESUMY_QNAA_INSERT = "insert into `hr_qna_a` (q_id, a_reply) values (?, ?)";
	
	public static final String[] REPLYS = { "문의 감사드립니다. 빠른 시일 내에 해결하겠습니다", "문의 감사드립니다. 언제나 이용 부탁드립니다", "기각합니다" };
	
	// 고객센터 답글 테이블
	@Test
	void genDataQnaA() {
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERID, USERPW);
			
			// 테스트용 dummy 데이터 만들기
			pstmt = conn.prepareStatement(SQL_RESUMY_QNAA_INSERT);
			
			int num = 10;
			Random rand = new Random();
			for(int i = 0; i < num; i++) {
				pstmt.setInt(1, i+1);
				pstmt.setString(2, REPLYS[rand.nextInt(REPLYS.length)]);  
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
