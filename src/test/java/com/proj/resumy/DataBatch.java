package com.proj.resumy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

class DataBatch {
	// JDBC 관련 기본 객체 변수들 선언
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null; // executeQuery(), SELECT 결과
	//int cnt = 0; // executeUpdate(), DML 결과

	// MySQL
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver"; // JDBC 드라이버 클래스
	public static final String URL = "jdbc:mysql://localhost:3306/resumy"; // DB 서버 정보
	public static final String USERID = "userdm"; // DB 사용자 계정 정보
	public static final String USERPW = "1234";
	
	public static final String SQL_RESUMY_MEM_INSERT = "insert into `hr_member` (mem_userid, mem_pw, mem_name, mem_email) values (?, ?, ?, ?)";
	public static final String SQL_RESUMY_AUTHORITY_INSERT = "insert into `hr_authority` (mem_userid, mem_auth) values (?, ?)";
	public static final String SQL_RESUMY_CAREER_INSERT = "insert into `hr_career` (cr_company, cr_hiredate, cr_leavedate, cr_post, mem_id) values (?, ?, ?, ?, ?)";
	public static final String SQL_RESUMY_SPEC_INSERT = "insert into `hr_spec_info` (spec_cat_cd, spec_name, spec_area, mody_dtm, reg_dtm, mem_id) values (?, ?, ?, ?, ?, ?)";
	
	public static final String SQL_RESUMY_FILE_INSERT = "insert into `hr_file` (file_name, file_volume, mem_id) values (?, ?, ?)";
	public static final String SQL_RESUMY_INTRO_INSERT = "insert into `hr_introduction` (intr_title, mem_userid) values (?, ?)";
	public static final String SQL_RESUMY_INTRO_C_INSERT = "insert into `hr_introduction_c` (intr_question, intr_content, intr_id) values (?, ?, ?)";
	public static final String SQL_RESUMY_FED_INSERT = "insert into `intr_feedback` (fb_userid, fb_content, intr_id) values (?, ?, ?)";
	public static final String SQL_RESUMY_QNAQ_INSERT = "insert into `hr_qna_q` (q_subject, q_content, mem_userid) values (?, ?, ?)";
	public static final String SQL_RESUMY_QNAA_INSERT = "insert into `hr_qna_a` (q_id, a_reply) values (?, ?)";
	
	public static final String[] SHCOOL = { "초등학교", "중학교", "고등학교", "대학교.대학권"};
	public static final String[] SUBJECTS = { "사이트 개선 문의합니다", "이용 문의합니다", "문제있습니다", "안녕하십니까", "회사 정보 보기 불편합니다" };
	public static final String[] CONTENTS = { "사이트 이용에 문제가 있는 것 같습니다. 개선해주세요.", "내용이 문제가 있습니다", "수업 진행에 있어 동그라미가 많습니다", "부침개는 동그랗지만 포카리는 맛있습니다" };
	public static final String[] REPLYS = { "문의 감사드립니다. 빠른 시일 내에 해결하겠습니다", "문의 감사드립니다. 언제나 이용 부탁드립니다", "기각합니다" };
	
	
	@Order(1)
	@Test
	void genData() {
		// 회원정보 테이블
		int cnt = 0; // executeUpdate(), DML 결과
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERID, USERPW);
			
			// 테스트용 dummy 데이터 만들기
			pstmt = conn.prepareStatement(SQL_RESUMY_MEM_INSERT);
			
			int num = 10;
			for(int i = 0; i < num; i++) {
				pstmt.setString(1, String.format("mem%02d", i));  
				pstmt.setString(2, "$2a$10$1iZtb1e1Xb81Wwm2NPcHTOzmbdyZDLLKnh9.wUsorFj/1t6RYsUKy");	// 비밀번호 aa
				pstmt.setString(3, String.format("person%02d", i));
				pstmt.setString(4, "a@email.com");
				cnt += pstmt.executeUpdate();
			}
			System.out.println(cnt + "개 의 회원정보 데이터가 INSERT 되었습니다");
			
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
		
