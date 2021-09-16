package com.proj.resumy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

import org.junit.jupiter.api.Test;

public class DataBatchQnaQ {
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
	
	public static final String SQL_RESUMY_QNAQ_INSERT = "insert into `hr_qna_q` (q_subject, q_content, mem_id) values (?, ?, ?)";
	
	public static final String[] SUBJECTS = { "사이트 개선 문의합니다", "이용 문의합니다", "문제있습니다", "안녕하십니까", "회사 정보 보기 불편합니다" };
	public static final String[] CONTENTS = { "사이트 이용에 문제가 있는 것 같습니다. 개선해주세요.", "내용이 문제가 있습니다", "수업 진행에 있어 동그라미가 많습니다", "부침개는 동그랗지만 포카리는 맛있습니다" };
	
	@Test
	// 고객센터 테이블
	void genDataQnaQ() {
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERID, USERPW);
			
			// 테스트용 dummy 데이터 만들기
			pstmt = conn.prepareStatement(SQL_RESUMY_QNAQ_INSERT);

			int num = 10;
			Random rand = new Random();
			for(int i = 0; i < num; i++) {
				pstmt.setString(1, SUBJECTS[rand.nextInt(SUBJECTS.length)]);  
				pstmt.setString(2, CONTENTS[rand.nextInt(CONTENTS.length)]);
				pstmt.setInt(3, (rand.nextInt(9)+1));
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
