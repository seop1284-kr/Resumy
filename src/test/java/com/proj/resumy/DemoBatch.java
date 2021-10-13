package com.proj.resumy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Random;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class DemoBatch {
	// JDBC 관련 기본 객체 변수들 선언
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;

	// MySQL
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver"; // JDBC 드라이버 클래스
	public static final String URL = "jdbc:mysql://localhost:3306/resumy"; // DB 서버 정보
	public static final String USERID = "userdm"; // DB 사용자 계정 정보
	public static final String USERPW = "1234";

	// Query
	// 회원 정보
	public static final String SQL_RESUMY_MEM_INSERT = "insert into `hr_member` (mem_userid, mem_pw, mem_name, mem_birthday, mem_email) values (?, ?, ?, ?, ?)";
	public static final String[][] MEM_INFO = { { "admin", "12345678", "관리자", "1993-06-08", "resumy.ggdm@gmail.com" },
			{ "seop1284", "12345678", "김진섭", "1993-06-08", "seop1284@gmail.com" },
			{ "kimpkoiw", "12345678", "김민수", "1993-06-08", "pkoiw@naver.com" },
			{ "hahbr88", "12345678", "하병노", "1993-06-08", "hahbr88@gmail.com" },
			{ "binigy97", "12345678", "노수빈", "1993-06-08", "binigy97@gmail.com" } };

	// 권한 정보
	public static final String SQL_RESUMY_AUTHORITY_INSERT = "insert into `hr_authority` (mem_userid, mem_auth) values (?, ?)";

	@Test
	void genData() {

		int cnt = 0; // executeUpdate(), DML 결과

		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERID, USERPW);

			// 회원정보 테이블
			pstmt = conn.prepareStatement(SQL_RESUMY_MEM_INSERT);
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

			for (int i = 0; i < MEM_INFO.length; i++) {
				pstmt.setString(1, MEM_INFO[i][0]);
				pstmt.setString(2, encoder.encode(MEM_INFO[i][1])); // 비밀번호 aa
				pstmt.setString(3, MEM_INFO[i][2]);
				pstmt.setString(4, MEM_INFO[i][3]);
				pstmt.setString(5, MEM_INFO[i][4]);
				cnt += pstmt.executeUpdate();
			}
			System.out.println(cnt + "개의 회원정보 데이터가 INSERT 되었습니다");

			// 회원권한 테이블
			cnt = 0;
			pstmt = conn.prepareStatement(SQL_RESUMY_AUTHORITY_INSERT);

			for (int i = 0; i < MEM_INFO.length; i++) {
				pstmt.setString(1, MEM_INFO[i][0]);
				pstmt.setString(2, "ROLE_MEMBER");
				cnt += pstmt.executeUpdate();
			}
			// 관리자 권한 추가
			pstmt.setString(1, MEM_INFO[0][0]);
			pstmt.setString(2, "ROLE_ADMIN");
			cnt += pstmt.executeUpdate();
			
			System.out.println(cnt + "개의 경력사항 데이터가 INSERT 되었습니다");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}