		// 회원권한 테이블
		cnt = 0; // executeUpdate(), DML 결과
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERID, USERPW);
			
			// 테스트용 dummy 데이터 만들기
			pstmt = conn.prepareStatement(SQL_RESUMY_AUTHORITY_INSERT);
		
			int num = 10;
			for(int i = 0; i < num; i++) {
				
				pstmt.setString(1, String.format("mem%02d", i));  				// 회사명
				pstmt.setString(2, "ROLE_MEMBER");							// 재직일
				cnt += pstmt.executeUpdate();
				}
			System.out.println(cnt + "개 의 경력사항 데이터가 INSERT 되었습니다");
			
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
				
				
		// 경력사항 테이블
		cnt = 0; // executeUpdate(), DML 결과
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
				pstmt.setInt(5, i + 1);
				cnt += pstmt.executeUpdate();
				}
			System.out.println(cnt + "개 의 경력사항 데이터가 INSERT 되었습니다");
			
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
		
		// 회원학력사항 테이블
		cnt = 0; // executeUpdate(), DML 결과
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
				
				pstmt.setString(1, SHCOOL[rand.nextInt(SHCOOL.length)]);  			// '01: 초등학교 02: 중학교 03: 고등학교 04: 대학교.대학원'
				pstmt.setString(2, String.format("school%02d", i)); 				// 학교명
				pstmt.setString(3, String.format("area%02d", i)); 					// 지역명
				pstmt.setString(4, now.format(formatter));							// 등록일시
				pstmt.setString(5, now.format(formatter));							// 수정일시
				pstmt.setInt(6, i + 1);
				cnt += pstmt.executeUpdate();
				}
			System.out.println(cnt + "개 의 학력사항 데이터가 INSERT 되었습니다");
			
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
		
		// 파일 테이블
		cnt = 0; // executeUpdate(), DML 결과
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERID, USERPW);
			
			// 테스트용 dummy 데이터 만들기
			pstmt = conn.prepareStatement(SQL_RESUMY_FILE_INSERT);
			
			int num = 10;
			for(int i = 0; i < num; i++) {
				pstmt.setString(1, String.format("file%02d", i));  
				pstmt.setInt(2, i+500);
				pstmt.setInt(3, i + 1);
				cnt += pstmt.executeUpdate();
			}
			System.out.println(cnt + "개 의 파일 데이터가 INSERT 되었습니다");
			
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
		
		// 자기소개서 테이블
		cnt = 0; // executeUpdate(), DML 결과
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERID, USERPW);
			
			// 테스트용 dummy 데이터 만들기
			pstmt = conn.prepareStatement(SQL_RESUMY_INTRO_INSERT);
			
			int num = 10;
			for(int i = 0; i < num; i++) {
				pstmt.setString(1, String.format("title%02d", i));
				pstmt.setString(2, "mem01");
		
				cnt += pstmt.executeUpdate();
			}
			System.out.println(cnt + "개 의 자소서 데이터가 INSERT 되었습니다");
			
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
		
		// 자소서컨텐츠 테이블
		cnt = 0; // executeUpdate(), DML 결과
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERID, USERPW);
			
			// 테스트용 dummy 데이터 만들기
			pstmt = conn.prepareStatement(SQL_RESUMY_INTRO_C_INSERT);
			
			int num = 10;
			for(int i = 0; i < num; i++) {
				pstmt.setString(1, String.format("question%02d", i));  
				pstmt.setString(2, String.format("content%02d", i));  
				pstmt.setInt(3, i + 1);
				cnt += pstmt.executeUpdate();
			}
			System.out.println(cnt + "개 의 자소서컨텐츠 데이터가 INSERT 되었습니다");
			
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
		
		// 자기소개서 피드백 게시판
		cnt = 0; // executeUpdate(), DML 결과
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERID, USERPW);
			
			// 테스트용 dummy 데이터 만들기
			pstmt = conn.prepareStatement(SQL_RESUMY_FED_INSERT);
			
			int num = 10;
			for(int i = 0; i < num; i++) {
				pstmt.setString(1, String.format("user%02d", i));
				pstmt.setString(2, String.format("content%02d", i));
				pstmt.setInt(3, i + 1);
		
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
		
		// 고객센터 테이블
		cnt = 0; // executeUpdate(), DML 결과
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
				pstmt.setString(3, String.format("mem%02d", rand.nextInt(9)+1));
				cnt += pstmt.executeUpdate();
			}
			System.out.println(cnt + "개의 고객센터 데이터가 INSERT 되었습니다");
			
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
		
		// 고객센터 답글 테이블
		cnt = 0; // executeUpdate(), DML 결과
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERID, USERPW);
			
			// 테스트용 dummy 데이터 만들기
			pstmt = conn.prepareStatement(SQL_RESUMY_QNAA_INSERT);
			
			int num = 10;
			Random rand = new Random();
			for(int i = 0; i < num; i++) {
				pstmt.setInt(1, i + 1);
				pstmt.setString(2, REPLYS[rand.nextInt(REPLYS.length)]);  
				cnt += pstmt.executeUpdate();
			}
			System.out.println(cnt + "개 의 고객센터 답글 데이터가 INSERT 되었습니다");
			
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