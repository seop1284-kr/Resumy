package com.proj.resumy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Random;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class DataBatch {
   // JDBC 관련 기본 객체 변수들 선언
   Connection conn = null;
   Statement stmt = null;
   PreparedStatement pstmt = null;

   // MySQL
   public static final String DRIVER = "com.mysql.cj.jdbc.Driver"; // JDBC 드라이버 클래스
   public static final String URL = "jdbc:mysql://localhost:3306/resumy"; // DB 서버 정보
   public static final String USERID = "userdm"; // DB 사용자 계정 정보
   public static final String USERPW = "1234";
   
   public static final int NUM = 300;
   
   public static final String SQL_RESUMY_MEM_INSERT = "insert into `hr_member` (mem_userid, mem_pw, mem_name, mem_email) values (?, ?, ?, ?)";
   public static final String SQL_RESUMY_AUTHORITY_INSERT = "insert into `hr_authority` (mem_userid, mem_auth) values (?, ?)";
   public static final String SQL_RESUMY_ADMIN_INSERT = "INSERT INTO `hr_member` (mem_userid, mem_pw, mem_name, mem_email) VALUES ('admin', '$2a$10$1iZtb1e1Xb81Wwm2NPcHTOzmbdyZDLLKnh9.wUsorFj/1t6RYsUKy', 'admin', 'admin@admin')";
   public static final String SQL_RESUMY_CAREER_INSERT = "insert into `hr_career` (cr_company, cr_hiredate, cr_leavedate, cr_post,mem_userid) values (?, ?, ?, ?, ?)";
   public static final String SQL_RESUMY_SPEC_INSERT = "insert into `hr_spec_info` (spec_cat_cd, spec_name, spec_area, mem_userid) values (?, ?, ?, ?)";
   
   public static final String SQL_RESUMY_FILE_INSERT = "insert into `hr_file` (file_name, file_cname, file_volume, mem_userid) values (?, ?, ?, ?)";
   public static final String SQL_RESUMY_INTRO_INSERT = "insert into `hr_introduction` (intr_title, mem_userid, intr_public) values (?, ?, true)";
   public static final String SQL_RESUMY_INTRO_C_INSERT = "insert into `hr_introduction_c` (intr_question, intr_content, intr_id) values (?, ?, ?)";
   public static final String SQL_RESUMY_FED_INSERT = "insert into `intr_feedback` (fb_userid, fb_content, intr_id) values (?, ?, ?)";
   public static final String SQL_RESUMY_QNAQ_INSERT = "insert into `hr_qna_q` (q_subject, q_content, mem_userid) values (?, ?, ?)";
   public static final String SQL_RESUMY_QNAA_INSERT = "insert into `hr_qna_a` (q_id, a_reply) values (?, ?)";
   public static final String SQL_RESUMY_QNAQ_UPDATE = "UPDATE `hr_qna_q` SET `q_replyState` = true WHERE `q_id` = ?";
   
   public static final String[] SHCOOL = { "초등학교", "중학교", "고등학교", "대학교.대학권"};
   public static final String[] SUBJECTS = { "사이트 개선 문의합니다", "이용 문의합니다", "문제있습니다", "안녕하십니까", "회사 정보 보기 불편합니다" };
   public static final String[] CONTENTS = { "사이트 이용에 문제가 있는 것 같습니다. 개선해주세요.", "내용이 문제가 있습니다", "수업 진행에 있어 동그라미가 많습니다", "부침개는 동그랗지만 포카리는 맛있습니다" };
   public static final String[] REPLYS = { "문의 감사드립니다. 빠른 시일 내에 해결하겠습니다", "문의 감사드립니다. 언제나 이용 부탁드립니다", "기각합니다" };
   
   public static final String[] RESUMY_TITLES = {
		 "코리아IT아카데미",
		 "네이버(Naver)",
		 "엔씨소프트",
		 "넷마블",
		 "SAMSUNG"
   };
   
   public static final String[] RESUMY_QUESTIONS = {
		   "조직생활 중 다른 구성원들과의 갈등으로 어려움에 부딪힌 경험에 대해 간략히 설명하고, 긍정적인 변화를 이끌기 위해 본인이 기울인 노력과 그 결과에 대해 기술해 주십시오.",
		   "최근 3년 이내 본인의 부족한 부분을 발견하고 체계적인 과정이나 절차를 통해 극복한 경험이 있다면, 그 과정과 결과에 대해 구체적으로 기술하여 주십시오.",
		   "자기소개서",
		   "경력 및 경험기술서"
		   
		   
   };
   
   public static final String[] RESUMY_CONTENTS = {
		   "노래하며 작고 얼음이 같으며, 이상의 오아이스도 인생에 위하여 것이다. 가는 천자만홍이 아름답고 충분히 듣는다. 군영과 살았으며, 하는 불어 어디 과실이 앞이 동력은 아니다. 부패를 하였으며, 귀는 유소년에게서 보이는 맺어, 보배를 장식하는 같이, 그리하였는가? 방지하는 그들은 미묘한 열매를 부패뿐이다. 내려온 대중을 따뜻한 풍부하게 열락의 어디 구하지 두기 구하기 사막이다. 가는 행복스럽고 그들의 얼음과 하여도 목숨을 곳으로 바이며, 칼이다. 무엇이 곧 관현악이며, 튼튼하며, 노래하며 때까지 새 같이, 하는 것이다. 소담스러운 얼마나 피는 열락의 내는 실로 속에 싹이 있는가?"
		   ,"거친 우리의 힘차게 열락의 않는 이상은 황금시대다. 놀이 인생에 돋고, 오직 생의 인간은 것이다. 천지는 열락의 듣기만 사랑의 가슴에 같은 하여도 인간은 운다. 무엇을 풀밭에 생명을 용감하고 대중을 인생에 인간의 봄바람을 전인 것이다. 착목한는 바로 같은 내는 봄바람이다. 피고 맺어, 청춘에서만 위하여서, 사라지지 동산에는 못하다 칼이다. 그들의 곳으로 밥을 소금이라 위하여서, 목숨이 뼈 할지라도 아름다우냐? 청춘 어디 발휘하기 굳세게 부패를 불어 그들의 방지하는 칼이다. 열락의 이상의 뭇 거선의 아니더면, 곳이 소리다.이것은 그들의 봄바람이다. 청춘 원질이 열락의 이상은 봄날의 천고에 미묘한 힘있다."
		   ,"과실이 풀밭에 꽃이 내는 오아이스도 것이다. 대중을 살았으며, 피는 것이다. 우리는 위하여, 인생에 싹이 트고, 얼마나 그러므로 보이는 것이다. 청춘을 부패를 목숨이 못할 끝까지 가슴에 사라지지 우리는 사는가 약동하다. 우리 우는 지혜는 사람은 부패뿐이다. 하는 하여도 얼음 피가 눈이 것은 산야에 있다. 가장 이는 같은 이것이야말로 같지 바이며, 무엇을 청춘의 이성은 봄바람이다. 심장의 같이 위하여서, 타오르고 뿐이다. 싸인 찾아다녀도, 열매를 그러므로 거선의 있는 옷을 길지 듣는다. 타오르고 스며들어 얼마나 심장은 얼음에 붙잡아 그들은 고동을 아름다우냐? 것은 따뜻한 보이는 피가 보내는 있는 바이며, 찾아다녀도, 열락의 봄바람이다."
   };
   
   
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
         
         BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
         
         int num = 10;
         for(int i = 0; i < num; i++) {
            pstmt.setString(1, String.format("mem%02d", i));  
            pstmt.setString(2, encoder.encode("aa"));   // 비밀번호 aa
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
            pstmt.setString(1, String.format("mem%02d", i));
            pstmt.setString(2, "ROLE_MEMBER");
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
      
      // (관리자 계정)
      try {
    	  Class.forName(DRIVER);
	      conn = DriverManager.getConnection(URL, USERID, USERPW);
	      
	      // 관리자 계정 생성
	      stmt = conn.createStatement();
	      cnt = stmt.executeUpdate(SQL_RESUMY_ADMIN_INSERT);
	      
	      // 관리자 권한 부여
	      pstmt = conn.prepareStatement(SQL_RESUMY_AUTHORITY_INSERT);
	      String[] auth = {"ROLE_ADMIN", "ROLE_MEMBER"};
	      
	      for(int i = 0; i < auth.length; i++) {
	    	  pstmt.setString(1, "admin");
	    	  pstmt.setString(2, auth[i]);
	    	  pstmt.executeUpdate();
	      }
	      System.out.println(cnt + "개 의 관리자 계정이 INSERT 되었습니다.");
	      
      } catch(Exception e) {
    	  e.printStackTrace();
      } finally {
          try {
              if(pstmt != null) pstmt.close();
              if(stmt != null) stmt.close();
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
//         LocalDate now = LocalDate.now();
         //LocalDate leavedate = LocalDate.now();
         
//         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
         //SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         
         int num = 10;
         for(int i = 0; i < num; i++) {
            
            pstmt.setString(1, String.format("company%02d", i));              // 회사명
            pstmt.setString(2, String.format("start%02d", i));                     // 재직일
            pstmt.setString(3, String.format("end%02d", i));      // 퇴사일 추가 해야함   // 퇴사일
            pstmt.setString(4, String.format("post%02d", i));               // 직급/직책
            pstmt.setString(5, String.format("mem%02d", i));
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
         
         int num = 10;
         Random rand = new Random();
         for(int i = 0; i < num; i++) {
            
            pstmt.setString(1, SHCOOL[rand.nextInt(SHCOOL.length)]);           // '01: 초등학교 02: 중학교 03: 고등학교 04: 대학교.대학원'
            pstmt.setString(2, String.format("school%02d", i));             // 학교명
            pstmt.setString(3, String.format("area%02d", i));                // 지역명
            pstmt.setString(4, "mem01");
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
//      파일 업로드 다운로드 삭제 테스트를 위해 파일 테이블 배치는 주석처리 함
//      
//      // 파일 테이블
//      cnt = 0; // executeUpdate(), DML 결과
//      try {
//         Class.forName(DRIVER);
//         conn = DriverManager.getConnection(URL, USERID, USERPW);
//         
//         // 테스트용 dummy 데이터 만들기
//         pstmt = conn.prepareStatement(SQL_RESUMY_FILE_INSERT);
//         
//         int num = 10;
//         for(int i = 0; i < num; i++) {
//			pstmt.setString(1, String.format("file%02d", i));  
//			pstmt.setString(2, String.format("changed%02d", i));  
//			pstmt.setInt(3, i+500);
//			pstmt.setString(4, "mem01");
//			cnt += pstmt.executeUpdate();
//		}
//         System.out.println(cnt + "개 의 파일 데이터가 INSERT 되었습니다");
//         
//      } catch(Exception e) {
//         e.printStackTrace();
//      } finally {
//         try {
//            if(pstmt != null) pstmt.close();
//            if(conn != null) conn.close();
//         } catch(Exception e) {
//            e.printStackTrace();
//         }
//      }
//      
      
      // 자기소개서 테이블
      cnt = 0; // executeUpdate(), DML 결과
      try {
         Class.forName(DRIVER);
         conn = DriverManager.getConnection(URL, USERID, USERPW);
         
         // 테스트용 dummy 데이터 만들기
         pstmt = conn.prepareStatement(SQL_RESUMY_INTRO_INSERT);
         Random rand = new Random();
         
         for(int i = 0; i < NUM; i++) {
            pstmt.setString(1, RESUMY_TITLES[rand.nextInt(RESUMY_TITLES.length)]);
            pstmt.setString(2, String.format("mem%02d",  rand.nextInt(9)+1));
      
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
         Random rand = new Random();
         
         for(int i = 0; i < NUM; i++) {
            pstmt.setString(1, RESUMY_QUESTIONS[rand.nextInt(RESUMY_QUESTIONS.length)]);  
            pstmt.setString(2, RESUMY_CONTENTS[rand.nextInt(RESUMY_CONTENTS.length)]);  
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
         
         for(int i = 0; i < NUM; i = i + 2) {
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

         Random rand = new Random();
         for(int i = 0; i < NUM; i++) {
            pstmt.setString(1, SUBJECTS[rand.nextInt(SUBJECTS.length)]);  
            pstmt.setString(2, CONTENTS[rand.nextInt(CONTENTS.length)]);
            pstmt.setString(3, String.format("mem%02d", rand.nextInt(9)+1));
            cnt += pstmt.executeUpdate();
         }
         System.out.println(cnt + "개 의 고객센터 데이터가 INSERT 되었습니다");
         
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
         
         Random rand = new Random();
         for(int i = 1; i < NUM; i = i + 2) {
            pstmt.setInt(1, i);
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
      
      // 고객센터 테이블 (노수빈) : 답변 상태 업데이트
      cnt = 0;
      try {
         Class.forName(DRIVER);
         conn = DriverManager.getConnection(URL, USERID, USERPW);
         
         // 테스트용 dummy 데이터 만들기
         pstmt = conn.prepareStatement(SQL_RESUMY_QNAQ_UPDATE);
         
         for(int i = 1; i < NUM; i = i + 2) {
            pstmt.setInt(1, i);
            cnt += pstmt.executeUpdate();
         }
         System.out.println(cnt + "개 의 고객센터 데이터가 UPDATE 되었습니다");
         
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